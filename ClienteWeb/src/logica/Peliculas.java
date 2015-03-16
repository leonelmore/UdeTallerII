package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import logica.VOs.VODatosPelicula;
import utilidades.MonitorRW;



public class Peliculas implements Serializable{
	
	
	
	
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
	 public ArrayList<VODatosPelicula>  iteratorPelicula(){
			
		 ArrayList<VODatosPelicula> list = new ArrayList<VODatosPelicula>();
		 MonitorRW.getInstancia().comienzoLectura();	
		 Iterator<Pelicula> iter = this.peliculas.values().iterator();
			while (iter.hasNext()) {
				Pelicula pel = (Pelicula) iter.next();
				VODatosPelicula voPel = new VODatosPelicula(pel.getTitulo(),
						pel.getDescripcion());
				list.add(voPel);
			}
			MonitorRW.getInstancia().terminoLectura();
			
			return list;
			
		}


	public Pelicula eligirAzar(Iterator<Pelicula> listaPeliculaJugador) {
		ArrayList<Pelicula> peliculasUsuario=new ArrayList<Pelicula>();
		
		while(listaPeliculaJugador.hasNext()){
			Pelicula pelicula=listaPeliculaJugador.next();
			peliculasUsuario.add(pelicula);
		}
		
		Pelicula eligida=null;
		Iterator<Pelicula> listaTodasPeliculas=peliculas.values().iterator();
		ArrayList<Pelicula>listadoPeliculasFaltantes=new ArrayList<Pelicula>();
		
		while(listaTodasPeliculas.hasNext()){
			Pelicula pelicula=listaTodasPeliculas.next();
			if(!peliculasUsuario.contains(pelicula)){
				listadoPeliculasFaltantes.add(pelicula);
			}
			
		}
		
		int cantidad=listadoPeliculasFaltantes.size();
		int azar=(int)(Math.random()*(cantidad - 1)); //ACA HICE CAMBIO
		eligida=(Pelicula)listadoPeliculasFaltantes.get(azar);

		return eligida;

	}


	public int cantidadPeliculas() {
		
		return peliculas.size();
	}

	
	
	
}	
	
	
