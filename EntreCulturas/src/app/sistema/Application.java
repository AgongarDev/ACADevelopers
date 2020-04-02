package app.sistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.xml.bind.JAXBException;
/**
 * 
 *Clase que lanza la aplicaci�n
 *
 */
public class Application {
	
public static void main( String[] args ) throws IOException, JAXBException {
		
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	int respuesta = 0;
    	Integer[] opciones = {1, 2, 3};
    	
    	System.out.println("Entreculturas ONG");
    	
        do {
        	System.out.println("\n Selecciona una de las siguientes acciones: ");
        	System.out.println("1 - Iniciar sesi�n como adminitrador");
        	System.out.println("2 - Iniciar sesi�n como trabajador");
        	System.out.println("3 - Salir");
        	
        	try {
        		respuesta = Integer.parseInt(br.readLine());
            } catch(NumberFormatException nfe) {
                System.out.println("Error. Int�ntalo otra vez");
            }
        	
        } while (!Arrays.asList(opciones).contains(respuesta));
        
        switch(respuesta) {
           case 1:
        	  Administrador administrador = new Administrador();
           	  administrador.abrirSesion();
              break;
           
           case 2:
        	  Trabajador trabajador = new Trabajador();
        	  trabajador.abrirSesion();
              break;
              
           case 3:
        	  System.out.println("La sesi�n se ha cerrado con �xito.");
        	  System.exit(0);
              break;
        }
    }

}
