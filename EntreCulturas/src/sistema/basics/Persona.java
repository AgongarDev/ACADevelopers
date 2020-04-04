package sistema.basics;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Esta clase representa a todo el personal de la ONG: Trabajadores, Colaboradores, 
 * Socios y Voluntarios
 *
 */

@XmlRootElement(name = "persona")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(propOrder={"dni", "nombre", "apellidos", "telefono", "domicilio", "fechaInicio", 
		"fechaFin", "sedeAsignada", "cargo", "correo"})

public class Persona {

	    // CAMPOS
	
	    protected String dni;
		protected String nombre;
		protected String apellidos;
		protected String telefono;
		protected String domicilio;
		protected String fechaInicio;
		protected String fechaFin;
		protected AdministracionFisica sedeAsignada;
		protected Ong ong;
		protected String cargo;
		protected String correo;
		
		// CONSTRUCTORES
		
		/**
		 * Crea un nuevo objeto Persona sin inicializar sus campos.
		 * llamamos al método super() para dejar constancia que siempre que se llama al constructor superior, en este caso Objeto.
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
		 * @param fechaInicio Atributo que guarda la fecha de inicio de la colaboración de la persona en la ONG.
		 * @param fechaFin Atributo que guarda la fecha del fin de la colaboración de la persona en la ONG.
		 * @param sedeAsignada Atributo que asigana la sede a la persona.
		 * @param cargo Atributo asigna el cargo de la persona.
		 * @param correo Atributo que guarda el correo electronico de la persona.
		 * 
		 */
		public Persona(String dni, String nombre, String apellidos, String telefono,
			       String domicilio, String fechaInicio, String fechaFin, AdministracionFisica sedeAsignada, 
			       String cargo, String correo, Ong ong) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.sedeAsignada = sedeAsignada;
		this.cargo = cargo;
		this.correo = correo;
		this.ong = ong;
		
	}
		// METODOS
		
		public Ong getOng() {
			return ong;
		}

		public void setOng(Ong ong) {
			this.ong = ong;
		}
		/**
		 * Metodo de lectura que da el dni de la persona
		 * 
		 * @return devuelve el dni de la persona
		 */
		@XmlElement(name = "dni")
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
		public String getTelefono() {
			return telefono;
		}

		/**
		 * Metodo de escritura que guarda el telefono de la persona
		 * 
		 * @param telefono
		 */
		public void setTelefono(String telefono) {
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
		public String getFechaInicio() {
			return fechaInicio;
		}

		/**
		 * Metodo de escritura que guarda la fecha en la que la persona empieza a colaborar o trabajar con la ONG
		 * 
		 * @param fechaInicio
		 */
		public void setFechaInicio(String fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		
		/**
		 * Metodo de lectura que da la fecha en la que la persona finaliza su colaboración con la ONG
		 * 
		 * @return devuelve la fecha fin
		 */
		@XmlElement(name = "fechaFin")
		public String getFechaFin() {
			return fechaFin;
		}

		/**
		 * Metodo de escritura que guarda la fecha en la que la persona finaliza su colaboración con la ONG
		 * 
		 * @param fechaFin
		 */
		public void setFechaFin(String fechaFin) {
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
		 * Metodo de escritura que guarda la sede en la que se encuentra una persona
		 * 
		 * @param sedeAsignada
		 */
		public void setSedeAsignada(AdministracionFisica sede) {
			this.sedeAsignada = sede;
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
								
}
