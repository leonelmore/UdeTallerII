package grafica;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import controladores.ControladorAltaJugador;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.io.IOException;

public class VentanaAltaJugador  extends JFrame {
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private JButton btnRegistrar;
	private ControladorAltaJugador micontrolador;
	public VentanaAltaJugador() {
		getContentPane().setBackground(Color.WHITE);
		micontrolador = new ControladorAltaJugador(this);
		getContentPane().setLayout(null);
		setTitle("Alta Jugador");
		
		setBounds(300, 300, 400, 200);
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setBounds(34, 28, 61, 17);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo:");
		lblNewLabel_1.setForeground(SystemColor.desktop);
		lblNewLabel_1.setBounds(34, 76, 61, 17);
		getContentPane().add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(129, 25, 188, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(129, 73, 188, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombre = txtNombre.getText();
				String codigo = txtCodigo.getText();
				
				micontrolador.AltaJugador(nombre, codigo);	
				txtNombre.setText("");
				txtCodigo.setText("");
			}
		});
		
		
		btnRegistrar.setBounds(129, 126, 89, 23);
		getContentPane().add(btnRegistrar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");
				txtCodigo.setText("");
				
			}
		});
		btnLimpiar.setBounds(228, 126, 89, 23);
		getContentPane().add(btnLimpiar);
		
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
