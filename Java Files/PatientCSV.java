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
import java.util.UUID;

public class PatientCSV {
	
	// Method to load patients from CSV
	public List<Patient> viewPatientRecords() {
	    List<Patient> patients = new ArrayList<>();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/DD/yyyy");

	    try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PATIENT_FILE_PATH))) {
	        String line;
	        br.readLine(); // Skip the header line
	        while ((line = br.readLine()) != null) {
	            String[] values = line.split(",");
	            if (values.length == 10) { // Adjusted to 9 instead of 8
	                try {
	                    // Parse the values and convert to appropriate types
	                    String hospitalID = values[0];
	                    String password = values[1];
	                    Role role = Role.valueOf(values[2].toUpperCase()); // Enum conversion
	                    Gender gender = Gender.valueOf(values[3].toUpperCase()); // Enum conversion
	                    String name = values[4];
	                    Date dateOfBirth = dateFormat.parse(values[5]);
	                    BloodType bloodType = BloodType.valueOf(values[6].toUpperCase()); // Enum conversion
	                    String phoneNumber = values[7];
	                    String email = values[8];
	                    Boolean firstTimeLogin = Boolean.parseBoolean(values[9].trim());


	                    // Create Patient object and add to list
	                    Patient patient = new Patient(hospitalID, password, role, gender,
	                                                  name, dateOfBirth, bloodType,
	                                                  phoneNumber, email,firstTimeLogin);
	                    patients.add(patient);
	                } catch (Exception e) {
	                    System.out.println("Error processing record: " + line + " | " + e.getMessage());
	                }
	            } else {
	                System.out.println("Invalid record format: " + line);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return patients;
	}



	
	public static void updatePatientRecords(String patientID) {
		System.out.println("Would you like to update (1)Email or (2)Contact Number?");
		Scanner scanner = new Scanner(System.in);
		int choice;
		choice = scanner.nextInt();
		scanner.nextLine();
    	boolean valid = false;
    	//String path = "src\\\\OOPProject\\\\Patient.csv";
		String dateFormat = "MM/DD/yyyy";
    	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    	List<String[]> records = new ArrayList<>();
    	
		do{
			if(choice == 1) {
				
				System.out.println("Enter new Email");
				String newEmail = scanner.nextLine();
				try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PATIENT_FILE_PATH))) {
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
				try(BufferedReader br  = new BufferedReader(new FileReader(AppConfig.PATIENT_FILE_PATH))){
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
		
		try (PrintWriter pw = new PrintWriter(new FileWriter(AppConfig.PATIENT_FILE_PATH))) {
	        for (String[] record : records) {
	            pw.println(String.join(",", record));
	        }
	        System.out.println("Patient record updated successfully.");
	    } catch (IOException e) {
	        System.out.println("Error writing to the file: " + e.getMessage());
	    }

        
        
	}
	
	public static void updatePatientRecords(String patientID, int choice, String newValue) {
        List<String[]> records = new ArrayList<>();
        boolean recordUpdated = false;

        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PATIENT_FILE_PATH))) {
            String line = br.readLine(); // Read header
            records.add(line.split(","));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase(patientID)) {
                    if (choice == 1) {
                        values[8] = newValue; // Update Email
                    } else if (choice == 2) {
                        values[7] = newValue; // Update Contact Number
                    }
                    recordUpdated = true;
                }
                records.add(values);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        if (recordUpdated) {
            writeRecordsToFile(records);
        } else {
            System.out.println("Patient ID not found.");
        }
    }

    private static void writeRecordsToFile(List<String[]> records) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(AppConfig.PATIENT_FILE_PATH))) {
            for (String[] record : records) {
                pw.println(String.join(",", record));
            }
            System.out.println("Patient record updated successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
    

}
