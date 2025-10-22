package proyectoMavenDAM2A25_26.ejercicio12_Excel_POI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import proyectoMavenDAM2A25_26.utilidades.Utilidades;

public class Ejercicio_12 {
	private final static String DOCTRABAJO_IN = "vehiculosElectricos.xlsx";
	private Workbook wb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
			buscarEstacionesPorOperador("Wenea");
			String provincia = new String("zamora");
			try {
				buscarEstacionesPorProvincia(false, provincia);
			} catch (IllegalArgumentException e) {
				System.out.println("Ya se han encontrado los datos de " + provincia + "¿Desea sobreescribir? (S/N): ");
				if (sc.next().toUpperCase().equals("S")){
					buscarEstacionesPorProvincia(true, provincia);
				}
			}
			modificaOperador("EasyChargerEasyCharger", "EasyCharger");
	}

	private static void modificaOperador(String operadorOld, String operadorNew) {
		
		try (FileInputStream fis = new FileInputStream(Utilidades.getRuta() + Utilidades.getRutaExcel() + DOCTRABAJO_IN);
				Workbook wb = new XSSFWorkbook(fis)){
			Sheet hoja = wb.getSheetAt(0);
			for (Row fila : hoja) {
				Cell celdaBusqueda = fila.getCell(2);
				if (celdaBusqueda!=null) {
					if (celdaBusqueda.getStringCellValue().toLowerCase().equals(operadorOld.toLowerCase())) {
						celdaBusqueda.setCellValue(operadorNew);
					}
				}
			}
			wb.write(new FileOutputStream(Utilidades.getRuta() + Utilidades.getRutaExcel() + DOCTRABAJO_IN));
		} catch (FileNotFoundException e) {
			System.out.println("Documento no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de lectura/escritura");
			e.printStackTrace();
		}
	}
	/**
	 * Este método se puede hacer de manera más simple comprobando la existencia de la hoja al principio y haciendo la pregunta 
	 * correspondiente de sobreescribir o no, pero lo dejamos así para practicar el throws  
	 * @param eliminar
	 * @param provincia
	 * @throws IllegalArgumentException
	 */
	private static void buscarEstacionesPorProvincia(boolean eliminar, String provincia) throws IllegalArgumentException{
		try (FileInputStream fis = new FileInputStream(Utilidades.getRuta() + Utilidades.getRutaExcel() + DOCTRABAJO_IN);
				Workbook wb = new XSSFWorkbook(fis)){
			if (eliminar) {
				wb.removeSheetAt(wb.getSheetIndex(wb.getSheet(provincia)));
			}
			
			Sheet hoja = wb.getSheetAt(0);
			Sheet newHoja = wb.createSheet(provincia);
			
			//Copio y creo las filas con las cabeceras
			Row CabeceraFila = hoja.getRow(0);
			Row CabeceranewFila = newHoja.createRow(0); 
			Row newFila;
			CabeceranewFila.createCell(0).setCellValue(CabeceraFila.getCell(0).getStringCellValue());
			CabeceranewFila.createCell(1).setCellValue(CabeceraFila.getCell(1).getStringCellValue());
			int newNumFila = 1;
			
			// Muestro las localizaciones de los puntos de carga del operador pasado como parámetro
			System.out.println("PUNTOS DE RECARGA EN " + provincia );
			for (Row fila : hoja) {
				Cell celdaBusqueda = fila.getCell(1);
				if (celdaBusqueda!=null) {
					if (celdaBusqueda.getStringCellValue().toLowerCase().contains("("+provincia.toLowerCase()+")")) {
						System.out.println("----->" + fila.getCell(0).getStringCellValue() + "(" + 
								fila.getCell(1).getStringCellValue());
						newFila = newHoja.createRow(newNumFila++);
						newFila.createCell(0).setCellValue(fila.getCell(0).getStringCellValue());
						newFila.createCell(1).setCellValue(fila.getCell(1).getStringCellValue());
					}
				}
			}
			wb.write(new FileOutputStream(Utilidades.getRuta() + Utilidades.getRutaExcel() + DOCTRABAJO_IN));
		} catch (FileNotFoundException e) {
			System.out.println("Documento no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de lectura/escritura");
			e.printStackTrace();
		}
	}

	private static void buscarEstacionesPorOperador(String operador) {
		try (FileInputStream fis = new FileInputStream(Utilidades.getRuta() + Utilidades.getRutaExcel() + DOCTRABAJO_IN);
				Workbook wb = new XSSFWorkbook(fis)){
			
			Sheet hoja = wb.getSheetAt(0); 
			System.out.println("PUNTOS DE RECARGA " + operador);
			for (Row fila : hoja) {
				Cell celdaBusqueda = fila.getCell(2);
				if (celdaBusqueda!=null) {
					if (celdaBusqueda.getStringCellValue().toLowerCase().contains(operador.toLowerCase())) {
						System.out.println("----->" + fila.getCell(0).getStringCellValue() + "(" + 
								fila.getCell(1).getStringCellValue() + "\t" + fila.getCell(3).getStringCellValue()+ " conectores");
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Documento no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de lectura/escritura");
			e.printStackTrace();
		}
	}
}
