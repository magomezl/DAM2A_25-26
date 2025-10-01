package _6_ficheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

import utilidades.Utilidades;

public class _6_Ejercicio {
		private static Properties conf = new Properties();
		private final static String FICHEROTRABAJO = "configuracion.props";
		
		public static void main(String[] args) {
			creaConfiguracion();
			System.out.println("DOCUMENTO ORIGINAL");
			leerConfiguracion();
			modificaConfiguracion();
			System.out.println("DOCUMENTO MODIFICADO");
			leerConfiguracion();
		}

		private static void modificaConfiguracion() {
			Scanner sc = new Scanner (System.in);

			System.out.println("MODIFICANDO CONFIGURACIÓN");
//			System.out.println("user: ");
//			String usuario = sc.nextLine();
//			System.out.println("password: ");
//			String password = sc.nextLine();
//			System.out.println("server: ");
//			String servidor = sc.nextLine();
			System.out.println("valor a sumar al puerto: ");
			int puerto = Integer.valueOf(sc.nextLine());
			System.out.println("nuevo mes para la fecha (1-12): ");
			int mes  = Integer.valueOf(sc.nextLine());
			try (FileInputStream fiS = new FileInputStream(Utilidades.RUTA+FICHEROTRABAJO);
				 FileOutputStream foS =	new FileOutputStream(Utilidades.RUTA+FICHEROTRABAJO)){
				conf.load(fiS);
//				conf.setProperty("user", usuario);
//				conf.setProperty("password", password);
//				conf.setProperty("server", servidor );
				conf.setProperty("port", String.valueOf(Integer.parseInt((String) conf.get("port")) + puerto));
				//Tomo la propiedad (String) la paso a parseo a LocalDate, le modifico el mes (wirhMonth) y la transformo en String
				//TODO Fomato de fecha
				conf.setProperty("date", String.valueOf(
						LocalDate.parse(conf.getProperty("date")).withMonth(mes))
						);
				//Cambio el valor del power
				conf.setProperty("power", String.valueOf(!(Boolean.valueOf(conf.getProperty("power")))));
				conf.store(foS, "Fichero de configuración");
			} catch (FileNotFoundException e) {
				System.out.println("Fichero no encontrado");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error de lectura/escritura");
			}
		}

		private static void leerConfiguracion() {
			try (FileInputStream fiS = new FileInputStream(Utilidades.RUTA+FICHEROTRABAJO)){
				conf.load(fiS);
				conf.list(System.out);
			} catch (FileNotFoundException e) {
				System.out.println("Fichero no encontrado");
			} catch (IOException e) {
				System.out.println("Error de letura");
			}
		}

		private static void creaConfiguracion() {
			conf.setProperty("user", "usuario");
			conf.setProperty("password", "micontrasena");
			conf.setProperty("server", "localhost");
			conf.setProperty("port", "3306");
			conf.setProperty("date", "2022-11-08");
			conf.setProperty("power", "true");
			try (FileOutputStream foS = new FileOutputStream(Utilidades.RUTA+FICHEROTRABAJO)){
				conf.store(foS, "Fichero de configuración");
			} catch (FileNotFoundException e) {
				System.out.println("Fichero no encontrado");
			} catch (IOException e) {
				System.out.println("Error de escritura");
			}
		}
	}


