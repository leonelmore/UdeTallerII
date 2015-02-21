package logica;

import java.util.TreeMap;

public class Jugadores {
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

}
