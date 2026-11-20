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
import estructuraTPvista.Barrio.MenuBarrioPN;
import estructuraTPvista.Comuna.ComunaAltaPN;
import estructuraTPvista.Comuna.ComunaConsultarPN;
import estructuraTPvista.Comuna.MenuComunaPN;



public class MarcoPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MarcoPrincipal frame = new MarcoPrincipal();
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
	private MenuComunaPN mComuna = new MenuComunaPN();
	private MenuBarrioPN mBarrio = new MenuBarrioPN();
	private ComunaAltaPN altaComuna = new ComunaAltaPN();
	private ComunaConsultarPN consultarComuna = new ComunaConsultarPN();
	
	private BarrioAltaPN altaBarrio = new BarrioAltaPN();
	
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
		
		// COMUNAS
		panel.add(altaComuna, "AltaComuna");
		panel.add(consultarComuna, "ConsultarComuna");
		
		// BARRIOS
		panel.add(altaBarrio, "AltaBarrio");
		
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
				cl.show(panel, "PersonajeVisual");
			}
		});
	}
}

