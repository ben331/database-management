package model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Stack;

import datastructure.AVL;
import datastructure.Node;
import javafx.scene.image.Image;

import static java.lang.Double.parseDouble;

public class DataBase {

	public static final char TREE_NAME = 'N';
	public static final char TREE_LASTNAME = 'L';
	public static final char TREE_FULLNAME = 'F';
	public static final char TREE_CODE = 'C';
	public static final String FEMALE_NAMES = "/data/FemaleNames.txt";
	public static final String LAST_NAMES = "/data/LastNames";
	public static final String MALE_NAMES = "/data/MaleNames";
	public static final String CONTRY_POPULATION = "/data/PopulationOfCountries";
	public static final String AGE_PROPORTION = "/data/AgeProportion.txt";
	public static final int AGES = 5;
	public static final int COUNTRIES = 35;

	public static int DIGITS_CODE = 10;
	
	private AVL<String,Person> treeName;
	private AVL<String,Person> treeLastname;
	private AVL<String,Person> treeFullName;
	private AVL<String,Person> treeCode;
	
	private int currentCode;
	private int progress;
	private ArrayList<Person> coincidences;
	private ArrayList<String> coincidentsKeys;
	private Person selectedPerson;
	
	public DataBase() {
		currentCode = 1;
		
		treeName = new AVL<>();
		treeLastname = new AVL<>();
		treeFullName = new AVL<>();
		treeCode = new AVL<>();
		
		coincidences = new ArrayList<Person>();
		coincidentsKeys = new ArrayList<>();
		progress=0;
	}
	
	public ArrayList<Person> getCoincidences(){
		return coincidences;
	}
	
	public int getProgress() {
		return progress;
	}
	
	public Person getSelectedPerson() {
		return selectedPerson;
	}
	
	public void setSelectedPerson(Person person) {
		this.selectedPerson = person;
	}
	
