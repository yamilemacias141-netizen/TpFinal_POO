package estructuraTPmodelo;

import java.time.LocalDate;
import java.util.ArrayList;

import estructuraTPmodelo.Barrios.Barrio;

public class Comuna {
	private String nombre;
	private int numeroComuna;
	private int cantidadBarrios ;
	private ArrayList<Barrio> barrios = new ArrayList<>();
	
	// para consultar todo y mostrarlo en la tabla
	public Comuna(String nombre, int numeroComuna, int cantidadBarrios) {
		this.nombre = nombre;
		this.numeroComuna = numeroComuna;
		this.cantidadBarrios = cantidadBarrios;
	}
	
	public Comuna(String nombre, int numeroComuna) {
		this.nombre = nombre;
		this.numeroComuna = numeroComuna;
	}
	
	public boolean esDeAtencionPrioritaria() {
		float porcentaje  = losCriticos() / cantidadBarrios;
		boolean aux = false;
		if(porcentaje >= 0.5) {
			aux = true;
		}
		return aux;
	}
	
	private int losCriticos() {
		int contadorCriticos = 0;
		for(Barrio b: barrios) {
			if(b.criticidadHabitacional()) {
				contadorCriticos ++;
			}
		}
		return contadorCriticos;
	}
	
	public int getcantidarBarrios() {
		return cantidadBarrios;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getNumeroComuna() {
		return numeroComuna;
	}
	
	public void setBarrios(ArrayList<Barrio> barrios) {
		this.barrios = barrios;
	}
	
	public int tamañoLista() {
		return barrios.size();
	}
}
