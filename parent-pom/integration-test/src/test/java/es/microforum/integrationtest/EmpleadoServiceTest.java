package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.util.List;

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
public class EmpleadoServiceTest {

	@Autowired
	EmpleadoService empleadoService;
	@Autowired
	EmpresaService empresaService;
	
	@Test
	public void findTest(){
		Empleado empleado = empleadoService.findById("dni1");

		if(empleado == null){
			empleado=new Empleado();
			empleado.setDni("dni1");
			empleado.setNombre("nombre1");
			empleadoService.insert(empleado);
		}
		Empleado empleado2 = empleadoService.findById("dni5");
		if(empleado!=null){
			empleadoService.delete(empleado2);
		}
		empleado = empleadoService.findById("dni1");
		assertTrue(empleado != null);
		empleado = empleadoService.findById("dni5");
		assertTrue(empleado == null);
	}
	@Test
	public void findAllTest(){
		List<Empleado> empleados = empleadoService.findAll();
		Empleado empleado=new Empleado();
		empleado.setDni("dni1");
		empleado.setNombre("nombre1");
		empleadoService.insert(empleado);
		List<Empleado> empleados2 = empleadoService.findAll();	
		assertTrue(empleados2.size() > empleados.size());
	}
	@Test
	public void updateTest() {
		Empleado empleado = empleadoService.findById("dni1");

		if(empleado == null){
			empleado=new Empleado();
			empleado.setDni("dni1");
			empleado.setNombre("nombre1");
			empleadoService.insert(empleado);
		}
		empleado = empleadoService.findById("dni1");
		assertTrue(empleado.getNombre().equals("nombre1"));
		empleado.setNombre("nombre2");
		empleadoService.update(empleado);
		empleado = empleadoService.findById("dni1");
		assertTrue(empleado.getNombre().equals("nombre2"));
	
	}
	@Test
	public void insertTest(){
		Empleado empleado = new Empleado();
		empleado.setDni("dni1");
		empleado.setNombre("nombre1");
		empleado.setCantidadHoras(8.0);
		empleado.setDireccion("direccion1");
		empleado.setEmpleadocol("cajero");
		empleado.setSalarioAnual(12000.0);
		empleado.setTipoEmpleado("Contrato laboral");
		empleado.setValorHora(5.0);
		Empresa empresa = empresaService.findById("nif2");
		if(empresa != null)
			empleado.setEmpresa(empresa);
		empleadoService.insert(empleado);
		empleado = empleadoService.findById("dni1");
		assertTrue(empleado != null);
	}
	
	@Test
	public void deleteTest(){
		Empleado empleado = empleadoService.findById("dni1");
		if(empleado == null){
			empleado=new Empleado();
			empleado.setDni("dni1");
			empleado.setNombre("nombre1");
			empleadoService.insert(empleado);
		}
		empleado = empleadoService.findById("dni1");
		assertTrue(empleado != null);
		empleadoService.delete(empleado);
		empleado = empleadoService.findById("dni1");
		assertTrue(empleado == null);
	}

}
