package acadevs.entreculturas.dao.xml;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import acadevs.entreculturas.dao.IDAO;
import acadevs.entreculturas.modelo.Administrador;
import acadevs.entreculturas.modelo.ListadoAdministradores;
/**
 * Esta clase instancia objetos XML DAO para interaccionar con
 * los objetos administrador y persistirlos en formato XML.
 * 
 * @author Antonio,Cristina y Ana.
 * @version 1.0
 *
 */
public class XMLAdministradorDAO implements IDAO<Administrador, String>{
	// Campos
	private ListadoAdministradores listadoAdministradores = new ListadoAdministradores();	
	// Constructores
	
	/**
	 * Constructor que crea un nuevo objeto XMLAdministradorDAO sin inicializar sus campos.
	 */
     public XMLAdministradorDAO() {
    }
     
     /**
 	 * Constructor que crea un nuevo objeto XMLAdministradorDAO inicializando sus campos.
 	 * 
 	 * @param listadoAdministradores Atributo que guarda el listado de los administradores de la ONG.
 	 */
     public XMLAdministradorDAO(ListadoAdministradores listadoAdministradores) {
 		super();
 		this.listadoAdministradores = listadoAdministradores;
 	}
	// Metodos
    
     /**
 	 * Metodo accesor de lectura que nos da la lista de administradores de la ONG.
 	 * 
 	 * @return Nos devuelve la lista de administradores.
 	 */
     public ListadoAdministradores getListadoAdministradores() {
 		return listadoAdministradores;
 	}
     /**
 	 * Metodo accesor de escritura que asigna el listado de administradores de la ONG.
 	 * 
 	 * @param listadoAdministradores Listado de administradores que hay en la ONG.
 	 */
 	public void setListadoAdministradores(ListadoAdministradores listadoAdministradores) {
 		this.listadoAdministradores = listadoAdministradores;		
 	}
 	
 	/**
	 * Metodo para crear un nuevo objeto administrador a persistir
	 * en formato XML.
	 * 
	 * @param a Objeto administrador a persistir.
	 */
	@Override
	public void crearNuevo(Administrador a) {
				System.out.println("Se ha creado un nuevo administrador");
	}
	@Override
	public Administrador obtener(String dni) {
		System.out.println("Se ha creado un nuevo administrador:");
		 //return encontrarAdministradorPorId(id);
		return null;
	}
	@Override
	public void actualizar(Administrador admin) {
		
        System.out.println("El administrador con ID " + admin.getDni()+ " ha sido actualizado"); 		
	}
	/**
	 * Metodo para borrar un objeto administrador persistido.
	 * 
	 * @param a Objeto administrador a borrar.
	 */
	@Override
	public void borrar(Administrador a) {
		System.out.println("El administrador con ID " + a.getDni() + "ha sido eliminado"); 	
	}
	@Override
	public List<Administrador> obtenerTodos() {
		if (listadoAdministradores.getListadoAdministradores() != null) {
			System.out.println("La ONG cuenta con " + listadoAdministradores.getListadoAdministradores().size() + " administradores:");
	    	for (Administrador a : listadoAdministradores.getListadoAdministradores()) {
	    		System.out.println(a.toString());
	    	}   	
	    	try {
	    		JAXBContext context = JAXBContext.newInstance(ListadoAdministradores.class);
	    		Marshaller marshaller = context.createMarshaller();
	    		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    		marshaller.marshal(listadoAdministradores, new File("xml/administradores.xml"));
	    	} catch (JAXBException e) {
	    		e.printStackTrace();
	    	}
		    //Crea el directorio "xml" en caso de que no exista.
		    File f = new File("xml/");
		  	  if (!f.exists()) {
		  	    f.mkdirs();
		  	}
	    } else {
	    	System.out.println("La lista de administradores está vacía.");
	    }
		return listadoAdministradores.getListadoAdministradores();
	}
	
	/**
	 * Metodo que permite encontrar a un administrador dentro del
	 * listado de administradores en funcion de su id.
	 * 
	 * @param id Id del administrador buscado.
	 * @return Administrador buscado.
	 */
    public Administrador encontrarAdministradorPorId(String dni) {
    	for (Administrador a : listadoAdministradores.getListadoAdministradores()) {
    		if (a.getDni().equals(dni)) {
    			return a;
    		}
    	}
    	return null;	
    }
}
	