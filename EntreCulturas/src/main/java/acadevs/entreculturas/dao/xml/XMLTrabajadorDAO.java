package acadevs.entreculturas.dao.xml;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import acadevs.entreculturas.dao.DAO;
import acadevs.entreculturas.modelo.ListadoTrabajadores;
import acadevs.entreculturas.modelo.Trabajador;

/**
 * Esta clase instancia objetos XML DAO para interaccionar con
 * los objetos trabajador y persistirlos en formato XML.
 * 
 * @author Antonio,Cristina y Ana.
 * @version 1.0
 *
 */
public class XMLTrabajadorDAO implements DAO<Trabajador>{
	
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
	public void crearNuevo(Trabajador t) {
		listadoTrabajadores.add(t);
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(Trabajador.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			mar.marshal(t, new File("xml/trabajador.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void actualizar(String id) {
    	encontrarTrabajadorPorId(id); 
        System.out.println("El trabajador con ID " + id  + " ha sido actualizado"); 
		
	}

	/**
	 * Metodo para borrar un objeto trabajador persistido.
	 * 
	 * @param t Objeto trabajador a borrar.
	 */
	@Override
	public void borrar(Trabajador t) {
        System.out.println("El trabajador con ID " + t.getDni() + "ha sido eliminado"); 
	}

	/**
	 * Metodo para recuperar todos los objetos trabajador persistidos.
	 * 
	 * @return Listado con los objetos trabajador persistidos.
	 */
	@Override
	public List<Trabajador> obtenerTodos() {
		if (listadoTrabajadores.getListadoTrabajadores() != null) {
			System.out.println("La ONG cuenta con " + listadoTrabajadores.getListadoTrabajadores().size() + " trabajadores:");
	    	for (Trabajador t : listadoTrabajadores.getListadoTrabajadores()) {
	    		System.out.println(t.toString());
	    	}
	    	try {
	    		
	    		JAXBContext context = JAXBContext.newInstance(ListadoTrabajadores.class);
	    		Marshaller marshaller = context.createMarshaller();
	    		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);	
	    		marshaller.marshal(listadoTrabajadores, new File("xml/trabajadores.xml"));
	    	}
	    	catch (JAXBException e) {
	    		e.printStackTrace();
	    	}
	    	
		    //Crea el directorio "xml" en caso de que no exista.
		    File f = new File("xml/");
		  	  if (!f.exists()) {
		  	    f.mkdirs();
		  	}
			
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
    		if (t.getDni().equals(id)) {
    			return t;
    		}
    	}
    	return null;	
    }

}
