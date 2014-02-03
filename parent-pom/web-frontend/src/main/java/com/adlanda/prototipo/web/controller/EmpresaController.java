package com.adlanda.prototipo.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adlanda.prototipo.web.form.EmpleadoGrid;
import com.adlanda.prototipo.web.form.EmpresaGrid;
import com.adlanda.prototipo.web.form.Message;
import com.adlanda.prototipo.web.util.UrlUtil;

import es.microforum.model.Empleado;
import es.microforum.model.Empresa;
import es.microforum.serviceapi.EmpleadoService;
import es.microforum.serviceapi.EmpresaService;

@RequestMapping("/empresas")
@Controller
public class EmpresaController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MessageSource messageSource;

	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private EmpresaService  empresaService;
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(Date.class, editor);
    }	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing empresas");
		return "empresas/list";
	}

	/**
	 * Support data for front-end grid
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EmpresaGrid listGrid(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order) {
		logger.info("Listing enterprises for grid with page: {}, rows: {}", page,
				rows);
		logger.info("Listing enterprises for grid with sort: {}, order: {}",
				sortBy, order);

		PageRequest pageRequest = null;

		pageRequest = new PageRequest(page - 1, rows);

		EmpresaGrid empresaGrid = new EmpresaGrid();
		Page<Empresa> empresas;
			empresas = empresaService.findAll(pageRequest);
		empresaGrid.setCurrentPage(empresas.getNumber() + 1);
		empresaGrid.setTotalPages(empresas.getTotalPages());
		empresaGrid.setTotalRecords(empresas.getTotalElements());
		empresaGrid.setEmpresaData(empresas.getContent());

		return empresaGrid;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") String id, Model uiModel) {
		Empresa empresa = empresaService.findById(id);
		if(empresa != null){
			uiModel.addAttribute("empresa", empresa);
			DateTime fecha = new DateTime(empresa.getFechaInicioActividades());
			uiModel.addAttribute("fecha",fecha);
		}
		return "empresas/show";
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model uiModel) {
		Empresa empresa = empresaService.findById(id);
		uiModel.addAttribute("empresa", empresa);
		DateTime fecha = new DateTime(empresa.getFechaInicioActividades());
		uiModel.addAttribute("fecha",fecha);
		return "empresas/update";
	}
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(@Valid Empresa empresa, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, Locale locale) {
		
		logger.info("Updating empleado");
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute(
					"message",
					new Message("error", messageSource.getMessage(
							"empresa_save_fail", new Object[] {}, locale)));
			uiModel.addAttribute("empresa", empresa);
			return "empresas/update";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", messageSource.getMessage(
						"empresa_save_success", new Object[] {}, locale)));
		empresaService.update(empresa);
		return "redirect:/empresas/"
				+ UrlUtil.encodeUrlPathSegment(empresa.getNif(),
						httpServletRequest);
	}
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		Empresa empresa = new Empresa();
		uiModel.addAttribute("empresa", empresa);
		return "empresas/create";
	}
	@PreAuthorize("hasRole('supervisor')")
	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Empresa empresa, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, Locale locale) {
		logger.info("Creating empleado");
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute(
					"message",
					new Message("error", messageSource.getMessage(
							"empresa_save_fail", new Object[] {}, locale)));
			uiModel.addAttribute("empresa", empresa);
			return "empresas/create";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", messageSource.getMessage(
						"empresa_save_success", new Object[] {}, locale)));

		logger.info("Contacto id: " + empresa.getNif());
		empresaService.insert(empresa);
		return "redirect:/empresas/"
				+ UrlUtil.encodeUrlPathSegment(empresa.getNif(),
						httpServletRequest);
	}
	@PreAuthorize("hasRole('supervisor')")
	@RequestMapping(value = "/{id}", params = "del", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id, Model uiModel,RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest httpServletRequest) {
		Empresa empresa = empresaService.findById(id);
		empresaService.delete(empresa);
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", messageSource.getMessage(
						"empresa_delete_success", new Object[] {}, locale)));

		logger.info("Contacto id: " + empresa.getNif());
		return "redirect:/empresas/"
		+ UrlUtil.encodeUrlPathSegment(empresa.getNif(),
				httpServletRequest);
	}
}
