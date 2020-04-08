package ACADevelopers.Entreculturas;
/**
 * Esta clase factoria se usa para crear DAOs que usan
 * XML para el almacenaje de los modelos.
 * 
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
public class XMLDAOFactory extends DAOFactory {


	/**
	 * Metodo que permite acceder a un socio XML data object.
	 * 
	 * @return Nos devuelve un socio XML data object
	 */
	@Override
	public XMLSocioDAO getSocioDAO() {
		return new XMLSocioDAO();
	}

	/**
	 * Metodo que permite acceder a un proyecto XML data object.
	 * 
	 * @return Nos devuelve un proyecto XML data object
	 */
	@Override
	public XMLProyectoDAO getProyectoDAO() {
		return new XMLProyectoDAO();
	}
	/**
	 * Metodo que permite acceder a un trabajador XML data object.
	 * 
	 * @return Nos devuelve un trabajador XML data object
	 */
	@Override
	public XMLTrabajadorDAO getTrabajadorDAO() {
		return new XMLTrabajadorDAO();
	}

}
