package grafica;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import excepciones.ClaveIncorrectaException;
import excepciones.JugadorNoExisteException;
import excepciones.LetraNoPerteneceAlTituloException;
import excepciones.LetraYaAdivinadaException;
import excepciones.NoExistePartidaEnCurso;
import excepciones.NoHayMasPeliculasException;
import excepciones.PartidaEnCursoException;
import excepciones.TituloArriesgadoIncorrectoException;
import logica.Fachada;
import logica.VOs.VODatosPartida;
import logica.VOs.VODatosPelicula;
import logica.VOs.VOLogin;
import servidor.FachadaRMI;
import servidor.IFachadaRMI;

import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class VentanaIniciarPartida extends JFrame {

	private JFrame frame;
	private JTextField txtLetra;
	private JTextField textArriesga;
	private IFachadaRMI fachada;
	private JLabel lblTitulo;
	private VOLogin vol;
	private JButton btnJugar;
	private JButton btnIngresarLetra;
	private JButton btnArriesga ;
	private String tituloEnJuego;
	private JLabel lblUsuario;
	private JLabel lblDescripcion;
	private JLabel lblPuntaje;
	private JLabel lblMensajeError;
	private JTable table;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	
	
	
	/**
	 * Launch the application.
	 */

	
	
	private String ocultarLetras(String titulo){
		int cantidad=titulo.length();
		String palabraFinal="";
		for(int i=0;i<cantidad;i++){
			if(titulo.charAt(i)!=' '){
				palabraFinal=palabraFinal+"-";
			}
			else{
				palabraFinal=palabraFinal+" ";
			}
		}
		return palabraFinal;
		}
	
	public VentanaIniciarPartida(VOLogin vol,IFachadaRMI fachada) {
		initialize();
		lblUsuario.setText("Usuario : "+vol.getUsuario());
		modeloTabla.addColumn("Letra");
		lblDescripcion = new JLabel("");
		lblDescripcion.setForeground(Color.PINK);
		lblDescripcion.setVisible(false);
		lblDescripcion.setBounds(10, 74, 280, 14);
		frame.getContentPane().add(lblDescripcion);
		
		JButton btnNewButton = new JButton("Mostrar Descripcion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDescripcion.setVisible(true);
			}
		});
		btnNewButton.setBounds(437, 183, 156, 23);
		frame.getContentPane().add(btnNewButton);
		
		lblPuntaje = new JLabel("Puntaje :");
		lblPuntaje.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPuntaje.setBounds(486, 12, 107, 14);
		frame.getContentPane().add(lblPuntaje);
		
		lblMensajeError = new JLabel("Letras Erradas ");
		lblMensajeError.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMensajeError.setForeground(Color.RED);
		lblMensajeError.setBounds(376, 11, 61, 20);
		frame.getContentPane().add(lblMensajeError);
		
		JLabel lblNewLabel = new JLabel("Esta pelicula contiene la letra . . . .");
		lblNewLabel.setBounds(10, 103, 217, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 147, 308, 99);
		frame.getContentPane().add(panel);
		this.fachada=fachada;
		this.vol=vol;
		try {
			JLabel jLabel;
			panel.setLayout(null);
			jLabel = new JLabel(new ImageIcon(VentanaIniciarPartida.class.getResource("/grafica/let.jpg")));
			jLabel.setHorizontalAlignment(SwingConstants.LEFT);
			jLabel.setBounds(new Rectangle(0, 0, 307, 98));
			panel.add(jLabel);
			
			JLabel lblLetrasValidasEn = new JLabel("Letras validas en el sistema :");
			lblLetrasValidasEn.setBounds(10, 123, 217, 14);
			frame.getContentPane().add(lblLetrasValidasEn);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(379, 36, 48, 210);
			frame.getContentPane().add(scrollPane);
			
			table = new JTable();
			table.setEnabled(false);
			scrollPane.setViewportView(table);
		} catch (Exception e) {
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 619, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				try {
					btnJugar.setEnabled(false);
					btnArriesga.setEnabled(true);
					btnIngresarLetra.setEnabled(true);
					fachada.iniciarNuevaPartida(vol);
					VODatosPartida partida=fachada.visualizarPartidaEnCurso(vol);
					String titulo=partida.getTextoAdivinado();
					titulo=titulo.replace(" ","  ");
					titulo=titulo.replace("-","- ");
					tituloEnJuego=partida.getDatoPelicula().getTitulo();
					lblTitulo.setText(titulo);
					lblDescripcion.setText(partida.getDatoPelicula().getDescripcion());
					lblPuntaje.setText("Puntaje : "+partida.getPuntaje());
					
					textArriesga.setText("");
					
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage());
				} catch (NoHayMasPeliculasException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage());
				} catch (JugadorNoExisteException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage());
				} catch (PartidaEnCursoException e) {
					VODatosPartida partida;
					try {
						partida = fachada.visualizarPartidaEnCurso(vol);
						String titulo=partida.getTextoAdivinado();
						titulo=titulo.replace(" ","  ");
						titulo=titulo.replace("-","- ");
						lblTitulo.setText(titulo);
						btnJugar.setEnabled(false);
						lblDescripcion.setText(partida.getDatoPelicula().getDescripcion());
						lblPuntaje.setText("Puntaje : "+partida.getPuntaje());
					} catch (RemoteException e1) {
						JOptionPane.showMessageDialog(frame, e1.getMessage());
					} catch (ClaveIncorrectaException e1) {
						JOptionPane.showMessageDialog(frame, e1.getMessage());
					} catch (JugadorNoExisteException e1) {
						JOptionPane.showMessageDialog(frame, e1.getMessage());
					} catch (NoExistePartidaEnCurso e1) {
						JOptionPane.showMessageDialog(frame, e1.getMessage());
					}
					
				} catch (ClaveIncorrectaException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}catch(Exception e){
					
					JOptionPane.showMessageDialog(frame,"Error : "+e.getMessage());
				}
			}
		});
		btnJugar.setBounds(437, 40, 156, 23);
		frame.getContentPane().add(btnJugar);
		
		btnIngresarLetra = new JButton("Ingresar Letra");
		btnIngresarLetra.setEnabled(false);
		btnIngresarLetra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(txtLetra.getText().trim()!="")
					
					if(txtLetra.getText().length()==1){
						try {
							fachada.ingresarUnaLetra(vol, txtLetra.getText().charAt(0));
							VODatosPartida partida=fachada.visualizarPartidaEnCurso(vol);
							String titulo=partida.getTextoAdivinado();
							if(titulo.contains("-")){
								titulo=titulo.replace(" ","  ");
								titulo=titulo.replace("-","- ");
								
							}
							else{
								JOptionPane.showMessageDialog(frame,"Felicitacion adivino la ultima letra");
								btnJugar.setEnabled(true);
								btnArriesga.setEnabled(false);
								btnIngresarLetra.setEnabled(false);
							}
							lblTitulo.setText(titulo);
							lblPuntaje.setText("Puntaje : "+partida.getPuntaje());
							
							
					} catch (RemoteException e1) {
						JOptionPane.showMessageDialog(frame, e1.getMessage());
						}
						catch (LetraNoPerteneceAlTituloException ex){
							VODatosPartida partida;
							try {
								partida = fachada.visualizarPartidaEnCurso(vol);
								lblPuntaje.setText("Puntaje : "+partida.getPuntaje());
								
								// agregado michell
								
						       
						        	
						        	
								    Object[][] contenido = new Object[1][1];
								 
									
								    String[] fila = { txtLetra.getText() };
									    modeloTabla.addRow(fila);

									   
							
						        table.setModel(modeloTabla);
								// fin agregado michell
								//lblLetraInvalida.setText(lblLetraInvalida.getText()+"-"+txtLetra.getText().charAt(0));
								//lblMensajeError.setText("La letra "+txtLetra.getText().charAt(0)+" no pertenece al titulo");
							}catch(Exception e1) {
								JOptionPane.showMessageDialog(frame, e1.getMessage());
							}
							
						}
						 catch (Exception e1) {
							JOptionPane.showMessageDialog(frame, e1.getMessage());
						
						} 
						finally{
							txtLetra.setText("");
						}
					}
					else{
						JOptionPane.showMessageDialog(frame, "No se puede ingresar más de una letra");
					}
						
			}
		});
		btnIngresarLetra.setBounds(223, 99, 146, 23);
		frame.getContentPane().add(btnIngresarLetra);
		
		txtLetra = new JTextField();
		txtLetra.setForeground(Color.BLACK);
		txtLetra.setBounds(202, 100, 20, 20);
		frame.getContentPane().add(txtLetra);
		txtLetra.setColumns(10);
		
		textArriesga = new JTextField();
		textArriesga.setBounds(10, 257, 417, 20);
		frame.getContentPane().add(textArriesga);
		textArriesga.setColumns(10);
		
		btnArriesga = new JButton("Arriesgar");
		btnArriesga.setEnabled(false);
		btnArriesga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				try {
					btnJugar.setEnabled(true);
					btnArriesga.setEnabled(false);
					btnIngresarLetra.setEnabled(false);
					fachada.arriesgarPelicula(vol, textArriesga.getText());
							JOptionPane.showMessageDialog(frame, "Felicitaciones ganaste la partida");
							VODatosPartida partida=fachada.visualizarPartidaEnCurso(vol);
							lblTitulo.setText(tituloEnJuego);
							lblPuntaje.setText("Puntaje : "+partida.getPuntaje());
							
				}
						catch (TituloArriesgadoIncorrectoException ex){
							
							try {
								
								lblTitulo.setText(tituloEnJuego);
								
								JOptionPane.showMessageDialog(frame, "Lamentablemente no acertaste a la pelicula");
								VODatosPartida partida=fachada.visualizarPartidaEnCurso(vol);
								lblPuntaje.setText("Puntaje : "+partida.getPuntaje());
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(frame, e1.getMessage());
								
							}

							
						}
						catch (Exception e1) {
							JOptionPane.showMessageDialog(frame, e1.getMessage());
							
						} 
					
				
				
			}}
				
				
			
		);
		btnArriesga.setBounds(437, 256, 156, 23);
		frame.getContentPane().add(btnArriesga);
		
		lblTitulo = new JLabel("");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(10, 40, 230, 23);
		frame.getContentPane().add(lblTitulo);
		
		lblUsuario = new JLabel("Puntaje :");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBounds(10, 11, 183, 14);
		frame.getContentPane().add(lblUsuario);
		frame.setVisible(true);
		
		
	}
}
