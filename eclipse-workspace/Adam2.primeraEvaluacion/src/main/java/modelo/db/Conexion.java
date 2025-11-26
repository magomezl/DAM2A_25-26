package modelo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static Conexion instance;
	private Connection con;
	
	private Conexion() {
		try {
			ParametrosConexion.establecerParametros("/resources/config.properties");
			
			Class.forName(ParametrosConexion.getDriver());
			con = DriverManager.getConnection(ParametrosConexion.getPrefijo()+ParametrosConexion.getIp()+ParametrosConexion.getEsquema(), 
					ParametrosConexion.getUsuario(), ParametrosConexion.getContrasenia());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Conexion getInstance() {
		if (instance == null) {
			instance = new Conexion();
		}
		return instance;
	}

	public Connection getCon() {
		return con;
	}
	
	public void cerrarConexion() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
