package com.techiekernel.ws.jaxws.test;

import static org.junit.Assert.*;

import java.util.List;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;






import org.springframework.web.client.RestTemplate;

import com.techiekernel.ws.jaxws.IVariarSueldoWebService;

import es.microforum.model.Empleado;
import es.microforum.serviceapi.EmpleadoService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testcontext.xml"})
public class SueldoSoapTest {
	@Autowired
	IVariarSueldoWebService miServicio;
	@Autowired
	EmpleadoService empleadoService;
	@Test
	public void testCallVariarSueldo() {

		double sueldoTotal=0, sueldoTotalCambiado=0;
		List<Empleado> empleados = empleadoService.findAll();
		for(Empleado empleado : empleados){
			sueldoTotal += empleado.getSalarioAnual();
		}
		miServicio.callVariarSueldo(10);
		List<Empleado> empleados2 = empleadoService.findAll();
		for(Empleado empleado : empleados2){
			sueldoTotalCambiado += empleado.getSalarioAnual();
		}
		assertTrue(Math.floor(sueldoTotalCambiado) == Math.floor(sueldoTotal * 1.1));
		for(Empleado empleado : empleados)
			for(Empleado empleado2: empleados2)
				if(empleado.getDni().equals(empleado2.getDni())){
					empleado2.setValorHora(empleado.getValorHora());
					empleado2.setSalarioAnual(empleado.getSalarioAnual());
					empleadoService.update(empleado2);
					break;
				}
	}

}
