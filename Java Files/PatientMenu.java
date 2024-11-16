package OOPProject;

import java.util.Scanner;

/**
 * The PatientMenu class provides the menu interface for patients to interact with
 * the system. It presents various options such as viewing medical records, scheduling
 * appointments, updating personal information, and more.
 */
public class PatientMenu {

    private PatientService patientService;

    /**
     * Constructs a PatientMenu object with the given patient service.
     * 
     * @param patientService The patient service that will be used to handle the various
     *                       operations related to the patient menu.
     */
    public PatientMenu(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Displays the patient menu and handles user input for different patient operations.
     * The menu allows the patient to view medical records, update personal information,
     * schedule appointments, and perform other actions.
     * 
     * @param username The username of the patient logged into the system.
     */
    public void displayPatientMenu(String username) {
        Scanner scanner = new Scanner(System.in);
        int choice;
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

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            case 1:
                patientService.viewMedicalRecord(username); 
                break;
            case 2:
                patientService.updatePersonalInfo(); 
                break;
            case 3:
                patientService.viewAvailableAppointments(); 
                break;
            case 4:
                patientService.scheduleAppointment(username); 
                break;
            case 5:
                patientService.rescheduleAppointment(username); 
                break;
            case 6:
                patientService.cancelAppointment(username); 
                break;
            case 7:
                patientService.viewScheduledAppointments(username); 
                break;
            case 8:
                patientService.viewAppointmentOutcomeRecords(username); 
                break;
            case 9:
                System.out.println("Logout");
                main.displayLoginMenu();
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                break;
            }
        } while (choice != 9);
        scanner.close();
    }
}
