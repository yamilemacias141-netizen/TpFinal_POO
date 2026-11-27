package estructuraTP.Utilidades;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;
import estructuraTPvista.Comuna.ComunaAltaPN;

public class DialogoComuna {
public  void mostrarDialogoConTablaComuna(Comuna c,ArrayList<Barrio> b, JPanel panel) {
		
		Window parentWindow = SwingUtilities.getWindowAncestor(panel);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(590, 320);
        dialog.setLocationRelativeTo(parentWindow);
        JButton botonCerrar = new JButton("Cerrar");
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 170, 493, 261);
		
		Object[] columnas = new Object[] {"Nombre" , "Numero", "Barrio", "Tipo Barrio"};
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
		 
        JTable tabla =  new JTable();
		tabla.setDefaultEditor(Object.class, null);
		tabla.setBounds(57, 122, 300, 112);
		for(Barrio ba: b) {
			if(ba instanceof TrazadoFormal) {
				TrazadoFormal tz = (TrazadoFormal) ba;
				modelo.addRow(new Object[] {c.getNombre(), c.getNumeroComuna(), tz.getNombre(), "Trazado Formal"});
			}else if (ba instanceof UrbanizacionPlanificada) {
				UrbanizacionPlanificada up = (UrbanizacionPlanificada) ba;
				modelo.addRow(new Object[] {c.getNombre(), c.getNumeroComuna(), up.getNombre(), "Urbanizacion Planificada"});
			}else if(ba instanceof ComplejoHabitacional) {
				ComplejoHabitacional up = (ComplejoHabitacional) ba;
				modelo.addRow(new Object[] {c.getNombre(), c.getNumeroComuna(), up.getNombre(), "Complejo habitacional"});
			}
			
		}
		
		tabla.setModel(modelo);
		scrollPane.setViewportView(tabla);
        
        botonCerrar.addActionListener(e -> dialog.dispose());

        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
        dialog.getContentPane().add(botonCerrar, BorderLayout.SOUTH);

        dialog.setVisible(true);
	}

	public void mostrarDialogoConCargarComuna(Comuna c, JPanel panel) {
		Window parentWindow = SwingUtilities.getWindowAncestor(panel);

		JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
		ComunaAltaPN alta = new ComunaAltaPN(c.getNombre(), c.getNumeroComuna());
		dialog.getContentPane().setLayout(new BorderLayout());
		dialog.getContentPane().add(alta, BorderLayout.CENTER);
		dialog.setSize(650, 650);
		dialog.setLocationRelativeTo(parentWindow);
    
		dialog.setVisible(true);
}
}
