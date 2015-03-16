package servlets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.VOs.VODatoRanking;
import utilities.FachadaManager;

public class RankingServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession laSesion = req.getSession();
		ArrayList<VODatoRanking> listaRanking = null;
		boolean error = false;
		
		try {
			String unPuertoRMI = super.getInitParameter("puertoRMI");
			String unaIpServidor = super.getInitParameter("ipServidor");
			String unObjetoRMI = super.getInitParameter("objetoRMI");
			FachadaManager.getInstancia().setParametros(unaIpServidor, unPuertoRMI, unObjetoRMI);
			FachadaManager.getInstancia().conectar();
			listaRanking = FachadaManager.getInstancia().fachada.getRankingGeneral();
			synchronized (laSesion){
				laSesion.setAttribute("listaRanking", listaRanking);
			}
		} catch (NotBoundException e) {
			error = true;
			synchronized (laSesion) {
				laSesion.setAttribute("mensajeError", e.getMessage());
			}
		} catch (RemoteException e) {
			error = true;
			synchronized (laSesion) {
				laSesion.setAttribute("mensajeError", e.getMessage());
			}
		} catch (MalformedURLException e) {
			error = true;
			synchronized (laSesion) {
				laSesion.setAttribute("mensajeError", e.getMessage());
			}
		}
		
		RequestDispatcher rd;
		if (error == true) {
			rd = req.getRequestDispatcher("Error.jsp");	
		}
		else{
			if (listaRanking.size() > 0) {
				rd = req.getRequestDispatcher("Ranking.jsp");
			}
			else {
				rd = req.getRequestDispatcher("RankingVacio.jsp");
			}
		}
		
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
