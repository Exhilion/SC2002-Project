package OOPProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * DoctorService handles the medical record management, appointment scheduling,
 * and various doctor-related functionalities like viewing and updating patient
 * records.
 */
public class DoctorService {

	Scanner scanner = new Scanner(System.in);
	private AppointmentService appointmentService;
	private MedicalRecordService medicalRecordService;
	private loadCSVClass load;

	/**
	 * Constructor to inject the required services.
	 *
	 * @param medicalRecordService the service for handling medical records
	 * @param appointmentService   the service for handling appointments
	 * @param load                 the service for loading data from CSV files
	 */
	public DoctorService(MedicalRecordService medicalRecordService, AppointmentService appointmentService,
			loadCSVClass load) {
		this.appointmentService = appointmentService;
		this.medicalRecordService = medicalRecordService;
		this.load = load;
	}

	/**
	 * Views the medical records for a particular doctor. Prompts the doctor to
	 * select a patient from a list and displays their medical records.
	 *
	 * @param doctorID the ID of the doctor whose patients' medical records are
	 *                 being viewed
	 */
	public void viewPatientMedicalRecords(String doctorID) {

		List<Appointment> filteredAppointments = Appointment.filterAppointmentsByDoctor(load.getAppointments(),
				doctorID);

		if (filteredAppointments.isEmpty()) {
			System.out.println("No appointments found for this doctor.");
			return;
		}

		int index = 1;
		List<String> displayedPatientNames = new ArrayList<>();

		// Display the list of patients
		for (Appointment appointment : filteredAppointments) {
			Patient patient = appointment.getPatient();
			if (patient != null && !displayedPatientNames.contains(patient.getName())) {
				displayedPatientNames.add(patient.getName()); // Add the patient's name to the list
				System.out.println(index + ". PatientID: " + patient.getHospitalId() + " , " + patient.getName());
				index++;
			}
		}

		// Prompt the user to enter patient ID
		System.out.println("Please enter patientID: ");
		String selectedID = scanner.nextLine();

		// Filter medical records by hospital ID
		List<MedicalRecord> filteredRecords = MedicalRecord.filterByHospitalId(load.getMedicalRecords(), selectedID);

		if (filteredRecords.isEmpty()) {
			System.out.println("No medical records found for this patient.");
			return;
		}

		// Display each filtered medical record

		MedicalRecord.displayMedicalRecords(filteredRecords);

	}

