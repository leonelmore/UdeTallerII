package logica;

public class Jugador {
	
	//public static void main(String[] args) {
	
		private String codigo;
		private String userName;
        private int puntaje;
        private int cantPelAcer;
		//private ListaPeliculas
		//LEONEL: En esta clase falta agregar la colección de partidas del jugador.
        
		public Jugador (String cod, String userN, int punt, int cantPelAcer )  //Constructor Jugador
		{
			this.codigo = cod;
			this.userName= userN;
			this.puntaje = punt;
			this.cantPelAcer = cantPelAcer;
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

}
