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
 * @author Ana Iglesias 
 * @author Antonio González
 *
 *	Clase que recoge la estructura principal de la escena principal de la aplicación.
 */
public class Home {
	
	//items de la aplicación home o principal
	@SuppressWarnings("unused")
	private HomeController controlador;
	private BorderPane ordenEscena, centro;
	private Button btnAdmin, btnSocio, btnCreditos, btnProyectos, btnSalir;
	private HBox titulo, barraContacto;
	private VBox navLateral;
	private HomePaneFactory paneFactory;
	
	/*
	 * Constructor de la escena y lanzador del controlador que maneja los eventos de Home
	 * */
	public Home () {
		
		cargaCabecera();
		
		cargaPie();
		
		cargaNavLateral();
		
		setEscena();
		/* 
		 * pasamos el home o la escena y la factory de paneles detalle al controlador 
		 */
		this.controlador = new HomeController(this, paneFactory);
	}
	
	/*
	 * devuelve el panel base para poder instanciarlo desde App
	 * */
	public Parent asParent() {
		return ordenEscena; 
	
	}
	
	/*
	 * asigna el handler del parámetro a cada uno de los botones de la barra de menú lateral. Utilizamos OnAction por su versatilidad ya que se activa 
	 * en cualquier momento que el botón es activado, ya sea con un click de ratón, un press de pantalla, un espacio o un enter.
	 * */
	public void activaNavListeners(EventHandler<ActionEvent> handler) {
		
		List<Button> botones = getButtons();
		
		for (Button elem : botones ) {
			elem.setOnAction(handler);
		}
	}
	
	/*
	 * crea una lista de botones para ser manejada desde fuera. El objeto de esta función es centralizar la lista de botones para mejorar su manejabilidad y control.
	 * */
	public List<Button> getButtons() {
		
		List<Button> botones = new ArrayList<>();
		
		botones.addAll(Arrays.asList(new Button[] { btnCreditos, btnAdmin, btnSocio, btnProyectos, btnSalir }));
		
		return botones;
		
	}
	
	/*
	 * asigna los items a los segmentos del boderpane estructural.
	 * */
	public void setEscena() {
		
		this.ordenEscena = new BorderPane(); 
		this.ordenEscena.setTop(titulo);
		this.ordenEscena.setCenter(centro);
		this.ordenEscena.setBottom(barraContacto);
		this.ordenEscena.setLeft(navLateral);
	}
	
	/*
	 * Crea el HBox de la cabecera de la ventana.
	 * HBox distribuye los elementos de forma equidistante.
	 * */
	public void cargaCabecera () {
		
		Label lblTitulo = new Label("  Aplicación de gestión para EntreCulturas ONG");
		Label logotipo = new Label("ACADevelopers");
		logotipo.setId("logo-aca"); // asignamos un id para ser accedido desde la hoja de estilos.
		
		Image logoFP = new Image(Home.class.getResourceAsStream("/acadevs/entreculturas/recursos/img/logoFP.png")); //carga la imagen con una ruta relativa a la clase home.
		ImageView logoFPFrame = new ImageView(logoFP); // recurso de JavaFX que permite actuar sobre una imagen (frame)
		logoFPFrame.setId("logo-fp");
		logoFPFrame.setFitWidth(262.2);
		logoFPFrame.setFitHeight(64);
		
		Region blanc = new Region();
		HBox.setHgrow(blanc, Priority.ALWAYS); //creamos una región en blanco que hace que lblTitulo se sitúe en la izquierda.
		
		titulo = new HBox(logotipo, lblTitulo, blanc, logoFPFrame);//creamos el recipiente con los items creados.
		titulo.setId("intro-app");
	}
	
	/*
	 * Crea el HBox del pie de la ventana.
	 * HBox distribuye los elementos de forma equidistante.
	 * */
	public void cargaPie() {
		
		Label proyecto = new Label ("  Proyecto : ICB05 - Programación Orientada a Objetos con acceso a Bases de Datos");
		
		Region espacio = new Region ();
		HBox.setHgrow(espacio, Priority.ALWAYS);
		
		Label repo = new Label ("Repositorio Oficial: ");
		Label github = new Label("http://github.com/agongardev/ACADevelopers");
		
		barraContacto = new HBox(5, proyecto, espacio, repo, github);
		barraContacto.setId("barra-contacto");
	}
	
	/*
	 * Crea el VBox de la cabecera de la ventana.
	 * VBox distribuye los elementos de forma equidistante.
	 * */
	public void cargaNavLateral() {

		btnAdmin = new Button("Administrador");
		btnSocio = new Button("Socio");
		btnProyectos = new Button("Ver Proyectos");
		btnCreditos = new Button("Información");
		
		Region region = new Region();
		VBox.setVgrow(region, Priority.ALWAYS);
		
		btnSalir = new Button("Salir de la aplicación");
		
		navLateral = new VBox(btnAdmin,btnSocio,btnProyectos,btnCreditos, region, btnSalir);
		navLateral.getStyleClass().add("vbox");//en lugar de un id hacemos que el Vbox utilice la clase .vbox del css
	}
	
	/*
	 * Cambia el contenido del centro del panel por el nuevo pane del parámetro
	 * */
	public void cambiaEscena (Pane nuevoContenido) {
		this.ordenEscena.setCenter(nuevoContenido);
	}
	
}
