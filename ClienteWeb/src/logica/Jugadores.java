package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

import logica.VOs.VODatoRanking;
import logica.VOs.VODatosJugador;
import utilidades.MonitorRW;

public class Jugadores implements Serializable {
private TreeMap<String,Jugador> jugadores;
	
	public Jugadores(){
		this.jugadores= new TreeMap<String,Jugador>();
		
	}
	
	
	public boolean member(String j){
		
		return jugadores.containsKey(j);
	}
	
	public void insert (Jugador j){
		
		this.jugadores.put(j.getUserName(),j);
		
	}
	
	
	public Jugador find(String j){
		
		return this.jugadores.get(j);	
		
	}
   public ArrayList<VODatosJugador> iteratorJugadores(){
		
	   ArrayList<VODatosJugador> list = new ArrayList<VODatosJugador>();
	   MonitorRW.getInstancia().comienzoLectura();
	   Iterator<Jugador> iter = this.jugadores.values().iterator();
		while (iter.hasNext()) {
			Jugador jug = (Jugador) iter.next();
			VODatosJugador voJug = new VODatosJugador(jug.getUserName(),
					jug.getCodigo(), jug.getPuntaje(), jug.getCantPelAcer(),
					jug.getCantPelErre());
			list.add(voJug);
		}
		MonitorRW.getInstancia().terminoLectura();
		return list;
		
	}


public ArrayList<VODatoRanking> listadoRankingGeneral() {
		
	 ArrayList<Jugador> lista = new ArrayList<Jugador>();
	 ArrayList<VODatoRanking> datos=new ArrayList<VODatoRanking>();
		Iterator<Jugador> iter = jugadores.values().iterator();
		MonitorRW.getInstancia().comienzoLectura();
		while (iter.hasNext()) {
			Jugador jugador = (Jugador) iter.next();
			lista.add(jugador);
		}
		Collections.sort(lista);
		int cantidad=lista.size();
		
		for(int i=0;i<cantidad;i++){
			Jugador jugador=lista.get(i);
			VODatoRanking voRanking=new VODatoRanking(jugador);
			datos.add(voRanking);
		}
		
		
		MonitorRW.getInstancia().terminoLectura();
		return datos;
}


}
