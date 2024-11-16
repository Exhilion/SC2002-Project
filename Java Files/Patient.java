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

/**
 * The Patient class represents a patient in the hospital system.
 * It extends the User class and contains personal details such as name, date of birth, gender, blood type, 
 * hospital ID, password, contact information, and role.
 * The class also includes methods for retrieving and updating patient records.
 */
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

	/**
     * Constructs a new Patient object with the specified attributes.
     * 
     * @param hospitalID The patient's unique hospital ID.
     * @param password The patient's password.
     * @param role The patient's role (e.g., patient).
     * @param gender The patient's gender.
     * @param name The patient's full name.
     * @param dateOfBirth The patient's date of birth.
     * @param bloodType The patient's blood type.
     * @param phoneNumber The patient's phone number.
     * @param email The patient's email address.
     * @param firstTimeLogin Indicates whether this is the patient's first time logging in.
     */
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

	/**
     * Gets the patient's hospital ID, which serves as their username.
     * 
     * @return The hospital ID of the patient.
     */
	public String getUsername() {
		return hospitalID; 
	}
	
	/**
     * Gets the patient's email address.
     * 
     * @return The email address of the patient.
     */
	public String getEmail() {
		return email; 
	}
	
	/**
     * Gets the patient's role.
     * 
     * @return The role of the patient.
     */
	public Role getRole() {
		return role;
	}
	
	/**
     * Gets the patient's full name.
     * 
     * @return The name of the patient.
     */
	public String getName() {
		return name; 
	}
	
	/**
     * Sets the patient's name.
     * 
     * @param name The new name of the patient.
     */
	public void setName(String name) {
		this.name = name; 
	}
	
	/**
     * Gets the patient's date of birth.
     * 
     * @return The date of birth of the patient.
     */
	public Date getDateOfBirth() {
		return dateOfBirth; 
	}
	
	/**
     * Sets the patient's date of birth.
     * 
     * @param dateOfBirth The new date of birth of the patient.
     */
	public void setDate(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
     * Gets the patient's gender.
     * 
     * @return The gender of the patient.
     */
	public Gender getGender() {
		return gender; 
	}
	
	/**
     * Sets the patient's gender.
     * 
     * @param gender The new gender of the patient.
     */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	/**
     * Gets the patient's blood type.
     * 
     * @return The blood type of the patient.
     */
	public BloodType getBloodType() {
		return bloodType;
	}
	
	/**
     * Sets the patient's blood type.
     * 
     * @param bloodType The new blood type of the patient.
     */
	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType; 
	}
	
	/**
     * Gets the patient's phone number.
     * 
     * @return The phone number of the patient.
     */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	
	
	//public MedicalRecord viewMedicalRecord(Patient patient) {
		// Logic to view medical record
		//return patient.medicalRecord; 
	//}
	
	/*public static Patient viewPatientRecords(String patientID) {
		String path = "src\\\\OOPProject\\\\Patient.csv";
    	//System.out.println("Current directory: " + System.getProperty("user.dir"));
		
    	String dateFormat = "MM/DD/yyyy";
    	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    	try(BufferedReader br  = new BufferedReader(new FileReader(path))){
    		String line; 
    		br.readLine();
    		while((line = br.readLine()) != null) {
    			String[] values = line.split(","); 
    			//System.out.println("Values length: " + values.length);
    			if(values.length >= 7) {
    				String fileUsername = values[0].trim();
    				String filePassword = values[1].trim();
    				Role fileRole = Role.valueOf(values[2].trim().toUpperCase());
    				Gender fileGender = Gender.valueOf(values[3].trim().toUpperCase());
    				String fileName = values[4].trim(); 
    				String fileDateOfBirth = values[5].trim(); 
    				BloodType fileBloodType = BloodType.valueOf(values[6].trim());
    				String fileContactInfo = values[7].trim();
    				String fileEmail = values[8].trim();
    				Date date = null;
    				try {
    					date = sdf.parse(fileDateOfBirth);
    					
    				}catch(ParseException e) {
    					System.out.println("Error parsing date: " + fileDateOfBirth);
    				}
    				
    				if(fileUsername.trim().equalsIgnoreCase(patientID.trim())) {
    					
    					return new Patient(fileUsername, filePassword, fileRole, fileGender, fileName, date, fileBloodType, fileContactInfo, fileEmail); 
    				}
    			}
    			System.out.println("Reached");
    		}
    	}catch (IOException e ) {
    			System.out.println("Error Reading the file: " + e.getMessage());
    			
    		
    	} 
    	return null; 
	}*/
	
	/*public static void updatePatientRecords(String patientID) {
		System.out.println("Would you like to update (1)Email or (2)Contact Number?");
		Scanner scanner = new Scanner(System.in);
		int choice;
		choice = scanner.nextInt();
		scanner.nextLine();
    	boolean valid = false;
    	String path = "src\\\\OOPProject\\\\Patient.csv";
		String dateFormat = "MM/DD/yyyy";
    	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    	List<String[]> records = new ArrayList<>();
    	
		do{
			if(choice == 1) {
				
				System.out.println("Enter new Email");
				String newEmail = scanner.nextLine();
				try (BufferedReader br = new BufferedReader(new FileReader(path))) {
					String line = br.readLine();  // Read header line
		            records.add(line.split(","));
		    		while((line = br.readLine())!= null) {
		    			String [] values = line.split(",");
		    			if(values[0].trim().equalsIgnoreCase(patientID)) {
		    				values[8] = newEmail;
		    				valid = true; 
		    			}
		    			records.add(values);
		    		}
		    	}catch (IOException e ) {
	    			System.out.println("Error Reading the file: " + e.getMessage());
		    	}
			}
			else if(choice == 2) {
				System.out.println("Enter new Contact Number");
				String newContactNumber = scanner.nextLine();
				try(BufferedReader br  = new BufferedReader(new FileReader(path))){
		    		String line = br.readLine();
		    		records.add(line.split(","));
		    		while((line = br.readLine())!= null) {
		    			String [] values = line.split(",");
		    			if(values[0].trim().equalsIgnoreCase(patientID)) {
		    				values[7] = newContactNumber;
		    				valid = true; 
		    			}
		    			records.add(values);
		    		}
		    	}catch (IOException e ) {
	    			System.out.println("Error Reading the file: " + e.getMessage());
		    	}
			}
			else {
				System.out.println("Invalid input try again");
			}
			
		}while(!valid);
		
		try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
	        for (String[] record : records) {
	            pw.println(String.join(",", record));
	        }
	        System.out.println("Patient record updated successfully.");
	    } catch (IOException e) {
	        System.out.println("Error writing to the file: " + e.getMessage());
	    }

        
        
	}*/
}
