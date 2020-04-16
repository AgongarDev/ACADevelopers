package ACADevelopers.Entreculturas;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa cada uno de los proyectos que forman la ONG
 * y que involucran a personal contratado y voluntarios.
 * 
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
@XmlRootElement(name = "proyecto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(propOrder={"idProyecto", "nombre", "direccion","pais", "socioLocal","financiador","financiacion", "fechaInicio","fechaFinalizacion","lAccion","sublineaDeAccion","accionesARealizar","voluntariosAsignados","contratadosAsignados"})
public class Proyecto {
	// CAMPOS
	private String idProyecto;
	private LineaDeAccion lAccion;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFinalizacion;
	private String sublineaDeAccion;
	private String pais;
	private String direccion;
	private Colaborador socioLocal;
	private Aportador financiador; 
	//estas variables se cebar�n de manera independiente.
	private ArrayList<Accion> accionesARealizar = new ArrayList<Accion>();
	private float financiacion = 0;
	private ArrayList<Voluntario> voluntariosAsignados = new ArrayList<Voluntario>();
	private ArrayList<Administrador> contratadosAsignados = new ArrayList<Administrador>();
	
	// CONSTRUCTORES
	
		/**
		 * Constructor que crea un nuevo objeto Proyecto sin iniciar sus campos.
		 */

	public Proyecto () {
		super();
	}
	/**
	 * Constructor que crea un nuevo objeto Delegacion inicializando sus campos.
	 * 
	 * @param idProyecto Atributo que guarda el nombre del proyecto.
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
	public Proyecto (String id, String nombre, LineaDeAccion lAccion, Date fechaInicio,Date fechaFin, String subLineaDeAccion, String pais, String direccion, Colaborador socioLocal, Aportador financiador) {
		super();
		this.idProyecto = id;
		this.nombre = nombre;
		this.lAccion = lAccion;
		this.fechaInicio = fechaInicio;
		this.fechaFinalizacion = fechaFin;
		this.sublineaDeAccion = subLineaDeAccion;
		this.pais = pais;
		this.direccion = direccion;
		this.socioLocal = socioLocal;
		this.financiador = financiador;
	}
	

	//Getters y Setters
	/**
	 * Metodo accesor de lectura que nos da el ID de la Delegación.
	 * 
	 * @return nos devuelve el id del proyecto.	 
	 *
	 */
	@XmlElement(name = "idProyecto")
	public String getIdProyecto() {
		return idProyecto;
	}

	/**
	 * Metodo accesor de escritura que asigna el id del proyecto.
	 * 
	 * @param idProyecto id del proyecto.
	 */
	public void setIdProyecto(String idProyecto) {
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
	public void setlAccion(String lAccion) {
		this.lAccion = LineaDeAccion.valueOf(lAccion);
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
	/**
	 * Metodo accesor de lectura que nos da el socio local del Proyecto.
	 * 
	 * @return nos devuelve el socio local del proyecto.	 
	 *
	 */
	@XmlElement(name = "socioLocal")
	public Colaborador getSocioLocal() {
		return socioLocal;
	}
	/**
	 * Metodo accesor de escritura que asigna un Colaborador como socio local.
	 * 
	 * @param socioLocal  socio local del proyecto.
	 */
	public void setSocioLocal(Colaborador socioLocal) {
		this.socioLocal = socioLocal;
	}
	/**
	 * Metodo accesor de lectura que nos da el financiador del Proyecto.
	 * 
	 * @return nos devuelve el financiador del proyecto.	 
	 *
	 */
	@XmlElement(name = "financiador")
	public Aportador getFinanciador() {
		return financiador;
	}
	/**
	 * Metodo accesor de escritura que asigna un Aportador como financiador.
	 * 
	 * @param socioLocal  financiador del proyecto.
	 */
	public void setFinanciador(Aportador financiador) {
		this.financiador = financiador;
	}
	/**
	 * Metodo que lista las acciones a realizar en el proyecto. 
	 * 
	 * @param acciones a realizar lista de acciones.
	 */
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
	public float getFinanciacion() {
		return financiacion;
	}
	/**
	 * Metodo accesor de escritura que asigna a la financiación a un proyecto.
	 * 
	 * @param financiación, la financiación del proyecto.
	 */
	public void setFinanciacion(float financiacion) {
		this.financiacion = financiacion;
	}
	/**
	 * Metodo que lista los voluntarios asignados al proyecto. 
	 * 
	 * @param listVoluntariosAsignador, lista de voluntarios asignados al proyecto.
	 */
	@XmlElement(name = "voluntariosAsignados")
	public void listVoluntariosAsignados() {
		for (Voluntario elem : voluntariosAsignados) {
			elem.toString();
		}
	}
	/**
	 * Metodo que añade los voluntarios asignados al proyecto. 
	 * 
	 * @param voluntariosAsignados,lista de voluntarios asignados al proyecto.
	 */
	public void addVoluntariosAsignados(Voluntario voluntario) {
		this.voluntariosAsignados.add(voluntario);
	}
	/**
	 * Metodo que lista los trabajadores asignados al proyecto. 
	 * 
	 * @param listtrabajadores, lista de trabajadores contratados asignados al proyecto.
	 */
	@XmlElement(name = "contratadosAsignados")
	public void listContratadosAsignados() {
		for (Administrador elem : contratadosAsignados) {
			elem.toString();
		}
	}
	/**
	 * Metodo que añade los trabajadores contratados asignados al proyecto. 
	 * 
	 * @param voluntariosAsignados,lista de trabajadores contratados asignados al proyecto.
	 */
	public void addContratado(Administrador trabajador) {
		this.contratadosAsignados.add(trabajador);
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
