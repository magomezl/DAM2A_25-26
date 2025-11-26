package modelo.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ParametrosConexion {
	private static String driver;
	private static String prefijo;
	private static String ip;
	private static String puerto;
	private static String esquema;
	private static String usuario;
	private static String contrasenia;
	
	public static void establecerParametros(String rutaFichero) {
		Properties propiedades = new Properties();
		try (InputStream fis = ParametrosConexion.class.getResourceAsStream(rutaFichero) ) {
			propiedades.load(fis);
			driver = propiedades.getProperty("driver");
			prefijo = propiedades.getProperty("prefijo");
			ip = propiedades.getProperty("ip");
			puerto = propiedades.getProperty("puerto");
			esquema = propiedades.getProperty("esquema");
			usuario = propiedades.getProperty("usuario");
			contrasenia = propiedades.getProperty("contrasenia");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getDriver() {
		return driver;
	}

	public static String getPrefijo() {
		return prefijo;
	}

	public static String getIp() {
		return ip;
	}

	public static String getPuerto() {
		return puerto;
	}

	public static String getEsquema() {
		return esquema;
	}

	public static String getUsuario() {
		return usuario;
	}

	public static String getContrasenia() {
		return contrasenia;
	}
	
	

}
