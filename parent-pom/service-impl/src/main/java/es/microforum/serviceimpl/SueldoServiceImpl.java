package es.microforum.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.microforum.model.Empleado;
import es.microforum.serviceapi.SueldoService;
@Service("jpaSueldoService")
@Repository
@Transactional
public class SueldoServiceImpl implements SueldoService{
	@Autowired
	private EmpleadoRepository repositorioEmpleado;
	
	public boolean variarSueldo(double porcentaje){
		boolean resultado = false;

		if(porcentaje <=100 && porcentaje >= -100){
			double factor = 1 + porcentaje/100;
			List<Empleado> empleados = (List<Empleado>) repositorioEmpleado.findAll();
			for(Empleado empleado: empleados){
				empleado.setValorHora(empleado.getValorHora()*factor);
				empleado.setSalarioAnual(empleado.getValorHora()*empleado.getCantidadHoras());

			}
			repositorioEmpleado.save(empleados);
			resultado = true;
		}
		return resultado;
	}
}
