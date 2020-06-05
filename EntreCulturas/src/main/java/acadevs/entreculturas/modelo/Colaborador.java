package acadevs.entreculturas.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="colaboradores")
public class Colaborador implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_colaborador")
	private int id;
	
	@Column (name = "nif")
	private String nif;
	@Column (name = "nombre")
	private String nombre;
	@Column (name = "direccion")
	private String direccion;
	@Column (name = "correo")
	private String email;
	@Column (name = "telefono")
	private int telefono;
	@ManyToMany (mappedBy = "colaboradores")
	private Set<Proyecto> proyectos = new HashSet<>();
	
	public Colaborador () {
		
	}

	public Colaborador(String nif, String nombre, String direccion, String email, int telefono) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.direccion = direccion;
		this.email = email;
		this.telefono = telefono;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public Set<Proyecto> getProyectos() {
		return proyectos;
	}
	
	public void addProyecto (Proyecto p) {
		this.proyectos.add(p);
	}
	
	public void removeProyecto (Proyecto p) {
		proyectos.remove(p);
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	@Override
	public String toString() {
		return "Colaborador [nif=" + nif + ", nombre=" + nombre + ", direccion=" + direccion + ", email=" + email
				+ ", telefono=" + telefono + "]";
	}
	
}
