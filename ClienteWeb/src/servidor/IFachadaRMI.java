package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import excepciones.ClaveIncorrectaException;
import excepciones.ConfiguracionException;
import excepciones.GuardarDatosException;
import excepciones.JugadorNoExisteException;
import excepciones.JugadorRepetidoException;
import excepciones.LetraNoPerteneceAlTituloException;
import excepciones.LetraYaAdivinadaException;
import excepciones.NoExistePartidaEnCurso;
import excepciones.NoHayMasPeliculasException;
import excepciones.PartidaEnCursoException;
import excepciones.PeliculaRepetidaException;
import excepciones.TituloArriesgadoIncorrectoException;
import logica.VOs.VODatoRanking;
import logica.VOs.VODatosJugador;
import logica.VOs.VODatosPartida;
import logica.VOs.VODatosPelicula;
import logica.VOs.VOLogin;

public interface IFachadaRMI extends Remote{
	public void registrarPelicula(VODatosPelicula unVOPelicula) throws RemoteException, PeliculaRepetidaException;
	public ArrayList<VODatosPelicula> listarPeliculas() throws RemoteException;
	public void registrarJugador(VOLogin datosJugador) throws RemoteException,JugadorRepetidoException;
	public ArrayList<VODatosJugador> listarJugadores() throws RemoteException;
	public ArrayList<VODatosPartida> listarPartidasDeUnJugador(VOLogin unJugador) throws RemoteException,ClaveIncorrectaException, JugadorNoExisteException;
	public void guardarCambios() throws GuardarDatosException, ConfiguracionException, RemoteException;
	public boolean loguearseParaJugar(VOLogin unJugador) throws RemoteException,  ClaveIncorrectaException , JugadorNoExisteException;
	public void iniciarNuevaPartida(VOLogin unJugador) throws RemoteException, NoHayMasPeliculasException, JugadorNoExisteException, PartidaEnCursoException, ClaveIncorrectaException;
	public VODatosPartida visualizarPartidaEnCurso(VOLogin unJugador) throws RemoteException , ClaveIncorrectaException, JugadorNoExisteException, NoExistePartidaEnCurso;
	public void ingresarUnaLetra(VOLogin unJugador, char unaLetra) throws RemoteException, JugadorNoExisteException, NoExistePartidaEnCurso, ClaveIncorrectaException, LetraNoPerteneceAlTituloException, LetraYaAdivinadaException;
	public void arriesgarPelicula(VOLogin unJugador, String unTitulo) throws RemoteException, NoExistePartidaEnCurso, ClaveIncorrectaException, JugadorNoExisteException, TituloArriesgadoIncorrectoException;
	public ArrayList<VODatoRanking> getRankingGeneral() throws RemoteException;
}
