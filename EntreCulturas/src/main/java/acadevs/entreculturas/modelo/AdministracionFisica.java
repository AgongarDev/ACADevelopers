package acadevs.entreculturas.modelo;

import java.io.Serializable;

public class AdministracionFisica implements Serializable{

	//ATRIBUTOS DEL MODELO ADMINISTRACION FISICA
	
	private Integer idAdmin = null;
	private String nombre;
	private String direccion;
	private short numEmpleados;
	private String correo;
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
		public Integer getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
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
