package acadevs.entreculturas.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLAdministracionFisicaDAO;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLSocioDAO;
import acadevs.entreculturas.enums.TipoCuota;
import acadevs.entreculturas.modelo.AdministracionFisica;
import acadevs.entreculturas.modelo.Socio;
import acadevs.entreculturas.modelo.ViewException;

public class FormDatosSocio {
	
	private String dni;
	
	public FormDatosSocio (String dniSocio) throws ViewException {
	
		this.dni = dniSocio;
		
		try {
			imprimeFormulario();
		} catch (DAOException | IOException | ParseException e) {
			e.printStackTrace();
			throw new ViewException ("Hubo un problema en el formulario de socios", e);
		}
	}
	
	public Socio imprimeFormulario() throws DAOException, IOException, ParseException {
		
		Application.limpiaPantalla();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		Socio nuevoSocio = new Socio();
		
		nuevoSocio.setDni(dni);
		
		System.out.println("\n****************************");
		System.out.println(" Formulario de datos de SOCIO");
		System.out.println("*****************************");
		
		System.out.println("\nNombre: ");
		nuevoSocio.setNombre(br.readLine());
		
		System.out.println("\nApellidos: ");
		nuevoSocio.setApellidos(br.readLine());
		
		System.out.println("\nEmail: ");
		nuevoSocio.setCorreo(br.readLine());
		
		String tlf;
		do {
			System.out.println("\nTelefono: ");
		    tlf = br.readLine();
        } while ((!validarNumeroTelefono(tlf)) || (tlf != ""));
		if (tlf == "") { 
			tlf = "0"; 
		}
        	nuevoSocio.setTelefono(Integer.parseInt(tlf));
	
        System.out.println("\nFecha Inscripción: ");
		nuevoSocio.setFechaInicio(new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine()));
		
		System.out.println("\nFecha Finalización: ");
		nuevoSocio.setFechaInicio(new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine()));
		        
		System.out.println("\nCargo del Socio: ");
		nuevoSocio.setCargo(br.readLine());

		System.out.println("\nCantidad por Aportación: ");
		String linea = br.readLine();
		if (linea == "") {
			linea = "0";
		}
		float cantidad = Float.parseFloat(br.readLine());
		nuevoSocio.setCuotaAportacion(cantidad);
		
		System.out.println("¿Aportación ACTIVA? (S/N): ");
		if (br.readLine().equalsIgnoreCase("s")) {
			nuevoSocio.setEstadoAportacion(true);
		}
		
		System.out.println("\nTipo Cuota -- Mensual (M) : Trimestral (T) : Anual(A): ");
		String tipoCuota = br.readLine();
			if  (tipoCuota.equalsIgnoreCase("M")) {
				nuevoSocio.setTipoCuota(TipoCuota.MES);
			} else if (tipoCuota.equalsIgnoreCase("T")) {
				nuevoSocio.setTipoCuota(TipoCuota.TRIM);
			} else {
				nuevoSocio.setTipoCuota(TipoCuota.ANUAL);//establece el tipo de cuota en base a la clase Enum. Si el campo está vacío asigna Anual.
			}
				
		/*
		 * Bloque sede asignada
		 */
			System.out.println("\nNombre Sede Asignada: ");
			String nombreAdmin = br.readLine();
		
			MySQLDAOFactory mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL");
 	   		MySQLAdministracionFisicaDAO administraciones = mysqlF.getAdministracionFisicaDAO();
 	   
 	   		AdministracionFisica sede = administraciones.obtener(nombreAdmin);

 	   		if (sede == null) {
				System.out.println("La sede "+nombreAdmin+" no existe. ¿Desea crear una nueva sede con este nombre?");
				String respuesta = br.readLine();
				
				while (sede == null) {
					if (respuesta.equalsIgnoreCase("s")) {
						FormDatosAdministracion frmDatosSede = new FormDatosAdministracion(nombreAdmin);
						sede = frmDatosSede.imprimeMenu(nombreAdmin);
						administraciones.crearNuevo(sede);
  		  			} else {
  		  				System.out.println("Es obligatorio asignar una sede al socio");
  		  				
  		  				System.out.println("¿Mostrar Sedes disponibles?");
  		  				respuesta = br.readLine();
  		  				
  		  				if (respuesta.equalsIgnoreCase("s")) {
  		  					List<AdministracionFisica> listaSedes = administraciones.obtenerTodos();
  		  					for ( AdministracionFisica elem : listaSedes) {
  		  						elem.toString();
  		  					}
  		  				}
  		  			}
  		  		}
			}
			
 	   		cierraConexionMySQL(mysqlF);	
			
			nuevoSocio.setSedeAsignada(sede);
  		// Fin Bloque sede asignada
		
		Random random = new Random();
		
		String pass = String.format("%06d", random.nextInt(1000000));
		nuevoSocio.setPass(pass);
		
		return nuevoSocio;
	}
	
	public void cierraConexionMySQL(MySQLDAOFactory mysqlf) throws DAOException {
		try {
	 		   mysqlf.cerrar();
	 	   } catch (SQLException e) {
	 		   throw new DAOException("Error al intentar cerrar la conexión a la base de datos", e);
	 	   }
	}
	
	private boolean validarNumeroTelefono(String numero) {
		final String regexStr = "^(\\+34|0034|34)?[6789]\\d{8}$";
		System.out.println("Formato de telefono incorrecto");
		return (!Pattern.matches(regexStr, numero));
	}
}
