package logica;

import java.io.Serializable;

public class Pelicula implements Serializable{

	//public static void main(String[] args) {
		
	private String titulo;
	private String descripcion;
	
	public Pelicula (String tit, String desc)  //Constructor Pelicula
	{
		this.titulo = tit;
		this.descripcion= desc;
	}
	
	public String getTitulo()
	{
		return titulo;
	}
	
	public void setTitulo( String titulo){
		this.titulo= titulo;
	}
	
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion=descripcion;
	}
	
	public boolean equals(Object objeto){
		boolean valido=false;
		if(objeto instanceof Pelicula){
			Pelicula pelicula=(Pelicula)objeto;
			if(this.getTitulo().equals(pelicula.getTitulo()))
				valido=true;
		}
		return valido;
	}
	
	public String ocultarLetras(){
		int cantidad=titulo.length();
		String palabraFinal="";
		for(int i=0;i<cantidad;i++){
			if(titulo.charAt(i)!=' '){
				palabraFinal=palabraFinal+"-";
			}
			else{
				palabraFinal=palabraFinal+" ";
			}
		}
		return palabraFinal;
		}

	public boolean perteneceTitulo(char unaLetra) {
		return (titulo.contains(String.valueOf(unaLetra)));
	}

	public String ocultarLetras(String textoHastaElMomento, char unaLetra) {
		int cantidad=titulo.length();
		String palabraFinal="";
		for(int i=0;i<cantidad;i++){
			if(titulo.charAt(i)!=' '){
				if(titulo.charAt(i)==unaLetra)
					palabraFinal=palabraFinal+String.valueOf(unaLetra);
				else{
					palabraFinal=palabraFinal+String.valueOf(textoHastaElMomento.charAt(i));
				}
			}
			else{
				palabraFinal=palabraFinal+" ";
			}
		}
		return palabraFinal;
	}	
}
	


