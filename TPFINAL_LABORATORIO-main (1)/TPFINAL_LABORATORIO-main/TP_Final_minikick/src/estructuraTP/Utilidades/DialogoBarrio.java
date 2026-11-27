package estructuraTP.Utilidades;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import estructuraDAO.Sentencias;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;
import estructuraTPvista.Barrio.BarrioAltaPN;

public class DialogoBarrio {
	private static String nombreComuna = null;	
	
	public void mostrarDialogoConCargarBarrio(Barrio b, Component invoker) {
		Window parentWindow = SwingUtilities.getWindowAncestor(invoker);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        BarrioAltaPN alta = new BarrioAltaPN();
       
        if(b instanceof TrazadoFormal) {
        	TrazadoFormal tf = (TrazadoFormal)b;
        	alta = new BarrioAltaPN(tf.getNombre(),tf.getSuperficieEnHectarias(), tf.getCantidadEdificios(),tf.getPorcentajeAsfalto());	
        }else if (b instanceof UrbanizacionPlanificada) {
        	UrbanizacionPlanificada up = (UrbanizacionPlanificada)b;
            alta = new BarrioAltaPN(up.getNombre(),up.getSuperficie(),up.getNumeroLey(),up.getTieneConexionAgua());
        }else if (b instanceof ComplejoHabitacional) {
        	ComplejoHabitacional ch = (ComplejoHabitacional) b;
        	alta  = new BarrioAltaPN(ch.getNombre(), ch.getSuperficie(),ch.getCantidadMonoblock(),ch.getTieneConsorcioAdministracion());
        }
        
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(alta, BorderLayout.CENTER);
        dialog.setSize(650, 700);
        dialog.setLocationRelativeTo(parentWindow);
        
        dialog.setVisible(true);
	}
	

	
public void mostrarDialogoConTablaBarrios(Barrio b, JPanel panel) {
		
		Window parentWindow = SwingUtilities.getWindowAncestor(panel);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(590, 320);
        dialog.setLocationRelativeTo(parentWindow);
        JButton botonCerrar = new JButton("Cerrar");
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 170, 493, 261);
		
		Object[] columnas = new Object[] {"Densidad poblacional", "¿Tiene criticidad habitacional?"};
		DefaultTableModel modelo = null;
		
		
		
		if(b instanceof TrazadoFormal) {
			columnas = new Object[] { "Cantidad edificios", "Porcentaje asfaltado", columnas[0], columnas[1]};
			modelo = new DefaultTableModel(columnas, 0);
			TrazadoFormal tz = (TrazadoFormal) b;
			String esCritico = obtenerSioNo(tz.criticidadHabitacional());
			modelo.addRow(new Object[] {tz.getCantidadEdificios(), tz.getPorcentajeAsfalto(), tz.calculoDensidad(), esCritico});
			
		}else if (b instanceof UrbanizacionPlanificada) {
			columnas = new Object[] {"Numero ley", "¿Tiene conexion de agua?",columnas[0], columnas[1]};
			modelo = new DefaultTableModel(columnas, 0);
			UrbanizacionPlanificada up = (UrbanizacionPlanificada) b;
			String respuesta = obtenerSioNo(up.getTieneConexionAgua());
			String esCritico = obtenerSioNo(up.criticidadHabitacional());
			modelo.addRow(new Object[] {up.getNumeroLey(), respuesta, up.calculoDensidad(), esCritico});
			
		}else if(b instanceof ComplejoHabitacional) {
			columnas = new Object[] {"Cantidad Monoblocks", "¿Tiene consorcio de administracion?",columnas[0], columnas[1]};
			
			modelo = new DefaultTableModel(columnas, 0);
			ComplejoHabitacional ch = (ComplejoHabitacional) b;
			String respuesta = obtenerSioNo(ch.getTieneConsorcioAdministracion());
			String esCritico = obtenerSioNo(ch.criticidadHabitacional());
			
			modelo.addRow(new Object[] {ch.getCantidadMonoblock(), respuesta, ch.calculoDensidad(), esCritico});
		}
		
		
		
        JTable tabla =  new JTable();
		tabla.setDefaultEditor(Object.class, null);
		tabla.setBounds(57, 122, 300, 112);

		tabla.setModel(modelo);
		scrollPane.setViewportView(tabla);
        
        botonCerrar.addActionListener(e -> dialog.dispose());

        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
        dialog.getContentPane().add(botonCerrar, BorderLayout.SOUTH);

        dialog.setVisible(true);
	}

	private static String obtenerSioNo(Boolean aux) {
		String resultado = "Si";
		if(aux == false) {
			resultado = "No";
		}
		return resultado;
}
	
	public String mostrarDialogoReAsignarBarrios(String nombreComunaEliminada,JPanel panel) {
		Window parentWindow = SwingUtilities.getWindowAncestor(panel);
        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        JPanel panelDialgo = new JPanel();
        panelDialgo.setLayout(null);
        JButton botonCerrar = new JButton("Cerrar");
        int i = 0;
        boolean salir = true;
        ArrayList<String> nombres = Sentencias.comuna.consultarNombres();
        while(salir || i < nombres.size()) {
        	if(nombres.get(i).equals(nombreComunaEliminada)) {
        		salir = false;
        		nombres.remove(i);
        	}
        	i++;
        }
        
        
		JLabel labelMigrarBarrios = new JLabel("Tienes que migar los barrios a un comuna");
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
				nombreComuna = comboBoxComunas.getSelectedItem() + "";
				dialog.dispose();
			}
		});
        
        dialog.setLocationRelativeTo(parentWindow);
        dialog.setContentPane(panelDialgo);
        dialog.setVisible(true);
        
        return nombreComuna;
	}
}
