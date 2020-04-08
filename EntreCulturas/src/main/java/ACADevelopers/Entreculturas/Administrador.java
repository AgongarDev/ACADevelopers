package ACADevelopers.Entreculturas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;


/**
 * Esta clase representa a la persona encargada de crear los proyectos, modificarlos,
 * asignar el personal y gestionar la financiación de la ONG.
 * 
 * @author Cristina, Antonio y Ana.
 * @version 1.0
 *
 */
public class Administrador extends Persona implements Usuario {
	
	// CAMPOS
	
	private String rootPass;
	private DAOFactory xmlDAOFactory = DAOFactory.getDAOFactory(DAOFactory.XML);
	private DAO<Socio> socioDAO = (XMLSocioDAO) xmlDAOFactory.getSocioDAO();
	private DAO<Proyecto> proyectoDAO = (XMLProyectoDAO) xmlDAOFactory.getProyectoDAO();
	
	
	// CONSTRUCTORES
	
	/**
	 * Constructor que crea un nuevo objeto Admin sin inicializar sus campos.
	 * 
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
	public Administrador() throws JAXBException {
		super();
	}


	/**
	 * Constructor que crea un nuevo objeto Administrador inicializando sus campos.
	 * 
	 * @param nombre Atributo que guarda el nombre de la persona.
	 * @param apellidos Atributo que guarda los apellidos de la persona.
	 * @param id Atributo que guarda el id de la persona.
	 * @param email Atributo que guarda el email de la persona.
	 * @param telefono Atributo que guarda el telefono de la persona.
	 * @param direccion Atributo que guarda la direccion de la persona.
	 * @param delegacionAsignada Atributo que guarda el nombre de la administracion física asignada a la persona.
	 * @param antiguedad Atributo que guarda la antiguedad en la ong de la persona.
	 * @param proyectosAsignados Atributo que guarda los proyectos asignados a la persona.
	 * @param horarioLaboral Atributo que guarda el horario laboral de la persona.
	 * @param pass Atributo que guarda la contraseña de loggin de la persona.
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
	public Administrador(String dni, String nombre, String apellidos, String telefono,
		       String domicilio, String fechaInicio,String fechaFin, AdministracionFisica sedeAsignada, 
		       String cargo, String correo, Ong ong, String pass) throws JAXBException {
		super(dni, nombre, apellidos, telefono, domicilio, fechaInicio, fechaFin, sedeAsignada, cargo, correo);
		
		this.rootPass = pass;
		}
	// METODOS
	
	/**
	 * Metodo accesor de lectura que nos da la password del admin.
	 * 
	 * @return Nos devuelve la password del admin. 
	 */
	public String getRootPass() {
		return rootPass;
	}

	/**
	 * Metodo accesor de escritura que asigna la password del admin.
	 * 
	 * @param rootPass password del admin. 
	 */
	public void setRootPass(String rootPass) {
		this.rootPass = rootPass;
	}

