package ACADevelopers.Entreculturas;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;


/**
 * Esta clase que representa el listado de proyectos que se estan realizando en la ONG.
 * 
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
@XmlRootElement(name = "Socios")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)

public class ListadoSocios {
	
	// CAMPOS

	private List<Socio> listadoSocios;

	
	// CONSTRUCTORES
	
	/**
	 * Constructor que crea un nuevo objeto ListadoProyectos sin inicializar sus campos. 
	 */
	
	public ListadoSocios() {
		super();
	}

	/**
	 *  Constructor que crea un nuevo objeto ListadoProyectos inicializando sus campos.
	 *  
	 * @param listadoProyectos El listado de proyectos que se estan realizando en la ONG.
	 */
	public ListadoSocios(ArrayList<Socio> listadoSocios) {
		super();
		this.listadoSocios = listadoSocios;
	}


	// METODOS
	
	/**
	 * Metodo accesor de lectura que nos da el listado de los
	 *socios en la ONG.
	 * @return nos devuelve el listado de los proyectos que se estan realizando.
	 */
	@XmlElements ({ @XmlElement(name = "socio", type = Socio.class, required = false)})
	public List<Socio> getListadoSocios() {
		return listadoSocios;
	}
	
	/**
	 * Metodo accesor de escritura que asigna un socio al listado.
	 * 
	 * @param listadoSocios El listado de los socios.
	 */
	public void setListadoSocios(List<Socio> listadoSocios) {
		this.listadoSocios = listadoSocios;
	}
	
	 /**
     * Este metodo nos permite agregar un socio al listado.
     * 
     * @param s socio.
     */
    public void add(Socio s) {
    	
        if (this.listadoSocios == null) {
            this.listadoSocios = new ArrayList<Socio>();
        }
        this.listadoSocios.add(s);

    }
	
}
