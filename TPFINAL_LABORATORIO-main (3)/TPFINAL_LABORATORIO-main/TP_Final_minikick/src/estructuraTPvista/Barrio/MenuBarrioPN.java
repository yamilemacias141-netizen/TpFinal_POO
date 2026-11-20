package estructuraTPvista.Barrio;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import estructuraTP.Utilidades.Navegador;
import estructuraTPvista.Comuna.MenuComunaPN;

public class MenuBarrioPN extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelTitulo;
	private JLabel labelPregunta;
	private JButton botonAltaBarrio;
	private JButton botonConsultaBarrio;
	
	public MenuBarrioPN() {
		setLayout(null);
		setBounds(800, 400, 800, 800);
		crearLabels();
		crearBotones();
		usosBotones();
	}

	private void crearLabels() {
		labelTitulo = new JLabel("Menu de Barrio");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		labelTitulo.setBounds(239, 66, 300, 47);
		add(labelTitulo);
		
		labelPregunta = new JLabel("Elija uno");
		labelPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		labelPregunta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelPregunta.setBounds(308, 124, 154, 20);
		add(labelPregunta);
	}
	private void crearBotones() {
		botonAltaBarrio = new JButton("Cargar");
		botonAltaBarrio.setBounds(256, 213, 253, 117);
		add(botonAltaBarrio);
		
		botonConsultaBarrio = new JButton("Mostrar");
		botonConsultaBarrio.setBounds(256, 424, 253, 117);
		add(botonConsultaBarrio);
	}
	
	private void usosBotones() {
		
		botonAltaBarrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Navegador.cambiarPantalla(MenuBarrioPN.this, "AltaBarrio");
			}
		});

		botonConsultaBarrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Navegador.cambiarPantalla(MenuBarrioPN.this, "");
			}
		});
	}

}
