package acadevs.entreculturas.vista.javafx;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class HomeSocio extends BorderPane {

	private Button btnEntrar; 
	private Button btnLimpiar;
	private TextField txtUsuario;
	private GridPane datos;

	public HomeSocio() {
		
		Label lblTituloMenuSocio = new Label("Acceder como Socio");
		lblTituloMenuSocio.setId("titulo-opcion");
	//DETALLE	
		Text dniSocio = new Text("DNI del Socio");
		txtUsuario = new TextField();
		btnEntrar = new Button("Entrar");
		btnLimpiar = new Button("Limpiar");

		Text infoText = new Text("Introduzca el DNI para acceder a su perfil de Socio o crear uno nuevo. Como socio, usted colabora a que EntreCulturas "
				+ "pueda continuar desarrollando su labor en todo el mundo y ayudando a los colectivos más necesitados, dentro y fuera de nuestro país. "
				+ "Además, en cualquier momento puede activar o desactivar su cuota, gestionar la cuantía y la periodicidad del abono. \n\n ¡Muchas gracias!");
		infoText.setWrappingWidth(300);
		
		datos = new GridPane();
		datos.add(dniSocio, 0, 1);
		datos.add(txtUsuario, 1, 1);
		datos.add(btnEntrar, 1, 2);
		datos.add(btnLimpiar, 2, 2);
		datos.add(infoText, 0, 4);
		GridPane.setColumnSpan(txtUsuario, 2);
		GridPane.setColumnSpan(infoText, 4);
		datos.getStyleClass().add("detalle");
		
		Region margen = new Region();
		HBox.setHgrow(margen, Priority.ALWAYS);
		Image logoONGF = new Image(Home.class.getResourceAsStream("/acadevs/entreculturas/recursos/img/logo_entreculturas.png"));
		ImageView logoONG = new ImageView(logoONGF);
		logoONG.setFitWidth(195);
		logoONG.setFitHeight(47.4);
		HBox ONGRef = new HBox(margen, logoONG);
		
		setId("centro");
		setTop(lblTituloMenuSocio);
		setCenter(datos);
		setBottom(ONGRef);
	}
	
	public void msgError(String msg, boolean muestra) {
		
		if (muestra) {
			Label lblError = new Label(msg);
			lblError.getStyleClass().add("error-msg");
			GridPane.setColumnSpan(lblError, 4);
			datos.add(lblError, 0, 0);
			
		} else {
			List<Node> elementos = datos.getChildren();
			if (elementos.size() > 4) {
				datos.getChildren().remove(elementos.size()-1);// el último nodo añadido a la lista de elementos observables
			}
		}
	}
	
	public void activaFormHandler (EventHandler<ActionEvent> event) {
		this.btnEntrar.setOnAction(event);
		this.btnLimpiar.setOnAction(event);
	}

	public String getTxtUsuario() {
		return txtUsuario.getText();
	}
	
	public void setTxtUsuario(String txtUsuario) {
		this.txtUsuario.setText(txtUsuario);
	}
	
	public Button getBtnEntrar() {
		return btnEntrar;
	}
	
	public Button getBtnLimpiar() {
		return btnLimpiar;
	}
}
