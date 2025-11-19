package ut2_ejercicio17.modelo.db;

public class ParametrosConexion {
	/**
	 * Parámetros de conexión con MySQLWorkBench
	
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String cadenaConection = "jdbc:mysql://10.196.55.103:3306/empresa";
	public static final String usuario = "root";
	public static final String contrasenia = "toor";
	*/
	/**
	 * Parámetros de conexión con SQLite
	 */
	public static final String driver = "org.sqlite.JDBC";
	public static final String cadenaConection = "jdbc:sqlite:src\\main\\resources\\dbSQLite\\empresa.db";
	public static final String usuario = "";
	public static final String contrasenia = "";
	
}
