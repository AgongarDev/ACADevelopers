package acadevs.entreculturas.vista.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Arrays;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLSocioDAO;
import acadevs.entreculturas.modelo.ListadoProyectos;
import acadevs.entreculturas.modelo.Socio;
import acadevs.entreculturas.modelo.ViewException;
import acadevs.entreculturas.util.Utilidad;
import acadevs.entreculturas.vista.javafx.App;

public class MenuPrincipal {
	
	private final String NL = System.getProperty("line.separator"); // separador de línea multiplataforma
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	private String pw = "111";
	private MySQLDAOFactory mysqlF;
	
	public MenuPrincipal() throws ViewException {

		try {
			mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL");
			imprimeMenu();
		} catch (IOException | DAOException | ViewException | ParseException e) {
			e.printStackTrace();
			throw new ViewException ("Hubo un problema en el menú de usuario invitado", e);
		}
	}
	
	public void imprimeMenu() throws IOException, DAOException, ViewException, ParseException{
		
		Utilidad.limpiaPantalla();
		
		String id = "";
		
//MENU DE INTERACCIÓN
		int opcion = 0;
		Integer[] opcionesValidas = {0, 1, 2, 3};
		
		System.out.println("\n**************************************************************************");
		System.out.println("                              Menú principal");
		System.out.println("\n**************************************************************************");	
    	
        do {
        	System.out.println(NL+"¿Qué desea hacer?: ");
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
        			System.out.println("Introduzca la contraseña de administrador (PW = 111) o marque 0 para volver al menú de invitado:");
					id = br.readLine();
        		} while ((!id.equals(pw)) && (!id.equals("0")));
					if (id.equals("0")) {
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
           case 0:
        	  System.out.println("...La sesión se ha cerrado con �xito.");
        	  App.salirDelPrograma();
        	  Utilidad.limpiaPantalla();
              break;
        }
	}
	
	private void abrirSesionSocio(String id) throws IOException, DAOException, ViewException, ParseException {
		 
		MySQLSocioDAO socios = mysqlF.getSocioDAO();
 	   
 	   	do {
 	   		System.out.println("Introduzca DNI para acceder al perfil de socio o marque 0 para volver al menú de invitado");
			id = br.readLine();
 	   	} while ((!Utilidad.validarNIF(id)) && (!id.equals("0")));
 	   
 	    if (id.equals("0")) {
 		   imprimeMenu();
 	    }
 	   
 	    Socio socio = socios.obtener(id);
 	   
 	    if (socio == null) {
 	    	System.out.println("El socio con DNI "+id+" no existe en la base de datos.");
 		    System.out.println("¿Desea crear uno nuevo con este DNI?");
 		    String respuesta = br.readLine();
			   
 		    if (respuesta.equalsIgnoreCase("s")) {
 		    	socio = new Socio();
 		    	socio = new FormDatosSocio(socio, true).imprimeFormulario();// true : alta - se abre el formulario de datos de socio completo
 		    	socios.crearNuevo(socio);
 		    	new MenuSocio(socio, this);
			} else {
				imprimeMenu();
				}       		
 	    } else { 
 	    	new MenuSocio(socio, this);
 	    }
	}
}
