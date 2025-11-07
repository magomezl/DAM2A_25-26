package ut2_ejercicio17.main;

import javafx.application.Application;
import javafx.stage.Stage;
import ut2_ejercicio17.modelo.dao.DepartamentoDAO;
import ut2_ejercicio17.modelo.dao.DepartamentoDAOImpl;
import ut2_ejercicio17.modelo.dto.DepartamentoDTO;

public class MainApp extends Application{

	public static void main(String[] args) {
		DepartamentoDAO dptoDAO = new DepartamentoDAOImpl();
		DepartamentoDTO dptoDTO = new DepartamentoDTO();
		dptoDTO.setDepNombre("Formación");
		dptoDTO.setDepLocalidad("Murcia");
		dptoDAO.anadirDpto(dptoDTO);
		dptoDTO.setDepNombre("Adiestramiento");
		dptoDTO.setDepLocalidad("Orense");
		dptoDAO.anadirDpto(dptoDTO);
		
		
		dptoDAO.eliminarDpto(2);
		
		dptoDTO.setDepNombre("Digitalización");
		dptoDTO.setDepLocalidad("Santander");
		dptoDAO.modificarDpto(3, dptoDTO);
		
		for (DepartamentoDTO dpto: dptoDAO.listarDptos()) {
			System.out.println(dpto);
		}
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
