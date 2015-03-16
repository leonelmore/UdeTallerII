package grafica;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controladores.controladorPrincipalAdmin;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;












import logica.VOs.VODatoRanking;
import logica.VOs.VODatosJugador;
import logica.VOs.VODatosPelicula;

import javax.swing.ScrollPaneConstants;

import persistencia.CargarConfiguracion;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PrincipalAdministrador extends JFrame {
	private ArrayList<VODatosPelicula> pelis = null;
	private ArrayList<VODatosJugador> jugadores = null;
	private ArrayList <VODatoRanking> ranking = null;
	private controladorPrincipalAdmin micontrolador ; 
	private  VODatosPelicula pel = null;
	private JTable table;
	private JTable table_jugadores;
	private JTable table_ranking;
	
	public PrincipalAdministrador() {
		
		
		
		
		setTitle("Principal Administrador");
		micontrolador = new controladorPrincipalAdmin(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 698, 438);
		getContentPane().setLayout(null);
		
	
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBounds(0, 0, 682, 400);
		try {
			panelPrincipal.setLayout(null);
		} catch (Exception e) {
		}
		getContentPane().add(panelPrincipal);
		
		JButton btnJugador = new JButton("+ Jugador");
		btnJugador.setBounds(178, 8, 109, 23);
		panelPrincipal.add(btnJugador);
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(590, 375, 83, 23);
		panelPrincipal.add(btnGuardar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(564, 8, 109, 23);
		panelPrincipal.add(btnActualizar);
		
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaAbout about = new VentanaAbout();
				about.setVisible(true);
			}
		});
		btnAbout.setBounds(21, 375, 68, 23);
		panelPrincipal.add(btnAbout);
		
		JButton btnPelicula = new JButton("+ Pelicula");
		btnPelicula.setBounds(21, 8, 109, 23);
		panelPrincipal.add(btnPelicula);
		
		
		
		table = new JTable();
		table.setBounds(23, 5, 289, 81);
		table.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 42, 652, 101);
		scrollPane.setViewportView(table);
		panelPrincipal.add(scrollPane);
		
		JScrollPane scrollPane_jugadores = new JScrollPane();
		scrollPane_jugadores.setBounds(21, 151, 652, 101);
		panelPrincipal.add(scrollPane_jugadores);
		
		table_jugadores = new JTable();
		table_jugadores.setEnabled(false);
		scrollPane_jugadores.setViewportView(table_jugadores);
		
		JScrollPane scrollPane_ranking = new JScrollPane();
		scrollPane_ranking.setBounds(21, 263, 652, 101);
		panelPrincipal.add(scrollPane_ranking);
		
		table_ranking = new JTable();
		table_ranking.setEnabled(false);
		scrollPane_ranking.setViewportView(table_ranking);
		
		JButton btnNewButton = new JButton("Partida X Jugador");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaListarPartidaJugador parJug = new VentanaListarPartidaJugador();
				parJug.setVisible(true);
			}
		});
		btnNewButton.setBounds(352, 8, 157, 23);
		panelPrincipal.add(btnNewButton);
		
		btnPelicula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAltaPeliculas altPel = new VentanaAltaPeliculas();
				altPel.setVisible(true);
				//altPel.setBounds(300, 300, 400, 200);
				
			}
		});
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();	
			}
		});
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				micontrolador.guardar();
			}
		});
		btnJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAltaJugador vJug = new VentanaAltaJugador();
				vJug.setVisible(true);
				//setBounds(300, 300, 442, 326);
			
			}
		});
		
		
		BufferedImage icon = null;
		Image fondo = null;
		try {
			
			icon = ImageIO.read(getClass().getResource("/grafica/icono.jpg"));
		
			this.setIconImage(icon);
			//this.repaint();
			
			
		} catch (IOException e) {}
		
		actualizarDatos();
	}
	
	public void CargarListaPeliculasTabla ( )
	{
		try {
			
			pelis = micontrolador.listarPelis();
		} 
		catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DefaultTableModel modeloTabla = new DefaultTableModel();
        if (pelis.size() > 0){
        	modeloTabla.addColumn("Titulo");
        	modeloTabla.addColumn("Descripcion");
        	
		    Object[][] contenido = new Object[2][pelis.size()];
		for (int i = 0; i < pelis.size(); i++)
		{  
			
			    String[] fila = { pelis.get(i).getTitulo(),  pelis.get(i).getDescripcion() };
			    modeloTabla.addRow(fila);

			   
		} 
        } else 
        {
        	
        	 String[] fila = { "No existen ",  "peliculas en el sistema" };
        	 modeloTabla.addRow(fila);
        }
        table.setModel(modeloTabla);
		
		
	}
	public void cargarListJugadoresTabla ()
	{
		try {
			jugadores = micontrolador.listarJugadores();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DefaultTableModel modeloTabla = new DefaultTableModel();
        if (jugadores.size() > 0){
        	modeloTabla.addColumn("Nombre");
        	modeloTabla.addColumn("Cod.Ingreso");
        	modeloTabla.addColumn("Puntaje");
        	modeloTabla.addColumn("Pel.Acertadas");
        	modeloTabla.addColumn("Pel.Erradas");
		    Object[][] contenido = new Object[5][jugadores.size()];
		for (int i = 0; i < jugadores.size(); i++)
		{  
			
			    String[] fila = { jugadores.get(i).getNombre(),  jugadores.get(i).getCodigo(), Integer.toString(jugadores.get(i).getPuntaje()),Integer.toString(jugadores.get(i).getPeliculasAcertadas()),Integer.toString(jugadores.get(i).getPeliculasErradas()) };
			    modeloTabla.addRow(fila);

			   
		} 
        } else 
        {
        	
        	 String[] fila = { "No existen ",  "jugadores en el sistema." };
        	 modeloTabla.addRow(fila);
        }
        table_jugadores.setModel(modeloTabla);
	}
	
    public void cargarRanking ()
    {
    	try {
			ranking = micontrolador.listarRanking();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    	DefaultTableModel modeloTabla = new DefaultTableModel();
        if (ranking.size() > 0){
        	modeloTabla.addColumn("Nombre");
        	modeloTabla.addColumn("Puntaje Total");
        	modeloTabla.addColumn("Peliculas acertadas");
        	modeloTabla.addColumn("Peliculas erradas");
        	
		    Object[][] contenido = new Object[4][ranking.size()];
		for (int i = 0; i < ranking.size(); i++)
		{  
			
			    String[] fila = { ranking.get(i).getNombre(),   Integer.toString(ranking.get(i).getPuntaje()),Integer.toString(ranking.get(i).getPeliculasAcertadas()),Integer.toString(ranking.get(i).getPeliculasErradas()) };
			    modeloTabla.addRow(fila);

			   
		} 
        } else 
        {
        	
        	 String[] fila = { "No existen ",  "jugadores para rankear" };
        	 modeloTabla.addRow(fila);
        }
        table_ranking.setModel(modeloTabla);
		
    	
    }
    
    public void actualizarDatos()
    {
    	cargarRanking ();
    	cargarListJugadoresTabla ();
    	CargarListaPeliculasTabla ();
    }
	public void MostrarMensaje(String msj)
	{
		JOptionPane.showMessageDialog(this, msj);
	}
}
