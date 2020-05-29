package acadevs.entreculturas.vista.javafx;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLSocioDAO;
import acadevs.entreculturas.modelo.Socio;
import acadevs.entreculturas.util.Utilidad;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * Controlador de la clase home. Genera y maneja las acciones que se lleven a cabo en la interfaz gráfica y realiza las peticiones al modelo 
 * */
public class HomeController {

	Home menu; // la vista
	HomePaneFactory paneFactory; // el acceso al modelo de paneles
	StringBuffer txtInfo;
	HomeAdmin adminPane;
	HomeSocio socioPane;
	
	MySQLDAOFactory mysqlF;
	MySQLSocioDAO socios;
	
	/*
	 * Constructor del controlador. Activa los listeners de los botones laterales de la vista principal e inicia el programa con el panel info.
	 * @param menu recibe la vista con la que va a interactuar
	 * @param paneFactory recibe la factory de paneles detalle que aparecerán al pulsar botones del nav lateral
	 * */
	public HomeController(Home menu, HomePaneFactory paneFactory) {
		
		this.menu = menu;
		this.paneFactory = paneFactory;
		
		try {
			this.mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL");
		} catch (DAOException e) {
			System.out.println("Error al acceder a la base de datos");
			e.printStackTrace();
			App.salirDelPrograma(); // si existe algún error con la base de datos, cierra la aplicación de forma segura.
		}
		
		socios = mysqlF.getSocioDAO();
		
		menu.activaNavListeners(new NavHandler());
		
		menu.cambiaEscena((BorderPane) HomePaneFactory.getCreditos("info"));
	}
	
	/*
	 * clase manejadora de eventos del tipo ActionEvent.
	 * */
	public class NavHandler implements EventHandler<ActionEvent> {
	
		@Override
		public void handle(ActionEvent event) { //método obligatorio de EventHandler que define las acciones que se generan con la activación del evento.
			
			final Object source = event.getSource();// obtiene el disparador del evento. En este caso un botón.
			List<Button> botones = menu.getButtons();// recuperamos la lista de botones del navegador lateral
			int posicionBoton = botones.indexOf(source);// buscamos si existe el botón disparador en la lista de botones del menú. -1 no lo encuentra
			
			/*
			 * la posición del botón depende de la posición en la creación de la lista de botones.
			 * 1 panel administrador
			 * 2 panel socio
			 * 3 panel proyectos
			 * 4 panel info
			 * 0 salir de la aplicación de forma segura
			 * */
			if (posicionBoton != -1) {
				switch (posicionBoton) { 
					case 1: 
						adminPane = (HomeAdmin) HomePaneFactory.getAccesoAdmin();//pedimos a la factory de paneles el panel de acceso de admin
						adminPane.activaFormHandler(new AccesAdminHandler()); // agregamos el handler a los botones del panel administrador
						menu.cambiaEscena(adminPane); //solicita el cambio de contenido del centro del menú con el panel que hemos creado al inicio.
						break;
						
					case 2: 
						socioPane = (HomeSocio) HomePaneFactory.getAccesoSocio();
						socioPane.activaFormHandler(new AccesSocioHandler());
						menu.cambiaEscena(socioPane); 
						break;
						
					case 3: menu.cambiaEscena (HomePaneFactory.getVistaProyectos());
						break;
						
					case 4: menu.cambiaEscena (HomePaneFactory.getCreditos("salir")); // si es salir coloca un label de agradecimiento. (debido a la velocidad del proceso, no se muestra)
							App.salirDelPrograma();
						break;
						
					case 0:	menu.cambiaEscena (HomePaneFactory.getCreditos("info")); // si es info despliega la información de los creadores y el contexto de la aplicación
						break;
				}
			}
		}
	}
	
	//handler del panel de acceso de administrador
	public class AccesAdminHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			//si el botón pulsado es btnEntrar, comprueba datos y genera nueva ventana. Si no (limpiar) limpia los datos de los textboxes.
			if (event.getSource().equals(adminPane.getBtnEntrar())) {
				/*
				 * Debido a que se trata de un programa ficticio, el acceso de usuario no existe. En todo caso, aquí aparecería la lógica de
				 * verificación.*/
				try {
					Parent menuAdmin = FXMLLoader.load(getClass().getResource("menuAdmin.fxml"));
					Scene adminScene = new Scene(menuAdmin);
					Stage stage = new Stage(StageStyle.UNDECORATED);
					stage.setTitle("Ventana diseñada y generada con FXML");
					stage.setScene(adminScene);
					stage.showAndWait();
					
				} catch (IOException e) {
					System.out.println("No se encuentra el archivo FXML de administrador");
					e.printStackTrace();
				}
			} else {
				adminPane.setTxtUsuario("");
				adminPane.setTxtPassword("");
			}
		}
		
	}

	//handler de los botones del panel de acceso de socio
	public class AccesSocioHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			
			// si el botón pulsado es btnEntrar comprueba los datos ingresados y genera nueva ventana de socio. Si no (limpiar) limpia el contenido de los txtboxes.
			if (event.getSource().equals(socioPane.getBtnEntrar())) {
				
				String id = socioPane.getTxtUsuario();
				
				if (Utilidad.validarNIF(id)) { // comprobamos que el dni introducido es correcto
					
					Socio socio = null;
					try {
						socio = socios.obtener(id); // buscamos socio en la base de datos mediante el MysqlSocioDAO.obtener(dni) si no existe devuelve null
						
					} catch (DAOException e) {
						socioPane.msgError("Error al acceder a la base de datos", true); //muestra mensaje de error en pantalla
						e.printStackTrace();
					}
					
					if (socio == null) {
						String msgError = "El DNI introducido no es correcto \n o no se encuentra en la base de datos";
						// en este punto, el dni es necesariamente correcto pero no damos la información clara al usuario por seguridad ante ataques de bots.
						socioPane.msgError(msgError, true); // mensaje de error. True se muestra.
						solicitaCrearSocio();//
					} else {
						socioPane.setTxtUsuario("¡Enhorabuena!");//si existe el usuario se abre la pantalla de opciones de socio. Este mensaje es provisional
						socioPane.msgError(null, false);
						//En este punto el objeto socio ha obtenido los datos de socio de la base de datos y con ello podemos acceder al menu de socio
					}
					
				} else {// el dni no es correcto
					String msgError = "El DNI introducido no es correcto \n o no se encuentra en la base de datos";
					socioPane.msgError(msgError, true);
				}
				
			} else { // botón pulsado es limpiar.
				socioPane.setTxtUsuario("");
				socioPane.msgError(null, false);
			}
			
		}
		
		// abre ventana emergente para crear un nuevo socio. Si la respuesta es si - acceso a formulario
		public void solicitaCrearSocio() {
			
			Alert alert = new Alert(AlertType.CONFIRMATION); //ventana de tipo confirmación OK, Cancel
			alert.setTitle("Crear NUEVO SOCIO");
			alert.setHeaderText("¿Desea crear un nuevo socio?");
			
			Optional<ButtonType> resultado = alert.showAndWait();// bloquea la ventana host hasta que se cierre la ventana emergente.
			
			if (resultado.get() == ButtonType.OK){
			    /*
			     * Aquí crearíamos el acceso al formulario con opción de guardar nuevo usuario
			     * */
			}
		}
	}
}