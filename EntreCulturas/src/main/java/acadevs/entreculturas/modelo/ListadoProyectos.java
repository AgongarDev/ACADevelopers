package acadevs.entreculturas.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;


/**
 * Esta clase que representa el listado de proyectos que se estan realizando en la ONG.
 * 
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
@XmlRootElement(name = "proyectos")
public class ListadoProyectos {
	
	// CAMPOS
	private List<Proyecto> listadoProyectos;

	
	// CONSTRUCTORES
	
	/**
	 * Constructor que crea un nuevo objeto ListadoProyectos sin inicializar sus campos. 
	 */
	public ListadoProyectos() {
		super();
	}

	/**
	 *  Constructor que crea un nuevo objeto ListadoProyectos inicializando sus campos.
	 *  
	 * @param listadoProyectos El listado de proyectos que se estan realizando en la ONG.
	 */
	public ListadoProyectos(ArrayList<Proyecto> listadoProyectos) {
		super();
		this.listadoProyectos = listadoProyectos;
	}


	// METODOS
	
	/**
	 * Metodo accesor de lectura que nos da el listado de los
	 * proyectos que se estan realizando actualmente en la ONG.
	 * @return nos devuelve el listado de los proyectos que se estan realizando.
	 */
	@XmlElement(name = "Proyecto")
	public List<Proyecto> getListadoProyectos() {
		return listadoProyectos;
	}
	
	/**
	 * Metodo accesor de escritura que asigna el listado de los
	 * proyectos que se estan realizando actualmente en la ONG.
	 * 
	 * @param listadoProyectos El listado de los proyectos que se estan realizando.
	 */
	public void setListadoProyectos(List<Proyecto> listadoProyectos) {
		this.listadoProyectos = listadoProyectos;
	}
	
	 /**
     * Este metodo nos permite agregar una proyecto al listado.
     * 
     * @param p Proyecto.
     */
    public void add(Proyecto p) {
    	
        if (this.listadoProyectos == null) {
            this.listadoProyectos = new ArrayList<Proyecto>();
        }
        this.listadoProyectos.add(p);

    }
	
}
