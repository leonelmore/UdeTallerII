package excepciones;

//Esta excepcion se tira con una causa. Es decir, la excepcion que se atrapa,
//se agrega al constructor como una causa y luego se tira la ConfiguracionException
//creada.

public class ConfiguracionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfiguracionException(String unMensaje, Exception unaCausa){
		super("Error cargando variables de configuración. " + unMensaje, unaCausa);
	}
}
