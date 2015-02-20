package logica;

import logica.Pelicula;
import java.util.*;



public class Peliculas {
	
	
	
	
	private TreeMap<String,Pelicula> peliculas;
	
	public Peliculas(){
		this.peliculas= new TreeMap<String,Pelicula>();
		
	}
	
	
	public boolean member(String s){
		
		return peliculas.containsKey(s);
	}
	
	public void insert (Pelicula p){
		
		this.peliculas.put(p.getTitulo(),p);
		
		
	}
	
	
	public Pelicula find(String s){
		
		return this.peliculas.get(s);
		
		
	}
	
	
	
	
}	
	
	
