package excepciones;

public class TituloArriesgadoIncorrectoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TituloArriesgadoIncorrectoException(String unMensaje){
		super("Error: el t�tulo no es correcto. " + unMensaje);
	}
}
