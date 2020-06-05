package acadevs.entreculturas.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Esta clase representa a cada uno de los individuos que forman parte del personal de la ONG.
 * 
 * @author Cristina,Antonio y Ana.
 * @version 1.0
 *
 */
@Entity
@Table(name = "personal") 
@PrimaryKeyJoinColumn(name = "id_trabajador")
@XmlRootElement(name = "personal")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(propOrder={"delegacionAsignada", "antiguedad", "proyectosAsignados"})
public class Personal extends Persona implements Serializable{

	// CAMPOS
	
	@Column(name = "salario_neto")
	private BigDecimal SalarioNeto;
	
	@Column(name = "salario_bruto")
	private BigDecimal SalarioBase;
	
	@ManyToMany( cascade = {CascadeType.ALL} )
			@JoinTable (
					name = "personal_proyecto", 
					joinColumns = { @JoinColumn (name = "id_trabajador") } ,
					inverseJoinColumns = { @JoinColumn (name = "id_proyecto") }
					)
	private Set<Proyecto> proyectos = new HashSet<>();
	
	/**
	 * Constructor sin parámetros, necesario para JPA
	 */
	public Personal() {
	}
	
	// MÉTODOS
	public int getId() {
		return super.getId();
	}
	
	public BigDecimal getSalarioNeto() {
		return SalarioNeto;
	}

	public void setSalarioNeto(BigDecimal salarioNeto) {
		SalarioNeto = salarioNeto;
	}

	public BigDecimal getSalarioBase() {
		return SalarioBase;
	}

	public void setSalarioBase(BigDecimal salarioBase) {
		SalarioBase = salarioBase;
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public Set<Proyecto> getProyectos() {
		return proyectos;
	}


}
