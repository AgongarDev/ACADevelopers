package acadevs.entreculturas.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.modelo.Config;

public class Utilidad {

	public static Connection conexion = null;
	
	public static void limpiaPantalla() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static boolean validarNIF(String nif) {

	    boolean valido = false;

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
	
	public static boolean validarNumeroTelefono(String numero) {
		final String regexStr = "^[6789]+d{8}$";
		System.out.println("Formato de telefono incorrecto");
		return (!Pattern.matches(regexStr, numero));
	}
	
	public static boolean validarFloat(String numero) {
		final String regexStr = "^[0-9]*\\.?[0-9]+$";
		System.out.println("Formato de importe incorrecto");
		return (!Pattern.matches(regexStr, numero));
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
	
	
	public static void cargaConfiguracion() {
		
		File archivoConfig = new File("config.xml");
		Config config = new Config();
		if (archivoLegible(archivoConfig)) {
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				 config = (Config) unmarshaller.unmarshal(archivoConfig);
			} catch (JAXBException e){
				System.out.println("Error al cargar los datos de config.txt . Se aplicarán los valores por defecto \n"+e.toString());
			}
		} else { 
				try {
					JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.marshal(config, archivoConfig);
				} catch (JAXBException e) {
					System.out.println("Error al crear el archivo config.xml"+e.toString());
				}
			System.out.println("No se ha encontrado el archivo de configuración. Se aplicarán los valores por defecto");	
			}
	}


}
