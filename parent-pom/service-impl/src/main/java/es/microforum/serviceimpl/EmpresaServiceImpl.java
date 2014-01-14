/**
 * Created on Oct 17, 2011
 */
package es.microforum.serviceimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.microforum.model.Persona;
import es.microforum.serviceapi.PersonaService;

//import com.apress.prospring3.ch10.domain.Contact_;



/**
 * @author Clarence
 *
 */
@Service("jpaPersonaService")
@Repository
@Transactional
public class EmpresaServiceImpl implements PersonaService {

	//private Log log = LogFactory.getLog(PersonaServiceImpl.class);	
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly=true)
	public List<Persona> findAll() {
		List<Persona> personas = em.createNamedQuery("Persona.findAll", Persona.class).getResultList();
		return personas;
	}

//	@Transactional(readOnly=true)
//	public List<Contact> findAllWithDetail() {
//		List<Contact> contacts = em.createNamedQuery("Contact.findAllWithDetail", Contact.class).getResultList();
//		return contacts;
//	}

	@Transactional(readOnly=true)
	public Persona findById(int id) {
		TypedQuery<Persona> query = em.createNamedQuery("Persona.findById", Persona.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	public Persona save(Persona persona) {
		if (persona.getId() == 0) { // Insert Contact
			//log.info("Inserting new persona");
			em.persist(persona);
		} else {                       // Update Contact
			em.merge(persona);
			//log.info("Updating existing persona");
		}
		//log.info("Persona saved with id: " + persona.getId());
		return persona;
	}

	public void delete(Persona persona) {
		Persona mergedPersona = em.merge(persona);
		em.remove(mergedPersona);
		//log.info("Persona with id: " + persona.getId() + " deleted successfully");
	}
	
//	final static String ALL_CONTACT_NATIVE_QUERY =
//			"select id, first_name, last_name, birth_date, version from contact";
//
//	@Transactional(readOnly=true)
//	public List<Contact> findAllByNativeQuery() {
//
//		//return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, Contact.class).getResultList();
//		return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, "contactResult").getResultList();
//	}

//	@Transactional(readOnly=true)
//	public List<Contact> findByCriteriaQuery(String firstName, String lastName) {
//
//		log.info("Finding contact for firstName: " + firstName + " and lastName: " + lastName);
//		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
//		Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
//		contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
//		contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);
//
//		criteriaQuery.select(contactRoot).distinct(true);
//		
//		Predicate criteria = cb.conjunction();
//		
//		// First Name
//		if (firstName != null) {
//			Predicate p = cb.equal(contactRoot.get(Contact_.firstName), firstName);
//			criteria = cb.and(criteria, p);
//		}
//		
//		// Last Name
//		if (lastName != null) {
//			Predicate p = cb.equal(contactRoot.get(Contact_.lastName), lastName);
//			criteria = cb.and(criteria, p);
//		}		
//		
//		criteriaQuery.where(criteria);
//		List<Contact> result = em.createQuery(criteriaQuery).getResultList();
//		return result;
//
//	}

//	public List<Contact> findByFirstName(String firstName) {
//		// Not implemented
//		return null;
//	}
//
//	public List<Contact> findByFirstNameAndLastName(String firstName,
//			String lastName) {
//		// Not implemented
//		return null;
//	}	
	
 }
