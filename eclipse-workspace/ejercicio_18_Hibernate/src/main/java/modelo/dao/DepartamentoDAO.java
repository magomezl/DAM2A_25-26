package modelo.dao;



import java.util.List;

import modelo.dto.Departamentos;


public interface DepartamentoDAO {
	
	void anadirDpto(Departamentos dpto);
	
//	int eliminarDpto(int dptoNum);
	void modificarDpto(Departamentos dptoOld, Departamentos dptoNew);
	void modificarDpto(int dptoOld, Departamentos dptoNew);
	
	void eliminarDpto(int dptoOld);
	
	List<Departamentos> listarDptos();
	List<Departamentos> listarDptos(String localidad);

//	int creaTablaDepartamentos();
//	int buscarDpto(String nombre, String localidad);
}
