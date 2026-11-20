package estructuraTPvista.Comuna;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import estructuraTP.Utilidades.Navegador;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MenuComunaPN extends JPanel {

	private JLabel labelTitulo;
	private JLabel labelPregunta;
	private JButton botonAltaComuna;
	private JButton botonConsultaComuna;
	
	public MenuComunaPN() {
		setLayout(null);
		setBounds(800, 400, 800, 800);
		crearLabels();
		crearBotones();
		usosBotones();
	}

	private void crearLabels() {
		labelTitulo = new JLabel("Menu de comuna");
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
		botonAltaComuna = new JButton("Cargar");
		botonAltaComuna.setBounds(256, 230, 253, 117);
		add(botonAltaComuna);
		
		botonConsultaComuna = new JButton("Mostrar");
		botonConsultaComuna.setBounds(256, 434, 253, 117);
		add(botonConsultaComuna);
	}
	
	private void usosBotones() {
		
		botonAltaComuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Navegador.cambiarPantalla(MenuComunaPN.this, "AltaComuna");
			}
		});

		botonConsultaComuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Navegador.cambiarPantalla(MenuComunaPN.this, "ConsultarComuna");
			}
		});
	}
	
}
