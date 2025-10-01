package proyectoMavenDAM2A25_26.ejericio_07;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import proyectoMavenDAM2A25_26.utilidades.Utilidades;

public class Ejercicio_07 {
	private static final char SEPARADORVALORES= ',';
	private static final char DELIMITADORVALORES= '\"';
	private final static String FICHEROTRABAJO = "ejercicio_07" + System.getProperty("file.separator") + "ejercicio07.csv";
	
	public static void main(String[] args) {
		try (CSVReader reader = new CSVReader(new FileReader(Utilidades.getRuta()+FICHEROTRABAJO),
				SEPARADORVALORES, DELIMITADORVALORES)){
			String[] nextLine = null;
			while((nextLine=reader.readNext())!=null) {
				mostrarValores(nextLine);
			}
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no existe");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
		}
		
	}

	private static void mostrarValores(String[] fields) {
		for(int i=0; i<fields.length; i++) {
			System.out.printf("%-30s|", fields[i]);
		}
		System.out.println();
	}
}
