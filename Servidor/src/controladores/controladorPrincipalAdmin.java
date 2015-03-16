package controladores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import logica.VOs.VODatoRanking;
import logica.VOs.VODatosJugador;
import logica.VOs.VODatosPelicula;
import persistencia.CargarConfiguracion;
import servidor.IFachadaRMI;
import excepciones.ConfiguracionException;
import excepciones.GuardarDatosException;
import grafica.PrincipalAdministrador;
import grafica.VentanaLogin;
import servidor.IFachadaRMI;



public class controladorPrincipalAdmin {
	private PrincipalAdministrador miventa;
	private IFachadaRMI fac;
	
	public controladorPrincipalAdmin(PrincipalAdministrador miv)
	{
		miventa = miv;
		
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

	public ArrayList<VODatosPelicula> listarPelis () throws RemoteException 
	{
		ArrayList<VODatosPelicula> pelis = new ArrayList<VODatosPelicula> ();	
		
		pelis = fac.listarPeliculas();
		return pelis;

	}
    public ArrayList<VODatosJugador> listarJugadores () throws RemoteException
    {
    	ArrayList<VODatosJugador> jugadores = new ArrayList<VODatosJugador> ();	
    	jugadores = fac.listarJugadores();
		return jugadores;
    	
    }
    
    public ArrayList<VODatoRanking> listarRanking () throws RemoteException
    {
    	ArrayList<VODatoRanking> jugadores = new ArrayList<VODatoRanking> ();	
    	jugadores = fac.getRankingGeneral();
		return jugadores;
    	
    }
    public void guardar ()
    {
    	try {
			fac.guardarCambios();
			miventa.MostrarMensaje("Se guardo el estado actual del sistema de forma exitosa.");
		} catch (RemoteException e) {
			miventa.MostrarMensaje("Error RMI.");
			e.printStackTrace();
		} catch (GuardarDatosException e) {
			miventa.MostrarMensaje("Error al guardar los datos.");
			e.printStackTrace();
		} catch (ConfiguracionException e) {
			miventa.MostrarMensaje("Error en el archivo de configuracion");
			e.printStackTrace();
		}
    	
    	
    }

}
