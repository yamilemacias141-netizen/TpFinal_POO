package estructuraTPmodelo.Barrios;

public class TrazadoFormal extends Barrio{
	private int cantidadEdificios;
	private float porcentajeAsfalto;
	
	public TrazadoFormal(String nombre) {
		super(nombre);
		
	}
	
	public TrazadoFormal(String nombre, float superficie, int cantidadEdificios2, float porcentajeAsfalto) {
		super(nombre,superficie);
		this.cantidadEdificios=cantidadEdificios;
		this.porcentajeAsfalto=porcentajeAsfalto;
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
	public String getNombre() {
		return nombre;
	}
	public float getSuperficie() {
	
		return superficie;
	}
	public int getCantudadEdificios() {
		return cantidadEdificios;
	}
	public float getPorcentajeAsfalto() {
		return porcentajeAsfalto;
	}
}
