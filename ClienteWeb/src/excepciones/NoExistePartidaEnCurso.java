package excepciones;

public class NoExistePartidaEnCurso extends Exception  {
	private static final long serialVersionUID = 1L;

	public NoExistePartidaEnCurso(String unMensaje){
		super("Error: No existen partidas en curso para este jugador: " + unMensaje);
	}
}
