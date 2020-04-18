package acadevs.entreculturas.dao.mysql;

import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.IAdministracionFisicaDAO;
import acadevs.entreculturas.dao.IDAOListaFactories;
import acadevs.entreculturas.dao.ISocioDAO;
import acadevs.entreculturas.util.Utilidad;

/**
 * Esta clase factoria se usa para crear DAOs que usan MySQL para el almacenaje de los modelos.
 * Se heredan de la clase DAOFactory los DTO ISocioDAO, IAdministracionFisicaDAO
 * Implementa la interfaz IDAOListaFactories que tiene los métodos para llamar a los MySQLDAOS.
 * 
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
public class MySQLDAOFactory extends DAOFactory implements IDAOListaFactories<MySQLSocioDAO, MySQLAdministracionFisicaDAO> {
	
	public  MySQLDAOFactory () {	
		
		this.sociosDAO = null;
		this.administracionesDAO = null;
		}
		
	@Override
	public MySQLSocioDAO getSocioDAO() {
			
if (sociosDAO == null) {
			this.sociosDAO = (ISocioDAO) new MySQLSocioDAO(Utilidad.conexion);
		}
		return (MySQLSocioDAO) sociosDAO;
	}

	@Override
	public MySQLAdministracionFisicaDAO getAdministracionFisicaDAO() {
		
		if (administracionesDAO == null) {
			this.administracionesDAO = (IAdministracionFisicaDAO) new MySQLAdministracionFisicaDAO(Utilidad.conexion);
		}
		return (MySQLAdministracionFisicaDAO) administracionesDAO;
	}
}