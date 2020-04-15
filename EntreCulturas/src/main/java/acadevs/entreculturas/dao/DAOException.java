package acadevs.entreculturas.dao;

public class DAOException extends Exception {

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
