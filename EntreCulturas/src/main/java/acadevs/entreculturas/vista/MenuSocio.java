package acadevs.entreculturas.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLSocioDAO;
import acadevs.entreculturas.modelo.Socio;
import acadevs.entreculturas.modelo.ViewException;

public class MenuSocio {
		 
	private MenuInvitado salirAMenu;
	private Socio socio;
	public MenuSocio (Socio socioONG, MenuInvitado menuInv) throws ViewException {
		
		this.socio = socioONG;
		this.salirAMenu = menuInv;
		
			try {
				imprimeMenu();
			} catch ( DAOException | ParseException | IOException e) {
				e.printStackTrace();
				throw new ViewException("Hubo un problema en el menú de socio", e) ;
		}
	}
	
	public void imprimeMenu() throws IOException, ViewException, DAOException, ParseException {
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

	  	int respuestaOpcion = 0;
		Integer[] opcionesValidas = {0, 1, 2};
		
		System.out.println("\n**********************");
		System.out.println(" Opciones de socio");
		System.out.println("**********************");
		do {
			System.out.println("\nPor favor, introduce el número de la acción que deseas realizar: ");
			System.out.println("1 - Modificar los datos de socio");
			System.out.println("2 - Ver proyectos de la ONG ( -- No implementado --");
			System.out.println("0 - Cerrar Sesión");
			try {
				respuestaOpcion = Integer.parseInt(br.readLine());
			} catch(NumberFormatException nfe) {
				System.out.println("Los caracteres introducidos no son válidos.");
			}
			
		} while (!Arrays.asList(opcionesValidas).contains(respuestaOpcion));
		
		switch(respuestaOpcion) {
			case 1:
				MySQLDAOFactory mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL"); 
				MySQLSocioDAO socios = mysqlF.getSocioDAO();
				
				socio = new FormDatosSocio(socio.getDni()).imprimeFormulario();
				socios.actualizar(socio);
				
				cierraConexionMySQL(mysqlF);
				imprimeMenu();
				break;
			
			case 2:
				System.out.println("Ahora se listarían los proyectos de la ONG");
				imprimeMenu();
			case 3:
				System.out.println("La sesión se ha cerrado con éxito.");
				salirAMenu.imprimeMenu();
			break;
		}

	}
	
	public void cierraConexionMySQL(MySQLDAOFactory mysqlf) throws DAOException {
		try {
	 		   mysqlf.cerrar();
	 	   } catch (SQLException e) {
	 		   throw new DAOException("Error al intentar cerrar la conexión a la base de datos", e);
	 	   }
	}
}