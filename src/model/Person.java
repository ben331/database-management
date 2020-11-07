package model;


import java.time.LocalDate;
import javafx.scene.image.Image;

public class Person {
	
	public static final char MALE = 'M';
	public static final char FEMALE = 'F';
	
	private String code;
	private String name;
	private String lastName;
	private char gender;
	private int height;
	private String nationality;
	private LocalDate birthday;
	private Image photo;
	
	public Person(String code, String name, String lastName, char gender, int height, String nationality,
			LocalDate birthday) {
		super();
		this.code = code;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.height = height;
		this.nationality = nationality;
		this.birthday = birthday;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public Image getPhoto() {
		return photo;
	}
	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	public String getCode() {
		return code;
	}
	
	
	
}
