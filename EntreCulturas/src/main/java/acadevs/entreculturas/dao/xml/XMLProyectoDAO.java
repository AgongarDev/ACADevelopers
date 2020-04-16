package acadevs.entreculturas.dao.xml;

/**
 * Esta clase instancia objetos XML DAO para interaccionar con
 * los objetos delegacion y persistirlos en formato XML.
 * 
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import acadevs.entreculturas.dao.IDAO;
import acadevs.entreculturas.modelo.ListadoProyectos;
import acadevs.entreculturas.modelo.Proyecto;

public class XMLProyectoDAO {// implements IDAO<Proyecto>{
	
	//DAO NO IMPLEMENTADO 
	
	// CAMPOS
	
	private ListadoProyectos listadoProyectos = new ListadoProyectos();
	
	
	// CONSTRUCTORES
	
	/**
	 * Constructor que crea un nuevo objeto XMLProyectoDAO sin inicializar sus campos.
	 */
    public XMLProyectoDAO() {
    	
    }
    
	/**
	 * Constructor que crea un nuevo objeto XMLProyectoDAO inicializando sus campos.
	 * 
	 * @param listadoDelegaciones Atributo que guarda el listado de las delegaciones de la ONG.
	 */
	public XMLProyectoDAO(ListadoProyectos listadoProyectos) {
		super();
		this.listadoProyectos = listadoProyectos;
	}


	// METODOS
	
	/**
	 * Metodo accesor de lectura que nos da el lista de Proyectos de la ONG.
	 * 
	 * @return Nos devuelve el listado de proyectos.
	 */
	public ListadoProyectos getListadoProyectos() {
		return listadoProyectos;
	}

	/**
	 * Metodo accesor de escritura que asigna el listado de Proyectos de la ONG.
	 * 
	 * @param listadoDelegaciones El listado de proyectos de la ONG.
	 */
	public void setListadoProyectos(ListadoProyectos listadoProyectos) {
		this.listadoProyectos = listadoProyectos;
	}

	/**
	 * Metodo para crear un nuevo objeto delegacion a persistir
	 * en formato XML.
	 * 
	 * @param d Objeto delegacion a persistir.
	 */

	public void crearNuevo(Proyecto p) {
		listadoProyectos.add(p);
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(Proyecto.class);
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    Marshaller mar = null;
		try {
			mar = context.createMarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //Crea el directorio "xml" en caso de que no exista.
	    File f = new File("xml/");
	  	  if (!f.exists()) {
	  	    f.mkdirs();
	  	}
	    
	    try {
			mar.marshal(p, new File("xml/proyecto.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		System.out.println("Se ha creado un nuevo proyecto");
	}

	/**
	 * Metodo para obtener un objeto proyecto persistido.
	 * 
	 * @param id Identificador unico del objeto proyecto.
	 * @return Objeto proyecto persistido.
	 */
	
	public Optional<Proyecto> obtener(String id) {
		System.out.println("Se ha obtenido un proyecto");
        //return encontrarProyectoPorId(id); 
		return null;
	}

	/**
	 * Metodo para actualizar un objeto proyecto persistido.
	 * 
	 * @param p Objeto Proyecto a actualizar.
	 * @param params Parametros del objeto proyecto a modificar.
	 */

	public void actualizar(String id) {
		encontrarProyectoPorId(id);
        System.out.println("El proyecto con ID " + id + " ha sido actualizada"); 
	}

	/**
	 * Metodo para borrar un objeto proyecto persistido.
	 * 
	 * @param d Objeto proyecto a borrar.
	 */

	public void borrar(Proyecto p) {
        System.out.println("El proyecto con ID " + p.getIdProyecto() + "ha sido eliminado"); 
	}

	/**
	 * Metodo para recuperar todos los objetos proyecto persistidos.
	 * 
	 * @return Listado con los objetos proyecto persistidos.
	 */

	public List<Proyecto> obtenerTodos() {
		if (listadoProyectos.getListadoProyectos() != null) {
			System.out.println("La ONG cuenta con " + listadoProyectos.getListadoProyectos().size() + " sedes:");
	    	for (Proyecto p : listadoProyectos.getListadoProyectos()) {
	    		System.out.println(p.toString());
	    	}
	    	try {
	    		JAXBContext context = JAXBContext.newInstance(ListadoProyectos.class);
	    		Marshaller marshaller = context.createMarshaller();
	    		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    		marshaller.marshal(listadoProyectos, new File("xml/proyectos.xml"));
	    	} catch (JAXBException e) {
	    		e.printStackTrace();
	    	}
		    //Crea el directorio "xml" en caso de que no exista.
		    File f = new File("xml/");
		  	  if (!f.exists()) {
		  	    f.mkdirs();
		  	}
			
	    } else {
	    	System.out.println("La lista de proyectos está vacía.");
	    }
		return listadoProyectos.getListadoProyectos();
	}
	
	/**
	 * Metodo que permite encontrar una delegacion dentro del
	 * listado de delegaciones en funcion de su id.
	 * 
	 * @param id Id de la delegacion buscada.
	 * @return Delegacion buscada.
	 */
    public Proyecto encontrarProyectoPorId(String id) {
    	for (Proyecto p : listadoProyectos.getListadoProyectos()) {
    		if (p.getIdProyecto().equals(id)) {
    			return p;
    		}
    	}
    	return null;	
    }

}
