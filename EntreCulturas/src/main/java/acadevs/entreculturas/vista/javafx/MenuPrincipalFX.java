package acadevs.entreculturas.vista.javafx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import acadevs.entreculturas.vista.consola.MenuPrincipal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MenuPrincipalFX extends Application {
	
	private BorderPane centro = new BorderPane();
	
	@Override 
	public void start(Stage stage) throws FileNotFoundException {
	
		/* El menú principal contará con una cabecera para el título, una barra lateral con una botonera que permite que se exploren
		 * las diferentes opciones dentro del menú y una sección central con las interfaces necesarias para realizar las diferentes acciones.*/

		Label lblTitulo = new Label("  Aplicación de gestión para EntreCulturas ONG");
		Label logotipo = new Label("ACADevelopers");
		logotipo.setStyle("-fx-text-fill: white;");
		logotipo.setPrefSize(300,80);
		logotipo.setAlignment(Pos.CENTER);
		logotipo.setBackground(new Background(new BackgroundFill(Color.rgb(44,14,73), CornerRadii.EMPTY, Insets.EMPTY)));
		Region blanc = new Region();
		HBox.setHgrow(blanc, Priority.ALWAYS); //hace que lblTitulo se sitúe en la izquierda
		HBox titulo = new HBox(logotipo,lblTitulo, blanc);
		titulo.alignmentProperty().set(Pos.CENTER);
		titulo.setPrefHeight(80);
		//titulo.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Button btnAdmin = new Button("Administrador");
		btnAdmin.setPrefSize(Double.MAX_VALUE, 50);
		Button btnSocio = new Button("Socio");
		btnSocio.setPrefSize(Double.MAX_VALUE, 50);
		Button btnProyectos = new Button("Ver Proyectos");
		btnProyectos.setPrefSize(Double.MAX_VALUE, 50);
		Button btnCreditos = new Button("Información");
		btnCreditos.setPrefSize(Double.MAX_VALUE, 50);
		VBox navLateral = new VBox(btnAdmin,btnSocio,btnProyectos,btnCreditos);
		navLateral.setPadding(new Insets(50,0,0,0));
		navLateral.setPrefSize(300,600);
		navLateral.setBackground(new Background(new BackgroundFill(Color.rgb(84,26,140), CornerRadii.EMPTY, Insets.EMPTY)));
	
		
		Label proyecto = new Label ("  Proyecto : ICB05 - Programación Orientada a Objetos con acceso a Bases de Datos");
		proyecto.setStyle("-fx-text-fill: silver;");
		Region espacio = new Region ();
		HBox.setHgrow(espacio, Priority.ALWAYS);
		Label repo = new Label ("Repositorio Oficial: ");
		repo.setStyle("-fx-text-fill: silver;");
		Label github = new Label();
		github.setText("http://github.com/agongardev/ACADevelopers");
		github.setStyle("-fx-text-fill: silver;");
		HBox barraContacto = new HBox(5, proyecto, espacio, repo, github);
		barraContacto.setPadding(new Insets(10,10,10,10));
		barraContacto.setBackground(new Background(new BackgroundFill(Color.rgb(44,14,73), CornerRadii.EMPTY, Insets.EMPTY)));
		

		
		Label lblTituloMenuAdmin = new Label("Acceder como Administrador");
		lblTituloMenuAdmin.setPrefSize(Double.MAX_VALUE, 80);
		lblTituloMenuAdmin.setAlignment(Pos.CENTER);
		lblTituloMenuAdmin.setStyle("-fx-text-fill: white;");
		lblTituloMenuAdmin.setBackground(new Background(new BackgroundFill(Color.rgb(84,26,140), CornerRadii.EMPTY, Insets.EMPTY)));
		
		Text nombreAdmin = new Text("Usuario");
		Text password = new Text("Contraseña");
		TextField txtUsuario = new TextField();
		PasswordField txtPassword = new PasswordField();
		Button btnEntrar = new Button("Entrar");
		Button btnLimpiar = new Button("Limpiar");

		Text infoAdmin = new Text("Introduzca un nombre y una contraseña para acceder al menú de administrador.\nDesde este menú podrá "
				+ "gestionar proyectos, socios y otras acciones exclusivas de este perfil.");
		infoAdmin.setWrappingWidth(300);
		
		GridPane datos = new GridPane();
		datos.add(nombreAdmin, 0, 0);
		datos.add(password, 0, 1);
		datos.add(txtUsuario, 1, 0);
		datos.add(txtPassword, 1, 1);
		datos.add(btnEntrar, 0, 2);
		datos.add(btnLimpiar, 1, 2);
		datos.add(infoAdmin, 0, 4);
		GridPane.setColumnSpan(infoAdmin, 4);
		datos.setVgap(10);
		datos.setHgap(10);
		datos.setMinSize(400, 200);
		datos.setPadding(new Insets(10, 10, 10, 10));
		datos.setAlignment(Pos.CENTER);
		
		
		Image logoONGF = new Image(new FileInputStream("E:\\DAM-UOC\\Proyectos\\GitHub\\ACADevelopers\\EntreCulturas\\img\\logo_entreculturas.png"));
		ImageView logoONG = new ImageView(logoONGF);
		logoONG.setFitWidth(195);
		logoONG.setFitHeight(47.4);
		Region margen = new Region();
		HBox.setHgrow(margen, Priority.ALWAYS);
		Image logoFP = new Image(new FileInputStream("E:\\DAM-UOC\\Proyectos\\GitHub\\ACADevelopers\\EntreCulturas\\img\\logoFP.png"));
		ImageView logoFPFrame = new ImageView(logoFP);
		logoFPFrame.setFitWidth(262.2);
		logoFPFrame.setFitHeight(64);
		HBox uocRef = new HBox(logoONG, margen, logoFPFrame);
		uocRef.setMargin(logoFPFrame, new Insets(0,5,5,0));
		

		BorderPane centro = new BorderPane();
		centro.setTop(lblTituloMenuAdmin);
		centro.setCenter(datos);
		centro.setBottom(uocRef);
		
		BorderPane ordenEscena = new BorderPane(); 
		ordenEscena.setTop(titulo);
		ordenEscena.setCenter(centro);
		ordenEscena.setBottom(barraContacto);
		ordenEscena.setLeft(navLateral);
		
		Scene scene = new Scene(ordenEscena, 1080, 600);
		stage.setTitle("Aplicación de gestión para EntreCulturas ONG          - ACADevelopers -");
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();		
	}
	
	private void btnAdminClick (ActionEvent click) {
		
	}

	public static void main (String[] args) {
		launch(args);
	}
	
}
