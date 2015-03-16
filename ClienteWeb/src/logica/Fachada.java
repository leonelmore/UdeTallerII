package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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
import persistencia.Persistencia;
import utilidades.CargarConfiguracion;
import utilidades.MonitorRW;
import logica.VOs.VODatoRanking;
import logica.VOs.VODatosJugador;
import logica.VOs.VODatosPartida;
import logica.VOs.VODatosPelicula;
import logica.VOs.VOLogin;
import excepciones.JugadorNoExisteException;

public class Fachada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Peliculas listaDePeliculas;
	private Jugadores listaDeJugadores;

	public Fachada() {
		listaDePeliculas = new Peliculas();
		listaDeJugadores = new Jugadores();
	}

	// LISTO
	public void registrarPelicula(VODatosPelicula unVOPelicula)
			throws PeliculaRepetidaException {
		String titulo = unVOPelicula.getTitulo().toUpperCase().trim()
				.replaceAll(" +", " ");
		String descripcion = unVOPelicula.getDescripcion().toUpperCase().trim()
				.replaceAll(" +", " ");
		MonitorRW.getInstancia().comienzoLectura();
		if (listaDePeliculas.member(titulo)) {
			MonitorRW.getInstancia().terminoLectura();
			throw new excepciones.PeliculaRepetidaException("La película "
					+ titulo + " ya existe.");
		} else {
			MonitorRW.getInstancia().terminoLectura();
			MonitorRW.getInstancia().comienzoEscritura();
			listaDePeliculas.insert(new Pelicula(titulo, descripcion));
			MonitorRW.getInstancia().terminoEscritura();
		}
	}

	// LISTO
	//NOTA: el manejo del monitor se realiza dentro de listaDePeliculas.iteratorPelicula();
	public ArrayList<VODatosPelicula> listarPeliculas() {

		ArrayList<VODatosPelicula> list = new ArrayList<VODatosPelicula>();
		list = listaDePeliculas.iteratorPelicula();

		return list;

	}

	// LISTO
	public void registrarJugador(VOLogin datosJugador)
			throws JugadorRepetidoException {
		String codigo = datosJugador.getCodigo();
		String userName = datosJugador.getUsuario();
		int puntaje = 0;
		int cantPelAcer = 0;
		int cantpelErre = 0;
		MonitorRW.getInstancia().comienzoLectura();
		if (listaDeJugadores.member(userName)) {
			MonitorRW.getInstancia().terminoLectura();
			throw new excepciones.JugadorRepetidoException(
					"El jugador de nombre: " + userName + " ya existe.");
		} else {
			MonitorRW.getInstancia().terminoLectura();
			MonitorRW.getInstancia().comienzoEscritura();
			listaDeJugadores.insert(new Jugador(codigo, userName, puntaje,
					cantPelAcer, cantpelErre));
			MonitorRW.getInstancia().terminoEscritura();
		}
	}

	// LISTO
	//NOTA: el manejo del monitor se realiza dentro de listaDeJugadores.iteratorJugadores();
	public ArrayList<VODatosJugador> listarJugadores() {
		ArrayList<VODatosJugador> list = new ArrayList<VODatosJugador>();
		list = listaDeJugadores.iteratorJugadores();
		return list;
	}

	// LISTO
	public ArrayList<VODatosPartida> listarPartidasDeUnJugador(VOLogin unJugador)
			throws ClaveIncorrectaException, JugadorNoExisteException {
		ArrayList<VODatosPartida> list = new ArrayList<VODatosPartida>();
		MonitorRW.getInstancia().comienzoLectura();
		if (listaDeJugadores.member(unJugador.getUsuario())) {
			Jugador jug = listaDeJugadores.find(unJugador.getUsuario());
			list = jug.iteratorPartidas();
			MonitorRW.getInstancia().terminoLectura();

			//TODO: Limpiar comments.
			/*if (jug.getCodigo().equals(unJugador.getCodigo())) {

				list = jug.iteratorPartidas();
				MonitorRW.getInstancia().terminoLectura();

			} else {
				MonitorRW.getInstancia().terminoLectura();
				throw new excepciones.ClaveIncorrectaException(
						"Codigo Incorrecto.");
			}*/

		} else {
			MonitorRW.getInstancia().terminoLectura();
			throw new excepciones.JugadorNoExisteException(
					"El jugador de nombre: " + unJugador.getUsuario()
							+ " no existe.");

		}
		return list;
	}

	// LISTO
	public void guardarCambios() throws GuardarDatosException,
			ConfiguracionException {
		MonitorRW.getInstancia().comienzoEscritura();
		try {
			Persistencia.guardarDatos(
					CargarConfiguracion.getData().rutaYArchivoPersistencia, this);
			MonitorRW.getInstancia().terminoEscritura();
		}
		catch (Exception e){
			MonitorRW.getInstancia().terminoEscritura();
			throw e;
		}
	}

	// LISTO
	public boolean loguearseParaJugar(VOLogin unJugador)
			throws ClaveIncorrectaException, JugadorNoExisteException {
		MonitorRW.getInstancia().comienzoLectura();
		if (listaDeJugadores.member(unJugador.getUsuario())) {
			Jugador jug = listaDeJugadores.find(unJugador.getUsuario());
			if (jug.getCodigo().equals(unJugador.getCodigo())) {
				MonitorRW.getInstancia().terminoLectura();
				return true;

			} else {
				MonitorRW.getInstancia().terminoLectura();
				throw new excepciones.ClaveIncorrectaException(
						"Codigo Incorrecto , por favor ingrese codigo nuevamente");
			}

		} else {
			MonitorRW.getInstancia().terminoLectura();
			throw new excepciones.JugadorNoExisteException(
					"El jugador de nombre: " + unJugador.getUsuario()
							+ " no existe.");
		}
	}

	// LISTO
	public void iniciarNuevaPartida(VOLogin unJugador)
			throws NoHayMasPeliculasException, JugadorNoExisteException,
			PartidaEnCursoException, ClaveIncorrectaException {
		MonitorRW.getInstancia().comienzoLectura();
		if (listaDeJugadores.member(unJugador.getUsuario())) {
			Jugador jug = listaDeJugadores.find(unJugador.getUsuario());
			if (jug.getCodigo().equals(unJugador.getCodigo())) {
				Partida partida = jug.ultimaPartida();
				boolean enCurso = false;
				if (partida != null) {
					enCurso = partida.getEnCurso();
				}
				if (!enCurso) {
					if (jug.getCantidadPartidas() == listaDePeliculas
							.cantidadPeliculas()) {
						MonitorRW.getInstancia().terminoLectura();
						throw new NoHayMasPeliculasException("");
					} else {
						Iterator<Pelicula> listaPeliculaJugador = jug
								.listadoPeliculasJugadas();
						Pelicula pelicula = listaDePeliculas
								.eligirAzar(listaPeliculaJugador);
						partida = new Partida(pelicula);
						MonitorRW.getInstancia().terminoLectura();
						MonitorRW.getInstancia().comienzoEscritura();
						jug.insBack(partida);
						MonitorRW.getInstancia().terminoEscritura();
					}
					
				} else {
					MonitorRW.getInstancia().terminoLectura();
					throw new PartidaEnCursoException();
				}
			} else {
				MonitorRW.getInstancia().terminoLectura();
				throw new excepciones.ClaveIncorrectaException(
						"Codigo Incorrecto , por favor ingrese codigo nuevamente");
			}
		} else {
			MonitorRW.getInstancia().terminoLectura();
			throw new JugadorNoExisteException("El jugador de nombre: "
					+ unJugador.getUsuario() + " no existe.");
		}
	}

	// LISTO
	public VODatosPartida visualizarPartidaEnCurso(VOLogin unJugador)
			throws ClaveIncorrectaException, JugadorNoExisteException,
			NoExistePartidaEnCurso {

		MonitorRW.getInstancia().comienzoLectura();
		VODatosPartida datPart = null;
		if (listaDeJugadores.member(unJugador.getUsuario())) {
			Jugador jug = listaDeJugadores.find(unJugador.getUsuario());
			if (jug.getCodigo().equals(unJugador.getCodigo())) {
				if (jug.ultimaPartida() == null) {
					MonitorRW.getInstancia().terminoLectura();
					throw new excepciones.NoExistePartidaEnCurso("El jugador "+
								jug.getUserName()+" no tiene una partida en curso.");
				}
				if (jug.ultimaPartida().getEnCurso() == false) {
					MonitorRW.getInstancia().terminoLectura();
					throw new excepciones.NoExistePartidaEnCurso("El jugador "+
								jug.getUserName()+" no tiene una partida en curso.");
				}
				if (jug.ultimaPartida().getEnCurso() == true) {
					int numeroDePartida = jug.ultimaPartida().getNumero();
					int puntaje = jug.ultimaPartida().getPuntaje();
					VODatosPelicula datoPelicula = new VODatosPelicula(jug
							.ultimaPartida().getPelicula().getTitulo(), jug
							.ultimaPartida().getPelicula().getDescripcion());
					String textoAdivinado = jug.ultimaPartida()
							.getTextoHastaElMomento();
					boolean partidaFinalizada = !jug.ultimaPartida()
							.getEnCurso();
					boolean peliculaAcertada = jug.ultimaPartida()
							.getAcertada();
					datPart = new VODatosPartida(
							numeroDePartida, puntaje, partidaFinalizada,
							peliculaAcertada, datoPelicula, textoAdivinado);
					MonitorRW.getInstancia().terminoLectura();
					//return datPart;
				} 
			} else {
				MonitorRW.getInstancia().terminoLectura();
				throw new excepciones.ClaveIncorrectaException(
						"Codigo Incorrecto.");
			}

		} else {
			MonitorRW.getInstancia().terminoLectura();
			throw new excepciones.JugadorNoExisteException(
					"El jugador de nombre: " + unJugador.getUsuario()
							+ " no existe.");

		}
		return datPart;

	}

	public void ingresarUnaLetra(VOLogin unJugador, char unaLetra)
			throws JugadorNoExisteException, NoExistePartidaEnCurso,
			ClaveIncorrectaException, LetraNoPerteneceAlTituloException, LetraYaAdivinadaException {
		MonitorRW.getInstancia().comienzoLectura();
		if (listaDeJugadores.member(unJugador.getUsuario())) {
			Jugador jug = listaDeJugadores.find(unJugador.getUsuario());
			if (jug.getCodigo().equals(unJugador.getCodigo())) {
				Partida partida = jug.ultimaPartida();
				if (partida.getEnCurso()) {
					MonitorRW.getInstancia().terminoLectura();
					try {
						MonitorRW.getInstancia().comienzoEscritura();
						int cambioPuntaje = partida.ingresarLetra(unaLetra);
						jug.setPuntaje(jug.getPuntaje() + cambioPuntaje);
						MonitorRW.getInstancia().terminoEscritura();
					} catch (LetraNoPerteneceAlTituloException e) {
						jug.setPuntaje(jug.getPuntaje() - 5);
						MonitorRW.getInstancia().terminoEscritura();
						throw e;
					} catch (LetraYaAdivinadaException e) {
						MonitorRW.getInstancia().terminoEscritura();
						throw e;
					}				
					
				} else {
					MonitorRW.getInstancia().terminoLectura();
					throw new NoExistePartidaEnCurso("");
				}
			} else {
				MonitorRW.getInstancia().terminoLectura();
				throw new excepciones.ClaveIncorrectaException(	"Codigo Incorrecto.");
			}
		} else {
			MonitorRW.getInstancia().terminoLectura();
			throw new excepciones.JugadorNoExisteException(
					"El jugador de nombre: " + unJugador.getUsuario()
							+ " no existe.");
		}
	}

	public void arriesgarPelicula(VOLogin unJugador, String unTitulo) throws NoExistePartidaEnCurso, ClaveIncorrectaException, JugadorNoExisteException, TituloArriesgadoIncorrectoException {
		MonitorRW.getInstancia().comienzoLectura();
		if (listaDeJugadores.member(unJugador.getUsuario())) {
			Jugador jug = listaDeJugadores.find(unJugador.getUsuario());
			if (jug.getCodigo().equals(unJugador.getCodigo())) {
				Partida partida = jug.ultimaPartida();
				if (partida.getEnCurso()) {
					MonitorRW.getInstancia().terminoLectura();
					MonitorRW.getInstancia().comienzoEscritura();
					
					if(!partida.arriesgarPelicula(unTitulo)){
						MonitorRW.getInstancia().terminoEscritura();
						jug.setCantPelErre(jug.getCantPelErre() + 1);
						jug.setPuntaje(jug.getPuntaje() - 50);
						throw new TituloArriesgadoIncorrectoException("");
					}
					else
					{
						jug.setCantPelAcer(jug.getCantPelAcer() + 1);
						jug.setPuntaje(jug.getPuntaje() + 50);
					}
					
					MonitorRW.getInstancia().terminoEscritura();
					
				} else {
					MonitorRW.getInstancia().terminoLectura();
					throw new NoExistePartidaEnCurso("");

				}
			} else {

				MonitorRW.getInstancia().terminoLectura();
				throw new excepciones.ClaveIncorrectaException(	"Codigo Incorrecto.");
			}
		} else {
			MonitorRW.getInstancia().terminoLectura();
			throw new excepciones.JugadorNoExisteException(
					"El jugador de nombre: " + unJugador.getUsuario()
							+ " no existe.");

		}

	}

	//NOTA: el manejo del monitor se realiza dentro de listaDeJugadores.listadoRankingGeneral();
	public ArrayList<VODatoRanking> getRankingGeneral() {
		return listaDeJugadores.listadoRankingGeneral();
	}

	public Peliculas getListaDePeliculas() {
		return listaDePeliculas;
	}

	public void setListaDePeliculas(Peliculas listaDePeliculas) {
		this.listaDePeliculas = listaDePeliculas;
	}

}
