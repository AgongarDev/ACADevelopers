package sistema.basics;

public class AdministracionFisica {

	private String idAdmin;
	private String direccion;
	private int numEmpleados;
	private String correo;
	private int telefono;
	
	//constructores
	
	public AdministracionFisica () {
		super();
	}
	
	public AdministracionFisica (String id, String direccion, int empleados, String correo, int telefono) {
		this.idAdmin = id;
		this.direccion = direccion;
		this.numEmpleados = empleados;
		this.correo = correo;
		this.telefono = telefono;
	}
	
	//getters y setters

	public String getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(String idAdmin) {
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
	

	
	
}
