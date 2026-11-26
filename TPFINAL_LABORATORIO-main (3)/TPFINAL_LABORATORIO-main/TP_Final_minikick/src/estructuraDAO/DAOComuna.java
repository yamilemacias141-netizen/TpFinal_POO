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
		String sql = "INSERT INTO comuna (nombreSedeComuna, numeroComuna)VALUES(?,?);";
		try (Connection conexion = conect.conectar();
			 PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, c.getNombre());
			stmt.setInt(2, c.getNumeroComuna());
			stmt.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Comuna> consultar()
	{
		String sql = "SELECT c.numeroComuna, c.nombreSedeComuna , (SELECT COUNT(*) FROM barrio b WHERE b.idComuna = c.idComuna) AS cantidadBarrios FROM comuna c; ";
		//SANTY EN ESTE CASO TENGO QUE USAR ID YA QUE EN LA TABLA BARRIO ESTA EL ID DE COMUNA COMO FK, PODRIA UTILIZAR NUMEROCOMUNA PERO NO ESTA ESE CAMPO EN LA TABLA BARRIO
		ArrayList<Comuna> solucion = new ArrayList<>();
	
		try (Connection conexion = conect.conectar();
			 Statement stnt = conexion.createStatement();
		     ResultSet rs = stnt.executeQuery(sql)) {
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
		String sql = "DELETE FROM comuna WHERE numeroComuna = ?";
		try (Connection conexion = conect.conectar();
			 PreparedStatement stnt = conexion.prepareStatement(sql)) {
			stnt.setString(1, numeroComuna);
			stnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	public boolean  actualizar(String numeroComuna, Comuna c)
	{
		String sql = "UPDATE comuna SET nombreSedeComuna = ? , numeroComuna = ? WHERE numeroComuna = ?;";
		try (Connection conexion = conect.conectar();
			 PreparedStatement stnt = conexion.prepareStatement(sql)) {
			stnt.setString(1, c.getNombre());
			stnt.setString(2, c.getNumeroComuna() + "");
			stnt.setString(3, numeroComuna);
			stnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
	}
	public ArrayList<Barrio> consultarComunaDetalle(String numeroComuna)
	{
		String sql = "SELECT b.nombreBarrio, tb.idtipoBarrio  FROM comuna c INNER JOIN barrio b ON c.idComuna = b.idComuna INNER JOIN tipoBarrio tb ON b.idTipoBarrio = tb.idtipoBarrio where c.numeroComuna = ?;  ";
		ArrayList<Barrio> solucion = new ArrayList<>();
		try (Connection conexion = conect.conectar();
			 PreparedStatement stnt = conexion.prepareStatement(sql)) {
			stnt.setString(1, numeroComuna);
		    ResultSet rs = stnt.executeQuery();	
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
				case 3: b= new ComplejoHabitacional(nombre);
					
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
			 Statement stnt = conexion.createStatement();
			ResultSet rs = stnt.executeQuery(sql)) {
			while(rs.next())
			{
				String nombre = rs.getString("nombreSedeComuna");
				solucion.add(nombre);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return solucion;
		
	}
	

	public int consultarIdComunaPoNombre(String nombre){
		String sql = "SELECT idComuna FROM comuna where nombreSedeComuna = ?; ";
		int i = 0;
		try (Connection conexion = conect.conectar();
			 PreparedStatement stnt = conexion.prepareStatement(sql)) {
			stnt.setString(1, nombre);
			ResultSet rs = stnt.executeQuery();	
			while(rs.next())
			{
				i = rs.getInt("idComuna");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return i;
		
	}
	
	
	/*public ArrayList<Comuna> consultaPorAproximacion(String nombreAproximado)
	{
		String sql = "SELECT numeroComuna, nombreSedeComuna , (SELECT COUNT(*) FROM barrio b WHERE b.idComuna = c.idComuna) AS cantidadBarrios FROM comuna c  where nombreSedeComuna like ?; ";
		Connection conexion = null;
		ArrayList<Comuna> solucion = new ArrayList<>();
	
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
		    stmt.setString(1, nombreAproximado + "%");
		    ResultSet rs = stmt.executeQuery();	
		    
			while(rs.next())
			{
				int numero = rs.getInt("numeroComuna");
				String nombre = rs.getString("nombreSedeComuna");
				int cantidad = rs.getInt("cantidadBarrios");
				Comuna c = new Comuna(nombre,numero,cantidad);
				solucion.add(c);
			}
			
		    rs.close();
		    conect.cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			conect.cerrarConexion();
		} 
		
		return solucion;
	}
	*/
}