package estructuraTPvista;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import estructuraTPvista.Barrio.BarrioAltaPN;
import estructuraTPvista.Barrio.BarrioConsultarPN;
import estructuraTPvista.Comuna.ComunaAltaPN;
import estructuraTPvista.Comuna.ComunaConsultarPN;
import estructuraTPvista.Persona.PersonaAltaPN;
import estructuraTPvista.Persona.PersonaConsultarPN;



public class MarcoPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JPanel panel = new JPanel();
	
	//MENUS-----------
	private MenuInicio mInicio = new MenuInicio();
	
	private MenuGenerico mComuna = new MenuGenerico("AltaComuna", "ConsultarComuna", "Menu de comuna");
	private MenuGenerico mBarrio = new MenuGenerico("AltaBarrio", "ConsultarBarrio", "Menu de barrio");
	private MenuGenerico mPersona = new MenuGenerico("AltaPersona", "ConsultarPersona", "Menu de persona");
	
	// Comuna
	private ComunaAltaPN altaComuna = new ComunaAltaPN();
	private ComunaConsultarPN consultarComuna = new ComunaConsultarPN();
	
	/// barrio
	private BarrioConsultarPN consultarBarrio  = new BarrioConsultarPN();	
	private BarrioAltaPN altaBarrio = new BarrioAltaPN();
	
	// persona
	private PersonaAltaPN altaPersona = new PersonaAltaPN();
	private PersonaConsultarPN consultarPersona = new PersonaConsultarPN();
	
	// BOTONES BARRA DE TAREAS--------------------
	private JButton botonBarraComuna;
	private JButton botonBarraBarrio;
	private JButton botonBarraPersona;
	private JButton botonBarraMenu;
	
	
	private JToolBar toolBar = new JToolBar();
	
	private CardLayout cl = new CardLayout();
	
	public MarcoPrincipal() {

		
		//--------------
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 800, 800);
		contentPane = new JPanel();
		
		contentPane.setLayout(null);
		
		cargarBarraTareas();
		cargarBotonesMenuPrincipal();
		usosBotones();
		
		panel.setBounds(0, 38, 784, 723);
		contentPane.add(panel);
		panel.setLayout(cl);
		
		// AGREGAR PANELES AL LAYOUT-----------------
		panel.add(mInicio, "MenuPrincipal");
		panel.add(mComuna, "MenuComuna");
		panel.add(mBarrio, "MenuBarrio");
		panel.add(mPersona, "MenuPersona");
		
		// COMUNAS
		panel.add(altaComuna, "AltaComuna");
		panel.add(consultarComuna, "ConsultarComuna");
		
		
		// BARRIOS
		panel.add(altaBarrio, "AltaBarrio");
		panel.add(consultarBarrio, "ConsultarBarrio");
		
		// PERSONAS
		panel.add(altaPersona, "AltaPersona");
		panel.add(consultarPersona, "ConsultarPersona");
		
		cl.show(panel, "MenuPrincipal");
		
		setContentPane(contentPane);
		// Acciones
		
	}
	
	private void cargarBotonesMenuPrincipal() {
		
		// TOOLBAR------------------------
		botonBarraMenu = new JButton("Menu");
		toolBar.add(botonBarraMenu);
		
		botonBarraComuna = new JButton("Comuna");
		toolBar.add(botonBarraComuna);
		
		botonBarraBarrio = new JButton("Barrio");
		toolBar.add(botonBarraBarrio);
		
		botonBarraPersona = new JButton("Persona");
		toolBar.add(botonBarraPersona);
	}
	
	
	private void cargarBarraTareas() {
		toolBar.setBounds(0, 0, 784, 41);
		contentPane.add(toolBar);
	}
	
	private void usosBotones() {
		// MENU-----------
		botonBarraMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "MenuPrincipal");
			}
		});
		// COMUNA------------
		botonBarraComuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "MenuComuna");
			}
		});
		
		// BARRIO--------------------
		botonBarraBarrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "MenuBarrio");
			}
		});
		
		// PERSONA----------------
		botonBarraPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "MenuPersona");
			}
		});
	}
	/*
	 * / TODO NO ACTUALIZAR LA TABLAS AL RECARGAR 
	 * 	CAMBIAR LO DE BARRIO en tipobario y los instanceof 
	 * 	revisar los barrios
	 */

}