	/**
	 * Metodo que genera el menu de acciones que puede realizar el admin
	 * en la aplicacion cuando inicia sesion.
	 */
	@Override
	public void abrirSesion() throws IOException, JAXBException {
		    	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int respuestaOpcion = 0;
		Integer[] opcionesValidas = {1, 2, 3, 4, 5};
  	    String respuestaNuevaAccion;
		
    	System.out.println("\n***************************");
    	System.out.println(" Opciones de administrador");
    	System.out.println("***************************");
    	
        do {
        	
        	System.out.println("\nPor favor, introduce el número de la acción que deseas realizar: ");
        	System.out.println("1 - Dar de alta un socio");
        	System.out.println("2 - Crear un proyecto");
        	System.out.println("3 - Modificar un proyecto");
        	System.out.println("4 - Modificar datos de un socio");
        	System.out.println("5 - Salir");
        	
        	try {
        		respuestaOpcion = Integer.parseInt(br.readLine());
            } catch(NumberFormatException nfe) {
                System.out.println("Los caracteres introducidos no son válidos.");
            }
        	
        } while (!Arrays.asList(opcionesValidas).contains(respuestaOpcion));
        
        switch(respuestaOpcion) {
        
           case 1:
        	 /** darAltaSocio();*/

        	  do {
        		  
        		  do {
        			  System.out.println("¿Deseas dar de alta un socio? (S/N)");
            		  respuestaNuevaAccion = br.readLine();
        		  } while (!respuestaNuevaAccion.equalsIgnoreCase("s") && !respuestaNuevaAccion.equalsIgnoreCase("n"));
        		  
        		  if (respuestaNuevaAccion.equalsIgnoreCase("s")) {
        			  darAltaSocio();
      				}
        		  
        	  } while (!respuestaNuevaAccion.equalsIgnoreCase("n"));
        	  
        	  abrirSesion();
        	  
              break;
           
              
           case 2:
         	  /**darAltaProyecto();*/
         	  
        	  do {
        		  
        		  do {
        			  System.out.println("¿Deseas dar de crear un proyecto? (S/N)");
            		  respuestaNuevaAccion = br.readLine();
        		  } while (!respuestaNuevaAccion.equalsIgnoreCase("s") && !respuestaNuevaAccion.equalsIgnoreCase("n"));
        		  
        		  if (respuestaNuevaAccion.equalsIgnoreCase("s")) {
        			  darAltaProyecto();
      				}
        		  
        	  } while (!respuestaNuevaAccion.equalsIgnoreCase("n"));
        	  
        	  abrirSesion();
         	  
               break;
               
           case 3:
        	   imprimirListadoDelegaciones();
        	   abrirSesion();
        	   
               break;
           
           case 4:
        	   imprimirListadoDelegaciones();
        	   abrirSesion();
        	   
               break;
              
           case 5:
        	   System.out.println("La sesión se ha cerrado con éxito.");
        	   System.exit(0);
        	   
               break;
        }
    
	}
	
	/**
	 * Metodo que permite al administrador consultar el listado
	 * de trabajadores de la ONG.
	 * 
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
	
	/**
	 * Metodo que permite al administrador consultar el listado
	 * de delegaciones de la ONG.
	 * 
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
	public void imprimirListadoDelegaciones() throws JAXBException {
		proyectoDAO.obtenerTodos();
	}
	
	/**
	 * Metodo que permite al empleado introducir por consola los
	 * datos de alta de un nuevo trabajador.
	 * 
	 * @throws IOException si se produce un error de entrada/salida.
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
	private void darAltaSocio() throws IOException, JAXBException {
		Socio nuevoSocio = new Socio();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\nIntroduce el nombre del socio: ");
		nuevoSocio.setNombre(br.readLine());
		System.out.println("\nIntroduce los apellidos del socio: ");
		nuevoSocio.setApellidos(br.readLine());
		System.out.println("\nIntroduce el dni del socio: ");
		nuevoSocio.setDni(br.readLine());
		System.out.println("\nIntroduce el email del socio: ");
		nuevoSocio.setCorreo(br.readLine());
		System.out.println("\nIntroduce el teléfono del socio: ");
        try {
        	String numero = br.readLine();
        	validarNumeroTelefono(numero);
        	nuevoSocio.setTelefono(numero);
        } catch (TelefonoNoValidoException e) {
        	System.out.println("Número no válido, podrá modificarlo más adelante"); 
        	nuevoSocio.setTelefono("000000000");
        }
        System.out.println("\nIntroduce fecha de inscripción del socio: ");
		nuevoSocio.setFechaInicio(br.readLine());
		System.out.println("\nIntroduce fecha de fin de inscrpción del socio: ");
		nuevoSocio.setFechaInicio(br.readLine());
		/*
		 * Bloque sede asignada
		 */
		System.out.println("\nIntroduce el id de la sede asignada: ");
		nuevoSocio.sedeAsignada.setIdAdmin(br.readLine());
		System.out.println("\nIntroduce la dirección de la sede asignada: ");
		nuevoSocio.sedeAsignada.setDireccion(br.readLine());
		
		System.out.println("\nIntroduce la numero empleados de la sede asignada: ");
		nuevoSocio.sedeAsignada.setNumEmpleados(br.read());
		
		System.out.println("\nIntroduce e-mail de la sede asignada: ");
		nuevoSocio.sedeAsignada.setCorreo(br.readLine());
		
