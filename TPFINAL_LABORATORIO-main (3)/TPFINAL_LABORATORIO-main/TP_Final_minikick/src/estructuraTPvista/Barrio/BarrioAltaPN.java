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
import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;
import estructuraTPvista.Comuna.ComunaAltaPN;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BarrioAltaPN extends JPanel {

private static final long serialVersionUID = 1L;
	private JLabel labelTitulo;
	private JLabel labelNumero;
	private JLabel labelNombre;
	private JTextField textField;
	private JLabel labelTipoDeBarrio;
	private JComboBox comboBox;
	private JSpinner spinner;
	private JButton botonAgregar;
	private JLabel labelCaracteristicaEspecialAbajo;
	private JLabel labelCaracteristicaEspecialArriba;
	private JLabel labelSubtitulo;
	private JComboBox comboBoxEspecial;
	private String[] tipos = Sentencias.barrio.consultarTipoBarrios();

	private String nombreOriginal; //PARA VERIFICAR LO DEL MISMO NUMERO AL CAMBIAR
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
		cambiarComponentesEspeciales();
		usoBoton();
	}
	public BarrioAltaPN(String nombre, float superficie, int cantidadEdificios , float porcentajeAsfalto) {
		setLayout(null);
		setBounds(800, 400, 600, 600);
		crearComponentes();
		crearComponentesEspeciales();
		nombreOriginal = nombre;
		labelTitulo.setText("Modificar Barrio");
		spinner.setValue(superficie);
		textField.setText(nombre);
	}
	public BarrioAltaPN(String nombre, float superficie, String numeroLeyCaba , boolean conexionRedAgua) {
		setLayout(null);
		setBounds(800, 400, 600, 600);
		crearComponentes();
		crearComponentesEspeciales();
		nombreOriginal = nombre;
		labelTitulo.setText("Modificar Barrio");
		spinner.setValue(superficie);
		textField.setText(nombre);
	}
	public BarrioAltaPN(String nombre, float superficie, int cantidadMonoblocks , boolean consorcioAdministracion) {
		setLayout(null);
		setBounds(800, 400, 600, 600);
		crearComponentes();
		crearComponentesEspeciales();
		nombreOriginal = nombre;
		labelTitulo.setText("Modificar Barrio");
		spinner.setValue(superficie);
		textField.setText(nombre);
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
			spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spinner.setBounds(449, 174, 96, 26);
			spinner.setValue(1);
			add(spinner);
			
			botonAgregar = new JButton("AGREGAR");
			botonAgregar.setBackground(new Color(0, 128, 64));
			botonAgregar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			botonAgregar.setBounds(338, 613, 118, 40);
			add(botonAgregar);
			
			comboBox = new JComboBox();
			comboBox.setBounds(419, 299, 158, 26);
			comboBox.setModel(new DefaultComboBoxModel<>(tipos));
			add(comboBox);
			
			labelTipoDeBarrio = new JLabel("Tipo de Barrio");
			labelTipoDeBarrio.setHorizontalAlignment(SwingConstants.CENTER);
			labelTipoDeBarrio.setFont(new Font("Tahoma", Font.PLAIN, 20));
			labelTipoDeBarrio.setBounds(155, 295, 202, 26);
			add(labelTipoDeBarrio);
			
			
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
			spinnerEspecialArriba.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spinnerEspecialArriba.setBounds(449, 412, 96, 26);
			add(spinnerEspecialArriba);
			
			spinnerEspecialAbajo = new JSpinner();
			spinnerEspecialAbajo.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), null, Integer.valueOf(1)));
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
			
			lblAsignarComuna = new JLabel("Asignar Comuna");
			lblAsignarComuna.setHorizontalAlignment(SwingConstants.CENTER);
			lblAsignarComuna.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblAsignarComuna.setBounds(155, 235, 202, 26);
			add(lblAsignarComuna);
			
			comboBoxDeComunas = new JComboBox();
			comboBoxDeComunas.setBounds(419, 235, 158, 26);
			comboBoxDeComunas.setModel(new DefaultComboBoxModel(Sentencias.comuna.consultarNombresComuna().toArray()));
			add(comboBoxDeComunas);
			comboBoxEspecial.setVisible(false);
			
			
		}
		
		private void cambiarComponentesEspeciales() {
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				tipoElegido = comboBox.getSelectedIndex();
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
			});
		}
		
		private void usoBoton() {
			botonAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nombreComuna = comboBoxDeComunas.getSelectedItem().toString();
					String nombre = textField.getText();
					float superficie = (int) spinner.getValue();
					if(nombre.isEmpty()) {
						Dialogo.mostrarDialogo("Rellene el nombre del barrio", BarrioAltaPN.this);
					}else {
						Barrio barrio = null;
						switch(tipoElegido){
						case 0:
							int cantidadEdificios = (int) spinnerEspecialArriba.getValue();
							float porcentajeAsfaltado = (int) spinnerEspecialAbajo.getValue();
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
							if(comboBox.getSelectedItem().toString().equals("Si")) {
								tieneConsorcio = true;
							}
							barrio = new ComplejoHabitacional(nombre, superficie, cantidadMonoblocks, tieneConsorcio);
							break;
						}
						int numeroComuna = Sentencias.comuna.consultarNumeroComunaPorNombre(nombreComuna);
						if(Sentencias.barrio.agregar(barrio, numeroComuna)) {
							Dialogo.mostrarDialogo("Cargado con éxito", BarrioAltaPN.this);
						}else {
							Dialogo.mostrarDialogo("Algun dato esta mal", BarrioAltaPN.this);
						}
					}
				}
			});
		}
		
}
	

