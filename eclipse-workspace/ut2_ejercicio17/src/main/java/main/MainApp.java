package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application{

	public static void main(String[] args) {
		launch(args); // lanzar la aplicación JavaFX
		
//		DepartamentoDAO dptoDAO = new DepartamentoDAOImpl();
//		DepartamentoDTO dptoDTO = new DepartamentoDTO();
//		dptoDTO.setDepNombre("Formación");
//		dptoDTO.setDepLocalidad("Murcia");
//		dptoDAO.anadirDpto(dptoDTO);
//		dptoDTO.setDepNombre("Adiestramiento");
//		dptoDTO.setDepLocalidad("Orense");
//		dptoDAO.anadirDpto(dptoDTO);
//		
//		
//		dptoDAO.eliminarDpto(2);
//		
//		dptoDTO.setDepNombre("Digitalización");
//		dptoDTO.setDepLocalidad("Santander");
//		dptoDAO.modificarDpto(3, dptoDTO);
//		
//		for (DepartamentoDTO dpto: dptoDAO.listarDptos()) {
//			System.out.println(dpto);
//		}
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Cargamos la vista FXML
		Parent root = FXMLLoader.load(getClass().getResource("/view/Vista.fxml")); //ruta relativa a src/main/resources
		
		//Crear la escena
		Scene scene = new Scene(root);
		
		//Configurar y mostrar la ventana principal
		primaryStage.setTitle("Gestión de empresa");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
