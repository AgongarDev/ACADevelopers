package acadevs.entreculturas.vista.javafx;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLAdministracionFisicaDAO;
import acadevs.entreculturas.dao.mysql.MySQLDAOFactory;
import acadevs.entreculturas.dao.mysql.MySQLSocioDAO;
import acadevs.entreculturas.enums.TipoCuota;
import acadevs.entreculturas.modelo.AdministracionFisica;
import acadevs.entreculturas.modelo.Socio;
import acadevs.entreculturas.util.Utilidad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminSocioController implements Initializable {

	@FXML private TextField nombre, apellidos, dni, direccion, email, telefono, cargo;
	@FXML public Label lblID;
	@FXML private PasswordField password;
	@FXML private DatePicker fechaIni, fechaFin;
	@FXML private ComboBox<String> sede;
	@FXML private TextField importe;
	@FXML private ComboBox<TipoCuota> tipoCuota;
	@FXML private CheckBox activo;
	@FXML private HBox formButtonsPane;
	@FXML private GridPane formGridPane;
	
	@FXML private TableView<Socio> tbSocios;
	@FXML private TableColumn<Socio, String> cNombre, cApellidos, cTipoCuota;
	@FXML private TableColumn<Socio, Float> cImporte;
	@FXML private TableColumn<Socio, Boolean> cEstado;
	
	@FXML private Button btnLimpiar, btnEnviar, btnNuevo, btnEditar, btnBorrar;

	MySQLDAOFactory mysqlf;
	MySQLSocioDAO socios;
	MySQLAdministracionFisicaDAO administraciones;
	
	Boolean accionNuevo;// true = Insert Socio , false = Update Socio
	
	ObservableList<Socio> listaSocios = FXCollections.observableArrayList(); 
	ObservableList<String> listaSedes = FXCollections.observableArrayList();
	/*
	 * Acciones de refresco que suceden al iniciar la escena. 
	 * 	Aspectos visuales
	 *  Rellenado de datos en comboboxes y tabla
	 *  Conexiones y obtención de datos de la BD
	 * */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//condiciones visuales de consulta
		formButtonsPane.setVisible(false);
		fechaIni.setValue(LocalDate.now(ZoneId.systemDefault()));
		fechaFin.setValue(LocalDate.now(ZoneId.systemDefault()).plusYears(1));
	
		//Conexiones con MySQLDAOFactory
		try {
			this.mysqlf = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL");
		} catch (DAOException e) {
			System.out.println("Error al acceder a la base de datos");
			e.printStackTrace();
			App.salirDelPrograma(); // si existe algún error con la base de datos, cierra la aplicación de forma segura.
		}
		//Datos DAOs obtenidos de la conexión con el modelo.
		socios = mysqlf.getSocioDAO();
		administraciones = mysqlf.getAdministracionFisicaDAO();
		
		//Lista de Administraciones físicas de la BD con JDBC
		try {
			List<AdministracionFisica> sedesBD = administraciones.obtenerTodos();
			
			for (AdministracionFisica elem : sedesBD) {
				listaSedes.add(elem.getNombre());
			}
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		//Lista de Socios de la BD con JDBC
		//LLenamos los combobox
		sede.getItems().clear();
		sede.setItems(listaSedes);
		
		tipoCuota.getItems().clear();
		tipoCuota.getItems().addAll(TipoCuota.values());
		
		// construcción de la tabla de socios
		cNombre.setCellValueFactory(new PropertyValueFactory<Socio, String>("nombre"));
		cApellidos.setCellValueFactory(new PropertyValueFactory<Socio, String>("apellidos"));
		cImporte.setCellValueFactory(new PropertyValueFactory<Socio, Float>("cuotaAportacion"));
		cTipoCuota.setCellValueFactory(new PropertyValueFactory<Socio, String>("tipoCuota"));
		cEstado.setCellValueFactory(new PropertyValueFactory<Socio, Boolean>("estadoAportacion"));
		
		updateTb();
	}
	
	public void borraSocio(ActionEvent event) {
		Socio socio = tbSocios.getSelectionModel().getSelectedItem();
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setHeaderText("Está seguro que quiere eliminar el socio ");
		a.setContentText(socio.getNombre());
		
		ButtonType borrar = new ButtonType("Eliminar");
		ButtonType cancelar = new ButtonType("Cancelar");
		
		a.getButtonTypes().addAll(borrar, cancelar);
		
		Optional<ButtonType> respuesta = a.showAndWait();
		
		if (respuesta.get() == borrar) {
			try {
				socios.borrar(socio);
			} catch (DAOException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setContentText("Error al intentar borrar el socio de la BD");
				e.printStackTrace();
			}
		} else { 
			a.close(); 
		}
	}	
	
	public void editarSocio(ActionEvent event) {
		nombre.requestFocus();
		accionNuevo = false;
		formButtonsPane.setVisible(true);
	}
	
	private Socio guardaDatosSocio() {
		
		AdministracionFisica administracion = null;
		
		try {
			administracion = administraciones.obtener(sede.getValue());
		} catch (DAOException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Imposible hallar la administración física "+sede.getValue());
			e.printStackTrace();
		}
		
		Socio socio = new Socio(dni.getText(), 
				nombre.getText(), 
				apellidos.getText(), 
				direccion.getText(), 
				Integer.parseInt(telefono.getText()), 
				Utilidad.toDateToLocal(fechaIni.getValue()), 
				Utilidad.toDateToLocal(fechaFin.getValue()),
				cargo.getText(),
				email.getText(),
				Float.parseFloat(importe.getText()),
				activo.isSelected(),
				password.getText(),
				tipoCuota.getValue(),
				administracion);
		
		if (!accionNuevo) { socio.setId(Integer.parseInt(lblID.getText())); } 
		
		return socio;
	}

	public void insertarDatosSocio(ActionEvent event) {
		
		Socio socio = guardaDatosSocio();
		Alert a = new Alert(AlertType.INFORMATION);
		
		try {
			if (accionNuevo) {
				socios.crearNuevo(socio);
				a.setHeaderText("Socio Guardado");
			} else {
				socios.actualizar(socio);
				a.setHeaderText("Socio Actualizado");
			}
			
			limpiaDatos();
			updateTb();
			nombre.requestFocus();
			
		} catch (DAOException e) {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Error al guardar los datos de Socio");
			e.printStackTrace();
			
		} finally {
			a.show();
		}
	}
	
	public void limpiaDatos() {
		
		lblID.setText("");
		
		for (Node elem : formGridPane.getChildren()) {
				if (elem instanceof TextField) {
					((TextField) elem).setText("");
				}
					else if (elem instanceof CheckBox) {
						((CheckBox) elem).setSelected(false);
					}
		}	
	}

	public void muestraSocio(KeyEvent event) {
		Socio socio = tbSocios.getSelectionModel().getSelectedItem();
		rellenaSocio(socio);
	}
	
	public void nuevoSocio(ActionEvent event) {
		limpiaDatos();
		nombre.requestFocus();
		accionNuevo = true;
		formButtonsPane.setVisible(true);
	}
	
	public void rellenaSocio(Socio socio) {
		
		lblID.setText(String.valueOf((int) socio.getId()));
		nombre.setText(socio.getNombre());
		apellidos.setText(socio.getApellidos());
		dni.setText(socio.getDni());
		direccion.setText(socio.getDomicilio());
		email.setText(socio.getCorreo());
		telefono.setText(String.valueOf(socio.getTelefono()));
		fechaIni.setValue(Utilidad.toDateToLocal(socio.getFechaInicio()));
		fechaFin.setValue(Utilidad.toDateToLocal(socio.getFechaFin()));
		importe.setText(String.valueOf(socio.getCuotaAportacion()));
		tipoCuota.setValue(socio.getTipoCuota());
		if (socio.getEstadoAportacion() == true) { activo.setSelected(true); } else { activo.setSelected(false); }
		sede.setValue(socio.getSedeAsignada().getNombre());
		password.setText(socio.getPass());
	}

	public void seleccionaSocio() {
		Socio socio = tbSocios.getSelectionModel().getSelectedItem();
		rellenaSocio(socio);
	}

	public void updateTb() {
		
		listaSocios.clear();
		
		try {
			listaSocios.addAll(socios.obtenerTodos());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		tbSocios.setItems(listaSocios);
	}
}
