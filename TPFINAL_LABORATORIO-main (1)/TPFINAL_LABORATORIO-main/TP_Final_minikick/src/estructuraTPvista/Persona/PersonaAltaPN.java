package estructuraTPvista.Persona;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import estructuraDAO.Sentencias;
import estructuraTP.Utilidades.Dialogo;
import estructuraTP.Utilidades.Mes;
import estructuraTPmodelo.Persona;
import estructuraTPmodelo.TipoSituacionLaboral;

import javax.swing.JComboBox;

public class PersonaAltaPN extends JPanel {
	
	private JLabel labelTitulo;
	private JLabel labelNumero;
	private JLabel labelNombre;
	private JTextField textFieldNombre;
	private JLabel labelSituacionLaboral;
	private JComboBox comboBoxBarrios;
	private JComboBox comboBoxSituacionLaboral;
	private JComboBox comboBoxMes;
	private JLabel labelBarrio;
	private JSpinner spinnerDNI;
	private JButton botonAgregar;
	private JSpinner spinnerAño;
	private JSpinner spinnerDias;
	private JLabel labelFechaNacimiento;
	
	private String DNIantiguo;

	public PersonaAltaPN() {
		setLayout(null);
		setBounds(800, 400, 800, 800);
		crearComponentes();	
		actualizarComboBoxBarrio();
		actualizarSpinner();
		usoBotonAgregar();
	}	
	public PersonaAltaPN(Persona p, String DNI) {
		setLayout(null);
		setBounds(800, 400, 800, 800);
		DNIantiguo = DNI;
		crearComponentes();	
		actualizarComboBoxBarrio();
		actualizarSpinner();
		usoBotonModificar();
		
		labelTitulo.setText("Modificar persona");
		textFieldNombre.setText(p.getNombre());
		spinnerDNI.setValue(Integer.parseInt(DNIantiguo));
		LocalDate fecha = p.getFechaNacimiento();
		int dia = fecha.getDayOfMonth();
		int mes = fecha.getMonthValue();
		int año = fecha.getYear();
		
		spinnerAño.setValue(año);
		spinnerDias.setValue(dia);
		comboBoxMes.setSelectedIndex(mes-1);
		
	}
	private void crearComponentes() {
		labelTitulo = new JLabel("Agregar Persona");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		labelTitulo.setBounds(233, 52, 300, 47);
		add(labelTitulo);
		
		labelNombre = new JLabel("Nombre Personal");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setBounds(122, 163, 218, 26);
		add(labelNombre);
		
		labelNumero = new JLabel("DNI");
		labelNumero.setHorizontalAlignment(SwingConstants.CENTER);
		labelNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNumero.setBounds(219, 217, 89, 26);
		add(labelNumero);
		
		labelBarrio = new JLabel("Barrio");
		labelBarrio.setHorizontalAlignment(SwingConstants.CENTER);
		labelBarrio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelBarrio.setBounds(122, 330, 218, 26);
		add(labelBarrio);
		
		labelSituacionLaboral = new JLabel("Situacion laboral");
		labelSituacionLaboral.setHorizontalAlignment(SwingConstants.CENTER);
		labelSituacionLaboral.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelSituacionLaboral.setBounds(122, 383, 218, 26);
		add(labelSituacionLaboral);
		
		labelFechaNacimiento = new JLabel("Fecha Nacimiento");
		labelFechaNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
		labelFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelFechaNacimiento.setBounds(122, 273, 218, 26);
		add(labelFechaNacimiento);
		
		// -------------------------------- labels
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(425, 167, 186, 26);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		spinnerDNI = new JSpinner();
		spinnerDNI.setModel(new SpinnerNumberModel(1, 1, 999999999, 1));
		spinnerDNI.setBounds(425, 221, 152, 26);
		spinnerDNI.setValue(1);
		add(spinnerDNI);
		
		botonAgregar = new JButton("AGREGAR");
		botonAgregar.setBackground(new Color(0, 128, 64));
		botonAgregar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonAgregar.setBounds(330, 562, 118, 40);
		add(botonAgregar);
		
		spinnerDias = new JSpinner();
		spinnerDias.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinnerDias.setBounds(425, 277, 47, 26);
		add(spinnerDias);
		
		comboBoxMes = new JComboBox();
		comboBoxMes.setBounds(482, 277, 117, 26);
		add(comboBoxMes);
		
		spinnerAño = new JSpinner();
		spinnerAño.setModel(new SpinnerNumberModel(2026, 1900, 2026, 1));
		// para que el spinner no tenga las comas del mil
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinnerAño, "#");
		spinnerAño.setEditor(editor);
		spinnerAño.setBounds(609, 277, 77, 26);
		add(spinnerAño);
		
