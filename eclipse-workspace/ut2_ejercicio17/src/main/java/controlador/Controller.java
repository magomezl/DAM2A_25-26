package controlador;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.dao.DepartamentoDAO;
import modelo.dao.DepartamentoDAOImpl;
import modelo.dto.DepartamentoDTO;
import modelo.dto.DepartamentoDTOPropiedadesJavaFX;

public class Controller {

	@FXML
	private TableColumn<DepartamentoDTOPropiedadesJavaFX, Integer> colIdDpto;

	@FXML
	private TableColumn<DepartamentoDTOPropiedadesJavaFX, String> colLocalidadDpto;

	@FXML
	private TableColumn<DepartamentoDTOPropiedadesJavaFX, String> colNombreDpto;

	@FXML
	private TableView<DepartamentoDTOPropiedadesJavaFX> tablaDepartamentos;

	
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
		cargarDepartamentos();
		txtNombreDpto.setText(null);
		txtLocalidadDpto.setText(null);
	}

	@FXML
	void initialize() {
		//Cargar columnas de la tabla departamentos
		colIdDpto.setCellValueFactory(new PropertyValueFactory<>("depNum"));
		colNombreDpto.setCellValueFactory(new PropertyValueFactory<>("depNombre"));
		colLocalidadDpto.setCellValueFactory(new PropertyValueFactory<>("depLocalidad"));
		
		cargarDepartamentos();
	}

	private void cargarDepartamentos() {
		DepartamentoDAO dptoDAO = new DepartamentoDAOImpl();
		ArrayList<DepartamentoDTOPropiedadesJavaFX> alDptoJFX = new ArrayList<DepartamentoDTOPropiedadesJavaFX>();
		for(DepartamentoDTO dpto: dptoDAO.listarDptos()) {
			DepartamentoDTOPropiedadesJavaFX dptoJFX = new DepartamentoDTOPropiedadesJavaFX(dpto.getDepNum(), dpto.getDepNombre(), dpto.getDepLocalidad());
			alDptoJFX.add(dptoJFX);
		}
		
		ObservableList<DepartamentoDTOPropiedadesJavaFX> listaDepartamentos = FXCollections.observableArrayList(alDptoJFX);
		tablaDepartamentos.setItems(listaDepartamentos);
		
	}

	
}
