package _5_ficheros;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import utilidades.Utilidades;

public class _5_Ejercicio {
	private final static String DOCTRABAJO = "serializaPersona";
	
	public static void main(String[] args) {
		escribirObjeto();
		leerObjetos();
	}

	private static void leerObjetos() {
		
		
	}

	private static void escribirObjeto() {
		Scanner sc = new Scanner(System.in);
		Persona persona = new Persona();
		
		try (ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream(Utilidades.RUTA+DOCTRABAJO))){
			System.out.println("DATOS DEL USUARIO \nNombre:");
	        persona.setNombre(new StringBuilder(sc.nextLine()));
	        System.out.println("Primer apellido:");
	        persona.setApellido1(new StringBuilder(sc.nextLine()));
	        System.out.println("Segundo apellido:");
	        persona.setApellido2(new StringBuilder(sc.nextLine()));


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
