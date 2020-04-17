package ACADevelopers.Entreculturas;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;

import acadevs.entreculturas.enums.TipoCuota;
import acadevs.entreculturas.modelo.AdministracionFisica;
import acadevs.entreculturas.modelo.ListadoSocios;
import acadevs.entreculturas.modelo.Socio;

import org.junit.After;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

/*Clase preparada para hacer tests de las impresiones en xml*/
public class XMLSocioDAOTest {
		
	    // variables para pruebas
		private Socio socioPrueba1;
	    private AdministracionFisica sedePrueba;
	    
	    /* el método creaDatosPrueba se ejecuta al inicio de la prueba, antes que cualquier otro.
	     * Con este método cargaremos los datos de muestra para el testeo
	     */
	    @Before
	    public void creaDatosPrueba() {
	    	
	    	sedePrueba = new AdministracionFisica( "AFP1", "Direccion",971971971, "correo", 100);
	    	
	    	Date fechaIni = new Date(20200407);
	    	Date fechaFin = new Date(20210407);
	    	
	    	socioPrueba1 = new Socio("87654321Q", "socioPrueba1", "Uno", "calle socioPrueba 1", 971179179, fechaIni,
	    				fechaFin, "cargo1", "correo1", 123, true, "**", TipoCuota.TRIM,sedePrueba );
	    }
	    
	    /* el método borrarDatosPrueba se ejecuta al final de la prueba, después de cualquier otro.
	     * Con este método borraremos los datos en ram de las variables de prueba.
	     */
	    @After
	    public void borraDatosPrueba(){
	        socioPrueba1 = null;
	        sedePrueba = null;
	    }
	    
	    /* con el método datosXMLTest queremos comprobar que al cargar datos en xml no existe ningún error y que aparecen todos los nodos.
	     * Si todo es correcto, veremos en consola una simulación de la impresión de los datos en formato xml
	     */
	    @Test
	    public void datosXMLTest() throws JAXBException, FileNotFoundException { // excepciones controladas
	        
	    	ListadoSocios lsocios = new ListadoSocios(); // creamos una instancia de la clase ListadoSocios ya que es la que imprime XMLSocioDAO
	        lsocios.getSocios().add(socioPrueba1);// añadimos los datos de la prueba.
	       
	        JAXBContext jaxbContext = JAXBContext.newInstance(ListadoSocios.class); // jaxbcontext nos ayudará a imprimir los datos si la estructura es correcta.
	        Marshaller marshaller = jaxbContext.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
	        marshaller.marshal(lsocios, new File("sociosPrueba.xml")); // imprime los datos en un xml: si no se puede, lanza excepcion.
	        marshaller.marshal(lsocios, System.out); // imprime los datos en pantalla.
	    }
	    
	}