package _4_Ficheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import utilidades.Utilidades;

public class _4_Ejercicio {

	private final static String DOCTRABAJO_IN = "imagen.jpg";
	private final static String DOCTRABAJO_OUT = "imagen_copia_2.jpg";


	public static void main(String[] args) {
		try ( FileInputStream fIS = new FileInputStream(Utilidades.RUTA+DOCTRABAJO_IN);
				FileOutputStream fOS = new FileOutputStream(Utilidades.RUTA+DOCTRABAJO_OUT )){
			/**
				int leido;
				while ((leido=fIS.read())!=-1) {
					fOS.write(leido);
				}
			 **/
			fOS.write(fIS.readAllBytes());
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no existe");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de lectura o escritura");
			e.printStackTrace();
		}

	}

}
