package acadevs.entreculturas.vista;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLSocioDAO;
import acadevs.entreculturas.dao.xml.XMLDAOFactory;
import acadevs.entreculturas.dao.xml.XMLSocioDAO;
import acadevs.entreculturas.modelo.ListadoSocios;
import acadevs.entreculturas.modelo.Socio;
import acadevs.entreculturas.modelo.ViewException;

public class MenuAdministrador {
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private List<Socio> listaSocios;
	private ListadoSocios Lsocios;
	
	public MenuAdministrador() {
		
		this.Lsocios = new ListadoSocios();
		
		try {
			imprimeMenu();
		} catch (DAOException | ViewException | IOException | ParseException e) {
			e.printStackTrace();
			throw new ViewException ("Hubo un erro en el menú administrador", e);
		}
	}
	
	public void imprimeMenu () throws DAOException, ViewException, IOException, ParseException {
	
		int respuestaOpcion = 0;
		Integer[] opcionesValidas = {1, 2, 3, 4, 5, 0};
		
    	System.out.println("\n***************************");
    	System.out.println(" Opciones de administrador");
    	System.out.println("***************************");
    	
        do {
        	System.out.println("\nPor favor, introduce el número de la acción que deseas realizar: ");
        	System.out.println("1 - Dar de alta un socio");
        	System.out.println("2 - Actualizar datos de un socio");
        	System.out.println("3 - Crear un proyecto");
        	System.out.println("4 - Listar trabajadores");
        	System.out.println("5 - Listar socios");
        	System.out.println("0 - Salir");
        	
        	try {
        		respuestaOpcion = Integer.parseInt(br.readLine());
            } catch(NumberFormatException nfe) {
                System.out.println("Los caracteres introducidos no son válidos.");
            	}
        	
        } while (!Arrays.asList(opcionesValidas).contains(respuestaOpcion));
        
        switch(respuestaOpcion) {
        
           case 1:
        	   altaSocio();      	  
        	   imprimeMenu();
           case 2:
        	   actualizarSocio();
        	   imprimeMenu();
           case 3:   		  
        	   altaProyecto();
        	   imprimeMenu();
           case 4:
        	   System.out.println("-- NO IMPLEMENTADO -- \n Ha obtenido la lista de trabajadores");
        	   imprimeMenu();
               break;
           
           case 5:
        	   seleccionarSalida();
        	   imprimeMenu();
           case 0:
        	   System.out.println("La sesión se ha cerrado con éxito.");
        	   Application.salirDelPrograma();
               break;
        }
    
	}
	
	public void seleccionarSalida() throws DAOException, ViewException, IOException, ParseException {
		
		int opcion = 0;
		
		do {
			System.out.println("Seleccione la salida del listado");
			System.out.println("1 - En Consola");
			System.out.println("2 - En Archivo XML");
			try {
			opcion = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.out.println("Solo se permiten valores numéricos");
				seleccionarSalida();
			}
		} while ((opcion < 0) || (opcion > 2));
		
		if (opcion == 1) {
			imprimeSociosPantalla();
		} else if (opcion == 2) {
			imprimirSociosXML();
		}
		

	}
	
	public List<Socio> obtenerSociosMySQL() throws DAOException {
		
		MySQLDAOFactory mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL"); 
		MySQLSocioDAO socios = mysqlF.getSocioDAO();

		List<Socio> listado = socios.obtenerTodos();
		cierraConexionMySQL(mysqlF);
		
		return listado;
		
	}
	
	public void imprimirSociosXML() throws DAOException, IOException, ViewException, ParseException {
		
		List<Socio> listado = obtenerSociosMySQL();
		this.Lsocios.setSocios(listado);
		
		XMLDAOFactory xmlF = (XMLDAOFactory) DAOFactory.getDAOFactory("XML"); 
		XMLSocioDAO sociosXML = xmlF.getSocioDAO();
		sociosXML.setListadoSocios(Lsocios);
		
		System.out.print("Escriba la ruta en la que quiere guardar el archivo xml o Marque 0 para cancelar");
		String ruta = br.readLine(); 
		
		if (ruta != "0") {
			sociosXML.imprimirTodos(ruta);
		}

	}
	
