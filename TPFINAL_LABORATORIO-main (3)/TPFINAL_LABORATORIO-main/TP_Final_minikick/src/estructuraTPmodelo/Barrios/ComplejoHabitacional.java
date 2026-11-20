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