	/**
	 * <b>Description:</b> genera una cantidad determinada de personas a partir de archivos de texto
	 * que contienen la informacion (con bufferedReader)<br>
	 * @param amount es la cantidad de personas que se crean<br>
	 */
	public void generateRegister(int amount) throws IOException {
		BufferedReader brFNames = new BufferedReader(new FileReader(FEMALE_NAMES));
		BufferedReader brMNames = new BufferedReader(new FileReader(MALE_NAMES));
		BufferedReader brLastNames = new BufferedReader(new FileReader(LAST_NAMES));
		BufferedReader brCountry = new BufferedReader(new FileReader(CONTRY_POPULATION));
		BufferedReader brAgeRate = new BufferedReader(new FileReader(AGE_PROPORTION));
		String[] countries = new String[COUNTRIES];
		int[] countriesAmount = new int[COUNTRIES];
		int[] minAge = new int[AGES];
		int[] maxAge = new int[AGES];
		int[] amountAge = new int[AGES];
		for(int i = 0;i < COUNTRIES;i++) {
			String[] country = brCountry.readLine().split(",");
			countries[i] = country[0];
			countriesAmount[i] = (int) Math.floor(amount * parseDouble(country[1]));
		}
		for(int i = 0;i < AGES;i++){
			String[] age = brAgeRate.readLine().split(" ");
			minAge[i] = Integer.parseInt(age[0]);
			maxAge[i] = Integer.parseInt(age[1]);
			amountAge[i] = (int) Math.floor(amount* parseDouble(age[2]));
		}
		int maleDivFemale = amount/2;
		for(int i = 0;i < maleDivFemale;i++){ //male
			String name = brMNames.readLine();
			String lastName = brLastNames.readLine();
			char gender = Person.MALE;
			int randCountry = (int) Math.floor(Math.random()*COUNTRIES);
			while(countriesAmount[randCountry]==0){
				randCountry = (int) Math.floor(Math.random()*COUNTRIES);
			}
			String country = countries[randCountry];
			countriesAmount[randCountry]-=1;
			int randAge = (int)Math.floor(Math.random()*AGES);
			while(amountAge[randAge]==0){
				randAge = (int)Math.floor(Math.random()*AGES);
			}
			int age = (int)Math.floor(Math.random()*(maxAge[randAge]-minAge[randAge])+minAge[randAge]);
			int year = 2020 - age;
			int month = (int)Math.floor(Math.random()*12+1);
			int day = 0;
			if(month==2){
				day = (int)Math.floor(Math.random()*27+1);
			}else if(month%2==0){
				if(month>7){
					day = (int)Math.floor(Math.random()*31+1);
				}else{
					day = (int)Math.floor(Math.random()*30+1);
				}
			}else{
				if(month>7){
					day = (int)Math.floor(Math.random()*30+1);
				}else{
					day = (int)Math.floor(Math.random()*31+1);
				}
			}
			LocalDate birth = LocalDate.of(day,month,year); //check Date constructor
		}
	}
	/**
	 * <b>Description:</b> agrega una nueva persona a las 4 bases de datos<br>
	 * el codigo se genera con actualCode y se avanza<br>
	 * @param name es el nombre de la persona, name != null<br>
	 * @param lastName es el apellido de la persona, lastName != null<br>
	 * @param gender es el genero de la persona, gender es un char != ' '<br>
	 * @param height es la altura de la persona, es un double que tenga sentido <br>
	 * @param nationality es la nacionalidad de la persona,nationality != null<br>
	 * @param birthday es la fecha de nacimiento de la persona, es un obejto Date ya hecho<br>
	 * @param photo es la foto de la persona, es un objeto Image ya manejado<br>
	 * <b>Output:</b> una persona que se agrega a las 4 bases de datos<br>
	 */
	public void addPerson(String name, String lastName, char gender, int height, String nationality,
			LocalDate birthday) {
		
		String code = "";
		int numberDigits = (currentCode+"").length();
		
		for(int i=numberDigits; i<DIGITS_CODE;i++) {
			code +="0";
		}
		code += numberDigits; 
		
		Person newP = new Person(code, name, lastName, gender, height, nationality, birthday);
		
		treeName.insertE(name+code, newP);
		treeLastname.insertE(lastName+code, newP);
		treeFullName.insertE(name+lastName+code, newP);
		treeCode.insertE(code, newP);
		
		
	}
	/**
	 *<b>Description:</b> actualiza la informacion de una persona en las 4 bases de datos<br>
	 * @param searchId es el id con el que realiza la busqueda en el arbol de id<br>
	 * @param searchName es el nombre con el que busca en el arbol de name<br>
	 * @param searchLastName es el apellido con el que busca en el arbol de lastName<br>
	 * @param name es el nombre actualizado, puede estar vacio en ese caso el name no se cambia<br>
	 * @param lastName es el apellido actualizado, puede estar vacio en ese caso el lastName no se cambia<br>
	 * @param gender es el genero actualizado, puede estar vacio en ese el gender no se cambia<br>
	 * @param height es la altura actualizada, puede estar vacia en ese el height no se cambia<br>
	 * @param nationality es la nacionalidad actualizada, puede estar vacia en ese caso no se cambia<br>
	 * @param birthday es la fecha de nacimiento actualizada, puede estar vacias en ese caso no se cambia<br>
	 * @param photo es la foto actualizada, puede estar vacia en ese caso no se cambia<br>
	 */

	public void updatePerson(String searchId, String name, String lastName, char gender, double height, String nationality,
			LocalDate birthday, Image photo) {
		
	}
	/**
	 * <b>Description:</b> elimina una persona en las 4 bases de datos<br>
	 * @param id es el id que se usa para eliminar en el arbol de id<br>
	 * @param n es el nombre que se usa para eliminar en el arbol de name, tambien es la primera parte para eliminar el arbol de name y lastName<br>
	 * @param l es ek apellido que se usa para eliminar en el arbol de lastName, tambien es la segunda parte para eliminar el arbol de name y lastName<br>
	 */

