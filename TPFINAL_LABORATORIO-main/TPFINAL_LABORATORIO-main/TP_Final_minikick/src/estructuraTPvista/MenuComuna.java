package estructuraTPvista;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JLabel;

public class MenuComuna extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelTitulo;
	private JLabel labelPregunta;
	
	public MenuComuna() {
		setLayout(null);
		crearLabels();
	}

	private void crearLabels() {
		labelTitulo = new JLabel("Menu de comuna");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		labelTitulo.setBounds(43, 27, 300, 47);
		add(labelTitulo);
		
		labelPregunta = new JLabel("Elija uno");
		labelPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		labelPregunta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelPregunta.setBounds(115, 85, 154, 20);
		add(labelPregunta);
		
		
	}
}
