package ACADevelopers.Entreculturas;

/**
 * Clase abstracta que permite seleccionar la factoria con la que queremos
 * persistir los datos.
 * 
 * @author Antonio, Cristina, Ana.
 * @version 1.0
 *
 */
public abstract class DAOFactory {
	
	// Lista de tipos DAO soportado por la factoria.
	public static final int XML = 1;
	public static final int SQL = 2;

	// Hay un metodo para cada DAO que puede ser creado.
	// Las factorias tendran que implementar estos metodos.
	public abstract DAO<Trabajador> getTrabajadorDAO();
	public abstract DAO<Socio> getSocioDAO();
	public abstract DAO<Proyecto> getProyectoDAO();

	public static DAOFactory getDAOFactory(int whichFactory) {
		
		switch (whichFactory) {
			case 1: 
				return new XMLDAOFactory();
	        case 2: 
	            return new SQLDAOFactory();
	        default: 
	            return null;
	    }
		
	}
	/**
	 * Metodo que permite acceder a un Administrador XML data object.
	 * 
	 * @return Nos devuelve un trabajador XML data object
	 */
	
}