		System.out.println("\nIntroduce el teléfono de la sede asignada: ");
        try {
        	String numero = br.readLine();
        	validarNumeroTelefono(numero);
    		nuevoSocio.sedeAsignada.setTelefono(numero);
        } catch (TelefonoNoValidoException e) {
        	System.out.println("Número no válido, podrá modificarlo más adelante"); 
    		nuevoSocio.sedeAsignada.setTelefono("000000000");
        }
        /*
		 * Fin Bloque sede asignada
		 */
        System.out.println("\nIntroduce e-mail del socio: ");
		nuevoSocio.setCorreo(br.readLine());
        
		System.out.println("\nIntroduce cargo del socio: ");
		nuevoSocio.setCargo(br.readLine());
        
        /*
      	* Bloque Cuota
      	*/
		System.out.println("\nIntroduce cantidad de aportación del socio: ");
		nuevoSocio.setCuotaAportacion(br.read());
		System.out.println("¿La aportación está activa? (S/N): ");
		if (br.readLine().equalsIgnoreCase("s")) {
			nuevoSocio.estadoAportacion.equals(true);
		} else {
			nuevoSocio.estadoAportacion.equals(false);		
		}
		System.out.println("\nIntroduce el tipo de cuota del socio (M/T/A): ");
		switch (br.readLine()) {
			case "M":
				nuevoSocio.setTipoCuota(TipoCuota.MES);
				break;
	
			case "T":
				nuevoSocio.setTipoCuota(TipoCuota.TRIM);
				break;
	
			case "A":
				nuevoSocio.setTipoCuota(TipoCuota.ANUAL);
				break;
	
			default:
				break;
		}
		Random random = new Random();
		String pass = String.format("%06d", random.nextInt(1000000));
		nuevoSocio.setPass(pass);
		socioDAO.crearNuevo(nuevoSocio);
		
	}
	
	/**
	 * Metodo que permite al empleado introducir por consola los
	 * datos de alta de un nuevo Proyecto.
	 * 
	 * @throws IOException si se produce un error de entrada/salida.
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
	private void darAltaProyecto() throws IOException, JAXBException {
		Proyecto nuevoProyecto = new Proyecto();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\nIntroduce el nombre del proyecto: ");
		nuevoProyecto.setNombreProyecto(br.readLine());
		System.out.println("\nIntroduce el ID del proyecto: ");
		nuevoProyecto.setIdProyecto(br.readLine());
		System.out.println("\nIntroduce la linea de acción del proyecto (C/A/F/E): ");
		switch (br.readLine()) {
			case "C":
				nuevoProyecto.setlAccion(LineaDeAccion.COOP);
				break;
	
			case "A":
				nuevoProyecto.setlAccion(LineaDeAccion.ACC);;
				break;
	
			case "F":
				nuevoProyecto.setlAccion(LineaDeAccion.FORT);
				break;
			case "E":
				nuevoProyecto.setlAccion(LineaDeAccion.EDU);
				break;
	
			default:
				break;
		}
		System.out.println("\nIntroduce fecha de inicio del proyecto: ");
			nuevoProyecto.setFechaInicio(br.readLine());
		System.out.println("\nIntroduce fecha de fin del proyecto: ");
			nuevoProyecto.setFechaInicio(br.readLine());
		System.out.println("\nIntroduce sublínea de acción del proyecto: ");
			nuevoProyecto.setSublineaDeAccion(br.readLine());
		System.out.println("\nIntroduce país del proyecto: ");
			nuevoProyecto.setPais(br.readLine());
		System.out.println("\nIntroduce dirección del proyecto: ");
			nuevoProyecto.setDireccion(br.readLine());
			/*
			 * Bloque socio local del proyecto
			 */
		System.out.println("\nIntroduce el dni del socio local: ");
		    nuevoProyecto.getSocioLocal().setDni(br.readLine());
		System.out.println("\nIntroduce el nombre del socio local: ");
		    nuevoProyecto.getSocioLocal().setNombre(br.readLine());
		System.out.println("\nIntroduce el apellidos del socio local: ");
		
		    nuevoProyecto.getSocioLocal().setApellidos(br.readLine());
		System.out.println("\nIntroduce el apellidos del socio local: ");
		    nuevoProyecto.getSocioLocal().setApellidos(br.readLine());
		System.out.println("\nIntroduce el teléfono del socio locala: ");
	        try {
	        	String numero = br.readLine();
	        	validarNumeroTelefono(numero);
	    		nuevoProyecto.getSocioLocal().setTelefono(numero);
	        } catch (TelefonoNoValidoException e) {
	        	System.out.println("Número no válido, podrá modificarlo más adelante"); 
	        	nuevoProyecto.getSocioLocal().setTelefono("000000000");
	        }
	    System.out.println("\nIntroduce el domicilio del socio local: ");
		    nuevoProyecto.getSocioLocal().setDomicilio(br.readLine());
		System.out.println("\nIntroduce el fecha inicio de colaboración del socio local: ");
		    nuevoProyecto.getSocioLocal().setFechaInicio(br.readLine());
		System.out.println("\nIntroduce el fecha en la que la persona finaliza su colaboraci�n del socio local: ");
		    nuevoProyecto.getSocioLocal().setFechaFin(br.readLine());
			/*
			 * Bloque sede asignada del socio local
			 */
			System.out.println("\nIntroduce el id de la sede asignada: ");
			 nuevoProyecto.getSocioLocal().sedeAsignada.setIdAdmin(br.readLine());
			System.out.println("\nIntroduce la dirección de la sede asignada: ");
			nuevoProyecto.getSocioLocal().sedeAsignada.setDireccion(br.readLine());
			
			System.out.println("\nIntroduce la dirección de la sede asignada: ");
			nuevoProyecto.getSocioLocal().sedeAsignada.setNumEmpleados(br.read());
			
			System.out.println("\nIntroduce e-mail de la sede asignada: ");
			nuevoProyecto.getSocioLocal().sedeAsignada.setCorreo(br.readLine());
			
			System.out.println("\nIntroduce el teléfono de la sede asignada: ");
	        try {
	        	String numero = br.readLine();
	        	validarNumeroTelefono(numero);
	        	nuevoProyecto.getSocioLocal().sedeAsignada.setTelefono(numero);
	        } catch (TelefonoNoValidoException e) {
	        	System.out.println("Número no válido, podrá modificarlo más adelante"); 
	        	nuevoProyecto.getSocioLocal().sedeAsignada.setTelefono("000000000");
	        }
	        /*
			 * Fin Bloque sede asignada
			 */
		System.out.println("\nIntroduce el cargo del socio local: ");
		    nuevoProyecto.getSocioLocal().setCargo(br.readLine());
		System.out.println("\nIntroduce el correo del socio local: ");
		    nuevoProyecto.getSocioLocal().setCorreo(br.readLine());
		proyectoDAO.crearNuevo(nuevoProyecto);
		/****
		 * Fin Bloque socio local del proyecto
		 ****/
		/****
		 *Inicio Bloque aportador del proyecto
		 ****/
		System.out.println("\nIntroduce el dni del aportador del proyecto: ");
	    nuevoProyecto.getFinanciador().setIdAportador(br.readLine());
		System.out.println("\nIntroduce el tipo de aportador (I/P/E/H): ");
		switch (br.readLine()) {
			case "I":
				nuevoProyecto.getFinanciador().setTipoAportador(TipoAportador.INST);
				break;
	
			case "P":
				nuevoProyecto.getFinanciador().setTipoAportador(TipoAportador.PART);
				break;
	
			case "E":
				nuevoProyecto.getFinanciador().setTipoAportador(TipoAportador.EMPR);
				break;
			case "H":
				nuevoProyecto.getFinanciador().setTipoAportador(TipoAportador.HEREN);
				break;
	
			default:
				break;
		}
	}
	
	/**
	 * Metodo que valida si el numero de telefono introducido es correcto.
	 * 
	 * @param numero Numero de telefono introducido.
	 */
	private void validarNumeroTelefono(String numero) {
		final String regexStr = "^(\\+34|0034|34)?[6789]\\d{8}$";
		if (!Pattern.matches(regexStr, numero)) {
			throw new TelefonoNoValidoException(numero);
		}
	}


	@Override
	public void consultarProyectos(ArrayList<Proyecto> lp) {
		
	}


	@Override
	public void consultarSocios(ArrayList<Socio> ls) {
		
	}

}
