package ACADevelopers.Entreculturas;


import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

/**
 * Todas aquellas personas que pueden usar la aplicacion de la ONG
 * 
 */

public interface Usuario {
	
	/**
	 * Acciones que puede realizar el usuario en la aplicacion cuando inicia sesion
	 * 
	 * @throws IOException si se produce un error de entrada/salida.
	 * @throws JAXBException si se produce una excepci�n de tipo JAXB.
	 * @throws TransformerException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws XPathExpressionException 
	 */
	public void abrirSesion() throws IOException, JAXBException, XPathExpressionException, ParserConfigurationException, SAXException, TransformerException;
	
	/**
	 * Permite al usuario consultar el listado de proyectos en los que trabaja la ONG
	 * 
	 * @param lp Lista de proyectos.
	 *
	 * * @param ls Lista de socios.
	 */
	public void consultarProyectos(ArrayList<Proyecto> lp);

	public void consultarSocios(ArrayList<Socio> ls);

}
