package ut2_ejercicio17.main;

import ut2_ejercicio17.modelo.dao.DepartamentoDAO;
import ut2_ejercicio17.modelo.dao.DepartamentoDAOImpl;
import ut2_ejercicio17.modelo.dto.DepartamentoDTO;

public class MainApp {

	public static void main(String[] args) {
		DepartamentoDAO dptoDAO = new DepartamentoDAOImpl();
		DepartamentoDTO dptoDTO = new DepartamentoDTO();
		dptoDTO.setDepNombre("Formación");
		dptoDTO.setDepLocalidad("Murcia");
		dptoDAO.anadirDpto(dptoDTO);
	}
}
