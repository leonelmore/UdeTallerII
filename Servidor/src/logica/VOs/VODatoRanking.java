package logica.VOs;

public class VODatoRanking {
	private String nombre;
	private int puntaje;
	private int peliculasAcertadas;
	private int peliculasErradas;
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
