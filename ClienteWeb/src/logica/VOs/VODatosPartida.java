package logica.VOs;

import java.io.Serializable;

import logica.Pelicula;

public class VODatosPartida implements Serializable {
	private int numeroDePartida;
	private int puntaje;
	private VODatosPelicula datoPelicula; //se puede usar para titulo o para descripcion
	private String textoAdivinado;
	private boolean partidaFinalizada;
	private boolean peliculaAcertada;
	public VODatosPartida(int n, int punt, boolean a, boolean eC, VODatosPelicula p,
			String texto) {

		this.numeroDePartida = n;
		this.puntaje = punt;
		this.peliculaAcertada = a;
		this.partidaFinalizada = eC;
		this.datoPelicula = p;
		this.textoAdivinado = texto;
	}
	public int getNumeroDePartida() {
		return numeroDePartida;
	}
	public void setNumeroDePartida(int numeroDePartida) {
		this.numeroDePartida = numeroDePartida;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public VODatosPelicula getDatoPelicula() {
		return datoPelicula;
	}
	public void setDatoPelicula(VODatosPelicula datoPelicula) {
		this.datoPelicula = datoPelicula;
	}
	public String getTextoAdivinado() {
		return textoAdivinado;
	}
	public void setTextoAdivinado(String textoAdivinado) {
		this.textoAdivinado = textoAdivinado;
	}
	public boolean isPartidaFinalizada() {
		return partidaFinalizada;
	}
	public void setPartidaFinalizada(boolean partidaFinalizada) {
		this.partidaFinalizada = partidaFinalizada;
	}
	public boolean isPeliculaAcertada() {
		return peliculaAcertada;
	}
	public void setPeliculaAcertada(boolean peliculaAcertada) {
		this.peliculaAcertada = peliculaAcertada;
	}
	
}
