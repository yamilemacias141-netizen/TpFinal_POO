package estructuraTPmodelo.Barrios;

public class ComplejoHabitacional extends Barrio{
	private int cantidadMonoblock;
	private boolean tieneConsorcioAdministracion;
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
	
}
