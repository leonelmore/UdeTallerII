package controladores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import logica.VOs.VODatosPelicula;
import logica.VOs.VOLogin;
import persistencia.CargarConfiguracion;
import servidor.IFachadaRMI;
import excepciones.ConfiguracionException;
import excepciones.JugadorRepetidoException;
import excepciones.PeliculaRepetidaException;
import grafica.VentanaAltaPeliculas;


public class ControladorVentanaAltaPeliculas {
	private VentanaAltaPeliculas miventana;
	private IFachadaRMI fac;
	
	public ControladorVentanaAltaPeliculas(VentanaAltaPeliculas miv) 	
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
	public void AltaPelicula(String nombre,String descripcion)
	{
		VODatosPelicula vop = new VODatosPelicula(nombre, descripcion);
		
		
	
			try {
				if (!nombre.equals("")){
					if (!descripcion.equals("")){
						fac.registrarPelicula(vop);
						miventana.MostrarMensaje("La pelicula de titulo:" + vop.getTitulo() + " se registro de forma exitosa.");
					} else 
					{
						
						miventana.MostrarMensaje("Por favor ingrese un codigo.");
					}
				}else 
				{
					miventana.MostrarMensaje("Por favor ingrese un nombre.");	
					
				}
			} catch (RemoteException e) {
				miventana.MostrarMensaje("Error en RMI.");
				e.printStackTrace();
			} catch (PeliculaRepetidaException e) {
				miventana.MostrarMensaje("La Pelicula ya existe en el Sistema.");
				e.printStackTrace();
			}
		
	}
	
}
