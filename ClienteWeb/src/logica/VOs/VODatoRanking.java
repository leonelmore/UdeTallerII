package logica.VOs;

import java.io.Serializable;

import logica.Jugador;

public class VODatoRanking implements Serializable{
	private String nombre;
	private int puntaje;
	private int peliculasAcertadas;
	private int peliculasErradas;
	
	public VODatoRanking(Jugador jugador){
		nombre=jugador.getUserName();
		puntaje=jugador.getPuntaje();
		peliculasAcertadas=jugador.getCantPelAcer();
		peliculasErradas=jugador.getCantPelErre();
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public int getPeliculasErradas() {
		return peliculasErradas;
	}
	public void setPeliculasErradas(int peliculasErradas) {
		this.peliculasErradas = peliculasErradas;
	}
	public int getPeliculasAcertadas() {
		return peliculasAcertadas;
	}
	public void setPeliculasAcertadas(int peliculasAcertadas) {
		this.peliculasAcertadas = peliculasAcertadas;
	}
}
