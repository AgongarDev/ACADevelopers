package acadevs.entreculturas.modelo;

import java.util.Date;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Esta clase representa a cada uno de los miembros del personal de la ONG
 * que tienen un contrato de trabajo y tienen un horario laboral.
 * 
 * @author Antonio,Ana y Cristina.
 * @version 1.0
 *
 */
@XmlRootElement(name = "trabajador")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(propOrder={"horarioLaboral", "pass"})
public class Trabajador extends Persona{

	// CAMPOS

	private String horarioLaboral;

	// CONSTRUCTORES

	/**
	 * Constructor que crea un nuevo objeto Trabajador sin inicializar sus campos.
	 * 
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
	public Trabajador() throws JAXBException {
		super();
	}

	/**
	 * Constructor que crea un nuevo objeto Trabajador inicializando sus campos.
	 * 
	 * @param nombre Atributo que guarda el nombre de la persona.
	 * @param apellidos Atributo que guarda los apellidos de la persona.
	 * @param id Atributo que guarda el id de la persona.
	 * @param email Atributo que guarda el email de la persona.
	 * @param telefono Atributo que guarda el telefono de la persona.
	 * @param direccion Atributo que guarda la direccion de la persona.
	 * @param delegacionAsignada Atributo que guarda el nombre de la delegacion asignada a la persona.
	 * @param antiguedad Atributo que guarda la antiguedad en la ong de la persona.
	 * @param proyectosAsignados Atributo que guarda los proyectos asignados a la persona.
	 * @param horarioLaboral Atributo que guarda el horario laboral de la persona.
	 * @param pass Atributo que guarda la password de loggin de la persona.
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
	public Trabajador(String nombre, String apellidos, String id, String email,
					  String telefono, String direccion, AdministracionFisica delegacionAsignada,
					  Date antiguedad, ListadoProyectos proyectosAsignados,
					  String horarioLaboral, String pass) throws JAXBException {
		super();
		this.horarioLaboral = horarioLaboral;
	}


	// MÉTODOS

	/**
	 * Metodo accesor de lectura que nos da el horario laboral del trabajador.
	 * 
	 * @return Nos devuelve el horario laboral del trabajador.
	 */
	@XmlElement(name = "horario")
	public String getHorarioLaboral() {
		return horarioLaboral;
	}


	/**
	 * Metodo accesor de escritura que asigna el horario laboral del trabajador.
	 * 
	 * @param horarioLaboral El horario laboral del trabajador.
	 */
	public void setHorarioLaboral(String horarioLaboral) {
		this.horarioLaboral = horarioLaboral;
	}
}

