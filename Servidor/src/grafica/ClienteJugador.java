package grafica;

import java.awt.EventQueue;

public class ClienteJugador {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				/*
				 * Aqui lanzamos los clientes LAN, tanto el jugador como el adminstrador.
				 */
				
				PrincipalAdministrador frame = new PrincipalAdministrador(); //ESTE LANZA EL CLIENTE ADMIN
				//VentanaLogin frame = new VentanaLogin(); //ESTE LANZA EL CLIENTE JUGADOR
				
				frame.setVisible(true);
				
			}
		});
	}
	
}
