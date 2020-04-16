package ACADevelopers.Entreculturas;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Esta clase instancia objetos XML DAO para interaccionar con
 * los objetos socio y persistirlos en formato XML.
 * 
 * @author Antonio,Cristina y Ana.
 * @version 1.0
 *
 */
public class XMLSocioDAO implements DAO<Socio> {
	
	// CAMPOS
	
	private ListadoSocios listadoSocios = new ListadoSocios();
	
	
	// CONSTRUCTORES
	
	/**
	 * Constructor que crea un nuevo objeto XMLSocioDAO sin inicializar sus campos.
	 */
    public XMLSocioDAO() {
    	
    }
    
	/**
	 * Constructor que crea un nuevo objeto XMLSocioDAO inicializando sus campos.
	 * 
	 * @param listadoSocios Atributo que guarda el listado de los socios de la ONG.
	 */
	public XMLSocioDAO(ListadoSocios listadoSocios) {
		super();
		this.listadoSocios = listadoSocios;
	}

	
	// METODOS
	
	/**
	 * Metodo accesor de lectura que nos da el lista de socios de la ONG.
	 * 
	 * @return Nos devuelve el listado de socios.
	 */
	public ListadoSocios getListadoSocios() {
		return listadoSocios;
	}

	/**
	 * Metodo accesor de escritura que asigna el listado de socios de la ONG.
	 * 
	 * @param listadoSocios El listado de socios de la ONG.
	 */
	public void setListadoSocios(ListadoSocios listadoSocios) {
		this.listadoSocios = listadoSocios;
	}

	/**
	 * Metodo para crear un nuevo objeto socio a persistir
	 * en formato XML.
	 * 
	 * @param s Objeto socio a persistir.
	 */
	@Override
	public void crearNuevo(Socio s) throws JAXBException {
		listadoSocios.add(s);
		JAXBContext context = JAXBContext.newInstance(Socio.class);
	    Marshaller mar= context.createMarshaller();
	    mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    
	    //Crea el directorio "xml" en caso de que no exista.
	    File f = new File("xml/");
	  	  if (!f.exists()) {
	  	    f.mkdirs();
	  	}
	  	
	    mar.marshal(s, new File("xml/socio.xml"));

		System.out.println("Se ha creado un nuevo socio");
	}

	/**
	 * Metodo para obtener un objeto socio persistido.
	 * 
	 * @param id Identificador unico del objeto socio.
	 * @return Objeto socio persistido.
	 */
	@Override
	public Optional<Socio> obtener(String id) {
		System.out.println("Se ha obtenido un socio");
        //return encontrarTrabajadorPorId(id); 
		return null;
	}

	/**
	 * Metodo para actualizar un objeto socio persistido.
	 * 
	 * @param s Objeto socio a actualizar.
	 * @param params Parametros del objeto socio a modificar.
	 */
	@Override
	public void actualizar(Socio s, String[] params) {
    	encontrarSocioPorId(s.getDni()).setNombre(s.getNombre()); 
        System.out.println("El socio con ID " + s.getDni()  + " ha sido actualizado"); 
	}

	/**
	 * Metodo para borrar un objeto socio persistido.
	 * 
	 * @param s Objeto socio a borrar.
	 */
	@Override
	public void borrar(Socio s) {
        System.out.println("El socio con ID " + s.getDni() + "ha sido eliminado"); 
	}

	/**
	 * Metodo para recuperar todos los objetos socio persistidos.
	 * 
	 * @return Listado con los objetos socio persistidos.
	 */
	@Override
	public List<Socio> obtenerTodos() throws JAXBException {
		if (listadoSocios.getListadoSocios() != null) {
			System.out.println("La ONG cuenta con " + listadoSocios.getListadoSocios().size() + " socios:");
	    	for (Socio s : listadoSocios.getListadoSocios()) {
	    		System.out.println(s.toString());
	    	}
	    	
	    	JAXBContext context = JAXBContext.newInstance(ListadoSocios.class);
	    	Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
		    //Crea el directorio "xml" en caso de que no exista.
		    File f = new File("xml/");
		  	  if (!f.exists()) {
		  	    f.mkdirs();
		  	}
			
			marshaller.marshal(listadoSocios, new File("xml/socios.xml"));
	    } else {
	    	System.out.println("La lista de socios está vacía.");
	    }
		return listadoSocios.getListadoSocios();
	}
	
	/**
	 * Metodo que permite encontrar uno socio dentro del
	 * listado de socios en funcion de su id.
	 * 
	 * @param id Id del socio buscado.
	 * @return Socio buscado.
	 */
    public Socio encontrarSocioPorId(String dni) {
    	for (Socio s : listadoSocios.getListadoSocios()) {
    		if (s.getDni().equals(dni)) {
    			return s;
    		}
    	}
    	return null;	
    }

}
