package excepciones;

public class JugadorNoExisteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JugadorNoExisteException(String unMensaje){
		super("Error: el jugador no existe. " + unMensaje);
	}
}
