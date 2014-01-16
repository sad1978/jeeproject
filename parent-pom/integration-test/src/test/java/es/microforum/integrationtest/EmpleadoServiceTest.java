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
	EmpresaService  empresaService;
//cambios
	@Test
	public void test() {
		//GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		//ctx.load("classpath:spring-data-app-context.xml");
		//ctx.refresh();

		//EmpleadoService empleadoService = ctx.getBean("jpaEmpleadoService", EmpleadoService.class);
		Empleado empleado = new Empleado();
		empleado.setDni("dni7");
		empleado.setNombre("nombre7");
		empleadoService.insert(empleado);
		
		empleado = empleadoService.findById("dni1");
		System.out.println(empleado);
		List <Empleado> empleados = empleadoService.findAll(); 
		for(Empleado empleadob: empleados){
			System.out.println(empleadob);
		}
		empleado.setNombre("nomModificado");
		empleadoService.update(empleado);
		empleados = empleadoService.findAll(); 
		for(Empleado empleadob: empleados){
			System.out.println(empleadob);
		}
		empleadoService.delete(empleado);
		empleados = empleadoService.findAll(); 
		for(Empleado empleadob: empleados){
			System.out.println(empleadob);
		}
	
	}

}
