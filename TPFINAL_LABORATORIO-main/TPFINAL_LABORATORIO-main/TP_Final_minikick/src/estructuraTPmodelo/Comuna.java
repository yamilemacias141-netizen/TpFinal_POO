package estructuraTPmodelo;

import java.util.ArrayList;

import estructuraTPmodelo.Barrios.Barrio;

public class Comuna {
	private String nombre;
	private int numeroComuna;
	private ArrayList<Barrio> barrios = new ArrayList<>();
	private int cantidadBarrios =barrios.size() ;
	
	public Comuna(String nombre,int nummeroComuna)
	{
		this.nombre=nombre;
		this.numeroComuna=numeroComuna;
		
	}
	
	
	public boolean esDeAtencionPrioritaria() {
		float porcentaje  = losCriticos() / cantidadBarrios;
		boolean aux = false;
		if(porcentaje >= 0.5) {
			aux = true;
		}
		return aux;
	}
	
	public int losCriticos() {
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
}
