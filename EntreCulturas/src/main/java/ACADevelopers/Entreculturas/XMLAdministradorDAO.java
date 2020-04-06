package ACADevelopers.Entreculturas;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Esta clase instancia objetos XML DAO para interaccionar con
 * los objetos trabajador y persistirlos en formato XML.
 * 
 * @author Yaiza, Teresa y Marc.
 * @version 1.0
 *
 */
public class XMLAdministradorDAO implements DAO<Trabajador>{
	
	// CAMPOS
	
	private ListadoTrabajadores listadoTrabajadores = new ListadoTrabajadores();
	
	
	// CONSTRUCTORES
	
	/**
	 * Constructor que crea un nuevo objeto XMLTrabajadorDAO sin inicializar sus campos.
	 */
    public XMLTrabajadorDAO() {
    	
    }

	/**
	 * Constructor que crea un nuevo objeto XMLTrabajadorDAO inicializando sus campos.
	 * 
	 * @param listadoTrabajadores Atributo que guarda el listado de los trabajadores de la ONG.
	 */
	public XMLTrabajadorDAO(ListadoTrabajadores listadoTrabajadores) {
		super();
		this.listadoTrabajadores = listadoTrabajadores;
	}
    
    
	// METODOS
	
	/**
	 * Metodo accesor de lectura que nos da el lista de trabajadores de la ONG.
	 * 
	 * @return Nos devuelve el listado de trabajadores.
	 */
	public ListadoTrabajadores getListadoTrabajadores() {
		return listadoTrabajadores;
	}
	
	/**
	 * Metodo accesor de escritura que asigna el listado de trabajadores de la ONG.
	 * 
	 * @param listadoTrabajadores El listado de trabajadores de la ONG.
	 */
	public void setListadoTrabajadores(ListadoTrabajadores listadoTrabajadores) {
		this.listadoTrabajadores = listadoTrabajadores;
	}

	/**
	 * Metodo para crear un nuevo objeto trabajador a persistir
	 * en formato XML.
	 * 
	 * @param t Objeto trabajador a persistir.
	 */
	@Override
	public void crearNuevo(Trabajador t) throws JAXBException {
		listadoTrabajadores.add(t);
		JAXBContext context = JAXBContext.newInstance(Trabajador.class);
	    Marshaller mar= context.createMarshaller();
	    mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    
	    //Crea el directorio "xml" en caso de que no exista.
	    File f = new File("xml/");
	  	  if (!f.exists()) {
	  	    f.mkdirs();
	  	}
	  	  
	    mar.marshal(t, new File("xml/trabajador.xml"));
		System.out.println("Se ha creado un nuevo trabajador");
	}

	/**
	 * Metodo para obtener un objeto trabajador persistido.
	 * 
	 * @param id Identificador unico del objeto trabajador.
	 * @return Objeto trabajador persistido.
	 */
	@Override
	public Optional<Trabajador> obtener(String id) {
		System.out.println("Se ha obtenido un trabajador");
        //return encontrarTrabajadorPorId(id); 
		return null;
	}

	/**
	 * Metodo para actualizar un objeto trabajador persistido.
	 * 
	 * @param t Objeto trabajador a actualizar.
	 * @param params Parametros del objeto trabajador a modificar.
	 */
	@Override
	public void actualizar(Trabajador t, String[] params) {
    	encontrarTrabajadorPorId(t.getId()).setNombre(t.getNombre()); 
        System.out.println("El trabajador con ID " + t.getId()  + " ha sido actualizado"); 
		
	}

	/**
	 * Metodo para borrar un objeto trabajador persistido.
	 * 
	 * @param t Objeto trabajador a borrar.
	 */
	@Override
	public void borrar(Trabajador t) {
        System.out.println("El trabajador con ID " + t.getId() + "ha sido eliminado"); 
	}

	/**
	 * Metodo para recuperar todos los objetos trabajador persistidos.
	 * 
	 * @return Listado con los objetos trabajador persistidos.
	 */
	@Override
	public List<Trabajador> obtenerTodos() throws JAXBException {
		if (listadoTrabajadores.getListadoTrabajadores() != null) {
			System.out.println("La ONG cuenta con " + listadoTrabajadores.getListadoTrabajadores().size() + " trabajadores:");
	    	for (Trabajador t : listadoTrabajadores.getListadoTrabajadores()) {
	    		System.out.println(t.toString());
	    	}
	    	
	    	JAXBContext context = JAXBContext.newInstance(ListadoTrabajadores.class);
	    	Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
		    //Crea el directorio "xml" en caso de que no exista.
		    File f = new File("xml/");
		  	  if (!f.exists()) {
		  	    f.mkdirs();
		  	}
			
			marshaller.marshal(listadoTrabajadores, new File("xml/trabajadores.xml"));
			
	    } else {
	    	System.out.println("La lista de trabajadores está vacía.");
	    }
		return listadoTrabajadores.getListadoTrabajadores();
	}
	
	/**
	 * Metodo que permite encontrar uno trabajador dentro del
	 * listado de trabajadores en funcion de su id.
	 * 
	 * @param id Id del trabajador buscado.
	 * @return Trabajador buscado.
	 */
    public Trabajador encontrarTrabajadorPorId(String id) {
    	for (Trabajador t : listadoTrabajadores.getListadoTrabajadores()) {
    		if (t.getId().equals(id)) {
    			return t;
    		}
    	}
    	return null;	
    }

}
