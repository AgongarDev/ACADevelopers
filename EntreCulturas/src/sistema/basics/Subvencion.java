package sistema.basics;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Subvencion {

	private String idSubvencion;
	private String OrganismoConcesion;
	private Date fechaInicioConcesion;
	private Date fechaFinConcesion;
	private float importe;
	private String moneda;
	private TipoSubvencion tipoSubvencion;
	
	
	//constructores de clase
	
	public Subvencion () {
		super();
	}
	public Subvencion (String id, String org, Date fechaInicio, Date fechaFin, float importe, String moneda, String tipo) {
		this.idSubvencion = id;
		this.OrganismoConcesion = org;
		this.fechaInicioConcesion = fechaInicio;
		this.fechaFinConcesion = fechaFin;
		this.importe = importe;
		this.moneda = moneda;
		this.tipoSubvencion = TipoSubvencion.valueOf(tipo);
	}
	
	// getters y setters
	
	public String getIdSubvencion() {
		return idSubvencion;
	}
	public void setIdSubvencion(String idSubvencion) {
		this.idSubvencion = idSubvencion;
	}
	public String getOrganismoConcesion() {
		return OrganismoConcesion;
	}
	public void setOrganismoConcesion(String organismoConcesion) {
		OrganismoConcesion = organismoConcesion;
	}
	public Date getFechaInicioConcesion() {
		return fechaInicioConcesion;
	}
	public void setFechaInicioConcesion(Date fechaInicioConcesion) {
		this.fechaInicioConcesion = fechaInicioConcesion;
	}
	public Date getFechaFinConcesion() {
		return fechaFinConcesion;
	}
	public void setFechaFinConcesion(Date fechaFinConcesion) {
		this.fechaFinConcesion = fechaFinConcesion;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getTipoSubvencion() {
		return tipoSubvencion.getTexto();
	}
	public void setTipoSubvencion(String tipo) {
		this.tipoSubvencion = TipoSubvencion.valueOf(tipo);
	}
	
/*
 * @Override
 * public String toString(){
 * return 
 * }
 * */
	
}
