package _5_ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Scanner;

import utilidades.Utilidades;

public class _5_Ejercicio {
	private final static String DOCTRABAJO = "serializaPersona";
	private static Scanner sc = new Scanner(System.in);
	private static ObjectOutputStream oOS =null;
	
	public static void main(String[] args) {
		inicializar();
		escribirObjeto(obtenerDatos());
		escribirObjeto(new Persona(new StringBuilder("Felipe"), new StringBuilder("Gómez"), 
				new StringBuilder("Borrego"), LocalDate.of(2020, 12, 21)));
		System.out.println("El fichero contiene " + leerObjetos() + " elementos");
		
	}

	private static void inicializar() {
		try {
			File file = new File(Utilidades.RUTA+DOCTRABAJO);
			 if (file.exists() && file.length()>0) {
//				oOS = new MyObjectOutputStream(new FileOutputStream(file, true));
//				En este caso, la clase MyObjectOutputStream sería innecesaria 
				oOS = new ObjectOutputStream(new FileOutputStream(file, true)) {
					@Override
					protected void writeStreamHeader() throws IOException {
					}
				};
				 
			}else {
				oOS = new ObjectOutputStream(new FileOutputStream(file));
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida");
			e.printStackTrace();
		}
		
	}

	private static int leerObjetos() {
		int contador = 0;
		try (ObjectInputStream oIS = new ObjectInputStream(new FileInputStream
				(new File(Utilidades.RUTA+DOCTRABAJO)))){

			while(true) {
				System.out.println((Persona) oIS.readObject());
				++contador;
			}
		}catch (EOFException e) {
			System.out.println("Fin de fichero");
		}catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return contador; 
	}

	private static void escribirObjeto(Persona persona) {
		try {
		    oOS.writeObject(persona);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Persona obtenerDatos() {
		Persona persona = new Persona();
		System.out.println("DATOS DEL USUARIO \nNombre:");
        persona.setNombre(new StringBuilder(sc.nextLine()));
        System.out.println("Primer apellido:");
        persona.setApellido1(new StringBuilder(sc.nextLine()));
        System.out.println("Segundo apellido:");
        persona.setApellido2(new StringBuilder(sc.nextLine()));
        System.out.println("Fecha de nacimiento (yyyy-mm-dd):");
        persona.setNacimiento(LocalDate.parse(sc.nextLine()));
		return persona;
	}

}
