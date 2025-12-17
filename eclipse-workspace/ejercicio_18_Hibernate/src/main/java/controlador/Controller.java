package controlador;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.dao.DepartamentoDAO;
import modelo.dao.DepartamentoDAOImpl;
import modelo.dao.EmpleadosDAO;
import modelo.dao.EmpleadosDAOImpl;

import modelo.dto.Departamentos;
import modelo.dto.Empleados;
import modelo.dto.JavaFX.DepartamentoDTOPropiedadesJavaFX;

public class Controller {
	private static DepartamentoDAO dptoDAO = new DepartamentoDAOImpl();
	private static Departamentos dptoH = new Departamentos();
	private static DepartamentoDTOPropiedadesJavaFX dptoSeleccionado; 

	@FXML
    private TableView<DepartamentoDTOPropiedadesJavaFX> tablaDepartamentos;
	
    @FXML
    private TableColumn<DepartamentoDTOPropiedadesJavaFX, Integer> colIdDpto;

    @FXML
    private TableColumn<DepartamentoDTOPropiedadesJavaFX, String> colLocalidadDpto;

    @FXML
    private TableColumn<DepartamentoDTOPropiedadesJavaFX, String> colNombreDpto;

    @FXML
    private TextField txtLocalidadDpto;

    @FXML
    private TextField txtNombreDpto;

    @FXML
    void eliminarDepartamento(ActionEvent event) {
    	dptoDAO.eliminarDpto(dptoSeleccionado.getDepNum());
    	limpiaControles();
    	cargarDatosDepartamentos();
    }

    @FXML
    void guardarDepartamento(ActionEvent event) {
    	dptoH.setDnombre(txtNombreDpto.getText());
    	dptoH.setLoc(txtLocalidadDpto.getText());
    	dptoDAO.anadirDpto(dptoH);
    	limpiaControles();
    	cargarDatosDepartamentos();
    }

    @FXML
    void modificarDepartamento(ActionEvent event) {

    }
    
    @FXML
    void seleccionarDpto(MouseEvent event) {
    	dptoSeleccionado = tablaDepartamentos.getSelectionModel().getSelectedItem();
    	if (dptoSeleccionado != null) {
    		txtNombreDpto.setText(dptoSeleccionado.getDepNombre());
        	txtLocalidadDpto.setText(dptoSeleccionado.getDepLocalidad());
    	}
    }

    
    @FXML
	void initialize() {
    	// Cargo las columnas de la tableView DepartamentoDTOPropiedadesJavaFX
    	// Indico la fuente 
    	colIdDpto.setCellValueFactory(new PropertyValueFactory<>("depNum"));
    	colNombreDpto.setCellValueFactory(new PropertyValueFactory<>("depNombre"));
    	colLocalidadDpto.setCellValueFactory(new PropertyValueFactory<>("depLocalidad"));
    	// Cargamos datos en la tableView
    	cargarDatosDepartamentos();
    	
	}

    private void cargarDatosDepartamentos() {
    	List<DepartamentoDTOPropiedadesJavaFX> alDptoJFX = new ArrayList<DepartamentoDTOPropiedadesJavaFX>();
    	for (Departamentos dpto: dptoDAO.listarDptos()) {
    		DepartamentoDTOPropiedadesJavaFX dptoJFX = new DepartamentoDTOPropiedadesJavaFX(dpto.getDeptNo(), dpto.getDnombre(), dpto.getLoc());
    		alDptoJFX.add(dptoJFX);
    	}
    	tablaDepartamentos.setItems(FXCollections.observableArrayList(alDptoJFX));
 	}

	void limpiaControles() {
    	txtNombreDpto.setText("");
    	txtLocalidadDpto.setText("");
    }
}
