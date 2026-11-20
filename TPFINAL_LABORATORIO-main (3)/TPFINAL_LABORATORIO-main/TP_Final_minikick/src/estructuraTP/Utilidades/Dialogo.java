package estructuraTP.Utilidades;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;
import estructuraTPvista.Barrio.BarrioAltaPN;
import estructuraTPvista.Barrio.BarrioConsultar;
import estructuraTPvista.Comuna.ComunaAltaPN;

public abstract class Dialogo {
	static JButton botonCerrar = new JButton("Cerrar");
	public static void mostrarDialogo(String mensaje, Component invoker) {
        Window parentWindow = SwingUtilities.getWindowAncestor(invoker);
        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parentWindow);

        JLabel lblMensaje = new JLabel(mensaje, SwingConstants.CENTER);
        
        botonCerrar.addActionListener(e -> dialog.dispose());

        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(lblMensaje, BorderLayout.CENTER);
        dialog.getContentPane().add(botonCerrar, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
	
	public static void mostrarDialogoConTabla(Comuna c,ArrayList<Barrio> b, Component invoker) {
		
		Window parentWindow = SwingUtilities.getWindowAncestor(invoker);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(parentWindow);
        
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
	
	public static void mostrarDialogConCargarComuna(Comuna c, Component invoker) {
		Window parentWindow = SwingUtilities.getWindowAncestor(invoker);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        ComunaAltaPN alta = new ComunaAltaPN(c.getNombre(), c.getNumeroComuna());
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(alta, BorderLayout.CENTER);
        dialog.setSize(650, 650);
        dialog.setLocationRelativeTo(parentWindow);
        
        dialog.setVisible(true);
	}

	public static void mostrarDialogoCargarBarrio(Barrio b, Component invoker) {
		Window parentWindow = SwingUtilities.getWindowAncestor(invoker);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        BarrioAltaPN alta = null;
        String tipo = "";
        switch(tipo)
        {
        case "Trazado formal":
        	TrazadoFormal tf = (TrazadoFormal)b;
        	alta = new BarrioAltaPN(tf.getNombre(),tf.getSuperficieEnHectarias(), tf.getCantidadEdificios(),tf.getPorcentajeAsfalto());
        	break;
        case "Complejo habitacional":
        	ComplejoHabitacional ch = (ComplejoHabitacional) b;
        	alta  = new BarrioAltaPN(ch.getNombre(), ch.getSuperficieEnHectarias(),ch.getCantidadMonoblock(),ch.getTieneConsorcioAdministracion());
        	break;
        case "Urbanizacion planificada":
            UrbanizacionPlanificada up = (UrbanizacionPlanificada)b;
            alta = new BarrioAltaPN(up.getNombre(),up.getSuperficieEnHectarias(),up.getNumeroLey(),up.getTieneConexionAgua());
            break;
        
        }
        
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(alta, BorderLayout.CENTER);
        dialog.setSize(650, 650);
        dialog.setLocationRelativeTo(parentWindow);
        
        dialog.setVisible(true);
		
	}
	
	
}
