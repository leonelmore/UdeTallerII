package excepciones;

public class PeliculaRepetidaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PeliculaRepetidaException(String unMensaje){
		super("Error: película repetida. " + unMensaje);
	}
}
