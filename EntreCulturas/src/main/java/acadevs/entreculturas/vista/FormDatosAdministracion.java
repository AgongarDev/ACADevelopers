package acadevs.entreculturas.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.regex.Pattern;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLAdministracionFisicaDAO;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.modelo.AdministracionFisica;
import acadevs.entreculturas.modelo.ViewException;

public class FormDatosAdministracion {

	public FormDatosAdministracion(String nombre) throws ViewException {
		imprimeMenu(nombre);
	}
	
	public AdministracionFisica imprimeMenu(String nombre) throws ViewException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		AdministracionFisica sede = new AdministracionFisica();
		
		System.out.println("\n********************************************");
		System.out.println(" Formulario de datos de ADMINISTRACION FISICA");
		System.out.println("*********************************************");
		
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
	        } while ((!validarNumeroTelefono(tlf)) || (tlf != ""));
			if (tlf == "") { 
				tlf = "0"; 
			}
		return sede;
	}		
			
	private boolean validarNumeroTelefono(String numero) {
		final String regexStr = "^(\\+34|0034|34)?[6789]\\d{8}$";
		System.out.println("Formato de telefono incorrecto");
		return (!Pattern.matches(regexStr, numero));
	}
}
