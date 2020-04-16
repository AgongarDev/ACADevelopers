package ACADevelopers.Entreculturas;

public class Accion {

	private String codeAccion;
	private String descripcionAccion;
	
	//constructores de la clase
	
	public Accion() {
		super();
	}
	public Accion(String codigo, String descripcion) {
		this.codeAccion = codigo;
		this.descripcionAccion = descripcion;
	}
	
	// getters y setters
	
	public void setCodigo (String codigo) {
		this.codeAccion = codigo;
	}
	
	public String getCodigo () {
		return codeAccion;
	}
	
	public void setDescripcion (String descripcion) {
		this.descripcionAccion = descripcion;
	}
	
	public String getDescripcion () {
		return descripcionAccion;
	}
	
	//m�todos
	
	/*@Override 
	 * public String toString() {
	 * return 
	 * }*/
}
