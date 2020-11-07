package ui;

import model.DataBase;
import model.*;

import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class ControllerGUI {

	private DataBase database;
	
	public ControllerGUI() {
		database = new DataBase();
		
	}
	
	//Main scene---------------------------------------------------------------------------------------
	//Atributtes-------------------------------------------------------------------
	@FXML
    private BorderPane mainPane;
    
    //Methods----------------------------------------------------------------------
    
    @FXML
    void about(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Credits");
		alert.setContentText("Benjamin Silva \n Kevin Fernández \n Nicolas Colmenares");
		alert.showAndWait();
    }

    @SuppressWarnings("deprecation")
	@FXML
    void close(ActionEvent event) {
    	Thread.currentThread().destroy();
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
    	new Thread() {
    		@Override
    		public void run() {
    			database.saveData();
   	    	}
    	}.start();
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
    private Label labPercent;
    
    @FXML
    private Button btGenerate;
    
    @FXML
    private TextField txtPopulation;

    @FXML
    private AnchorPane animationPane;

    @FXML
    private Rectangle rectangleBlue;
    
    //Methods------------------------------------------------
    
    @FXML
    void generateData(ActionEvent event) {
    	
    	try {
    		int amount = Integer.parseInt(txtPopulation.getText());
    		if(amount>1000000000) {
    			throw new NumberFormatException();
    		}
    		
    		//Heavy Algorithm
        	new Thread() {
        		@Override
        		public void run() {
        			database.generateRegister(amount);
       	    	}
        	}.start();
        	runAnimation();
    	}catch(NumberFormatException e){
    		txtPopulation.setText("");
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setContentText("Type a positive real number less than or equal to 1000'000.000");
			alert.showAndWait();
    	}
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
    
    //Search Scene--------------------------------------------------------------------------
    //Atributtes---------------------------------------------
    
    @FXML
    private TableView<Person> table;

    @FXML
    private TableColumn<Person, String> columnCode;

    @FXML
    private TableColumn<Person, String> columnName;
    
    @FXML
    private TableColumn<Person, String> columnLastname;
    
    @FXML
    private TableColumn<Person, String> columnEdit;
    
    //Initializers-----------------------------------------------------------------------------------
    private void initializeTableResults() {
    	ObservableList<Person> persons = FXCollections.observableArrayList(database.getSuggestions());
    	table.setItems(persons);
    	columnCode.setCellValueFactory(new PropertyValueFactory<Person,String>("code"));
    	columnName.setCellValueFactory(new PropertyValueFactory<Person,String>("name"));
    	columnLastname.setCellValueFactory(new PropertyValueFactory<Person,String>("lastname"));
    }
    
    //Animation-----------------------------------------------------------------------------------------------
    public void runAnimation() {
    	
    	animationPane.setVisible(true);
    	btGenerate.setDisable(true);
    	
    	//Heavy Algorithm
    	new Thread() {
    		@Override
    		public void run() {
    			long time = System.currentTimeMillis();
    			boolean finish=false;
    			do {
    				int progress = database.getProgress();
    				double width = (progress/100) * 540;
        			
        			
        			Platform.runLater( new Thread() {
        	   			public void run() {
        	   				rectangleBlue.setWidth(width);
        	   				labPercent.setText(progress+"%");
       	    			}
            		});
        			
        			try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
        			
        			if(progress>=100) {
        				finish=true;
        			}
    			}while(!finish);
    			
    			time = (System.currentTimeMillis() - time)/1000;
    			String sTime = time%60+"s";
    			time = time/60;
    			String rTime = time + " min " + sTime;
    			
    			Platform.runLater( new Thread() {
    	   			public void run() {
    	   				animationPane.setVisible(false);
    	   				btGenerate.setDisable(false);
    	   				
    	   				Alert alert = new Alert(AlertType.INFORMATION);
    	    			alert.setTitle("Finish");
    	    			alert.setContentText("Population generated \nTime: "+rTime);
    	    			alert.showAndWait();
   	    			}
        		});
    		}
    	}.start();
    }
}
