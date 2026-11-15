package estructuraDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTPmodelo.Barrio;
import estructuraTPmodelo.ComplejoHabitacional;
import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Persona;
import estructuraTPmodelo.TrazadoFormal;
import estructuraTPmodelo.UrbanizacionPlanificada;

public class DAO {


	private String user = "root";
	private String psw = "123456";
	private String path = "jdbc:mysql://localhost:3306/tp_final";

	private int filas;

	public Connection conectar() {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(path, user, psw);

			System.out.println("se conecto");
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return conexion;
	}
	public String obtenerIdSituacionLaboral(String situacionLaboral)
	{
		String sql = "SELECT idSituacionLaboral FROM tipoSituacionLaboral WHERE nombreSituacionLaboral = '" + situacionLaboral+"';";
		Connection conexion = null;
		int id=0;
		try {
			conexion = conectar();
			Statement stnt = conexion.createStatement();
		    ResultSet rs = stnt.executeQuery(sql);	
			while(rs.next())
			{
				id = rs.getInt("idSituacionLaboral");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return String.valueOf(id);
	}
	
	public String obtenerIdBarrio(String nombreBarrio)
	{
		String sql = "SELECT idBarrio FROM barrio WHERE nombreBarrio = '" + nombreBarrio +"';";
		Connection conexion = null;
		int id=0;
		try {
			conexion = conectar();
			Statement stnt = conexion.createStatement();
		    ResultSet rs = stnt.executeQuery(sql);	
			while(rs.next())
			{
				id = rs.getInt("idBarrio");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return String.valueOf(id);
	}
	
	
	
	public void agregarPersona(Persona p, String situacionLaboral, String nombreBarrio) {
		Connection conexion = null;
		
		String sql = "INSERT INTO persona (nombrePersona,fechaNacimientoPersona,dniPersona, barrio_idBarrio,tiposituacionlaboral_idtipoSituacionLaboral)VALUES(?,?,"+ obtenerIdBarrio(nombreBarrio) +","+ obtenerIdSituacionLaboral(situacionLaboral) +")"+";";
		try {
			conexion = conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, p.getNombre());
			stmt.setDate(2, p.getFechaNacimiento());
			stmt.setString(3, p.getDNI());
			stmt.executeUpdate();
			System.out.println("se agrego");
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void agregarBarrioTrazadoFormal (TrazadoFormal t ) {
		Connection conexion = null;
		String sql = "INSERT INTO barrio (nombreBarrio,superficieBarrio,cantidadEdificiosBarrio,porcentjaAsfaltoBarrio,numeroLeyCabaBarrio, conexionRedAguaBarrio,cantidadMonoblocksBarrio,consorcioAdministracionBarrio )VALUES(?,?,?,?,?,?,?,?);";
		try {
			conexion = conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, t.getNombre());
			stmt.setFloat(2, t.getSuperficie());
			stmt.setInt(3, t.getCantudadEdificios());
			stmt.setFloat(4, t.getPorcentajeAsfalto());
			stmt.executeUpdate();
			System.out.println("se agrego");
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void agregarBarrioUrbanizacionPlanificada (UrbanizacionPlanificada u ) {
		Connection conexion = null;
		String sql = "INSERT INTO barrio (nombreBarrio,superficieBarrio,cantidadEdificiosBarrio,porcentjaAsfaltoBarrio,numeroLeyCabaBarrio, conexionRedAguaBarrio,cantidadMonoblocksBarrio,consorcioAdministracionBarrio )VALUES(?,?,?,?,?,?,?,?);";
		try {
			conexion = conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, u.getNombre());
			stmt.setFloat(2, u.getSuperficie());
			stmt.setString(5, u.getNumeroLey());
			stmt.setBoolean(6, u.getTieneConexionAgua());
			stmt.executeUpdate();
			System.out.println("se agrego");
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void agregarBarrioComplejoHabitacional(ComplejoHabitacional c ) {
		Connection conexion = null;
		String sql = "INSERT INTO barrio (nombreBarrio,superficieBarrio,cantidadEdificiosBarrio,porcentjaAsfaltoBarrio,numeroLeyCabaBarrio, conexionRedAguaBarrio,cantidadMonoblocksBarrio,consorcioAdministracionBarrio )VALUES(?,?,?,?,?,?,?,?);";
		try {
			conexion = conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, c.getNombre());
			stmt.setFloat(2, c.getSuperficie());
			stmt.setInt(7, c.getCantidadMonoblock());
			stmt.setBoolean(8, c.getTieneConsorcioAdministracion());
			stmt.executeUpdate();
			System.out.println("se agrego");
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void agregarComuna(Comuna c ) {
		Connection conexion = null;
		
		String sql = "INSERT INTO comuna (nombreComuna, numeroComuna)VALUES(?,?);";
		try {
			conexion = conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, c.getNombre());
			stmt.setFloat(3, c.getNumeroComuna());
			stmt.executeUpdate();
			System.out.println("se agrego");
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void  actualizarPersona(String dni, String situacionLaboralNuevo)
	{
		String sql = "UPDATE persona SET tiposituacionlaboral_idtipoSituacionLaboral = "+ obtenerIdSituacionLaboral(situacionLaboralNuevo) +" WHERE dni = ?;";
		Connection conexion = null;
		try {
			conexion = conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dni);
			stmt.executeUpdate();
			System.out.println("se actualizo");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	public void eliminarPersona(String dni)
	{
		String sql = "DELETE FROM `tabla`WHERE dni = ?;";
		Connection conexion = null;
		try {
			conexion = conectar();
			PreparedStatement stnt = conexion.prepareStatement(sql);
			stnt.setString(1,dni);
			stnt.executeUpdate();
			System.out.println("se elimino");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	public ArrayList<Persona> consultarPersona()
	{
		String sql = "SELECT nombrePersona, fechaNacimientoPersona, dniPersona,tiposituacionlaboral_idtipoSituacionLaboral,   FROM persona ";
		Connection conexion = null;
		ArrayList<Persona> solucion = new ArrayList<>();
		try {
			conexion = conectar();
			Statement stnt = conexion.createStatement();
		    ResultSet rs = stnt.executeQuery(sql);	
			while(rs.next())
			{
				String nombre = rs.getString("nombrePersona");
				Date fechaNacimiento = rs.getDate("fechaNacimientoPersona");
			    String dni = rs.getString("dniPersona");
			    String tipoSituacionLaboral = rs.getString("tipoSituacionLaboral");
			    Persona p = new Persona(nombre,fechaNacimiento, dni,obtenerIdSituacionLaboral("tiposituacionlaboral_idtipoSituacionLaboral") ,obtenerIdBarrio("barrio_idBarrio") );
			    solucion.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return solucion;
	}

	
	
	
	public ArrayList<Barrio> consultarBarrios()
	{
		String sql = "SELECT nombrePersona, fechaNacimientoPersona, dniPersona, tipoSituacionLaboral FROM persona ";
		Connection conexion = null;
		ArrayList<Barrio> solucion = new ArrayList<>();
		try {
			conexion = conectar();
			Statement stnt = conexion.createStatement();
		    ResultSet rs = stnt.executeQuery(sql);	
			while(rs.next())
			{
				//int id = rs.getInt("id");
				//String nombre = rs.getString("nombre");
				//String dni = rs.getString("dni");
				//Persona p1 = new Persona ();
				//solucion.add(p1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return solucion;
	}
	
	
	
	
	public void  actualizarBarrios( String nombree, String dniNuevo)
	{
		String sql = "UPDATE tabla SET nombre = ? , dni = ? WHERE dni = ?;";
		Connection conexion = null;
		try {
			conexion = conectar();
			PreparedStatement stnt = conexion.prepareStatement(sql);
			stnt.setString(1, nombree);
			stnt.setString(2, dniNuevo);
	
			System.out.println("se actualizo");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	public ArrayList<Comuna> consultar()
	{
		String sql = "SELECT nombreConsultar, numeroComuna FROM comuna; ";
		Connection conexion = null;
		ArrayList<Comuna> solucion = new ArrayList<>();
		try {
			conexion = conectar();
			Statement stnt = conexion.createStatement();
		    ResultSet rs = stnt.executeQuery(sql);	
			while(rs.next())
			{
				String nombre = rs.getString("nombreConsultar");
				int numero = rs.getInt("numeroComuna");
				Comuna c = new Comuna(nombre, numero);
			    solucion.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return solucion;
	}
	


	public void agregar() {
		String sql = "INSERT INTO `tabla`(`nombre`,`dni`)VALUES('Lautaro',12345678) , ('Yamile',48856758) ;";
		Connection conexion = null;
		try {
			conexion = conectar();
			Statement stnt = conexion.createStatement();
			stnt.executeUpdate(sql);
			System.out.println("se agrego");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
	

	
	public void eliminar(String dni)
	{
		String sql = "DELETE FROM `tabla`WHERE dni = ?;";
		Connection conexion = null;
		try {
			conexion = conectar();
			PreparedStatement stnt = conexion.prepareStatement(sql);
			stnt.setString(1,dni);
			stnt.executeUpdate(sql);
			System.out.println("se elimino");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	public void  actualizar( String nombree, String dniNuevo)
	{
		String sql = "UPDATE tabla SET nombre = ? , dni = ? WHERE dni = ?;";
		Connection conexion = null;
		try {
			conexion = conectar();
			PreparedStatement stnt = conexion.prepareStatement(sql);
			stnt.setString(1, nombree);
			stnt.setString(2, dniNuevo);
	
			System.out.println("se actualizo");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	public void agregarPersonaPorParametro() {
		Connection conexion = null;
		
		String sql = "INSERT INTO persona (nombrePersona,fechaNacimientoPersona,tiposituacionlaborall )VALUES(?,?,?,?);";
		try {
			conexion = conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, "");
			//stmt.set
			stmt.setString(3, "");
		
		 
			System.out.println("se agrego");
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}


