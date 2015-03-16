package logica;

import logica.Partida;
import logica.VOs.VODatosJugador;
import logica.VOs.VODatosPartida;
import logica.VOs.VODatosPelicula;

import java.io.Serializable;
import java.util.*;

import utilidades.MonitorRW;

public class Partidas implements Serializable{

	private ArrayList<Partida> ListaPartidas;
	public Partidas() {
		this.ListaPartidas = new ArrayList<Partida>();
	}

	public void InstBack(Partida p) {
		int ultimoNumero = ListaPartidas.size();
		p.setPartida(ultimoNumero + 1);
		ListaPartidas.add(p);
	}

	public boolean esVacia() {
		return ListaPartidas.isEmpty();

	}

	public Partida ultimaPartida() {
		Partida partida=null;
		if(ListaPartidas.size()-1>=0)
			partida=ListaPartidas.get(ListaPartidas.size()-1);
		return partida;
	}
//nuevo
	public Iterator<Pelicula> listadoPeliculasJugadas() {
		int largo=ListaPartidas.size();
		ArrayList<Pelicula> aux=new ArrayList<Pelicula>();
		
		for(int i=0;i<largo;i++){
			Partida partida=ListaPartidas.get(i);
			aux.add(partida.getPelicula());
		}
		return aux.iterator();
		}
	
	public ArrayList<VODatosPartida> iteratorPartidas()
     {
     	ArrayList<VODatosPartida> list = new ArrayList<VODatosPartida>();
 		Iterator<Partida> iter = this.ListaPartidas.iterator(); 
 		MonitorRW.getInstancia().comienzoLectura();
 		while (iter.hasNext()) {
 			Partida part = (Partida) iter.next();
 			VODatosPelicula pel = new VODatosPelicula(part.getPelicula().getTitulo(),part.getPelicula().getDescripcion());
 			VODatosPartida voPart = new VODatosPartida(part.getNumero(), part.getPuntaje(),part.getAcertada(), !part.getEnCurso(), pel,part.getTextoHastaElMomento());
 			list.add(voPart);
 		}
 		MonitorRW.getInstancia().terminoLectura();
 		return list;
     	
     }
	public int cantidadPartidas() {
		
		return ListaPartidas.size();
	}
	}


