package acadevs.entreculturas.modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.JAXBException;


/**
 * Esta clase representa a la persona encargada de crear los proyectos, modificarlos,
 * asignar el personal y gestionar la financiación de la ONG.
 * 
 * @author Cristina, Antonio y Ana.
 * @version 1.0
 *
 */
public class Administrador extends Persona implements Serializable {
	
	// CAMPOS

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private String rootPass;
	
	// CONSTRUCTORES
	/**
	 * Constructor que crea un nuevo objeto Admin sin inicializar sus campos.
	 * 
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
	public Administrador() {
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
	public Administrador(String dni, String nombre, String apellidos, String domicilio, int telefono,
		       Date fechaInicio, Date fechaFin, AdministracionFisica sedeAsignada, 
		       String cargo, String correo, Ong ong, String pass) throws JAXBException {
		super(dni, nombre, apellidos, domicilio, telefono, fechaInicio, fechaFin, sedeAsignada, cargo, correo);
		
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
	

}