	/**
	 * Updates the medical records for a patient. Allows the doctor to select a
	 * medical record and update the diagnosis, treatment, and prescriptions.
	 *
	 * @param doctorID the ID of the doctor who is updating the medical record
	 */
	public void updatePatientMedicalRecords(String doctorID) {

		MedicalRecordCSV medicalRecordCSV = new MedicalRecordCSV();
		String patientID = null;

		System.out.print("Enter patient ID: ");
		patientID = scanner.nextLine().trim();

		String test = patientID;

		List<Patient> patients = load.getPatients();
		List<Diagnosis> diagnoses = load.getDiagnoses();
		List<Treatment> treatments = load.getTreatments();
		List<Prescription> prescriptions = load.getPrescriptions();

		boolean patientExists = patients.stream().anyMatch(p -> p.getHospitalId().equalsIgnoreCase(test));
		if (!patientExists) {
			System.out.println("Patient ID not found. Please try again.");
			return;
		}

		List<MedicalRecord> records = medicalRecordCSV.loadMedicalRecordsFromCSV(patients, diagnoses, treatments,
				prescriptions);
		List<MedicalRecord> patientRecords = MedicalRecord.filterByHospitalId(records, patientID);

		if (patientRecords.isEmpty()) {
			System.out.println("No medical records found for the given patient ID.");
			return;
		}

		System.out.println("Select a medical record to update (enter record number):");
		for (int i = 0; i < patientRecords.size(); i++) {
			MedicalRecord record = patientRecords.get(i);
			System.out.println((i + 1) + ". ");
			System.out.println("Medical Record ID: " + record.getRecordID());
			System.out.println("Patient Name: " + record.getPatient().getName());
			System.out.println("Diagnosis: " + record.getDiagnosis().getdiagnosis());
			System.out.println("Treatment: " + record.getTreatment().gettreatment());
			record.getPrescriptions().forEach(
					prescription -> System.out.println("  - " + prescription.getMedication().getMedicineName()));
			System.out.println("----------------------------------------------------");
		}

		int recordNumber = -1;
		while (recordNumber < 1 || recordNumber > patientRecords.size()) {
			System.out.print("Enter the number of the record to update: ");
			if (scanner.hasNextInt()) {
				recordNumber = scanner.nextInt();
				scanner.nextLine();
			} else {
				System.out.println("Invalid input. Please enter a valid number.");
				scanner.next();
			}
		}

		MedicalRecord selectedRecord = patientRecords.get(recordNumber - 1);

		int diagnosisChoice = -1;
		while (diagnosisChoice < 0 || diagnosisChoice >= diagnoses.size()) {
			System.out.println("Available Diagnoses:");
			for (int i = 0; i < diagnoses.size(); i++) {
				System.out.println((i + 1) + ". " + diagnoses.get(i).getdiagnosis());
			}
			System.out.print("Select a diagnosis (1-" + diagnoses.size() + "): ");
			if (scanner.hasNextInt()) {
				diagnosisChoice = scanner.nextInt() - 1;
				scanner.nextLine();
			} else {
				System.out.println("Invalid input. Please enter a valid number.");
				scanner.next();
			}
		}

		int treatmentChoice = -1;
		while (treatmentChoice < 0 || treatmentChoice >= treatments.size()) {
			System.out.println("Available Treatments:");
			for (int i = 0; i < treatments.size(); i++) {
				System.out.println((i + 1) + ". " + treatments.get(i).gettreatment());
			}
			System.out.print("Select a treatment (1-" + treatments.size() + "): ");
			if (scanner.hasNextInt()) {
				treatmentChoice = scanner.nextInt() - 1;
				scanner.nextLine();
			} else {
				System.out.println("Invalid input. Please enter a valid number.");
				scanner.next();
			}
		}

		List<Prescription> selectedPrescriptions = new ArrayList<>();
		String addMore = "y";
		while ("y".equalsIgnoreCase(addMore)) {
			int prescriptionChoice = -1;
			while (prescriptionChoice < 0 || prescriptionChoice >= prescriptions.size()) {
				System.out.println("Available Prescriptions:");
				for (int i = 0; i < prescriptions.size(); i++) {
					Prescription p = prescriptions.get(i);
					System.out.println(
							(i + 1) + ". " + p.getMedication().getMedicineName() + " - " + p.getDosage() + " mg");
				}
				System.out.print("Select a prescription by number (1-" + prescriptions.size() + "): ");
				if (scanner.hasNextInt()) {
					prescriptionChoice = scanner.nextInt() - 1;
					scanner.nextLine();
				} else {
					System.out.println("Invalid input. Please enter a valid number.");
					scanner.next(); // Clear invalid input
					continue;
				}

				if (prescriptionChoice >= 0 && prescriptionChoice < prescriptions.size()) {
					selectedPrescriptions.add(prescriptions.get(prescriptionChoice));
					System.out.println("Prescription added: "
							+ prescriptions.get(prescriptionChoice).getMedication().getMedicineName());
				} else {
					System.out.println("Invalid choice.");
				}
			}

			System.out.print("Add another prescription? (y/n): ");
			addMore = scanner.nextLine().trim().toLowerCase();
			while (!addMore.equals("y") && !addMore.equals("n")) {
				System.out.print("Invalid input. Please enter 'y' or 'n': ");
				addMore = scanner.nextLine().trim().toLowerCase();
			}
		}

		medicalRecordService.updatePatientRecords(selectedRecord.getRecordID(), patientID,
				diagnoses.get(diagnosisChoice), treatments.get(treatmentChoice), selectedPrescriptions);

		System.out.println("Medical record updated successfully.");
	}

	/**
	 * Displays the doctor's personal schedule. This shows the doctor's availability
	 * and upcoming appointments.
	 *
	 * @param doctorID the ID of the doctor whose schedule is being viewed
	 */
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

	/**
	 * Sets the availability for a doctor to take appointments. Allows the doctor to
	 * define the start and end time for appointment slots.
	 *
	 * @param doctorID the ID of the doctor who is setting the availability
	 */
	public void setAvailabilityForAppointments(String doctorID) {
		System.out.println("\nSet Availability for Appointments:");

		String startTime = null;
		String endTime = null;
		String dateStr = null;

		while (startTime == null) {
			System.out.print("Enter Start Time (HH:mm): ");
			startTime = scanner.nextLine();
			if (!startTime.matches("\\d{2}:\\d{2}")) {
				System.out.println("Invalid time format. Please use 'HH:mm'.");
				startTime = null;
			}
		}

		while (endTime == null) {
			System.out.print("Enter End Time (HH:mm): ");
			endTime = scanner.nextLine();
			if (!endTime.matches("\\d{2}:\\d{2}")) {
				System.out.println("Invalid time format. Please use 'HH:mm'.");
				endTime = null;
			}
		}

		Date date = null;
		while (date == null) {
			System.out.print("Enter Date (dd/MM/yyyy): ");
			dateStr = scanner.nextLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
			try {
				date = dateFormat.parse(dateStr);
			} catch (ParseException e) {
				System.out.println("Invalid date format. Please use 'dd/MM/yyyy'.");
			}
		}

		String[] startTimeParts = startTime.split(":");
		String[] endTimeParts = endTime.split(":");
		int startHour = Integer.parseInt(startTimeParts[0]);
		int startMinute = Integer.parseInt(startTimeParts[1]);
		int endHour = Integer.parseInt(endTimeParts[0]);
		int endMinute = Integer.parseInt(endTimeParts[1]);

		if ((startHour > endHour) || (startHour == endHour && startMinute >= endMinute)) {
			System.out.println("Start time must be earlier than end time.");
			return;
		}

		if (appointmentService.addAppointmentSlot(doctorID, startTime, endTime, date)) {
			System.out.println("Appointment slot added successfully!");
		} else {
			System.out.println("Failed to add appointment slot.");
		}
	}

