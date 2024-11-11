import java.io.*;
import java.util.*;
import java.io.BufferedReader;

public class User implements UserAuthentication {
    private String hospitalID;
    private String password;
    private Role role;
    private Gender gender;
    
	public User() {
		
	}
	
	public User(String hospitalID, String password, Role role, Gender gender) {
		this.hospitalID = hospitalID; 
		this.password = password; 
		this.role = role; 
		this.gender = gender;
	}
	
	public String login(String Username, String Password) {
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
		
	}

    public void changePassword(String hospitalID, String oldPassword, String newPassword) {
        User user = getUserFromCSV(hospitalID);
        if (user != null && user.password.equals(oldPassword)) {
            user.password = newPassword;
            updateUserInCSV(user);
            System.out.println("Password changed successfully for user: " + hospitalID);
        } else {
            System.out.println("Password change failed for user: " + hospitalID);
        }
    }

    public void forgotPassword(String hospitalID) {
        User user = getUserFromCSV(hospitalID);
        if (user != null) {
            System.out.println("Password reset process initiated for user: " + hospitalID);
        } else {
            System.out.println("User not found for hospital ID: " + hospitalID);
        }
    }

    public boolean logOut(String hospitalID) {
        User user = getUserFromCSV(hospitalID);
        if (user != null && user.isLoggedIn) {
            user.isLoggedIn = false;
            updateUserInCSV(user);
            System.out.println("User logged out successfully: " + hospitalID);
            return true;
        } else {
            System.out.println("Logout failed for user: " + hospitalID);
            return false;
        }
    }

    private User getUserFromCSV(String hospitalID) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(hospitalID)) {
                    return new User(
                            values[0],
                            values[1],
                            Role.valueOf(values[2]),
                            Gender.valueOf(values[3]),
                            Boolean.parseBoolean(values[4])
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateUserInCSV(User user) {
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(user.hospitalID)) {
                    // Update the user information in the record
                    values[1] = user.password;
                    values[2] = user.role.toString();
                    values[3] = user.gender.toString();
                    values[4] = Boolean.toString(user.isLoggedIn);
                }
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE_PATH))) {
            for (String[] record : records) {
                pw.println(String.join(",", record));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

