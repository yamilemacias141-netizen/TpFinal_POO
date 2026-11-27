package estructuraTPvista.Barrio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import estructuraDAO.Sentencias;
import estructuraTP.Utilidades.Dialogo;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class BarrioAltaPN extends JPanel {

private static final long serialVersionUID = 1L;
	private JLabel labelTitulo;
	private JLabel labelNumero;
	private JLabel labelNombre;
	private JTextField textField;
	private JLabel labelTipoDeBarrio;
	private JComboBox comboBoxTipoBarrio;
	private JSpinner spinner;
	private JButton botonAgregar;
	private JLabel labelCaracteristicaEspecialAbajo;
	private JLabel labelCaracteristicaEspecialArriba;
	private JLabel labelSubtitulo;
	private JComboBox comboBoxEspecial;
	private String[] tipos = Sentencias.barrio.consultarTipoBarrios();

	private String nombreOriginal; 
	private JSpinner spinnerEspecialArriba;
	private JTextField textFieldParaNumeroLey;
	private JSpinner spinnerEspecialAbajo;
	
	private int tipoElegido = 0;
	private JLabel lblAsignarComuna;
	private JComboBox comboBoxDeComunas;

	public BarrioAltaPN() {
		setLayout(null);
		setBounds(800, 400, 800, 800);
		crearComponentes();
		crearComponentesEspeciales();
		actualizarComboBoxComunas();
		usoBotonCargar();
		actualizarComboBoxComuna();
	}
	public BarrioAltaPN(String nombre, float superficie, int cantidadEdificios , float porcentajeAsfalto) {
		setLayout(null);
		setBounds(800, 400, 600, 600);
		crearComponentes();
		crearComponentesEspeciales();
		actualizarComboBoxComunas();
		usoBotonModificar();
		actualizarComboBoxComuna();
		cambiarLosElementos();
		tipoElegido = 0;
		comboBoxTipoBarrio.setSelectedIndex(tipoElegido);
		nombreOriginal = nombre;
		labelTitulo.setText("Modificar Barrio");
		spinner.setValue(superficie);
		textField.setText(nombre);
		spinnerEspecialArriba.setValue(cantidadEdificios);
		spinnerEspecialAbajo.setValue(porcentajeAsfalto);
	}
	public BarrioAltaPN(String nombre, float superficie, String numeroLeyCaba , boolean conexionRedAgua) {
		setLayout(null);
		setBounds(800, 400, 600, 600);
		crearComponentes();
		crearComponentesEspeciales();
		actualizarComboBoxComunas();
		usoBotonModificar();
		actualizarComboBoxComuna();
		cambiarLosElementos();
		tipoElegido = 1;
		nombreOriginal = nombre;
		labelTitulo.setText("Modificar Barrio");
		comboBoxTipoBarrio.setSelectedIndex(tipoElegido);
		spinner.setValue(superficie);
		textField.setText(nombre);
		textFieldParaNumeroLey.setText(numeroLeyCaba);
		if(conexionRedAgua) {
			comboBoxEspecial.setSelectedItem(0);
		}else {
			comboBoxEspecial.setSelectedItem(1);
		}
		
	}
	public BarrioAltaPN(String nombre, float superficie, int cantidadMonoblocks , boolean consorcioAdministracion) {
		setLayout(null);
		setBounds(800, 400, 600, 600);
		crearComponentes();
		crearComponentesEspeciales();
		actualizarComboBoxComunas();
		usoBotonModificar();
		actualizarComboBoxComuna();
		cambiarLosElementos();
		tipoElegido = 2;
		nombreOriginal = nombre;
		labelTitulo.setText("Modificar Barrio");
		spinner.setValue(superficie);
		textField.setText(nombre);
		comboBoxTipoBarrio.setSelectedIndex(tipoElegido);
		spinnerEspecialArriba.setValue(cantidadMonoblocks);
		if(consorcioAdministracion) {
			comboBoxEspecial.setSelectedIndex(0);
		}else {
			comboBoxEspecial.setSelectedIndex(1);
		}
	}
	
		private void crearComponentes() {
			labelTitulo = new JLabel("Agregar Barrio");
			labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
			labelTitulo.setBounds(233, 52, 300, 47);
			add(labelTitulo);
			
			textField = new JTextField();
			textField.setBounds(393, 114, 186, 26);
			add(textField);
			textField.setColumns(10);
			
			labelNombre = new JLabel("Nombre del barrio");
			labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
			labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
			labelNombre.setBounds(136, 110, 218, 26);
			add(labelNombre);
			
			labelNumero = new JLabel("Superficie en hectarias");
			labelNumero.setHorizontalAlignment(SwingConstants.CENTER);
			labelNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
			labelNumero.setBounds(155, 170, 202, 26);
			add(labelNumero);
			
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(99903), Float.valueOf(1)));
			spinner.setBounds(449, 174, 96, 26);
			spinner.setValue(1);
			add(spinner);
			
			botonAgregar = new JButton("AGREGAR");
			botonAgregar.setBackground(new Color(0, 128, 64));
			botonAgregar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			botonAgregar.setBounds(338, 613, 118, 40);
			add(botonAgregar);
			
			comboBoxTipoBarrio = new JComboBox();
			comboBoxTipoBarrio.setBounds(419, 299, 158, 26);
			comboBoxTipoBarrio.setModel(new DefaultComboBoxModel<>(tipos));
			comboBoxTipoBarrio.setSelectedIndex(tipoElegido);
			add(comboBoxTipoBarrio);
			
			labelTipoDeBarrio = new JLabel("Tipo de Barrio");
			labelTipoDeBarrio.setHorizontalAlignment(SwingConstants.CENTER);
			labelTipoDeBarrio.setFont(new Font("Tahoma", Font.PLAIN, 20));
			labelTipoDeBarrio.setBounds(155, 295, 202, 26);
			add(labelTipoDeBarrio);
			
			comboBoxDeComunas = new JComboBox();
			comboBoxDeComunas.setBounds(419, 235, 158, 26);
			comboBoxDeComunas.setModel(new DefaultComboBoxModel(Sentencias.comuna.consultarNombres().toArray()));
			add(comboBoxDeComunas);
	
			
			
		}
		private void crearComponentesEspeciales(){
			labelSubtitulo = new JLabel("Caracteristicas especiales");
			labelSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
			labelSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 24));
			labelSubtitulo.setBounds(212, 354, 348, 47);
			add(labelSubtitulo);
			
			labelCaracteristicaEspecialArriba = new JLabel("Cantidad edificios horizontales");
			labelCaracteristicaEspecialArriba.setHorizontalAlignment(SwingConstants.CENTER);
			labelCaracteristicaEspecialArriba.setFont(new Font("Tahoma", Font.PLAIN, 20));
			labelCaracteristicaEspecialArriba.setBounds(44, 418, 313, 26);
			add(labelCaracteristicaEspecialArriba);
			
			labelCaracteristicaEspecialAbajo = new JLabel("Porcentaje asfaltado");
			labelCaracteristicaEspecialAbajo.setHorizontalAlignment(SwingConstants.CENTER);
			labelCaracteristicaEspecialAbajo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			labelCaracteristicaEspecialAbajo.setBounds(44, 495, 313, 26);
			add(labelCaracteristicaEspecialAbajo);
			
			spinnerEspecialArriba = new JSpinner();
			spinnerEspecialArriba.setModel(new SpinnerNumberModel(1, 1, null, 1));
			spinnerEspecialArriba.setBounds(449, 412, 96, 26);
			add(spinnerEspecialArriba);
			
			spinnerEspecialAbajo = new JSpinner();
			spinnerEspecialAbajo.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(99903), Float.valueOf(1)));
			
			spinnerEspecialAbajo.setBounds(449, 495, 96, 26);
			add(spinnerEspecialAbajo);
			
			textFieldParaNumeroLey = new JTextField();
			textFieldParaNumeroLey.setVisible(false);
			textFieldParaNumeroLey.setColumns(10);
			textFieldParaNumeroLey.setBounds(411, 412, 186, 26);
			add(textFieldParaNumeroLey);
			textFieldParaNumeroLey.setVisible(false);
			
			comboBoxEspecial = new JComboBox();
			comboBoxEspecial.setModel(new DefaultComboBoxModel(new String[] {"Si", "No"}));
			comboBoxEspecial.setBounds(419, 497, 158, 26);
			add(comboBoxEspecial);
			comboBoxEspecial.setVisible(false);
			
			lblAsignarComuna = new JLabel("Asignar Comuna");
			lblAsignarComuna.setHorizontalAlignment(SwingConstants.CENTER);
			lblAsignarComuna.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblAsignarComuna.setBounds(155, 235, 202, 26);
			add(lblAsignarComuna);
			
			
			
		}
		
		private void actualizarComboBoxComunas() {
			comboBoxTipoBarrio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tipoElegido = comboBoxTipoBarrio.getSelectedIndex();
					cambiarLosElementos();
				}
			});
		}
		
		private void usoBotonCargar() {
			botonAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nombreComuna = comboBoxDeComunas.getSelectedItem().toString();
					String nombreBarrio = textField.getText();
					Barrio barrio = null;
					if(nombreBarrio.isEmpty()) {
						Dialogo.mostrarDialogo("Rellene el nombre del barrio", BarrioAltaPN.this);
					}else {
						barrio = verQueTipoEs(nombreBarrio);
						}
						if(Sentencias.barrio.agregar(barrio, nombreComuna)) {
							Dialogo.mostrarDialogo("Cargado con éxito", BarrioAltaPN.this);
						}else {
							Dialogo.mostrarDialogo("Algun dato esta mal", BarrioAltaPN.this);
						}
					}
				
			});
		}
		private void usoBotonModificar() {
			botonAgregar.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					String nombreComuna = comboBoxDeComunas.getSelectedItem().toString();
					String nombreBarrio = textField.getText();
					Barrio barrio = null;
					if(nombreBarrio.isEmpty()) {
						Dialogo.mostrarDialogo("Rellene el nombre del barrio", BarrioAltaPN.this);
					}else {
						barrio = verQueTipoEs(nombreBarrio);
						if(Sentencias.barrio.actualizar(barrio,nombreOriginal, nombreComuna)){
							Dialogo.mostrarDialogo("Cargado con éxito", BarrioAltaPN.this);
						}else {
							Dialogo.mostrarDialogo("Ingrese un número distinto o no deje un nombre vacio", BarrioAltaPN.this);
						}
					}
					
					
					
				}
			});
		}

		private void actualizarComboBoxComuna() {
			comboBoxDeComunas.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {

			    public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
			    	comboBoxDeComunas.setModel(new DefaultComboBoxModel(Sentencias.comuna.consultarNombres().toArray()));
			    }

			    public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {
			    }

			    public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {
			    }
			});
			
		}
		
		private void cambiarLosElementos() {			
			switch (tipoElegido) {
			case 0:
				labelCaracteristicaEspecialArriba.setText("Cantidad edificios horizontales");
				labelCaracteristicaEspecialAbajo.setText("Porcentaje asfaltado");
				
				textFieldParaNumeroLey.setVisible(false);
				comboBoxEspecial.setVisible(false);
				spinnerEspecialArriba.setVisible(true);
				spinnerEspecialAbajo.setVisible(true);
				
				break;
			case 1:
				labelCaracteristicaEspecialArriba.setText("Numero ley de integración");
				labelCaracteristicaEspecialAbajo.setText("¿Tiene conexion formal a la red de agua?");
				
				textFieldParaNumeroLey.setVisible(true);
				comboBoxEspecial.setVisible(true);
				spinnerEspecialArriba.setVisible(false);
				spinnerEspecialAbajo.setVisible(false);
				break;
			case 2:
				labelCaracteristicaEspecialArriba.setText("Cantidad de monoblocks");
				labelCaracteristicaEspecialAbajo.setText("¿posee un consorcio de administración macro activo?");
				
				textFieldParaNumeroLey.setVisible(false);
				comboBoxEspecial.setVisible(true);
				spinnerEspecialArriba.setVisible(true);
				spinnerEspecialAbajo.setVisible(false);
				break;
			}
		}
		
		
		private Barrio verQueTipoEs(String nombre) {
			float superficie = (float) spinner.getValue();
			Barrio barrio = null;
			switch(tipoElegido){
			case 0:
				int cantidadEdificios = (int) spinnerEspecialArriba.getValue();
				float porcentajeAsfaltado = (float) spinnerEspecialAbajo.getValue();
				barrio = new TrazadoFormal(nombre, superficie, cantidadEdificios, porcentajeAsfaltado);
				break;
			case 1:
				String numeroLey = textFieldParaNumeroLey.getText();
				boolean hayRedAgua = false;
				if(comboBoxEspecial.getSelectedItem().toString().equals("Si")) {
					hayRedAgua = true;
				}
				barrio = new UrbanizacionPlanificada(nombre, superficie, numeroLey, hayRedAgua);
				break;
			case 2:
				int cantidadMonoblocks = (int) spinnerEspecialArriba.getValue();
				boolean tieneConsorcio = false;
				if(comboBoxTipoBarrio.getSelectedItem().toString().equals("Si")) {
					tieneConsorcio = true;
				}
				barrio = new ComplejoHabitacional(nombre, superficie, cantidadMonoblocks, tieneConsorcio);
				break;
			}
			return barrio;
		}
}
	

