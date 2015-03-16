package controladores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import logica.VOs.VODatosPartida;
import logica.VOs.VODatosPelicula;
import logica.VOs.VOLogin;
import persistencia.CargarConfiguracion;
import servidor.IFachadaRMI;
import excepciones.ClaveIncorrectaException;
import excepciones.ConfiguracionException;
import excepciones.JugadorNoExisteException;
import excepciones.PeliculaRepetidaException;

import grafica.VentanaListarPartidaJugador;

public class controladorVentanaPartidasJugador {
	
	
	private VentanaListarPartidaJugador miventana;
	private IFachadaRMI fac;
	
	public controladorVentanaPartidasJugador(VentanaListarPartidaJugador miv) 
	{
		miventana = miv;
		
		try {
			fac = (IFachadaRMI)	Naming.lookup("//" + CargarConfiguracion.getData().ipServidor + ":" + CargarConfiguracion.getData().puertoRMI + "/" + CargarConfiguracion.getData().nombreObjRMI);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfiguracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public ArrayList<VODatosPartida> ConsultarPartidas(String nombre)
	{
		ArrayList<VODatosPartida> vop = new ArrayList<VODatosPartida>();
		
		VOLogin unJugador  = new VOLogin();
		unJugador.setUsuario(nombre);
					
					try {
						vop = fac.listarPartidasDeUnJugador(unJugador);
					} catch (RemoteException e) {
						miventana.MostrarMensaje("Error RMI.");
						e.printStackTrace();
					} catch (ClaveIncorrectaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JugadorNoExisteException e) {
						miventana.MostrarMensaje("Jugador Inexistente.");
						e.printStackTrace();
					}	
			
			
			return vop;
	}	
	}

