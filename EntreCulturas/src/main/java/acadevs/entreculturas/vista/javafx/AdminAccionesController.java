package acadevs.entreculturas.vista.javafx;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import acadevs.entreculturas.modelo.Accion;
import acadevs.entreculturas.modelo.HibernateDBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class AdminAccionesController implements Initializable{

	@FXML TextField nombre;
	@FXML TextArea descripcion;
	@FXML Button btnLimpiar, btnGuardar, btnBorrar;
	@FXML ToggleButton btnNuevo, btnEditar;
	@FXML Label lblId;
	
	@FXML HBox formButtonsPane;
	
	@FXML TableView<Accion> tbAcciones;
	@FXML TableColumn<Accion, String> cNombre, cDescripcion;
	
	HibernateDBManager dbManager;
	
	Boolean accionNuevo;
	
	List<Accion> accionesDB;
	ObservableList<Accion> acciones;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnNuevo.fire();
		btnNuevo.setSelected(true);
		nombre.requestFocus();
		
		dbManager = new HibernateDBManager();
		acciones = FXCollections.observableArrayList();
		
		cNombre.setCellValueFactory(new PropertyValueFactory<Accion, String>("nombre"));
		cDescripcion.setCellValueFactory(new PropertyValueFactory<Accion, String>("descripcion"));
	
		update();
	}
	
	public void borrar(ActionEvent event) {
		
		btnNuevo.disarm();
		btnEditar.disarm();
		
		Accion accion = tbAcciones.getSelectionModel().getSelectedItem();
		Alert a = new Alert(AlertType.NONE);
		a.setHeaderText("Está seguro que quiere eliminar la Acción "+accion.getNombre());
		
		ButtonType borrar = new ButtonType("Eliminar");
		ButtonType cancelar = new ButtonType("Cancelar");
		
		a.getButtonTypes().addAll(borrar, cancelar);
		
		Optional<ButtonType> respuesta = a.showAndWait();
		
		if (respuesta.get() == borrar) {
				dbManager.borrar(accion);
				update();
		} else { 
			a.close(); 
		}
	}	
	
	public void guardaDatos (ActionEvent event) {
		
		Accion accion = new Accion(nombre.getText(), descripcion.getText());
				
		dbManager.guardarOActualizar(accion);
		
		update();
		limpiaDatos(event);
	}

	public void limpiaDatos (ActionEvent event) {
	
		nombre.setText("");
		descripcion.setText("");
		nombre.requestFocus();	
	}
	
	private void rellenaDatos(Accion accion) {
		
		lblId.setText(String.valueOf(accion.getId()));
		nombre.setText(accion.getNombre());
		descripcion.setText(accion.getDescripcion());
		
	}
	
	public void selecciona () {
		
		Accion accion = (Accion) tbAcciones.getSelectionModel().getSelectedItem();
		rellenaDatos(accion);
		
	}
	
	public void irANombre () {
		
		nombre.requestFocus();
	}
	
	public void SeleccionaConKey (KeyEvent event) {
		
		Accion accion = (Accion) tbAcciones.getSelectionModel().getSelectedItem();
		rellenaDatos(accion);
		
	}

	private void update () {
		
		if (acciones != null) {
				acciones.clear();
			}
		
		accionesDB = dbManager.obtenerTodos(Accion.class); 
		
		if (!accionesDB.isEmpty()) {
			acciones.addAll(accionesDB);
			tbAcciones.setItems(acciones);
		}	
		
		
	}
}