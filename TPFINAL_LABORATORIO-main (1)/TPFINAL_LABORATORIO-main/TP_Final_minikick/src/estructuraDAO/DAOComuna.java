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
	
	
	public boolean agregar(Comuna c ) {
		boolean sePudoCargar = true;
		String sql = "INSERT INTO comuna (nombreSedeComuna, numeroComuna)VALUES(?,?);";
		 try (Connection conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);) {
			stmt.setString(1, c.getNombre());
			stmt.setInt(2, c.getNumeroComuna());
			stmt.executeUpdate();
			
		}catch(SQLException e){
			sePudoCargar = false;
			e.printStackTrace();
		}
		return sePudoCargar;
	}
	
	public ArrayList<Comuna> consultar()
	{
		String sql = "SELECT c.numeroComuna, c.nombreSedeComuna , (SELECT COUNT(*) FROM barrio b WHERE b.idComuna = c.idComuna) AS cantidadBarrios FROM comuna c; ";
		//SANTY EN ESTE CASO TENGO QUE USAR ID YA QUE EN LA TABLA BARRIO ESTA EL ID DE COMUNA COMO FK, PODRIA UTILIZAR NUMEROCOMUNA PERO NO ESTA ESE CAMPO EN LA TABLA BARRIO
		ArrayList<Comuna> solucion = new ArrayList<>();
		try (Connection conexion = conect.conectar();
				PreparedStatement stmt = conexion.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
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
		} 
		return solucion;
	}
	
	public boolean eliminar(String numeroComuna)
	{
		boolean sePudoEliminar = true;
		String sql = "DELETE FROM comuna WHERE numeroComuna = ?";
		try (Connection conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);) {
			stmt.setString(1, numeroComuna);
			stmt.executeUpdate();
		} catch (SQLException e) {
			sePudoEliminar = false;
			e.printStackTrace();
		} 
		return sePudoEliminar;
	}
	
	public boolean  actualizar(String numeroComuna, Comuna c)
	{
		boolean sePudoCargar = true;
		String sql = "UPDATE comuna SET nombreSedeComuna = ? , numeroComuna = ? WHERE numeroComuna = ?;";
		try (Connection conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);) {
			stmt.setString(1, c.getNombre());
			stmt.setString(2, c.getNumeroComuna() + "");
			stmt.setString(3, numeroComuna);
			stmt.executeUpdate();
		} catch (SQLException e) {
			sePudoCargar = false;
			e.printStackTrace();
		} 
		return sePudoCargar;
	}
	public ArrayList<Barrio> consultarComunaDetalle(String numeroComuna)
	{
		String sql = "SELECT b.nombreBarrio, tb.idtipoBarrio  FROM comuna c INNER JOIN barrio b ON c.idComuna = b.idComuna INNER JOIN tipoBarrio tb ON b.idTipoBarrio = tb.idtipoBarrio where c.numeroComuna = ?;  ";
		ArrayList<Barrio> solucion = new ArrayList<>();
		try (Connection conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);) {
			stmt.setString(1, numeroComuna);
		    ResultSet rs = stmt.executeQuery();	
			while(rs.next())
			{
				int aux  = rs.getInt("idTipoBarrio");
				String nombre = rs.getString("nombreBarrio");
				Barrio b = null;
				switch(aux) {
				case 1: b = new TrazadoFormal(nombre);
					break;
				case 2: b = new UrbanizacionPlanificada(nombre);
					break;
				case 3: b = new ComplejoHabitacional(nombre);
					break;
				}
				solucion.add(b);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return solucion;
	}
	
	public ArrayList<String> consultarNombres(){
		String sql = "SELECT nombreSedeComuna FROM comuna ; ";
		ArrayList<String> solucion = new ArrayList<>();
		try (Connection conexion = conect.conectar();
				Statement stmt = conexion.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {	
			while(rs.next())
			{
				String nombre = rs.getString("nombreSedeComuna");
				solucion.add(nombre);
			}
			
		    rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return solucion;
		
	}
}
