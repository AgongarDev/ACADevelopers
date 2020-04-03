package sistema.basics;

public class Ong {

	private String id;
	private String nombre;
	private int numSocios;
	
	//Constructores de la clase Ong
	
	public Ong() {
		super();
	}	
	
	public Ong(String pid, String pnombre, int psocios) {
		super();
		this.id = pid;
		this.nombre = pnombre;
		this.numSocios = psocios;
	}	

	//M�todos de la clase Ong
	//Getters y Setters
	
	public String getId() {
		return id;
	}
	
	public int getNumSocios() {
		return numSocios;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String pnombre) {
		this.nombre = pnombre;
	}
	
	//@param codigo es el c�digo alfanum�rico que se usar� como id.
	public void setId(String codigo) {
		this.id = codigo;
	}
	
	//@param socios es el n�mero de socios que dispone la ong 
	public void setNumSocios(int socios) {
		this.numSocios = socios;
	}
	
	//acciones de la ong
	//@param cantidad es el entero que se a�adir� a la cantidad de socios existentes.
	public void incNumSocios(int cantidad) {
		this.numSocios = this.numSocios + cantidad;
		System.out.println("Enhorabuena! la ONG "+nombre+" ahora tiene "+numSocios+" socios.");
	}
	
	public void decNumSocios(int cantidad) {
		this.numSocios = this.numSocios - cantidad;
	}
	
}
