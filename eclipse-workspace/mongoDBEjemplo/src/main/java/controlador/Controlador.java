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
import modelo.dao.hibernate.HibernateDAO;
import modelo.dao.hibernate.HibernateDAOImpl;
import modelo.dao.mongodb.MongoDAO;
import modelo.dao.mongodb.MongoDAOImpl;
import modelo.dto.Autores;
import modelo.dto.Libros;
import modelo.dto.ObservablesJFX.GenericaDTOPropiedadesJavaFX;

public class Controlador {
	private static MongoDAO mongoDAO = new MongoDAOImpl();
	private static HibernateDAO hibernateDAO = new HibernateDAOImpl();

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
    	
    	/**
    	//TODO
    	colGeneroLibro.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	List<GenericaDTOPropiedadesJavaFX> algeneroJFX = new ArrayList<GenericaDTOPropiedadesJavaFX>();
    	for (String genero: mongoDAO.getGeneros()) {
    		System.out.println(genero);
    		GenericaDTOPropiedadesJavaFX generoJFX = new GenericaDTOPropiedadesJavaFX(genero);
    		algeneroJFX.add(generoJFX);
    	}
    	tablaGeneros.setItems(FXCollections.observableArrayList(algeneroJFX));
    	**/
    	
    	
    	/**
    	 * Con estas dos llamadas, obtengo dos listas de objetos desacopladas (detached/transient), 
    	 * es decir, no están gestionadas por hibernate (salvo porque usamos las clases de hibernate como dto
    	 * (objetos de transferencia de datos), algo que en proyectos reales no haríamos para ganarantizar desacoplamiento,
    	 *  	  
    	 */
    	List<Autores> autores =  mongoDAO.getAutores();
    	List<Libros> libros = mongoDAO.getLibros();
    	
//    	System.out.println(autores);
//    	System.out.println(libros);
    	// Guardo en MySQL a través de hibernate
    	hibernateDAO.anadirAutores(autores);
    	
    	// TODO sin acabar. Seguir por aquí.
    	hibernateDAO.anadirLibros(libros);
    }
    
}
