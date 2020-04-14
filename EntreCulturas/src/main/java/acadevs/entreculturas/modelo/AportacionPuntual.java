package acadevs.entreculturas.modelo;

import java.util.Date;

public class AportacionPuntual extends AportacionParticular {

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