	/**
	 * Allows a doctor to accept or decline pending appointment requests. Displays
	 * each pending appointment and prompts the doctor to respond. If the doctor
	 * accepts, the appointment status is updated to "Confirmed" and the
	 * corresponding time slot is marked as booked. If the doctor declines, the
	 * appointment status is updated to "Cancelled".
	 * 
	 * @param doctorID The ID of the doctor whose appointments are being managed.
	 */
	public void acceptDeclineAppointments(String doctorID) {
		System.out.println("\nAccept/Decline Appointment Requests:");
		List<Appointment> pendingAppointments = appointmentService.getPendingAppointments(doctorID);

		if (pendingAppointments.isEmpty()) {
			System.out.println("No pending appointments found.");
		} else {
			for (Appointment appointment : pendingAppointments) {
				appointment.printAppointmentDetails();
				int response = 0;
				boolean validInput = false;

				while (!validInput) {
					System.out.println("Do you want to accept or decline this appointment? (1: Accept, 2: Decline)");

					if (scanner.hasNextInt()) {
						response = scanner.nextInt();
						scanner.nextLine();
						if (response == 1 || response == 2) {
							validInput = true;
						} else {
							System.out.println("Invalid choice! Please enter 1 to accept or 2 to decline.");
						}
					} else {
						System.out.println("Invalid input! Please enter a valid number (1 or 2).");
						scanner.nextLine();
					}
				}

				String newStatus = (response == 1) ? "Confirmed" : "Cancelled";
				appointmentService.updateAppointmentStatus(appointment.getAppointmentID(), newStatus);
				if (response == 1) {
					appointmentService.updateAppointmentSlotBookingStatus(
							appointment.getAppointmentSlot().getAppointmentSlotID(), true);
					System.out.println("Appointment Confirmed successfully!");
				} else {
					System.out.println("Appointment Cancelled");
				}
			}
		}
	}

	/**
	 * Displays the list of upcoming confirmed appointments for a doctor. This
	 * method retrieves the confirmed appointments for the specified doctor and
	 * prints the details of each one.
	 * 
	 * @param doctorID The ID of the doctor whose upcoming appointments are to be
	 *                 viewed.
	 */
	public void viewUpcomingAppointments(String doctorID) {
	    List<Appointment> confirmedAppointments = appointmentService.getConfirmedAppointments(doctorID);
	    
	    if (confirmedAppointments == null || confirmedAppointments.isEmpty()) {
	        System.out.println("No upcoming appointments found");
	        return;
	    }
	    
	    for (Appointment appointment : confirmedAppointments) {
	        appointment.printAppointmentDetails();
	    }
	}


