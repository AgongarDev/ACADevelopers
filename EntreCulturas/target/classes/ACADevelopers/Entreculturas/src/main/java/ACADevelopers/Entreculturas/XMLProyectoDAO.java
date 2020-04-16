package ACADevelopers.Entreculturas;

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

public class XMLProyectoDAO implements DAO<Proyecto>{
	
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
	@Override
	public void crearNuevo(Proyecto p) throws JAXBException {
		listadoProyectos.add(p);
		JAXBContext context = JAXBContext.newInstance(Proyecto.class);
	    Marshaller mar= context.createMarshaller();
	    mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    
	    //Crea el directorio "xml" en caso de que no exista.
	    File f = new File("xml/");
	  	  if (!f.exists()) {
	  	    f.mkdirs();
	  	}
	    
	    mar.marshal(p, new File("xml/proyecto.xml"));
	    
		System.out.println("Se ha creado un nuevo proyecto");
	}

	/**
	 * Metodo para obtener un objeto proyecto persistido.
	 * 
	 * @param id Identificador unico del objeto proyecto.
	 * @return Objeto proyecto persistido.
	 */
	@Override
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
	@Override
	public void actualizar(Proyecto p, String[] params) {
		encontrarProyectoPorId(p.getIdProyecto()).setNombreProyecto(p.getNombreProyecto());
        System.out.println("El proyecto con ID " + p.getIdProyecto()  + " ha sido actualizada"); 
	}

	/**
	 * Metodo para borrar un objeto proyecto persistido.
	 * 
	 * @param d Objeto proyecto a borrar.
	 */
	@Override
	public void borrar(Proyecto p) {
        System.out.println("El proyecto con ID " + p.getIdProyecto() + "ha sido eliminado"); 
	}

	/**
	 * Metodo para recuperar todos los objetos proyecto persistidos.
	 * 
	 * @return Listado con los objetos proyecto persistidos.
	 */
	@Override
	public List<Proyecto> obtenerTodos() throws JAXBException {
		if (listadoProyectos.getListadoProyectos() != null) {
			System.out.println("La ONG cuenta con " + listadoProyectos.getListadoProyectos().size() + " sedes:");
	    	for (Proyecto p : listadoProyectos.getListadoProyectos()) {
	    		System.out.println(p.toString());
	    	}
	    	JAXBContext context = JAXBContext.newInstance(ListadoProyectos.class);
	    	Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
		    //Crea el directorio "xml" en caso de que no exista.
		    File f = new File("xml/");
		  	  if (!f.exists()) {
		  	    f.mkdirs();
		  	}
			
			marshaller.marshal(listadoProyectos, new File("xml/proyectos.xml"));
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