	public void importarSociosXML() throws DAOException {
		
		XMLDAOFactory xmlF = (XMLDAOFactory) DAOFactory.getDAOFactory("XML"); 
		XMLSocioDAO sociosXML = xmlF.getSocioDAO();
		
		List <Socio> listaSocios = sociosXML.obtenerTodos();
		int updates = 0;
		
		MySQLDAOFactory mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL"); 
		MySQLSocioDAO sociosMySQL = mysqlF.getSocioDAO();
		
		for (Socio s: listaSocios) {
			if (subirSocio(s, sociosMySQL)) {
				updates = updates++;
			};
		}
		
		cierraConexionMySQL(mysqlF);
	}
	
	public boolean subirSocio(Socio s, MySQLSocioDAO socios) throws DAOException {
				
		String id = s.getDni();
		Socio socio = socios.obtener(id);
		
		if (socio == null) {
			socios.crearNuevo(socio);
			return true;
		} 
		
		return false;
	}
	
	public void imprimeSociosPantalla() throws DAOException {
		
		List<Socio> listado = obtenerSociosMySQL();
		if (listado.size() != 0) {
			for (Socio elem : listado) {
				elem.toString();
			}
		}
	}
	
	public void altaProyecto () throws DAOException, ViewException, IOException, ParseException {

		//NO IMPLEMENTADO
		System.out.println("-- NO IMPLEMENTADO -- \nEnhorabuena! ha creado un proyecto nuevo");
		imprimeMenu();
		/*
		String id;
		MySQLDAOFactory mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL"); 
		MySQLSocioDAO proyectos = mysqlF.getProyectoDAO();
 	   
 	   	do {
 	   		System.out.println("Introduzca el código identificador del proyecto o Marque 0 para volver al menú de administrador");
			id = br.readLine();
 	   	} while (id != "0");
 	   
 	    if (id == "0") {
 		   imprimeMenu();
 	    }
 	   
 	    Proyecto proyecto = proyectos.obtener(id);
 	   
 	    if (proyecto == null) {
 	    	proyecto = new FormDatosProyecto(id).imprimeFormulario();// Hay que ver cómo funciona
 			proyectos.crearNuevo(proyecto);
 			cierraConexionMySQL(mysqlF);
 	    } else {
 	    	cierraConexionMySQL(mysqlF);
			System.out.println("El código de proyecto "+id+" ya está registrado en la base de datos.");
			altaProyecto();
 	    	}       		
 	   
 	    if (mysqlF != null) {
 	    	cierraConexionMySQL(mysqlF);
 	    }
		 */
	}

	public void actualizarSocio() throws IOException, DAOException, ViewException, ParseException {
		
		String id;
		MySQLDAOFactory mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL"); 
		MySQLSocioDAO socios = mysqlF.getSocioDAO();
 	   
 	   	do {
 	   		System.out.println("Introduzca DNI para acceder al perfil de socio o marque 0 para volver al menú de invitado");
			id = br.readLine();
 	   	} while ((!Application.validarNIF(id)) || (id != "0"));
 	   
 	    if (id != "0") {
 		    Socio socio;
 		    socio = socios.obtener(id);
 	   
 		    if (socio == null) {
 		    	System.out.println("No existe un socio con el dni: "+id);
 		    } else {
 		    	socio = new FormDatosSocio(id).imprimeFormulario();
 		    	socios.actualizar(socio);
 		    	}

 		    cierraConexionMySQL(mysqlF);
 	    }
	}
	public void altaSocio () throws DAOException, ViewException, IOException, ParseException {

		String id;
		MySQLDAOFactory mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL"); 
		MySQLSocioDAO socios = mysqlF.getSocioDAO();
 	   
 	   	do {
 	   		System.out.println("Introduzca el DNI del socio o marque 0 para volver al menú de administrador");
			id = br.readLine();
 	   	} while ((!Application.validarNIF(id)) || (id != "0"));
 	   
 	    if (id != "0") {
	 	    Socio socio = socios.obtener(id);
	 	   
	 	    if (socio == null) {
	 	    	socio = new FormDatosSocio(id).imprimeFormulario();// Hay que ver cómo funciona
	 			socios.crearNuevo(socio);
	 			cierraConexionMySQL(mysqlF);
	 	    } else {
	 	    	cierraConexionMySQL(mysqlF);
				System.out.println("El DNI "+id+" ya está registrado en la base de datos.");
				altaSocio();
	 	    	}       		
	 	   
	 	    if (mysqlF != null) {
	 	    	cierraConexionMySQL(mysqlF);
	 	    }
 	    }
	}
	
	private static void cierraConexionMySQL(MySQLDAOFactory mysqlf) throws DAOException {
		try {
			mysqlf.cerrar();
		} catch (SQLException e) {
			throw new DAOException("Error al intentar cerrar la base de datos", e);
			}
	}
	
}
