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

import es.microforum.model.Empresa;
import es.microforum.serviceapi.EmpresaService;




/**
 * @author Clarence
 *
 */
@Service("jpaEmpresaService")
@Repository
@Transactional
public class EmpresaServiceImpl implements EmpresaService {

	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly=true)
	public List<Empresa> findAll() {
		List<Empresa> empresas = em.createNamedQuery("Empresa.findAll", Empresa.class).getResultList();
		return empresas;
	}

	@Transactional(readOnly=true)
	public Empresa findById(String id) {
		TypedQuery<Empresa> query = em.createNamedQuery("Empresa.findById",Empresa.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	public Empresa save(Empresa empresa) {
		if (empresa.getNif() == null) { // Insert Contact
			em.persist(empresa);
		} else {                       // Update Contact
			em.merge(empresa);
		}
		return empresa;
	}
	public Empresa update(Empresa empresa){
		return null;
	}
	public Empresa insert(Empresa empresa){
		return null;
	}
	public void delete(Empresa empresa) {
		Empresa mergedEmpresa = em.merge(empresa);
		em.remove(mergedEmpresa);
	}
 }
