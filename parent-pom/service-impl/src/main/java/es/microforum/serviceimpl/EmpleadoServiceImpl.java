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




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.microforum.model.Empleado;
import es.microforum.serviceapi.EmpleadoService;





@Service("jpaEmpleadoService")
@Repository
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {
	@Autowired
	private EmpleadoRepository repositorioEmpleado;
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly=true)
	public List<Empleado> findAll() {
		List<Empleado> empleados = em.createNamedQuery("Empleado.findAll", Empleado.class).getResultList();
		return empleados;
	}


	@Transactional(readOnly=true)
	public Empleado findById(String id) {
		//TypedQuery<Empleado> query = em.createNamedQuery("Empleado.findById", Empleado.class);
		//query.setParameter("id", id);
		//return query.getSingleResult();
		return repositorioEmpleado.findOne(id);

	}

	public Empleado save(Empleado empleado) {
		if (empleado.getDni() == null) { // Insert Contact
			em.persist(empleado);
		} else {                       // Update Contact
			em.merge(empleado);
		}
		return empleado;
	}

	public void delete(Empleado empleado) {
		Empleado mergedEmpleado = em.merge(empleado);
		em.remove(mergedEmpleado);
	}
	
 }
