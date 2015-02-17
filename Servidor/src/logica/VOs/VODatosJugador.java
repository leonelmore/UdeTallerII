package logica.VOs;

public class VODatosJugador {
	private String nombre;
	private String codigo;
	private int puntaje;
	private int peliculasAcertadas;
	private int peliculasErradas;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public int getPeliculasAcertadas() {
		return peliculasAcertadas;
	}
	public void setPeliculasAcertadas(int peliculasAcertadas) {
		this.peliculasAcertadas = peliculasAcertadas;
	}
	public int getPeliculasErradas() {
		return peliculasErradas;
	}
	public void setPeliculasErradas(int peliculasErradas) {
		this.peliculasErradas = peliculasErradas;
	} 
	
}
