package OOPProject;

import java.util.List;
import java.util.Scanner;

public class PatientService {
	private static loadCSVClass load = new loadCSVClass();
	private static final Scanner scanner = new Scanner(System.in);

	public static void viewMedicalRecord(String username) {
		MedicalRecord.displayMedicalRecords(load.getMedicalRecords(), username);
	}

	public static void updatePersonalInfo() {
		System.out.println("Enter Patient ID:");
		String patientID = scanner.nextLine();

		System.out.println("Would you like to update (1)Email or (2)Contact Number?");
		int choice = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter new value:");
		String newValue = scanner.nextLine();

		if (choice == 1) {
			PatientCSV.updatePatientRecords(patientID, choice, newValue); // Update Email
		} else if (choice == 2) {
			PatientCSV.updatePatientRecords(patientID, choice, newValue); // Update Contact Number
		} else {
			System.out.println("Invalid choice. Exiting.");
		}
	}

	public static void viewAvailableAppointments() {
		List<AppointmentSlot> availableSlots = AppointmentSlot.filterAvailableSlots(load.getAppointmentSlots());
		if (availableSlots.isEmpty()) {
			System.out.println("No schedule found");
		} else {
			for (AppointmentSlot schedule : availableSlots) {
				System.out.println(schedule.toString());
			}
		}
	}

	public static void scheduleAppointment(String username) {
		System.out.println("Enter Doctor ID: ");
		String doctorID = scanner.nextLine();

		System.out.println("Enter the date for your appointment: (DD/MM/YYYY)");
		String dateOfChoice = scanner.nextLine();

		System.out.println("Enter the start time (HH:mm): ");
		String startTime = scanner.nextLine();

		System.out.println("Enter the end time (HH:mm): ");
		String endTime = scanner.nextLine();

		AppointmentCSV.scheduleAppointment(load.getAppointmentSlots(), username, doctorID, dateOfChoice, startTime,
				endTime);
	}

	public static void rescheduleAppointment(String username) {
		System.out.println("Enter the Appointment ID to reschedule: ");
		String appointmentID = scanner.nextLine();

		System.out.println("Please enter new appointment information: ");
		scheduleAppointment(username);
	}

	public static void cancelAppointment(String username) {
		System.out.println("Enter the Appointment ID to cancel: ");
		String appointmentID = scanner.nextLine();
		AppointmentCSV.cancelAppointment(username, appointmentID);
	}

	public static void viewScheduledAppointments(String username) {
		List<Appointment> scheduledAppointments = Appointment.filterScheduledAppointment(load.getAppointments(),
				username);
		if (scheduledAppointments.isEmpty()) {
			System.out.println("No schedule found");
		} else {
			for (Appointment schedule : scheduledAppointments) {
				System.out.println(schedule.toString());
			}
		}
	}

	public static void viewAppointmentOutcomeRecords(String username) {
		AppointmentOutcome.viewAppointmentOutcomeRecords(load.getAppointmentOutcomes(), username);
	}
	

}
