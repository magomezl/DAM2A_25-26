package controlador;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.dao.mongodb.MongoDAO;
import modelo.dao.mongodb.MongoDAOImpl;
import modelo.dto.ObservablesJFX.GenericaDTOPropiedadesJavaFX;

public class Controlador {
	private static MongoDAO mongoDAO = new MongoDAOImpl();

    @FXML
    private ComboBox<?> ComboAutores;

    @FXML
    private TableColumn<?, ?> colAnioLibro;

    @FXML
    private TableColumn<GenericaDTOPropiedadesJavaFX, String> colGeneroLibro;

    @FXML
    private TableColumn<?, ?> colMuerteAutor;

    @FXML
    private TableColumn<?, ?> colMuerteAutoria;

    @FXML
    private TableColumn<?, ?> colNacimientoAutor;

    @FXML
    private TableColumn<?, ?> colNacimientoAutoria;

    @FXML
    private TableColumn<?, ?> colNacionalidad;

    @FXML
    private TableColumn<?, ?> colNacionalidadAutor;

    @FXML
    private TableColumn<?, ?> colNacionalidadAutoria;

    @FXML
    private TableColumn<?, ?> colNombreAutor;

    @FXML
    private TableColumn<?, ?> colNombreAutoria;

    @FXML
    private TableColumn<?, ?> colTituloLibro;
    
    @FXML
    private TableColumn<?, ?> colGeneroLibroEnLibros;

    @FXML
    private TableView<?> tablaAutores;

    @FXML
    private TableView<?> tablaAutorias;

    @FXML
    private TableView<GenericaDTOPropiedadesJavaFX> tablaGeneros;

    @FXML
    private TableView<?> tablaLibros;

    @FXML
    private TableView<?> tablaNacionalidades;

    @FXML
    private TextField txtAnioLibro;

    @FXML
    private TextField txtGeneroLibro;

    @FXML
    private TextField txtMuerteAutor;

    @FXML
    private TextField txtNacimientoAutor;

    @FXML
    private TextField txtNacionalidad;

    @FXML
    private TextField txtNacionalidadAutor;

    @FXML
    private TextField txtNombreAutor;

    @FXML
    private TextField txtNombreLibroAutoria;

    @FXML
    private TextField txtTituloLibro;

    @FXML
    void buscarLibroAutoria(ActionEvent event) {

    }

    @FXML
    void eliminarAutor(ActionEvent event) {

    }

    @FXML
    void eliminarAutoria(ActionEvent event) {

    }

    @FXML
    void eliminarGenero(ActionEvent event) {

    }

    @FXML
    void eliminarLibro(ActionEvent event) {

    }

    @FXML
    void eliminarNacionalidad(ActionEvent event) {

    }

    @FXML
    void guardarAutor(ActionEvent event) {

    }

    @FXML
    void guardarAutoria(ActionEvent event) {

    }

    @FXML
    void guardarGenero(ActionEvent event) {

    }

    @FXML
    void guardarLibro(ActionEvent event) {

    }

    @FXML
    void guardarNacionalidad(ActionEvent event) {

    }

    @FXML
    void modificarAutor(ActionEvent event) {

    }

    @FXML
    void modificarGenero(ActionEvent event) {

    }

    @FXML
    void modificarLibro(ActionEvent event) {

    }

    @FXML
    void modificarNacionalidad(ActionEvent event) {

    }

    @FXML
    void seleccionarAutor(MouseEvent event) {

    }

    @FXML
    void seleccionarAutoria(MouseEvent event) {

    }

    @FXML
    void seleccionarGenero(MouseEvent event) {

    }

    @FXML
    void seleccionarLibro(MouseEvent event) {

    }

    @FXML
    void initialize() {
    	//TODO
    	colGeneroLibro.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	List<GenericaDTOPropiedadesJavaFX> algeneroJFX = new ArrayList<GenericaDTOPropiedadesJavaFX>();
    	for (String genero: mongoDAO.getGeneros()) {
    		System.out.println(genero);
    		GenericaDTOPropiedadesJavaFX generoJFX = new GenericaDTOPropiedadesJavaFX(genero);
    		algeneroJFX.add(generoJFX);
    	}
    	tablaGeneros.setItems(FXCollections.observableArrayList(algeneroJFX));
    }
    
    
    
}
