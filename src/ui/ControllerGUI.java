package ui;

import model.DataBase;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
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
    void openPaneToAdd(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("new-user.fxml"));
		loader.setController(this);
		Parent login = loader.load();
		mainPane.setCenter(login);
    }

    @FXML
    void openPaneToSearch(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
		loader.setController(this);
		Parent login = loader.load();
		mainPane.setCenter(login);
    }

    @FXML
    void saveData(ActionEvent event) {
    	
    }

    @FXML
    void openPaneToGenerate(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("generate-data-scene.fxml"));
		loader.setController(this);
		Parent login = loader.load();
		mainPane.setCenter(login);
    }
    
    //Generate Data Scene--------------------------------------------------------------------------
    //Atributtes---------------------------------------------
    
    @FXML
    private TextField txtPopulation;

    @FXML
    private AnchorPane animationPane;

    @FXML
    private Rectangle rectangleBlue;
    
    //Methods------------------------------------------------
    
    @FXML
    void generateData(ActionEvent event) {

    }
    
    //Add user Scene--------------------------------------------------------------------------
    //Atributtes---------------------------------------------
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastname;

    @FXML
    private RadioButton rbMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private TextField txtHeight;

    @FXML
    private TextField txtNationality;

    @FXML
    private DatePicker birthdate;
    
    //Methods------------------------------------------------
    
    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) {

    }
	
    //Profile Scene--------------------------------------------------------------------------
    //Atributtes---------------------------------------------

    @FXML
    private ImageView imgPhoto;
    
    //Methods------------------------------------------------
    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }
    
    //Search Scene--------------------------------------------------------------------------
    //Atributtes---------------------------------------------
    
    @FXML
    private BorderPane searchPane;

    @FXML
    private TextField txtSearch;

    @FXML
    private ListView<?> listViewCoincidences;

    @FXML
    private RadioButton rbName;

    @FXML
    private ToggleGroup criteria;

    @FXML
    private RadioButton rbLastname;

    @FXML
    private RadioButton rbFullName;

    @FXML
    private RadioButton rbCode;
    
    //Methods------------------------------------------------
    
    @FXML
    void search(ActionEvent event) {

    }
    
    //Initializers-----------------------------------------------------------------------------------
    
    
    //-----------------------------------------------------------------------------------------------
    
}
