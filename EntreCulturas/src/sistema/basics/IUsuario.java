package sistema.basics;


import java.io.IOException;

import javax.xml.bind.JAXBException;

/**
 * Todas aquellas personas que pueden usar la aplicacion de la ONG
 * 
 */

public interface IUsuario {
	
	/**
	 * Acciones que puede realizar el usuario en la aplicacion cuando inicia sesion
	 * 
	 * @throws IOException si se produce un error de entrada/salida.
	 * @throws JAXBException si se produce una excepción de tipo JAXB.
	 */
	public void abrirSesion() throws IOException, JAXBException;
	
	/**
	 * Permite al usuario consultar el listado de proyectos en los que trabaja la ONG
	 * 
	 * @param lp Lista de proyectos.
	 */
	public void consultarProyectos(ListaProyectos lp);

}
