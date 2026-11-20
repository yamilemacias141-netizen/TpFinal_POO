package estructuraTPvista;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import estructuraTPvista.Barrio.BarrioAltaPN;
import estructuraTPvista.Barrio.BarrioConsultar;
import estructuraTPvista.Barrio.MenuBarrioPN;

import estructuraTPvista.Comuna.ComunaAltaPN;

import estructuraTPvista.Comuna.ComunaConsultarPN;
import estructuraTPvista.Comuna.MenuComuna;



public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel panel = new JPanel();
	
	//MENUS-----------
	private MenuInicio mInicio = new MenuInicio();
	private MenuComuna mComuna = new MenuComuna();
	private MenuBarrioPN mBarrio = new MenuBarrioPN();
	//COMUNAS---------
	private ComunaAltaPN cAlta = new ComunaAltaPN();
	private ComunaConsultarPN cConsulta = new ComunaConsultarPN();
	//BARRIO
	private BarrioAltaPN bAlta= new BarrioAltaPN();
	private BarrioConsultar bConsultar = new BarrioConsultar();
	// BOTONES BARRA DE TAREAS--------------------
	private JButton botonBarraComuna;
	private JButton botonBarraBarrio;
	private JButton botonBarraPersona;
	private JButton botonBarraMenu;
	// BOTONES DEL MENU COMUNA ------------------
	private JButton botonAltaComuna;
	private JButton botonConsultaComuna;
	// BOTONES DE MENU BARRIO
	private JButton botonAltaBarrio;
	private JButton botonConsultaBarrio;
	
	private JToolBar toolBar = new JToolBar();
	
	private CardLayout cl = new CardLayout();
	
	public Principal() {

		
		//--------------
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 800, 800);
		contentPane = new JPanel();
		
		contentPane.setLayout(null);
		
		cargarBarraTareas();
		cargarBotonesMenuPrincipal();
		usosBotones();
		cargarBotonesMenuComuna(); //temporal para pruebas
		cargarBotonesMenuBarrio();
		usosBotonesComuna(); //temporal
		
		panel.setBounds(0, 38, 784, 723);
		contentPane.add(panel);
		panel.setLayout(cl);
		
		// AGREGAR PANELES AL LAYOUT-----------------
		panel.add(mInicio, "MenuPrincipal");
		panel.add(mComuna, "MenuComuna");
		panel.add(cAlta, "AltaComuna");
		panel.add(cConsulta, "ConsultaComuna");
		panel.add(mBarrio , "MenuBarrio");
		panel.add(bAlta , "AltaBarrio");
		panel.add(bConsultar, "ConsultaBarrio");
		
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
	
	private void cargarBotonesMenuComuna() {
		botonAltaComuna = new JButton("Cargar");
		botonAltaComuna.setBounds(256, 230, 253, 117);
		mComuna.add(botonAltaComuna);
		
		botonConsultaComuna = new JButton("Mostrar");
		botonConsultaComuna.setBounds(256, 434, 253, 117);
		mComuna.add(botonConsultaComuna);
	}
	
	private void cargarBotonesMenuBarrio()
	{
		botonAltaBarrio = new JButton("Cargar");
		botonAltaComuna.setBounds(256, 230, 253, 117);
		mBarrio.add(botonAltaBarrio);
		
		botonConsultaBarrio = new JButton("Mostrar");
		botonConsultaComuna.setBounds(256, 434, 253, 117);
		mBarrio.add(botonConsultaBarrio);
	}
	private void cargarBarraTareas() {
		toolBar.setBounds(0, 0, 784, 41);
		contentPane.add(toolBar);
	}
	private void usosBotonesComuna() {
		botonAltaComuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "AltaComuna");
			}
		});

		botonConsultaComuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "ConsultaComuna");
			}
		});
	}
	
	private void usosBotonesBarrio()
	{
		botonAltaBarrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "AltaBarrio");
			}
		});

		botonConsultaBarrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "ConsultaBarrio");
			}
		});
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
				cargarBotonesMenuComuna();
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
				cl.show(panel, "PersonajeVisual");
			}
		});
	}
}

