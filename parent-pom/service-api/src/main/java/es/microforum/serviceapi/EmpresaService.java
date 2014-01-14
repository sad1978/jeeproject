/**
 * Created on Oct 17, 2011
 */
package es.microforum.serviceapi;

import java.util.List;

import es.microforum.model.Persona;

/**
 * @author Clarence
 *
 */
public interface EmpresaService {

	// Find all contacts
	public List<Persona> findAll();
	
	// Find all contacts with telephone and hobbies	
	//public List<Contact> findAllWithDetail();
	
	// Find a contact with details by id
	public Persona findById(int id);
	
	// Insert or update a contact	
	public Persona save(Persona persona);
	
	// Delete a contact	
	public void delete(Persona persona);
	
	// Find all contacts by native query
	//public List<Contact> findAllByNativeQuery();
	
	// Find contacts by criteria query
	//public List<Contact> findByCriteriaQuery(String firstName, String lastName);
	
	// Find contacts by first name
	//public List<Persona> findByNombreAndSalario(String nombre, int salario);
	
	// Find contacts by first name and last name
	//public List<Contact> findByFirstNameAndLastName(String firstName, String lastName);	
}
