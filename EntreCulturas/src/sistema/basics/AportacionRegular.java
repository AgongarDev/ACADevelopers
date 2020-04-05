package sistema.basics;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class AportacionRegular extends AportacionParticular{

	private Socio aportador;

	// constructores 
	
	public AportacionRegular() {
		super();
		
	}

	public AportacionRegular(String codAportacion, float importe, String moneda, Date fechaAportacion, Socio aportador) {
		super(codAportacion, importe, moneda, fechaAportacion);
		this.aportador = aportador;
		// podemos obtener todos los datos de los atributos que aparecen en el diagrama a través de la clase Socio.
	}

	public Socio getAportador() {
		return aportador;
	}

	public void setAportador(Socio aportador) {
		this.aportador = aportador;
	}
	

	
}
