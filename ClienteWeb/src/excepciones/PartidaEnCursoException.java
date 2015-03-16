package excepciones;

public class PartidaEnCursoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PartidaEnCursoException() {
		super("Error: existe una partida en curso. " );
	}
	public PartidaEnCursoException(String unMensaje) {
		super("Error: existe una partida en curso. " + unMensaje);
	}
}
