/**
 * Created on Oct 18, 2011
 */
package es.microforum.serviceimpl;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.microforum.model.Empleado;


/**
 * @author Clarence
 *
 */
public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, String> {
	//public List<Persona> findByNombreAndSalario(String nombre,int salario);
	
	//public List<Persona> findByFirstNameAndLastName(String firstName, String lastName);	
	
}
