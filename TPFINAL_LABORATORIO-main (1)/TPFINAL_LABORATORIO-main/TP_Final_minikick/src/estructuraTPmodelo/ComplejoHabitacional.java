package estructuraTPmodelo;

public class ComplejoHabitacional extends Barrio{
	private int cantidadMonoblock;
	private boolean tieneConsorcioAdministracion;
	public ComplejoHabitacional(String nombre,float superficie,int cantidadMonoblocks, boolean tieneConsorcioAdministracion)
	{
		super(nombre,superficie);
		this.cantidadMonoblock=cantidadMonoblocks;
		this.tieneConsorcioAdministracion=tieneConsorcioAdministracion;
		
	}
	@Override
	public float calculoDensidad() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean criticidadHabitacional() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
