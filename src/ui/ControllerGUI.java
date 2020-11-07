package ui;

import model.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class ControllerGUI {

	private DataBase dataBase;
	
	public ControllerGUI() {
		dataBase = new DataBase();
	}
	
	//Main scene---------------------------------------------------------------------------------------
	//Atributtes-------------------------------------------------------------------
	@FXML
    private BorderPane mainPane;
    
    //Methods----------------------------------------------------------------------
    
    @FXML
    void about(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void openPaneToAdd(ActionEvent event) {

    }

    @FXML
    void openPaneToSearch(ActionEvent event) {

    }

    @FXML
    void saveData(ActionEvent event) {

    }

    @FXML
    void showPaneToGenerate(ActionEvent event) {

    }
    
    //
	
}
