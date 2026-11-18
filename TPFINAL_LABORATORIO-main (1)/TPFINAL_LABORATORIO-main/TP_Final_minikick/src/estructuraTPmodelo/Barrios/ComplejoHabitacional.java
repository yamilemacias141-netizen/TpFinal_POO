package estructuraTPmodelo.Barrios;

public class ComplejoHabitacional extends Barrio{
	private int cantidadMonoblock;
	private boolean tieneConsorcioAdministracion;
	

	
	public ComplejoHabitacional(String nombre, float superficie, int cantidadMonoblock,
			boolean tieneConsorcioAdministracion) {
		super(nombre,superficie);
		this.cantidadMonoblock=cantidadMonoblock;
		this.tieneConsorcioAdministracion=tieneConsorcioAdministracion;
	}

	public ComplejoHabitacional(String nombre) {
		super(nombre);
	}

	@Override
	public float calculoDensidad() {
		float aux = personas.size() / cantidadMonoblock;
		return aux;
	}
	@Override
	public boolean criticidadHabitacional() {
		boolean aux = false;
		if(tieneConsorcioAdministracion == false) {
			aux = true;
		}
		return aux;
	}
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}
	public float getSuperficie() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getCantidadMonoblock() {
		// TODO Auto-generated method stub
		return 0;
	}
	public boolean getTieneConsorcioAdministracion() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
