package estructuraDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Persona;
import estructuraTPmodelo.TipoSituacionLaboral;

public abstract class DAOPersona{
	
	private Conexion conect;
	public DAOPersona(Conexion conectar) {
		this.conect = conectar;
	}
	public boolean agregarPersona(Persona p,String nombreSituacionLaboral, String barrio) {
		boolean sePudoCargar = true;
		Connection conexion = null;
		//SANTY MODIFICA LOS NOMBRES DE COMO TENES LA FK DE BARRIO Y TIPOSITUACIONLABORAL EN PERSONA
		String sql = "INSERT INTO persona (nombrePersona , fechaNacimientoPersona,dniPersona , barrio_idBarrio, tipoSituacionLaboral_idtipoSituacionLaboral)VALUES(?,?,?,(SELECT idBario FROM barrio WHERE nombreBarrio =?  ),(SELECT idtipoSituacionLaboral FROM tipoSituacionLaboral WHERE nombreTipoSituacionLaboral  =?));";
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, p.getNombre());
			stmt.setDate(2, p.getFechaNacimiento());
			stmt.setString(3, p.getDNI());
			stmt.setString(4,barrio);
			stmt.setString(5, nombreSituacionLaboral);
			
			stmt.executeUpdate();
			
			stmt.close();
		}catch(SQLException e){
			sePudoCargar = false;
			e.printStackTrace();
		
		}
		return sePudoCargar;
	}
	
	public ArrayList<Persona> consultarPersonas()
	{
		String sql = "SELECT p.nombrePersona , p.fechaNacimientoPersona , p.dniPersona, b.nombreBarrio , t.nombreTipoSituacionLaboral FROM persona p INNER JOIN barrio b ON p.barrio_idBarrio = b.idBarrio INNER JOIN tipoSituacionLaboral t ON p.tipoSituacionLaboral_idtipoSituacionLaboral = t.idtipoSituacionLaboral";
		
		Connection conexion = null;
		ArrayList<Persona> solucion = new ArrayList<>();
		try {
			conexion = conect.conectar();
			Statement stmt = conexion.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);	
			while(rs.next())
			{
				String nombre = rs.getString("nombrePersona");
				Date fechaNacimiento = rs.getDate("fechaNacimientoPersona");
				String dni = rs.getString("dniPersona");
				TipoSituacionLaboral tipo = TipoSituacionLaboral.obtenerTipoSituacionLaboral(rs.getString("nombreTipoSituacionLaboral"));
				String nombreBarrio = rs.getString("nombreBarrio");
				Persona p = new Persona(nombre,fechaNacimiento,dni,tipo,nombreBarrio);
				solucion.add(p);
			}
			rs.close();
			stmt.close();
		    conexion.close();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return solucion;
	}
	

	public ArrayList<Persona> consultarPersona(String dniBusqueda)
	{
		String sql = "SELECT p.nombrePersona , p.fechaNacimientoPersona , p.dniPersona, b.nombreBarrio , t.nombreTipoSituacionLaboral FROM persona p INNER JOIN barrio b ON p.barrio_idBarrio = b.idBarrio INNER JOIN tipoSituacionLaboral t ON p.tipoSituacionLaboral_idtipoSituacionLaboral = t.idtipoSituacionLaboral WHERE dni = ?";
		
		Connection conexion = null;
		ArrayList<Persona> solucion = new ArrayList<>();
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dniBusqueda);
		    ResultSet rs = stmt.executeQuery(sql);	
			while(rs.next())
			{
				String nombre = rs.getString("nombrePersona");
				Date fechaNacimiento = rs.getDate("fechaNacimientoPersona");
				String dni = rs.getString("dniPersona");
				TipoSituacionLaboral tipo = TipoSituacionLaboral.obtenerTipoSituacionLaboral(rs.getString("nombreTipoSituacionLaboral"));
				String nombreBarrio = rs.getString("nombreBarrio");
				
				Persona p = new Persona(nombre,fechaNacimiento,dni,tipo,nombreBarrio);
				solucion.add(p);
			}
			rs.close();
			stmt.close();
		    conexion.close();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return solucion;
	}
	
	
	public boolean  actualizarPersona(Persona p, String nombreBarrio , String nombreSituacionLaboral, String dni)
	{
		boolean sePudoCargar = true;
		String sql = "UPDATE persona SET nombrePersona = ?, fechaNacimientoPersona = ? , dniPersona = ? , barrio_idBarrio = (SELECT idBarrio FROM barrio WHERE nombreBarrio = ?), tipoSituacionLaboral_idtipoSituacionLabora = (SELECT idtipoSituacionLaboral FROM tipoSituacionLaboral nombreTipoSituacionLaboral = ?) WHERE dniPersona =  ?";
		Connection conexion = null;
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1,p.getNombre());
			stmt.setDate(2, p.getFechaNacimiento());
			stmt.setString(3,p.getDNI());
			stmt.setString (4 , nombreBarrio);
			stmt.setString(5, nombreSituacionLaboral );
			stmt.setString(6, dni);
			
			stmt.executeUpdate();
			stmt.close();
			conexion.close();
		} catch (SQLException e) {
			sePudoCargar = false;
			e.printStackTrace();
		} 

		
		return sePudoCargar;
	}
	
	public void eliminarPersona(String dni)
	{
		String sql = "DELETE FROM persona WHERE dni = ?";
		Connection conexion = null;
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dni);
			stmt.executeUpdate();
			stmt.close();
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
