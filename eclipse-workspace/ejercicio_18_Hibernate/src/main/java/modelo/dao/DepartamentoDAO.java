package modelo.dao;



import java.util.List;

import modelo.dto.Departamentos;


public interface DepartamentoDAO {
	
	void anadirDpto(Departamentos dpto);
	
//	int eliminarDpto(int dptoNum);
//	int modificarDpto(int dptoNum, DepartamentoDTO dpto);
	List<Departamentos> listarDptos();

//	int creaTablaDepartamentos();
//	int buscarDpto(String nombre, String localidad);
}
