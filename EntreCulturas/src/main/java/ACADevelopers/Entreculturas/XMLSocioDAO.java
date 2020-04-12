package ACADevelopers.Entreculturas;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;

import org.w3c.dom.Document;

/* estos imports son necesarios para realizar todo el trabajo de actualización e impresión de datos a XML*/
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.crypto.dsig.spec.XPathType;
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
	
	// CAMPOS
	
	private static String RUTAXML = "./xml/socios.xml";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private boolean haySocios = true;
	
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
	
	public boolean archivoLegible (File archivo) {
			
		try {
			if ((!archivo.createNewFile()) && (archivo.length() != 0)) {
					return true;
				} else {
					System.out.println("Archivo sin datos");
			
				}
			} catch (IOException e) {
				e.printStackTrace();
				}
		return false;
	}
	
	/**
	 * Metodo para crear un nuevo objeto socio a persistir
	 * en formato XML.
	 * 
	 * @param s Objeto socio a persistir.
	 * @throws JAXBException 
	 */
	@Override
	public void crearNuevo(Socio s) {
		
		File archivoXML = new File(RUTAXML);
		List<Socio> socios = obtenerTodos();
	
			try {
				socios.add(s);
				FileOutputStream fos = new FileOutputStream(archivoXML);
				XMLEncoder encoder = new XMLEncoder(fos);
				encoder.writeObject(socios);
				encoder.close();
				fos.close();
				
			} catch ( IOException ex) {
				ex.printStackTrace();
			}
			
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

	/*
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	

	/**
	 * Metodo que permite encontrar uno socio dentro del
	 * listado de socios en funcion de su id.
	 * 
	 * @param id Id del socio buscado.
	 * @return Socio buscado.
	 * @throws XPathExpressionException 
	 */
	
    public Node encontrarSocioPorDni(String id, XPath xpath, Document doc) throws XPathExpressionException {
    	
    	Node nodoAActualizar = (Node) xpath.compile("//socio[dni='"+id+"']").evaluate(doc, XPathConstants.NODE);
    	
		return nodoAActualizar;
        }
    
	/* Método para actualizar un objeto socio persistido.
	 * 
	 * 	@param s Objeto socio a actualizar.
	 * @param params Parametros del objeto socio a modificar.
	 */
	@Override
	public void actualizar (String id) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(RUTAXML));
		
 	   	System.out.println("Introduzca el campo a modificar");
 	   	String nodoACambiar = br.readLine();
 	   
 	   	System.out.println("Introduzca el nuevo valor");
 	   	String valorACambiar = br.readLine();
 	   	
 		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile("/socio[@dni='"+id+"']/"+nodoACambiar);
		Node nodo = (Node) expr.evaluate(doc, XPathConstants.NODE);
		nodo.setTextContent(valorACambiar);
		
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		tf.setOutputProperty(OutputKeys.METHOD, "xml");
		tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		
		DOMSource domSource = new DOMSource(doc);
		StreamResult sr = new StreamResult(new File(RUTAXML));
		tf.transform(domSource, sr);
      
	}

	/**
	 * Metodo para borrar un objeto socio persistido.
	 * 
	 * @param s Objeto socio a borrar.
	 */
	@Override
	public void borrar(Socio s) {
        System.out.println("El socio con DNI " + s.getDni() + "ha sido eliminado"); 
	}

	/**
	 * Metodo para recuperar todos los objetos socio persistidos.
	 * 
	 * @return Listado con los objetos socio persistidos.
	 */
	@Override
	public List<Socio> obtenerTodos() {
	
		File archivoXML = new File(RUTAXML);
		
		if (archivoLegible(archivoXML)) {
		
			try {
				FileInputStream fis = new FileInputStream(archivoXML);
				XMLDecoder decoder = new XMLDecoder(fis);
			
				@SuppressWarnings("unchecked")
				List<Socio> lista = (List<Socio>) decoder.readObject();
				decoder.close();
				fis.close();
				this.listadoSocios.setListadoSocios(lista);
				
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		
			if (listadoSocios.getListadoSocios() != null) {
				System.out.println("La ONG cuenta con " + listadoSocios.getListadoSocios().size() + " socios:");
		    	for (Socio s : listadoSocios.getListadoSocios()) {
		    		System.out.println(s.toString());
		    		}
		    	} 
			} else {
				System.out.println("La lista de socios está vacía.");
				return new ArrayList<Socio>();
		  }
		return listadoSocios.getListadoSocios();
	}
	
 }
