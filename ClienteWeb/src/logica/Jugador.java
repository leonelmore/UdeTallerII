package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import utilidades.MonitorRW;
import logica.VOs.VODatosJugador;
import logica.VOs.VODatosPartida;

public class Jugador implements Comparable<Jugador>, Serializable {
	
	//public static void main(String[] args) {
	
		private String codigo;
		private String userName;
        private int puntaje;
        private int cantPelAcer;
        private int cantPelErre;
		private Partidas listaPartidas;
		
		public Jugador (String cod, String userN, int punt, int cantPelAcer, int cantPelErre )  //Constructor Jugador
		{
			this.codigo = cod;
			this.userName= userN;
			this.puntaje = punt;
			this.cantPelAcer = cantPelAcer;
			this.cantPelErre = cantPelErre;
			listaPartidas=new Partidas();
		}
		
		public String getCodigo()
		{
			return codigo;
		}
		
		
		public String getUserName()
		{
			return userName;
		}
		public int getPuntaje()
		{
			return puntaje;
		}
		public int getCantPelAcer()
		{
			return cantPelAcer;
		}	
		public int getCantPelErre()
		{
			return cantPelErre;
		}	

		public void setCantPelAcer(int acertada){
			cantPelAcer=acertada;
		}
		
		public void setCantPelErre(int cantidadPeliculasErradas){
			cantPelErre = cantidadPeliculasErradas;
		}
		
		/*public void incrementar(int numero){
			cantPelAcer=cantPelAcer+numero;
		}*/
		
		public void setPuntaje(int nuevoPuntaje){
			this.puntaje = nuevoPuntaje;
		}
		
        public ArrayList<VODatosPartida> iteratorPartidas()
        {
        	
    		return listaPartidas.iteratorPartidas();
        	
        }
       
		public Partida ultimaPartida() {
			return listaPartidas.ultimaPartida();

		}
		
		public void insBack(Partida nueva){
			listaPartidas.InstBack(nueva);
		}
		
		public Iterator<Pelicula> listadoPeliculasJugadas(){
			return listaPartidas.listadoPeliculasJugadas();
		}

		public int getCantidadPartidas() {
			
			return listaPartidas.cantidadPartidas();
		}

		@Override
		public int compareTo(Jugador o) {
			int numero=this.puntaje-o.puntaje;
			if(numero==0){
				numero=this.cantPelAcer-o.getCantPelAcer();
			}
			return numero*-1;
		}
		
		
}
