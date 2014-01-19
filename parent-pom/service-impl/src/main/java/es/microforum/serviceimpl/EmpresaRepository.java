/**
 * Created on Oct 18, 2011
 */
package es.microforum.serviceimpl;


import org.springframework.data.repository.CrudRepository;

import es.microforum.model.Empresa;


/**
 * @author Clarence
 *
 */
public interface EmpresaRepository extends CrudRepository<Empresa, String> {	
	
}
