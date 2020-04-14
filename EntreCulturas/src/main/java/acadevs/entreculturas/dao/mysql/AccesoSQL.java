package acadevs.entreculturas.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoSQL {

	public static final String COMENTARIO = "\u001B[34m"; // Pinta de azúl el texto por consola
	
	private Connection conexion = null;
	
	public void conectar(String host,  String usuario, String pass) throws SQLException {
		String jdbc = "jdbc:mysql://localhost:3306/entreculturasdb_v1";
		conexion = DriverManager.getConnection(jdbc, usuario, pass); 
		System.out.println("...Conexión establecida con éxito"+COMENTARIO);
	}
	
	public void cerrar() throws SQLException {
		if (conexion != null) { 
			conexion.close();
		}
	}
}
