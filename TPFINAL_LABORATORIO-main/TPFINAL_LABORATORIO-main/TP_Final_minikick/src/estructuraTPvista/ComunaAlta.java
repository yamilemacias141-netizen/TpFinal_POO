package estructuraTPvista;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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
import java.awt.event.ActionEvent;

public class ComunaAlta extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelTitulo;
	private JLabel labelNumero;
	private JLabel labelNombre;
	private JTextField textField;
	
	private JSpinner spinner;
	private JButton botonAgregar;
	
	public ComunaAlta() {
		setLayout(null);
		crearLabels();
		usosBotones();
	}
	private void crearLabels() {
		labelTitulo = new JLabel("Agregar Comuna");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		labelTitulo.setBounds(47, 27, 300, 47);
		add(labelTitulo);
		
		textField = new JTextField();
		textField.setBounds(179, 100, 186, 26);
		add(textField);
		textField.setColumns(10);
		
		labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setBounds(47, 100, 86, 26);
		add(labelNombre);
		
		labelNumero = new JLabel("Numero");
		labelNumero.setHorizontalAlignment(SwingConstants.CENTER);
		labelNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNumero.setBounds(47, 169, 89, 26);
		add(labelNumero);
		
		spinner = new JSpinner();
		spinner.setBounds(179, 169, 48, 26);
		add(spinner);
		
		botonAgregar = new JButton("AGREGAR");
		botonAgregar.setBackground(new Color(0, 128, 64));
		botonAgregar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonAgregar.setBounds(138, 217, 118, 40);
		add(botonAgregar);
	}
	
	private void usosBotones() {
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			mostrarDialogo();
			}
		});
	}
	
	private void mostrarDialogo() {
        Window parentWindow = SwingUtilities.getWindowAncestor(this);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parentWindow);

        JLabel lblMensaje = new JLabel("Cargado exitosamente", SwingConstants.CENTER);
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dialog.dispose());

        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(lblMensaje, BorderLayout.CENTER);
        dialog.getContentPane().add(btnCerrar, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}
