package acadevs.entreculturas.dao;

/**
 * 
 * @author Ana, Cristina, Antonio
 *
 * Esta interfaz se utiliza para controlar que todas las factorias realicen una llamada a los DAO de los modelos que queremos persistir
 * sea de la manera que sea.
 * En la versión 1.0 del programa de gestión básica de entreculturas, se utilizan dos tipos de persistencias: XML y MySql
 *
 * @param <T> tipo del primer getDAO
 * @param <K> tipo del segundo getDAO
 */
public interface IDAOListaFactories <T, K>{

	public T getSocioDAO();
	
	public K getAdministracionFisicaDAO();
	
}
