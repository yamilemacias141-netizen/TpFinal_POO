package estructuraTPvista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MenuInicio extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelTitulo;
	private JLabel labelPregunta;
	
	public MenuInicio() {
		setLayout(null);
		
		crearLabels();

	}
	private void crearLabels() {
		labelTitulo = new JLabel("Bienvenido al sistema");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		labelTitulo.setBounds(47, 27, 300, 47);
		add(labelTitulo);
		
		labelPregunta = new JLabel("Elija uno");
		labelPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		labelPregunta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelPregunta.setBounds(115, 85, 154, 20);
		add(labelPregunta);
		
		
	}

}
