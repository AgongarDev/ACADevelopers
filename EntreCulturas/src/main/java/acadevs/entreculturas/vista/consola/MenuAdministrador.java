package acadevs.entreculturas.vista.consola;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Arrays;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLAdministracionFisicaDAO;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLSocioDAO;
import acadevs.entreculturas.dao.xml.XMLDAOFactory;
import acadevs.entreculturas.dao.xml.XMLSocioDAO;
import acadevs.entreculturas.modelo.AdministracionFisica;
import acadevs.entreculturas.modelo.ListadoSocios;
import acadevs.entreculturas.modelo.Socio;
import acadevs.entreculturas.modelo.ViewException;
import acadevs.entreculturas.util.Config;
import acadevs.entreculturas.util.Utilidad;

public class MenuAdministrador {
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private ListadoSocios Lsocios;
	private String dniSocio;
	
	XMLDAOFactory xmlF; 
	MySQLDAOFactory mysqlF; 
	MySQLSocioDAO socios;
	
	public MenuAdministrador() throws DAOException {
		
		this.xmlF = (XMLDAOFactory) DAOFactory.getDAOFactory("XML"); 
		this.mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL"); 
		this.socios = mysqlF.getSocioDAO();
		
		this.Lsocios = new ListadoSocios();
		
		try {
			imprimeMenu();
		} catch (DAOException | ViewException | IOException | ParseException e) {
			e.printStackTrace();
			throw new ViewException ("Hubo un erro en el menú administrador", e);
		}
	}
	
	public void imprimeMenu () throws DAOException, ViewException, IOException, ParseException {
	
		Utilidad.limpiaPantalla();
		int respuestaOpcion = 0;
		Integer[] opcionesValidas = {1, 2, 3, 4, 5, 6, 7, 8, 0};
		System.out.println("\n**************************************************************************");
		System.out.println("                       Opciones de administrador");
		System.out.println("\n**************************************************************************");		
    	
        do {
        	System.out.println("\nPor favor, introduce el número de la acción que deseas realizar: ");
        	System.out.println("\n1 - Dar de alta un socio");
        	System.out.println("2 - Actualizar datos de un socio");
        	System.out.println("3 - Actualizar datos de la CUOTA de un socio");
        	System.out.println("\n4 - Crear un proyecto");
        	System.out.println("\n5 - Listar trabajadores");
        	System.out.println("6 - Listar socios");
        	System.out.println("\n7 - Crear alta de NUEVA SEDE");
        	System.out.println("\n8 - Importar Socios desde archivo XML");
        	System.out.println("\n0 - Salir de la aplicación");
        	
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
        	   break;
        	   
           case 2:
        	   actualizarSocio(true); //actualizar todo
        	   imprimeMenu();
        	   break;
        	   
           case 3:
        	   actualizarSocio(false); //actualizar solo específicos
        	   imprimeMenu();
        	   break;
        	   
           case 4:   		  
        	   altaProyecto();
        	   imprimeMenu();
        	   break;
        	   
           case 5:
        	   System.out.println("-- NO IMPLEMENTADO -- \n Ha obtenido la lista de trabajadores");
        	   imprimeMenu();
        	   break;
        	   
           case 6:
        	   seleccionarSalida();
        	   imprimeMenu();
        	   break;
          
           case 7:
        	   altaAdministracion();
        	   imprimeMenu();
        	   break;
        	   
           case 8:
        	   importarSociosXML();
        	   imprimeMenu();
        	   break;
        	   
           case 0:
        	   System.out.println("La sesión se ha cerrado con éxito.");
        	   Application.salirDelPrograma();
               break;
        }
	}
	
