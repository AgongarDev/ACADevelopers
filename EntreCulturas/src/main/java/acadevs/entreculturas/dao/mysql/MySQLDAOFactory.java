package acadevs.entreculturas.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.IAdministracionFisicaDAO;
import acadevs.entreculturas.dao.IDAOListaFactories;
import acadevs.entreculturas.dao.ISocioDAO;
import acadevs.entreculturas.modelo.Config;

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
	
	private String jdbc = "jdbc:mysql://";
	
	private Connection conexion = null;
	
	public  MySQLDAOFactory () throws SQLException {	
		
		this.sociosDAO = null;
		this.administracionesDAO = null;
		conexion = DriverManager.getConnection(jdbc+Config.MySQLHost+":"+Config.MySQLPuerto+"/"+Config.MySQLDataBase+
				"?useLegacyDatetimeCode=false&serverTimezone=UTC", Config.MySQLUsuario, Config.MySQLPass); 
	}
	
	public void cerrar() throws SQLException {
		
		if (conexion != null) { 
			conexion.close();
		}
	}
	
	@Override
	public MySQLSocioDAO getSocioDAO() {
			
if (sociosDAO == null) {
			this.sociosDAO = (ISocioDAO) new MySQLSocioDAO(conexion);
		}
		return (MySQLSocioDAO) sociosDAO;
	}

	@Override
	public MySQLAdministracionFisicaDAO getAdministracionFisicaDAO() {
		
		if (administracionesDAO == null) {
			this.administracionesDAO = (IAdministracionFisicaDAO) new MySQLAdministracionFisicaDAO(conexion);
		}
		return (MySQLAdministracionFisicaDAO) administracionesDAO;
	}
}