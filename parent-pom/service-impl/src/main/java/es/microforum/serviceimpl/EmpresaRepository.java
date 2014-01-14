/**
 * Created on Oct 18, 2011
 */
package es.microforum.serviceimpl;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.microforum.model.Persona;

/**
 * @author Clarence
 *
 */
public interface EmpresaRepository extends CrudRepository<Persona, Integer> {

	public List<Persona> findByNombreAndSalario(String nombre,int salario);
	
	//public List<Persona> findByFirstNameAndLastName(String firstName, String lastName);	
	
}
