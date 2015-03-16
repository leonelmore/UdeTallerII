package excepciones;

public class JugadorRepetidoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JugadorRepetidoException(String unMensaje){
		super("Error: jugador repetido. " + unMensaje);
	}
}
