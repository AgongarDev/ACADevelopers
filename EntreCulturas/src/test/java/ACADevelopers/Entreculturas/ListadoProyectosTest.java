package ACADevelopers.Entreculturas;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import javax.xml.bind.JAXBException;

/* Clase preparada para hacer tests de los métodos de ListadoProyectos*/
public class ListadoProyectosTest {

	
	/*con este test comprobamos que no haya ningún conflico a la hora de añadir socios a la lista de socios disponibles que 
	 * puede listar el administrador*/
	@Test
	public void testAddProyecto() throws JAXBException {
			Proyecto p1 = new Proyecto();
			Proyecto p2 = new Proyecto();
			Proyecto p3 = new Proyecto();
			Proyecto p4 = new Proyecto();
		
			// creamos un array con 4 proyectos de prueba
			Proyecto[] listaDePrueba = {p1, p2, p3, p4}; 
		
			// guardamos en la variable esperado que guardará el tamaño del array
			int esperado = listaDePrueba.length;
		
			// creamos una instancia de ListadoProyectos
			ListadoProyectos listaProyectos = new ListadoProyectos();
		
			// llenamos la instancia de listaProyectos con los proyectos de prueba desde el array listaDePrueba
			for (int i = 0 ; i < listaDePrueba.length ; i++) {
				listaProyectos.add(listaDePrueba[i]);
			}
		
			/* guardamos en la variable resultado el tamaño de la instancia para comprobar que son del mismo tamaño y por tanto se 
			está ejecutando correctamente.*/
			int resultado = listaProyectos.getListadoProyectos().size();
		
		assertEquals(esperado, resultado);
		
	}

}
