package excepciones;

public class LetraNoPerteneceAlTituloException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LetraNoPerteneceAlTituloException(String unMensaje){
		super("Error: la letra arriesgada no pertenece al t�tulo de la pel�cula. " + unMensaje);
	}
}
