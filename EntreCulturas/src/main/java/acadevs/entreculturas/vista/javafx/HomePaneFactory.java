package acadevs.entreculturas.vista.javafx;

import javafx.scene.layout.Pane;

//factory de paneles detalle para situar en el center del borderPane inicial.
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
