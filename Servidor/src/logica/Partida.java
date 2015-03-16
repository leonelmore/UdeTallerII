package logica;

import java.io.Serializable;

import excepciones.LetraNoPerteneceAlTituloException;
import excepciones.LetraYaAdivinadaException;
import excepciones.TituloArriesgadoIncorrectoException;
import logica.Pelicula;

public class Partida implements Serializable{

	private int numero;
	private int puntaje;
	private boolean acertada;
	private boolean enCurso;
	private Pelicula peli;
	private String textoHastaElMomento;

	// Constructor
	public Partida(int n, int punt, boolean a, boolean eC, Pelicula p,
			String texto) {

		this.numero = n;
		this.puntaje = punt;
		this.acertada = a;
		this.enCurso = eC;
		this.peli = p;
		this.textoHastaElMomento = texto;
	}

	public Partida(Pelicula p) {

		this.numero = 0;
		this.puntaje = 0;
		this.acertada = false;
		this.enCurso = true;
		this.peli = p;
		this.textoHastaElMomento = p.ocultarLetras();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public boolean getAcertada() {
		return acertada;
	}

	public void setAcertada(boolean acertada) {
		this.acertada = acertada;
	}

	public boolean getEnCurso() {
		return enCurso;
	}

	public void setEnCurso(boolean enCurso) {
		this.enCurso = enCurso;
	}

	public Pelicula getPelicula() {
		return peli;

	}

	public String getTextoHastaElMomento() {
		return textoHastaElMomento;
	}
	
	public void setTextoHastaElMomento(String unTexto){
		textoHastaElMomento = unTexto;
	}

	public void setPartida(int numero) {
		this.numero = numero;

	}

	public int ingresarLetra(char unaLetra) throws LetraNoPerteneceAlTituloException, LetraYaAdivinadaException {
		int cambioPuntaje = 0;
		char letra = Character.toUpperCase(unaLetra);
		if(!textoHastaElMomento.contains(String.valueOf(letra))){
			
			if(peli.perteneceTitulo(letra)){
				puntaje++;
				cambioPuntaje = 1;
				setTextoHastaElMomento(peli.ocultarLetras(textoHastaElMomento,letra));
				this.setAcertada(!textoHastaElMomento.contains("-"));
				//VERIFICAR CAMBIO
				this.setEnCurso(!acertada);
			}
			else{
				puntaje=puntaje-5;
				cambioPuntaje = -5;
				throw new LetraNoPerteneceAlTituloException("");
			}
		}
		else{
			throw new LetraYaAdivinadaException("");
		}
		return cambioPuntaje;
		
		
	}

	public boolean arriesgarPelicula(String unTitulo)  {
		/*String partes[]=unTitulo.split(" ");
		String tituloUsuario="";
		for(int i=0;i<partes.length;i++){
			if(!partes[i].equals(""))
				tituloUsuario=tituloUsuario+partes[i]+" ";
		}
		tituloUsuario=tituloUsuario.trim();
		tituloUsuario=tituloUsuario.toUpperCase();*/
		
		String tituloArriesgadoFormateado = unTitulo.toUpperCase().trim()
				.replaceAll(" +", " ");
		this.setEnCurso(false);
		boolean valida=false;
		if(tituloArriesgadoFormateado.equals(this.getPelicula().getTitulo())){
			this.setPuntaje(puntaje+50);
			valida=true;
			this.setTextoHastaElMomento(this.getPelicula().getTitulo());
		}
		else{
			this.setPuntaje(puntaje-50);
		}
		this.setAcertada(valida);
		return valida;
	}

}
