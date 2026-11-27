package estructuraTPvista.Barrio;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import estructuraDAO.Sentencias;
import estructuraTP.Utilidades.Dialogo;
import estructuraTPmodelo.Persona;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;

public class BarrioConsultarPN extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelTitulo;
	private JLabel labelIngreseNombre;
	private JLabel labelNombre;
	private JTextField textField;
	private JTable table;
	private JButton botonModificar;
	
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JButton botonBorrar;
	
	private JButton botonVerMas;
	
	private ArrayList<Barrio> barrios = Sentencias.barrio.consultar();
	
	// Constructor para barrio
	public BarrioConsultarPN() {
	    setLayout(null);
	    setBounds(800, 400, 800, 800);
	    crearLabels();
	    crearTabla();
	    addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentShown(ComponentEvent e) {
		    	barrios = Sentencias.barrio.consultar();
		    	actualizarTabla(barrios);
		    }
		});
	    
	    usosBotonesBarrio();
	}
	
	private void crearLabels() {

		labelTitulo = new JLabel("Consultar barrios");
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
		
		botonBorrar = new JButton("Borrar");
		botonBorrar.setBounds(583, 239, 145, 38);
		add(botonBorrar);
		
		labelIngreseNombre = new JLabel("Ingrese el nombre del Barrio");
		labelIngreseNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelIngreseNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelIngreseNombre.setBounds(70, 76, 380, 26);
		add(labelIngreseNombre);
		
		botonBorrar.setBounds(583, 301, 145, 38);
		add(botonBorrar);
		
		botonModificar = new JButton("Modificar");
		botonModificar.setBounds(583, 170, 145, 38);
		add(botonModificar);
		
		botonVerMas = new JButton("Ver detalle");
		botonVerMas.setBounds(583, 235, 145, 38);
		add(botonVerMas);
		
		
	}
	

	private void usosBotonesBarrio()
	{
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nombre = textField.getText();
				if(!nombre.isEmpty()) {
					actualizarTabla(busquedaPorAproximacion(nombre));
				}else {
					actualizarTabla(barrios);
				}

			}
		});
		
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila <0 ) {
					Dialogo.mostrarDialogo("Seleccione una fila para borrar",BarrioConsultarPN.this);
				}
				else {
					ArrayList<Persona> personas = Sentencias.persona.consultarPorNombreBarrio(table.getValueAt(fila, 0) + "");
					if(personas.size() > 0){
						String nombreBarrio = Dialogo.persona.mostrarDialogomigrarDeBarrio(table.getValueAt(fila, 0) + "", BarrioConsultarPN.this);
						String antiguoNombre = table.getValueAt(fila, 0) + "";
						Sentencias.persona.actualizarElBarrioAlBorrar(antiguoNombre, nombreBarrio);
						Sentencias.barrio.eliminar(table.getValueAt(fila, 0).toString());
					}else {
						Sentencias.barrio.eliminar(table.getValueAt(fila, 0).toString());
						Dialogo.mostrarDialogo("Se elimino con exito", BarrioConsultarPN.this);
						
					}
					actualizarTabla(Sentencias.barrio.consultar());
					
				}
			}
		});
		
		botonModificar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int fila = table.getSelectedRow();
		        if (fila < 0) {
		            Dialogo.mostrarDialogo("Seleccione una fila para modificar", BarrioConsultarPN.this);
		        } else {
		          
		        	String nombreViejo = table.getValueAt(fila, 0).toString();
                    ArrayList<Barrio> barrios = Sentencias.barrio.consultar();
		            Barrio b = null;
		            for (int i = 0; i < barrios.size(); i++) {
		                if (barrios.get(i).getNombre().equalsIgnoreCase(nombreViejo)) {
		                    b = barrios.get(i);
		                    break;
		                }
		            }
		            Dialogo.barrio.mostrarDialogoConCargarBarrio(b, BarrioConsultarPN.this);

		            actualizarTabla(Sentencias.barrio.consultar());
		        }
		    }
		});
		

		botonVerMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
		        if (fila < 0) {
		            Dialogo.mostrarDialogo("Seleccione una fila para modificar", BarrioConsultarPN.this);
		        } else {
		        	String nombreBarrio = table.getValueAt(fila, 0) + "";
		        	
		        	Barrio b = Sentencias.barrio.consultarPorNombre(nombreBarrio);
		        	b.setPersonas(Sentencias.persona.consultarPorNombreBarrio(b.getNombre()));
		        	Dialogo.barrio.mostrarDialogoConTablaBarrios(b, BarrioConsultarPN.this);
		        }
			}
		});
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
	
	private void actualizarTabla(ArrayList<Barrio> barrios) {
		modelo.setRowCount(0);
		for(Barrio b: barrios) {
				if(b instanceof TrazadoFormal)
				{
					TrazadoFormal tf = (TrazadoFormal) b;
					modelo.addRow(new Object[] {tf.getNombre(), tf.getSuperficieEnHectarias() ,"Trazado Formal" });
				}
				else if(b instanceof ComplejoHabitacional)
				{
					ComplejoHabitacional ch = (ComplejoHabitacional) b;
					modelo.addRow(new Object[] {ch.getNombre(), ch.getSuperficie() , "Complejo Habitacional" });
				}
				else if(b instanceof UrbanizacionPlanificada)
				{
					UrbanizacionPlanificada up = (UrbanizacionPlanificada) b;
					modelo.addRow(new Object[] {up.getNombre(),up.getSuperficie() ,"Urbanizacion Planificada" });
				}

				
			}
		}
	
	
	private ArrayList<Barrio> busquedaPorAproximacion(String nombre){
		String nombreMinuscula = nombre.toLowerCase();
		char primeraLetraBuscado = nombreMinuscula.charAt(0);
		ArrayList<Barrio> barrioOrdenadas = ordenarNombres();
		ArrayList<Barrio> barrioBuscadas = new ArrayList<>();
		boolean salir = false;
		int indice = 0;
		while(salir == false) {
			String nombrePersona = barrioOrdenadas.get(indice).getNombre().toLowerCase();
			char primeraLetra = nombrePersona.charAt(0);
			
			if(primeraLetraBuscado == primeraLetra) {
				barrioBuscadas.add(barrioOrdenadas.get(indice));
			}else if(primeraLetraBuscado < primeraLetra){
				salir = true;
			}
			indice++;

			if(indice == barrioOrdenadas.size()) {
				salir = true;
			}
		}
		if(nombreMinuscula.length() > 1) {
			int contadorIguales= 0;
			
			for(int i = barrioBuscadas.size() - 1; i >= 0; i--) {
				String nombreBuscado = barrioBuscadas.get(i).getNombre().toLowerCase();
				contadorIguales = 0;
				if(nombreBuscado.length() >= nombreMinuscula.length()) {
					for(int j = 1; j < nombreMinuscula.length(); j++) {
						char letraBuscado = nombreBuscado.charAt(j);
						char letraIngresada = nombreMinuscula.charAt(j);
						
						if(letraBuscado == letraIngresada) {
							contadorIguales++;
						}
					}
				}
				 if(contadorIguales != nombreMinuscula.length() - 1) {
	                    barrioBuscadas.remove(i);
	            }
			}	
			
		}
		return barrioBuscadas;
	}
	
	private ArrayList<Barrio> ordenarNombres() {
		ArrayList<Barrio> barriosOrdenadas = cargarArrayList();
		int maximo = barriosOrdenadas.size();
		for(int i = 0; i < maximo; i++) {
			for(int j = 0; j < maximo - 1 - i; j++) {
				String nombre1 = barriosOrdenadas.get(j).getNombre().toLowerCase();
				String nombre2 = barriosOrdenadas.get(j+1).getNombre().toLowerCase();
				
				int codigoAscii1 = nombre1.charAt(0);
				int codigoAscii2 = nombre2.charAt(0);
				
				if(codigoAscii1 > codigoAscii2) {
					Barrio aux = barriosOrdenadas.get(j);
					barriosOrdenadas.set(j, barriosOrdenadas.get(j+1));
					barriosOrdenadas.set(j+1, aux);
				}
			}
		}
		return barriosOrdenadas;
	}
	
	private ArrayList<Barrio> cargarArrayList(){
		ArrayList<Barrio> barrioOrdenadas = new ArrayList<>();
		for(int i = 0; i < barrios.size(); i++) {
			barrioOrdenadas.add(barrios.get(i));
		}
		return barrioOrdenadas;
	}
	
}



