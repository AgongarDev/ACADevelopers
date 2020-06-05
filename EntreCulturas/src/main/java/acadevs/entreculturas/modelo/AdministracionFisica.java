package acadevs.entreculturas.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="administraciones")
public class AdministracionFisica implements Serializable{

	//ATRIBUTOS DEL MODELO ADMINISTRACION FISICA
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id_sede")
	private Short idAdmin;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="num_empleados")
	private short numEmpleados;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "sedeAsignada")
	private List<Persona> personalAsignado;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="telefono")
	private int telefono;
	
	//CONSTRUCTORES
	
	public AdministracionFisica () {
	}
	
	public AdministracionFisica (String nombre, String direccion, int telefono, String correo, short empleados) {
	
		this.nombre = nombre;
		
		this.direccion = direccion;
		
		this.numEmpleados = empleados;
		
		this.correo = correo;
		
		this.telefono = telefono;
	}
	
	//getters y setters
		public Short getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(short idAdmin) {
		this.idAdmin = idAdmin;	
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumEmpleados() {
		return numEmpleados;
	}

	public void setNumEmpleados(short numEmpleados) {
		this.numEmpleados = numEmpleados;
	}

	public List<Persona> getPersonalAsignado() {
		return personalAsignado;
	}

	public void setPersonalAsignado(List<Persona> personalAsignado) {
		this.personalAsignado = personalAsignado;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "AdministracionFisica [idAdmin=" + idAdmin + ", nombre = " + nombre + ", direccion =" + direccion + ", numEmpleados="
				+ numEmpleados + ", correo=" + correo + ", telefono=" + telefono + "]";
	}
	

	
	
}
