package ui;

import model.DataBase;
import model.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

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
        			try {
						database.generateRegister(amount);
					} catch (IOException e) {
						e.printStackTrace();
					}
       	    	}
        	}.start();
        	
        	runAnimation();
        	
        	Alert alert2 = new Alert(AlertType.CONFIRMATION, "Do you really want to add this population?", ButtonType.YES, ButtonType.NO);
			alert2.setTitle("Alert");
			
	        ButtonType result = alert2.showAndWait().orElse(ButtonType.NO);
	        
	        if (ButtonType.YES.equals(result)) {
	           database.addPopulation();
	        }
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
    	char gender = rbMale.isSelected()? Person.MALE: Person.FEMALE;
    	try {
    		int height = Integer.parseInt(txtHeight.getText());
    		String name = txtName.getText();
        	String lastname = txtLastname.getText();
        	String nationality = txtNationality.getText();
        	LocalDate birthdate = this.birthdate.getValue();
        	
        	if((birthdate==null || name.equals("") || (lastname.equals("")|| nationality.equals("")))){
        		throw new Exception("There are empty fields");
        	}
        	
        	database.addPerson(name, lastname, gender, height, nationality, birthdate);
    	}catch(NumberFormatException e) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warnign");
			alert.setContentText("Type the height in cm");
			alert.showAndWait();
    	} catch (Exception e) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warnign");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
		loader.setController(this);
		Parent login = loader.load();
		mainPane.setCenter(login);
    }
	
    //Profile Scene--------------------------------------------------------------------------
    //Atributtes---------------------------------------------

    @FXML
    private TextField txtCode;
    
    @FXML
    private ImageView imgPhoto;
    
    //Methods------------------------------------------------
    @FXML
    void delete(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure of deleted this person?", ButtonType.YES, ButtonType.NO);
		alert.setTitle("Alert");
		
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        
        if (ButtonType.YES.equals(result)) {
           database.deletePerson();
        }
    }

    @FXML
    void save(ActionEvent event) {
    	char gender = rbMale.isSelected()? Person.MALE: Person.FEMALE;
    	try {
    		int height = Integer.parseInt(txtHeight.getText());
    		String name = txtName.getText();
        	String lastname = txtLastname.getText();
        	String nationality = txtNationality.getText();
        	LocalDate birthdate = this.birthdate.getValue();
        	
        	if((birthdate==null || name.equals("") || (lastname.equals("")|| nationality.equals("")))){
        		throw new Exception("There are empty fields");
        	}
        	
        	database.updatePerson(database.getSelectedPerson().getCode(),  name, lastname, gender, height, nationality, birthdate, null);
    	}catch(NumberFormatException e) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warnign");
			alert.setContentText("Type the height in cm");
			alert.showAndWait();
    	} catch (Exception e) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warnign");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
    }
    
    //Search Scene--------------------------------------------------------------------------
    //Atributtes---------------------------------------------
    
    @FXML
    private BorderPane searchPane;

    @FXML
    private TextField txtSearch;

    @FXML
    private ListView<String> listViewCoincidences;

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
    void showCoincidences(InputMethodEvent event) {
    	char c;
    	String text = txtSearch.getText();
    	if(rbCode.isSelected()) {
    		c = DataBase.TREE_CODE;
    	}else if(rbName.isSelected()) {
    		c = DataBase.TREE_NAME;
    	}else if(rbLastname.isSelected()) {
    		c = DataBase.TREE_LASTNAME;
    	}else {
    		c = DataBase.TREE_FULLNAME;
    	}
    	
    	//Heavy Algorithm
    	new Thread() {
    		@Override
    		public void run() {
    			database.searchCoincidences(text, c);
    			
    			ArrayList<Person> coincidences = database.getCoincidences();
        		if(coincidences.size()<=20) {
        			showListView();
        		}else {
        			
        			FXMLLoader loader = new FXMLLoader(getClass().getResource("table.fxml"));
        			loader.setController(this);
        			Parent login;
					try {
						login = loader.load();
						searchPane.setCenter(login);
					} catch (IOException e) {
						e.printStackTrace();
					}
        			
        			initializeTableResults();
        		}
   	    	}
    	}.start();
    	
    }
    
    @FXML
    void selectPerson(MouseEvent event) {
    	try {
    		int indexSelected = listViewCoincidences.getSelectionModel().getSelectedIndex();
        	database.selectPerson(indexSelected);
        	listViewCoincidences.setVisible(false);
        	txtSearch.setText(listViewCoincidences.getSelectionModel().getSelectedItem());
    	}catch(NullPointerException e) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warnign");
			alert.setContentText("Error del Sistema");
			alert.showAndWait();
    	}	
    }
    
    @FXML
    void search(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
		loader.setController(this);
		Parent login = loader.load();
		mainPane.setCenter(login);
		initializeFields();
    }
    
    //Table Scene--------------------------------------------------------------------------
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
    	ObservableList<Person> persons = FXCollections.observableArrayList(database.getCoincidences());
    	table.setItems(persons);
    	columnCode.setCellValueFactory(new PropertyValueFactory<Person,String>("code"));
    	columnName.setCellValueFactory(new PropertyValueFactory<Person,String>("name"));
    	columnLastname.setCellValueFactory(new PropertyValueFactory<Person,String>("lastname"));
    	addButtonToTable();
    }
    
    private void showListView() {
    	
    	listViewCoincidences.setVisible(true);
    	ObservableList<String> keys = FXCollections.observableArrayList(database.getCoincidentsKeys());
    	listViewCoincidences.setItems(keys);
    	listViewCoincidences.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	
	}
    
    private void initializeFields(){
    	Person person = database.getSelectedPerson();
    	txtCode.setText(person.getCode());
    	txtName.setText(person.getName());
    	txtLastname.setText(person.getLastName());
    	txtNationality.setText(person.getNationality());
    	if(person.getGender()==Person.FEMALE) {
    		rbFemale.setSelected(true);
    		rbMale.setSelected(false);
    	}
    	txtHeight.setText(person.getHeight()+"");
    	txtNationality.setText(person.getNationality());
    	birthdate.setValue(person.getBirthday());
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void addButtonToTable() {
        TableColumn<Person, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cellFactory = new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
            @Override
            public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
                final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

                    private final Button btn = new Button("Action");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	Person person = getTableView().getItems().get(getIndex());
                        	database.setSelectedPerson(person);
                            
                        	FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
                    		loader.setController(this);
                    		Parent login;
							try {
								login = loader.load();
								mainPane.setCenter(login);
								initializeFields();
							} catch (IOException e) {
								e.printStackTrace();
							}
                        	
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        table.getColumns().add(colBtn);

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
        			
        			Platform.runLater( new Thread() {
        	   			public void run() {
        	   				rectangleBlue.setWidth(database.getRectangleWidth());
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
