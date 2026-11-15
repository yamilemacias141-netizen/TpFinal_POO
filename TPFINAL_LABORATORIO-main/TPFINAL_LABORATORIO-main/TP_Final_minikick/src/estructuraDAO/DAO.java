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

	
	//AGREGAR COMUNAAAAAAA
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
	
	//CONSULTAR COMUNAAAAAA
	public ArrayList<Comuna> consultarComuna()
	{
		String sql = "SELECT c.numeroComuna, c.nombreComuna , (SELECT COUNT(*) FROM barrio b WHERE b.comuna_idComuna = c.idComunna) AS cantidadBarrios FROM comuna c; ";
		//SANTY EN ESTE CASO TENGO QUE USAR ID YA QUE EN LA TABLA BARRIO ESTA EL ID DE COMUNA COMO FK, PODRIA UTILIZAR NUMEROCOMUNA PERO NO ESTA ESE CAMPO EN LA TABLA BARRIO
		Connection conexion = null;
		ArrayList<Comuna> solucion = new ArrayList<>();
		try {
			conexion = conectar();
			Statement stnt = conexion.createStatement();
		    ResultSet rs = stnt.executeQuery(sql);	
			while(rs.next())
			{
				int numero = rs.getInt("numeroComuna");
				String nombre = rs.getString("nombreComuna");
				int cantidad = rs.getInt("cantidadBarrios");
				Comuna c = new Comuna(nombre,numero,cantidad);
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
	//ELIMINAR COMUNAAAAA
	public void eliminarComuna(String numeroComuna)
	{
		String sql = "DELETE FROM comuna WHERE numeroComuna = ?;";
		Connection conexion = null;
		try {
			conexion = conectar();
			PreparedStatement stnt = conexion.prepareStatement(sql);
			stnt.setString(1, numeroComuna);
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
	//ACTUALIZAR COMUNAAAA
	public void  actualizarComuna(String numeroComuna, String nombreNuevo, String numeroNuevo )
	{
	
		String sql = "UPDATE comuna SET nombreComuna = ? , numeroComuna = ? WHERE numeroComuna = ?;";
		Connection conexion = null;
		try {
			conexion = conectar();
			PreparedStatement stnt = conexion.prepareStatement(sql);
			stnt.setString(1, nombreNuevo);
			stnt.setString(2, numeroNuevo);
			stnt.setString(3, numeroComuna);
			
			stnt.executeUpdate();
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
	
	
	
	
	
	
	
	
}
