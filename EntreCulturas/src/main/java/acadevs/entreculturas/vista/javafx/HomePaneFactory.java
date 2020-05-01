package acadevs.entreculturas.vista.javafx;

import javafx.scene.layout.Pane;

public class HomePaneFactory {

	public static Pane getAccesoAdmin() {
		return new HomeAdmin();
	}
	
	public static Pane getAccesoSocio() {
		return new HomeSocio();
	}
	
	public static Pane getVistaProyectos() {
		return new HomeProyectos();
	}
	
	public static Pane getCreditos(String tipo) { //info o salir
		return new HomeInfo(tipo);
	}
	
}
