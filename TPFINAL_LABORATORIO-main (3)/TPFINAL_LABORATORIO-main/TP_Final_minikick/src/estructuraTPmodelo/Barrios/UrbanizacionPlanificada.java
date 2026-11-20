package estructuraTPmodelo.Barrios;

public class UrbanizacionPlanificada extends Barrio {
	private String numeroLey;
	private boolean tieneConexionAgua;
	
	public UrbanizacionPlanificada(String nombre, float superficie, String numeroLeyCaba, boolean conexionRedAgua) {
		this.nombre = nombre;
		this.superficieEnHectarias = superficie;
		this.numeroLey = numeroLeyCaba;
		this.tieneConexionAgua = conexionRedAgua;
	}
	
	public UrbanizacionPlanificada(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public float calculoDensidad() {
		float cantidadHectareas = superficieEnHectarias;
		float aux = personas.size() / cantidadHectareas;
		return aux;
	}
	@Override
	public boolean criticidadHabitacional() {
		boolean aux = false;
		if(tieneConexionAgua == false) {
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
	public String getNumeroLey() {
		return numeroLey;
	}
	public boolean getTieneConexionAgua() {
		return tieneConexionAgua;
	}
}
