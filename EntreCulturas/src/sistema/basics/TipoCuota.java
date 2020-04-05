package sistema.basics;

public enum TipoCuota {
	TRIM("Trimestral"), 
	MES("Mensual"), 
	ANUAL("Anual");

	private final String texto;
	
	TipoCuota (String texto) {
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}
	
	@Override
	public String toString() {
		return this.texto;
		
	}
}
