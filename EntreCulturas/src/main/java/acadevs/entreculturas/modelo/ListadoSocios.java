package acadevs.entreculturas.modelo;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Esta clase que representa el listado de proyectos que se estan realizando en la ONG.
 * 
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
@XmlRootElement(name = "Socios")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ListadoSocios implements Serializable{
	
	// CAMPOS
	private List<Socio> listaSocios = new ArrayList<Socio>();

	// METODOS
	
	/**
	 * Metodo accesor de lectura que nos da el listado de los
	 *socios en la ONG.
	 * @return nos devuelve el listado de los proyectos que se estan realizando.
	 */
	public List<Socio> getSocios() {
		return listaSocios;
	}
	
	/**
	 * Metodo accesor de escritura que asigna un socio al listado.
	 * 
	 * @param listadoSocios El listado de los socios.
	 */
	public void setSocios(List<Socio> lsocios) {
		this.listaSocios = lsocios;
	}

}
