package acadevs.entreculturas.modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="acciones")
public class Accion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_accion")
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	//constructores de la clase
	
	public Accion() {
		
	}
	
	public Accion(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	 
	@Override
	public String toString() {
		return "Accion [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
	
}
