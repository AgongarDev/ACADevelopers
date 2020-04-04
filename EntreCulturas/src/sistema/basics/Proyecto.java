package sistema.basics;

import java.util.ArrayList;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Proyecto {

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
	//estas variables se cebarán de manera independiente.
	private ArrayList<Accion> accionesARealizar = new ArrayList<Accion>();
	private float financiacion = 0;
	private ArrayList<Voluntario> voluntariosAsignados = new ArrayList<Voluntario>();
	private ArrayList<Trabajador> contratadosAsignados = new ArrayList<Trabajador>();
	


	public Proyecto () {
		super();
	}
	
	public Proyecto (String id, String nombre, String lAccion, Date fechaInicio, Date fechaFin, String subLineaDeAccion, String pais, String direccion, Colaborador socioLocal, Aportador financiador) {
		super();
		this.idProyecto = id;
		this.nombre = nombre;
		this.lAccion = LineaDeAccion.valueOf(lAccion);
		this.fechaInicio = fechaInicio;
		this.fechaFinalizacion = fechaFin;
		this.sublineaDeAccion = subLineaDeAccion;
		this.pais = pais;
		this.direccion = direccion;
		this.socioLocal = socioLocal;
		this.financiador = financiador;
	}
	

	//Getters y Setters
	
	public String getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getlAccion() {
		return lAccion.getTexto();
	}

	public void setlAccion(LineaDeAccion lAccion) {
		this.lAccion = LineaDeAccion.valueOf(lAccion);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public String getSublineaDeAccion() {
		return sublineaDeAccion;
	}

	public void setSublineaDeAccion(String sublineaDeAccion) {
		this.sublineaDeAccion = sublineaDeAccion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Colaborador getSocioLocal() {
		return socioLocal;
	}

	public void setSocioLocal(Colaborador socioLocal) {
		this.socioLocal = socioLocal;
	}

	public Aportador getFinanciador() {
		return financiador;
	}

	public void setFinanciador(Aportador financiador) {
		this.financiador = financiador;
	}

	public void listAccionesARealizar() {
		for (Accion elem : accionesARealizar) {
			elem.toString();
		};
	}

	public void addAccion(Accion accion) {
		this.accionesARealizar.add(accion);
	}

	public float getFinanciacion() {
		return financiacion;
	}

	public void setFinanciacion(float financiacion) {
		this.financiacion = financiacion;
	}

	public void listVoluntariosAsignados() {
		for (Voluntario elem : voluntariosAsignados) {
			elem.toString();
		}
	}

	public void addVoluntariosAsignados(Voluntario voluntario) {
		this.voluntariosAsignados.add(voluntario);
	}

	public void listContratadosAsignados() {
		for (Trabajador elem : contratadosAsignados) {
			elem.toString();
		}
	}

	public void addContratado(Trabajador trabajador) {
		this.contratadosAsignados.add(trabajador);
	}
	
	
	/*
	 * @param string introducimos un string con la lineaDeAccion seleccionada.
	 * */
@Override
public String toString(){
   	/*
	StringBuilder str = new StringBuilder();
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY");
    String landingDate = formatDate.format(this.flightdate.getTime());
    
    //    str = str.append(ID + ": " + this.id + NL);
    */
    return str.toString();
}
}
