package acadevs.entreculturas.dao;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException(String msg, Throwable causa) {
		super(msg, causa);

	}

	public DAOException(String msg) {
		super(msg);

	}

	public DAOException(Throwable causa) {
		super(causa);

	}

	
}
