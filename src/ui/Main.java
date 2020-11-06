package ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	private ControllerGUI controllerGUI;
	
	public Main() throws IOException {
		super();
		controllerGUI = new ControllerGUI();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
		loader.setController(controllerGUI);
		
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Data Base Manager");
		stage.show();
	}
}
