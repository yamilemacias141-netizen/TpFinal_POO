package estructuraTP.Utilidades;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.awt.GridBagLayout;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import estructuraDAO.Sentencias;
import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Persona;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;
import estructuraTPvista.Barrio.BarrioAltaPN;
import estructuraTPvista.Comuna.ComunaAltaPN;
import estructuraTPvista.Persona.PersonaAltaPN;

public abstract class Dialogo {
	public static final DialogoPersona persona = new DialogoPersona();
	public static final DialogoBarrio barrio = new DialogoBarrio();
	public static final DialogoComuna comuna = new DialogoComuna();
	
	public static void mostrarDialogo(String mensaje, JPanel panel) {
        Window parentWindow = SwingUtilities.getWindowAncestor(panel);
        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parentWindow);
        JButton botonCerrar = new JButton("Cerrar");
        JLabel lblMensaje = new JLabel(mensaje, SwingConstants.CENTER);
        
        botonCerrar.addActionListener(e -> dialog.dispose());

        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(lblMensaje, BorderLayout.CENTER);
        dialog.getContentPane().add(botonCerrar, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
	
	
	
}
