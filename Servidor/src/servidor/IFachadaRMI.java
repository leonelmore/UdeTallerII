package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import excepciones.ConfiguracionException;
import excepciones.GuardarDatosException;
import excepciones.PeliculaRepetidaException;
import logica.VOs.VODatoRanking;
import logica.VOs.VODatosJugador;
import logica.VOs.VODatosPartida;
import logica.VOs.VODatosPelicula;
import logica.VOs.VOLogin;

public interface IFachadaRMI extends Remote{
	public void registrarPelicula(VODatosPelicula unVOPelicula) throws RemoteException, PeliculaRepetidaException;
	public ArrayList<VODatosPelicula> listarPeliculas() throws RemoteException;
	public void registrarJugador(VOLogin datosJugador) throws RemoteException;
	public ArrayList<VODatosJugador> listarJugadores() throws RemoteException;
	public ArrayList<VODatosPartida> listarPartidasDeUnJugador(VOLogin unJugador) throws RemoteException;
	public void guardarCambios() throws GuardarDatosException, ConfiguracionException, RemoteException;
	public boolean loguearseParaJugar(VOLogin unJugador) throws RemoteException;
	public void iniciarNuevaPartida(VOLogin unJugador) throws RemoteException;
	public VODatosPartida visualizarPartidaEnCurso(VOLogin unJugador) throws RemoteException;
	public void ingresarUnaLetra(VOLogin unJugador, char unaLetra) throws RemoteException;
	public void arriesgarPelicula(VOLogin unJugador, String unTitulo) throws RemoteException;
	public ArrayList<VODatoRanking> getRankingGeneral() throws RemoteException;
}
