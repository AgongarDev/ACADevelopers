package acadevs.entreculturas.vista.javafx;

import java.io.File;
import java.net.MalformedURLException;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.modelo.HibernateUtil;
import acadevs.entreculturas.modelo.ViewException;
import acadevs.entreculturas.util.Utilidad;
import acadevs.entreculturas.vista.consola.MenuPrincipal;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Ana Iglesias
 * @author Antonio González
 */
/*
 *Clase que lanza la aplicación. Extiende de Application necesariamente para poder utilizar las propiedades y métodos de JavaFX.
 */
public class App extends Application{
	
	public MySQLDAOFactory accesoMySQL;
	
	public static void main(String [] args) throws ViewException {
	
		int opcion = 0;
		
		Utilidad.cargaConfiguracion();
		Utilidad.conectarMySQL("MySQL");
		HibernateUtil.crearSessionFactory();
		
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
		
		opcion = 1; // Inicio automático de la aplicación gráfica
		
		if (opcion == 1) {
			launch(args);
		} else if (opcion == 2) {
			new MenuPrincipal();
		} else {
			salirDelPrograma();
		}
		
	}
	
	/*
	 * En la función start incluimos todos los objetos relacionados con la interfaz gráfica.
	 * La clase Home es la interfaz gráfica de la pantalla de inicio.
	 * Creamos una escena necesariamente para poder introducir los items en ella y asignamos la escena al stage o ventana.
	 * La función asParent nos permite llamar a la escena del objeto home. De esta manera conseguimos aplicar el MVC.
	 * Para asignar 
	 * */
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
		
		//cerramos la conexión con JPA 
		HibernateUtil.cerrarSessionFactory();
		
		//cerramos la conexión con jdbc de los DAO
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

