package acadevs.entreculturas.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.regex.Pattern;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLSocioDAO;
import acadevs.entreculturas.modelo.ListadoProyectos;
import acadevs.entreculturas.modelo.Socio;
import acadevs.entreculturas.modelo.ViewException;

public class MenuInvitado {
	
	private final String COMENTARIO = "\u001B[34m"; // Pinta de azúl el texto por consola
	private final String NL = System.getProperty("line.separator"); // separador de línea multiplataforma
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	
	public MenuInvitado() throws ViewException {

		try {
				imprimeMenu();
		} catch (IOException | DAOException | ViewException | ParseException e) {
			e.printStackTrace();
			throw new ViewException ("Hubo un problema en el menú de usuario invitado", e);
		}
	}
	
	public void imprimeMenu() throws IOException, DAOException, ViewException, ParseException{
		
		Application.limpiaPantalla();
		
		String id = "";
		
//MENU DE INTERACCIÓN
		int opcion = 0;
		Integer[] opcionesValidas = {0, 1, 2, 3};
		
    	System.out.println("*****Entreculturas ONG******");
    	System.out.println("****************************");
    	System.out.println("Bienvenido, usuario invitado");
    	System.out.println("****************************");
    	
        do {
        	System.out.println(NL+"¿Qué desea hacer?: "+COMENTARIO);
        	System.out.println("1 - Iniciar sesión como administrador");
        	System.out.println("2 - Iniciar sesión como socio");
        	System.out.println("3 - Ver los proyectos publicados");
        	System.out.println("0 - Salir");
        	
        	try {
        		opcion = Integer.parseInt(br.readLine());
            } catch(NumberFormatException nfe) {
                System.out.println("Error. Introduzca un número del 1 al 3");
            } catch (IOException e) {
    			throw new ViewException ("Hubo un problema en la recepción de la entrada de teclado para la opción", e);
			}
        	
        } while (!Arrays.asList(opcionesValidas).contains(opcion));
        
//***********************************
        switch(opcion) {
           case 1:
        	   do {
        		  System.out.println("Introduzca la contraseña de administrador (PW = ACADevelopers) o marque 0 para volver al menú de invitado:");
					id = br.readLine();
        	   } while ((id != "ACADevelopers") || (id !="0")); // lo correcto sería disponer de una tabla usuarios en la BD donde se guardara el nombre, el id y la contraseña encriptada.
        	   if (id == "0") {
        		   imprimeMenu();
        	   } else {
        		   new MenuAdministrador();
        	   }
           	   break;
           
           case 2:
        	   abrirSesionSocio(id);
        	   
           case 3:
        	  ListadoProyectos ListaProyectos = new ListadoProyectos();
        	  ListaProyectos.getListadoProyectos();
        	  break;   
           case 4:
        	  System.out.println("...La sesión se ha cerrado con �xito."+COMENTARIO);
        	  Application.salirDelPrograma();
              break;
        }
	}
	
	private void abrirSesionSocio(String id) throws IOException, DAOException, ViewException, ParseException {
		
		MySQLDAOFactory mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL"); 
		MySQLSocioDAO socios = mysqlF.getSocioDAO();
 	   
 	   	do {
 	   		System.out.println("Introduzca DNI para acceder al perfil de socio o marque 0 para volver al menú de invitado");
			id = br.readLine();
 	   	} while ((!validarDNI(id)) || (id != "0"));
 	   
 	    if (id == "0") {
 		   imprimeMenu();
 	    }
 	   
 	    Socio socio;
 	    socio = socios.obtener(id);
 	   
 	    if (socio == null) {
 		   System.out.println("No existe un socio con el dni: "+id+". ¿Desea crear uno nuevo?");
 		   String respuesta;
 		
 		   respuesta = br.readLine();
			   
 		   if (respuesta.equalsIgnoreCase("s")) {
//comprobar si TODO OK
 			   socio = new FormDatosSocio(id).imprimeFormulario();// Hay que ver cómo funciona
 			   socios.crearNuevo(socio);
 			   cierraConexionMySQL(mysqlF);
				} else {
					imprimeMenu();
					}       		
			}
 	   
 	   if (mysqlF != null) {
 		cierraConexionMySQL(mysqlF);
 	   }
 	
 	   new MenuSocio(socio, this);
	}
	
	private static void cierraConexionMySQL(MySQLDAOFactory mysqlf) throws DAOException {
		try {
			mysqlf.cerrar();
		} catch (SQLException e) {
			throw new DAOException("Error al intentar cerrar la base de datos", e);
			}
	}
	
	private static boolean validarDNI(String dni) {
		final String dniRegexp = "\\d{8}[A-HJ-NP-TV-Z]";
		System.out.println("El DNI introducido no cumple con los requisitos de formato.");
		return (!Pattern.matches(dniRegexp, dni));
	}	
}
