package acadevs.entreculturas.modelo;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta clase representa al listado de los trabajadores de la ONG.
 * 
 * @author Antonio,Cristina y Ana.
 * @version 1.0
 *
 */
@XmlRootElement(name = "trabajadores")
public class ListadoPersonal implements Serializable{
	
	// CAMPOS
    private List<Personal> listadoTrabajadores;
    
	// CONSTRUCTORES
	
	/**
	 * Constructor que llama a un nuevo objeto ListadoTrabajadores sin inicializar sus campos.
	 */
	public ListadoPersonal() {
		super();
	}
	
	
	/**
	 * Constructor que llama a un nuevo objeto ListadoTrabajadores inicializando sus campos.
	 * 
	 * @param listadoTrabajadores Listado con todos los trabajadores de la ONG.
	 */
	public ListadoPersonal(ArrayList<Personal> listadoTrabajadores) {
		super();
		this.listadoTrabajadores = listadoTrabajadores;
	}
    
    
	// METODOS
	
    /**
     * Metodo accesor de lectura que nos da el listado de trabajadores de la ONG.
     * 
     * @return Nos devuelve el listado de trabajadores. 
     */
    @XmlElement(name = "trabajador")
    public List<Personal> getListadoTrabajadores() {
        return listadoTrabajadores;
    }

    /**
     * Metodo accesor de escritura que asigna el listado de trabajadores de la ONG.
     * 
     * @param listadoTrabajadores El listado de trabajadores.
     */
    public void setListadoTrabajadores(List<Personal> listadoTrabajadores) {
        this.listadoTrabajadores = listadoTrabajadores;
    }

    
    /**
     * Metodo que permite agregar un trabajador al listado de trabajadores.
     * 
     * @param t Trabajador.
     */ 
    public void add(Personal t) {
    	
        if (this.listadoTrabajadores == null) {
            this.listadoTrabajadores = new ArrayList<Personal>();
        }
        
        this.listadoTrabajadores.add(t);

    }

}
