package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Patient extends User{
	private String name; 
	private Date dateOfBirth; 
	private Gender gender; 
	private BloodType bloodType; 
	private String hospitalID; 
	private String password; 
	private Role role;
	private String phoneNumber; 
	private String email; 


	
	
	private static PatientCSV patientCSV  = new PatientCSV();
	
	public static void updatePatientRecords(String username) {
		patientCSV.updatePatientRecords(username);
	}
	
	
	public Patient(String hospitalID, String password, Role role, Gender gender, 
            String name, Date dateOfBirth, BloodType bloodType, 
            String phoneNumber, String email, Boolean firstTimeLogin) {
			 super(hospitalID, password, role, gender, firstTimeLogin);
			 this.name = name;
			 this.dateOfBirth = dateOfBirth;
			 this.bloodType = bloodType;
			 this.phoneNumber = phoneNumber;
			 this.email = email;
			 this.gender = gender; 
			 this.hospitalID = hospitalID; 
			 this.role = role; 
	}

	public String getUsername() {
		return hospitalID; 
	}
	public String getEmail() {
		return email; 
	}
	public Role getRole() {
		return role;
	}
	
	public String getName() {
		return name; 
	}
	public void setName(String name) {
		this.name = name; 
	}
	public Date getDateOfBirth() {
		return dateOfBirth; 
	}
	public void setDate(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Gender getGender() {
		return gender; 
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public BloodType getBloodType() {
		return bloodType;
	}
	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType; 
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
}