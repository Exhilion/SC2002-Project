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
	private static loadCSVClass load = new loadCSVClass();
	// Method to load patients from CSV
	public List<Patient> viewPatientRecords() {
	    List<Patient> patients = new ArrayList<>();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	    try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PATIENT_FILE_PATH))) {
	        String line;
	        br.readLine(); // Skip the header line
	        while ((line = br.readLine()) != null) {
	            String[] values = line.split(",");
	            if (values.length == 10) { // Adjusted to 9 instead of 8
	                try {
	                    // Parse the values and convert to appropriate types
	                    String hospitalID = values[0].trim();
	                    String password = values[1].trim();
	                    Role role = Role.valueOf(values[2].trim().toUpperCase()); // Enum conversion
	                    Gender gender = Gender.valueOf(values[3].trim().toUpperCase()); // Enum conversion
	                    String name = values[4].trim();
	                    Date dateOfBirth = dateFormat.parse(values[5].trim());
	                    BloodType bloodType = BloodType.valueOf(values[6].trim().toUpperCase()); // Enum conversion
	                    String phoneNumber = values[7].trim();
	                    String email = values[8].trim();
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
		String dateFormat = "dd/MM/yyyy";
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
	
	public static void scheduleAppointment(String patientID) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter Doctor ID: ");
	    String doctorID = scanner.nextLine();
	    System.out.println("Enter the date for your appointment: (DD/MM/YYYY)");
	    String dateOfChoice = scanner.nextLine();
	    System.out.println("Enter the start time: ");
	    String startTime = scanner.nextLine(); 
	    System.out.println("Enter the end time: "); 
	    String endTime = scanner.nextLine();

	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    List<AppointmentSlot> slots = load.getAppointmentSlots();  // Assuming this loads the slots from CSV
	    boolean appointmentScheduled = false;
	    String appointmentSlotID = null;

	    try {
	        Date targetDate = dateFormat.parse(dateOfChoice);

	        // Find the matching slot and mark it as booked
	        for (AppointmentSlot slot : slots) {
	            if (slot.getDoctor() != null 
	                && slot.getDoctor().getHospitalId().equals(doctorID) 
	                && dateFormat.format(slot.getDate()).equals(dateFormat.format(targetDate))
	                && !slot.isBooked()
	                && slot.getStartTime().equals(startTime)
	                && slot.getEndTime().equals(endTime)) {

	                appointmentSlotID = slot.getAppointmentSlotID();  // Save the appointmentSlotID
	                System.out.println("Appointment has been scheduled!");
	                appointmentScheduled = true;
	                break;
	            }
	        }

	        if (!appointmentScheduled) {
	            System.out.println("Appointment has not been scheduled. No available slots found.");
	            return;
	        }

	        // Use AppointmentSlotCSV to update the booking status in the CSV
	        AppointmentSlotCSV appointmentSlotCSV = new AppointmentSlotCSV();
	        boolean updateSuccess = appointmentSlotCSV.updateAppointmentSlotBookingStatus(appointmentSlotID, true);

	        if (updateSuccess) {
	            System.out.println("Booking status updated successfully in AppointmentSlot.csv.");
	            
	            // Step 3: Generate a unique AppointmentID and create a new entry in Appointment.csv
	            String appointmentID = "A" + UUID.randomUUID().toString();
	            String status = "Pending";

	            // Append the new appointment entry to Appointment.csv
	            try (PrintWriter writer = new PrintWriter(new FileWriter(AppConfig.APPOINTMENT_FILE_PATH, true))) {  // Append mode
	                writer.printf("%s,%s,%s,%s%n", appointmentID, appointmentSlotID, status, patientID);
	                System.out.println("New appointment entry added to Appointment.csv.");
	            } catch (IOException e) {
	                System.out.println("An error occurred while writing to Appointment.csv.");
	                e.printStackTrace();
	            }

	        } else {
	            System.out.println("Failed to update booking status in the AppointmentSlot.csv.");
	        }

	    } catch (ParseException e) {
	        System.out.println("Invalid date format: " + dateOfChoice);
	        e.printStackTrace();
	    }
	}
	
	public static void cancelAppointment(String patientID) {
		Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter the Appointment ID to cancel: ");
	    String appointmentID = scanner.nextLine();
	    List<String> appointmentLines = new ArrayList<>();
	    boolean appointmentFound = false;
	    String appointmentSlotID = null;

        // Read the CSV file and update the status if the appointment ID matches
        try (BufferedReader reader = new BufferedReader(new FileReader(AppConfig.APPOINTMENT_FILE_PATH))) {
            String line;
            appointmentLines.add(reader.readLine());  // Add header to the list
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(appointmentID) && values[3].equals(patientID)) {  // Assuming AppointmentID is in the first column and PatientID is in the fourth column
                    System.out.println("Cancelling appointment with ID: " + appointmentID);
                    values[2] = "Cancelled";  // Update the status to "Cancelled"
                    appointmentSlotID = values[1];  // Save the AppointmentSlotID for updating in AppointmentSlot.csv
                    appointmentFound = true;
                }
                appointmentLines.add(String.join(",", values));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the Appointment.csv file.");
            e.printStackTrace();
            return;
        }

        // Step 2: If no matching appointment was found, exit the function
        if (!appointmentFound) {
            System.out.println("No matching appointment found for Patient ID " + patientID + " with Appointment ID " + appointmentID);
            return;
        }

        // Step 3: Write updated lines back to the Appointment.csv file
        try (PrintWriter writer = new PrintWriter(new FileWriter(AppConfig.APPOINTMENT_FILE_PATH))) {
            for (String updatedLine : appointmentLines) {
                writer.println(updatedLine);
            }
            System.out.println("Appointment status updated to 'Cancelled' in the Appointment.csv.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the Appointment.csv file.");
            e.printStackTrace();
        }
        
        if (appointmentSlotID != null) {
            List<String> slotLines = new ArrayList<>();
            boolean slotFound = false;

            try (BufferedReader slotReader = new BufferedReader(new FileReader(AppConfig.APPOINTMENT_SLOT_FILE_PATH))) {
                String slotLine;
                slotLines.add(slotReader.readLine());  // Add header to the list
                while ((slotLine = slotReader.readLine()) != null) {
                    String[] slotValues = slotLine.split(",");
                    if (slotValues[0].equals(appointmentSlotID)) {  // Assuming AppointmentSlotID is in the first column
                        System.out.println("Updating isBooked status for Appointment Slot ID: " + appointmentSlotID);
                        slotValues[5] = "FALSE";  // Set isBooked to false
                        slotFound = true;
                    }
                    slotLines.add(String.join(",", slotValues));
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the AppointmentSlot.csv file.");
                e.printStackTrace();
                return;
            }

            // Step 5: Write updated lines back to the AppointmentSlot.csv file
            if (slotFound) {
                try (PrintWriter slotWriter = new PrintWriter(new FileWriter(AppConfig.APPOINTMENT_SLOT_FILE_PATH))) {
                    for (String updatedSlotLine : slotLines) {
                        slotWriter.println(updatedSlotLine);
                    }
                    System.out.println("Appointment slot isBooked status updated to 'FALSE' in the AppointmentSlot.csv.");
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to the AppointmentSlot.csv file.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("No matching appointment slot found for AppointmentSlotID " + appointmentSlotID);
            }
        }
    }
	
	public static void viewScheduledAppointments(String patientID) {
		
	}
}
	
	
