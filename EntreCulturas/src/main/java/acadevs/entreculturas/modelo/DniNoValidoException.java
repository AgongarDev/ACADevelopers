package acadevs.entreculturas.modelo;
public class DniNoValidoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor que crea un nuevo objeto DniNoValidoException .
	 * 
	 * @param msj Mensaje de error.
	 */
	public DniNoValidoException(String msg) {
        super(msg);
    }

}
