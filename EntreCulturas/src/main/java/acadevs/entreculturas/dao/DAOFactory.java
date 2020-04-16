package acadevs.entreculturas.dao;

import java.sql.SQLException;

import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.dao.xml.XMLDAOFactory;

/**
 * Clase abstracta que permite seleccionar la factoria con la que queremos
 * persistir los datos.
 * 
 * @author Antonio, Cristina, Ana.
 * @version 1.0
 *
 */
public class DAOFactory {
	
	/* Lista de tipos DAO soportado por la factoria.
	 * Hay un metodo para cada DAO que puede ser creado.
	 */
	protected ISocioDAO sociosDAO;
	protected IAdministracionFisicaDAO administracionesDAO;
	
	public static DAOFactory getDAOFactory(String whichFactory) throws DAOException {
		
		switch (whichFactory) {
			
			case "XML": 
				return new XMLDAOFactory();
				
	        case "MySQL": 
	        	try {
	        		  return new MySQLDAOFactory("localhost", "3306", "entreculturasdB_v1", "root", "321998");	
	        	} catch (SQLException e ) {
	        		throw new DAOException("Error al crear el acceso DAO de MySQL", e);
	        	}
	         
	        default: 
	            return null;
	    }
		
	}
	/**
	 * Metodo que permite acceder a un Administrador XML data object.
	 * 
	 * @return Nos devuelve un trabajador XML data object
	 */
	
}
