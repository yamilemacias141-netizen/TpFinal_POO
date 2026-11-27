package estructuraTP.Utilidades;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import estructuraDAO.Sentencias;
import estructuraTPmodelo.Persona;
import estructuraTPvista.Persona.PersonaAltaPN;

public class DialogoPersona {
	private static String nombreBarrio = null;
	
	public String mostrarDialogomigrarDeBarrio(String nombreBarrioEliminado, JPanel panel) {
		Window parentWindow = SwingUtilities.getWindowAncestor(panel);
        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        JPanel panelDialgo = new JPanel();
        panelDialgo.setLayout(null);
        JButton botonCerrar = new JButton("Cerrar");
        int i = 0;
        boolean salir = true;
        ArrayList<String> nombres = Sentencias.barrio.consultarNombresBarrio();
        while(salir || i < nombres.size()) {
        	if(nombres.get(i).equals(nombreBarrioEliminado)) {
        		salir = false;
        		nombres.remove(i);
        	}
        	i++;
        }
        
        
		JLabel labelMigrarBarrios = new JLabel("Tienes que migar a las personas asociadas a un barrio");
		labelMigrarBarrios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelMigrarBarrios.setHorizontalAlignment(SwingConstants.CENTER);
		labelMigrarBarrios.setBounds(10, 11, 280, 22);
		panelDialgo.add(labelMigrarBarrios);
		
		JComboBox comboBoxComunas = new JComboBox();
		comboBoxComunas.setBounds(74, 73, 160, 31);
		comboBoxComunas.setModel(new DefaultComboBoxModel(nombres.toArray()));
		panelDialgo.add(comboBoxComunas);
		
		JLabel labelEligeUno = new JLabel("Elige uno");
		labelEligeUno.setHorizontalAlignment(SwingConstants.CENTER);
		labelEligeUno.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelEligeUno.setBounds(10, 40, 280, 22);
		panelDialgo.add(labelEligeUno);

		botonCerrar.setBounds(201, 121, 89, 23);
		panelDialgo.add(botonCerrar);
		
		JButton botonCambiar = new JButton("Cambiar");
		botonCambiar.setBounds(74, 121, 89, 23);
		panelDialgo.add(botonCambiar);
        dialog.setSize(350, 300);
        
        botonCerrar.addActionListener(e -> dialog.dispose());
        
        botonCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombreBarrio = comboBoxComunas.getSelectedItem() + "";
				dialog.dispose();
			}
		});
        
        dialog.setLocationRelativeTo(parentWindow);
        dialog.setContentPane(panelDialgo);
        dialog.setVisible(true);
        
        return nombreBarrio;
	}
	
	
	public void mostrarDialogoConCargarPersona(Persona p,JPanel panel) {
		Window parentWindow = SwingUtilities.getWindowAncestor(panel);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        PersonaAltaPN alta = new PersonaAltaPN(p, p.getDNI());
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(alta, BorderLayout.CENTER);
        dialog.setSize(730, 700);
        dialog.setLocationRelativeTo(parentWindow);
        
        dialog.setVisible(true);
	}
}
