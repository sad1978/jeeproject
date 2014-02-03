package com.adlanda.prototipo.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adlanda.prototipo.web.form.EmpleadoGrid;
import com.adlanda.prototipo.web.form.Message;
import com.adlanda.prototipo.web.util.UrlUtil;

import es.microforum.model.Empleado;
import es.microforum.model.Empresa;
import es.microforum.serviceapi.EmpleadoService;
import es.microforum.serviceapi.EmpresaService;

@RequestMapping("/empleados")
@Controller
public class EmpleadoController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MessageSource messageSource;

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private EmpresaService empresaService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing empleados");
		return "empleados/list";
	}

	/**
	 * Support data for front-end grid
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EmpleadoGrid listGrid(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order) {
		logger.info("Listing contacts for grid with page: {}, rows: {}", page,
				rows);
		logger.info("Listing contacts for grid with sort: {}, order: {}",
				sortBy, order);

		PageRequest pageRequest = null;

		pageRequest = new PageRequest(page - 1, rows);

		EmpleadoGrid empleadoGrid = new EmpleadoGrid();
		Page<Empleado> empleados;
		empleados = empleadoService.findAll(pageRequest);
		empleadoGrid.setCurrentPage(empleados.getNumber() + 1);
		empleadoGrid.setTotalPages(empleados.getTotalPages());
		empleadoGrid.setTotalRecords(empleados.getTotalElements());
		empleadoGrid.setEmpleadoData(empleados.getContent());

		return empleadoGrid;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") String id, Model uiModel,
			HttpServletRequest request) throws IOException {
		Empleado empleado = empleadoService.findById(id);
		uiModel.addAttribute("empleado", empleado);
		if (empleado != null) {
			String fileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/images/" + empleado.getDni();
			if (empleado.getImagen() != null) {
				OutputStream outputStream = null;
				outputStream = new FileOutputStream(fileName);
				System.out.println(empleado.getImagen().length);
				outputStream.write(empleado.getImagen(), 0,
						empleado.getImagen().length);
				outputStream.close();
			} else {
				File f = new File(fileName);
				if (f.exists())
					f.delete();
			}
		}
		return "empleados/show";
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model uiModel) {
		Empleado empleado = empleadoService.findById(id);
		uiModel.addAttribute("empleado", empleado);
		List<Empresa> empresas = empresaService.findAll();
		uiModel.addAttribute("empresas", empresas);
		return "empleados/update";
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(@RequestParam(value = "empresa") String nif,
			@Valid Empleado empleado, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, Locale locale) {
		logger.info("Updating empleado");
		List<Empresa> empresas = empresaService.findAll();
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute(
					"message",
					new Message("error", messageSource.getMessage(
							"contact_save_fail", new Object[] {}, locale)));
			uiModel.addAttribute("empleado", empleado);
			uiModel.addAttribute("empresas", empresas);
			return "empleados/update";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", messageSource.getMessage(
						"contact_save_success", new Object[] {}, locale)));
		Empresa empresa = empresaService.findById(nif);
		empleado.setEmpresa(empresa);
		Empleado empleado2 = empleadoService.findById(empleado.getDni());
		empleado.setImagen(empleado2.getImagen());
		empleadoService.update(empleado);
		return "redirect:/empleados/"
				+ UrlUtil.encodeUrlPathSegment(empleado.getDni(),
						httpServletRequest);
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		Empleado empleado = new Empleado();
		uiModel.addAttribute("empleado", empleado);
		List<Empresa> empresas = empresaService.findAll();
		uiModel.addAttribute("empresas", empresas);
		return "empleados/create";
	}

	@PreAuthorize("hasRole('supervisor')")
	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestParam(value = "empresa") String nif,
			@Valid Empleado empleado, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, Locale locale) {
		List<Empresa> empresas = empresaService.findAll();
		logger.info("Creating empleado");
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute(
					"message",
					new Message("error", messageSource.getMessage(
							"contact_save_fail", new Object[] {}, locale)));
			uiModel.addAttribute("empleado", empleado);
			uiModel.addAttribute("empresas", empresas);
			return "empleados/create";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", messageSource.getMessage(
						"contact_save_success", new Object[] {}, locale)));

		logger.info("Contacto id: " + empleado.getDni());
		Empresa empresa = empresaService.findById(nif);
		empleado.setEmpresa(empresa);
		empleadoService.insert(empleado);
		return "redirect:/empleados/"
				+ UrlUtil.encodeUrlPathSegment(empleado.getDni(),
						httpServletRequest);
	}

	@PreAuthorize("hasRole('supervisor')")
	@RequestMapping(value = "/{id}", params = "del", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id, Model uiModel,
			RedirectAttributes redirectAttributes, Locale locale,
			HttpServletRequest httpServletRequest) {
		Empleado empleado = empleadoService.findById(id);
		empleadoService.delete(empleado);
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", messageSource.getMessage(
						"contact_delete_success", new Object[] {}, locale)));

		logger.info("Contacto id: " + empleado.getDni());
		return "redirect:/empleados/"
				+ UrlUtil.encodeUrlPathSegment(empleado.getDni(),
						httpServletRequest);
	}
}
