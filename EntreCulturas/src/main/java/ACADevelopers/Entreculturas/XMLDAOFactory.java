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
	 * Metodo que permite acceder a un Administrador XML data object.
	 * 
	 * @return Nos devuelve un trabajador XML data object
	 */
	@Override
	public XMLAdministradorDAO getAdministradorDAO() {
		return new XMLTrabajadorDAO();
	}

	/**
	 * Metodo que permite acceder a un socio XML data object.
	 * 
	 * @return Nos devuelve un socio XML data object
	 */
	@Override
	public XMLSocioDAO getSocioDAO() {
		// TODO Auto-generated method stub
		return new XMLSocioDAO();
	}

	/**
	 * Metodo que permite acceder a un delegacion XML data object.
	 * 
	 * @return Nos devuelve un delegacion XML data object
	 */
	@Override
	public XMLProyectoDAO getProyectoDAO() {
		// TODO Auto-generated method stub
		return new XMLProyectoDAO();
	}

}
