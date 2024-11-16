package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The UserCSV class handles user authentication by reading user data from CSV files.
 * It verifies the provided username and password against records in different databases
 * based on the user's role (Doctor, Pharmacist, Administrator, or Patient).
 */
public class UserCSV {
	
	/**
     * Authenticates a user by checking their username and password against the appropriate CSV file
     * based on the user's role (Doctor, Pharmacist, Administrator, or Patient).
     * 
     * @param Username The username (hospital ID) of the user attempting to log in.
     * @param Password The password of the user attempting to log in.
     * @return A string representing the role of the user ("DR" for Doctor, "PH" for Pharmacist, 
     *         "AD" for Administrator, "PT" for Patient) if authentication is successful, or an empty string if unsuccessful.
     */
	public String login(String Username, String Password) {
		String role = Username.substring(0,2);
        if(role.equalsIgnoreCase("DR")) {
        	//Check against Doc database
        	//String path = "src\\\\OOPProject\\\\doctors.csv";
        	
        	try(BufferedReader br  = new BufferedReader(new FileReader(AppConfig.DOCTOR_FILE_PATH))){
        		String line; 
        		br.readLine();
        		while((line = br.readLine()) != null) {
        			String[] values = line.split(","); 
        			if(values.length == 8) {
        				String fileUsername = values[0].trim();
        				String filePassword = values[1].trim();
        				System.out.println(fileUsername + filePassword);
        				if(fileUsername.equalsIgnoreCase(Username) && filePassword.equalsIgnoreCase(Password)) {
        					return "DR"; 
        				}
        			}
        			
        		}
        	}catch (IOException e ) {
        			System.out.println("Error Reading the file: " + e.getMessage());
        			
        		
        	}
        	return " ";
        	
        	
        }else if(role.equalsIgnoreCase("PH")) {
        	//Check against Pharma database
        	//String path = "src\\\\OOPProject\\\\Pharmacist.csv";
        	//System.out.println("Current directory: " + System.getProperty("user.dir"));
        	
        	try(BufferedReader br  = new BufferedReader(new FileReader(AppConfig.PHARMACIST_FILE_PATH))){
        		String line; 
        		br.readLine();
        		
        		while((line = br.readLine()) != null) {
        			String[] values = line.split(","); 
        			if(values.length == 6) {
        				String fileUsername = values[0].trim();
        				String filePassword = values[1].trim();
        				if(fileUsername.equalsIgnoreCase(Username) && filePassword.equalsIgnoreCase(Password)) {
        					return "PH"; 
        				}
        			}
        			
        		}
        	}catch (IOException e ) {
        			System.out.println("Error Reading the file: " + e.getMessage());
        			
        		
        	}
        	return " ";
        	
        }else if(role.equalsIgnoreCase("AD")) {
        	//Check against Admin database
        	
        	//String path = "src\\\\OOPProject\\\\Admin.csv";
        	//System.out.println("Current directory: " + System.getProperty("user.dir"));
        	
        	try(BufferedReader br  = new BufferedReader(new FileReader(AppConfig.ADMIN_FILE_PATH))){
        		String line; 
        		br.readLine();
        		
        		while((line = br.readLine()) != null) {
        			String[] values = line.split(","); 
        			if(values.length == 6) {
        				String fileUsername = values[0].trim();
        				String filePassword = values[1].trim();
        				if(fileUsername.equalsIgnoreCase(Username) && filePassword.equalsIgnoreCase(Password)) {
        					return "AD"; 
        				}
        			}
        			
        		}
        	}catch (IOException e ) {
        			System.out.println("Error Reading the file: " + e.getMessage());
        			
        		
        	}
        	return " ";
        	
        }else if(role.equalsIgnoreCase("PT")) {
        	//Check against Patient database
        	//String path = "src\\\\OOPProject\\\\Patient.csv";
        	//System.out.println("Current directory: " + System.getProperty("user.dir"));
        	
        	try(BufferedReader br  = new BufferedReader(new FileReader(AppConfig.PATIENT_FILE_PATH))){
        		String line; 
        		br.readLine();
        		while((line = br.readLine()) != null) {
        			String[] values = line.split(","); 
        			if(values.length == 10) {
        				String filePatientID = values[0].trim();
        				String filePassword = values[1].trim();
        				System.out.println(filePatientID + filePassword);
        				if(filePatientID.equalsIgnoreCase(Username) && filePassword.equalsIgnoreCase(Password)) {
        					return "PT"; 
        				}
        			}
        			
        		}
        	}catch (IOException e ) {
        			System.out.println("Error Reading the file: " + e.getMessage());
        			
        		
        	}
        	return " ";
        }
        else {
        	System.out.println("Username does not exist");
        	return " "; 
        }
		
	}
}
