package estructuraTPmodelo.Barrios;

public class TrazadoFormal extends Barrio{
	private int cantidadEdificios;
	private float porcentajeAsfalto;
	
	public TrazadoFormal(String nombre, float superficie, int cantidadEdificios, float porcentajeAsfalto) {
		this.nombre = nombre;
		this.superficieEnHectarias = superficie;
		this.cantidadEdificios = cantidadEdificios;
		this.porcentajeAsfalto = porcentajeAsfalto;
	}
	
	public TrazadoFormal(String nombre) {
		this.nombre = nombre;
	}

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
	
	public int getCantidadEdificios() {
		return cantidadEdificios;
	}
	public float getPorcentajeAsfalto() {
		return porcentajeAsfalto;
	}


	
	
}
