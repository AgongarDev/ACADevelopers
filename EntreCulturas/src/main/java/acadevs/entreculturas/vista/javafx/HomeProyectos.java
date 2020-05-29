package acadevs.entreculturas.vista.javafx;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class HomeProyectos extends BorderPane {

	public HomeProyectos () {
		
		//titulo
		Label lblTitulo = new Label("Proyectos de EntreCulturas");
		lblTitulo.setId("titulo-opcion");
		
		//centro
		TableView tbProyectos = new TableView();
		
		TableColumn cNombre = new TableColumn("Proyecto");
		TableColumn cLAccion = new TableColumn("L. Acción");
		TableColumn cLblFechas = new TableColumn("Fechas"); 
		TableColumn cFechaIni = new TableColumn("Inicio");
		TableColumn cFechaFin = new TableColumn("Finalización");
		TableColumn cPais = new TableColumn("País");
		TableColumn cPersonal = new TableColumn("Personal");
		
		cLblFechas.getColumns().addAll(cFechaIni, cFechaFin);
		tbProyectos.getColumns().addAll(cNombre, cLAccion, cLblFechas, cPais, cPersonal);
		
		//base
		Region margen = new Region();
		HBox.setHgrow(margen, Priority.ALWAYS);
		Image logoONGF = new Image(Home.class.getResourceAsStream("/acadevs/entreculturas/recursos/img/logo_entreculturas.png"));
		ImageView logoONG = new ImageView(logoONGF);
		logoONG.setFitWidth(195);
		logoONG.setFitHeight(47.4);
		HBox ONGRef = new HBox(margen, logoONG);
		
		setCenter(tbProyectos);
		setTop(lblTitulo);
		setBottom(ONGRef);
	}
}
