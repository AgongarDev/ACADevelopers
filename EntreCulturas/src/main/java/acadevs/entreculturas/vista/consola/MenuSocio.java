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
import acadevs.entreculturas.modelo.Socio;
import acadevs.entreculturas.modelo.ViewException;

public class MenuSocio {
		 
	private MenuPrincipal salirAMenu;
	private Socio socio;
	private MySQLDAOFactory mysqlF;
	
	public MenuSocio (Socio socioONG, MenuPrincipal menuInv) throws ViewException {
		
		this.socio = socioONG;
		this.salirAMenu = menuInv;
		
			try {
				mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL"); 
				imprimeMenu();
			} catch ( DAOException | ParseException | IOException e) {
				e.printStackTrace();
				throw new ViewException("Hubo un problema en el menú de socio", e) ;
		}
	}
	
	public void imprimeMenu() throws IOException, ViewException, DAOException, ParseException {
			
		
		MySQLSocioDAO socios = mysqlF.getSocioDAO();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

	  	int respuestaOpcion = 0;
		Integer[] opcionesValidas = {0, 1, 2};
		
		System.out.println("\n**************************************************************************");
		System.out.println("                           Opciones de Socio");
		System.out.println("\n**************************************************************************");	
		do {
			System.out.println("\nPor favor, introduce el número de la acción que deseas realizar: ");
			System.out.println("1 - MODIFICAR los DATOS ESPECÍFICOS de socio");
			System.out.println("2 - MODIFICAR TODOS LOS DATOS del socio");
			System.out.println("3 - Ver proyectos de la ONG ( -- No implementado -- )");
			System.out.println("0 - Cerrar Sesión");
			try {
				respuestaOpcion = Integer.parseInt(br.readLine());
			} catch(NumberFormatException nfe) {
				System.out.println("Los caracteres introducidos no son válidos.");
			}
			
		} while (!Arrays.asList(opcionesValidas).contains(respuestaOpcion));
		
		switch(respuestaOpcion) {
			case 1:
				socio = new FormDatosSocio(socio, false).especificosSocio(socio); // false : actualización - se abre el formulario de socio sólo con las opciones específicas.
				socios.datosEspecificosSocio(socio, "UPDATE_SOCIO");
				imprimeMenu();
				break;
				
			case 2:
				socio = new FormDatosSocio(socio, false).imprimeFormulario(); // false : actualización - se abre el formulario de socio completo. Igual que en un alta de socio.
				socios.actualizar(socio);
				imprimeMenu();
				break;
				
			case 3:
				System.out.println("Ahora se listarían los proyectos de la ONG");
				imprimeMenu();
				break;
				
			case 0:
				System.out.println("La sesión se ha cerrado con éxito.");
				salirAMenu.imprimeMenu();
				break;
		}

	}
	
}