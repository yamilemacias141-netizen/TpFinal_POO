package estructuraDAO;

public class Sentencias {
	
	private static final Conexion conexion = new Conexion();
	
	public static final DAOComuna comuna = new DAOComuna(conexion);
	public static final DAOBarrio barrio = new DAOBarrio(conexion);
	public static final DAOPersona persona = null;
	

}
