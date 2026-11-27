package estructuraTP;

import java.awt.EventQueue;

import estructuraTPvista.MarcoPrincipal;

public class App {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MarcoPrincipal frame = new MarcoPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				char letra = 92;
				System.out.println();
			}
		});
		
		/*TODO
		 * busqueda por aproximacion en todo
		 * 
		 * testear conexion;s
		 */
		
		
		
		/*
		 * / Mandamientos
		 * - Una persona tiene que tener un barrio asociado
		 * - Un Barrio debe tener una comuna asociada.
		 * - El nombre de una persona no puede estar vacío
		 * - El nombre de un barrio no puede estar vacío
		 * - El nombre de la sede comunal de una comuna no puede estar vacío
		 * - 
		 */
		
	}

}
