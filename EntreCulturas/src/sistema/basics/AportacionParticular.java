package sistema.basics;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class AportacionParticular {
	
	private String codAportacion;
	private float importe;
	private String moneda;
	private Date fechaAportacion;
	
	public AportacionParticular () {
		super();
	}
	
	public AportacionParticular(String codAportacion, float importe, String moneda,
			Date fechaAportacion) {
		super();
		this.codAportacion = codAportacion;
		this.importe = importe;
		this.moneda = moneda;
		this.fechaAportacion = fechaAportacion;
	}

	public String getCodAportacion() {
		return codAportacion;
	}

	public void setCodAportacion(String codAportacion) {
		this.codAportacion = codAportacion;
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

	public Date getFechaAportacion() {
		return fechaAportacion;
	}

	public void setFechaAportacion(Date fechaAportacion) {
		this.fechaAportacion = fechaAportacion;
	}
	

}
