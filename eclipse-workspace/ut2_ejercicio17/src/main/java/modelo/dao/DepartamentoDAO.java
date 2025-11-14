package modelo.dao;

import java.util.ArrayList;

import modelo.dto.DepartamentoDTO;



public interface DepartamentoDAO {
	int anadirDpto(DepartamentoDTO dpto);
	int eliminarDpto(int dptoNum);
	int modificarDpto(int dptoNum, DepartamentoDTO dpto);
	ArrayList<DepartamentoDTO> listarDptos();
}
