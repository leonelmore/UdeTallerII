package excepciones;
//Esta excepcion se tira con una causa. Es decir, la excepcion que se atrapa,
//se agrega al constructor como una causa y luego se tira la ConfiguracionException
//creada.
public class CargarDatosException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CargarDatosException(String unMensaje, Exception unaCausa){
		super("Error cargando archivo de datos. " + unMensaje, unaCausa);
	}
}
