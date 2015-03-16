package controladores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;






import logica.VOs.VOLogin;
import persistencia.CargarConfiguracion;
import servidor.IFachadaRMI;
import excepciones.JugadorRepetidoException;
import excepciones.ConfiguracionException;
import grafica.VentanaAltaJugador;


public class ControladorAltaJugador {

	private VentanaAltaJugador miventanajug;
	private IFachadaRMI fac;
	
	public ControladorAltaJugador(VentanaAltaJugador miv)
	{
		miventanajug = miv;
		
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
			e.printStackTrace();
		}
	}
	

	public void AltaJugador(String nombre,String codigo)
	{
		VOLogin vol = new VOLogin();
		vol.setUsuario(nombre);
		vol.setCodigo(codigo);
		
		
			try {
				
				if (! nombre.equals("")){
					if(! codigo.equals("")){
				fac.registrarJugador(vol);
				miventanajug.MostrarMensaje("Jugador registrado de forma exitosa.");
					}else 
					{
						miventanajug.MostrarMensaje("Por favor ingrese un codigo.");
					}
				}else 
				{
					miventanajug.MostrarMensaje("Por favor ingrese un nombre.");	
				}
			} catch (RemoteException e) {
				miventanajug.MostrarMensaje("Error RMI");
				e.printStackTrace();
			} catch (JugadorRepetidoException e) {
				miventanajug.MostrarMensaje("Error: Jugador repetido");
				e.printStackTrace();
			}
		
			
		
			
		
	
	}


}