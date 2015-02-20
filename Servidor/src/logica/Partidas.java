package logica;
import logica.Partida;

import java.util.*;

public class Partidas {
	
	
	private ArrayList<Partida> ListaPartidas;
	
	public Partidas() {
	this.ListaPartidas= new ArrayList<Partida>();
	}

	public void InstBack(Partida p){
	ListaPartidas.add(p);
	}
		
	public boolean esVacia(){
		return ListaPartidas.isEmpty();
		
	}  	



}




