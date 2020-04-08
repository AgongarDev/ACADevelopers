package ACADevelopers.Entreculturas;

/**
 * Esta clase factoria se usa para crear DAOs que usan
 * SQL para el almacenaje de los modelos.
 * 
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
public class SQLDAOFactory extends DAOFactory {
	
	/**
	 * Metodo que permite acceder a un trabajador SQL data object.
	 * 
	 * @return Nos devuelve un trabajador SQL data object
	 */
	@Override
	public DAO<Administrador> getAdministradorDAO() {
		return null;
	}

	/**
	 * Metodo que permite acceder a un socio SQL data object.
	 * 
	 * @return Nos devuelve un socio SQL data object
	 */
	@Override
	public DAO<Socio> getSocioDAO() {
		return null;
	}

	/**
	 * Metodo que permite acceder a un delegacion SQL data object.
	 * 
	 * @return Nos devuelve un delegacion SQL data object
	 */
	@Override
	public DAO<Proyecto> getProyectoDAO() {
		return null;
	}


}
