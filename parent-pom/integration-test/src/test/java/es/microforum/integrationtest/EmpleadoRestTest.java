package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import es.microforum.model.Empleado;
import es.microforum.model.Empresa;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-data-app-context.xml"})
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback=false)
//@Transactional()
public class EmpleadoRestTest {
	@Autowired
	DataSource dataSource;
	String dirAplicacion = "http://localhost:8080/service-frontend";
	RestTemplate restTemplate;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void before() {
		//DataSource dataSource = (DataSource) context.getBean("dataSource");
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("DELETE FROM empresa where nif LIKE '%prueba%'");
		jdbcTemplate.execute("DELETE FROM empleado where dni LIKE '%prueba%'");
		restTemplate = new RestTemplate();
		
	}
	//@Test
	public void getTest() {

		try{
			jdbcTemplate.execute("INSERT INTO empresa values('nifprueba1','nombreprueba1','Direccion prueba',null,0)");
			jdbcTemplate.execute("INSERT INTO empleado values('dniprueba1','nombreprueba1','direccionprueba1','tipo1','col1',10000,10,1000,null,'nifprueba1',0)");
			URI uri = new URI(dirAplicacion + "/empleado/dniprueba1");
			Resource<Empleado> resource = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Empleado>>() {}).getBody();
			assertTrue(resource.getContent().getNombre().equals("nombreprueba1"));
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		jdbcTemplate.execute("DELETE FROM empleado where dni='dniprueba1'");
		jdbcTemplate.execute("DELETE FROM empresa where nif='nifprueba1'");
	}
	//@Test
	public void deleteTest() {
		try {
			jdbcTemplate.execute("INSERT INTO empresa values('nifprueba1','nombreprueba1','Direccion prueba',null,0)");
			jdbcTemplate.execute("INSERT INTO empleado values('dniprueba1','nombreprueba1','direccionprueba1','tipo1','col1',10000,10,1000,null,'nifprueba1',0)");
			int count = jdbcTemplate.queryForInt("select count(*) from empleado where dni='dniprueba1'");
			assertTrue(count == 1);
			URI uri = new URI(dirAplicacion.toString() + "/empleado/dniprueba1");
			restTemplate.delete(uri);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		int count = jdbcTemplate.queryForInt("select count(*) from empleado where dni='dniprueba1'");
		assertTrue(count == 0);
		jdbcTemplate.execute("DELETE FROM empresa where nif='nifprueba1'");
	}
	@Test
	public void postTest() throws RestClientException, URISyntaxException {
		jdbcTemplate.execute("INSERT INTO empresa values('nifprueba1','nombreprueba1','Direccion prueba',null,0)");
		String url = dirAplicacion + "/empleado/";
		String acceptHeaderValue = "application/json";

		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.valueOf(acceptHeaderValue));
		requestHeaders.setAccept(mediaTypes);
		requestHeaders.setContentType(MediaType.valueOf(acceptHeaderValue));
		HttpMethod post = HttpMethod.POST;

		String body = "{\"dni\":\"dniprueba1\",\"nombre\":\"nombreprueba1\",\"direccion\":\"direccionprueba\",\"tipoEmpleado\":\"tipo1\",\"empleadocol\":\"col1\",\"salarioAnual\":\"10000\",\"valorHora\":\"10\",\"cantidadHoras\":\"1000\",\"empresa\":{\"nif\":\"nifprueba1\",\"version\":\"0\"},\"version\":\"0\"}";
		HttpEntity<String> entity = new HttpEntity<String>(body, requestHeaders);

		ResponseEntity<String> response = restTemplate.exchange(url, post, entity, String.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
		int count = jdbcTemplate.queryForInt("select count(*) from empleado where dni='dniprueba1'");
		assertTrue(count == 1);
		jdbcTemplate.execute("DELETE FROM empleado where dni='dniprueba1'");
		jdbcTemplate.execute("DELETE FROM empresa where nif='nifprueba1'");
	}
	@Test
	public void putTest() throws RestClientException, URISyntaxException {
		jdbcTemplate.execute("INSERT INTO empresa values('nifprueba1','nombreprueba1','Direccion prueba',null,0)");
		jdbcTemplate.execute("INSERT INTO empleado values('dniprueba1','nombreprueba1','direccionprueba1','tipo1','col1',10000,10,1000,null,'nifprueba1',0)");
		String url = dirAplicacion + "/empleado/dniprueba1";
		String acceptHeaderValue = "application/json";

		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.valueOf(acceptHeaderValue));
		requestHeaders.setAccept(mediaTypes);
		requestHeaders.setContentType(MediaType.valueOf(acceptHeaderValue));
		HttpMethod put = HttpMethod.PUT;

		String body = "{\"nombre\":\"nombreprueba2\"}";
		HttpEntity<String> entity = new HttpEntity<String>(body, requestHeaders);

		ResponseEntity<String> response = restTemplate.exchange(url, put, entity, String.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.NO_CONTENT));
		int count = jdbcTemplate.queryForInt("select count(*) from empleado where nombre = 'nombreprueba2'");
		assertTrue(count == 1);
		jdbcTemplate.execute("DELETE FROM empleado where dni='dniprueba1'");
		jdbcTemplate.execute("DELETE FROM empresa where nif='nifprueba1'");
	}
}
