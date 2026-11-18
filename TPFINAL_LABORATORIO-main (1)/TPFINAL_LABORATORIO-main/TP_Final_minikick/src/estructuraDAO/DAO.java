package estructuraDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
	
	
	public void agregarBarrio(Barrio b)
	{
		Connection conexion = null;
		String sql = "INSERT INTO barrio (nombreBarrio,superficieBarrio,cantidadEdificiosBarrio,porcentjaAsfaltoBarrio,numeroLeyCabaBarrio, conexionRedAguaBarrio,cantidadMonoblocksBarrio,consorcioAdministracionBarrio )VALUES(?,?,?,?,?,?,?,?);";
		boolean aux = false;
		try {
			conexion = conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
	
			if (b instanceof TrazadoFormal)
			{
				TrazadoFormal tf = (TrazadoFormal)b;
				stmt.setString(1, tf.getNombre());
				stmt.setFloat(2, tf.getSuperficie());
				stmt.setInt(3, tf.getCantudadEdificios());
				stmt.setFloat(4, tf.getPorcentajeAsfalto());
				stmt.setNull(5, Types.VARCHAR);
				stmt.setNull(6, Types.BOOLEAN);
				stmt.setNull(7, Types.INTEGER);
				stmt.setNull(8, Types.BOOLEAN);
			}
			else if(b instanceof UrbanizacionPlanificada)
			{
				UrbanizacionPlanificada up = (UrbanizacionPlanificada) b;
				
				stmt.setString(1, up.getNombre());
				stmt.setFloat(2, up.getSuperficie());
				stmt.setNull(3, Types.INTEGER);
				stmt.setNull(4, Types.VARCHAR);
				stmt.setString(5, up.getNumeroLey());
				stmt.setBoolean(6, up.getTieneConexionAgua());
				stmt.setNull(7, Types.INTEGER);
				stmt.setNull(8, Types.BOOLEAN);
			
				
			}
			else if(b instanceof ComplejoHabitacional)
			{
				ComplejoHabitacional ch = (ComplejoHabitacional) b;
				stmt.setString(1, ch.getNombre());
				stmt.setFloat(2, ch.getSuperficie());
				stmt.setNull(3, Types.INTEGER);
				stmt.setNull(4, Types.VARCHAR);
				stmt.setNull(5, Types.VARCHAR);
				stmt.setInt(7, ch.getCantidadMonoblock());
				stmt.setBoolean(8, ch.getTieneConsorcioAdministracion());
				
			}
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

	public void actualizar(Barrio b, String nombreBarrio)
	{
	    String sql = "UPDATE barrio SET nombreBarrio varchar = ? , superficieBarrio=? , cantidadEdificiosBarrio=? , porcentajeAsfaltoBarrio= ?,numeroLeyCabaBarrio = ?, conexionRedAguaBarrio = ?,cantidadMonoblocksBarrio=?,consorcioAdministracionBarrio=? WHERE nombreBarrio = ?";
	    Connection conexion = null;
	    try {
	        conexion = conectar();
	        PreparedStatement stmt = conexion.prepareStatement(sql);

	        String nombreTipo = "";

	        if (b instanceof TrazadoFormal) {
	            TrazadoFormal tf = (TrazadoFormal) b;
	            stmt.setString(1, tf.getNombre());
	            stmt.setFloat(2, tf.getSuperficie());
	            stmt.setInt(3, tf.getCantudadEdificios());
	            stmt.setFloat(4, tf.getPorcentajeAsfalto());
	            stmt.setNull(5, Types.VARCHAR);
	            stmt.setNull(6, Types.BOOLEAN);
	            stmt.setNull(7, Types.INTEGER);
	            stmt.setNull(8, Types.BOOLEAN);
	           

	        } else if (b instanceof UrbanizacionPlanificada) {
	            UrbanizacionPlanificada up = (UrbanizacionPlanificada) b;
	            stmt.setString(1, up.getNombre());
	            stmt.setFloat(2, up.getSuperficie());
	            stmt.setNull(2, Types.INTEGER);
	            stmt.setNull(3, Types.FLOAT);
	            stmt.setString(4, up.getNumeroLey());
	            stmt.setBoolean(5, up.getTieneConexionAgua());
	            stmt.setNull(6, Types.INTEGER);
	            stmt.setNull(7, Types.BOOLEAN);
	            nombreTipo = "Urbanizacion planificada";

	        } else if (b instanceof ComplejoHabitacional) {
	            ComplejoHabitacional ch = (ComplejoHabitacional) b;
	            stmt.setNull(2, Types.INTEGER);
	            stmt.setNull(3, Types.FLOAT);
	            stmt.setNull(4, Types.VARCHAR);
	            stmt.setNull(5, Types.BOOLEAN);
	            stmt.setInt(6, ch.getCantidadMonoblock());
	            stmt.setBoolean(7, ch.getTieneConsorcioAdministracion());
	            nombreTipo = "Complejo Habitacional";
	        }

	

	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conexion != null) conexion.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public ArrayList<Barrio> consultarBarrios()
	{
	    String sql = "SELECT b.* , tb.nombreTipoBarrio FROM barrio b INNER JOIN tipobarrio tb ON b.tipobarrio_idtipobarrio = tb.idtipobarrio;";
	    Connection conexion = null;
	    ArrayList<Barrio> solucion = new ArrayList<>();
	    try {
	        conexion = conectar();
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
	public static Comuna consultarComunaPorNombre(String nombreComuna)
	{
		String sql = "SELECT c.numeroComuna, c.nombreSedeComuna , (SELECT COUNT(*) FROM barrio b WHERE b.idComuna = c.idComuna) AS cantidadBarrios FROM comuna c WHERE nombreSedeComuna = ?; ";
		//SANTY EN ESTE CASO TENGO QUE USAR ID YA QUE EN LA TABLA BARRIO ESTA EL ID DE COMUNA COMO FK, PODRIA UTILIZAR NUMEROCOMUNA PERO NO ESTA ESE CAMPO EN LA TABLA BARRIO
		Connection conexion = null;
		Comuna c = null;
		
		try {
			conexion = conectar();
			PreparedStatement stnt = conexion.prepareStatement(sql);
		    stnt.setString(1, nombreComuna);
		    ResultSet rs = stnt.executeQuery();	
		    if (rs.next()) {
		    	int numero = rs.getInt("numeroComuna");
				String nombre = rs.getString("nombreSedeComuna");
				int cantidad = rs.getInt("cantidadBarrios");
				c = new Comuna(nombre,numero,cantidad);
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
		return c;
	}
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