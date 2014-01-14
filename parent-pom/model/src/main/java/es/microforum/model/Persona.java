/**
 * Created on Oct 12, 2011
 */
package es.microforum.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * @author Clarence
 *
 */
@Entity
@Table(name = "persona")
@NamedQueries({
	@NamedQuery(name="Persona.findAll",
			    query="select c from Persona c"),
	@NamedQuery(name="Persona.findById", 
	  query="select distinct c from Persona c where c.id = :id")
})
public class Persona implements Serializable {

	private int id;
	private String nombre;
	private Date fechaNacimiento;
	private int salario;

	public Persona() {
	}

	@Id
	@Column(name = "ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NOMBRE")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String firstName) {
		this.nombre = firstName;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_NACIMIENTO")
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	@Column(name = "SALARIO")
	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre
				+ ", fechaNacimiento=" + fechaNacimiento + ", salario="
				+ salario + "]";
	}

}
