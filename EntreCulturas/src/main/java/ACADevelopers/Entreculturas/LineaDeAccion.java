package ACADevelopers.Entreculturas;

public enum LineaDeAccion {
	COOP("Cooperaci�n al desarrollo"), 
	ACC("Acci�n Humanitaria"),
	FORT("Fortalecimiento institucional"), 
	EDU("Educaci�n para el desarrollo");
	private final String texto;
	private LineaDeAccion laccion;
	LineaDeAccion (String texto) {
	this.texto = texto;
	}
	public void setEnumValue (LineaDeAccion nuevalaccion) {
	this.laccion = nuevalaccion; 
	}
	public String getTexto() {
	return texto;
	}	
	@Override
	public String toString() {
	return this.texto;
	}
	}

