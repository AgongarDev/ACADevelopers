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
	
	public static void main(String [] args) throws ViewException {
	
		Ong ong = new Ong("EntreC", "Entre Culturas", 100000);
		
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
		new MenuInvitado ();
	}
	
	public static void tiempoDeCreditos(int seg) throws ViewException {
			
		for (int i = 1 ; i < seg ; i++) {
			try {
				 System.out.print("*");
			     Thread.currentThread();
				Thread.sleep(100);
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
		System.out.println("****************************************************************************");
		System.out.println("                              MUCHAS GRACIAS                                ");
		System.out.println("****************************************************************************");
		System.out.println("                                                                @ Abril 2020");
		tiempoDeCreditos(40);
	}

}