	public void deletePerson() {
		

		String code = selectedPerson.getCode();
		String nameKey = selectedPerson.getName()+code;
		String lastnameKey = selectedPerson.getLastName()+code;
		String fullNameKey = nameKey+lastnameKey+code;
		treeName.removeE(nameKey);
		treeLastname.removeE(lastnameKey);
		treeFullName.removeE(fullNameKey);
		treeCode.removeE(code);

		
	}
	
	
	
	/**
	 *<b>Description:</b> busca a una persona en la base de datos seleccionada<br>
	 * @param k es la clave que se usa para buscar en una base de datos<br>
	 * @param c es en cual de los 4 arboles se va a realizar la busqueda<br>
	 * @return es la persona que encontro que cumple con el parametro de k<br>
	 */
	public Person searchPerson(String k, char c) {
		
		Person personR ;

		if(c == DataBase.TREE_NAME) {
			personR = treeName.searchE(k);
		}else if(c == DataBase.TREE_LASTNAME) {
			personR = treeLastname.searchE(k);
		}else if(c == DataBase.TREE_FULLNAME) {
			personR = treeFullName.searchE(k);
		}else {
			personR = treeCode.searchE(k);
		}
		
		return personR;
		
	}
	
	/**
	 * <Description:</b> genera una lista de sugerencia con la busqueda de todos los que cumplen con un parametro de busqueda<br>
	 * @param k es la clave que se usa para buscar en una base de datos<br>
	 * @param c es en cual de los 4 arboles se va a realizar la busqueda<br>
	 * @return es una lista con todos los que cumplen con el parametro de busqueda<br>
	 */
	

	public void searchCoincidences(String text, char c){
		
		boolean found=false;
		int letters = text.length();
		String key;
		Node<String, Person> current;

		AVL<String, Person> tree;

		
		if(c==DataBase.TREE_CODE) {
			tree = treeCode;
		}else if(c==DataBase.TREE_LASTNAME) {
			tree = treeName;
		}else if(c==DataBase.TREE_FULLNAME) {
			tree = treeLastname;
		}else {
			tree = treeFullName;
		}
		
		current = tree.getRoot();
		
		while(current!=null && !found) {
			key = current.getKey().substring(0,letters-1);
			if(text.compareTo(key)<0) {
				current = current.getLeft();
			}else if(text.compareTo(key)>0) {
				current = current.getRight();
			}else {
				searchCoincidences(current, text);
			}
		}
	}
	
	private void searchCoincidences(Node<String, Person> current, String text) {
		boolean thereAreMore = true;
		Stack<Node<String,Person>> points = new Stack<>();
		int letters = text.length();
		String key;
		
		coincidences.add(current.getValue());
		points.push(current);
		
		while(!points.isEmpty() && coincidences.size()<100) {
			current = points.pop();
			thereAreMore = true;
			while((current!=null && thereAreMore) && coincidences.size()<100) {
				if(current.getLeft()!=null) {
					key = current.getLeft().getKey().substring(0, letters);
					if(key.equals(text)) {
						points.push(current.getLeft());
						coincidences.add(current.getLeft().getValue());
						coincidentsKeys.add(current.getLeft().getKey());
						current = current.getLeft();
					}else {
						thereAreMore=false;
					}
				}else {
					thereAreMore = false;
				}
			}
			
			thereAreMore = true;
			while((current!=null && thereAreMore) && coincidences.size()<100) {
				if(current.getLeft()!=null) {
					key = current.getRight().getKey().substring(0, letters);
					if(key.equals(text)) {
						points.push(current.getRight());
						coincidences.add(current.getRight().getValue());
						coincidentsKeys.add(current.getRight().getKey());
						current = current.getRight();
					}else {
						thereAreMore=false;
					}
				}else {
					thereAreMore = false;
				}
			}
		}
	}
	
	public int getRectangleWidth() {
		return progress/100 * 540;
	}
	
	public void saveData() {
		
	}

	public ArrayList<String> getCoincidentsKeys() {
		return coincidentsKeys;
	}

	public void selectPerson(int indexSelected) {
		selectedPerson = coincidences.get(indexSelected);
	}

	public void addPopulation() {
		
	}
}
