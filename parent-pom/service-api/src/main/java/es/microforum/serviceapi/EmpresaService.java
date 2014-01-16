/**
 * Created on Oct 17, 2011
 */
package es.microforum.serviceapi;

import java.util.List;

import es.microforum.model.Empresa;

public interface EmpresaService {

	// Busca todas las empresas
	public List<Empresa> findAll();
	
	// Busca una empresa por clave primaria
	public Empresa findById(String id);
	
	// Introduce una empresa
	public Empresa insert(Empresa empresa);
	
	//Modifica una empresa
	public Empresa update(Empresa empresa);
	
	// Delete a contact	
	public void delete(Empresa empresa);
	
}
