package grafica;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import controladores.ControladorVentanaAltaPeliculas;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class VentanaAltaPeliculas  extends JFrame {
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private ControladorVentanaAltaPeliculas micontrolador;
   private Image icon;
	public VentanaAltaPeliculas() {
		micontrolador = new ControladorVentanaAltaPeliculas(this);
		getContentPane().setLayout(null);
		setTitle("Alta Pelicula");
		
		setBounds(300, 300, 400, 200);
		
	
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.BLACK);
		panel.setBounds(0, 0, 384, 162);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Nombre:");
		lblTitulo.setBounds(34, 37, 59, 14);
		panel.add(lblTitulo);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(34, 81, 77, 14);
		panel.add(lblDescripcion);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 34, 210, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(133, 78, 210, 20);
		panel.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = txtNombre.getText();
				String codigo = txtDescripcion.getText();
				micontrolador.AltaPelicula(nombre,codigo);
				txtNombre.setText("");
				txtDescripcion.setText("");
			}
		});
		btnNewButton.setBounds(130, 133, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpiar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtNombre.setText("");
				txtDescripcion.setText("");
			}
		});
		btnNewButton_1.setBounds(254, 133, 89, 23);
		panel.add(btnNewButton_1);
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
}
