package logica.VOs;

import java.io.Serializable;

public class VODatosPelicula implements Serializable{
	private String titulo;
	private String descripcion;
	public VODatosPelicula (String tit, String desc)  //Constructor PVODatosPelicula
	{
		this.titulo = tit;
		this.descripcion= desc;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
