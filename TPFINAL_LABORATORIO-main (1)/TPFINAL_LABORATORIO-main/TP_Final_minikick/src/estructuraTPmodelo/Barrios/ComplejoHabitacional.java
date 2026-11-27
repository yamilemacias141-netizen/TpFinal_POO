package estructuraTPmodelo.Barrios;

public class ComplejoHabitacional extends Barrio{
	private int cantidadMonoblock;
	private boolean tieneConsorcioAdministracion;
	
	public ComplejoHabitacional(String nombre, float superficie, int cantidadMonoblocks, boolean consorcioAdministracion) {
		this.nombre = nombre;
		this.superficieEnHectarias = superficie;
		this.cantidadMonoblock = cantidadMonoblocks;
		this.tieneConsorcioAdministracion = consorcioAdministracion;
	}
	
	public ComplejoHabitacional(String nombre) {
		this.nombre = nombre;
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
		return nombre;
	}
	public float getSuperficie() {
		return superficieEnHectarias;
	}
	public int getCantidadMonoblock() {
		return cantidadMonoblock;
	}
	public boolean getTieneConsorcioAdministracion() {
		return tieneConsorcioAdministracion;
	}
	
}
