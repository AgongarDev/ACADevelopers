package acadevs.entreculturas.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import acadevs.entreculturas.modelo.AdministracionFisica;
import acadevs.entreculturas.modelo.ViewException;
import acadevs.entreculturas.util.Utilidad;

public class FormDatosAdministracion {

	public FormDatosAdministracion(String nombre) throws ViewException {
		imprimeMenu(nombre);
	}
	
	public AdministracionFisica imprimeMenu(String nombre) throws ViewException {
		
		Utilidad.limpiaPantalla();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		AdministracionFisica sede = new AdministracionFisica();
		
		System.out.println("\n**************************************************************************");
		System.out.println("              Formulario de datos de ADMINISTRACION FISICA");
		System.out.println("\n**************************************************************************");
		
		System.out.println("\nDirección: ");
		try {
			sede.setDireccion(br.readLine());
		} catch (IOException e) {
			throw new ViewException ("Hubo un problema en la asignación de la dirección", e);
		}
	
		System.out.println("\nNúmero de Empleados: ");
		try {
			sede.setNumEmpleados(br.read());
		} catch (IOException e) {
			throw new ViewException ("Hubo un problema en la asignación del número de empleados", e);
		}
	
		System.out.println("\nEmail Contacto Sede: ");
		try {
			sede.setCorreo(br.readLine());
		} catch (IOException e) {
			throw new ViewException ("Hubo un problema en la asignación del email", e);
		}
		
		String tlf;
			do {
				System.out.println("\nTeléfono: ");
			    try {
					tlf = br.readLine();
				} catch (IOException e) {
					throw new ViewException ("Hubo un problema en la asignación del teléfono", e);
				}
	        } while ((!Utilidad.validarNumeroTelefono(tlf)) && (tlf.isEmpty()));
			if (tlf.isEmpty()) { 
				tlf = "0"; 
			}
		return sede;
	}		
			
}
