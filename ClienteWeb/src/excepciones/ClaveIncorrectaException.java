package excepciones;

public class ClaveIncorrectaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClaveIncorrectaException(String unMensaje){
		super("Error: la clave no es correcta. " + unMensaje);
	}
}
