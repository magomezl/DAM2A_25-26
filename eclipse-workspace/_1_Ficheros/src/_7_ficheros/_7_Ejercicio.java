package _7_ficheros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import utilidades.Utilidades;

public class _7_Ejercicio {
	private static final String SEPARADORVALORES= ",";
	private static final String DELIMITADORVALORES= "\"";
	private final static String FICHEROTRABAJO = "ejercicio07.csv";
	
	public static void main(String[] args) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(Utilidades.RUTA+FICHEROTRABAJO))){ 
			String line;
			while((line=br.readLine())!=null) {
				String[] fields = line.split(SEPARADORVALORES);
				mostrarValores(fields);
				fields = eliminaComillas(fields);
				mostrarValores(fields);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String[] eliminaComillas(String[] fields) {
		for(int i=0; i<fields.length; i++) {
			fields[i] = fields[i]
					.trim() //Elimina espacios en blanco
					.replaceAll("^"+DELIMITADORVALORES,"")   //Reemplazo comillas al principio
					.replaceAll(DELIMITADORVALORES+"$","")	 //Reemplazo comillas al final
					.replaceAll(DELIMITADORVALORES+DELIMITADORVALORES, DELIMITADORVALORES); 
		}
		return fields;
	}

	private static void mostrarValores(String[] fields) {
		for(int i=0; i<fields.length; i++) {
			System.out.printf("%-30s|", fields[i]);
		}
		System.out.println();
	}
}
