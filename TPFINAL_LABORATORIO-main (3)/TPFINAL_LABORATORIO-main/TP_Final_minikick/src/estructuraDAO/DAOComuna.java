package estructuraDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;

public class DAOComuna {
	private Conexion conect;
	public DAOComuna(Conexion conectar) {
		this.conect = conectar;
	}
	public boolean agregarComuna(Comuna c ) {
		boolean sePudoCargar = true;
		Connection conexion = null;
		
		String sql = "INSERT INTO comuna (nombreSedeComuna, numeroComuna)VALUES(?,?);";
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, c.getNombre());
			stmt.setInt(2, c.getNumeroComuna());
			stmt.executeUpdate();
		}catch(SQLException e){
			sePudoCargar = false;
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sePudoCargar;
	}
	
	public ArrayList<Comuna> consultarComuna()
	{
		String sql = "SELECT c.numeroComuna, c.nombreSedeComuna , (SELECT COUNT(*) FROM barrio b WHERE b.idComuna = c.idComuna) AS cantidadBarrios FROM comuna c; ";
		//SANTY EN ESTE CASO TENGO QUE USAR ID YA QUE EN LA TABLA BARRIO ESTA EL ID DE COMUNA COMO FK, PODRIA UTILIZAR NUMEROCOMUNA PERO NO ESTA ESE CAMPO EN LA TABLA BARRIO
		Connection conexion = null;
		ArrayList<Comuna> solucion = new ArrayList<>();
		try {
			conexion = conect.conectar();
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
			
		    rs.close();
		    
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
	
	public void eliminarComuna(String numeroComuna)
	{
		String sql = "DELETE FROM comuna WHERE numeroComuna = ?";
		Connection conexion = null;
		try {
			conexion = conect.conectar();
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
	
	public boolean  actualizarComuna(String numeroComuna, Comuna c)
	{
		boolean sePudoCargar = true;
		String sql = "UPDATE comuna SET nombreSedeComuna = ? , numeroComuna = ? WHERE numeroComuna = ?;";
		Connection conexion = null;
		try {
			conexion = conect.conectar();
			PreparedStatement stnt = conexion.prepareStatement(sql);
			stnt.setString(1, c.getNombre());
			stnt.setString(2, c.getNumeroComuna() + "");
			stnt.setString(3, numeroComuna);
			
			stnt.executeUpdate();
		} catch (SQLException e) {
			sePudoCargar = false;
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return sePudoCargar;
	}
	public ArrayList<Barrio> consultarComunaDetalle(String numeroComuna)
	{
		String sql = "SELECT b.nombreBarrio, tb.idtipoBarrio  FROM comuna c INNER JOIN barrio b ON c.idComuna = b.idComuna INNER JOIN tipoBarrio tb ON b.idTipoBarrio = tb.idtipoBarrio where c.numeroComuna = ?;  ";
		Connection conexion = null;
		ArrayList<Barrio> solucion = new ArrayList<>();
		try {
			conexion = conect.conectar();
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
	
	public ArrayList<String> consultarNombresComuna(){
		String sql = "SELECT nombreSedeComuna FROM comuna ; ";
		Connection conexion = null;
		ArrayList<String> solucion = new ArrayList<>();
		try {
			conexion = conect.conectar();
			Statement stnt = conexion.createStatement();
		    ResultSet rs = stnt.executeQuery(sql);	
			while(rs.next())
			{
				String nombre = rs.getString("nombreSedeComuna");
				solucion.add(nombre);
			}
			
		    rs.close();
		    
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
	

	public int consultarNumeroComunaPorNombre(String nombre){
		String sql = "SELECT idComuna FROM comuna where nombreSedeComuna = ?; ";
		Connection conexion = null;
		int i = 0;
		try {
			conexion = conect.conectar();
			PreparedStatement stnt = conexion.prepareStatement(sql);
			stnt.setString(1, nombre);
			ResultSet rs = stnt.executeQuery();	
			while(rs.next())
			{
				i = rs.getInt("idComuna");
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
		return i;
		
	}
}
