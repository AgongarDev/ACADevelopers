package sistema.basics;

import java.util.ArrayList;

public class Ong {

	private String id;
	private String nombre;
	private int numSocios;
	/*Todas las listas de ONG se deber�n llenar despu�s de haber creado el objeto ong, de lo contrario deber�amos crearlo fuera de �ste (por ejemplo en el administrador) y creo que lo mejor es 
	 * que se haga desde esta clase*/
	private ArrayList<AdministracionFisica> administraciones = new ArrayList<AdministracionFisica>();
	private ArrayList<Proyecto> proyectosOng = new ArrayList<Proyecto>();
	private ArrayList<TotalIngresos> listaIngresos = new ArrayList<TotalIngresos>();
	private float fondosOng;
	
	//Constructores de la clase Ong
	
	public Ong() {
		super();
	}	
	
	public Ong(String pid, String pnombre, int psocios, float fondos) {
		super();
		this.id = pid;
		this.nombre = pnombre;
		this.numSocios = psocios;
		this.fondosOng = fondos;
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
	
	public ArrayList<AdministracionFisica> getAdministraciones(){
		return administraciones;
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
	
    public void muestraIngresos(){
        for (TotalIngresos ingreso : listaIngresos) {
        	ingreso.toString();
        }
    }
    public void muestraProyectos(){
        for (Proyecto proyecto : proyectosOng) {
           	proyecto.toString();
        }
    }
    public ArrayList<Proyecto> getProyectos(){
    	return proyectosOng;
    }
    public void muestraAdministraciones(){
        for (AdministracionFisica sede : administraciones) {
        	sede.toString();
        }
    }
    public void addIngresos() {
    	TotalIngresos nuevoIngreso = new TotalIngresos();
    	//aqu� meteremos los m�todos que necesitemos que haga TotalIngreso para a�adir una nueva cantidad.
    	this.listaIngresos.add(nuevoIngreso); 
    }
    public void addProyecto() {
    	Proyecto nuevoProyecto = new Proyecto();
    	//aqu� meteremos los m�todos que necesitemos que haga Proyecto para a�adir un nuevo proyecto.
    	this.proyectosOng.add(nuevoProyecto);
    }
    public void addAdministracion() {
    	AdministracionFisica sede = new AdministracionFisica();
    	this.administraciones.add(sede);
    }
    public void borraAdministracion(AdministracionFisica sede) {
    	for (AdministracionFisica elem : administraciones) {
    		if (elem.equals(sede)) {
    			administraciones.remove(elem);
    		}
    	}
    }
    public void borraProyecto(Proyecto proyecto) {
    	for (Proyecto elem : proyectosOng) {
    		if (elem.equals(proyecto)) {
    			proyectosOng.remove(elem);
    		}
    	}
    }  
    public void borraIngreso(TotalIngresos ingreso) {
    	for (TotalIngresos elem : listaIngresos) {
    		if (elem.equals(ingreso)) {
    			listaIngresos.remove(elem);
    		}
    	}
    }
    public void calculaFondos(ArrayList<TotalIngresos>ingresos) {
    	float sumaImportes = 0;
    	for (TotalIngresos elem : listaIngresos) {
    		sumaImportes = sumaImportes + elem.importe;
    	}
    	this.fondosOng = sumaImportes;
    	String fondosToString = String.format ("%.2f", fondosOng);
    	System.out.println("Los fondos de la ONG "+nombre+" son "+fondosToString);
    }
}
