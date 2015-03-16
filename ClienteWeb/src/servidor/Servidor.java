package servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import excepciones.CargarDatosException;
import excepciones.ConfiguracionException;
import persistencia.Persistencia;
import utilidades.CargarConfiguracion;
import logica.Fachada;

/* Esta clase es la que se corre en servidor.
 * Hace que se cree el objeto RMI y que se exponga mediante TCP.
 * Va a intentar cargar un archivo de datos guardado, y si no puede crea uno desde cero.
 */
public class Servidor {
	static Fachada f = null;
	static FachadaRMI fRMI= null;
	
	public static void main(String[] args) {
		try {
			//Intenta recuperar datos
			f = Persistencia.recuperarDatos(CargarConfiguracion.getData().rutaYArchivoPersistencia);
		} catch (CargarDatosException e) {
			//Se supone aqui un error con el archivo de datos, no existe o similar
			System.out.println("Imposible recuperar datos.");
			System.out.println("Inicializando datos...");
			f = new Fachada();
		} catch (ConfiguracionException e) {
			//Este es un error irrecuperable. Hay que abortar!
			System.out.println(e.getMessage());
		}
		
		try {
			if (f != null){
				//Lanzar objeto RMI
				System.out.println("Iniciando servidor en " + "//" + CargarConfiguracion.getData().ipServidor + ":" + CargarConfiguracion.getData().puertoRMI + "/" + CargarConfiguracion.getData().nombreObjRMI + "...");
				fRMI = new FachadaRMI(f);
				LocateRegistry.createRegistry(CargarConfiguracion.getData().puertoRMI);
				Naming.rebind("//" + CargarConfiguracion.getData().ipServidor + ":" + CargarConfiguracion.getData().puertoRMI + "/" + CargarConfiguracion.getData().nombreObjRMI, fRMI);
				System.out.println("Servidor iniciado.");
			}
			else {
				System.out.println("No se pudo iniciar el servidor.");
			}
		}
		catch(ConfiguracionException e){
			System.out.println(e.getMessage());
			System.out.println("Servidor detenido.");
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Error en ejecución remota.");
		} catch (MalformedURLException e) {
			System.out.println("La url a publicar está mal formada. Esta es la url intentada:");
			try {
				System.out.println("//" + CargarConfiguracion.getData().ipServidor + ":" + CargarConfiguracion.getData().puertoRMI + "/" + CargarConfiguracion.getData().nombreObjRMI);
			} catch (ConfiguracionException e1) {
				System.out.println(e1.getMessage());
			}
			System.out.println("Servidor detenido.");
		}
	}

}
