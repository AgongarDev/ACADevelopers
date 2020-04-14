package acadevs.entreculturas.modelo;

public class AdministracionFisica {

	//ATRIBUTOS DEL MODELO
	
	private Long idAdmin = null;
	private String nombre;
	private String direccion;
	private int numEmpleados;
	private String correo;
	private int telefono;
	
	//CONSTRUCTORES
	
	public AdministracionFisica () {
	}
	
	public AdministracionFisica (String direccion, int empleados, String correo, int telefono) {
	
		this.direccion = direccion;
		
		this.numEmpleados = empleados;
		
		this.correo = correo;
		
		this.telefono = telefono;
	}
	
	//getters y setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Long getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(Long idAdmin) {
		this.idAdmin = idAdmin;
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

	public void setNumEmpleados(int numEmpleados) {
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
		return "AdministracionFisica [idAdmin=" + idAdmin + ", direccion=" + direccion + ", numEmpleados="
				+ numEmpleados + ", correo=" + correo + ", telefono=" + telefono + "]";
	}
	

	
	
}
