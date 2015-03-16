package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.ArrayList;
import java.rmi.registry.LocateRegistry;

import excepciones.*;
import logica.VOs.*;
import persistencia.CargarConfiguracion;
import servidor.IFachadaRMI;



public class PruebaServidor {

	public static void main(String[] args) {
		IFachadaRMI fachada = null;
		BufferedReader br;
		boolean fin = false;
		String input;
		System.out.println("Programa de prueba Servidor (UDE Taller II - 2015)");
		System.out.println("**************************************************");
		try {
			
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Conectar con el servidor? (1 = si, 0 = no)");
			if (br.readLine().charAt(0) == '1') {
				System.out.println("Conectando con el servidor...");
				fachada = (IFachadaRMI)	Naming.lookup("//" + CargarConfiguracion.getData().ipServidor + ":" + CargarConfiguracion.getData().puertoRMI + "/" + CargarConfiguracion.getData().nombreObjRMI);
				System.out.println("Conectado con el servidor!");
			}
				
			while (!fin){
			
				try {
					mostrarMenu();
					input = br.readLine();
					
					//Se determina que función se eligió probar, con un select case.
					switch (input){
					case "1": registrarPelicula(fachada,br);
					break;
					case "2": listarPeliculas(fachada,br);
					break;
					case "3": registrarJugador(fachada,br);
					break;
					case "4": listarJugadores(fachada,br);
					break;
					case "5": listarPartidasDeUnJugador(fachada,br);
					break;
					case "6": guardarCambios(fachada,br);
					break;
					case "7": loguearseParaJugar(fachada,br);
					break;
					case "8": iniciarNuevaPartida(fachada,br);
					break;
					case "9": visualizarPartidaEnCurso(fachada,br);
					break;
					case "10": ingresarUnaLetra(fachada,br);
					break;
					case "11": arriesgarPelicula(fachada,br);
					break;
					case "12": rankingGeneral(fachada,br);
					break;
					case "0": fin=true;
					}
				}
				catch (Exception e){
					System.out.println();
					System.out.println("Ha ocurrido una excepción:");
					System.out.println(e.getMessage());
					System.out.println("--- COMIENZO DEL STACK TRACE ---");
					e.printStackTrace(System.out);
					System.out.flush();
					System.out.println("--- FIN DEL STACK TRACE ---");
					System.out.println();
					System.out.println("Presione enter para mostar menú");
					br.readLine();
				}
				
			}
		}
		catch (Exception e){ 
			//Atrapa excepciones de conexión con el sevidor 
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	private static void rankingGeneral(IFachadaRMI fachada, BufferedReader br) throws IOException {
		System.out.println("Mostrar ranking general.-");
		System.out.println("Llamando fachada.getRankingGeneral...");
		ArrayList<VODatoRanking> listaRanking = fachada.getRankingGeneral();
		System.out.println("Fin de llamada.");
		System.out.println();
		System.out.println("Indice - nombre, puntaje, acertadas, erradas.");
		for (int i = 0;i < listaRanking.size();i++) {
			System.out.println(i + " - " + listaRanking.get(i).getNombre() + ", " + listaRanking.get(i).getPuntaje() + ", " + listaRanking.get(i).getPeliculasAcertadas() + ", " + listaRanking.get(i).getPeliculasErradas()  + "." );
		}
		System.out.println("Presione enter para mostar menú");
		br.readLine();
	}

	private static void arriesgarPelicula(IFachadaRMI fachada, BufferedReader br) throws IOException, NoExistePartidaEnCurso, ClaveIncorrectaException, JugadorNoExisteException, TituloArriesgadoIncorrectoException {
		VOLogin unJugador = new VOLogin();
		String unTitulo;
		System.out.println("Arriesgar un título.-");
		System.out.println("Nombre usuario:");
		unJugador.setUsuario(br.readLine());
		System.out.println("Codigo de usuario:");
		unJugador.setCodigo(br.readLine());
		System.out.println("Titulo a arriesgar:");
		unTitulo = br.readLine();
		System.out.println("Llamando fachada.arriesgarPelicula...");
		fachada.arriesgarPelicula(unJugador, unTitulo);
		System.out.println("Fin de llamada.");
		System.out.println("Presione enter para mostar menú");
		br.readLine();
		
	}

	private static void ingresarUnaLetra(IFachadaRMI fachada, BufferedReader br) throws IOException, JugadorNoExisteException, NoExistePartidaEnCurso, ClaveIncorrectaException, LetraNoPerteneceAlTituloException, LetraYaAdivinadaException {
		VOLogin unJugador = new VOLogin();
		char unaLetra;
		System.out.println("Ingresar una letra.-");
		System.out.println("Nombre usuario:");
		unJugador.setUsuario(br.readLine());
		System.out.println("Codigo de usuario:");
		unJugador.setCodigo(br.readLine());
		System.out.println("Letra a ingresar:");
		unaLetra = br.readLine().charAt(0);
		System.out.println("Llamando fachada.ingresarUnaLetra...");
		fachada.ingresarUnaLetra(unJugador, unaLetra);
		System.out.println("Fin de llamada.");
		System.out.println("Presione enter para mostar menú");
		br.readLine();
	}

	private static void visualizarPartidaEnCurso(IFachadaRMI fachada,
			BufferedReader br) throws IOException, ClaveIncorrectaException, JugadorNoExisteException, NoExistePartidaEnCurso {
		VOLogin unJugador = new VOLogin();
		System.out.println("Visualizar partida en curso.-");
		System.out.println("Nombre usuario:");
		unJugador.setUsuario(br.readLine());
		System.out.println("Codigo de usuario:");
		unJugador.setCodigo(br.readLine());
		System.out.println("Llamando fachada.visualizarPartidaEnCurso...");
		VODatosPartida unaPartida = fachada.visualizarPartidaEnCurso(unJugador);
		System.out.println("Fin de llamada.");
		//mostramos los datos de la película
		System.out.println("Descripción: " + unaPartida.getDatoPelicula().getDescripcion());
		System.out.println("Numero de partida: " + unaPartida.getNumeroDePartida());
		System.out.println("Puntaje: " + unaPartida.getPuntaje());
		System.out.println("Texto adivinado hasta el momento: " + unaPartida.getTextoAdivinado());
		System.out.println("Esta finalizada?: " + unaPartida.isPartidaFinalizada());
		System.out.println("Esta acertada?: " + unaPartida.isPeliculaAcertada());
		System.out.println();
		System.out.println("Presione enter para mostar menú");
		br.readLine();
		
	}

	private static void iniciarNuevaPartida(IFachadaRMI fachada,
			BufferedReader br) throws IOException, NoHayMasPeliculasException, JugadorNoExisteException, PartidaEnCursoException, ClaveIncorrectaException {
		VOLogin unJugador = new VOLogin();
		System.out.println("Iniciar nueva partida de un jugador.-");
		System.out.println("Nombre usuario:");
		unJugador.setUsuario(br.readLine());
		System.out.println("Codigo de usuario:");
		unJugador.setCodigo(br.readLine());
		System.out.println("Llamando fachada.iniciarNuevaPartida...");
		fachada.iniciarNuevaPartida(unJugador);
		System.out.println("Fin de llamada.");
		System.out.println("Presione enter para mostar menú");
		br.readLine();
	}

	private static void loguearseParaJugar(IFachadaRMI fachada,
			BufferedReader br) throws IOException ,  ClaveIncorrectaException , JugadorNoExisteException{
		boolean loginExitoso = false;
		VOLogin unJugador = new VOLogin();
		System.out.println("Loguearse para jugar.-");
		System.out.println("Nombre usuario:");
		unJugador.setUsuario(br.readLine());
		System.out.println("Codigo de usuario:");
		unJugador.setCodigo(br.readLine());
		System.out.println("Llamando fachada.loguearseParaJugar...");
		loginExitoso = fachada.loguearseParaJugar(unJugador);
		System.out.println("Fin de llamada.");
		System.out.println("El login fue exitoso? : " + loginExitoso);
		System.out.println("Presione enter para mostar menú");
		br.readLine();
	}

	public static void guardarCambios(IFachadaRMI fachada, BufferedReader br) throws IOException, GuardarDatosException, ConfiguracionException{
		System.out.println("Guardar cambios.-");
		System.out.println("Presione enter para realizar llamada");
		br.readLine();
		System.out.println("Llamando fachada.guardarCambios...");
		fachada.guardarCambios();
		System.out.println("Fin de llamada.");
		System.out.println("Presione enter para mostar menú");
		br.readLine();
	}
	
	public static void listarPartidasDeUnJugador(IFachadaRMI fachada, BufferedReader br) throws IOException, ClaveIncorrectaException, JugadorNoExisteException{
		VOLogin unJugador = new VOLogin();
		System.out.println("Listar partidas de un jugador.-");
		System.out.println("Nombre usuario:");
		unJugador.setUsuario(br.readLine());
		System.out.println("Llamando fachada.listarPartidasDeUnJugador...");
		ArrayList<VODatosPartida> unaLista = fachada.listarPartidasDeUnJugador(unJugador);
		System.out.println("Fin de llamada.");
		System.out.println("Indice - titulo, numero, finalizada, acertada, puntaje, texto adivinado.");
		for (int i = 0;i < unaLista.size();i++) {
			System.out.println(i + " - " + unaLista.get(i).getDatoPelicula().getTitulo() + ", " + unaLista.get(i).getNumeroDePartida() + ", " + unaLista.get(i).isPartidaFinalizada() + ", " + unaLista.get(i).isPeliculaAcertada()  + ", " + unaLista.get(i).getPuntaje() + ", " + unaLista.get(i).getTextoAdivinado() + "." );
		}
		System.out.println("Presione enter para mostar menú");
		br.readLine();
	}

	public static void listarJugadores(IFachadaRMI fachada, BufferedReader br) throws IOException{
		System.out.println("Listar jugadores.-");
		System.out.println("Presione enter para realizar llamada");
		br.readLine();
		System.out.println("Llamando fachada.listarJugadores...");
		ArrayList<VODatosJugador> unaLista = fachada.listarJugadores();
		System.out.println("Fin de llamada.");
		System.out.println("Indice - nombre, puntaje, peliculas acertadas, peliculas erradas.");
		for (int i = 0;i < unaLista.size();i++) {
			System.out.println(i + " - " + unaLista.get(i).getNombre() + ", " + unaLista.get(i).getPuntaje() + ", " + unaLista.get(i).getPeliculasAcertadas() + ", " + unaLista.get(i).getPeliculasErradas() + "." );
		}
		System.out.println("Presione enter para mostar menú");
		br.readLine();
	}
	
	public static void registrarJugador(IFachadaRMI fachada, BufferedReader br) throws IOException, JugadorRepetidoException{
		VOLogin datosJugador = new VOLogin();
		System.out.println("Registrar una nuevo jugador.-");
		System.out.println("Nombre usuario:");
		datosJugador.setUsuario(br.readLine());
		System.out.println("Codigo:");
		datosJugador.setCodigo(br.readLine());
		System.out.println("Llamando fachada.registrarJugador...");
		fachada.registrarJugador(datosJugador);
		System.out.println("Fin de llamada.");
		System.out.println("Presione enter para mostar menú");
		br.readLine();
	}
	
	public static void listarPeliculas(IFachadaRMI fachada, BufferedReader br) throws IOException{
		System.out.println("Listar películas.-");
		System.out.println("Presione enter para realizar llamada");
		br.readLine();
		System.out.println("Llamando fachada.listarPeliculas...");
		ArrayList<VODatosPelicula> unaLista = fachada.listarPeliculas();
		System.out.println("Fin de llamada.");
		for (int i = 0;i < unaLista.size();i++) {
			System.out.println(i + " - " + unaLista.get(i).getTitulo());
		}
		System.out.println("Presione enter para mostar menú");
		br.readLine();
	}
	
	public static void registrarPelicula(IFachadaRMI fachada, BufferedReader br) throws PeliculaRepetidaException, IOException{
		
		//VODatosPelicula unVOPelicula = new VODatosPelicula();
		System.out.println("Registrar una nueva película.-");
		System.out.println("Titulo:");
		String titulo = br.readLine();
		//unVOPelicula.setTitulo(br.readLine());
		System.out.println("Descripción:");
		//unVOPelicula.setDescripcion(br.readLine());
		String descripcion = br.readLine();
		System.out.println("Llamando fachada.registrarPelicula...");
		VODatosPelicula unVOPelicula = new VODatosPelicula(titulo,descripcion);
		fachada.registrarPelicula(unVOPelicula);
		System.out.println("Fin de llamada.");
		System.out.println("Presione enter para mostar menú");
		br.readLine();
	}
	
	public static void mostrarMenu(){
		System.out.println("");
		System.out.println("Elija una opción:");
		System.out.println("1  - Registrar una nueva película");
		System.out.println("2  - Listar películas");
		System.out.println("3  - Registrar nuevo jugador");
		System.out.println("4  - Listar jugadores");
		System.out.println("5  - Listar partidas de un jugador");
		System.out.println("6  - Guardar cambios");
		System.out.println("7  - Loguearse para jugar");
		System.out.println("8  - Iniciar nueva partida");
		System.out.println("9  - Visualizar partida en curso");
		System.out.println("10 - Ingresar una letra");
		System.out.println("11 - Arriesgar película");
		System.out.println("12 - Ranking general");
		System.out.println("0  - Salir");
	}
}
