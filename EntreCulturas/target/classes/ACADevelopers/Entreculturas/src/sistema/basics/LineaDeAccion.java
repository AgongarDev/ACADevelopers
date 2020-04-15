package sistema.basics;

public enum LineaDeAccion {
	COOP("Cooperación al desarrollo"), 
	ACC("Acción Humanitaria"),
	FORT("Fortalecimiento institucional"), 
	EDU("Educación para el desarrollo");
	
	private final String texto;
	
	LineaDeAccion(String texto) {
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
