package acadevs.entreculturas.util;

import java.io.File;
import java.io.IOException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import acadevs.entreculturas.dao.DAOException;

/**
 * @author Ana Iglesias, Antonio González
 * Clase formada por métodos estáticos o de clase variados y comunes a varias clases de la aplicación para simplificar y unificar estas acciones.
 */
public class Utilidad {

	public static Connection conexion = null;
	
	//limpia pantalla de emulador de terminal cmd windows
	public static void limpiaPantalla() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		} 
	}
	
	//conversor java.util -> java.sql
	public static Date conversorFecha(java.util.Date uFecha) {
		Date sqlDate = new Date (uFecha.getTime());
		
		return sqlDate;
	}
	
	//conversor java.sql -> java.util
	public static java.util.Date conversorFecha(Date sqlDate) {
		java.util.Date uDate = new java.util.Date (sqlDate.getTime());
		
		return uDate;
	}
	
	public static LocalDate toDateToLocal(java.util.Date date) {
	    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static java.util.Date toDateToLocal(LocalDate localDate) {
	    return java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	// valida Dni con el formato y letras aceptados por el sistema nacional de identificación de personas fisicas.
	public static boolean validarNIF(String nif) {

	    boolean valido = false;

	  //patrón de expresión regular: debe empezar con almenos un dígito y máximo 8 y continuar con una letra del grupo.
	    Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])"); 
	    
	    Matcher matcher = pattern.matcher(nif);

	    if (matcher.matches()) {
	        String letra = matcher.group(2);
	        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
	        int index = Integer.parseInt(matcher.group(1));
	        index = index % 23;
	        String reference = letras.substring(index, index + 1);

	        if (reference.equalsIgnoreCase(letra)) {
	            valido = true;
	        } else {
	            valido = false;
	        }
	    } else {
	        valido = false;
	    }
	    return valido;
	}
	
	//valida el número de telefono sólo números y solo 9
	public static boolean validarNumeroTelefono(String numero) {
		final String regexStr = "\\d{9}";
		 CharSequence inputStr = numero;
	       Pattern pattern = Pattern.compile(regexStr);
	       Matcher matcher = pattern.matcher(inputStr);
	       
	       return (matcher.matches());
	}
	
	//valida que el valor pasado (leído) sea un entero o un numero de coma flotante.
	public static boolean validarFloat(String numero) {
			try {
				Integer.parseInt(numero);
				return true;
			} catch (NumberFormatException nfe){
				return false;
			}
		}
	
	//valida que el email tenga un formato correcto
	public static boolean validarMail(String mail) {
		
			Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			 
	        Matcher mather = pattern.matcher(mail);
	        return mather.find();
	}
	
	//valida que el archivo pasado exista, tenga información y sea legible.
	public static boolean archivoLegible (File archivo) {
		
		try {
			if ((!archivo.createNewFile()) && (archivo.length() != 0)) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			}
		return false;
	}
	
	// conecta a la base de datos.
	public static void conectarMySQL(String sgbd) {
		
		if (sgbd.equalsIgnoreCase("MySQL")) {
			String jdbc = "jdbc:mysql://";
			
			try {
				conexion = DriverManager.getConnection(jdbc+Config.MySQLHost+":"+Config.MySQLPuerto+"/"+Config.MySQLDataBase+
					"?useLegacyDatetimeCode=false&serverTimezone=UTC", Config.MySQLUsuario, Config.MySQLPass); 
			} catch (SQLException e) {
				System.out.println("Imposible conectar con la base de datos");
				}
		}
	}
	
	public static void cierraConexionMySQL() throws DAOException {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				throw new DAOException("Error al intentar cerrar la base de datos", e);
				}
		}
	}
	
	public static void cierraStat(PreparedStatement stat) throws DAOException{
		
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				throw new DAOException("Error al cerrar la llamada a la tabla Administraciones", e);
			}
		}
	}
	
	public static void cierraRs(ResultSet rs) throws DAOException{
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DAOException ("Error al cerrrar el resultado de la llamada a la tabla Administraciones", e);
			}
		}
	}
	
	public static void activaAutoCommit (Connection con, boolean activar) {
		try {
			con.setAutoCommit(activar);
		} catch (SQLException e) {
			new DAOException ("Error al manejar autocommit en una transición", e);
		}
	}
	
	public static void cargaConfiguracion() {
		
		File archivoConfig = new File("config.xml");
		
		Config config = null;
		
		if (archivoLegible(archivoConfig)) {
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				 config = (Config) unmarshaller.unmarshal(archivoConfig);
			} catch (JAXBException e){
				System.out.println("Error al cargar los datos de config.txt . Se aplicarán los valores por defecto \n"+e.toString());
			}
		} else { 
			grabaConfiguracion(config);
		}
	}
	
	public static void grabaConfiguracion(Config config) {
		
		File archivoConfig = new File("config.xml");
		if (config == null) {
			config = new Config();
		} else {
			if (archivoLegible(archivoConfig)) {
				try {
					JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.marshal(config, archivoConfig);
				} catch (JAXBException e) {
					System.out.println("Error al crear el archivo config.xml"+e.toString());
					}
			}
		}
	}
}
