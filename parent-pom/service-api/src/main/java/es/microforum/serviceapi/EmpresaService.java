package es.microforum.serviceapi;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.microforum.model.Empleado;
import es.microforum.model.Empresa;

public interface EmpresaService {

	// Busca todas las empresas
	public List<Empresa> findAll();
	
	// Busca una empresa por clave primaria
	public Empresa findById(String id);
	
	// Inserta una empresa
	public Empresa insert(Empresa empresa);
	
	// Modifica una empresa
	public Empresa update(Empresa empresa);
	
	// Borra una empresa	
	public void delete(Empresa empresa);
	
	//Asigna empleado existente a empresa existente
	public void asignaEmpleadoAEmpresa(Empresa empresa, Empleado empleado);
	
	Page<Empresa> findAll(Pageable pageable);
}