	public void seleccionarSalida() throws DAOException, ViewException, IOException, ParseException {
		
		List<Socio> listado = obtenerSociosMySQL();
		
		if (listado.size() == 0) {
			System.out.println("No existen socios en la base de datos");
		} else {
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
				imprimeSociosPantalla(listado);
			} else if (opcion == 2) {
				imprimeSociosXML(listado);
			}
		}
	}
	
	public List<Socio> obtenerSociosMySQL() throws DAOException {
		
		List<Socio> listado = socios.obtenerTodos();
		
		return listado;
	}
	
	public void imprimeSociosPantalla(List<Socio> listado) throws DAOException {
		
		if (listado.size() != 0) {
			for (Socio elem : listado) {
				System.out.println(elem.toString()+"\n");
			}
		} else {
			System.out.println("No existen socios en la base de datos");
		}
	}
		
	public void imprimeSociosXML(List<Socio> listado) throws DAOException, IOException, ViewException, ParseException {
		
		if (listado != null) {
			this.Lsocios.setSocios(listado);
		
			XMLSocioDAO sociosXML = xmlF.getSocioDAO();
			sociosXML.setListadoSocios(Lsocios);
		
			System.out.println("Escriba la ruta en la que quiere guardar el archivo xml o Marque 0 para cancelar");
			String ruta = br.readLine(); 
		
			if (!ruta.equals("0"))  {
				sociosXML.imprimirTodos(ruta);
			}
		} else {
			System.out.println("No existen socios en la base de datos");
		}
	}
	
	public void importarSociosXML() throws DAOException {
		
		System.out.println("El directorio del archivo xml establecido es "+Config.rutaXML+". \n ¿Es correcto?");
		try {
			
			if (br.readLine().equalsIgnoreCase("n")) {
				System.out.print("Ruta del archivo de socios a importar: ");
					Config config = new Config();
					config.setRutaXML(br.readLine());	
					Utilidad.grabaConfiguracion(config);
			}
		} catch (IOException e) {
			System.out.println("Error :"+e);
			e.printStackTrace();
			}
		
		XMLSocioDAO sociosXML = xmlF.getSocioDAO();
		AdministracionFisica sedeXml = null, sedeMySQL = null;
		
		List <Socio> listaSocios = sociosXML.obtenerTodos();
		int leidos = 0;
		int importados = 0;
		
		MySQLSocioDAO sociosMySQL = mysqlF.getSocioDAO();
		MySQLAdministracionFisicaDAO administraciones = mysqlF.getAdministracionFisicaDAO();
		
		for (Socio socioXml: listaSocios) {
			sedeXml = socioXml.getSedeAsignada();
			sedeMySQL = administraciones.obtener(sedeXml.getNombre());
			if (sedeMySQL == null) {
				administraciones.crearNuevo(sedeXml);
				sedeMySQL = administraciones.obtener(sedeXml.getNombre());
			}
				socioXml.setSedeAsignada(sedeMySQL);
			
			if (subirSocio(socioXml, sociosMySQL)) {
				importados++;
			};
			leidos++;
		}
		
		System.out.println("Socios leídos: "+leidos+"; Socios importados: "+importados);
	}
	
	public boolean subirSocio(Socio s, MySQLSocioDAO socios) throws DAOException {
				
		String id = s.getDni();
		Socio socio = socios.obtener(id);
		
		if (socio == null) {
			socios.crearNuevo(s);
			return true;
		} 
		return false;
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

	public Socio buscaSocioMySQL() throws IOException, DAOException {
		
		Socio socio = null;
		
		do {
 	   		System.out.println("Introduzca DNI para acceder al perfil de socio o marque 0 para volver al menú de invitado");
			this.dniSocio = br.readLine();
 	   	} while ((!Utilidad.validarNIF(dniSocio)) && (!dniSocio.equals("0")));
 	   
 	    if (!dniSocio.equals("0")) {
 		    socio = socios.obtener(dniSocio);
 	    }
 	    return socio;
	}
	
	public void actualizarSocio(boolean todo) throws IOException, DAOException, ViewException, ParseException {
		
		Socio socioActualizado = null;
 	   
		Socio socio = buscaSocioMySQL();
		
		 if (socio == null) {
 		    	System.out.println("No existe un socio con el dni: "+dniSocio);
 		  } else {
		 
 			  if (todo) {
 				  socioActualizado = new FormDatosSocio(socio, false).imprimeFormulario(); // false : actualizar - pasamos datos socio existente
 				  socios.actualizar(socioActualizado);
 			  } else {
 				  socioActualizado = socio;
 				  socioActualizado = new FormDatosSocio(socio, false).especificosSocio(socio);
 				  socios.datosEspecificosSocio(socioActualizado, "UPDATE_SOCIO");
 			  	}
 	   			System.out.println("Datos del socio "+socioActualizado.getNombre()+" "+socioActualizado.getApellidos()+" actualizados.");
 		  	}
	}
	
	public void altaSocio () throws DAOException, ViewException, IOException, ParseException {

		String id;
		MySQLSocioDAO socios = mysqlF.getSocioDAO();
 	   
 	   	do {
 	   		System.out.println("Introduzca el DNI del socio o marque 0 para volver al menú de administrador");
			id = br.readLine();
 	   	} while ((!Utilidad.validarNIF(id)) && (!id.equals("0")));
 	   
 	    if (!id.equals("0")) {
	 	    Socio socio = socios.obtener(id);
	 	    if (socio == null) {   	
	 	    	socio = new FormDatosSocio(socio, true).imprimeFormulario();// true : alta - pasamos datos socio vacio 
	 			socio.setDni(id);
	 	    	socios.crearNuevo(socio);
	 			System.out.println("Se ha insertado el socio "+socio.getNombre()+" "+socio.getApellidos()+" a la base de datos.");
	 	    } else {
				System.out.println("El DNI "+id+" ya está registrado en la base de datos.");
				altaSocio();
	 	    	}       		
 	    }
	}
	
	public void altaAdministracion () throws DAOException, ViewException, IOException, ParseException {

		String nombreSede;
		AdministracionFisica sede = null;
		
		MySQLAdministracionFisicaDAO administraciones = mysqlF.getAdministracionFisicaDAO();
 	   
 	   	do {
 	   		System.out.println("Introduzca el nombre de la nueva Sede o marque 0 para volver al menú de administrador");
			nombreSede = br.readLine();
			
			if ((!nombreSede.equals("0")) && (nombreSede.isEmpty() == false)) { 
				sede = administraciones.obtener(nombreSede);
	 	   
		 	    if (sede == null) {   	
		 	    	sede = new FormDatosAdministracion(nombreSede).imprimeFormulario();// true : alta - pasamos datos socio vacio 
		 			administraciones.crearNuevo(sede);
		 			System.out.println("Se ha insertado una nueva sede con nombre \""+sede.getNombre()+"\" a la base de datos.");
		 			nombreSede = "";
		 	    } else {
					System.out.println("Ya existe una sede con el nombre "+nombreSede+" en la base de datos.\n");
					altaAdministracion();
		 	    	}
			}
 	   	} while ((!nombreSede.equals("0")) && (nombreSede.isEmpty() == false));
	}
}
