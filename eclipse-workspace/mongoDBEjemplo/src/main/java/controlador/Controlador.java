package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
import modelo.dto.Generos;
import modelo.dto.Libros;
import modelo.dto.Nacionalidades;
import modelo.dto.ObservablesJFX.AutorDTOPropiedadesJavaFX;
import modelo.dto.ObservablesJFX.GenericaDTOPropiedadesJavaFX;
import modelo.dto.ObservablesJFX.LibroDTOPropiedadesJavaFX;

public class Controlador {
	private static MongoDAO mongoDAO = new MongoDAOImpl();
	private static HibernateDAO hibernateDAO = new HibernateDAOImpl();

    @FXML
    private ComboBox<?> ComboAutores;

    @FXML
    private TableColumn<?, ?> colAnioLibro;
    
    @FXML
    private TableView<GenericaDTOPropiedadesJavaFX> tablaGeneros;

    @FXML
    private TableColumn<GenericaDTOPropiedadesJavaFX, String> colGeneroLibro;
    
    @FXML
    private TableView<GenericaDTOPropiedadesJavaFX> tablaNacionalidades;
    
    @FXML
    private TableColumn<GenericaDTOPropiedadesJavaFX, String> colNacionalidad;

    
    @FXML
    private TableView<AutorDTOPropiedadesJavaFX> tablaAutores;
    
    @FXML
    private TableColumn<AutorDTOPropiedadesJavaFX, String> colNombreAutor;
    
    @FXML
    private TableColumn<AutorDTOPropiedadesJavaFX, String> colNacionalidadAutor;

    @FXML
    private TableColumn<AutorDTOPropiedadesJavaFX, Integer> colMuerteAutor;
    
    @FXML
    private TableColumn<AutorDTOPropiedadesJavaFX, Integer> colNacimientoAutor;
    
    @FXML
    private ComboBox<Nacionalidades> cmbNacionalidadAutor;
    
    
    @FXML
    private TableView<LibroDTOPropiedadesJavaFX> tablaLibros;
    @FXML
    private TableColumn<LibroDTOPropiedadesJavaFX, String> colTituloLibro;
    @FXML
    private TableColumn<LibroDTOPropiedadesJavaFX, String> colGeneroLibroEnLibros;

   
    @FXML
    private TableView<AutorDTOPropiedadesJavaFX> tablaAutorias;

    @FXML
    private TableColumn<AutorDTOPropiedadesJavaFX, Integer> colMuerteAutoria;
    @FXML
    private TableColumn<AutorDTOPropiedadesJavaFX, Integer> colNacimientoAutoria;
    @FXML
    private TableColumn<AutorDTOPropiedadesJavaFX, String> colNacionalidadAutoria;
    @FXML
    private TableColumn<AutorDTOPropiedadesJavaFX, String> colNombreAutoria;


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
    private TextField txtNombreAutor;

    @FXML
    private TextField txtNombreLibroAutoria;

    @FXML
    private TextField txtTituloLibro;

