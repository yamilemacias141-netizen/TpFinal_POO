package estructuraTPmodelo;

import java.util.ArrayList;

public abstract class  Barrio {
	private String nombre;
	private float superficie;
	private ArrayList<Persona> personas = new ArrayList<>();
	
	public Barrio(String nombre, float superficie)
	{
		this.nombre=nombre;
		this.superficie=superficie;
	}
	public abstract float calculoDensidad() ;
	
	public abstract boolean criticidadHabitacional();

	public String getNombre() {
		return nombre;
	}

	public float getSuperficie() {
		return superficie;
	}
}
