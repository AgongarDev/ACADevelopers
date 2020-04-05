package ACADevelopers.Entreculturas;

public enum TipoSubvencion {
	
		LOC("Local"), 
		CA("Auton�mica"), 
		UE("Uni�n Europea");
	
		private final String texto;
		
		TipoSubvencion (String texto) {
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