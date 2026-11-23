package estructuraDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;

public class DAOBarrio{
	private Conexion conect;
	
	public DAOBarrio(Conexion c) {
		conect = c;
	}
	
	public boolean agregar(Barrio b, int numeroComuna)
	{
		Connection conexion = null;
		String sql = "INSERT INTO barrio (nombreBarrio,superficieEnHectarias,cantidadEdificiosBarrio,porcentajeAsfaltoBarrio,numeroLeyCabaBarrio, conexionRedAguaBarrio,cantidadMonoblocksBarrio,consorcioAdministracionBarrio, idComuna, idTipoBarrio)VALUES(?,?,?,?,?,?,?,?,(SELECT idComuna FROM comuna WHERE numeroComuna = ?),?);";
		boolean aux = true;
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			cargarBarrio(stmt,b);
			
			stmt.executeUpdate();
		
			
			stmt.close();
			conexion.close();
		}catch(SQLException e){
			aux = false;
			e.printStackTrace();
		
		}
		return aux;
	}

	public void actualizar(Barrio b, String nombreBarrio)
	{
	    String sql = "UPDATE barrio SET nombreBarrio varchar = ? , superficieBarrio=? , cantidadEdificiosBarrio=? , porcentajeAsfaltoBarrio= ?,numeroLeyCabaBarrio = ?, conexionRedAguaBarrio = ?,cantidadMonoblocksBarrio=?,consorcioAdministracionBarrio=? WHERE nombreBarrio = ?";
	    Connection conexion = null;
	    try {
	        conexion = conect.conectar();
	        PreparedStatement stmt = conexion.prepareStatement(sql);

	        cargarBarrio(stmt,b);
	        stmt.executeUpdate();
            stmt.close();
            conexion.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    
	    }
	}
	
	public ArrayList<Barrio> consultarBarrios()
	{
	    String sql = "SELECT b.* , tb.nombreTipoBarrio FROM barrio b INNER JOIN tipobarrio tb ON b.tipobarrio_idtipobarrio = tb.idtipobarrio;";
	    Connection conexion = null;
	    ArrayList<Barrio> solucion = new ArrayList<>();
	    try {
	        conexion = conect.conectar();
	        Statement stnt = conexion.createStatement();
	        ResultSet rs = stnt.executeQuery(sql);	
	        while(rs.next())
	        {
	            String nombre = rs.getString("nombreBarrio");
	            float superficie = rs.getFloat("superficieBarrio");
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

	            solucion.add(b);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(conexion != null) conexion.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return solucion;
	}
	public void eliminarBarrio(String nombreBarrio)
	{
		String sql = "DELETE FROM barrio WHERE nombreBarrio = ?";
		Connection conexion = null;
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, nombreBarrio);
			stmt.executeUpdate();
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
	
	
	public String[] consultarTipoBarrios() {
		String sql = "SELECT nombreTipoBarrio from tipobarrio;";
	    Connection conexion = null;
	    String[] tipos = new String[3];
	    int i = 0;
	    
	    try {
	        conexion = conect.conectar();
	        Statement stnt = conexion.createStatement();
	        ResultSet rs = stnt.executeQuery(sql);	
	        while(rs.next())
	        {
	        	String tipo = rs.getString("nombreTipoBarrio");
	        	tipos[i] = tipo;
	        	i++;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(conexion != null) conexion.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return tipos;
	}
	
	private void cargarBarrio(PreparedStatement stmt, Barrio b) throws SQLException{
		stmt.setString(1, b.getNombre());
	    stmt.setFloat(2, b.getSuperficieEnHectarias());

	    if (b instanceof TrazadoFormal tf) {
	        stmt.setInt(3, tf.getCantidadEdificios());
	        stmt.setFloat(4, tf.getPorcentajeAsfalto());
	        stmt.setNull(5, Types.VARCHAR);
	        stmt.setNull(6, Types.BOOLEAN);
	        stmt.setNull(7, Types.INTEGER);
	        stmt.setNull(8, Types.BOOLEAN);
	    } else if (b instanceof UrbanizacionPlanificada up) {
	        stmt.setNull(3, Types.INTEGER);
	        stmt.setNull(4, Types.FLOAT);
	        stmt.setString(5, up.getNumeroLey());
	        stmt.setBoolean(6, up.getTieneConexionAgua());
	        stmt.setNull(7, Types.INTEGER);
	        stmt.setNull(8, Types.BOOLEAN);
	    } else if (b instanceof ComplejoHabitacional ch) {
	        stmt.setNull(3, Types.INTEGER);
	        stmt.setNull(4, Types.FLOAT);
	        stmt.setNull(5, Types.VARCHAR);
	        stmt.setNull(6, Types.BOOLEAN);
	        stmt.setInt(7, ch.getCantidadMonoblock());
	        stmt.setBoolean(8, ch.getTieneConsorcioAdministracion());
	    }
	  
	}
}