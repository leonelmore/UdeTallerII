package logica;

import java.io.Serializable;
import java.util.ArrayList;

import excepciones.ConfiguracionException;
import excepciones.GuardarDatosException;
import excepciones.PeliculaRepetidaException;
import persistencia.Persistencia;
import utilidades.CargarConfiguracion;
import utilidades.MonitorRW;
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

	private ABBPeliculas listaDePeliculas;
	
	public Fachada(){
		listaDePeliculas = new ABBPeliculas();
	}
	
	public void registrarPelicula(VODatosPelicula unVOPelicula) throws PeliculaRepetidaException {
		String titulo = unVOPelicula.getTitulo().toUpperCase().trim().replaceAll(" +", " ");
		String descripcion = unVOPelicula.getDescripcion().toUpperCase().trim().replaceAll(" +", " ");
		MonitorRW.getInstancia().comienzoLectura();
		if (listaDePeliculas.member(titulo)) {
			MonitorRW.getInstancia().terminoLectura();
			throw new excepciones.PeliculaRepetidaException("La película "+titulo+" ya existe.");
		}
		else {
			MonitorRW.getInstancia().terminoLectura();
			MonitorRW.getInstancia().comienzoEscritura();
			listaDePeliculas.insert(new Pelicula(titulo,descripcion));
			MonitorRW.getInstancia().terminoEscritura();
		}
	}
	
	public ArrayList<VODatosPelicula> listarPeliculas(){
		//TODO: Implementar método
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * registrarJugador(...) usa VOLogin, que es el mismo value object que se usa para
	 * autenticar a un jugador.
	 */
	public void registrarJugador(VOLogin datosJugador){
		//TODO: Implementar método
	}
	
	public ArrayList<VODatosJugador> listarJugadores(){
		return null;
		//TODO: Implementar método
	}
	
	public ArrayList<VODatosPartida> listarPartidasDeUnJugador(VOLogin unJugador){
		return null;
		//TODO: Implementar método
	}
	
	public void guardarCambios() throws GuardarDatosException, ConfiguracionException{
		Persistencia.guardarDatos(CargarConfiguracion.getData().rutaYArchivoPersistencia, this);
	}
	
	public boolean loguearseParaJugar(VOLogin unJugador){
		return false;
		//TODO: Implementar método.
	}
	
	public void iniciarNuevaPartida(VOLogin unJugador){
		//TODO: Implementar método.
	}
	
	public VODatosPartida visualizarPartidaEnCurso(VOLogin unJugador){
		return null;
		//TODO: Implementar método.
	}
	
	public void ingresarUnaLetra(VOLogin unJugador, char unaLetra){
		//TODO: Implementar método.
	}
	
	public void arriesgarPelicula(VOLogin unJugador, String unTitulo){
		//TODO: Implementar método.
	}
	
	public ArrayList<VODatoRanking> getRankingGeneral(){
		return null;
		//TODO: Implementar método.
	}

	public ABBPeliculas getListaDePeliculas() {
		return listaDePeliculas;
	}

	public void setListaDePeliculas(ABBPeliculas listaDePeliculas) {
		this.listaDePeliculas = listaDePeliculas;
	}
	
}
