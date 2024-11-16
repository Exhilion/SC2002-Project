package OOPProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DoctorService {
	private static loadCSVClass load = new loadCSVClass();

	private AppointmentService appointmentService;
	private AppointmentOutcomeService appointmentOutcomeService;

	// Constructor to inject the services
	public DoctorService(AppointmentService appointmentService, AppointmentOutcomeService appointmentOutcomeService) {
		this.appointmentService = appointmentService;
		this.appointmentOutcomeService = appointmentOutcomeService;
	}

	public static void viewPatientMedicalRecords(String doctorID) {
		// Implement the logic for viewing patient medical records here
	}

	public static void updatePatientMedicalRecords(String doctorID) {
		// Implement the logic for updating patient medical records here
	}

	// View personal schedule for the doctor
	public static void viewPersonalSchedule(String doctorID) {
		List<AppointmentSlot> doctorSchedule = AppointmentSlot.filterByDoctorID(load.getAppointmentSlots(), doctorID);
		System.out.println("\nSchedule:");
		if (doctorSchedule.isEmpty()) {
			System.out.println("No schedule found");
		} else {
			for (AppointmentSlot schedule : doctorSchedule) {
				System.out.println(schedule.toString());
			}
		}
	}

	// Set availability for appointments
	public void setAvailabilityForAppointments(String doctorID) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSet Availability for Appointments:");

		System.out.print("Enter Start Time (HH:mm): ");
		String startTime = scanner.nextLine();
		System.out.print("Enter End Time (HH:mm): ");
		String endTime = scanner.nextLine();
		System.out.print("Enter Date (dd/MM/yyyy): ");
		String dateStr = scanner.nextLine();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("Invalid date format. Please use 'dd/MM/yyyy'.");
			return;
		}

		if (appointmentService.addAppointmentSlot(doctorID, startTime, endTime, date)) {
			System.out.println("Appointment slot added successfully!");
		} else {
			System.out.println("Failed to add appointment slot.");
		}
	}

	// Accept or decline appointments
	public void acceptDeclineAppointments(String doctorID) {
		System.out.println("\nAccept/Decline Appointment Requests:");
		List<Appointment> pendingAppointments = appointmentService.getPendingAppointments(doctorID);

		if (pendingAppointments.isEmpty()) {
			System.out.println("No pending appointments found.");
		} else {
			for (Appointment appointment : pendingAppointments) {
				appointment.printAppointmentDetails();
				System.out.println("Do you want to accept or decline this appointment? (1: Accept, 2: Decline)");
				Scanner scanner = new Scanner(System.in);
				int response = scanner.nextInt();
				scanner.nextLine();
				String newStatus = (response == 1) ? "Confirmed" : "Cancelled";
				appointmentService.updateAppointmentStatus(appointment.getAppointmentID(), newStatus);
				if (response == 1) {
					AppointmentSlotCSV.updateAppointmentSlotBookingStatus(
							appointment.getAppointmentSlot().getAppointmentSlotID(), true);
					System.out.println("Appointment Confirmed successfully!");
				} else {
					System.out.println("Appointment Cancelled");
				}
			}
		}
	}

	// View upcoming appointments
	public void viewUpcomingAppointments(String doctorID) {
		List<Appointment> confirmedAppointments = appointmentService.getConfirmedAppointments(doctorID);
		for (Appointment appointment : confirmedAppointments) {
			appointment.printAppointmentDetails();
		}
	}

	// Record the outcome of an appointment
	public void recordAppointmentOutcome(String doctorID) {
		List<Appointment> filteredAppointments = appointmentService.getConfirmedAppointments(doctorID);

		if (filteredAppointments.isEmpty()) {
			System.out.println("No confirmed appointments found.");
		} else {
			System.out.println("Patient's appointments:");
			for (Appointment appointment : filteredAppointments) {
				appointment.printAppointmentDetails();
			}

			// Example logic to record an outcome
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter number to record outcome details (Diagnosis/ Treatment): ");
			String outcomeDetails = scanner.nextLine();

			// Record the outcome for the first confirmed appointment (you could add logic
			// for multiple appointments)
			appointmentOutcomeService.recordOutcome(filteredAppointments.get(0).getAppointmentID(), outcomeDetails);
		}
	}

	public void logout() {
		// Implementation here for logging out if needed
	}
}
