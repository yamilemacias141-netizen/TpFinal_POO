package estructuraTPmodelo;

public enum TipoSituacionLaboral {
	OCUPADO,
	DESOCUPADO,
	SUBOCUPADO;
	
	
	
	 public static TipoSituacionLaboral obtenerTipoSituacionLaboral(int indice) {
		    TipoSituacionLaboral tipo = null;
	        for (TipoSituacionLaboral t : values()) {
	            if(t.ordinal() == indice) {
	            	tipo = t;
	            }
	        }
	        return tipo;
	    }
}
