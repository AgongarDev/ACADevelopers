package acadevs.entreculturas.vista.javafx;

import java.io.File;
import java.net.MalformedURLException;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.modelo.ViewException;
import acadevs.entreculturas.util.Utilidad;
import acadevs.entreculturas.vista.consola.MenuPrincipal;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 *Clase que lanza la aplicación
 *
 */
public class App extends Application{
	
	public MySQLDAOFactory accesoMySQL;
	
	public static void main(String [] args) throws ViewException {
	
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int opcion = 0;
		
		Utilidad.cargaConfiguracion();
		Utilidad.conectarMySQL("MySQL");
		
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
		/*
		Integer[] opciones = new Integer[] {0, 1, 2};
		do {		
			System.out.println(" 1 - MODO GRÁFICO");
			System.out.println(" 2 - EN CONSOLA");
			System.out.println("\n 0 - SALIR DE LA APLICACIÓN");
			
			System.out.println("\n\n  Seleccione cómo desea continuar con la aplicación");
				
			try {
				opcion = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.out.println("Por favor, introduzca un valor entre 0 y 2");
			}
				
		} while (!Arrays.asList(opciones).contains(opcion));
		
		 */
		opcion = 1; // Inicio automático de la aplicación gráfica
		
		if (opcion == 1) {
			launch(args);
		} else if (opcion == 2) {
			new MenuPrincipal();
		} else {
			salirDelPrograma();
		}
		
	}
	
	@Override
    public void start(Stage primaryStage) {
		
        Home menu = new Home();
       
        Scene scene = new Scene(menu.asParent(), 1080, 600);
        
        String fontSheet = fileToStylesheetString( new File ("src/main/java/acadevs/entreculturas/recursos/css/menu-principal.css"));
		
		if ( fontSheet == null ) {
		    System.out.println("Error en la carga de estilos");
		} else {
		    scene.getStylesheets().add( fontSheet );
		}
		
		primaryStage.setTitle("Aplicación de gestión para EntreCulturas ONG -By ACADevelopers -");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	public String fileToStylesheetString ( File stylesheetFile ) {
	    try {
	        return stylesheetFile.toURI().toURL().toString();
	    } catch ( MalformedURLException e ) {
	        return null;
	    }
	}
	
	public static void salirDelPrograma() {
		
		try {
			Utilidad.cierraConexionMySQL();
		} catch (DAOException e) {
			System.out.println("No se pudo cerrar la conexión a la base de datos");			
			}
		
		Utilidad.limpiaPantalla();
		
		System.out.println("*****************************************************************************");
		System.out.println("*                              MUCHAS GRACIAS                               *");
		System.out.println("*****************************************************************************");
		System.out.println("                                                                 @ Abril 2020");
		
		/*for (int i = 1 ; i < 80 ; i++) {
			
			try {
				 System.out.print("*");
			     Thread.currentThread();
				Thread.sleep(5);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			    throw new ViewException("Error al intentar parar el proceso actual en el menú start", e);
			}
		}*/
		Platform.exit();
	}
	
}

