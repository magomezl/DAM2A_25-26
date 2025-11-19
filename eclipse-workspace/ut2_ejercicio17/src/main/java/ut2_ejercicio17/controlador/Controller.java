package ut2_ejercicio17.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ut2_ejercicio17.modelo.dao.DepartamentoDAO;
import ut2_ejercicio17.modelo.dao.DepartamentoDAOImpl;
import ut2_ejercicio17.modelo.dto.DepartamentoDTO;

public class Controller {
	@FXML
	private TextField txtNombreDpto;
	
	@FXML
	private TextField txtLocalidadDpto;
	
	@FXML
	private void guardarDepartamento(ActionEvent event) {
		DepartamentoDAO dptoDAO = new DepartamentoDAOImpl();
		DepartamentoDTO dptoDTO = new DepartamentoDTO();
		dptoDTO.setDepNombre(txtNombreDpto.getText());
		dptoDTO.setDepLocalidad(txtLocalidadDpto.getText());
		dptoDAO.anadirDpto(dptoDTO);
		txtNombreDpto.setText(null);
		txtLocalidadDpto.setText(null);
	}

	@FXML
	void initialize() {
		DepartamentoDAO dptoDAO = new DepartamentoDAOImpl();
		dptoDAO.creaEsquemaSiNoExiste();
		
		

	}
}
