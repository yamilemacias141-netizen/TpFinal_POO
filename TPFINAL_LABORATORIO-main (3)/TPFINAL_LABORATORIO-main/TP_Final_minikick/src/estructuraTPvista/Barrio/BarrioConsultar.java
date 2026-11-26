package estructuraTPvista.Barrio;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import estructuraDAO.DAO;
import estructuraDAO.Sentencias;
import estructuraTP.Utilidades.Dialogo;
import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;
import estructuraTPvista.Comuna.ComunaConsultarPN;

public class BarrioConsultar extends JPanel {

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
	
	private boolean esUno = false; 
	
	public BarrioConsultar() {
		setLayout(null);
		setBounds(800, 400, 800, 800);
		crearLabels();
        crearTabla();
		
	}
	
	private void crearLabels() {
		labelTitulo = new JLabel("Consultar Barrio");
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
		
		labelIngreseNombre = new JLabel("Ingrese el nombre del Barrio");
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
	private void crearTabla() {
		table =  new JTable();
		table.setBounds(57, 122, 300, 112);
		
		Object[] columnas = new Object[] {"Nombre" , "Superficie", "Tipo Barrio" };
		
		modelo = new DefaultTableModel(columnas, 0);
		table.setDefaultEditor(Object.class, null);
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
	}

	private void usosBotones()
	{
		botonBuscar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ArrayList<Barrio> barrios = Sentencias.barrio.consultarBarrios();
		        Barrio c = null;
		        String nombreBuscado = textField.getText();

		        for (int i = 0; i < barrios.size(); i++) {
		            if (barrios.get(i).getNombre().equalsIgnoreCase(nombreBuscado)) {
		                c = barrios.get(i);
		                break;
		            }
		        }

		        if (c == null) {
		            Dialogo.mostrarDialogo("Barrio no encontrado", BarrioConsultar.this);
		        } else {
		            Dialogo.mostrarDialogo("Barrio encontrado con exito", BarrioConsultar.this);

		            
		            String tipo = "";
		            if (c instanceof TrazadoFormal) {
		                tipo = "Trazado formal";
		            } else if (c instanceof UrbanizacionPlanificada) {
		                tipo = "Urbanizacion Planificada";
		            } else if (c instanceof ComplejoHabitacional) {
		                tipo = "Complejo Habitacional";
		            }

		            modelo.setRowCount(0);
		            modelo.addRow(new Object[] { c.getNombre(), c.getSuperficieEnHectarias(), tipo });
		        }
		    }
		});
		
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila <0 ) {
					Dialogo.mostrarDialogo("Seleccione una fila para borrar",BarrioConsultar.this);
				}
				else {
					Sentencias.barrio.eliminarBarrio(table.getValueAt(fila, 1).toString());
					Dialogo.mostrarDialogo("Se elimino con exito", BarrioConsultar.this);
					mostrarDespuesDeAlgo();
				}
			}
		});
		
		botonModificar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int fila = table.getSelectedRow();
		        if (fila < 0) {
		            Dialogo.mostrarDialogo("Seleccione una fila para modificar", BarrioConsultar.this);
		        } else {
		          
		            String nombreViejo = table.getValueAt(fila, 0).toString();
                    ArrayList<Barrio> barrios = Sentencias.barrio.consultarBarrios();
		            Barrio b = null;
		            for (int i = 0; i < barrios.size(); i++) {
		                if (barrios.get(i).getNombre().equalsIgnoreCase(nombreViejo)) {
		                    b = barrios.get(i);
		                    break;
		                }
		            }

		            Dialogo.mostrarDialogoCargarBarrio(b, BarrioConsultar.this);
		            

		            mostrarDespuesDeAlgo();
		        }
		    }
		});
				
		botonMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esUno = false;
				mostrarDespuesDeAlgo();
			}
		});
	}
	
	private void mostrarDespuesDeAlgo() {
		// ------------- ACA SE USA EL UNO
		if(esUno == true) {
			modelo.setRowCount(0);
		}
		else {
			modelo.setRowCount(0);
			ArrayList<Barrio> barrios = Sentencias.barrio.consultarBarrios();
			
			for(Barrio b: barrios) {
				if(b instanceof TrazadoFormal)
				{
					modelo.addRow(new Object[] {b.getNombre(), b.getSuperficieEnHectarias() ,"Trazado Formal" });
				}
				else if(b instanceof ComplejoHabitacional)
				{
					modelo.addRow(new Object[] {b.getNombre(), b.getSuperficieEnHectarias() , "Complejo Habitacional" });
				}
				else if(b instanceof UrbanizacionPlanificada)
				{
					modelo.addRow(new Object[] {b.getNombre(),b.getSuperficieEnHectarias() ,"Urbanizacion Planificada" });
				}

				
			}
		}
	}
	
	private void 
}
