package OOPProject;

import java.util.List;
import java.util.Scanner;

public class PatientService {
	private Scanner scanner = new Scanner(System.in);

	private AppointmentService appointmentService;
	private loadCSVClass load;

	public PatientService(AppointmentService appointmentService, loadCSVClass load) {
		this.appointmentService = appointmentService;
		this.load = load;
	}

	public void viewMedicalRecord(String username) {
		List<MedicalRecord> filteredRecords = MedicalRecord.filterByHospitalId(load.getMedicalRecords(),
				username);
	}

	public void updatePersonalInfo() {
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

	public void viewAvailableAppointments() {
		List<AppointmentSlot> availableSlots = AppointmentSlot.filterAvailableSlots(load.getAppointmentSlots());
		if (availableSlots.isEmpty()) {
			System.out.println("No schedule found");
		} else {
			for (AppointmentSlot schedule : availableSlots) {
				System.out.println(schedule.toString());
			}
		}
	}

	public void scheduleAppointment(String username) {
		System.out.println("Enter Doctor ID: ");
		String doctorID = scanner.nextLine();

		System.out.println("Enter the date for your appointment: (DD/MM/YYYY)");
		String dateOfChoice = scanner.nextLine();

		System.out.println("Enter the start time (HH:mm): ");
		String startTime = scanner.nextLine();

		System.out.println("Enter the end time (HH:mm): ");
		String endTime = scanner.nextLine();

		appointmentService.scheduleAppointment(username, doctorID, dateOfChoice, startTime, endTime);
	}

	public void rescheduleAppointment(String username) {
		System.out.println("Enter the Appointment ID to reschedule: ");
		String appointmentID = scanner.nextLine();
		
		appointmentService.cancelAppointment(username, appointmentID);

		System.out.println("Enter Doctor ID: ");
		String doctorID = scanner.nextLine();

		System.out.println("Enter the date for your appointment: (DD/MM/YYYY)");
		String dateOfChoice = scanner.nextLine();

		System.out.println("Enter the start time (HH:mm): ");
		String startTime = scanner.nextLine();

		System.out.println("Enter the end time (HH:mm): ");
		String endTime = scanner.nextLine();

		appointmentService.scheduleAppointment(username, doctorID, dateOfChoice, startTime, endTime);
	}

	public void cancelAppointment(String username) {
		System.out.println("Enter the Appointment ID to cancel: ");
		String appointmentID = scanner.nextLine();
		appointmentService.cancelAppointment(username, appointmentID);
	}

	public void viewScheduledAppointments(String username) {
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

	public void viewAppointmentOutcomeRecords(String username) {
		AppointmentOutcome.viewAppointmentOutcomeRecords(load.getAppointmentOutcomes(), username);
	}

}
