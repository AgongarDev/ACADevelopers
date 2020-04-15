package acadevs.entreculturas.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.SocioDAO;
import acadevs.entreculturas.dao.AdministracionFisicaDAO;

/**
 * Esta clase factoria se usa para crear DAOs que usan
 * SQL para el almacenaje de los modelos.
 * 
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
public class MySQLDAOFactory extends DAOFactory {
	
	public static final String COMENTARIO = "\u001B[34m"; // Pinta de azúl el texto por consola
	private String jdbc = "jdbc:mysql://";
	
	private Connection conexion = null;
	
	private AdministracionFisicaDAO administraciones = null;
	private SocioDAO socios = null;
	
	public  MySQLDAOFactory (String host, String port, String dB, String usuario, String pass) throws SQLException {	
	
		conexion = DriverManager.getConnection(jdbc+host+":"+port+"/"+dB, usuario, pass); 
		System.out.println("...Conexión establecida con éxito"+COMENTARIO);
	}
	
	public void cerrar() throws SQLException {
		if (conexion != null) { 
			conexion.close();
		}
	}
	
	@Override
	public SocioDAO getSocioDAO() {
		
		if (socios == null) {
			socios = new MySQLSocioDAO(conexion);
		}
		return socios;
	}

	@Override
	public AdministracionFisicaDAO getAdministracionFisicaDAO() {
		
		if (administraciones == null) {
			administraciones = new MySQLAdministracionFisicaDAO(conexion);
		}
		return administraciones;
	}
}