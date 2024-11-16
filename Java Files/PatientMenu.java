package OOPProject;

import java.util.Scanner;

public class PatientMenu {

	private PatientService patientService;

	PatientMenu(PatientService patientService) {
		this.patientService = patientService;
	}

	public static void displayPatientMenu(String username) {
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
				PatientService.viewMedicalRecord(username);
				break;
			case 2:
				PatientService.updatePersonalInfo();
				break;
			case 3:
				PatientService.viewAvailableAppointments();
				break;
			case 4:
				PatientService.scheduleAppointment(username);
				break;
			case 5:
				PatientService.rescheduleAppointment(username);
				break;
			case 6:
				PatientService.cancelAppointment(username);
				break;
			case 7:
				PatientService.viewScheduledAppointments(username);
				break;
			case 8:
				PatientService.viewAppointmentOutcomeRecords(username);
				break;
			case 9:
				System.out.println("Logging out...");

				break;
			default:
				System.out.println("Invalid choice, please try again.");
				break;
			}
		} while (choice != 9);
	}

}
