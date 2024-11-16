package OOPProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DoctorService {

	private AppointmentService appointmentService;
	private AppointmentOutcomeService appointmentOutcomeService;
	private MedicalRecordService medicalRecordService;
	private AppointmentSlotService appointmentSlotService;
	private loadCSVClass load;

	// Constructor to inject the services
	public DoctorService(MedicalRecordService medicalRecordService, AppointmentService appointmentService,
			AppointmentOutcomeService appointmentOutcomeService, AppointmentSlotService appointmentSlotService, loadCSVClass load) {
		this.appointmentService = appointmentService;
		this.appointmentOutcomeService = appointmentOutcomeService;
		this.medicalRecordService = medicalRecordService;
		this.load = load;
	}

	public static void viewPatientMedicalRecords(String doctorID) {
		// Implement the logic for viewing patient medical records here
	}

	public static void updatePatientMedicalRecords(String doctorID) {
		// Implement the logic for updating patient medical records here
	}

	// View personal schedule for the doctor
	public void viewPersonalSchedule(String doctorID) {
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
					appointmentSlotService.updateAppointmentSlotBookingStatus(
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
		// Filter confirmed appointments for the given doctor
		List<Appointment> filteredAppointments = appointmentService.getConfirmedAppointments(doctorID);

		if (filteredAppointments.isEmpty()) {
			System.out.println("No confirmed appointments found.");
		} else {
			System.out.println("Patient's appointments:");
			for (Appointment appointment : filteredAppointments) {
				appointment.printAppointmentDetails();
			}

			// Select the appointment to update
			Scanner scanner = new Scanner(System.in);
			System.out.print("Select the patient to update their medical record: ");
			int selectedAppointmentIndex = scanner.nextInt() - 1; // Input is 1-indexed, so subtract 1
			scanner.nextLine(); // Consume newline

			if (selectedAppointmentIndex >= 0 && selectedAppointmentIndex < filteredAppointments.size()) {
				Appointment selectedAppointment = filteredAppointments.get(selectedAppointmentIndex);
				Patient selectedPatient = selectedAppointment.getPatient();

				if (selectedPatient != null) {
					System.out.println("Patient ID: " + selectedPatient.getHospitalId());
					List<Diagnosis> diagnoses = load.getDiagnoses();
					List<Treatment> treatments = load.getTreatments();
					List<Prescription> prescriptions = load.getPrescriptions();

					// Select diagnosis
					System.out.println("Available Diagnoses:");
					for (int i = 0; i < diagnoses.size(); i++) {
						System.out.println((i + 1) + ". " + diagnoses.get(i).getdiagnosis());
					}
					int diagnosisChoice = scanner.nextInt() - 1;
					scanner.nextLine();

					// Select treatment
					System.out.println("Available Treatments:");
					for (int i = 0; i < treatments.size(); i++) {
						System.out.println((i + 1) + ". " + treatments.get(i).gettreatment());
					}
					int treatmentChoice = scanner.nextInt() - 1;
					scanner.nextLine();

					// Select prescriptions
					System.out.println("Available Prescriptions:");
					for (int i = 0; i < prescriptions.size(); i++) {
						Prescription p = prescriptions.get(i);
						System.out.println(
								(i + 1) + ". " + p.getMedication().getMedicineName() + " - " + p.getDosage() + " mg");
					}

					// Collect multiple prescriptions
					List<Prescription> selectedPrescriptions = new ArrayList<>();
					String addMore = "y";
					while ("y".equalsIgnoreCase(addMore)) {
						System.out.print("Select a prescription by number: ");
						int prescriptionChoice = scanner.nextInt() - 1;
						scanner.nextLine(); // Consume the newline character
						if (prescriptionChoice >= 0 && prescriptionChoice < prescriptions.size()) {
							selectedPrescriptions.add(prescriptions.get(prescriptionChoice));
							System.out.println("Prescription added: "
									+ prescriptions.get(prescriptionChoice).getMedication().getMedicineName());
						} else {
							System.out.println("Invalid choice.");
						}
						System.out.print("Add another prescription? (y/n): ");
						addMore = scanner.next();
						scanner.nextLine();
					}

					// Enter consultation notes
					System.out.print("Enter consultation notes: \n");
					String consultationNotes = scanner.nextLine();

					// Create a new medical record for the patient

					String recordID = "MR" + UUID.randomUUID().toString().substring(0, 8);
					MedicalRecord newMedicalRecord = new MedicalRecord(recordID, selectedPatient,
							diagnoses.get(diagnosisChoice), treatments.get(treatmentChoice), selectedPrescriptions);

					// Add the medical record to the CSV
					medicalRecordService.addMedicalRecord(recordID, selectedPatient, diagnoses.get(diagnosisChoice),
							treatments.get(treatmentChoice), selectedPrescriptions);
					System.out.println("Medical record updated for Patient ID " + selectedPatient.getHospitalId());

					// Create the appointment outcome
					String appointmentID = selectedAppointment.getAppointmentID();
					String appointmentOutcomeID = "A" + UUID.randomUUID().toString();
					appointmentOutcomeService.addAppointmentOutcome(appointmentOutcomeID, selectedAppointment,
							newMedicalRecord, consultationNotes, PrescriptionStatus.Pending);

					// Update the appointment status
					appointmentService.updateAppointmentStatus(appointmentID, "Completed");

				} else {
					System.out.println("Invalid selection.");
				}
			} else {
				System.out.println("Invalid selection.");
			}
		}
	}

	public void logout() {
		// Implementation here for logging out if needed
	}
}
