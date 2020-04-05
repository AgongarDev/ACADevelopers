package sistema.basics;

public enum TipoSubvencion {
	
		LOC("Local"), 
		CA("Autonómica"), 
		UE("Unión Europea");
	
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