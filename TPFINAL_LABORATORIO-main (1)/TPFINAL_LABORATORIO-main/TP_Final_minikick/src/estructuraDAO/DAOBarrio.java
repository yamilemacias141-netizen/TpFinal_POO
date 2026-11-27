package estructuraDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;

public class DAOBarrio{
	private Conexion conect;
	
	public DAOBarrio(Conexion c) {
		conect = c;
	}
	
	public boolean agregar(Barrio b, String nombreComuna)
	{
		String sql = "INSERT INTO barrio (nombreBarrio,superficieEnHectarias,cantidadEdificiosBarrio,porcentajeAsfaltoBarrio,numeroLeyCabaBarrio, conexionRedAguaBarrio,cantidadMonoblocksBarrio,consorcioAdministracionBarrio, idTipoBarrio,idComuna)VALUES(?,?,?,?,?,?,?,?,(Select idTipoBarrio from tipoBarrio where nombreTipoBarrio = ?),(SELECT idComuna FROM comuna WHERE nombreSedeComuna = ?));";
		boolean aux = true;
		try (Connection conexion = conect.conectar();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {
				meterLosBarriosPorTipos(stmt,b);
				stmt.setString(10, nombreComuna);
				stmt.executeUpdate();
			}catch(SQLException e){
				aux = false;
				e.printStackTrace();
			}
		return aux;
	}

	public void actualizarLaComunaAlBorrar(String nombre, int numeroComuna) {
		String sql = "UPDATE barrio set idComuna = (select idComuna from comuna where nombreSedeComuna = ?) where idComuna = (SELECT idComuna FROM comuna WHERE numeroComuna = ?);";
	    try (Connection conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql)) {
	        stmt.setString(1, nombre);
	        stmt.setInt(2, numeroComuna);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean actualizar(Barrio b, String nombreBarrio, String nombreComuna)
	{
		boolean aux = true;
	    String sql = "UPDATE barrio SET nombreBarrio = ? , superficieEnHectarias=? , cantidadEdificiosBarrio=? , porcentajeAsfaltoBarrio= ?,numeroLeyCabaBarrio = ?, conexionRedAguaBarrio = ?,cantidadMonoblocksBarrio=?,consorcioAdministracionBarrio=?, idTipoBarrio = (Select idTipoBarrio from tipoBarrio where nombreTipoBarrio = ?), idComuna = (Select idComuna from comuna where nombreSedeComuna = ?) WHERE nombreBarrio = ?";
	    try (Connection conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql)) {
	        meterLosBarriosPorTipos(stmt,b);
	        stmt.setString(10, nombreComuna);
	        stmt.setString(11, nombreBarrio);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	    	aux = false;
	        e.printStackTrace();
	    }
	    
	    return aux;
	}

	public void eliminar(String nombreBarrio)
	{
		String sql = "DELETE FROM barrio WHERE nombreBarrio = ?";
		try (Connection conexion = conect.conectar();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, nombreBarrio);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Barrio> consultar()
	{
	    String sql = "SELECT b.* , tb.nombreTipoBarrio FROM barrio b INNER JOIN tipobarrio tb ON b.idtipobarrio = tb.idtipobarrio;";
	    ArrayList<Barrio> solucion = new ArrayList<>();
	    try (Connection conexion = conect.conectar();
	    	Statement stmt = conexion.createStatement();
	    	ResultSet rs = stmt.executeQuery(sql)) {
	        while(rs.next())
	        {
	            solucion.add(sacarLosBarriosPorTipos(rs));
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return solucion;
	}
	
	public ArrayList<Barrio> consultarPorNombreComuna(String nombreComuna)
	{
	    String sql = "SELECT b.* , tb.nombreTipoBarrio FROM barrio b INNER JOIN tipobarrio tb ON b.idtipobarrio = tb.idtipobarrio where b.idComuna = (select idComuna from comuna where nombreSedeComuna = ?);";
	   
	    ArrayList<Barrio> solucion = new ArrayList<>();
	    try (Connection conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql)) {
	        stmt.setString(1, nombreComuna);
	        ResultSet rs = stmt.executeQuery();	
	        while(rs.next())
	        {
	            solucion.add(sacarLosBarriosPorTipos(rs));
	        }

	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return solucion;
	}
	
	public String[] consultarTipoBarrios() {
		String sql = "SELECT nombreTipoBarrio from tipobarrio;";
	    String[] tipos = new String[3];
	    int i = 0;
	    
	    try (Connection conexion = conect.conectar();
	    	Statement stmt = conexion.createStatement();
	    	ResultSet rs = stmt.executeQuery(sql);	) {
	        while(rs.next())
	        {
	        	String tipo = rs.getString("nombreTipoBarrio");
	        	tipos[i] = tipo;
	        	i++;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();

	    } 
	    return tipos;
	}
	
	public Barrio consultarPorNombre(String nombreBarrio)
	{
	    String sql = "SELECT b.* , tb.nombreTipoBarrio FROM barrio b INNER JOIN tipobarrio tb ON b.idtipobarrio = tb.idtipobarrio where nombreBarrio = ?;";
	    Barrio b = null;
	    try (Connection conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql)) {
	        
	        stmt.setString(1, nombreBarrio);
	        ResultSet rs = stmt.executeQuery();	
	        
	        while(rs.next()) {
	        	b = sacarLosBarriosPorTipos(rs);
	        }
	        
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    
	    return b;
	}
	
	/*public ArrayList<Barrio> consultarPorAproximacion(String nombreAproximado)
	{
		String sql = "SELECT b.* , tb.nombreTipoBarrio FROM barrio b INNER JOIN tipobarrio tb ON b.idtipobarrio = tb.idtipobarrio where nombreBarrio like ?;";
		Connection conexion = null;
		ArrayList<Barrio> solucion = new ArrayList<>();
	
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
		    stmt.setString(1, nombreAproximado + "%");
		    ResultSet rs = stmt.executeQuery();	
		    		    
		    while(rs.next()) {
		    	solucion.add(sacarLosBarriosPorTipos(rs));
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
	public ArrayList<String> consultarNombresBarrio(){
		String sql = "SELECT nombreBarrio FROM Barrio; ";
		ArrayList<String> solucion = new ArrayList<>();
		 try (Connection conexion = conect.conectar();
			Statement stmt = conexion.createStatement();
			  ResultSet rs = stmt.executeQuery(sql);	) {	
			while(rs.next())
			{
				String nombre = rs.getString("nombreBarrio");
				solucion.add(nombre);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return solucion;
		
	}
	
	private void meterLosBarriosPorTipos(PreparedStatement stmt, Barrio b) throws SQLException{
		stmt.setString(1, b.getNombre());
	    stmt.setFloat(2, b.getSuperficieEnHectarias());

	    if (b instanceof TrazadoFormal tf) {
	        stmt.setInt(3, tf.getCantidadEdificios());
	        stmt.setFloat(4, tf.getPorcentajeAsfalto());
	        stmt.setNull(5, Types.VARCHAR);
	        stmt.setNull(6, Types.BOOLEAN);
	        stmt.setNull(7, Types.INTEGER);
	        stmt.setNull(8, Types.BOOLEAN);
	        stmt.setString(9, "Trazado formal");
	    } else if (b instanceof UrbanizacionPlanificada up) {
	        stmt.setNull(3, Types.INTEGER);
	        stmt.setNull(4, Types.FLOAT);
	        stmt.setString(5, up.getNumeroLey());
	        stmt.setBoolean(6, up.getTieneConexionAgua());
	        stmt.setNull(7, Types.INTEGER);
	        stmt.setNull(8, Types.BOOLEAN);
	        stmt.setString(9, "Urbanizacion planificada");
	    } else if (b instanceof ComplejoHabitacional ch) {
	        stmt.setNull(3, Types.INTEGER);
	        stmt.setNull(4, Types.FLOAT);
	        stmt.setNull(5, Types.VARCHAR);
	        stmt.setNull(6, Types.BOOLEAN);
	        stmt.setInt(7, ch.getCantidadMonoblock());
	        stmt.setBoolean(8, ch.getTieneConsorcioAdministracion());
	        stmt.setString(9, "Complejo habitacional");
	    }
	  
	}
	
	private Barrio sacarLosBarriosPorTipos(ResultSet rs) throws SQLException {
            String nombre = rs.getString("nombreBarrio");
            float superficie = rs.getFloat("superficieEnHectarias");
            int cantidadEdificios = rs.getInt("cantidadEdificiosBarrio");
            float porcentajeAsfalto = rs.getFloat("porcentajeAsfaltoBarrio");
            String numeroLeyCaba = rs.getString("numeroLeyCabaBarrio");
            boolean conexionRedAgua = rs.getBoolean("conexionRedAguaBarrio");
            int cantidadMonoblocks = rs.getInt("cantidadMonoblocksBarrio");
            boolean consorcioAdministracion = rs.getBoolean("consorcioAdministracionBarrio");

            String nombreTipo = rs.getString("nombreTipoBarrio");

            Barrio b = null;

            if(nombreTipo.equalsIgnoreCase("Trazado formal"))
            {
                b = new TrazadoFormal(nombre, superficie, cantidadEdificios, porcentajeAsfalto);
            }
            else if(nombreTipo.equalsIgnoreCase("Urbanizacion planificada"))
            {
                b = new UrbanizacionPlanificada(nombre, superficie, numeroLeyCaba, conexionRedAgua);
            }
            else if(nombreTipo.equalsIgnoreCase("Complejo Habitacional"))
            {
                b = new ComplejoHabitacional(nombre, superficie, cantidadMonoblocks, consorcioAdministracion);
            }
            
            return b;
	
	}
	
}
