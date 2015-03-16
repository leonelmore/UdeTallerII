package utilidades;

/*
 * SOLO SE USA EN LA CLASE FACHADA.
 * 
 * EJEMPLO DE USO PARA LECTURA. PARA ESCRITURA CAMBIAR POR comienzoEscritura() Y terminoEscritura().
 * HAY QUE TENER OJO, Y SABER QUE ES LO QUE HACEMOS EN LOS METODOS.
 * 
 * public void iniciarSesión (String nom, String cod) throws LoginException
 * {
 * 		MonitorRW.getInstancia().comienzoLectura( ); ///////
 * 		busco nombre del jugador en diccionario de jugadores
 * 		if (no existe el jugador)
 * 		{ 
 * 			MonitorRW.getInstancia().terminoLectura( ); ///////
 * 			throw new LoginException(“el jugador no está registrado”);
 * 		}
 * 		else
 * 		{ 
 * 			obtengo al jugador y veo si su código de acceso es correcto
 * 			if (su código no es correcto)
 * 			{ 
 * 				MonitorRW.getInstancia().terminoLectura(); ///////
 * 				throw new LoginException(“código de acceso inválido”);
 * 			}
 * 			else
 *  			MonitorRW.getInstancia().terminoLectura(); ///////
 *  	}
 * }
 */

//SOLO SE USA EN LA CLASE FACHADA.
public class MonitorRW {
	private static MonitorRW instancia;
	private int cantidadLeyendo;
	private boolean escribiendo;
	
	private MonitorRW(){
		super();
		cantidadLeyendo = 0;
		escribiendo = false;
	}
	
	public static MonitorRW getInstancia() {
		if (instancia == null) {
			instancia = new MonitorRW();
		}
		return instancia;
	}
	public synchronized void comienzoLectura() {
		while (getInstancia().escribiendo) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// NO HACER NADA
			}
		}
		getInstancia().cantidadLeyendo++;
	}
	
	public synchronized void terminoLectura() {
		getInstancia().cantidadLeyendo--;
		if (getInstancia().cantidadLeyendo == 0)
		{
			this.notify();
		}
	}
	
	public synchronized void comienzoEscritura(){
		while (getInstancia().cantidadLeyendo > 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// NO HACER NADA
			}
		}
		getInstancia().escribiendo = true;
	}
	
	public synchronized void terminoEscritura(){
		getInstancia().escribiendo = false;
		this.notify();
	}
}

//SOLO SE USA EN LA CLASE FACHADA.
