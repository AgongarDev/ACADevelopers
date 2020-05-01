package acadevs.entreculturas.vista.javafx;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

public class HomeController {

	Home menu; // la vista
	HomePaneFactory paneFactory; // el acceso al modelo de paneles
	StringBuffer txtInfo;
	HomeAdmin adminPane;
	HomeSocio socioPane;
	
	MySQLDAOFactory mysqlF;
	MySQLSocioDAO socios;
	
	public HomeController(Home menu, HomePaneFactory paneFactory) {
		
		this.menu = menu;
		this.paneFactory = paneFactory;
		
		try {
			this.mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL");
		} catch (DAOException e) {
			System.out.println("Error al acceder a la base de datos");
			e.printStackTrace();
			App.salirDelPrograma();
		}
		
		socios = mysqlF.getSocioDAO();
		
		menu.activaNavListeners(new NavHandler());
		
		menu.cambiaEscena((BorderPane) HomePaneFactory.getCreditos("info"));
	}
	
	public class NavHandler implements EventHandler<ActionEvent> {
	
		@Override
		public void handle(ActionEvent event) {
			
			final Object source = event.getSource();
			List<Button> botones = menu.getButtons();
			int posicionBoton = botones.indexOf(source);
			
			if (posicionBoton != -1) {
				switch (posicionBoton) {
					case 1: 
						adminPane = (HomeAdmin) HomePaneFactory.getAccesoAdmin();
						adminPane.activaFormHandler(new AccesAdminHandler());
						menu.cambiaEscena(adminPane);
						break;
						
					case 2: 
						socioPane = (HomeSocio) HomePaneFactory.getAccesoSocio();
						socioPane.activaFormHandler(new AccesSocioHandler());
						menu.cambiaEscena(socioPane); 
						break;
						
					case 3: menu.cambiaEscena (HomePaneFactory.getVistaProyectos());
						break;
						
					case 4: menu.cambiaEscena (HomePaneFactory.getCreditos("salir"));
							App.salirDelPrograma();
						break;
						
					case 0:	menu.cambiaEscena (HomePaneFactory.getCreditos("info"));
						break;
				}
			}
		}
	}
	
	public class AccesAdminHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			if (event.getSource().equals(adminPane.getBtnEntrar())) {
				/*
				 * Debido a que se trata de un programa ficticio, el acceso de usuario no existe. En todo caso, aquí aparecería la lógica de
				 * verificación.
				 * Aquí vendrá la llamada al menú de administrador
				 * */
			} else {
				adminPane.setTxtUsuario("");
				adminPane.setTxtPassword("");
			}
			
			
			
		}
		
	}

	public class AccesSocioHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			
			if (event.getSource().equals(socioPane.getBtnEntrar())) {
				
				String id = socioPane.getTxtUsuario();
				
				if (Utilidad.validarNIF(id)) {
					
					Socio socio = null;
					try {
						socio = socios.obtener(id);
						
					} catch (DAOException e) {
						socioPane.msgError("Error al acceder a la base de datos", true);
						e.printStackTrace();
					}
					
					if (socio == null) {
						String msgError = "El DNI introducido no es correcto \n o no se encuentra en la base de datos";
						socioPane.msgError(msgError, true);
						solicitaCrearSocio();
					} else {
						socioPane.setTxtUsuario("¡Enhorabuena!");
						socioPane.msgError(null, false);
						/*
						 * En este punto el objeto socio ha obtenido los datos de socio de la base de datos y con ello podemos acceder al menu
						 * de socio
						 * */
					}
					
				} else {
					String msgError = "El DNI introducido no es correcto \n o no se encuentra en la base de datos";
					socioPane.msgError(msgError, true);
				}
				
			} else {
				socioPane.setTxtUsuario("");
				socioPane.msgError(null, false);
			}
			
		}
		
		public void solicitaCrearSocio() {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Crear NUEVO SOCIO");
			alert.setHeaderText("¿Desea crear un nuevo socio?");
			
			Optional<ButtonType> resultado = alert.showAndWait();
			if (resultado.get() == ButtonType.OK){
			    /*
			     * Aquí crearíamos el acceso al formulario con opción de guardar nuevo usuario
			     * */
			} else {
			    
			}
		}
	}
}