package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.microforum.model.Empleado;
import es.microforum.serviceapi.EmpleadoService;
import es.microforum.serviceapi.SueldoService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-data-app-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback=true)
@Transactional()
public class SueldoServiceTest {
	@Autowired
	SueldoService sueldoService;
	@Autowired
	EmpleadoService empleadoService;
	@Test
	public void test() {
		double sueldoTotal=0, sueldoTotalCambiado=0;
		List<Empleado> empleados = empleadoService.findAll();
		for(Empleado empleado : empleados){
			sueldoTotal += empleado.getSalarioAnual();
		}
		sueldoService.variarSueldo(10);
		empleados = empleadoService.findAll();
		for(Empleado empleado : empleados){
			sueldoTotalCambiado += empleado.getSalarioAnual();
		}
		//sueldoService.variarSueldo(-10);
		assertTrue(Math.floor(sueldoTotalCambiado) == Math.floor(sueldoTotal * 1.1));
	}

}
