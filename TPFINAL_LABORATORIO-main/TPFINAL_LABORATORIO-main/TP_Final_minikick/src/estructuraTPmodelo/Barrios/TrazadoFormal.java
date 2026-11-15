package estructuraTPmodelo.Barrios;

public class TrazadoFormal extends Barrio{
	private int cantidadEdificios;
	private float porcentajeAsfalto;
	
	@Override
	public float calculoDensidad() {
		float aux  = personas.size() / cantidadEdificios;
		return aux;
	}
	@Override
	public boolean criticidadHabitacional() {
		Boolean aux = false;
		if(porcentajeAsfalto > 95) {
			aux =true;
		}
		return aux;
	}
}
