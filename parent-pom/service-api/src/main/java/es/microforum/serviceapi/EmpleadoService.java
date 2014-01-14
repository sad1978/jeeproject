package es.microforum.serviceapi;

import java.util.List;

import es.microforum.model.Empleado;
import es.microforum.model.Persona;

public interface EmpleadoService {

	// Find all contacts
	public List<Empleado> findAll();
	
	// Find a contact with details by id
	public Empleado findById(String id);
	
	// Insert or update a contact	
	public Empleado save(Empleado empleado);
	
	// Delete a contact	
	public void delete(Empleado empleado);
	
}