    @FXML
    void buscarLibroAutoria(ActionEvent event) {
    	
    	cargarDatosAutores(hibernateDAO.buscarAutoresDeLibros(this.txtNombreLibroAutoria.getText().toLowerCase()), this.tablaAutorias);
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
    
    /**
    cargarDatosGenerico (Generos.class, 
	    	Generos::getIdGenero,
	    	Generos::getNombre,
	    	this.tablaGeneros);
    **/
    private <T> void cargarDatosGenerico (Class<T> entityClass, 
    		Function<T, Number> idExtractor, // admite Integer, Long, int ...
    		Function<T, String> nombreExtractor,
    		TableView<GenericaDTOPropiedadesJavaFX> tablaDestino ) {
	
    	List<GenericaDTOPropiedadesJavaFX> algenericaJFX = new ArrayList<GenericaDTOPropiedadesJavaFX>(); 
    	
    	for (T item: hibernateDAO.getAll(entityClass)) {
    		GenericaDTOPropiedadesJavaFX genericaJFX = new GenericaDTOPropiedadesJavaFX((int)idExtractor.apply(item), nombreExtractor.apply(item));
    		algenericaJFX.add(genericaJFX);
    	}
    	tablaDestino.setItems(FXCollections.observableArrayList(algenericaJFX));
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

    	// Guardo en MySQL a través de hibernate
//    	hibernateDAO.anadirAutores(autores);
//    	hibernateDAO.anadirLibros(libros);
    	
    	// Establezco el origen de los datos de la columna
    	this.colGeneroLibro.setCellValueFactory(new PropertyValueFactory("nombre"));
    	//Doto a la tabla de contenido
    	/**
    	//Alternativa al método genérico más complicado pero más eficiente
    	List<GenericaDTOPropiedadesJavaFX> algenericaJFX = new ArrayList<GenericaDTOPropiedadesJavaFX>(); 
    	for (Generos genero: hibernateDAO.getAll(Generos.class)) {
    		GenericaDTOPropiedadesJavaFX genericaJFX = new GenericaDTOPropiedadesJavaFX(genero.getIdGenero(), genero.getNombre());
    		algenericaJFX.add(genericaJFX);
    	}
    	tablaGeneros.setItems(FXCollections.observableArrayList(algenericaJFX));
    	**/
    	cargarDatosGenerico (Generos.class, 
    	    	Generos::getIdGenero,
    	    	Generos::getNombre,
    	    	this.tablaGeneros);
    			
    	
//    	algenericaJFX.clear();
    	
    	colNacionalidad.setCellValueFactory(new PropertyValueFactory("nombre"));
    	cargarDatosGenerico (Nacionalidades.class, 
    			Nacionalidades::getIdNacionalidad,
    			Nacionalidades::getNombre,
    	    	this.tablaNacionalidades);
    			
    	
    	/**
    	 // Alternativa al método generico 
    	for (Nacionalidades item: hibernateDAO.getAll(Nacionalidades.class)) {
    		GenericaDTOPropiedadesJavaFX genericaJFX = new GenericaDTOPropiedadesJavaFX(item.getIdNacionalidad(), item.getNombre());
    		algenericaJFX.add(genericaJFX);
    	}
    	tablaNacionalidades.setItems(FXCollections.observableArrayList(algenericaJFX));
    	**/
    	//TODO FALTABA ESTO PARA QUE SE MOSTRARÄN LOS DATOS Y LA CONSULTA CORRECTA QUE TENGO QUE EXPLICAR
    	this.colNombreAutor.setCellValueFactory(new PropertyValueFactory("nombre"));
    	this.colMuerteAutor.setCellValueFactory(new PropertyValueFactory("muerte"));
    	this.colNacimientoAutor.setCellValueFactory(new PropertyValueFactory("nacimiento"));
    	this.colNacionalidadAutor.setCellValueFactory(new PropertyValueFactory("nacionalidad"));
    	
    	
    	
    	cargarDatosAutores(hibernateDAO.getAllAutoresConNacionalidad(), this.tablaAutores); 
    	
    	this.cmbNacionalidadAutor.getItems().addAll(hibernateDAO.getAll(Nacionalidades.class));
    	
    	this.colTituloLibro.setCellValueFactory(new PropertyValueFactory("titulo"));
    	this.colGeneroLibroEnLibros.setCellValueFactory(new PropertyValueFactory("genero"));
    	
    	cargarDatosLibros();
    	
    	//Tab Autorias
    	this.colNombreAutoria.setCellValueFactory(new PropertyValueFactory("nombre"));
    	this.colMuerteAutoria.setCellValueFactory(new PropertyValueFactory("muerte"));
    	this.colNacimientoAutoria.setCellValueFactory(new PropertyValueFactory("nacimiento"));
    	this.colNacionalidadAutoria.setCellValueFactory(new PropertyValueFactory("nacionalidad"));
	
		
	}

	private void cargarDatosLibros() {
		List<LibroDTOPropiedadesJavaFX> algenericaJFX = new ArrayList<LibroDTOPropiedadesJavaFX>(); 
    	for (Libros item: hibernateDAO.getAllLibrosConGenero()) {
    		LibroDTOPropiedadesJavaFX genericaJFX = new LibroDTOPropiedadesJavaFX(item.getIdLibro(), item.getTitulo(), item.getGeneros().getNombre()); 
    		algenericaJFX.add(genericaJFX);
    	}
    	this.tablaLibros.setItems(FXCollections.observableArrayList(algenericaJFX));
		
	}
	
	
	
	// TODO Hacer genérico
	private void cargarDatosAutores(List<Autores> algenerica, TableView<AutorDTOPropiedadesJavaFX> tabla) {
		List<AutorDTOPropiedadesJavaFX> algenericaJFX = new ArrayList<AutorDTOPropiedadesJavaFX>(); 
    	for (Autores item: algenerica) {
    		AutorDTOPropiedadesJavaFX genericaJFX = new AutorDTOPropiedadesJavaFX(item.getIdAutor(), item.getNombre(), item.getNacionalidades().getNombre(), item.getNacimiento(), 
    				item.getMuerte()!=null ? item.getMuerte() : 0); 
    		algenericaJFX.add(genericaJFX);
    	}
    	tabla.setItems(FXCollections.observableArrayList(algenericaJFX));
	}
}
   

