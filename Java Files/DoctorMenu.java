package OOPProject;

import java.util.Scanner;

/**
 * The DoctorMenu class provides a menu for doctors to interact with the system.
 * It allows the doctor to perform various actions such as viewing and updating
 * patient medical records, managing appointments, and setting their
 * availability.
 */
public class DoctorMenu {

	private DoctorService doctorService;

	/**
	 * Constructs a DoctorMenu with the specified DoctorService.
	 * 
	 * @param doctorService The service that provides functionality for managing
	 *                      doctor operations.
	 */
	public DoctorMenu(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	/**
	 * Displays the doctor menu with various options for the doctor to choose from.
	 * The options include viewing and updating patient records, setting
	 * availability, accepting/declining appointments, and recording appointment
	 * outcomes. The menu continues to display until the doctor logs out.
	 * 
	 * @param doctorID The unique identifier of the doctor.
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
