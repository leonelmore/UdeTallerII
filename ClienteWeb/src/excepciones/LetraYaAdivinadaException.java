package excepciones;

public class LetraYaAdivinadaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LetraYaAdivinadaException(String unMensaje){
		super("Error: esta letra ya se ha adivinado. " + unMensaje);
	}
}
