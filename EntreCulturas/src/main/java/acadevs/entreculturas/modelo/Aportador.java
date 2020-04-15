package acadevs.entreculturas.modelo;

import java.util.ArrayList;
import java.util.Date;

import acadevs.entreculturas.soporte.TipoAportador;

public class Aportador {
	private String idAportador;
	private TipoAportador tipoAportador;
	private ArrayList<AportacionPuntual> aportacionesHechas;
	
	public Aportador () {
		super();
	}
	public Aportador (String id, TipoAportador tipodeportador) {
		this.idAportador = id;
		this.tipoAportador = tipodeportador;
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
	public void setTipoAportador(TipoAportador tipodeaportador) {
		this.tipoAportador = tipodeaportador;
	}
	public void nuevaAportacion(String codAportacion, float importe, String moneda,
		Date fechaAportacion) {
		AportacionPuntual aportacion = new AportacionPuntual(codAportacion, this, importe, moneda, fechaAportacion);
		
		aportacionesHechas.add(aportacion);
	}
	
}
