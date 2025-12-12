package modelo.dao;

import java.util.List;

import modelo.dto.Departamentos;
import modelo.dto.Empleados;

public interface EmpleadosDAO {
	/**
	 * 
	 */
	/**
	 * añade un empleado a la DB 
	 * @param empleado objeto de la clase Empleados a añadir
	 * @return 0 en caso de no añadir el empleado 1 en caso de que se añada con éxito
	 */
	int anadirEmpleado(Empleados empleado);
	
	/**
	 * modifica un empleado de la DB
	 * @param empleadoOld objeto de la clase Empleados que queremos modificar
	 * @param empleadoNew objeto de la clase Empleados cuyos valores utilizaremos para cambiar el dato original
	 */
	int modificarEmpleado(Empleados empleadoOld, Empleados empleadoNew);
	int modificarEmpleado(int empleadoOld, Empleados empleadoNew);
	
	int eliminarEmpleado(int empleadoOld);
	
	/**
	 * proporciona todos los datos de los empleados de la DB
	 * @return una lista de objetos de la clase Departamentos
	 */
	List<Empleados> listarEmpleados();
	List<Empleados> listarEmpleados(String localidad);
	List<Empleados> listarEmpleadosNombreDpto(String nombreDpto);
	List<Empleados> listarEmpleados(String nombre, String apellido1, String apellido2);
	List<Empleados> listarEmpleados(Departamentos dpto);
	Empleados listarEmpleados(Integer id);
	
	
	
	
}
