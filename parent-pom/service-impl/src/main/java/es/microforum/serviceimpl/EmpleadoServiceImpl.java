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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		return (List<Empleado>) repositorioEmpleado.findAll();
	}

	@Transactional(readOnly=true)
	public Empleado findById(String id) {
		return repositorioEmpleado.findOne(id);

	}

	public Empleado insert(Empleado empleado) {
		return repositorioEmpleado.save(empleado);
	}
	public Empleado update(Empleado empleado){
		return repositorioEmpleado.save(empleado);
	}
	
	public void delete(Empleado empleado) {
		Empleado mergedEmpleado = em.merge(empleado);
		em.remove(mergedEmpleado);
	}
	@Transactional(readOnly=true)	
	public Page<Empleado> findAll(Pageable pageable){
		return repositorioEmpleado.findAll(pageable);
	}
 }
