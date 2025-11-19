package ut2_ejercicio17.modelo.dao;

import java.util.ArrayList;

import ut2_ejercicio17.modelo.dto.DepartamentoDTO;

public interface DepartamentoDAO {
	
	/**
	 * 
	 * @param dpto objeto de la clase DepartamentoDTO correspondiente al departamento que se quiere añadir
	 * @return 0 en caso de que el departamento no se añadio o el id del departamento que se añadió
	 */
	int anadirDpto(DepartamentoDTO dpto);
	/**
	 * 
	 * @param dptoNum codigo del departamento a eliminar
	 * @return 1 en caso de que el departamento se eliminara con éxito 0 en caso contrario
	 */
	int eliminarDpto(int dptoNum);
	int modificarDpto(int dptoNum, DepartamentoDTO dpto);
	/**
	 * 
	 * @return ArrayList<DepartamentoDTO>
	 */
	ArrayList<DepartamentoDTO> listarDptos();
	
	//Para SQLite
	int creaEsquemaSiNoExiste();
	/**
	 * 
	 * @param nombre nombre del departamento a buscar
	 * @param localidad localidad del departamento a buscar
	 * @return id del departamento buscado 0 en caso de no encontrar el departamento con ese nombre y localidad
	 */
	int buscarDpto(String nombre, String localidad);
}
