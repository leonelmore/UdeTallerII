package utilidades;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import servidor.IFachadaRMI;

//Esta es la clase estática que conecta con el FachadaRMI

/*
 * EJEMPLO DE USO:
 * 
 * (Al momento de iniciar la ejecución del programa)
 * * FachadaManager.getInstancia().setParametros(unaIp, unPRMI, unObjRMI);
 * * FachadaManager.getInstancia().conectar();
 * 
 * Este es un ejemplo del formato que hay que seguir:
 * unaIP = "10.10.10.10"
 * unPRMI = "1100"
 * unObjRMI = "MiObjetoRMI"
 * 
 * Si no se inician estos valores, se tira una MalformedURLException con un e.getMessage de "Falto iniciar los valores de conexión".
 * 
 * Luego, para acceder a la fachada se ejecuta lo siguiente:
 * * unaListaVOs = FachadaManager.getInstancia().fachada.getRankingGeneral();
 * 
 * NOTA: No iniciar los valores mas de una vez!
 * 
 */
public class FachadaManager {

	private static FachadaManager instancia=null;
	private IFachadaRMI fachada;
	private String ipServidor;
	private int puertoRMI;
	private String objetoRMI;
	
	public static FachadaManager getInstancia() {
		if (instancia == null) {
			instancia = new FachadaManager();
		}
		return instancia;
	}
	
	private FachadaManager() {
		this.ipServidor = null;
		this.puertoRMI = 0;
		this.objetoRMI = null;
		this.fachada = null;
	}
	
	public IFachadaRMI getFachadaRMI(){
		return fachada;
	}
	
	public void conectar() throws RemoteException, MalformedURLException, NotBoundException {
		if (ipServidor == null) {
			throw new MalformedURLException("Falto iniciar los valores de conexión.");
		}
		else {
			fachada = (IFachadaRMI)	Naming.lookup("//" + ipServidor + ":" + puertoRMI + "/" + objetoRMI);			
		}
	}
	
	public void setParametros(String unaIpServidor, int unPuertoRMI, String unObjetoRMI) {
		this.ipServidor = unaIpServidor;
		this.puertoRMI = unPuertoRMI;
		this.objetoRMI = unObjetoRMI;
	}
}
