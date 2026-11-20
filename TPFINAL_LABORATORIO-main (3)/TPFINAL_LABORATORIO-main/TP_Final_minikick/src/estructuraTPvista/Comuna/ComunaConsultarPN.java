package estructuraTPvista.Comuna;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraDAO.Conexion;
import estructuraDAO.Sentencias;
import estructuraTP.Utilidades.Dialogo;
import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ComunaConsultarPN extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelTitulo;
	private JLabel labelIngreseNombre;
	private JLabel labelNombre;
	private JTextField textField;
	private JTable table;
	private JButton botonModificar;
	
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JButton botonBuscar;
	private JButton botonBorrar;
	private JButton botonMostrarTodo;
	private JButton botonVerDetalle;
	private JButton botonLimpiar;
	// esto es para saber si la persona al borrar apreto el de busqueda de 1 o el de todos.
	private boolean esUno = false; //verás bien en el metodo "usosBotones" que lo uso en la busqueda;
	
	public ComunaConsultarPN() {
		setLayout(null);
		setBounds(800, 400, 800, 800);
		crearLabels();
		crearTabla();
		usosBotones();
		
	}
	
	private void crearLabels() {
		labelTitulo = new JLabel("Consultar Comuna");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		labelTitulo.setBounds(266, 28, 300, 47);
		add(labelTitulo);
		
		labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setBounds(57, 113, 86, 26);
		add(labelNombre);
		
		textField = new JTextField();
		textField.setBounds(249, 117, 216, 26);
		add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 170, 493, 301);
		add(scrollPane);
		
		botonBuscar = new JButton("buscar");
		botonBuscar.setBounds(480, 113, 86, 29);
		add(botonBuscar);
		
		botonBorrar = new JButton("Borrar");
		botonBorrar.setBounds(583, 239, 145, 38);
		add(botonBorrar);
		
		botonMostrarTodo = new JButton("Mostrar todo");
		botonMostrarTodo.setBounds(583, 179, 145, 38);
		add(botonMostrarTodo);
		
		labelIngreseNombre = new JLabel("Ingrese el nombre de la sede comunal");
		labelIngreseNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelIngreseNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelIngreseNombre.setBounds(70, 76, 380, 26);
		add(labelIngreseNombre);
		
		botonModificar = new JButton("Borrar");
		botonBorrar.setBounds(583, 301, 145, 38);
		add(botonBorrar);
		
		botonVerDetalle = new JButton("Ver detalle");
		botonVerDetalle.setBounds(583, 364, 145, 38);
		add(botonVerDetalle);
		
		botonLimpiar = new JButton("Limpiar tabla");
		botonLimpiar.setBounds(583, 433, 145, 38);
		add(botonLimpiar);
		
		botonModificar = new JButton("Modificar");
		botonModificar.setBounds(583, 240, 145, 38);
		add(botonModificar);
		
		
	}
	private void usosBotones() {
		// -------------- BUSCAR UNO.
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Comuna> comunas = Sentencias.comuna.consultarComuna();
				esUno = true;
				Comuna c = null;
				boolean salir = true;
				int i = 0;
				String nombreBuscado = textField.getText();
				while(salir == true) {
					if(comunas.get(i).getNombre().equals(nombreBuscado)) {
						c = new Comuna(comunas.get(i).getNombre(), comunas.get(i).getNumeroComuna());
						salir = false;
						System.out.println(i);
					}
					i++;
					if(i >= comunas.size()) {
						salir =false;
					}
				}
				if(c == null) {
					Dialogo.mostrarDialogo("Comuna no encontrada", ComunaConsultarPN.this); // dialogo, podes poner el texto que queiras
				}else {
					Dialogo.mostrarDialogo("Comuna encontrada con exito", ComunaConsultarPN.this);
					modelo.setRowCount(0); //borrar todo lo de la tabla (de forma visual) es como actualizarla
					modelo.addRow(new Object[] {c.getNombre(), c.getNumeroComuna(), c.getcantidarBarrios()});
				}
			
			}
		});
		// --------------------------- MOSTRAR TODO-
		botonMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esUno = false;
				modelo.setRowCount(0); //siempre actualizamos por si acaso
				ArrayList<Comuna> comunas = Sentencias.comuna.consultarComuna();
				mostrarDespuesDeAlgo();
			}
		});
		//- -------------- VER DETALLE
		botonVerDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila < 0) {
					Dialogo.mostrarDialogo("Seleccione una fila para ver detalle", ComunaConsultarPN.this);
				}else {
					Comuna c = new Comuna(table.getValueAt(fila, 1).toString(), (int) table.getValueAt(fila, 2));
					ArrayList<Barrio> b = Sentencias.comuna.consultarComunaDetalle(c.getNumeroComuna() + "");
					System.out.println(b.size());
					Dialogo.mostrarDialogoConTabla(c, b, ComunaConsultarPN.this);
				}
			}
		});
		
		// ---------------------- BORRAR LO SELECCIONADO
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila <0 ) {
					Dialogo.mostrarDialogo("Seleccione una fila para borrar",ComunaConsultarPN.this);
				}
				else {
					Sentencias.comuna.eliminarComuna(table.getValueAt(fila, 1).toString());
					Dialogo.mostrarDialogo("Se elimino con éxito", ComunaConsultarPN.this);
					mostrarDespuesDeAlgo();
				}
			}
		});
		
		// ---------------------- LIMPIAR PANTALLA
		botonLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setRowCount(0);
			}
		});
		// ------------------------- MODIFICAR
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila <0 ) {
					Dialogo.mostrarDialogo("Seleccione una fila para modificar", ComunaConsultarPN.this);
				}
				else {
					Comuna c = new Comuna(table.getValueAt(fila, 0).toString(), (int)table.getValueAt(fila, 1));
					Dialogo.mostrarDialogConCargarComuna(c, ComunaConsultarPN.this);
					mostrarDespuesDeAlgo();
				}
				
			}
		});
	}
	
	private void crearTabla() {
		table =  new JTable();
		table.setBounds(57, 122, 300, 112);
		
		Object[] columnas = new Object[] {"Nombre" , "Numero", "Cantidad Barrios"};
		
		modelo = new DefaultTableModel(columnas, 0);
		table.setDefaultEditor(Object.class, null);
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
	}

	private void mostrarDespuesDeAlgo() {
		// ------------- ACA SE USA EL UNO
		if(esUno == true) {
			modelo.setRowCount(0);
		}
		else {
			modelo.setRowCount(0);
			ArrayList<Comuna> comunas = Sentencias.comuna.consultarComuna();
			for(Comuna c: comunas) {
				modelo.addRow(new Object[] {c.getNombre(), c.getNumeroComuna(), c.getcantidarBarrios()});
			}
		}
	}
	
	
	
	
}
