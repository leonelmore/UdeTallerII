package logica.VOs;

public class VODatosPartida {
	private int numeroDePartida;
	private int puntaje;
	private String datoPelicula; //se puede usar para titulo o para descripcion
	private String textoAdivinado;
	private boolean partidaFinalizada;
	private boolean peliculaAcertada;
	
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
	public String getDatoPelicula() {
		return datoPelicula;
	}
	public void setDatoPelicula(String datoPelicula) {
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
