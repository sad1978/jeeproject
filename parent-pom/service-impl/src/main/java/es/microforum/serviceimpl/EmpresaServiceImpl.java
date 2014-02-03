package es.microforum.serviceimpl;

import java.util.List;
import java.util.Set;

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
import es.microforum.model.Empresa;
import es.microforum.serviceapi.EmpleadoService;
import es.microforum.serviceapi.EmpresaService;

@Service("jpaEmpresaService")
@Repository
@Transactional
public class EmpresaServiceImpl implements EmpresaService {
	@Autowired
	private EmpresaRepository repositorioEmpresa;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Empresa> findAll() {
		return (List<Empresa>) repositorioEmpresa.findAll();
	}


	public Empresa findById(String id) {
		return repositorioEmpresa.findOne(id);

	}

	public Empresa insert(Empresa empresa) {
		em.persist(empresa);
		return empresa;
	}
	public Empresa update(Empresa empresa){
		em.merge(empresa);
		return empresa;
	}

	public void delete(Empresa empresa) {
		Empresa mergedEmpresa = em.merge(empresa);
		em.remove(mergedEmpresa);
	}
	public void asignaEmpleadoAEmpresa(Empresa empresa, Empleado empleado){
		empleado.setEmpresa(empresa);
		Set<Empleado> empleados = empresa.getEmpleados();
		empleados.add(empleado);
		empresa.setEmpleados(empleados);
	}
	@Transactional(readOnly=true)	
	public Page<Empresa> findAll(Pageable pageable){
		return repositorioEmpresa.findAll(pageable);
	}
	
 }
