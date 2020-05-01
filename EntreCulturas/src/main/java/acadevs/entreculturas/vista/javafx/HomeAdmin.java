package acadevs.entreculturas.vista.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class HomeAdmin extends BorderPane {

	private Button btnEntrar;
	private Button btnLimpiar;
	private TextField txtUsuario;
	private PasswordField txtPassword;
	
	public HomeAdmin() {
		
		Label lblTituloMenuAdmin = new Label("Acceder como Administrador");
		lblTituloMenuAdmin.setId("titulo-opcion");
	//DETALLE	
		Text nombreAdmin = new Text("Usuario");
		Text password = new Text("Contraseña");
		txtUsuario = new TextField();
		txtPassword = new PasswordField();
		this.btnEntrar = new Button("Entrar");
		this.btnLimpiar = new Button("Limpiar");

		Text infoText = new Text("Introduzca un nombre y una contraseña para acceder al menú de administrador.\nDesde este menú podrá "
				+ "gestionar proyectos, socios y otras acciones exclusivas de este perfil.");
		infoText.setWrappingWidth(300);
		
		GridPane datos = new GridPane();
		datos.add(nombreAdmin, 0, 0);
		datos.add(password, 0, 1);
		datos.add(txtUsuario, 1, 0);
		datos.add(txtPassword, 1, 1);
		datos.add(btnEntrar, 0, 2);
		datos.add(btnLimpiar, 1, 2);
		datos.add(infoText, 0, 4);
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
		setTop(lblTituloMenuAdmin);
		setCenter(datos);
		setBottom(ONGRef);
		
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
	
	public String getTxtPassword() {
		return txtPassword.getText();
	}
	
	public void setTxtPassword(String txtPassword) {
		this.txtPassword.setText(txtPassword);
	}
	
	public Button getBtnEntrar() {
		return btnEntrar;
	}
	
	public Button getBtnLimpiar() {
		return btnLimpiar;
	}
}
