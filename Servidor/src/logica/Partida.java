package logica;
import logica.Pelicula;
public class Partida {

	private int numero;
	private int puntaje;
	private boolean acertada;
	private boolean enCurso;
	private Pelicula peli;
    private String textoHastaElMomneto;


//Constructor
public Partida ( int n, int punt, boolean a, boolean eC, Pelicula p, String texto){
	
this.numero=n;
this.puntaje=punt;
this.acertada=a;
this.enCurso=eC;
this.peli=p;
this.textoHastaElMomneto=texto;
}

public int getNumero(){	
	return numero;
}	
public void setNumero(int numero){
	this.numero=numero;
}



public int getPuntaje(){	
	return puntaje;
}
public void setPuntaje(int puntaje){
	this.puntaje=puntaje;
}



public boolean getAcertada(){	
	return acertada;
}
public void setAcertada(boolean acertada){
	this.acertada=acertada;
}





public boolean getEnCurso(){	
	return enCurso;
}


public void setEnCurso(boolean enCurso){
	this.enCurso=enCurso;
}






public Pelicula getPelicula(){	
	return peli;

}


public String textoHastaElMomento(){	
	return textoHastaElMomneto;

}








}


