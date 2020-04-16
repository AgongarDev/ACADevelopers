package ACADevelopers.Entreculturas;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBException;

/**
 * Interfaz que proporciona acceso a los metodos de persistencia.
 * 
 * @author Antonio,Cristina,Ana.
 * @version 1.0
 *
 */
public interface DAO<T> {
	
	/**
	 * Metodo para crear un nuevo objeto a persistir.
	 * 
	 * @param t Objeto a persistir.
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
    public void crearNuevo(T t) throws JAXBException;
    
	/**
	 * Metodo para obtener un objeto persistido.
	 * 
	 * @param id Identificador unico del objeto.
	 * @return Objeto persistido.
	 */
    public Optional<T> obtener(String id); 
    
	/**
	 * Metodo para actualizar un objeto persistido.
	 * 
	 * @param t Objeto a actualizar.
	 * @param params Parametros del objeto a modificar.
	 */
    public void actualizar(T t, String[] params);
    
	/**
	 * Metodo para borrar un objeto persistido.
	 * 
	 * @param t Objeto a borrar.
	 */
    public void borrar(T t);
    
	/**
	 * Metodo para recuperar todos los objetos persistidos de un tipo.
	 * 
	 * @return Listado con los objetos persistidos.
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
    public List<T> obtenerTodos() throws JAXBException;

}
