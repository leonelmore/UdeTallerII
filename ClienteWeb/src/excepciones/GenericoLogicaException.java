package excepciones;

public class GenericoLogicaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenericoLogicaException(String unMensaje, Exception unaCausa){
		super("Ha ocurrido un error." + unMensaje, unaCausa);
	}
}
