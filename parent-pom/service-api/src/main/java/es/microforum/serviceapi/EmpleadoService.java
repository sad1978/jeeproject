package es.microforum.serviceapi;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.microforum.model.Empleado;

public interface EmpleadoService {

	// Busca todos los contactos
	public List<Empleado> findAll();
	
	// Busca un contacto por clave primaria
	public Empleado findById(String id);
	
	// Inserta un contacto
	public Empleado insert(Empleado empleado);
	
	// Modifica un contacto
	public Empleado update(Empleado empleado);
	
	// Borra un contacto	
	public void delete(Empleado empleado);
	Page<Empleado> findAll(Pageable pageable);
	
}
