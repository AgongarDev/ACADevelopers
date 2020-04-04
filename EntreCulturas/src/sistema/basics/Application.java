package sistema.basics;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.xml.bind.JAXBException;
/**
 * 
 *Clase que lanza la aplicación
 *
 */
public class Application {
	
	public Ong ong = new Ong("EntreC", "Entre Culturas", 100000);
	
public static void main( String[] args ) throws IOException, JAXBException {
		
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	int respuesta = 0;
    	Integer[] opciones = {1, 2, 3};
    	
    	System.out.println("*****Entreculturas ONG******");
    	System.out.println("****************************");
    	System.out.println("Bienvenido, usuario invitado");
    	System.out.println("****************************");
    	
        do {
        	System.out.println("\n ¿Qué desea hacer?: ");
        	System.out.println("1 - Iniciar sesión como adminitrador");
        	System.out.println("2 - Iniciar sesión como socio");
        	System.out.println("3 - Ver los proyectos publicados");
        	System.out.println("4 - Salir");
        	
        	try {
        		respuesta = Integer.parseInt(br.readLine());
            } catch(NumberFormatException nfe) {
                System.out.println("Error. introduzca un valor del 1 al 4");
            }
        	
        } while (!Arrays.asList(opciones).contains(respuesta));
        
        switch(respuesta) {
           case 1:
        	  Administrador administrador = new Administrador();
           	  administrador.abrirSesion();
              break;
           
           case 2:
        	  Socio socio = new Socio();
        	  socio.abrirSesion();
              break;
              
           case 3:

        	  break;
        	   
           case 4:
        	  System.out.println("La sesión se ha cerrado con éxito.");
        	  System.exit(0);
              break;
        }
    }

}
