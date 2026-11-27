package estructuraTPvista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import estructuraDAO.Sentencias;
import estructuraTPmodelo.Comuna;

public class MenuInicio extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelTitulo;
	private JLabel labelPregunta;
	private JTable table;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	
	public MenuInicio() {
		setLayout(null);
		setBounds(800, 400, 800, 800);
		crearLabels();
		crearTabla();
		addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentShown(ComponentEvent e) {
		    	actualizarTabla(Sentencias.comuna.consultar());
		    }
		});
	}
	private void crearLabels() {
		labelTitulo = new JLabel("Bienvenido al sistema");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		labelTitulo.setBounds(208, 55, 358, 87);
		add(labelTitulo);
		
		labelPregunta = new JLabel("Resumen analitico de la ciudad");
		labelPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		labelPregunta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPregunta.setBounds(219, 153, 318, 39);
		add(labelPregunta);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 229, 493, 301);
		add(scrollPane);
		
	}
	
	private void crearTabla() {
		table =  new JTable();
		table.setBounds(57, 122, 300, 112);
		
		Object[] columnas = new Object[] {"Nombre" , "Numero", "ATENCION PRIORITARIA"};
		
		modelo = new DefaultTableModel(columnas, 0);
		table.setDefaultEditor(Object.class, null);
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		actualizarTabla(Sentencias.comuna.consultar());
	}
	
	private void actualizarTabla(ArrayList<Comuna> comunas) {
		modelo.setRowCount(0);
		for(Comuna c: comunas) {
				c.setBarrios(Sentencias.barrio.consultarPorNombreComuna(c.getNombre()));
				
				if(c.tamañoLista() > 0) {
					String respuesta = "Si";
					if(!c.esDeAtencionPrioritaria()) {
						respuesta = "No";
					}
					modelo.addRow(new Object[] {c.getNombre(), c.getNumeroComuna(), respuesta});
				}else {
					modelo.addRow(new Object[] {c.getNombre(), c.getNumeroComuna(), "Sin barrios asociados"});
				}
		}
	}
}
