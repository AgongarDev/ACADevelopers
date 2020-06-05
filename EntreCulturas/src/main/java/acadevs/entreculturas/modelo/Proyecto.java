package acadevs.entreculturas.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;
import java.math.BigDecimal;

import acadevs.entreculturas.enums.LineaDeAccion;

/**
 * Clase que representa cada uno de los proyectos que forman la ONG
 * y que involucran a personal contratado y voluntarios.
 * 
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
@Entity
@Table(name="proyectos")
@XmlRootElement(name = "proyecto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(propOrder={"idProyecto", "nombre", "direccion","pais", "socioLocal","financiador","financiacion", "fechaInicio","fechaFinalizacion","lAccion","sublineaDeAccion","accionesARealizar","voluntariosAsignados","contratadosAsignados"})
public class Proyecto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// CAMPOS
	@Id
    @Column(name="id_proyecto")
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private int idProyecto;
	
	@Column(name="linea_accion")
	@Enumerated(EnumType.STRING)
	private LineaDeAccion lAccion;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="fecha_inicio")
	private Date fechaInicio;
	
	@Column(name="fecha_fin")
	private Date fechaFinalizacion;
	
	@Column(name="sublinea_accion")
	private String sublineaDeAccion;
	
	@Column(name="pais")
	private String pais;
	
	@Column(name="direccion")
	private String direccion;
	
	@ManyToMany( cascade = {CascadeType.ALL})
	@JoinTable(
			name = "proyecto_colaborador",
			joinColumns = { @JoinColumn (name = "id_proyecto") },
			inverseJoinColumns = { @JoinColumn (name = "id_colaborador") }
			)
	private Set<Colaborador> colaboradores = new HashSet<>();
	
	@Column(name="financiador")
	@Enumerated(EnumType.STRING)
	private FINANCIACION financiador; 
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "acciones_proyecto",
			joinColumns = { @JoinColumn (name = "id_proyecto") },
			inverseJoinColumns = { @JoinColumn (name = "id_accion") }
			)
	private Set<Accion> accionesARealizar = new HashSet<>();

	@Column(name="financiacion")
	private BigDecimal financiacion;
	
	@ManyToMany(mappedBy = "proyectos")
	private Set<Personal> personalAsignado = new HashSet<>(); 
	
	// CONSTRUCTORES
	
		/**
		 * Constructor que crea un nuevo objeto Proyecto sin iniciar sus campos.
		 */

	enum FINANCIACION {
		Institucion, Empresa, Particular, Herencias
	}
	
	public Proyecto () {

	}
	
	/**
	 * Constructor que crea un nuevo objeto Proyecto inicializando sus campos.
	 * 
	 * @param idProyecto Atributo que guarda el id del proyecto.
	 * @param nombre Atributo que guarda el nombre del proyecto. 
	 * @param lAccion Atributo que guarda la linea de acción del proyecto.
	 * @param fechaInicio Atributo que guarda la fecha en que comienza el proyecto.
	 * @param fechaFinalizacion Atributo que guarda la fecha en que acaba del proyecto.
	 * @param sublineaDeAccion Atributo que guarda la sublinea de acción del proyecto.
	 * @param pais Atributo que guarda el país donde se desarrolla el proyecto. 
	 * @param direccion Atributo que guarda dirección donde se puede encontrar información sobre el proyecto. 
	 * @param sociolocal Atributo que guarda la identidad del socio local del proyecto. 
	 * @param financiador Atributo que guarda la identidad del financiador del proyecto. 
	 */
	public Proyecto (int id, 
			String nombre, 
			LineaDeAccion lAccion, 
			Date fechaInicio,
			Date fechaFin, 
			String subLineaDeAccion, 
			String pais, 
			String direccion, 
			FINANCIACION financiador,
			BigDecimal importeFinanciado) {
		super();
		this.idProyecto = id;
		this.nombre = nombre;
		this.lAccion = lAccion;
		this.fechaInicio = fechaInicio;
		this.fechaFinalizacion = fechaFin;
		this.sublineaDeAccion = subLineaDeAccion;
		this.pais = pais;
		this.direccion = direccion;
		this.financiador = financiador;
		this.financiacion = importeFinanciado;
	}

	//Getters y Setters
	/**
	 * Metodo accesor de lectura que nos da el ID de la Delegación.
	 * 
	 * @return nos devuelve el id del proyecto.	 
	 *
	 */
	@XmlElement(name = "idProyecto")
	public int getIdProyecto() {
		return idProyecto;
	}

	/**
	 * Metodo accesor de escritura que asigna el id del proyecto.
	 * 
	 * @param idProyecto id del proyecto.
	 */
	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}
	/**
	 * Metodo accesor de lectura que nos da la línea de Acción.
	 * 
	 * @return nos devuelve la línea de Acción del proyecto.	 
	 *
	 */
	@XmlElement(name = "lAccion")
	public String getlAccion() {
		return lAccion.getTexto();
	}
	/**
	 * Metodo accesor de escritura que asigna un string como linea de acción.
	 * 
	 * @param lAccion línea de Acción del proyecto.
	 */
	public void setlAccion(LineaDeAccion lineadeaccion) {
		this.lAccion = lineadeaccion;
	}
	/**
	 * Metodo accesor de lectura que nos da el nombre del Proyecto.
	 * 
	 * @return nos devuelve el nombre del proyecto.	 
	 *
	 */
	@XmlElement(name = "nombre")
	public String getNombreProyecto() {
		return nombre;
	}
	/**
	 * Metodo accesor de escritura que asigna un string como nombre.
	 * 
	 * @param nombre nombre del proyecto.
	 */
	public void setNombreProyecto(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo accesor de lectura que nos da la fecha de inicio del Proyecto.
	 * 
	 * @return nos devuelve la fecha de inicio del proyecto.	 
	 *
	 */
	@XmlElement(name = "fechaInicio")
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * Metodo accesor de escritura que asigna una fecha como inicio.
	 * 
	 * @param fechaInicio Inicio del proyecto.
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * Metodo accesor de lectura que nos da la fecha de finalización del Proyecto.
	 * 
	 * @return nos devuelve la fecha de finalización del proyecto.	 
	 *
	 */
	@XmlElement(name = "fechaFinalizacion")
	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	/**
	 * Metodo accesor de escritura que asigna una fecha como inicio.
	 * 
	 * @param fechaInicio Inicio del proyecto.
	 */
	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	/**
	 * Metodo accesor de lectura que nos da la sublinea de acción del Proyecto.
	 * 
	 * @return nos devuelve la sublinea de acción del proyecto.	 
	 *
	 */
	@XmlElement(name = "sublineaAccion")
	public String getSublineaDeAccion() {
		return sublineaDeAccion;
	}
	/**
	 * Metodo accesor de escritura que asigna una string como  sublinea de acción.
	 * 
	 * @param  sublineaDeAccion  sublinea de acción del proyecto.
	 */
	public void setSublineaDeAccion(String sublineaDeAccion) {
		this.sublineaDeAccion = sublineaDeAccion;
	}
	/**
	 * Metodo accesor de lectura que nos da el país donde se desarrolla el Proyecto.
	 * 
	 * @return nos devuelve el país donde se desarrolla el proyecto.	 
	 *
	 */
	@XmlElement(name = "pais")
	public String getPais() {
		return pais;
	}
	/**
	 * Metodo accesor de escritura que asigna una string como  país.
	 * 
	 * @param  pais  pais del proyecto.
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}
	/**
	 * Metodo accesor de lectura que nos da la direccion del Proyecto.
	 * 
	 * @return nos devuelve la direccion del proyecto.	 
	 *
	 */
	@XmlElement(name = "direccion")
	public String getDireccion() {
		return direccion;
	}
	/**
	 * Metodo accesor de escritura que asigna una string como direccion.
	 * 
	 * @param direccion  direccion del proyecto.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Set<Colaborador> getColaboradores() {
		return colaboradores;
	}
	
	public void addColaborador ( Colaborador c ) {
		this.colaboradores.add(c);
		c.addProyecto(this);
	}

	public void removeColaborador ( Colaborador c ) {
		this.colaboradores.remove(c);
		c.removeProyecto(this);
	}

	public void setColaboradores(Set<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	/**
	 * Metodo accesor de lectura que nos da el financiador del Proyecto.
	 * 
	 * @return nos devuelve el financiador del proyecto.	 
	 *
	 */
	@XmlElement(name = "financiador")
	public FINANCIACION getFinanciador() {
		return financiador;
	}
	/**
	 * Metodo accesor de escritura que asigna un Aportador como financiador.
	 * 
	 * @param socioLocal  financiador del proyecto.
	 */
	public void setFinanciador(FINANCIACION financiador) {
		this.financiador = financiador;
	}
	/**
	 * Metodo que lista las acciones a realizar en el proyecto. 
	 * 
	 * @param acciones a realizar lista de acciones.
	 */
	
	public Set<Accion> getAccionesARealizar() {
		return accionesARealizar;
	}

	public void setAccionesARealizar(Set<Accion> accionesARealizar) {
		this.accionesARealizar = accionesARealizar;
	}

	public Set<Personal> getPersonalAsignado() {
		return personalAsignado;
	}

	public void setPersonalAsignado(Set<Personal> personalAsignado) {
		this.personalAsignado = personalAsignado;
	}
	
	@XmlElement(name = "accionesARealizar")
	public void listAccionesARealizar() {
		for (Accion elem : accionesARealizar) {
			elem.toString();
		};
	}
	/**
	 * Metodo que añade las acciones a realizar en el proyecto. 
	 * 
	 * @param accionesARealizar, a realizar en el proyecto.
	 */
	public void addAccion(Accion accion) {
		this.accionesARealizar.add(accion);
	}
	 /**
     * Metodo accesor de lectura que nos dice la financiación de un proyecto.
     * 
     * @return la financiacion de un proyecto.
     */
	@XmlElement(name = "financiacion")
	public BigDecimal getFinanciacion() {
		return financiacion;
	}
	/**
	 * Metodo accesor de escritura que asigna a la financiación a un proyecto.
	 * 
	 * @param financiación, la financiación del proyecto.
	 */
	public void setFinanciacion(BigDecimal financiacion) {
		this.financiacion = financiacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	/**
     * Crea una cadena de caracteres con los datos principales del proyecto.
     * 
     * @return Cadena con los datos del proyecto.
     */
	 public String toString() {
		
			 return this.nombre + this.idProyecto + this.pais ;
	 }

}
