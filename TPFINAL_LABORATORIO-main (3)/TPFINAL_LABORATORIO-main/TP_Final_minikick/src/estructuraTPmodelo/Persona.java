package estructuraTPmodelo;

import java.sql.Date;
import java.time.LocalDate;

public class Persona {
	private String nombre;
	private Date fechaNacimiento;
	private TipoSituacionLaboral tipo;
	private String DNI;
	private String nombreBarrio;
	public Persona(String nombre, Date fechaNacimiento,  String DNI,TipoSituacionLaboral tipo, String nombreBarrio) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.tipo = tipo;
		this.DNI = DNI;
		this.nombreBarrio = nombreBarrio;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getDNI() {
		return DNI;
	}

	public String getNombreBarrio() {
		return nombreBarrio;
	}
	
	
}
