package es.microforum.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EmpresaTest {

	@Test
	public void testEqual() {
		Empresa empresa1 = new Empresa("nif1");
		Empresa empresa2 = new Empresa("nif2");
		assertTrue(!empresa1.equals(empresa2));
		empresa2.setNif("nif1");
		assertTrue(empresa1.equals(empresa2));
	}

}
