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

/**
 * @author Ana Iglesias
 * @author Antonio González
 *
 * Border panel de detalle que contiene los créditos de la aplicación.
 */
public class HomeInfo extends BorderPane {

	// constructor de la clase: tipo = info -> créditos. tipo = salir -> agradecimiento
	public HomeInfo (String tipo) {
	
		if (tipo.equals("info")) {
			
		//bloque de imágenes en el lateral izquierdo del panel de detalle.
			//iniciamos las imagenes para poder actuar con ellas fuera del try.
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
				// controlamos que la aplicación continúe en caso de haber algún problema con las imágenes ya que no es relevante.
				System.out.println("No se encontró la imagen");
			}
			
		// bloque de texto informativo. 
		// Los estilos se han asignado directamente en java para comprobar las diferencias entre hacerlo desde aquí y en css
			Label lblTitulo = new Label ("PROYECTO ICB05 - Programación orientada a objetos con acceso a bases de datos");
			lblTitulo.setId("titulo-opcion");
			
			Label lblSubTitulo = new Label ("Repositorio oficial : http://github.com/agongardev/ACADevelopers\n");
			Label lblAutores = new Label ("Autores : Ana Iglesias Sanchez y Antonio González García\n");

			Label lblConsul = new Label ("Consultor :");
			lblConsul.setTextAlignment(TextAlignment.RIGHT);
			Label lblConsultor = new Label ("Josep Vaño Chic");
			
			VBox infoConsultor = new VBox (lblConsul, lblConsultor);
			infoConsultor.setPadding(new Insets(0, 5, 0, 5));
			
			Text info = new Text(cargaTexto().toString());// el texto contenido en el panel de info o créditos se obtiene de un archivo txt.
			info.setWrappingWidth(500);
			info.setTextAlignment(TextAlignment.JUSTIFY);
			
			VBox centro = new VBox(lblSubTitulo, lblAutores, info);
			centro.setPadding(new Insets(15));
			centro.setSpacing(10);
					
			/*if paginación setbottom con el link a nueva carga de info.*/ 
		
		// asignamos los elementos al panel detalle.
			setTop(lblTitulo);
			setCenter(centro);
			setRight(infoConsultor);
			
		} else {
			
			Label lblDespedida = new Label("Muchas gracias!");
			lblDespedida.setId("bye");
			setCenter(lblDespedida);
		}
		
		
	}
	/*
	 * @return txtInfo, un stringbuffer con el contenido de info.txt
	 * */
	private StringBuffer cargaTexto() {
	
		/*podemos utilizar un escaner o un bufferRenderer. Nos decantamos por el escaner por que simplifica el recorrido por las lineas. Es importante cerrar el escaner.
		 * añadiremos las lineas leídas a un stringbuffer para devolverlo finalmente.*/ 
		Scanner escaner;
		StringBuffer txtInfo = new StringBuffer();
		
		try {
			
			FileReader reader = new FileReader(new File("src/main/java/acadevs/entreculturas/recursos/txt/info.txt"));

			escaner = new Scanner (reader);
			
			while (escaner.hasNextLine()) { //mientras haya líneas que leer añadimos la información al stringbuffer.
				
				txtInfo.append(escaner.nextLine());
				txtInfo.append("\n");
			}
			
			escaner.close();
			
		} catch (FileNotFoundException e) {
			//si no encuentra el archivo, muestra la ruta en la que se encuentra para poder analizar el estado.
			System.out.println("no se encontró el archivo. La ruta actual es: \n "+ new File(".").getAbsolutePath());
		}
		return txtInfo;
	}
}
