package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.microforum.model.Empleado;
import es.microforum.model.Empresa;
import es.microforum.serviceapi.EmpleadoService;
import es.microforum.serviceapi.EmpresaService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-data-app-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback=true)
@Transactional()
public class EmpresaServiceTest {

	@Autowired
	EmpleadoService empleadoService;
	@Autowired
	EmpresaService empresaService;

	@Test
	public void findTest(){
		Empresa empresa = empresaService.findById("nifprueba1");

		if(empresa == null){
			empresa=new Empresa();
			empresa.setNif("nifprueba1");
			empresa.setNombre("nombre1");
			empresaService.insert(empresa);
		}
		Empresa empresa2 = empresaService.findById("nifprueba5");
		if(empresa2!=null){
			empresaService.delete(empresa2);
		}
		empresa = empresaService.findById("nifprueba1");
		assertTrue(empresa != null);
		empresa = empresaService.findById("nifprueba5");
		assertTrue(empresa == null);
	}
	@Test
	public void findAllTest(){
		List<Empresa> empresas = empresaService.findAll();
		Empresa empresa=new Empresa();
		empresa.setNif("nifprueba1");
		empresa.setNombre("nombre1");
		empresaService.insert(empresa);
		List<Empresa> empresas2 = empresaService.findAll();	
		assertTrue(empresas2.size() > empresas.size());
	}
	@Test
	public void updateTest() {
		Empresa empresa = empresaService.findById("nifprueba1");

		if(empresa == null){
			empresa=new Empresa();
			empresa.setNif("nifprueba1");
			empresa.setNombre("nombre1");
			empresaService.insert(empresa);
		}
		empresa = empresaService.findById("nifprueba1");
		assertTrue(empresa.getNombre().equals("nombre1"));
		empresa.setNombre("nombre2");
		empresaService.update(empresa);
		empresa = empresaService.findById("nifprueba1");
		assertTrue(empresa.getNombre().equals("nombre2"));
	
	}
	@Test
	public void insertTest(){
		Empresa empresa = new Empresa();
		empresa.setNif("nifprueba1");
		empresa.setNombre("nombre1");
		empresa.setFechaInicioActividades(new Date());
		empresa.setDireccionFiscal("dirFiscal1");
		empresaService.insert(empresa);
		empresa = empresaService.findById("nifprueba1");
		assertTrue(empresa != null);		
	}
	
	@Test
	public void deleteTest(){
		Empresa empresa = empresaService.findById("nifprueba1");
		if(empresa == null){
			empresa=new Empresa();
			empresa.setNif("nifprueba1");
			empresa.setNombre("nombre1");
			empresaService.insert(empresa);
		}
		empresa = empresaService.findById("nifprueba1");
		assertTrue(empresa != null);
		empresaService.delete(empresa);
		empresa = empresaService.findById("nifprueba1");
		assertTrue(empresa == null);
	}

}
