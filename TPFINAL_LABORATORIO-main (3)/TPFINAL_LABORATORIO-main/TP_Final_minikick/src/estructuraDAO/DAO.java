package estructuraDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Persona;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;

public abstract class DAO {


	private static String user = "root";
	private static String psw = "12345";
	private static String path = "jdbc:mysql://localhost:3306/tp_final";


	private static Connection conectar() {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(path, user, psw);

			System.out.println("se conecto");
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return conexion;
	}
	
	
	
	
	public static void agregarBarrioTrazadoFormal (TrazadoFormal t ) {
		Connection conexion = null;
		String sql = "INSERT INTO barrio (nombreBarrio,superficieBarrio,cantidadEdificiosBarrio,porcentjaAsfaltoBarrio,numeroLeyCabaBarrio, conexionRedAguaBarrio,cantidadMonoblocksBarrio,consorcioAdministracionBarrio )VALUES(?,?,?,?,?,?,?,?);";
		try {
			conexion = conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, t.getNombre());
			stmt.setFloat(2, t.getSuperficie());
			stmt.setInt(3, t.getCantidadEdificios());
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
	
	public static void agregarBarrioUrbanizacionPlanificada (UrbanizacionPlanificada u ) {
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
	
	public static void agregarBarrioComplejoHabitacional(ComplejoHabitacional c ) {
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
	public static void agregarComuna(Comuna c ) {
		Connection conexion = null;
		
		String sql = "INSERT INTO comuna (nombreSedeComuna, numeroComuna)VALUES(?,?);";
		try {
			conexion = conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, c.getNombre());
			stmt.setInt(2, c.getNumeroComuna());
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
	public static ArrayList<Comuna> consultarComuna()
	{
		String sql = "SELECT c.numeroComuna, c.nombreSedeComuna , (SELECT COUNT(*) FROM barrio b WHERE b.idComuna = c.idComuna) AS cantidadBarrios FROM comuna c; ";
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
				String nombre = rs.getString("nombreSedeComuna");
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
	
	// consultar comuna especifica 
	
	//ELIMINAR COMUNAAAAA
	public static void eliminarComuna(String numeroComuna)
	{
		String sql = "DELETE FROM comuna WHERE numeroComuna = ?";
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
	public static void  actualizarComuna(String numeroComuna, Comuna c)
		{
		
			String sql = "UPDATE comuna SET nombreSedeComuna = ? , numeroComuna = ? WHERE numeroComuna = ?;";
			Connection conexion = null;
			try {
				conexion = conectar();
				PreparedStatement stnt = conexion.prepareStatement(sql);
				stnt.setString(1, c.getNombre());
				stnt.setString(2, c.getNumeroComuna() + "");
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
	
	// mi invencion
	
	public static ArrayList<Integer> consultaMismoNumeroComuna() {
		String sql = "SELECT numeroComuna FROM comuna c; ";
		Connection conexion = null;
		ArrayList<Integer> numerosComuna = new ArrayList<>();
		try {
			conexion = conectar();
			PreparedStatement stnt = conexion.prepareStatement(sql);
		    ResultSet rs = stnt.executeQuery();	
			while(rs.next()) {
				numerosComuna.add(rs.getInt("numeroComuna"));
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
		return numerosComuna;
	}
	
	public static ArrayList<Barrio> consultarComunaDetalle(String numeroComuna)
	{
		String sql = "SELECT b.nombreBarrio, tb.idtipoBarrio  FROM comuna c INNER JOIN barrio b ON c.idComuna = b.idComuna INNER JOIN tipoBarrio tb ON b.idTipoBarrio = tb.idtipoBarrio where c.idComuna = ?;  ";
		
		Connection conexion = null;
		ArrayList<Barrio> solucion = new ArrayList<>();
		try {
			conexion = conectar();
			PreparedStatement stnt = conexion.prepareStatement(sql);
			stnt.setString(1, numeroComuna);
		    ResultSet rs = stnt.executeQuery();	
			while(rs.next())
			{
				int aux  = rs.getInt("idTipoBarrio");
				String nombre = rs.getString("nombreBarrio");
				switch(aux) {
				case 1: TrazadoFormal tz = new TrazadoFormal(nombre);
						solucion.add(tz);	
					break;
				case 2: UrbanizacionPlanificada up = new UrbanizacionPlanificada(nombre);
						solucion.add(up);
					break;
				case 3: ComplejoHabitacional ch = new ComplejoHabitacional(nombre);
						solucion.add(ch);
					break;
				}
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
}