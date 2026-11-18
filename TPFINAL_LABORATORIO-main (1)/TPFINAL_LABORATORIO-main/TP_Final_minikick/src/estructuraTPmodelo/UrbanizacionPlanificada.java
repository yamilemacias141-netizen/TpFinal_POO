package estructuraTPmodelo;

public class UrbanizacionPlanificada extends Barrio {
	private String numeroLey;
	private boolean tieneConexionAgua;
	
	public UrbanizacionPlanificada(String nombre,float superficie,String numeroLey,boolean tieneConexionAgua)
	{
		super(nombre,superficie);
		this.numeroLey=numeroLey;
		this.tieneConexionAgua=tieneConexionAgua;
	}
	@Override
	public float calculoDensidad() {
		return 0;
	}
	@Override
	public boolean criticidadHabitacional() {
		return false;
	}
}
