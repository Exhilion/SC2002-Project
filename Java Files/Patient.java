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
	private Boolean firstTimeLogin;

	
	
	private static PatientCSV patientCSV  = new PatientCSV();
	
	public static void updatePatientRecords(String username) {
		patientCSV.updatePatientRecords(username);
	}
	
	public Patient(String hospitalID, String password, Role role, Gender gender, 
            String name, Date dateOfBirth, BloodType bloodType, 
            String phoneNumber, String email, Boolean firstTimeLogin) {
			 super(hospitalID, password, role, gender);
			 this.name = name;
			 this.dateOfBirth = dateOfBirth;
			 this.bloodType = bloodType;
			 this.phoneNumber = phoneNumber;
			 this.email = email;
			 this.gender = gender; 
			 this.hospitalID = hospitalID; 
			 this.role = role; 
			 this.firstTimeLogin = firstTimeLogin;
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
