package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class EmpleadoServiceTest {

	@Autowired
	EmpleadoService empleadoService;
	@Autowired
	EmpresaService empresaService;
	private static final Logger logger = LoggerFactory.getLogger(EmpleadoServiceTest.class);
	@Test
	public void findTest(){
		Empleado empleado = empleadoService.findById("dniprueba1");

		if(empleado == null){
			empleado=new Empleado();
			empleado.setDni("dniprueba1");
			empleado.setNombre("nombreprueba1");
			empleadoService.insert(empleado);
		}
		Empleado empleado2 = empleadoService.findById("dniprueba2");
		if(empleado2!=null){
			empleadoService.delete(empleado2);
		}
		empleado = empleadoService.findById("dniprueba1");
		assertTrue(empleado != null);
		empleado = empleadoService.findById("dniprueba2");
		assertTrue(empleado == null);
	}
	@Test
	public void findAllTest(){
		List<Empleado> empleados = empleadoService.findAll();
		Empleado empleado=new Empleado();
		empleado.setDni("dniprueba1");
		empleado.setNombre("nombreprueba1");
		empleado.setVersion(0);
		empleadoService.insert(empleado);
		List<Empleado> empleados2 = empleadoService.findAll();	
		assertTrue(empleados2.size() > empleados.size());
	}
	@Test
	public void updateTest() {
		Empleado empleado = empleadoService.findById("dniprueba1");

		if(empleado == null){
			empleado=new Empleado();
			empleado.setDni("dniprueba1");
			empleado.setNombre("nombreprueba1");
			empleadoService.insert(empleado);
		}
		empleado = empleadoService.findById("dniprueba1");
		assertTrue(empleado.getNombre().equals("nombreprueba1"));
		empleado.setNombre("nombreprueba2");
		empleadoService.update(empleado);
		empleado = empleadoService.findById("dniprueba1");
		assertTrue(empleado.getNombre().equals("nombreprueba2"));
	
	}
	@Test
	public void insertTest(){
		Empleado empleado = new Empleado();
		empleado.setDni("dniprueba1");
		empleado.setNombre("nombreprueba1");
		empleado.setCantidadHoras(8.0);
		empleado.setDireccion("direccion1");
		empleado.setEmpleadocol("cajero");
		empleado.setSalarioAnual(12000.0);
		empleado.setTipoEmpleado("Contrato laboral");
		empleado.setValorHora(5.0);
		Empresa empresa = empresaService.findById("nifprueba1");
		if(empresa != null){
			empleado.setEmpresa(empresa);
		}
		else{
			empresa = new Empresa();
			empresa.setNif("nifprueba1");
			empresa.setVersion(0);
			empresaService.insert(empresa);
		}
		empleadoService.insert(empleado);
		empleado = empleadoService.findById("dniprueba1");
		assertTrue(empleado != null);
	}
	
	@Test
	public void deleteTest(){
		Empleado empleado = empleadoService.findById("dniprueba1");
		if(empleado == null){
			empleado=new Empleado();
			empleado.setDni("dniprueba1");
			empleado.setNombre("nombre1");
			empleadoService.insert(empleado);
		}
		empleado = empleadoService.findById("dniprueba1");
		assertTrue(empleado != null);
		empleadoService.delete(empleado);
		empleado = empleadoService.findById("dniprueba1");
		assertTrue(empleado == null);
	}
	@Test
	public void findAllPageable(){
		Empleado empleado = new Empleado();
		empleado.setDni("dniprueba1");
		empleado.setVersion(0);
		empleadoService.insert(empleado);
		empleado=new Empleado();
		empleado.setDni("dniprueba2");
		empleadoService.insert(empleado);
		empleado=new Empleado();
		empleado.setDni("dniprueba3");
		empleadoService.insert(empleado);
		PageRequest pageRequest = null;
		pageRequest = new PageRequest(0, 2);
		Page<Empleado> empleados = empleadoService.findAll(pageRequest);
		assertTrue(empleados.getSize() == 2);
	}

}
