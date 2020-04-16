package acadevs.entreculturas.dao;

import java.util.List;

import org.xml.sax.SAXException;

/**
 * Interfaz que proporciona acceso a los metodos de persistencia.
 * 
 * @author Antonio,Cristina,Ana.
 * @version 1.0
 *
 */
public interface IDAO<T, K> {
	
	/**
	 * Metodo para crear un nuevo objeto a persistir.
	 * 
	 * @param t Objeto a persistir.
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 * @throws IOException 
	 */
    public void crearNuevo(T t) throws DAOException;
    
	/**
	 * Metodo para obtener un objeto persistido.
	 * 
	 * @param id Identificador unico del objeto.
	 * @return Objeto persistido.
	 */
    public T obtener(K id) throws DAOException; 
    
	/**
	 * Metodo para actualizar un objeto persistido.
	 * 
	 * @param id el identificador del objeto.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws TransformerException 
	 * @throws XPathExpressionException 
	 */
    public void actualizar (T t) throws DAOException;
    
	/**
	 * Metodo para borrar un objeto persistido.
	 * 
	 * @param t Objeto a borrar.
	 */
    public void borrar(T t) throws DAOException;
    
	/**
	 * Metodo para recuperar todos los objetos persistidos de un tipo.
	 * 
	 * @return Listado con los objetos persistidos.
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
    public List<T> obtenerTodos() throws DAOException;

}
