package acadevs.entreculturas.modelo;


import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import acadevs.entreculturas.enums.TipoCuota;

/**
 * Clase que representa cada uno de los socios que forman la ONG
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
@XmlRootElement(name = "Socio")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Socio extends Persona {
    
// ATRIBUTOS DEL MODELO
	
	private Integer id = null;
	
	private String pass;
	
	private float cuotaAportacion;
	
	boolean estadoAportacion = false;
	
	private TipoCuota tipoCuota;

	
	// CONSTRUCTORES
	
	/**
	* Constructor que crea un nuevo objeto Socio sin iniciar sus campos.
	*/
	public Socio() {
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
			Date fechaFin, String cargo, String correo, float cuotaAportacion, boolean estadoAportacion, String pass, TipoCuota tipoCuota, AdministracionFisica sede) 
	{
		super(dni, nombre, apellidos, direccion, telefono, fechaIni, fechaFin, sede, cargo, correo);
		this.cuotaAportacion = cuotaAportacion;
		this.estadoAportacion = estadoAportacion;
		this.tipoCuota = tipoCuota;
		this.pass = pass;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	 * Crea una cadena de caracteres con los datos del trabajador.
	 * 
	 * @return Cadena con los datos del trabajador.
	 */
	@Override
	public String toString() {
		return super.toString() + "Socio [pass=" + pass + ", cuotaAportacion=" + cuotaAportacion + ", estadoAportacion=" + estadoAportacion
				+ ", tipoCuota=" + tipoCuota + "]";
	}
	


	
}
