package acadevs.entreculturas.modelo;
import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
/**
 * Esta clase que representa el listado de proyectos que se estan realizando en la ONG.
 * 
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
@XmlRootElement(name = "administradores")
public class ListadoAdministradores implements Serializable{
	// CAMPOS
	private List<Administrador> listadoAdministradores;
	// CONSTRUCTORES
	
	/**
	 * Constructor que crea un nuevo objeto ListadoAdministradores sin inicializar sus campos. 
	 */
	public ListadoAdministradores() {
		super();
	}
	/**
	 *  Constructor que crea un nuevo objeto ListadoAdministradores inicializando sus campos.
	 *  
	 * @param listadoAdministradores El listado de administradores que hay en la ONG.
	 */
	public ListadoAdministradores(ArrayList<Administrador> listadoAdministradores) {
		super();
		this.listadoAdministradores = listadoAdministradores;
	}
	// METODOS
	
	/**
	 * Metodo accesor de lectura que nos da el listado de los
	 * administradores que hay actualmente en la ONG.
	 * @return nos devuelve el listado de los administradores que hay en la ONG.
	 */
	@XmlElement(name = "Administrador")
	public List<Administrador> getListadoAdministradores() {
		return listadoAdministradores;
	}
	
	/**
	 * Metodo accesor de escritura que asigna el listado de los
	 * administradores que hay actualmente en la ONG.
	 * 
	 * @param listadoAdministradores El listado de los administradores que hay en la ONG.
	 */
	public void setListadoAdministradores(List<Administrador> listadoAdministradores) {
		this.listadoAdministradores = listadoAdministradores;
	}
	
	 /**
     * Este metodo nos permite agregar un administrador al listado.
     * 
     * @param a administrador.
     */
    public void add(Administrador a) {
    	
        if (this.listadoAdministradores == null) {
            this.listadoAdministradores = new ArrayList<Administrador>();
        }
        this.listadoAdministradores.add(a);
    }
}