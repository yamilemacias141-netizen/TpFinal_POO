package estructuraTPmodelo;

import java.sql.Date;
import java.time.LocalDate;

public class Persona {
	private String nombre;
	private LocalDate fechaNacimiento;
	private TipoSituacionLaboral tipo;
	private String DNI;
	
	public Persona(String nombre, LocalDate fechaNacimiento, String DNI, TipoSituacionLaboral tipo) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.tipo = tipo;
		this.DNI = DNI;
	}

	public TipoSituacionLaboral getTipo() {
		return tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getDNI() {
		return DNI;
	}
	
	
}
