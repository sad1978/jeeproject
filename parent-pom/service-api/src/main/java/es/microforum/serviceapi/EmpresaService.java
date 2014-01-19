package es.microforum.serviceapi;

import java.util.List;

import es.microforum.model.Empleado;
import es.microforum.model.Empresa;

public interface EmpresaService {

	// Busca todos los contactos
	public List<Empresa> findAll();
	
	// Busca un contacto por clave primaria
	public Empresa findById(String id);
	
	// Inserta un contacto
	public Empresa insert(Empresa empresa);
	
	// Modifica un contacto
	public Empresa update(Empresa empresa);
	
	// Borra un contacto	
	public void delete(Empresa empresa);
	
}
