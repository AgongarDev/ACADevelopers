package ACADevelopers.Entreculturas;

public enum TipoCuota {
	TRIM("Trimestral"), 
	MES("Mensual"), 
	ANUAL("Anual");

	private final String texto;
	private TipoCuota cuota;
	
	TipoCuota (String texto) {
		this.texto = texto;
	}
	public void setEnumValue (TipoCuota nuevotipodecuota) {
		this.cuota = nuevotipodecuota; 
	}
	public String getTexto() {
		return texto;
	}
	
	@Override
	public String toString() {
		return this.texto;
		
	}
}
