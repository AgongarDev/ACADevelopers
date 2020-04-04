package sistema.basics;

import java.util.ArrayList;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Aportador {
	private String idAportador;
	private TipoAportador tipoAportador;
	private ArrayList<AportacionPuntual> aportacionesHechas;
	
	public Aportador () {
		super();
	}
	public Aportador (String id, String tipo) {
		this.idAportador = id;
		this.tipoAportador = TipoAportador.valueOf(tipo);
	}
	public String getIdAportador() {
		return idAportador;
	}
	public void setIdAportador(String idAportador) {
		this.idAportador = idAportador;
	}
	public String getTipoAportador() {
		return tipoAportador.getTexto();
	}
	public void setTipoAportador(String tipoAportador) {
		this.tipoAportador = TipoAportador.valueOf(tipoAportador);
	}
	public void nuevaAportacion(String codAportacion, float importe, String moneda,
			Date fechaAportacion) {
		AportacionPuntual aportacion = new AportacionPuntual(codAportacion, this, importe, moneda, fechaAportacion);
		
		aportacionesHechas.add(aportacion);
	}
	
}
