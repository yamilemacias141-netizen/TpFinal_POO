package estructuraTPmodelo.Barrios;

import java.util.ArrayList;

import estructuraTPmodelo.Persona;

public abstract class  Barrio {
	protected String nombre;
	protected float superficieEnHectarias;
	protected ArrayList<Persona> personas = new ArrayList<>();
	
	public abstract float calculoDensidad() ;
	
	public abstract boolean criticidadHabitacional();

	public String getNombre()
	{
		return nombre;
	}
	public float getSuperficieEnHectarias()
	{
		return superficieEnHectarias;
	}
}
