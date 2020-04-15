package acadevs.entreculturas.modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import acadevs.entreculturas.dao.DAO;
import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.xml.XMLSocioDAO;
import acadevs.entreculturas.soporte.TipoCuota;

/**
 * Clase que representa cada uno de los socios que forman la ONG
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
@XmlRootElement(name = "Socio")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Socio extends Persona implements Usuario {
    
	// ATRIBUTOS DEL MODELO
	
	private Long id = null;
	
	private String pass;
	
	private float cuotaAportacion;
	
	Boolean estadoAportacion;
	
	private TipoCuota tipoCuota;
	
	
	// CONSTRUCTORES
	
		/**
		 * Constructor que crea un nuevo objeto Socio sin iniciar sus campos.
		 */
	
	public Socio() {
		super();
	}
	
	/**
	 * Constructor que crea un nuevo objeto Socio inicializando sus campos.
	 * 
	 * @param dni Atributo que guarda el dni del socio.
	 * @param nombre Atributo que guarda el nombre del socio. 
	 * @param apellidos Atributo que guarda los apellidos del socio.
	 * @param telefono Atributo que guarda el número de teléfono del socio.
	 * @param telefono2 Atributo que guarda la dirección donde reside el socio.
	 * @param fechaIni Atributo que guarda la fecha de inscripción del socio.
	 * @param fechaFin Atributo que guarda la fecha de baja del socio. 
	 * @param sedeAsignada Atributo que guarda la sede donde está asignado el socio. 
	 * @param cargo Atributo que guarda el cargo del socio. 
	 * @param correo Atributo que guarda la dirección e-mail del socio.
	 * @param cuotaAportación Atributo que guarda la cuota de aportación del socio.
	 * @param estadoAportación Atributo que guarda el estado de aportación del socio. 
	 * @param pass2 Atributo que guarda el tipo de cuota del socio.     
	 * @param tipoCuota2  Atributo que guarda la contraseña del socio.  
	 */
	public Socio(String dni, String nombre, String apellidos, String direccion, Integer telefono, Date fechaIni,
			Date fechaFin, String cargo, String correo, float cuotaAportacion, boolean estadoAportacion, String pass, TipoCuota tipoCuota, Long sede) 
	{
		super(dni, nombre, apellidos, direccion, telefono, fechaIni, fechaFin, sede, cargo, correo);
		this.cuotaAportacion = cuotaAportacion;
		this.estadoAportacion = estadoAportacion;
		this.tipoCuota = tipoCuota;
		this.pass = pass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Metodo accesor de lectura que nos da la password del socio.
	 * 
	 * @return Nos devuelve la password del socio.
	 */
	public String getPass() {
		return pass;
	}
	
	/**
	 * Metodo accesor de lectura que asigna la password del socio.
	 * 
	 * @param pass La password del socio.
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	/**
	 * Metodo accesor de lectura que nos da la aportación que realiza el socio.
	 * 
	 * @return Nos devuelve la aportación del socio.
	 */
	@XmlElement(name = "cuota")
	public float getCuotaAportacion() {
		return cuotaAportacion;
	}
	
	/**
	 * Metodo accesor de lectura que asigna la aportación del socio.
	 * 
	 * @param cuotaAportacion La aportacion del socio.
	 */
	public void setCuotaAportacion(float cuotaAportacion) {
		this.cuotaAportacion = cuotaAportacion;
	}
	
	/**
	 * Metodo accesor de lectura que nos da el estado de la aportación que realiza el socio.
	 * 
	 * @return Nos devuelve la el estado de la aportación del socio.
	 */
	@XmlElement(name = "Estado")
	public Boolean getEstadoAportacion() {
		return estadoAportacion;
	}
	
     /**
 	 * Metodo accesor de lectura que asigna el estado de la  aportación del socio.
 	 * 
 	 * @param estadoAportacion el estado de La aportacion del socio.
 	 */
	public void setEstadoAportacion(Boolean estadoAportacion) {
		this.estadoAportacion = estadoAportacion;
	}
	
	/**
	 * Metodo accesor de lectura que nos da el tipo de cuota del socio.
	 * 
	 * @return Nos devuelve el tipo de cuota del socio.
	 */
	public TipoCuota getTipoCuota() {
		return tipoCuota;
	}
	
     /**
 	 * Metodo accesor de lectura que asigna el tipo de cuota del socio.
 	 * 
 	 * @param tipoCuota el tipo de cuota del socio.
 	 */
	public void setTipoCuota(TipoCuota tipodecuota) {
		this.tipoCuota = tipodecuota;
	}
	
	
		/**
		 * Metodo que genera el menu de acciones que puede realizar el trabajador
		 * en la aplicacion cuando inicia sesion.
		 */
		@Override
		public void abrirSesion() throws IOException, JAXBException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
			int respuestaOpcion = 0;
			Integer[] opcionesValidas = {1, 2, 3};
			String respuestaNuevaAccion;

			System.out.println("\n**********************");
			System.out.println(" Opciones de socio");
			System.out.println("**********************");

			do {

				System.out.println("\nPor favor, introduce el número de la acción que deseas realizar: ");
				System.out.println("1 - Dar de alta un socio");
				System.out.println("2 - Imprimir listado de socios");
				System.out.println("3 - Salir");

				try {
					respuestaOpcion = Integer.parseInt(br.readLine());
				} catch(NumberFormatException nfe) {
					System.out.println("Los caracteres introducidos no son válidos.");
				}

			} while (!Arrays.asList(opcionesValidas).contains(respuestaOpcion));

			switch(respuestaOpcion) {

			case 1:
				darAltaSocio();

				do {

					do {
						System.out.println("¿Deseas darte de alta? (S/N)");
						respuestaNuevaAccion = br.readLine();
					} while (!respuestaNuevaAccion.equalsIgnoreCase("s") && !respuestaNuevaAccion.equalsIgnoreCase("n"));

					if (respuestaNuevaAccion.equalsIgnoreCase("s")) {
						darAltaSocio();
					}

				} while (!respuestaNuevaAccion.equalsIgnoreCase("n"));

				abrirSesion();

				break;

			case 2:
				imprimirListadoSocios();
				abrirSesion();

				break;

			case 3:
				System.out.println("La sesión se ha cerrado con éxito.");
				System.exit(0);

				break;

			}

		}

		/**
		 * Crea una cadena de caracteres con los datos del trabajador.
		 * 
		 * @return Cadena con los datos del trabajador.
		 */
		@Override
		public String toString() {
			return this.nombre + " " + this.apellidos + " (ID empleado - " + this.dni + ")";
		}


		/**
		 * Metodo que permite al empleado introducir por consola los
		 * datos de alta de un nuevo socio.
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
			try {
	        	String dni = br.readLine();
	        	validarDni(dni);
	    		nuevoSocio.setDni(dni);
	        } catch (TelefonoNoValidoException e) {
	        	System.out.println("Dni no válido, podrá modificarlo más adelante"); 
	    		nuevoSocio.setDni("000000000");
	        }
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
		 * Metodo que valida si el numero de dni introducido es correcto.
		 * 
		 * @param numero Numero de telefono introducido.
		 */
		private void validarDni(String dni) {
			final String dniRegexp = "\\d{8}[A-HJ-NP-TV-Z]";
			if (!Pattern.matches(dniRegexp, dni)) {
				throw new DniNoValidoException(dni);
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
