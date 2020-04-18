package acadevs.entreculturas.vista;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.modelo.Config;
import acadevs.entreculturas.modelo.ViewException;

/**
 * 
 *Clase que lanza la aplicación
 *
 */
public class Application {
	
	public MySQLDAOFactory accesoMySQL;
	
	public static void main(String [] args) throws ViewException {
	
		configuracion();
		
		System.out.println("********************************************************************************");
		System.out.println("*                  Persistencia de datos con Java y MySQL                      *");
		System.out.println("********************************************************************************");
		System.out.println("* Producto 3 (P5) Programación Orientada a Objetos con acceso a Bases de Datos *");
		System.out.println("********************************************************************************");
		System.out.println("*                                                                              *");
		System.out.println("* Grupo ACADevelopers :     ANA IGLESIAS -                < anaigsan@uoc.edu > *");
		System.out.println("*                     : ANTONIO GONZÁLEZ -       < agonzalezgarcia10@uoc.edu > *");
		System.out.println("*                                                                              *");
		System.out.println("********************************************************************************");
		System.out.println("*                                                   Consultor: Josep Vaño Chic *");
		System.out.println("********************************************************************************");
		
		/*-Pendiente implementar una abstract factory para ver la aplicación en modo consola o en modo gráfico.
		 * 	-Aquí vendría la llamada a un método menú de selección que permita que el usuario decida el modo de visualización de la aplicación.
		 *-Pendiente implementar un método que tome el texto de un archivo .txt en el que expliquemos el proyecto y lo muestre en consola.
		 */
		tiempoDeCreditos();
		
		new MenuInvitado ();
	}
	
	public static void tiempoDeCreditos() throws ViewException {
			
		for (int i = 1 ; i < 80 ; i++) {
			try {
				 System.out.print("*");
			     Thread.currentThread();
				Thread.sleep(30);
			       }
			     catch (InterruptedException e) {
			       e.printStackTrace();
			       throw new ViewException("Error al intentar parar el proceso actual en el menú start", e);
			       }
		}
	}
	
	public static void limpiaPantalla() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void salirDelPrograma() {
		limpiaPantalla();
		System.out.println("*****************************************************************************");
		System.out.println("*                              MUCHAS GRACIAS                               *");
		System.out.println("*****************************************************************************");
		System.out.println("                                                                 @ Abril 2020");
		tiempoDeCreditos();
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
	
	public static void cierraConexionMySQL(MySQLDAOFactory mysqlf) throws DAOException {
		try {
			mysqlf.cerrar();
		} catch (SQLException e) {
			throw new DAOException("Error al intentar cerrar la base de datos", e);
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
	
	private static void configuracion() {
		
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

