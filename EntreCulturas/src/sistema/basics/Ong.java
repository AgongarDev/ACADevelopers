package sistema.basics;

import java.util.ArrayList;

public class Ong {

	private String id;
	private String nombre;

	/*Todas las listas de ONG se deberán llenar después de haber creado el objeto ong, de lo contrario deberíamos crearlo fuera de éste (por ejemplo en el administrador) y creo que lo mejor es 
	 * que se haga desde esta clase*/
	private ArrayList<AdministracionFisica> administraciones = new ArrayList<AdministracionFisica>();
	private ArrayList<Proyecto> proyectosOng = new ArrayList<Proyecto>();
	private ArrayList<TotalIngresos> listaIngresos = new ArrayList<TotalIngresos>();
	private float fondosOng;
	private ArrayList<Persona> listaSocios;
	
	//Constructores de la clase Ong
	
	public Ong() {
		super();
	}	
	
	public Ong(String pid, String pnombre, float fondos) {
		super();
		this.id = pid;
		this.nombre = pnombre;
		this.fondosOng = fondos;
	}	

	//Métodos de la clase Ong
	//Getters y Setters
	
	public ArrayList<Persona> getListaSocios() {
		return listaSocios;
	}

	public void addSocio(Persona socio) {
		this.listaSocios.add(socio);
	}
	
	public String getId() {
		return id;
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
	
	//@param codigo es el código alfanumérico que se usará como id.
	public void setId(String codigo) {
		this.id = codigo;
	}
	
	//acciones de la ong
	//@param cantidad es el entero que se añadirá a la cantidad de socios existentes.
	
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
    public void addIngreso(TotalIngresos ingreso) {
    	listaIngresos.add(ingreso); 
    }
    
    public void addProyecto() {
    	Proyecto nuevoProyecto = new Proyecto();
    	//aquí meteremos los métodos que necesitemos que haga Proyecto para añadir un nuevo proyecto.
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
