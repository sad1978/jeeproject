/**
 * Created on Oct 18, 2011
 */
package es.microforum.serviceimpl;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.microforum.model.Empleado;
import es.microforum.model.Empresa;


/**
 * @author Clarence
 *
 */
public interface EmpresaRepository extends PagingAndSortingRepository<Empresa, String> {	
	
}
