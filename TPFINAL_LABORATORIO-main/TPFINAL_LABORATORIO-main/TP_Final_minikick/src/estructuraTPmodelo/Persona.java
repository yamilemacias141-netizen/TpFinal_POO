package estructuraTPmodelo;

import java.sql.Date;
import java.time.LocalDate;

public class Persona {
	private String nombre;
	private Date fechaNacimiento;
	private String situacionLaboral;
	private String barrio;
	private TipoSituacionLaboral tipo;
	private String DNI;
	
	public Persona(String nombre, Date fechaNacimiento, String dni,String situacionLaboral,String barrio)
	{
		this.nombre=nombre;
		this.fechaNacimiento=fechaNacimiento;
		this.DNI = dni;
		this.situacionLaboral=situacionLaboral;
	}
	public String getNombre() {
		return nombre;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public TipoSituacionLaboral getTipo() {
		return tipo;
	}
	public String getDNI() {
		return DNI;
	}
	
	
	
}
