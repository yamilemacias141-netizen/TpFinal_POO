package estructuraTPvista.Comuna;

import javax.swing.JPanel;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import estructuraTPmodelo.Comuna;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

import estructuraDAO.Conexion;
import estructuraDAO.DAOComuna;
import estructuraDAO.Sentencias;
import estructuraTP.Utilidades.Dialogo;

import javax.swing.SpinnerNumberModel;

public class ComunaAltaPN extends JPanel {
	private JLabel labelTitulo;
	private JLabel labelNumero;
	private JLabel labelNombre;
	private JTextField textFieldNombreSede;
	
	private JSpinner spinner;
	private JButton botonAgregar;
	
	private int numeroOriginal; //PARA VERIFICAR LO DEL MISMO NUMERO AL CAMBIAR
	
	public ComunaAltaPN() {
		setLayout(null);
		setBounds(800, 400, 800, 800);
		crearLabels();
		usoBotonAgregar();
	}
	
	public ComunaAltaPN(String nombre, int numeroComuna) {
		setLayout(null);
		setBounds(800, 400, 600, 600);
		crearLabels();
		usoBotonModificar();
		numeroOriginal = numeroComuna;
		labelTitulo.setText("Modificar Comuna");
		spinner.setValue(numeroComuna);
		textFieldNombreSede.setText(nombre);
	}
	
	
	private void crearLabels() {
		labelTitulo = new JLabel("Agregar Comuna");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		labelTitulo.setBounds(233, 52, 300, 47);
		add(labelTitulo);
		
		textFieldNombreSede = new JTextField();
		textFieldNombreSede.setBounds(425, 224, 186, 26);
		add(textFieldNombreSede);
		textFieldNombreSede.setColumns(10);
		
		labelNombre = new JLabel("Nombre Sede comunal");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setBounds(122, 220, 218, 26);
		add(labelNombre);
		
		labelNumero = new JLabel("Numero");
		labelNumero.setHorizontalAlignment(SwingConstants.CENTER);
		labelNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNumero.setBounds(251, 334, 89, 26);
		add(labelNumero);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner.setBounds(425, 338, 48, 26);
		spinner.setValue(1);
		add(spinner);
		
		botonAgregar = new JButton("AGREGAR");
		botonAgregar.setBackground(new Color(0, 128, 64));
		botonAgregar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonAgregar.setBounds(340, 535, 118, 40);
		add(botonAgregar);
	}
	
	private void usoBotonAgregar() {
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String nombre = textFieldNombreSede.getText();
			int numero = (int) spinner.getValue();
			Comuna comuna = new Comuna(nombre, numero);
			if(comuna.getNombre().isEmpty()) {
				Dialogo.mostrarDialogo("Rellene el nombre de comuna", ComunaAltaPN.this);
			}else {
				if(Sentencias.comuna.agregar(comuna)){
					Dialogo.mostrarDialogo("Cargado con éxito", ComunaAltaPN.this);
				}else {
					Dialogo.mostrarDialogo("Ingrese un número distinto", ComunaAltaPN.this);
				}
			}
			
			}
		});
	}
	
	private void usoBotonModificar() {
		botonAgregar.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldNombreSede.getText();
				int numero = (int) spinner.getValue();
				Comuna comuna = new Comuna(nombre, numero);
				if(nombre.isEmpty()) {
					Dialogo.mostrarDialogo("Rellene el nombre de comuna", ComunaAltaPN.this);
				}else {
					if(Sentencias.comuna.actualizar(numeroOriginal+"", comuna)){
						Dialogo.mostrarDialogo("Cargado con éxito", ComunaAltaPN.this);
					}else {
						Dialogo.mostrarDialogo("Ingrese un número distinto o no deje un nombre vacio", ComunaAltaPN.this);
					}
				}
				
			}
		});
	}
	
}
