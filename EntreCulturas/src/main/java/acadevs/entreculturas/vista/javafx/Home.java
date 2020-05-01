package acadevs.entreculturas.vista.javafx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * @author Aplicación creada por Ana Iglesias y Antonio González
 *
 */
public class Home {
	
	@SuppressWarnings("unused")
	private HomeController controlador;
	private BorderPane ordenEscena, centro;
	private Button btnAdmin, btnSocio, btnCreditos, btnProyectos, btnSalir;
	private HBox titulo, barraContacto;
	private VBox navLateral;
	private HomePaneFactory paneFactory;
	
	public Home () {
		
		cargaCabecera();
		
		cargaPie();
		
		cargaNavLateral();
		
		setEscena();
		
		this.controlador = new HomeController(this, paneFactory);
	}
	
	public Parent asParent() {
		return ordenEscena; 
	
	}
	
	public void activaNavListeners(EventHandler<ActionEvent> handler) {
		
		List<Button> botones = getButtons();
		
		for (Button elem : botones ) {
			elem.setOnAction(handler);
		}
	}
	
	public List<Button> getButtons() {
		
		List<Button> botones = new ArrayList<>();
		
		botones.addAll(Arrays.asList(new Button[] { btnCreditos, btnAdmin, btnSocio, btnProyectos, btnSalir }));
		
		return botones;
		
	}
	
	public void setEscena() {
		
		this.ordenEscena = new BorderPane(); 
		this.ordenEscena.setTop(titulo);
		this.ordenEscena.setCenter(centro);
		this.ordenEscena.setBottom(barraContacto);
		this.ordenEscena.setLeft(navLateral);
	}
	
	public void cargaCabecera () {
		
		Label lblTitulo = new Label("  Aplicación de gestión para EntreCulturas ONG");
		Label logotipo = new Label("ACADevelopers");
		logotipo.setId("logo-aca");
		
		Image logoFP = new Image(Home.class.getResourceAsStream("/acadevs/entreculturas/recursos/img/logoFP.png"));
		ImageView logoFPFrame = new ImageView(logoFP);
		logoFPFrame.setId("logo-fp");
		logoFPFrame.setFitWidth(262.2);
		logoFPFrame.setFitHeight(64);
		
		Region blanc = new Region();
		HBox.setHgrow(blanc, Priority.ALWAYS); //hace que lblTitulo se sitúe en la izquierda
		
		titulo = new HBox(logotipo, lblTitulo, blanc, logoFPFrame);
		titulo.setId("intro-app");
	}
	
	public void cargaPie() {
		
		Label proyecto = new Label ("  Proyecto : ICB05 - Programación Orientada a Objetos con acceso a Bases de Datos");
		
		Region espacio = new Region ();
		HBox.setHgrow(espacio, Priority.ALWAYS);
		
		Label repo = new Label ("Repositorio Oficial: ");
		Label github = new Label("http://github.com/agongardev/ACADevelopers");
		
		barraContacto = new HBox(5, proyecto, espacio, repo, github);
		barraContacto.setId("barra-contacto");
	}
	
	public void cargaNavLateral() {

		btnAdmin = new Button("Administrador");
		btnSocio = new Button("Socio");
		btnProyectos = new Button("Ver Proyectos");
		btnCreditos = new Button("Información");
		
		Region region = new Region();
		VBox.setVgrow(region, Priority.ALWAYS);
		
		btnSalir = new Button("Salir de la aplicación");
		
		navLateral = new VBox(btnAdmin,btnSocio,btnProyectos,btnCreditos, region, btnSalir);
		navLateral.getStyleClass().add("vbox");
	}
	
	public void cambiaEscena (Pane nuevoContenido) {
		this.ordenEscena.setCenter(nuevoContenido);
	}
	
}
