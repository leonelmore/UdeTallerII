package grafica;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VentanaAbout  extends JFrame {
	public VentanaAbout() {
         setTitle("About");
		
		setBounds(300, 300, 758, 406);
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(365, 0, 377, 368);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 366, 368);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		try {
			JLabel jLabel;
			jLabel = new JLabel(new ImageIcon(getClass().getResource("/grafica/fondo.png")));
			jLabel.setBounds(new Rectangle(0, 0, 377, 368));
			panel.add(jLabel, null);
			
			JLabel jLabel1;
			jLabel1 = new JLabel(new ImageIcon(VentanaAbout.class.getResource("/grafica/about.jpg")));
			jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
			jLabel1.setBackground(Color.WHITE);
			jLabel1.setBounds(new Rectangle(0, 0, 366, 368));
			panel_1.add(jLabel1,null);
		} catch (Exception e) {
		}

	}
}
