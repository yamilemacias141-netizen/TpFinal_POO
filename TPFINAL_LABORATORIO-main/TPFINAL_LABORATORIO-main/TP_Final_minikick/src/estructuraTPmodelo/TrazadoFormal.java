package estructuraTPmodelo;

public class TrazadoFormal extends Barrio{

	private int cantidadEdificios;
	private float porcentajeAsfalto;
	
	public TrazadoFormal(String nombre, float superficie, int cantidadEdificios, float porcentajeAsfalto) {
		super(nombre, superficie);
		this.cantidadEdificios=cantidadEdificios;
        this.porcentajeAsfalto=porcentajeAsfalto;
		
	}
	@Override
	public float calculoDensidad() {
		return 0;
	}
	@Override
	public boolean criticidadHabitacional() {
		Boolean aux = false;
		if(porcentajeAsfalto > 95) {
			aux =true;
		}
		return aux;
	}
	
	public int getCantudadEdificios()
	{
		return cantidadEdificios;
	}
	
	public float getPorcentajeAsfalto()
	{
		return porcentajeAsfalto;
	}
}
