package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application{

	public static void main(String[] args) {
		// TODO con la aplicación JavaFX continuaremos cuando el personal del CAU nos solucione los problemas con la VM. 
		// JavaFX NO ENTRA EN EL EXAMEN
		
		launch(args); // lanzar la aplicación JavaFX
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Cargamos la vista FXML
		Parent root = FXMLLoader.load(getClass().getResource("/Vista.fxml")); //ruta relativa a src/main/resources
		//Crear la escena
		Scene scene = new Scene(root);
		//Configurar y mostrar la ventana principal
		primaryStage.setTitle("Gestión de empresa");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
