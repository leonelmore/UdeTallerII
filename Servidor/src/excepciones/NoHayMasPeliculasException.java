package excepciones;

public class NoHayMasPeliculasException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoHayMasPeliculasException(String unMensaje){
		super("Error: ya se han jugado todas las películas. " + unMensaje);
	}
}
