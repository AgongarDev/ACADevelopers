package acadevs.entreculturas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;

import java.util.Date;

/**
 * 
 * Esta clase representa a todo el personal de la ONG: Trabajadores, Colaboradores, 
 * Socios y Voluntarios
 *
 */
/**
 * Clase que representa cada uno de los socios que forman la ONG
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
@Entity
@Table(name="contactos")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(propOrder={"dni", "nombre", "apellidos", "domicilio", "telefono", "fechaInicio", 
		"fechaFin", "sedeAsignada", "cargo", "correo"})
@XmlSeeAlso ({Socio.class, Administrador.class})
public abstract class Persona implements Serializable{

	    // CAMPOS
	    @Id
	    @Column(name="dni")
		protected String dni;
	    @Column(name="nombre")
		protected String nombre;
	    @Column(name="apellido")
		protected String apellidos;
	    @Column(name="telefono")
		protected int telefono = 0;
	    @Column(name="direccion")
		protected String domicilio;
	    @Column(name="fecha_inicio")
		protected Date fechaInicio;
	    @Column(name="fecha_fin")
		protected Date fechaFin;
	    @Column(name="sede")
		private AdministracionFisica sedeAsignada;
	    @Column(name="cargo")
		protected String cargo;
	    @Column(name="correo")
		protected String correo;
		
		// CONSTRUCTORES
		
		/**
		 * Crea un nuevo objeto Persona sin inicializar sus campos.
		 * llamamos al m�todo super() para dejar constancia que siempre que se llama al constructor superior, en este caso Objeto.
		 */
		
		public Persona() {
			super();
		}

		/**
		 * Crea un nuevo objeto Persona inicializando sus campos.
		 * 
		 * @param dni Atributo que guarda el dni de la persona.
		 * @param nombre Atributo que guarda el nombre de la persona.
		 * @param apellidos Atributo que guarda los apellidos de la persona.
		 * @param telefono Atributo que guarda el telefono de la persona.
		 * @param domicilio Atributo que guarda el domicilio de la persona.
		 * @param fechaInicio Atributo que guarda la fecha de inicio de la colaboraci�n de la persona en la ONG.
		 * @param fechaFin Atributo que guarda la fecha del fin de la colaboraci�n de la persona en la ONG.
		 * @param sedeAsignada Atributo que asigana la sede a la persona.
		 * @param cargo Atributo asigna el cargo de la persona.
		 * @param correo Atributo que guarda el correo electronico de la persona.
		 * 
		 */
		public Persona(String dni, String nombre, String apellidos, String direccion, int telefono,
				Date fechaIni, Date fechaFin, AdministracionFisica sede, String cargo, String correo) {
			
			this.dni = dni;
			this.nombre = nombre;
			this.apellidos = apellidos;
			this.telefono = telefono;
			this.domicilio = direccion;
			this.fechaInicio = fechaIni;
			this.fechaFin = fechaFin;
			this.setSedeAsignada(sede);
			this.cargo = cargo;
			this.correo = correo;
		}

	
		// METODOS
		/**
		 * Metodo de lectura que da el dni de la persona
		 * 
		 * @return devuelve el dni de la persona
		 */
		@XmlAttribute(name = "dni")
		public String getDni() {
			return dni;
		}

		/**
		 * Metodo de escritura que guarda el dni de la persona
		 * 
		 * @param dni
		 */
		public void setDni(String dni) {
			this.dni = dni;
		}
		
		/**
		 * Metodo de lectura que da el nombre de la persona
		 * 
		 * @return devuelve el nombre de la persona
		 */
		@XmlElement(name = "nombre")
		public String getNombre() {
			return nombre;
		}

		/**
		 * Metodo de escritura que guarda el nombre de la persona
		 * 
		 * @param nombre 
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		/**
		 * Metodo de lectura que da los apellidos de la persona
		 * 
		 * @return devuelve los apellidos de la persona
		 */
		@XmlElement(name = "apellidos")
		public String getApellidos() {
			return apellidos;
		}

		/**
		 * Metodo de escritura que guarda los apellidos de la persona
		 * 
		 * @param apellidos 
		 */
		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}
		
		/**
		 * Metodo de lectura que da el telefono de la persona
		 * 
		 * @return devuelve el telefono de la persona
		 */
		@XmlElement(name = "telefono")
		public int getTelefono() {
			return telefono;
		}

		/**
		 * Metodo de escritura que guarda el telefono de la persona
		 * 
		 * @param telefono
		 */
		public void setTelefono(int telefono) {
			this.telefono = telefono;
		}

		/**
		 * Metodo de lectura que da el domicilio de la persona
		 * 
		 * @return devuelve el domicilio de la persona
		 */
		@XmlElement(name = "domicilio")
		public String getDomicilio() {
			return domicilio;
		}

		/**
		 * Metodo de escritura que guarda el domicilio de la persona
		 * 
		 * @param domicilio
		 */
		public void setDomicilio(String domicilio) {
			this.domicilio = domicilio;
		}
		
		/**
		 * Metodo de lectura que da la fecha en la que la persona empieza a colaborar o trabajar con la ONG
		 * 
		 * @return devuelve la fecha de inicio
		 */
		@XmlElement(name = "fechaInicio")
		public Date getFechaInicio() {
			return fechaInicio;
		}

		/**
		 * Metodo de escritura que guarda la fecha en la que la persona empieza a colaborar o trabajar con la ONG
		 * 
		 * @param fechaInicio
		 */
		public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		
		/**
		 * Metodo de lectura que da la fecha en la que la persona finaliza su colaboraci�n con la ONG
		 * 
		 * @return devuelve la fecha fin
		 */
		@XmlElement(name = "fechaFin")
		public Date getFechaFin() {
			return fechaFin;
		}

		/**
		 * Metodo de escritura que guarda la fecha en la que la persona finaliza su colaboraci�n con la ONG
		 * 
		 * @param fechaFin
		 */
		public void setFechaFin(Date fechaFin) {
			this.fechaFin = fechaFin;
		}
		
		/**
		 * Metodo de lectura que da la sede en la que se encuentra una persona determinada
		 * 
		 * @return devuelve la sede asignada
		 */
		@XmlElement(name = "sedeAsignada")
		public AdministracionFisica getSedeAsignada() {
			return sedeAsignada;
		}
		
		/**
		 * Metodo de lectura que da el cargo que tiene la persona
		 * 
		 * @return devuelve el cargo de la persona
		 */
		@XmlElement(name = "cargo")
		public String getCargo() {
			return cargo;
		}

		/**
		 * Metodo de escritura que guarda el cargo que ostenta la persona
		 * 
		 * @param cargo
		 */
		public void setCargo(String cargo) {
			this.cargo = cargo;
		}
		
		/**
		 * Metodo de lectura que da el correo de la persona
		 * 
		 * @return devuelve el correo de la persona
		 */
		@XmlElement(name = "correo")
		public String getCorreo() {
			return correo;
		}

		/**
		 * Metodo de escritura que guarda el correo de la persona
		 * 
		 * @param correo
		 */
		public void setCorreo(String correo) {
			this.correo = correo;
		}
		
		/**
		 * Metodo de escritura que guarda la sede en la que se encuentra una persona
		 * 
		 * @param sedeAsignada
		 */
		public void setSedeAsignada(AdministracionFisica sedeAsignada) {
			this.sedeAsignada = sedeAsignada;
		}
		
	    @Override
		public String toString() {
			return "Persona [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
					+ ", domicilio=" + domicilio + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
					+ ", sedeAsignada=" + sedeAsignada + ", cargo=" + cargo + ", correo=" + correo + "]";
		}
								
}
