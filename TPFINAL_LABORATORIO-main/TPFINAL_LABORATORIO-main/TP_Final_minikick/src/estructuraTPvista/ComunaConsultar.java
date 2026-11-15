package estructuraTPvista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ComunaConsultar extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelTitulo;
	private JLabel labelNombre;
	private JTextField textField;
	private JTable table;
	
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;

	
	public ComunaConsultar() {
		setLayout(null);
		crearLabels();
		crearTabla();
		
	}
	
	private void crearLabels() {
		labelTitulo = new JLabel("Consultar Comuna");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		labelTitulo.setBounds(47, 27, 300, 47);
		add(labelTitulo);
		
		labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setBounds(47, 85, 86, 26);
		add(labelNombre);
		
		textField = new JTextField();
		textField.setBounds(177, 85, 186, 26);
		add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 122, 309, 123);
		add(scrollPane);
		
		
	}
	
	private void crearTabla() {
		table =  new JTable();
		table.setBounds(57, 122, 300, 112);
		
		Object[] columnas = new Object[] {"Nombre" , "Numero", "Cantidad Barrios"};
		
		modelo = new DefaultTableModel(columnas, 0);

		table.setModel(modelo);
		scrollPane.setViewportView(table);
		modelo.addRow(new Object[] {"nombre", "12"});
	}
}
