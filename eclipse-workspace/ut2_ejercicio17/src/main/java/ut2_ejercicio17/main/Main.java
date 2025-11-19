package ut2_ejercicio17.main;

import java.util.ArrayList;
import java.util.Scanner;

import ut2_ejercicio17.modelo.dao.DepartamentoDAO;
import ut2_ejercicio17.modelo.dao.DepartamentoDAOImpl;
import ut2_ejercicio17.modelo.db.Conexion;
import ut2_ejercicio17.modelo.dto.DepartamentoDTO;

public class Main {
	
	
	public static void main(String[] args) {
		// TODO para hacer alumnos: DTO para Empleados DAO para Empleados con anadirEmpleado y listarEmpleados 
		// Modificar apartado 2 del menu para añadir departamento de manera que se abra una transacción y se contemple la 
		// opción de añadir empleados a ese departamento. Si no se añade al menos un empleado no se crea el departamento
		
		DepartamentoDAO dptDAO = new DepartamentoDAOImpl();
		dptDAO.creaEsquemaSiNoExiste();
		Scanner sc = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("\n===== MENÚ PRINCIPAL =====");
			System.out.println("1. Gestionar Departamentos");
			System.out.println("2. Gestionar Empleados");
			System.out.println("0. Salir");
			System.out.print("Elige una opción: ");
			opcion = sc.nextInt();
			
			switch (opcion) {
			case 1:
				menuDepartamentos(sc);
				break;
			case 2:
				menuEmpleados(sc);
				break;
			case 0:
				
				//TODO método para cerrar conexión
				
				System.out.println("¡Hasta pronto!");
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (opcion != 0);
		sc.close();
	}

	private static void menuDepartamentos(Scanner sc) {
		DepartamentoDAO dptDAO = new DepartamentoDAOImpl();
		int opcion;
		do {
			System.out.println("\n--- GESTIÓN DE DEPARTAMENTOS ---");
			System.out.println("1. Listar departamentos");
			System.out.println("2. Añadir departamento");
			System.out.println("3. Eliminar departamento");
			System.out.println("4. Modificar departamento");
			System.out.println("0. Volver al menú principal");
			System.out.print("Elige una opción: ");
			opcion = sc.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("Listando departamentos...");
				
				ArrayList<DepartamentoDTO> alDptoJ = new ArrayList<DepartamentoDTO>();
		    	for (DepartamentoDTO dpto: dptDAO.listarDptos()) {
		    		System.out.println(dpto);
		    	}
				break;
			case 2:
				sc.nextLine();
				System.out.println("Añadiendo departamento...");
				
				DepartamentoDTO dpto = new DepartamentoDTO();
				System.out.print("Introduce el nombre del departamento: ");
				dpto.setDepNombre(sc.nextLine());
				System.out.print("Introduce la localidad del departamento: ");
				dpto.setDepLocalidad(sc.nextLine());
				System.out.println("Departamento añadido con clave (0 indica que no se añadió) " + dptDAO.anadirDpto(dpto));
				
				break;
			case 3:
				sc.nextLine();
				System.out.println("Eliminando departamento...");
				System.out.print("Introduce el nombre del departamento: ");
				String nombre = sc.nextLine();
				System.out.print("Introduce la localidad del departamento: ");
				String localidad = sc.nextLine();
				
				System.out.println(dptDAO.eliminarDpto(dptDAO.buscarDpto(nombre, localidad))==0 ? 
						"No se eliminó el departamento por no existir" : "Departamento eliminado con éxito");
				break;
			case 4:
				sc.nextLine();
				System.out.println("Modificando departamento...");
				break;
			case 0:
				System.out.println("Volviendo al menú principal...");
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (opcion != 0);
	}

	private static void menuEmpleados(Scanner sc) {

		int opcion;
		do {
			System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
			System.out.println("1. Listar empleados");
			System.out.println("2. Añadir empleado");
			System.out.println("3. Eliminar empleado");
			System.out.println("4. Modificar empleado");
			System.out.println("0. Volver al menú principal");
			System.out.print("Elige una opción: ");
			opcion = sc.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("Listando empleados...");
				break;
			case 2:
				System.out.println("Añadiendo empleado...");
				break;
			case 3:
				System.out.println("Eliminando empleado...");
				break;
			case 4:
				System.out.println("Modificando empleado...");
				break;
			case 0:
				System.out.println("Volviendo al menú principal...");
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (opcion != 0);
	}

}
