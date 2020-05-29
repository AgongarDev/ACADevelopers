package acadevs.entreculturas.vista.javafx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuAdminController {

	@FXML
	private Label logoAca;
	
	@FXML
	private Button btnSocios;
	@FXML
	private Button btnProyectos;
	@FXML
	private Button btnAcciones;
	@FXML
	private Button btnSociosLocales;
	@FXML
	private Button btnAdministraciones;
	@FXML
	private Button btnFinanciadores;
	@FXML
	private Button btnPersonal;
	@FXML
	private Button btnSalir;

	@FXML 
	private BorderPane menuAdmin;
	
	@FXML
	private void showAdminSocios(ActionEvent event) throws IOException {
		
		Parent newFrm = FXMLLoader.load(getClass().getResource("admin-socios.fxml"));
		menuAdmin.setCenter(newFrm);
	}
	
	@FXML
	private void showAdminSedes(ActionEvent event) throws IOException {
		
			Parent newFrm = FXMLLoader.load(getClass().getResource("admin-sedes.fxml"));
			menuAdmin.setCenter(newFrm);
	}
	
	@FXML
	private void showAdminAportadores(ActionEvent event) throws IOException {
		
			Parent newFrm = FXMLLoader.load(getClass().getResource("admin-aportadores.fxml"));
			menuAdmin.setCenter(newFrm);
	}
	
	@FXML
	private void showAdminPersonal(ActionEvent event) throws IOException {
		
			Parent newFrm = FXMLLoader.load(getClass().getResource("admin-personal.fxml"));
			menuAdmin.setCenter(newFrm);
	}
	
	@FXML
	private void showAdminProyectos(ActionEvent event) throws IOException {
		
			Parent newFrm = FXMLLoader.load(getClass().getResource("admin-proyectos.fxml"));
			menuAdmin.setCenter(newFrm);
	}
	
	@FXML
	private void showAdminAcciones(ActionEvent event) throws IOException {
		
			Parent newFrm = FXMLLoader.load(getClass().getResource("admin-acciones.fxml"));
			menuAdmin.setCenter(newFrm);
	}
	
	@FXML
	private void showAdminColaboradores(ActionEvent event) throws IOException {
		
			Parent newFrm = FXMLLoader.load(getClass().getResource("admin-colaboradores.fxml"));
			menuAdmin.setCenter(newFrm);
	}
	
	@FXML
	private void salirAMenu(ActionEvent event) {
		
			Stage stage = (Stage) btnSalir.getScene().getWindow();
			stage.close();
	}
	
	
}
