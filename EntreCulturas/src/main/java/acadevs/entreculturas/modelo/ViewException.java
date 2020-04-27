package acadevs.entreculturas.modelo;
/**
 * Esta clase representa la excepcion que se lanza cuando el
 * numero de telefono introducido no es valido.
 * 
 * @author Antonio,Cristina y Ana.
 * @version 1.0
 *
 */
public class ViewException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que crea un nuevo objeto TelefonoNoValidoException.
	 * 
	 * @param msj Mensaje de error.
	 */
	public ViewException (String msj) {
        super(msj);
    }

	public ViewException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
