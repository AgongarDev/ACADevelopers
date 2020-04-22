package acadevs.entreculturas.vista.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLAdministracionFisicaDAO;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.enums.TipoCuota;
import acadevs.entreculturas.modelo.AdministracionFisica;
import acadevs.entreculturas.modelo.Socio;
import acadevs.entreculturas.modelo.ViewException;
import acadevs.entreculturas.util.Utilidad;

public class FormDatosSocio {
	
	private Socio socioAnterior;
	private Socio nuevoSocio;
	private boolean esNuevo;
	private BufferedReader br;
	private String entrada;
	
	public FormDatosSocio (Socio anterior, boolean esNuevo) throws ViewException {
	
		this.nuevoSocio = new Socio();
		this.br = new BufferedReader(new InputStreamReader(System.in));
		this.esNuevo = esNuevo;
		
		if (anterior == null) {
			this.socioAnterior = new Socio();
		}else this.socioAnterior = anterior;
		
	}
	
	public Socio imprimeFormulario() throws DAOException, IOException, ParseException {
		
		Utilidad.limpiaPantalla();
		
		System.out.println("\n**************************************************************************");
		System.out.println("                  Formulario de datos de CONTACTO DE SOCIO");
		System.out.println("\n**************************************************************************");
		
		if (!esNuevo) {
			System.out.println("Los datos del socio anterior son:");
			System.out.println(socioAnterior.toString());
		}
//nombre		
		System.out.print("\nNombre: ");
			this.entrada = br.readLine();
			if (entrada.isEmpty()) {
				System.out.print(socioAnterior.getNombre());
				nuevoSocio.setNombre(socioAnterior.getNombre());
			} else {
				nuevoSocio.setNombre(entrada);
				}
//apellidos		
		System.out.print("\nApellidos: ");
			this.entrada = br.readLine();
			if (entrada.isEmpty()) {
				System.out.print(socioAnterior.getApellidos());
				nuevoSocio.setApellidos(socioAnterior.getApellidos());
			} else {
				nuevoSocio.setApellidos(entrada);
				}
//email		
		System.out.print("\nEmail: ");
			this.entrada = br.readLine();
			if (entrada.isEmpty()) {
				System.out.print(socioAnterior.getCorreo());
				nuevoSocio.setCorreo(socioAnterior.getCorreo());
			} else {
				nuevoSocio.setCorreo(entrada);
				}
//telefono
			do {
				System.out.print("\nTelefono: ");
				this.entrada = br.readLine();
	        } while ((!Utilidad.validarNumeroTelefono(entrada)) && (!entrada.isEmpty()));
			if (entrada.isEmpty()) { 
				System.out.print(socioAnterior.getTelefono());
				nuevoSocio.setTelefono(socioAnterior.getTelefono()); 
			}	else {
				nuevoSocio.setTelefono(Integer.parseInt(entrada));
				}
//fecha inicial	
        System.out.print("\nFecha Inscripción: ");
        	this.entrada = br.readLine();
        	if (entrada.isEmpty()) {
        		System.out.print(socioAnterior.getFechaInicio());
        		nuevoSocio.setFechaInicio(socioAnterior.getFechaInicio());
        	} else {
        		nuevoSocio.setFechaInicio(new SimpleDateFormat("dd/MM/yyyy").parse(entrada));
        		}
//fecha final        
		System.out.print("\nFecha Finalización: ");
			this.entrada = br.readLine();
			if (entrada.isEmpty()) {
				System.out.print(socioAnterior.getFechaFin());
				nuevoSocio.setFechaFin(socioAnterior.getFechaFin());
			} else {
				nuevoSocio.setFechaFin(new SimpleDateFormat("dd/MM/yyyy").parse(entrada));
				}
//cargo		        
		System.out.print("\nCargo del Socio: ");
			this.entrada = br.readLine();
			if (entrada.isEmpty()) {
				System.out.print(socioAnterior.getCargo());
				nuevoSocio.setCargo(socioAnterior.getCargo());
			} else {
				nuevoSocio.setCargo(entrada);
				}
//password
		Random random = new Random();
		
		String pass = String.format("%06d", random.nextInt(1000000));
		nuevoSocio.setPass(pass);	
		
		nuevoSocio.setSedeAsignada(especificosAdministracion());
		
		nuevoSocio.setDni(socioAnterior.getDni());
		nuevoSocio.setId(socioAnterior.getId());
		
		especificosSocio(nuevoSocio);
		
		System.out.println("Los datos del socio a guardar son:\n");
		System.out.println(nuevoSocio.toString());
		
		System.out.println("\nDatos correctos para grabación? (S/N)");
		if (br.readLine().equalsIgnoreCase("n")) {
			imprimeFormulario();
		}
		
		return nuevoSocio;
	}

