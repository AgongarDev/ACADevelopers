package acadevs.entreculturas.vista;

import java.io.IOException;

import acadevs.entreculturas.modelo.Ong;
import acadevs.entreculturas.modelo.ViewException;

/**
 * 
 *Clase que lanza la aplicación
 *
 */
public class Application {
	
	public Ong ong = new Ong("EntreC", "Entre Culturas", 100000);

	public static void main() throws ViewException {
	
		System.out.println("\n**************************************************************************");
		System.out.println("                     Aplicación de gestión de una ONG");
		System.out.println("****************************************************************************");
		System.out.println("Producto 3 (P5) Programación Orientada a Objetos con acceso a Bases de Datos");
		System.out.println("****************************************************************************");
		System.out.println("Grupo ACADevelopers : Ana Iglesias - < anaigsan@uoc.edu >");
		System.out.println("                    : Antonio González - < agonzalezgarcia10@uoc.edu >");
		System.out.println("****************************************************************************");
		System.out.println("                                                  Consultor: Josep Vaño Chic");
		
		/*-Pendiente implementar una abstract factory para ver la aplicación en modo consola o en modo gráfico.
		 * 	-Aquí vendría la llamada a un método menú de selección que permita que el usuario decida el modo de visualización de la aplicación.
		 *-Pendiente implementar un método que tome el texto de un archivo .txt en el que expliquemos el proyecto y lo muestre en consola.
		 */
		tiempoDeCreditos(40);
		MenuInvitado menu = new MenuInvitado ();
	}
	
	public static void tiempoDeCreditos(int seg) throws ViewException {
			
		for (int i = 1 ; i < seg ; i++) {
			try {
				 System.out.print("*");
			     Thread.currentThread().sleep(250);
			       }
			     catch (InterruptedException e) {
			       e.printStackTrace();
			       throw new ViewException("Error al intentar parar el proceso actual en el menú start", e);
			       }
		}
	}
	
	public static void limpiaPantalla() {
	
		try {
			Runtime.getRuntime().exec("cls");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ViewException("Error al intentar limpiar la pantalla en el menú start", e);
		}	
	}
	
	public static void salirDelPrograma() {
		limpiaPantalla();
		System.out.println("****************************************************************************");
		System.out.println("                              MUCHAS GRACIAS                                ");
		System.out.println("****************************************************************************");
		System.out.println("                                                                @ Abril 2020");
		tiempoDeCreditos(40);
	}
}

