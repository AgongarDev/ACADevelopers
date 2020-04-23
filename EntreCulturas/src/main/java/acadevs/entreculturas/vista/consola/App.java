package acadevs.entreculturas.vista.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.modelo.ViewException;
import acadevs.entreculturas.util.Utilidad;
import acadevs.entreculturas.vista.javafx.HelloFX;

/**
 * 
 *Clase que lanza la aplicación
 *
 */
public class App {
	
	public MySQLDAOFactory accesoMySQL;
	
	public static void main(String [] args) throws ViewException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
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
		
		if (opcion == 1) {
			HelloFX.ver();
		} else if (opcion == 2) {
			new MenuPrincipal();
		} else {
			salirDelPrograma();
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
		
		for (int i = 1 ; i < 80 ; i++) {
			
			try {
				 System.out.print("*");
			     Thread.currentThread();
				Thread.sleep(30);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			    throw new ViewException("Error al intentar parar el proceso actual en el menú start", e);
			}
		}
	}
	
}

