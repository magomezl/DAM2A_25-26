package modelo.dao;



import java.util.List;

import modelo.dto.Departamentos;

/**
 * @author magomezl. Mª Aurora Gómez López
 * @version 1.0
 */
public interface DepartamentoDAO {
	/**
	 * añade un departamento a la DB
	 * @param dpto objeto de la clase Departamentos a añadir
	 */
	void anadirDpto(Departamentos dpto);
	
	/**
	 * modifica un departamento de la DB
	 * @param dptoOld objeto de la clase Departamentos que queremos modificar
	 * @param dptoNew objeto de la clase Departamentos cuyos valores utilizaremos para cambiar el dato original
	 */
	void modificarDpto(Departamentos dptoOld, Departamentos dptoNew);
	void modificarDpto(int dptoOld, Departamentos dptoNew);
	
	void eliminarDpto(int dptoOld);
	
	/**
	 * proporciona todos los datos de los departamentos de la DB
	 * @return una lista de objetos de la clase Departamentos
	 */
	List<Departamentos> listarDptos();
	List<Departamentos> listarDptos(String localidad);
	List<Departamentos> listarDptosNombre(String nombre);
	Departamentos listarDptosLocNom(String nombre, String localidad);
	int listarDptos(String nombre, String localidad);
}
