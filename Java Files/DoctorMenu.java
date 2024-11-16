package OOPProject;

import java.util.Scanner;

/**
 * This class represents the menu interface for the doctor in the application.
 * It allows the doctor to interact with various services related to patient care, appointments,
 * and personal schedule management.
 * 
 * The menu provides options such as viewing and updating patient medical records, 
 * managing personal schedules, and handling appointment requests.
 */
public class DoctorMenu {

    private DoctorService doctorService;

    /**
     * Constructor to initialize the {@link DoctorMenu} with a {@link DoctorService} instance.
     *
     * @param doctorService The {@link DoctorService} used to handle the actions in the menu.
     */
    public DoctorMenu(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * Displays the doctor menu and processes the user's choice.
     * Provides a variety of options such as viewing/updating patient records, setting appointment availability, 
     * accepting or declining appointments, and more.
     * 
     * The menu continues to prompt the doctor for input until they choose to log out (option 8).
     * 
     * @param doctorID The ID of the doctor who is logged in, used to personalize the actions.
     */
    public void displayMenu(String doctorID) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nDoctor Menu:");
            System.out.println("(1) View Patient Medical Records");
            System.out.println("(2) Update Patient Medical Records");
            System.out.println("(3) View Personal Schedule");
            System.out.println("(4) Set Availability for appointments");
            System.out.println("(5) Accept/Decline Appointment Requests");
            System.out.println("(6) View Upcoming Appointments");
            System.out.println("(7) Record Appointment Outcome");
            System.out.println("(8) Logout");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    doctorService.viewPatientMedicalRecords(doctorID);
                    break;
                case 2:
                    doctorService.updatePatientMedicalRecords(doctorID);
                    break;
                case 3:
                    doctorService.viewPersonalSchedule(doctorID);
                    break;
                case 4:
                    doctorService.setAvailabilityForAppointments(doctorID);
                    break;
                case 5:
                    doctorService.acceptDeclineAppointments(doctorID);
                    break;
                case 6:
                    doctorService.viewUpcomingAppointments(doctorID);
                    break;
                case 7:
                    doctorService.recordAppointmentOutcome(doctorID);
                    break;
                case 8:
                	 System.out.println("Logout");
                     main.displayLoginMenu();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (choice != 8);

        scanner.close();
    }
}
