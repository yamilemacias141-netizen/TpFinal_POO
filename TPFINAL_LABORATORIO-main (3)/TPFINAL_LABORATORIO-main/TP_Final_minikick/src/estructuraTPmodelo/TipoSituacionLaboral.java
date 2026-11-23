package estructuraTPmodelo;

public enum TipoSituacionLaboral {
	OCUPADO("Ocupado"),
	DESOCUPADO("Desocupado"),
	SUBOCUPADO("Subocupado");
	
	private String nombre;
	
	TipoSituacionLaboral(String nombre)
	{
		this.nombre=nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	 public static TipoSituacionLaboral obtenerTipoSituacionLaboral(String nombre) {
		    TipoSituacionLaboral tipo = null;
	        for (TipoSituacionLaboral t : values()) {
	            if (t.getNombre().equalsIgnoreCase(nombre)) {
	                tipo = t;
	            }
	        }
	        return tipo;
	    }
}