	public AdministracionFisica especificosAdministracion () throws IOException, DAOException {
		
		MySQLDAOFactory mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL");
	   	MySQLAdministracionFisicaDAO administraciones = mysqlF.getAdministracionFisicaDAO();
		
	   	AdministracionFisica sede = null;
	   	
		do{
			System.out.print("\nNombre Sede Asignada: ");
				this.entrada = br.readLine();
				
				if (entrada.isEmpty()) {
					nuevoSocio.setSedeAsignada(socioAnterior.getSedeAsignada());
					sede = socioAnterior.getSedeAsignada();
				} else {
					sede = administraciones.obtener(entrada);
					if (sede == null) {
						System.out.println("La sede "+entrada+" no existe. ¿Desea crear una nueva sede con este nombre?");
						String respuesta = br.readLine();
			
						if (respuesta.equalsIgnoreCase("s")) {
							sede = new FormDatosAdministracion(entrada).imprimeFormulario();
							administraciones.crearNuevo(sede);
	  		  			} 	else {
	  		  					System.out.println("Es obligatorio asignar una sede al socio");
	  		  			
	  		  					System.out.println("\n¿Mostrar Sedes disponibles?");
	  		  					respuesta = br.readLine();
	  		  				
	  		  					if (respuesta.equalsIgnoreCase("s")) {
	  		  						List<AdministracionFisica> listaSedes = administraciones.obtenerTodos();
	  		  						for ( AdministracionFisica elem : listaSedes) {
	  		  							System.out.println(elem.toString());
	  		  						}
	  		  					}
		  		  			}
		  		  }
				} 	   		
		} while (sede == null);
		
		return sede;
	}
	
	public Socio especificosSocio(Socio socioAActualizar) throws IOException {
		
		nuevoSocio = socioAActualizar;
		
		System.out.println("\n**************************************************************************");
		System.out.println("                 Formulario de datos ESPECÍFICOS DE SOCIO");
		System.out.println("\n**************************************************************************");
		
		//importe
				System.out.print("\nImporte por Aportación: ");
					entrada = br.readLine();
					
					while ((!Utilidad.validarFloat(entrada)) && (!entrada.isEmpty())) {
						System.out.print("\nIntroduzca el importe de la cuota: ");
						entrada = br.readLine();
					}
					
					if (entrada.isEmpty()) {
						nuevoSocio.setCuotaAportacion(socioAnterior.getCuotaAportacion());
					} else {
						nuevoSocio.setCuotaAportacion(Float.parseFloat(entrada));
						}
		//Socio activo S/N
				do {
					System.out.print("¿Aportación ACTIVA? (S/N): ");
					entrada = br.readLine();
					
					if (entrada.equalsIgnoreCase("s")) {
						nuevoSocio.setEstadoAportacion(true);
					} else {
						nuevoSocio.setEstadoAportacion(false);
						}
				} while (!entrada.equalsIgnoreCase("s") && (!entrada.equalsIgnoreCase("n") && (!entrada.isEmpty())));
				
				if (entrada.isEmpty()) {
					nuevoSocio.setSedeAsignada(socioAnterior.getSedeAsignada());
				}
		//Tipo cuota		
				System.out.print("\nTipo Cuota -- Mensual (M) : Trimestral (T) : Anual(A): ");
					String tipoCuota = br.readLine();
					if  (tipoCuota.equalsIgnoreCase("M")) {
						nuevoSocio.setTipoCuota(TipoCuota.MES);
					} else if (tipoCuota.equalsIgnoreCase("T")) {
						nuevoSocio.setTipoCuota(TipoCuota.TRIM);
						} else {
							nuevoSocio.setTipoCuota(TipoCuota.ANUAL);//establece el tipo de cuota en base a la clase Enum. Si el campo está vacío asigna Anual.
							}
					return nuevoSocio;
	}
}
