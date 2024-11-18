package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserCSV {

	public String login(String Username, String Password) {
		String role = Username.substring(0,2);
		Scanner scanner = new Scanner(System.in);
		boolean isUpdated = false; // To track if an update was made
		List<String> lines = new ArrayList<>();
		String roleResult = " ";
        if(role.equalsIgnoreCase("DR")) {
        	//Check against Doc database
        	try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.DOCTOR_FILE_PATH))) {
                String line;
                lines.add(br.readLine()); // Read and store the header line

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 8) {
                        String fileUsername = values[0].trim();
                        String filePassword = values[1].trim();
                        boolean firstTimeLogin = Boolean.parseBoolean(values[7].trim());

                        if (fileUsername.equalsIgnoreCase(Username) && filePassword.equalsIgnoreCase(Password)) {
                            // Check if it's a first-time login
                            if (firstTimeLogin) {
                                System.out.println("First Time Login");
                                System.out.println("Please change your password:");
                                System.out.print("Enter new password: ");
                                String newPassword = scanner.nextLine();

                                // Update password and set firstTimeLogin to false
                                values[1] = newPassword;
                                values[7] = "false"; // Set firstTimeLogin to false
                                isUpdated = true; // Mark that an update was made
                            }
                            // Store updated or unmodified line in lines
                            lines.add(String.join(",", values)); // Add updated line
                            roleResult = "DR"; // Set roleResult instead of returning immediately
                        } else {
                            lines.add(String.join(",", values)); // Add unmodified line if username/password don't match
                        }
                    } else {
                        lines.add(line); // Add the line as is if the length doesn't match
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
            }

            // Write the updated lines back to the file if an update was made
            if (isUpdated) {
                try (PrintWriter pw = new PrintWriter(new FileWriter(AppConfig.DOCTOR_FILE_PATH))) {
                    for (String updatedLine : lines) {
                        pw.println(updatedLine);
                    }
                    System.out.println("Password updated successfully.");
                } catch (IOException e) {
                    System.out.println("Error writing to the file: " + e.getMessage());
                }
            } else if (roleResult.equals(" ")) {
                System.out.println("Invalid username or password.");
            }

        return roleResult; // Return the role after writing to the file, ensuring all updates are complete
        	
        	
        }else if(role.equalsIgnoreCase("PH")) {
        	//Check against Pharma database
        	try(BufferedReader br  = new BufferedReader(new FileReader(AppConfig.PHARMACIST_FILE_PATH))){
        		String line;
                lines.add(br.readLine());
        		
                while((line = br.readLine()) != null) {
        			String[] values = line.split(","); 
        			if(values.length == 6) {
        				String fileUsername = values[0].trim();
        				String filePassword = values[1].trim();
        				boolean firstTimeLogin = Boolean.parseBoolean(values[5].trim());
        				if (fileUsername.equalsIgnoreCase(Username) && filePassword.equalsIgnoreCase(Password)) {
                            // Check if it's a first-time login
                            if (firstTimeLogin) {
                                System.out.println("First Time Login");
                                System.out.println("Please change your password:");
                                System.out.print("Enter new password: ");
                                String newPassword = scanner.nextLine();

                                // Update password and set firstTimeLogin to false
                                values[1] = newPassword;
                                values[5] = "false"; // Set firstTimeLogin to false
                                isUpdated = true; // Mark that an update was made
                            }
                            // Store updated or unmodified line in lines
                            lines.add(String.join(",", values)); // Add updated line
                            roleResult = "PH"; // Set roleResult instead of returning immediately
                        } else {
                            lines.add(String.join(",", values)); // Add unmodified line if username/password don't match
                        }
                    } else {
                        lines.add(line); // Add the line as is if the length doesn't match
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
            }

            // Write the updated lines back to the file if an update was made
            if (isUpdated) {
                try (PrintWriter pw = new PrintWriter(new FileWriter(AppConfig.PHARMACIST_FILE_PATH))) {
                    for (String updatedLine : lines) {
                        pw.println(updatedLine);
                    }
                    System.out.println("Password updated successfully.");
                } catch (IOException e) {
                    System.out.println("Error writing to the file: " + e.getMessage());
                }
            } else if (roleResult.equals(" ")) {
                System.out.println("Invalid username or password.");
            }

        return roleResult; 
        	
        }else if(role.equalsIgnoreCase("AD")) {
        	//Check against Admin database
        	try(BufferedReader br  = new BufferedReader(new FileReader(AppConfig.ADMIN_FILE_PATH))){
        		String line;
                lines.add(br.readLine());
        		
                while((line = br.readLine()) != null) {
        			String[] values = line.split(","); 
        			if(values.length == 6) {
        				String fileUsername = values[0].trim();
        				String filePassword = values[1].trim();
        				boolean firstTimeLogin = Boolean.parseBoolean(values[5].trim());
        				if (fileUsername.equalsIgnoreCase(Username) && filePassword.equalsIgnoreCase(Password)) {
                            // Check if it's a first-time login
                            if (firstTimeLogin) {
                                System.out.println("First Time Login");
                                System.out.println("Please change your password:");
                                System.out.print("Enter new password: ");
                                String newPassword = scanner.nextLine();

                                // Update password and set firstTimeLogin to false
                                values[1] = newPassword;
                                values[5] = "false"; // Set firstTimeLogin to false
                                isUpdated = true; // Mark that an update was made
                            }
                            // Store updated or unmodified line in lines
                            lines.add(String.join(",", values)); // Add updated line
                            roleResult = "AD"; // Set roleResult instead of returning immediately
                        } else {
                            lines.add(String.join(",", values)); // Add unmodified line if username/password don't match
                        }
                    } else {
                        lines.add(line); // Add the line as is if the length doesn't match
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
            }

            // Write the updated lines back to the file if an update was made
            if (isUpdated) {
                try (PrintWriter pw = new PrintWriter(new FileWriter(AppConfig.ADMIN_FILE_PATH))) {
                    for (String updatedLine : lines) {
                        pw.println(updatedLine);
                    }
                    System.out.println("Password updated successfully.");
                } catch (IOException e) {
                    System.out.println("Error writing to the file: " + e.getMessage());
                }
            } else if (roleResult.equals(" ")) {
                System.out.println("Invalid username or password.");
            }

        return roleResult; 
        	
        }else if(role.equalsIgnoreCase("PT")) {
        	//Check against Patient database
        	try(BufferedReader br  = new BufferedReader(new FileReader(AppConfig.PATIENT_FILE_PATH))){
        		String line;
                lines.add(br.readLine());
        		while((line = br.readLine()) != null) {
        			String[] values = line.split(","); 
        			if(values.length == 10) {
        				String fileUsername = values[0].trim();
        				String filePassword = values[1].trim();
        				boolean firstTimeLogin = Boolean.parseBoolean(values[9].trim());
        				if (fileUsername.equalsIgnoreCase(Username) && filePassword.equalsIgnoreCase(Password)) {
                            // Check if it's a first-time login
                            if (firstTimeLogin) {
                                System.out.println("First Time Login");
                                System.out.println("Please change your password:");
                                System.out.print("Enter new password: ");
                                String newPassword = scanner.nextLine();

                                // Update password and set firstTimeLogin to false
                                values[1] = newPassword;
                                values[9] = "false"; // Set firstTimeLogin to false
                                isUpdated = true; // Mark that an update was made
                            }
                            // Store updated or unmodified line in lines
                            lines.add(String.join(",", values)); // Add updated line
                            roleResult = "PT"; // Set roleResult instead of returning immediately
                        } else {
                            lines.add(String.join(",", values)); // Add unmodified line if username/password don't match
                        }
                    } else {
                        lines.add(line); // Add the line as is if the length doesn't match
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
            }

            // Write the updated lines back to the file if an update was made
            if (isUpdated) {
                try (PrintWriter pw = new PrintWriter(new FileWriter(AppConfig.PATIENT_FILE_PATH))) {
                    for (String updatedLine : lines) {
                        pw.println(updatedLine);
                    }
                    System.out.println("Password updated successfully.");
                } catch (IOException e) {
                    System.out.println("Error writing to the file: " + e.getMessage());
                }
            } else if (roleResult.equals(" ")) {
                System.out.println("Invalid username or password.");
            }
        }
        return roleResult; 

		
	}
}