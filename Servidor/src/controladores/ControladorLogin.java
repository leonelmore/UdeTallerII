package controladores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import logica.VOs.VOLogin;
import excepciones.ClaveIncorrectaException;
import excepciones.ConfiguracionException;
import excepciones.JugadorNoExisteException;
import grafica.VentanaIniciarPartida;
import grafica.VentanaLogin;
import persistencia.CargarConfiguracion;
import servidor.IFachadaRMI;
import utilidades.FachadaManager;

public class ControladorLogin {

	private VentanaLogin miventana;
	private IFachadaRMI fac;
	
	public ControladorLogin(VentanaLogin miv)
	{
		miventana = miv;
		
		try {
			FachadaManager.getInstancia().setParametros(CargarConfiguracion.getData().ipServidor, CargarConfiguracion.getData().puertoRMI, CargarConfiguracion.getData().nombreObjRMI);
			FachadaManager.getInstancia().conectar();
			fac=FachadaManager.getInstancia().getFachadaRMI();
			//fac = (IFachadaRMI )	Naming.lookup("//" + CargarConfiguracion.getData().ipServidor + ":" + CargarConfiguracion.getData().puertoRMI + "/" + CargarConfiguracion.getData().nombreObjRMI);
		}  catch (RemoteException e) {
			JOptionPane.showMessageDialog(miv, "Error conexion al servidor");
			System.exit(0);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(miv,e.getMessage());// TODO Auto-generated catch block

		}
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void loguearse(String nombre,String codigo)
	{
		VOLogin vol = new VOLogin();
		vol.setUsuario(nombre);
		vol.setCodigo(codigo);
		try {
			fac.loguearseParaJugar(vol);
			new VentanaIniciarPartida(vol,fac);
			miventana.setVisible(false);
		} catch (RemoteException e) {
			miventana.MostrarMensaje("Error RMI");
			e.printStackTrace();
		} catch (ClaveIncorrectaException e) {
			miventana.MostrarMensaje("Clave incorrecta");
			e.printStackTrace();
		} catch (JugadorNoExisteException e) {
			miventana.MostrarMensaje("Jugador no existe");
			e.printStackTrace();
		}
	
	}
}
