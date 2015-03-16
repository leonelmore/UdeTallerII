package grafica;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controladores.controladorVentanaPartidasJugador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import logica.VOs.VODatosPartida;

public class VentanaListarPartidaJugador extends JFrame {
	private JTextField txtNombreJug;
	private JTable tablePartidas;
	private controladorVentanaPartidasJugador micontrolador;
	private ArrayList<VODatosPartida> voPart = null;
	public VentanaListarPartidaJugador() {
		getContentPane().setBackground(Color.WHITE);
		micontrolador = new controladorVentanaPartidasJugador(this);
		setBounds(300, 300, 698, 296);
		setTitle("Consultar Partidas Jugador");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 682, 265);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Ingrese Nombre Jugador:");
		lblNombre.setBounds(28, 11, 174, 31);
		panel.add(lblNombre);
		
		txtNombreJug = new JTextField();
		txtNombreJug.setBounds(212, 16, 147, 20);
		panel.add(txtNombreJug);
		txtNombreJug.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = txtNombreJug.getText();
				
				
				
				visualizarPartidas ( nombre);
				
				
			}
		});
		btnConsultar.setBounds(384, 15, 107, 23);
		panel.add(btnConsultar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 53, 644, 198);
		panel.add(scrollPane);
		
		tablePartidas = new JTable();
		scrollPane.setViewportView(tablePartidas);
		tablePartidas.setEnabled(false);
		
		BufferedImage icon = null;
		Image fondo = null;
		try {
			
			icon = ImageIO.read(getClass().getResource("/grafica/icono.jpg"));
		
			this.setIconImage(icon);
			//this.repaint();
			
			
		} catch (IOException e) {}
		
	}
	public void MostrarMensaje(String msj)
	{
		JOptionPane.showMessageDialog(this, msj);
	}
	public void visualizarPartidas (String nombre)
	{  if (nombre.isEmpty())
	   {
		MostrarMensaje("Por favor ingrese nombre jugador.");
		txtNombreJug.setText("");
	   }else
	   {
		   
		DefaultTableModel modeloTabla = new DefaultTableModel();
	    modeloTabla.addColumn("Nro.Partida");
       	modeloTabla.addColumn("Puntaje");
       	modeloTabla.addColumn("Titulo");
       	modeloTabla.addColumn("Texto hasta el momento");
       	modeloTabla.addColumn("Finalizada ?");
       	modeloTabla.addColumn("Acertada ?");
		voPart = micontrolador.ConsultarPartidas(nombre);	
		
		
	  if (voPart.isEmpty())
	  {
		  MostrarMensaje("Este jugador no tiene partidas jugadas.");
		  String nropart = "";
		    String puntaje = "";
		    		String titu = "";
		    		String text = "";
		    		String fin = "";
		    		
		    		
		    		
		    		String acert= "";
		    		
		    		
		    String[] fila = {nropart ,puntaje, titu,text,fin,acert };
		    modeloTabla.addRow(fila);
	  }
	  else{
       
        	
        	
		    Object[][] contenido = new Object[6][voPart.size()];
		for (int i = 0; i < voPart.size(); i++)
		{       
			    String nropart = Integer.toString( voPart.get(i).getNumeroDePartida());
			    String puntaje = Integer.toString( voPart.get(i).getPuntaje());
			    		String titu = voPart.get(i).getDatoPelicula().getTitulo();
			    		String text = voPart.get(i).getTextoAdivinado();
			    		String fin;
			    		if(voPart.get(i).isPartidaFinalizada())
			    		{
			    		 fin =  "Si"; 
			    		 }else 
			    		 {
			    		 fin = "No";
			    		 }
			    		
			    		String acert;
			    		if(voPart.get(i).isPeliculaAcertada())
			    		{
			    			acert= "Si";
			    		}else 
			    		{
			    			acert = "No";
			    		}
			    String[] fila = {nropart ,puntaje, titu,text,fin,acert };
			    modeloTabla.addRow(fila);

			   
		} 
       
       
	  }
	  tablePartidas.setModel(modeloTabla);
	   }
		
		
	}
}