		comboBoxBarrios = new JComboBox();
		comboBoxBarrios.setBounds(425, 336, 152, 26);
		add(comboBoxBarrios);
		
		comboBoxSituacionLaboral = new JComboBox();
		comboBoxSituacionLaboral.setBounds(425, 383, 152, 26);
		add(comboBoxSituacionLaboral);
		
		comboBoxBarrios.setModel(new DefaultComboBoxModel(Sentencias.barrio.consultarNombresBarrio().toArray()));
		comboBoxSituacionLaboral.setModel(new DefaultComboBoxModel(TipoSituacionLaboral.values()));
		comboBoxMes.setModel(new DefaultComboBoxModel(Mes.values()));
	
	}
	private void actualizarComboBoxBarrio() {
		comboBoxBarrios.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {

		    public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
		    	comboBoxBarrios.setModel(new DefaultComboBoxModel(Sentencias.barrio.consultarNombresBarrio().toArray()));
		    }

		    public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {
		    }

		    public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {
		    	
		    }
		});
	}
	
	private void actualizarSpinner() {
		comboBoxMes.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {

		    public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
		    	spinnerDias.setValue(1);
		    }

		    public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {
		    	Mes mes = (Mes)comboBoxMes.getSelectedItem();
				spinnerDias.setModel(new SpinnerNumberModel(1, 1, mes.dias, 1));
		    }

		    public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {
		    }
		});
	}
	
	private void usoBotonAgregar() {
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombrePersona = textFieldNombre.getText();
				if(nombrePersona.isEmpty()) {
					Dialogo.mostrarDialogo("No se puede crear una persona sin nombre", PersonaAltaPN.this);
				}else {
					Persona p = crearPersona(nombrePersona);
					String nombreBarrio = comboBoxBarrios.getSelectedItem() + "";
					
					if(Sentencias.persona.agregar(p, nombreBarrio)) {
						Dialogo.mostrarDialogo("Cargado con éxito", PersonaAltaPN.this);
					}else {
						Dialogo.mostrarDialogo("Ya existe una persona con ese DNI", PersonaAltaPN.this);
					}
				}
			}  
			});
	}
	
	private void usoBotonModificar() {
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombrePersona = textFieldNombre.getText();
				if(nombrePersona.isEmpty()) {
					Dialogo.mostrarDialogo("No se puede crear una persona sin nombre", PersonaAltaPN.this);
				}else {
					Persona p = crearPersona(nombrePersona);
					String nombreBarrio = comboBoxBarrios.getSelectedItem() + "";
					
					if(Sentencias.persona.actualizar(p, nombreBarrio, DNIantiguo)) {
						Dialogo.mostrarDialogo("Actualizado con éxito", PersonaAltaPN.this);
					}else {
						Dialogo.mostrarDialogo("Ya existe una persona con ese DNI", PersonaAltaPN.this);
					}
				}
			}  
			});
	}
	
	private Persona crearPersona(String nombrePersona) {
		String dni = spinnerDNI.getValue() + "";
		TipoSituacionLaboral tipo = (TipoSituacionLaboral) comboBoxSituacionLaboral.getSelectedItem();
		Mes mes = (Mes) comboBoxMes.getSelectedItem();
		int dia = (int) spinnerDias.getValue();
		int año = (int) spinnerAño.getValue();
		LocalDate fecha = LocalDate.of(año, mes.ordinal()+1, dia);
		Persona p = new Persona(nombrePersona, fecha, dni, tipo);
		
		return p;
	}
	
}
