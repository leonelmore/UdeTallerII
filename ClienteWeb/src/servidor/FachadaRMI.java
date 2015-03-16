package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import excepciones.*;
import logica.Fachada;
import logica.VOs.VODatoRanking;
import logica.VOs.VODatosJugador;
import logica.VOs.VODatosPartida;
import logica.VOs.VODatosPelicula;
import logica.VOs.VOLogin;

public class FachadaRMI extends UnicastRemoteObject implements IFachadaRMI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fachada laFachada;
	
	public FachadaRMI(Fachada unaFachada) throws RemoteException {
		super();
		laFachada = unaFachada;
	}

	@Override
	public void registrarPelicula(VODatosPelicula unVOPelicula)
			throws RemoteException, PeliculaRepetidaException {
		laFachada.registrarPelicula(unVOPelicula);
	}

	@Override
	public ArrayList<VODatosPelicula> listarPeliculas() throws RemoteException {
		return laFachada.listarPeliculas();
	}

	@Override
	public void registrarJugador(VOLogin datosJugador) throws RemoteException , JugadorRepetidoException{
		laFachada.registrarJugador(datosJugador);
	}

	@Override
	public ArrayList<VODatosJugador> listarJugadores() throws RemoteException {
		return laFachada.listarJugadores();
	}

	@Override
	public ArrayList<VODatosPartida> listarPartidasDeUnJugador(VOLogin unJugador) throws RemoteException ,ClaveIncorrectaException, JugadorNoExisteException {
		return laFachada.listarPartidasDeUnJugador(unJugador);
	}

	@Override
	public void guardarCambios() throws GuardarDatosException,
			ConfiguracionException, RemoteException {
		laFachada.guardarCambios();
		
	}

	@Override
	public boolean loguearseParaJugar(VOLogin unJugador) throws RemoteException,  ClaveIncorrectaException , JugadorNoExisteException {
		return laFachada.loguearseParaJugar(unJugador);
	}

	@Override
	public void iniciarNuevaPartida(VOLogin unJugador) throws RemoteException, NoHayMasPeliculasException, JugadorNoExisteException, PartidaEnCursoException, ClaveIncorrectaException {
		laFachada.iniciarNuevaPartida(unJugador);
	}

	@Override
	public VODatosPartida visualizarPartidaEnCurso(VOLogin unJugador)
			throws RemoteException , ClaveIncorrectaException, JugadorNoExisteException, NoExistePartidaEnCurso {
		return laFachada.visualizarPartidaEnCurso(unJugador);
	}

	@Override
	public void ingresarUnaLetra(VOLogin unJugador, char unaLetra)throws RemoteException, JugadorNoExisteException, NoExistePartidaEnCurso, ClaveIncorrectaException, LetraNoPerteneceAlTituloException, LetraYaAdivinadaException {
		laFachada.ingresarUnaLetra(unJugador, unaLetra);
	}

	@Override
	public void arriesgarPelicula(VOLogin unJugador, String unTitulo)
			throws RemoteException, NoExistePartidaEnCurso, ClaveIncorrectaException, JugadorNoExisteException, TituloArriesgadoIncorrectoException {
		laFachada.arriesgarPelicula(unJugador, unTitulo);		
	}

	@Override
	public ArrayList<VODatoRanking> getRankingGeneral() throws RemoteException {
		return laFachada.getRankingGeneral();
	}

}
