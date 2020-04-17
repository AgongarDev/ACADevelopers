package acadevs.entreculturas.enums;

public enum LineaDeAccion {
	COOP("Cooperaci�n al desarrollo"), 
	ACC("Acci�n Humanitaria"),
	FORT("Fortalecimiento institucional"), 
	EDU("Educaci�n para el desarrollo");
	
	private final String texto;
	
	LineaDeAccion (String texto) {
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

