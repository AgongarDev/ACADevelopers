package acadevs.entreculturas.dao;

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
			return new MySQLDAOFactory();
	         
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
