package acadevs.entreculturas.vista.javafx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HomeInfo extends BorderPane {

	public HomeInfo (String tipo) {
	
		if (tipo.equals("info")) {
			
			Image logoJava = null;
			Image logoXml = null;
			Image logoMysql = null;
			
			try{
			
				logoJava = new Image(new FileInputStream("src/main/java/acadevs/entreculturas/recursos/img/logo_Java.png"));
				logoXml = new Image(new FileInputStream("src/main/java/acadevs/entreculturas/recursos/img/logo_xml.png"));
				logoMysql = new Image(new FileInputStream("src/main/java/acadevs/entreculturas/recursos/img/logo_Mysql.png"));
				
				ImageView logoJavaV = new ImageView(logoJava); 
				logoJavaV.setFitWidth(60);
				logoJavaV.setFitHeight(113.6);
				
				ImageView logoXmlV = new ImageView(logoXml);
				logoXmlV.setFitWidth(80);
				logoXmlV.setFitHeight(80);
	
				ImageView logoMysqlV = new ImageView(logoMysql);
				logoMysqlV.setFitWidth(99.1);
				logoMysqlV.setFitHeight(51.2);
				
				VBox imagenes = new VBox(logoJavaV, logoXmlV, logoMysqlV);
				imagenes.setId("herramientas");
	
				setLeft(imagenes);
				
			} catch (RuntimeException | FileNotFoundException e) {
	
				System.out.println("No se encontró la imagen");
			}
			
	
			Label lblTitulo = new Label ("PROYECTO ICB05 - Programación orientada a objetos con acceso a bases de datos");
			lblTitulo.setId("titulo-opcion");
			
			Label lblSubTitulo = new Label ("Repositorio oficial : http://github.com/agongardev/ACADevelopers\n");
			Label lblAutores = new Label ("Autores : Ana Iglesias Sanchez y Antonio González García\n");

			Label lblConsul = new Label ("Consultor :");
			lblConsul.setTextAlignment(TextAlignment.RIGHT);
			Label lblConsultor = new Label ("Josep Vaño Chic");
			
			VBox infoConsultor = new VBox (lblConsul, lblConsultor);
			infoConsultor.setPadding(new Insets(0, 5, 0, 5));
			
			Text info = new Text(cargaTexto().toString());
			info.setWrappingWidth(500);
			info.setTextAlignment(TextAlignment.JUSTIFY);
			
			VBox centro = new VBox(lblSubTitulo, lblAutores, info);
			centro.setPadding(new Insets(15));
			centro.setSpacing(10);
					
			/*if paginación setbottom con el link a nueva carga de info.*/
			
			setTop(lblTitulo);
			setCenter(centro);
			setRight(infoConsultor);
			
		} else {
			
			Label lblDespedida = new Label("Muchas gracias!");
			lblDespedida.setId("bye");
			setCenter(lblDespedida);
		}
		
		
	}
	
	private StringBuffer cargaTexto() {
		
		Scanner escaner;
		StringBuffer txtInfo = new StringBuffer();
		
		try {
			
			FileReader reader = new FileReader(new File("src/main/java/acadevs/entreculturas/recursos/txt/info.txt"));

			escaner = new Scanner (reader);
			
			while (escaner.hasNextLine()) {
				
				txtInfo.append(escaner.nextLine());
				txtInfo.append("\n");
			}
			
			escaner.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("no se encontró el archivo. La ruta actual es: \n "+ new File(".").getAbsolutePath());
		}
		return txtInfo;
	}
}
