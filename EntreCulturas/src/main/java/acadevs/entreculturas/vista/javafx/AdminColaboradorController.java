package acadevs.entreculturas.vista.javafx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import acadevs.entreculturas.enums.LineaDeAccion;
import acadevs.entreculturas.modelo.Colaborador;
import acadevs.entreculturas.modelo.HibernateDBManager;
import acadevs.entreculturas.modelo.Proyecto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminColaboradorController implements Initializable{

	@FXML TextField nombre, nif, direccion, email, telefono;
	@FXML GridPane gridPane;
	@FXML Label lblId;
	
	@FXML Tab vistaColaboradores, vistaPAsignados;
	
	@FXML TableView<Colaborador> tbColaboradores;
	@FXML TableView<Proyecto> tbProyectos, tbPAsignados;
	
	@FXML TableColumn<Colaborador, String> cNombre, cDireccion, cEmail, cNif;
	@FXML TableColumn<Colaborador, Integer> cTelefono; 
	
	@FXML TableColumn<Proyecto, String> cPNombre, cPANombre;
	@FXML TableColumn<Proyecto, LineaDeAccion> cPLAccion, cPALAccion;
	
	@FXML HBox formButtonsPane;
	
	@FXML Button btnLimpiar, btnGuardar, btnBorrar, btnAsignar, btnLiberar;
	@FXML ToggleButton btnNuevo, btnEditar;
	
	HibernateDBManager dbManager;
	
	List<Colaborador> colaboradoresDB;
	List<Proyecto> proyectosDB, proyectosAsignadosDB;
	
	ObservableList<Colaborador> colaboradores = FXCollections.observableArrayList();
	ObservableList<Proyecto> proyectos = FXCollections.observableArrayList();
	ObservableList<Proyecto> proyectosAsignados = FXCollections.observableArrayList();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		btnNuevo.fire();
		nombre.requestFocus();
		
		dbManager = new HibernateDBManager();
		
		cNif.setCellValueFactory(new PropertyValueFactory<Colaborador, String> ("nif"));
		cNombre.setCellValueFactory(new PropertyValueFactory<Colaborador, String> ("nombre"));
		cDireccion.setCellValueFactory(new PropertyValueFactory<Colaborador, String> ("direccion"));
		cEmail.setCellValueFactory(new PropertyValueFactory<Colaborador, String> ("email"));
		cTelefono.setCellValueFactory(new PropertyValueFactory<Colaborador, Integer> ("telefono"));
		
		cPNombre.setCellValueFactory(new PropertyValueFactory<Proyecto, String> ("nombre"));
		
		cPANombre.setCellValueFactory(new PropertyValueFactory<Proyecto, String> ("nombre"));
		
		updateTbColaboradores();
	}
	
	public void joinProyecto () {
		
		Proyecto p = (Proyecto) tbProyectos.getSelectionModel().getSelectedItem();
		Colaborador c = (Colaborador) tbColaboradores.getSelectionModel().getSelectedItem();
		
		p.addColaborador(c);
		
		dbManager.guardarOActualizar(p);
		dbManager.guardarOActualizar(c);
	}
	
	public void limpiaDatos (ActionEvent event) {
		
		nombre.setText("");
		nif.setText("");
		direccion.setText("");
		email.setText("");
		telefono.setText("");
		
		irANombre(event);
	}
	
	public void irANombre(ActionEvent event) {
		
		nombre.requestFocus();
	}
	
	public void guardaDatos (ActionEvent event) {
		
		Colaborador colaborador = new Colaborador(nif.getText(), nombre.getText(), direccion.getText(), email.getText(), Integer.parseInt(telefono.getText()));
		
		dbManager.guardarOActualizar(colaborador);
		
		updateTbColaboradores();
		limpiaDatos(event);
	}
	
	public void updateTbColaboradores() {
	
		colaboradores.clear();
		
		colaboradoresDB = dbManager.obtenerTodos(Colaborador.class);
		
		if (!colaboradoresDB.isEmpty()) {
			colaboradores.addAll(colaboradoresDB);
			tbColaboradores.setItems(colaboradores);
		}
	}
	
	public void updateTabProyectosAsignados() {
		
		proyectosAsignados.clear();
		
		proyectosDB = dbManager.obtenerTodos(Proyecto.class);
		
		if (!proyectosDB.isEmpty()) {
		proyectos.addAll(proyectosDB);
		tbProyectos.setItems(proyectos);
		}
	}
	
}
