package excepciones;

public class PartidaFinalizadaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PartidaFinalizadaException(String unMensaje){
		super("Error: la partida se encuentra finalizada. " + unMensaje);
	}
}
