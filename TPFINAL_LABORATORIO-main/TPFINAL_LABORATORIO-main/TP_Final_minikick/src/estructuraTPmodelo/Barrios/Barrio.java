package estructuraTPmodelo.Barrios;

import java.util.ArrayList;

import estructuraTPmodelo.Persona;

public abstract class  Barrio {
	private String nombre;
	protected float superficie;
	protected ArrayList<Persona> personas = new ArrayList<>();
	
	public abstract float calculoDensidad() ;
	
	public abstract boolean criticidadHabitacional();
}