	/**
	 * Records the outcome of an appointment for a doctor. This method allows the
	 * doctor to record the outcome of a completed appointment, such as marking it
	 * as successful or needing follow-up. The implementation for this method is not
	 * provided here.
	 * 
	 * @param doctorID The ID of the doctor for whom the appointment outcome is
	 *                 being recorded.
	 */
	public void recordAppointmentOutcome(String doctorID) {

		List<Appointment> filteredAppointments = appointmentService.getConfirmedAppointments(doctorID);

		if (filteredAppointments.isEmpty()) {
			System.out.println("No confirmed appointments found.");
		} else {
			System.out.println("Patient's appointments:");
			for (Appointment appointment : filteredAppointments) {
				appointment.printAppointmentDetails();
			}

			int selectedAppointmentIndex = -1;

			while (selectedAppointmentIndex < 0 || selectedAppointmentIndex >= filteredAppointments.size()) {
				System.out.print(
						"Select the patient to update their medical record (1-" + filteredAppointments.size() + "): ");
				while (!scanner.hasNextInt()) {
					System.out.println("Invalid input. Please enter a valid number.");
					scanner.next();
				}
				selectedAppointmentIndex = scanner.nextInt() - 1;
				scanner.nextLine();

				if (selectedAppointmentIndex < 0 || selectedAppointmentIndex >= filteredAppointments.size()) {
					System.out.println("Invalid selection. Please select a valid appointment.");
				}
			}

			Appointment selectedAppointment = filteredAppointments.get(selectedAppointmentIndex);
			Patient selectedPatient = selectedAppointment.getPatient();

			if (selectedPatient != null) {
				System.out.println("Patient ID: " + selectedPatient.getHospitalId());
				List<Diagnosis> diagnoses = load.getDiagnoses();
				List<Treatment> treatments = load.getTreatments();
				List<Prescription> prescriptions = load.getPrescriptions();

				int diagnosisChoice = -1;
				while (diagnosisChoice < 0 || diagnosisChoice >= diagnoses.size()) {
					System.out.println("Available Diagnoses:");
					for (int i = 0; i < diagnoses.size(); i++) {
						System.out.println((i + 1) + ". " + diagnoses.get(i).getdiagnosis());
					}
					System.out.print("Select a diagnosis (1-" + diagnoses.size() + "): ");
					while (!scanner.hasNextInt()) {
						System.out.println("Invalid input. Please enter a valid number.");
						scanner.next();
					}
					diagnosisChoice = scanner.nextInt() - 1;
					scanner.nextLine();

					if (diagnosisChoice < 0 || diagnosisChoice >= diagnoses.size()) {
						System.out.println("Invalid selection. Please select a valid diagnosis.");
					}
				}

				int treatmentChoice = -1;
				while (treatmentChoice < 0 || treatmentChoice >= treatments.size()) {
					System.out.println("Available Treatments:");
					for (int i = 0; i < treatments.size(); i++) {
						System.out.println((i + 1) + ". " + treatments.get(i).gettreatment());
					}
					System.out.print("Select a treatment (1-" + treatments.size() + "): ");
					while (!scanner.hasNextInt()) {
						System.out.println("Invalid input. Please enter a valid number.");
						scanner.next();
					}
					treatmentChoice = scanner.nextInt() - 1;
					scanner.nextLine();

					if (treatmentChoice < 0 || treatmentChoice >= treatments.size()) {
						System.out.println("Invalid selection. Please select a valid treatment.");
					}
				}

				List<Prescription> selectedPrescriptions = new ArrayList<>();
				String addMore = "y";
				while ("y".equalsIgnoreCase(addMore)) {
					int prescriptionChoice = -1;
					while (prescriptionChoice < 0 || prescriptionChoice >= prescriptions.size()) {
						System.out.println("Available Prescriptions:");
						for (int i = 0; i < prescriptions.size(); i++) {
							Prescription p = prescriptions.get(i);
							System.out.println((i + 1) + ". " + p.getMedication().getMedicineName() + " - "
									+ p.getDosage() + " mg");
						}
						System.out.print("Select a prescription by number (1-" + prescriptions.size() + "): ");
						while (!scanner.hasNextInt()) {
							System.out.println("Invalid input. Please enter a valid number.");
							scanner.next();
						}
						prescriptionChoice = scanner.nextInt() - 1;
						scanner.nextLine();

						if (prescriptionChoice >= 0 && prescriptionChoice < prescriptions.size()) {
							selectedPrescriptions.add(prescriptions.get(prescriptionChoice));
							System.out.println("Prescription added: "
									+ prescriptions.get(prescriptionChoice).getMedication().getMedicineName());
						} else {
							System.out.println("Invalid choice.");
						}
					}

					System.out.print("Add another prescription? (y/n): ");
					addMore = scanner.nextLine();
				}

				System.out.print("Enter consultation notes: \n");
				String consultationNotes = scanner.nextLine();

				String recordID = "MR" + UUID.randomUUID().toString().substring(0, 8);
				MedicalRecord newMedicalRecord = new MedicalRecord(recordID, selectedPatient,
						diagnoses.get(diagnosisChoice), treatments.get(treatmentChoice), selectedPrescriptions);

				medicalRecordService.addMedicalRecord(recordID, selectedPatient, diagnoses.get(diagnosisChoice),
						treatments.get(treatmentChoice), selectedPrescriptions);
				System.out.println("Medical record updated for Patient ID " + selectedPatient.getHospitalId());

				String appointmentID = selectedAppointment.getAppointmentID();
				String appointmentOutcomeID = "A" + UUID.randomUUID().toString();
				appointmentService.addAppointmentOutcome(appointmentOutcomeID, selectedAppointment, newMedicalRecord,
						consultationNotes, PrescriptionStatus.Pending);

				appointmentService.updateAppointmentStatus(appointmentID, "Completed");

			} else {
				System.out.println("Invalid selection.");
			}
		}
	}

}
