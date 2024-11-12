package OOPProject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader; 
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

public class User{
	private String hospitalID;
    private String password;
    private Role role;
    private Gender gender;
    
    

	public User() {
		
	}
	
	public User(String username, String pasword) {
		this.hospitalID = username;
		this.password = password;
	}
	
	public User(String hospitalID, String password, Role role, Gender gender) {
		this.hospitalID = hospitalID; 
		this.password = password; 
		this.role = role; 
		this.gender = gender;
	}
	
	/*public String login(String Username, String Password) {
		String role = Username.substring(0,2);
        if(role.equalsIgnoreCase("DR")) {
        	//Check against Doc database
        	String path = "src\\\\OOPProject\\\\doctors.csv";
        	//System.out.println("Current directory: " + System.getProperty("user.dir"));
        	
        	try(BufferedReader br  = new BufferedReader(new FileReader(path))){
        		String line; 
        		br.readLine();
        		
        		while((line = br.readLine()) != null) {
        			String[] values = line.split(","); 
        			if(values.length == 7) {
        				String fileUsername = values[0].trim();
        				String filePassword = values[1].trim();
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
        	String path = "src\\\\OOPProject\\\\Pharmacist.csv";
        	//System.out.println("Current directory: " + System.getProperty("user.dir"));
        	
        	try(BufferedReader br  = new BufferedReader(new FileReader(path))){
        		String line; 
        		br.readLine();
        		
        		while((line = br.readLine()) != null) {
        			String[] values = line.split(","); 
        			if(values.length == 5) {
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
        	
        	String path = "src\\\\OOPProject\\\\Admin.csv";
        	//System.out.println("Current directory: " + System.getProperty("user.dir"));
        	
        	try(BufferedReader br  = new BufferedReader(new FileReader(path))){
        		String line; 
        		br.readLine();
        		
        		while((line = br.readLine()) != null) {
        			String[] values = line.split(","); 
        			if(values.length == 5) {
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
        	String path = "src\\\\OOPProject\\\\Patient.csv";
        	//System.out.println("Current directory: " + System.getProperty("user.dir"));
        	
        	try(BufferedReader br  = new BufferedReader(new FileReader(path))){
        		String line; 
        		br.readLine();
        		
        		while((line = br.readLine()) != null) {
        			String[] values = line.split(","); 
        			if(values.length == 10) {
        				String filePatientID = values[0].trim();
        				String filePassword = values[1].trim();
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
		
	}*/
	
	public String getHospitalId() {
        return hospitalID;
    }

    public void setHospitalId(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for role
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Getter and Setter for gender
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

	
	
	
}
