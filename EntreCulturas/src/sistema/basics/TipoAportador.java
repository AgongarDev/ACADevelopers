package sistema.basics;

public enum TipoAportador {
	INST("Institución"), 
	PART("Particular"), 
	EMPR("Empresas"),
	HEREN("Herencias y Legados");

	private final String texto;
	
	TipoAportador (String texto) {
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
