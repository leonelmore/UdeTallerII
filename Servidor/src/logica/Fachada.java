package logica;

import java.io.Serializable;
import java.util.ArrayList;

import excepciones.ConfiguracionException;
import excepciones.GuardarDatosException;
import persistencia.CargarConfiguracion;
import persistencia.Persistencia;
import logica.VOs.VODatoRanking;
import logica.VOs.VODatosJugador;
import logica.VOs.VODatosPartida;
import logica.VOs.VODatosPelicula;
import logica.VOs.VOLogin;

public class Fachada implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void registrarPelicula(VODatosPelicula unVOPelicula) {
		//TODO: Implementar m�todo
	}
	
	public ArrayList<VODatosPelicula> listarPeliculas(){
		//TODO: Implementar m�todo
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * registrarJugador(...) usa VOLogin, que es el mismo value object que se usa para
	 * autenticar a un jugador.
	 */
	public void registrarJugador(VOLogin datosJugador){
		//TODO: Implementar m�todo
	}
	
	public ArrayList<VODatosJugador> listarJugadores(){
		return null;
		//TODO: Implementar m�todo
	}
	
	public ArrayList<VODatosPartida> listarPartidasDeUnJugador(VOLogin unJugador){
		return null;
		//TODO: Implementar m�todo
	}
	
	public void guardarCambios() throws GuardarDatosException, ConfiguracionException{
		Persistencia.guardarDatos(CargarConfiguracion.getData().rutaYArchivoPersistencia, this);
	}
	
	public boolean loguearseParaJugar(VOLogin unJugador){
		return false;
		//TODO: Implementar m�todo.
	}
	
	public void iniciarNuevaPartida(VOLogin unJugador){
		//TODO: Implementar m�todo.
	}
	
	public VODatosPartida visualizarPartidaEnCurso(VOLogin unJugador){
		return null;
		//TODO: Implementar m�todo.
	}
	
	public void ingresarUnaLetra(VOLogin unJugador, char unaLetra){
		//TODO: Implementar m�todo.
	}
	
	public void arriesgarPelicula(VOLogin unJugador, String unTitulo){
		//TODO: Implementar m�todo.
	}
	
	public ArrayList<VODatoRanking> getRankingGeneral(){
		return null;
		//TODO: Implementar m�todo.
	}
	
}
