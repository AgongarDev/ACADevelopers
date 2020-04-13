package ACADevelopers.Entreculturas;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.w3c.dom.Document;
// imports necesarios para realizar todo el trabajo de actualización e impresión de datos a XML

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.soap.Node;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.SAXException;

/**
 * Esta clase instancia objetos XML DAO para interaccionar con
 * los objetos socio y persistirlos en formato XML.
 * 
 * @author Antonio,Cristina y Ana.
 * @version 1.0
 *
 */
public class XMLSocioDAO implements DAO<Socio> {
	
	//CONSTANTES
	public static final String COMENTARIO = "\u001B[34m"; // Pinta de azúl el texto por consola
	private static String NL = System.getProperty("line.separator"); // separador de línea multiplataforma
	private static String RUTAXML = "xml/socios.xml"; // ruta del archivo de persistencia xml
	
	// VARIABLES
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // buffer de recogida de entrada teclado
	private ListadoSocios socios = new ListadoSocios();
	
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
		this.socios = listadoSocios;
	}

	
	// METODOS ACCESORES
	
	/**
	 * Metodo accesor de lectura que nos da el lista de socios de la ONG.
	 * 
	 * @return Nos devuelve el listado de socios.
	 */
	public ArrayList<Socio> getSocios() {
		return (ArrayList<Socio>) socios.getSocios();
	}
	/**
	 * Metodo accesor de escritura que asigna el listado de socios de la ONG.
	 * 
	 * @param listadoSocios El listado de socios de la ONG.
	 */
	public void setListadoSocios(ListadoSocios listadoSocios) {
		this.socios = listadoSocios;
	}
	
	// MÉTODOS DE CONTEXTO XML

	/**
	 * comprueba si el archivo o si tiene información para evitar excepciones de acceso por valores null.
	 * @param archivo
	 */
	public boolean archivoLegible (File archivo) {
			
		try {
			if ((!archivo.createNewFile()) && (archivo.length() != 0)) {
					return true;
				} else {
					System.out.println("...El archivo de socios no existe o no tiene datos"+COMENTARIO);
					System.out.println("...Se va a crear un nuevo archivo"+COMENTARIO);
				}
			} catch (IOException e) {
				e.printStackTrace();
				}
		return false;
	}
	
	/**
	 * Método para crear un nuevo objeto socio a persistir
	 * en formato XML.
	 * 
	 * @param s Objeto socio a persistir.
	 */
	@Override
	public void crearNuevo(Socio socio) {
		
		// crea el archivo destinado a guardar información de los socios en la carpeta xml.
		File archivoXML = new File(RUTAXML); 
		// guardamos en una lista contenedor los datos que obtenemos del archivo o una lista vacía si no existen.
		obtenerTodos(); 
			try {
				// añadimos los datos del nuevo socio a la lista contenedor
		    	this.getSocios().add(socio); 		    
		    	System.out.println("...Añadido un nuevo socio al listado"+COMENTARIO);
				
		    	// creamos el contexto de ListadoSocios
				JAXBContext jaxbContext = JAXBContext.newInstance(ListadoSocios.class);
				
				//creamos un marshaller para imprimir los datos de socio
				Marshaller marshaller = jaxbContext.createMarshaller(); 
			
				//configuramos que imprima los datos en un fichero con el formato estructurado en las clases Persona, Socio y ListadoSocios.
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
				//imprimimos
				marshaller.marshal(socios, archivoXML); 
				
				//arroja excepción si no encuentra datos que imprimir
				} catch (JAXBException ex) { 
					ex.printStackTrace();
					
		System.out.println(NL+"¡Enhorabuena! se ha creado un nuevo socio"); //literalmente, necesitaba leer algo así cuando consiguiera que se imprimiera un socio tras otro.
		System.out.println(NL+socio.toString());
		}
	}

	/**
	 * Metodo para obtener un objeto socio persistido.
	 * 
	 * @param id Identificador unico del objeto socio.
	 * @return Objeto socio persistido.
	 */
	//método sin programar
	@Override
	public Optional<Socio> obtener(String id) {
		System.out.println("...Se ha obtenido un socio"+COMENTARIO);
        //return encontrarTrabajadorPorId(id); 
		return null;
	}

	/**
	 * Crea un documento del tipo DOM para poder interactuar con los datos de un archivo de lenguaje de marcado.
	 * @param rutaDOM la ruta del archivo a formatear
	 * @throw ParserConfigurationException
	 * @throw SAXException
	 * @Throw IOException
	 * @return Documento formateado para interacción.
	 * */
    public Document creaDOM(String rutaDOM) throws ParserConfigurationException, SAXException, IOException {
    	
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       	factory.setNamespaceAware(true);
       	DocumentBuilder builder = factory.newDocumentBuilder();
       	Document doc = builder.parse(new File(rutaDOM));
       	
       	return doc;
    }
    /**
     * Método para actualizar un objeto socio persistido.
	 * 
	 * @param s Objeto socio a actualizar.
	 * @param id dni del socio al que apuntar.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws XPathExpressionException
	 * @throws TransformerException
     */
	@Override
	public void actualizar (String id) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
		
        System.out.println("Introduzca el campo a modificar");
 	   	String nodoACambiar = br.readLine(); //guardamos el string del nodo a modificar. Tiene que tener el mismo formato que en el xml (no espacios, minúsculas, acentos,...)
 	   
 	   	System.out.println("Introduzca el nuevo valor");
 	   	String valorACambiar = br.readLine(); // guardamos el nuevo valor (Strings independientemente del tipo (float,int..) ya que eso se programará en la lectura de datos.
 	   	
 	   	File archivoXML = new File(RUTAXML); //creamos el archivo
 	   	if (archivoLegible(archivoXML)) { // si el archivo creado existe y tiene datos...
	 	   	
 	   		Document doc = creaDOM(RUTAXML); // creamos el DOM para poder navegar entre los nodos
	 		XPath xpath = XPathFactory.newInstance().newXPath(); //creamos el path con el API XPath
			XPathExpression expr = xpath.compile("/socio[@dni='"+id+"']/"+nodoACambiar); // compilamos la ruta del nodo
			Node nodo = (Node) expr.evaluate(doc, XPathConstants.NODE); // apuntamos al nodo objetivo
			nodo.setTextContent(valorACambiar); // cambiamos el valor del nodo 
			
			Transformer tf = TransformerFactory.newInstance().newTransformer(); //transformador para la sobreimpresión
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.METHOD, "xml");
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
			DOMSource domSource = new DOMSource(doc); // transformamos la información
			StreamResult sr = new StreamResult(new File(RUTAXML)); //localizamos el archivo a actualizar
			tf.transform(domSource, sr);// imprimimos los datos en el archivo
			
 	   	} else { // si el archivo no tiene datos o no existe, sale con mensaje.
 	   		System.out.println(NL+"No existen datos a actualizar");
 	   	}
	}

	/**
	 * Metodo para borrar un objeto socio persistido.
	 * 
	 * @param s Objeto socio a borrar.
	 */
	@Override
	//método no programado
	public void borrar(Socio s) {
        System.out.println(NL+"El socio con DNI " + s.getDni() + "ha sido eliminado"); 
	}

	/**
	 * Metodo para recuperar todos los objetos socio persistidos.
	 * Devuelve un listado del tipo Array con los objetos del tipo socio formateados del archivo xml
	 * 
	 * @return Listado con los objetos socio persistidos.
	 */
	@Override
	public List<Socio> obtenerTodos() {
	
		File archivoXML = new File(RUTAXML);
		
		if (archivoLegible(archivoXML)) { //si el archivo existe y tiene datos
			
			try {
					JAXBContext jaxbContext = JAXBContext.newInstance(ListadoSocios.class);
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller(); //creamos un unmarshaller para leer los datos del archivo xml
					this.socios = (ListadoSocios) unmarshaller.unmarshal(archivoXML); //volcamos los datos formateados en una lista contenedor.

				} catch (JAXBException e) {
					e.printStackTrace();
				}			
								
			if (socios.getSocios() != null) { // si el listado tiene datos imprimimos una línea con el número de socios
				System.out.println(NL+"La ONG cuenta con " + socios.getSocios().size() + " socios:");
		    	for (Socio socio : socios.getSocios()) { //imprimimos todos los datos de los socios en pantalla
		    		System.out.println(socio.toString());
		    		}
			}
		} else { // si no existe el archivo o no tiene datos, devuelve un array vacío para evitar problemas de acceso del tipo null.
			System.out.println("...La lista de socios está vacía."+COMENTARIO);
			return new ArrayList<Socio>();
		  }
		return socios.getSocios();
	}
	
 }
