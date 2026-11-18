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

import estructuraDAO.DAO;
import javax.swing.SpinnerNumberModel;

public class ComunaAlta extends JPanel {


	private JLabel labelTitulo;
	private JLabel labelNumero;
	private JLabel labelNombre;
	private JTextField textField;
	
	private JSpinner spinner;
	private JButton botonAgregar;
	
	private int numeroOriginal; //PARA VERIFICAR LO DEL MISMO NUMERO AL CAMBIAR
	
	private boolean seModifica = false; //para el metodo de uso boton, tiene 2 variantes depende si se modifica o no el dato
	
	public ComunaAlta() {
		seModifica = false;
		setLayout(null);
		setBounds(800, 400, 800, 800);
		crearLabels();
		usoBoton();
	}
	
	public ComunaAlta(String nombre, int numeroComuna) {
		seModifica =true;
		setLayout(null);
		setBounds(800, 400, 600, 600);
		crearLabels();
		usoBoton();
		numeroOriginal = numeroComuna;
		labelTitulo.setText("Modificar Comuna");
		spinner.setValue(numeroComuna);
		textField.setText(nombre);
	}
	private void crearLabels() {
		labelTitulo = new JLabel("Agregar Comuna");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		labelTitulo.setBounds(233, 52, 300, 47);
		add(labelTitulo);
		
		textField = new JTextField();
		textField.setBounds(425, 224, 186, 26);
		add(textField);
		textField.setColumns(10);
		
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
	
	private void usoBoton() {
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Comuna comuna = new Comuna(textField.getText(), (int )spinner.getValue());
			ArrayList<Integer> numeros = DAO.consultaMismoNumeroComuna();
			
			if(comuna.getNombre().isEmpty()) { //verificar si ponen un puto nombre
				mostrarDialogo("Ingrese un nombre a la sede comunal");
			}else {
				
				System.out.println(comuna.getNombre());
				boolean aux = true; //para al final decidir si se guarda o no
				if(seModifica) {
					if(comuna.getNumeroComuna() != numeroOriginal) { // si no es igual, hay que verificar que no se parezca a otro
						for(int n: numeros) {
							if(n == comuna.getNumeroComuna()) { //si se parece a otro, no vale
								mostrarDialogo("Numero de comuna ya existente, ponga otro o el anterior");
								aux =false;
							}
						}
					}
					if (aux == true) {
						mostrarDialogo("Cargado con exito");
						DAO.actualizarComuna(numeroOriginal +"", comuna); //el +"" es para hacerlo string de forma rapida
					}
				}
				else {
					for(int n: numeros) {
						if(n == comuna.getNumeroComuna()) {
							mostrarDialogo("Numero de comuna ya existente, ponga otro");
							aux = false;
						}
					}
					if (aux == true) {
						mostrarDialogo("Cargado con exito");
						DAO.agregarComuna(comuna);
					}
				}
			}
			}
		});
	}
	
	private void mostrarDialogo(String mensaje) {
        Window parentWindow = SwingUtilities.getWindowAncestor(this);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parentWindow);

        JLabel lblMensaje = new JLabel(mensaje, SwingConstants.CENTER);
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dialog.dispose());

        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(lblMensaje, BorderLayout.CENTER);
        dialog.getContentPane().add(btnCerrar, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}
