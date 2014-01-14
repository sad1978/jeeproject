package es.microforum.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmpleadoTest {

	@Test
	public void testEqual() {
		Empleado empleado1 = new Empleado("dni1");
		Empleado empleado2 = new Empleado("dni2");
		assertTrue(!empleado1.equals(empleado2));
		empleado2.setDni("dni1");
		assertTrue(empleado1.equals(empleado2));
	}

}
