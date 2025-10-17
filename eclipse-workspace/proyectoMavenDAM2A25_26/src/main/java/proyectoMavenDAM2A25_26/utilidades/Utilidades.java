package proyectoMavenDAM2A25_26.utilidades;

public class Utilidades {
	
	private final static String RUTA = System.getProperty("user.dir") + System.getProperty("file.separator") +
			"src" + System.getProperty("file.separator") +
			"main" + System.getProperty("file.separator") +
			"resources" + System.getProperty("file.separator");

	private final static String RUTA_DOM = "ejercicios_DOM" + System.getProperty("file.separator");
	private final static String RUTA_SAX = "ejercicios_SAX" + System.getProperty("file.separator");
	private final static String RUTA_EXCEL = "ejercicios_Excel_POI" + System.getProperty("file.separator");
	
	public static String getRuta() {
		return RUTA;
	}

	public static String getRutaDom() {
		return RUTA_DOM;
	}

	public static String getRutaExcel() {
		return RUTA_EXCEL;
	}

	public static String getRutaSax() {
		return RUTA_SAX;
	}
	
}
