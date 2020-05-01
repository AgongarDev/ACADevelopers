package acadevs.entreculturas.modelo;

import java.util.Date;
import java.io.Serializable;

public class AportacionPuntual extends AportacionParticular implements Serializable{

	private Aportador aportador;
	
	public AportacionPuntual () {
		super();
	}

	public AportacionPuntual(String codAportacion, Aportador aportador, float importe, String moneda,
			Date fechaAportacion) {
		super(codAportacion, importe, moneda, fechaAportacion);
		this.aportador = aportador;
	}

	public Aportador getAportador() {
		return aportador;
	}

	public void setAportador(Aportador aportador) {
		this.aportador = aportador;
	}
	
}
