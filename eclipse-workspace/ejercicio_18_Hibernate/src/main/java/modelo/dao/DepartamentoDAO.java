package modelo.dao;



import java.util.List;

import modelo.dto.Departamentos;


public interface DepartamentoDAO {
	
	void anadirDpto(Departamentos dpto);

	void modificarDpto(Departamentos dptoOld, Departamentos dptoNew);
	void modificarDpto(int dptoOld, Departamentos dptoNew);
	
	void eliminarDpto(int dptoOld);
	
	List<Departamentos> listarDptos();
	List<Departamentos> listarDptos(String localidad);
	List<Departamentos> listarDptosNombre(String nombre);
	Departamentos listarDptosLocNom(String localidad, String nombre);
	int listarDptos(String localidad, String nombre);
}
