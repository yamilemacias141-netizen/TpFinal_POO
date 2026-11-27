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

public class Conexion {


	private static String user = "root";
	private static String psw = "admin"; //contraseña en laboratorio: admin
	private static String path = "jdbc:mysql://localhost:3306/tp_final";
	
	private Connection conexion = null;
	
	protected Connection conectar() {
		if(conexion == null || estaCerrado()) {
			try {
				conexion = DriverManager.getConnection(path, user, psw);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conexion;
	}	
	
	protected boolean estaCerrado() {
		boolean aux = true;
		if(conexion != null) {
			try {
				aux = conexion.isClosed();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return aux;
	}
	
	protected void cerrarConexion() {
		if(estaCerrado() == false) {
			try {
				conexion.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
}