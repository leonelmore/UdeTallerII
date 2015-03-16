package grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import controladores.ControladorLogin;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private ControladorLogin micontrolador;


	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		micontrolador = new ControladorLogin(this);
		setTitle("Loguearse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 151);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(40, 28, 70, 14);
		contentPane.add(lblNombre);
		
		JLabel lblCodigo = new JLabel("Codigo : ");
		lblCodigo.setBounds(40, 53, 70, 14);
		contentPane.add(lblCodigo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 25, 193, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(133, 50, 193, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String codigo = txtCodigo.getText();
				micontrolador.loguearse(nombre,codigo);	
				
			}
		});
		btnAceptar.setBounds(133, 78, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(237, 78, 89, 23);
		contentPane.add(btnCancelar);
	}
	
	public void MostrarMensaje(String msj)
	{
		JOptionPane.showMessageDialog(this, msj);
	}
}
