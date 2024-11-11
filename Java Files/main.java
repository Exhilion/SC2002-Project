package OOPProject;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
	
	private static void displayPatientMenu(String username) {
		int choice; 
		Scanner scanner = new Scanner(System.in);
        do {
        	
        	System.out.println("\nPatient Menu:");
            System.out.println("(1) View Medical Record");
            System.out.println("(2) Update Personal Info");
            System.out.println("(3) View Available Appointment");
            System.out.println("(4) Schedule an Appointment");
            System.out.println("(5) Reschedule Appointment");
            System.out.println("(6) Cancel Appointment");
            System.out.println("(7) View Scheduled Appointments"); 
            System.out.println("(8) View Appointment Outcome Records");
            System.out.println("(9) Logout");
            System.out.print("Enter your choice: ");
            
            
            
            Patient patient = null; 
        	choice = scanner.nextInt();
        	scanner.nextLine();
        	
        	switch(choice) {
        	case 1:
                // View Medical Record Record
        		patient = patient.viewPatientRecords(username);
        		System.out.println(username);
                System.out.println("Patient ID: " + patient.getUsername());
                System.out.println("Name: " + patient.getName());
                System.out.println("Date of Birth: " + patient.getDateOfBirth());
                System.out.println("Gender: " + patient.getGender());
                System.out.println("Blood Type: " + patient.getBloodType());
                System.out.println("Contact Info: " + patient.getContactInfo());
                System.out.println("Role: " + patient.getRole());
                System.out.println("Email: " + patient.getEmail());
                break;

            case 2:
                // Update Patient Personal Info
            	patient.updatePatientRecords(username);
                break;

            case 3:
                // View Available Appointment
                break;

            case 4:
                // Schedule Appointment 
                //patient.scheduleAppointment(patient, null);
                break;

            case 5:
                // Reschedule Appointment 
                //patient.rescheduleAppointment(patient, null, null);
                break;

            case 6:
                // Cancel Appointment 
                //patient.cancelAppointment(patient, null);
                break;

            case 7:
                // View Scheduled appointments
                //patient.viewAppointmentOutcomes(patient);
                break;
            case 8:
                // View Appointment outcome records
               
                break;
                
            case 9:
                // Quit the program
                System.out.println("Logout");
            	displayLoginMenu(); 
                break;

            default:
                System.out.println("Invalid choice, please try again.");
                break;
        			
        	}
        }while (choice != 9);

        scanner.close();
        
    }
	
	private static void displayDoctorMenu(String doctorID) {
		System.out.println("\nDoctor Menu:");
		System.out.println("(1) View Patient Medical Records");
		System.out.println("(2) Update Patient Medical Records");
		System.out.println("(3) View Personal Schedule");
		System.out.println("(4) Set Availability for appointments");
		System.out.println("(5) Accept or Decline Appointment Requests");
		System.out.println("(6) View Upcoming Appointments");
		System.out.println("(7) Record Appointment Outcome");
		System.out.println("(8) Logout");
		
		Doctor doctor = new Doctor();
		Scanner scanner = new Scanner(System.in);
        int choice;
        Patient patient = null; 
        String patientID = null; 
        do {
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // View Medical Record
                	System.out.println("Enter Patient ID");
                	patientID = scanner.nextLine();
                    patient = doctor.viewPatientRecords(patientID);
                    System.out.println("Patient ID: " + patient.getUsername());
                    System.out.println("Name: " + patient.getName());
                    System.out.println("Date of Birth: " + patient.getDateOfBirth());
                    System.out.println("Gender: " + patient.getGender());
                    System.out.println("Blood Type: " + patient.getBloodType());
                    System.out.println("Contact Info: " + patient.getContactInfo());
                    System.out.println("Role: " + patient.getRole());
                    break;

                case 2:
                    // Update Patient Personal Info
                	// Retrieve the details 
                	System.out.println("Enter Patient ID to update"); 
                	patientID = scanner.nextLine();
                	doctor.updatePatientInfo(patientID);
                    
                    break;

                case 3:
                    // View Available Appointment
                    break;

                case 4:
                    // Schedule Appointment 
                    //patient.scheduleAppointment(patient, null);
                    break;

                case 5:
                    // Reschedule Appointment 
                    //patient.rescheduleAppointment(patient, null, null);
                    break;

                case 6:
                    // Cancel Appointment 
                    //patient.cancelAppointment(patient, null);
                    break;

                case 7:
                    // View Appointment Outcome 
                    //patient.viewAppointmentOutcomes(patient);
                    break;
                case 8:
                    // Quit the program
                    System.out.println("Logout");
                	displayLoginMenu();
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }

        } while (choice != 8);

        scanner.close();
	}
	
	private static void displayAdminMenu(String adminID) {
		System.out.println("\nAdmin Menu:");
		System.out.println("(1) View and Manage Hospital Staff");
		System.out.println("(2) View Appointments details");
		System.out.println("(3) View and Manage Medication Inventory");
		System.out.println("(4) Approve Replenishment Requests");
		System.out.println("(5) Logout");
		
		Administrator admin = new Administrator();
		Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // View and Manage Hospital Staff
                	
                    break;

                case 2:
                    // View Appointments details
                    break;

                case 3:
                    // View and Manage Medication Inventory
                    break;

                case 4:
                    // Approve Replenishment Requests
                    
                    break;
                case 5:
                    // Quit the program
                    System.out.println("Logout");
                	displayLoginMenu();
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }

        } while (choice != 5);

        scanner.close();
	}
	
	private static void displayPharmacistMenu(String adminID) {
		System.out.println("\nPharmacist Menu:");
		System.out.println("(1) View Appointment Outcome Record");
		System.out.println("(2) Update Prescription Status");
		System.out.println("(3) View Medication Inventory");
		System.out.println("(4) Submit Replenishment Request");
		System.out.println("(5) Logout");
		
		Pharmacist admin = new Pharmacist();
		Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // View appointment Outcome Record
                	
                    break;

                case 2:
                    // Update Prescription Status
                    break;

                case 3:
                    // View Medication Inventory
                    break;

                case 4:
                    // Submit Replenishment Request
                    
                    break;
                case 5:
                    // Quit the program
                    System.out.println("Logout");
                	displayLoginMenu();
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }

        } while (choice != 5);

        scanner.close();
	}
	
	
	
	private static void displayLoginMenu() {
        System.out.println("Login");
        System.out.println("Enter your username ");
        Scanner scanner = new Scanner (System.in);
        String username = scanner.nextLine(); 
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        User tempuser = new User();
        //Verify username and password 
        String result = tempuser.login(username, password); 
        if(result == "DR") {
        	displayDoctorMenu(username);
        }
        else if (result == "PH"){
        	//displayPHMenu
        	displayPharmacistMenu(username);
        }
        else if (result == "AD") {
        	//displayADMenu
        	displayAdminMenu(username);
        }
        else if (result == "PT") {
        	//displayPTMenu
        	displayPatientMenu(username);
        }
        else {
        	System.out.println("Invalid Login");
        	displayLoginMenu();
        }
    }
	
	public static void main(String[] args) {
		displayLoginMenu();
		
	}
}
