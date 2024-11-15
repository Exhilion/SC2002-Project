package OOPProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class main {
	private static loadCSVClass load = new loadCSVClass();

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

			switch (choice) {
			case 1:
				// View Medical Record

				List<MedicalRecord> filteredRecords = MedicalRecord.filterByHospitalId(load.getMedicalRecords(),
						username);

				System.out.println("\nAvailable Medical Records:");
				if (filteredRecords.isEmpty()) {
					System.out.println("No medical records found for the given hospital ID.");
				} else {
					for (MedicalRecord record : filteredRecords) {
						System.out.println("Medical Record ID: " + record.getRecordID());
						System.out.println("Patient Name: " + record.getPatient().getName());
						System.out.println("Diagnosis: " + record.getDiagnosis().getdiagnosis());
						System.out.println("Treatment: " + record.getTreatment().gettreatment());
						System.out.println("Prescriptions: ");
						record.getPrescriptions().forEach(prescription -> System.out
								.println("  - " + prescription.getMedication().getMedicineName()));
						System.out.println("----------------------------------------------------");
					}
				}

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
				// patient.scheduleAppointment(patient, null);
				break;

			case 5:
				// Reschedule Appointment
				// patient.rescheduleAppointment(patient, null, null);
				break;

			case 6:
				// Cancel Appointment
				// patient.cancelAppointment(patient, null);
				break;

			case 7:
				// View Scheduled appointments
				// patient.viewAppointmentOutcomes(patient);
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
		} while (choice != 9);

		scanner.close();

	}

	private static void displayDoctorMenu(String doctorID) {
		System.out.println("\nDoctor Menu:");
		System.out.println("(1) View Patient Medical Records");
		System.out.println("(2) Update Patient Medical Records");
		System.out.println("(3) View Personal Schedule");
		System.out.println("(4) Set Availability for appointments");
		System.out.println("(5) Accept/Decline Appointment Requests");
		System.out.println("(6) View Upcoming Appointments");
		System.out.println("(7) Record Appointment Outcome");
		System.out.println("(8) Logout");

		// Doctor doctor = new Doctor();
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

				// Not right fix later

				break;

			case 2:
				// Update Patient Personal Info
				// Retrieve the details
				break;

			case 3:
				// View Available Appointment

				List<AppointmentSlot> DoctorSchedule = AppointmentSlot.filterByDoctorID(load.getAppointmentSlots(),
						doctorID);

				System.out.println("\nSchedule:");
				if (DoctorSchedule.isEmpty()) {
					System.out.println("No schedule found");
				} else {
					for (AppointmentSlot schedule : DoctorSchedule) {
						System.out.println(schedule.toString());
					}
				}
				break;

			case 4:
				// Set Availability for Appointments
				AppointmentSlotCSV newslot = new AppointmentSlotCSV();
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
					break;
				}

				String AppointmentSlotID = "AS" + UUID.randomUUID().toString();
				AppointmentSlot newSlot = new AppointmentSlot(AppointmentSlotID, startTime, endTime, date, false);

				if (newslot.addAppointmentSlotToCSV(newSlot, doctorID)) {
					System.out.println("Appointment slot added successfully!");
				} else {
					System.out.println("Failed to add appointment slot.");
				}
				break;

			case 5:
				// Accept/Decline Appointment Request
				System.out.println("\nAccept/Decline Appointment Requests:");

				List<Appointment> pendingAppointments = new ArrayList<>();
				for (Appointment appointment : load.getAppointments()) {
					if (appointment.getAppointmentSlot().getDoctor().getHospitalId().equals(doctorID)
							&& "pending".equalsIgnoreCase(appointment.getStatus())) {
						pendingAppointments.add(appointment);
					}
				}
				if (pendingAppointments.isEmpty()) {
					System.out.println("No pending appointments found.");
				} else {
					for (Appointment appointment : pendingAppointments) {
						appointment.printAppointmentDetails();
						System.out
								.println("Do you want to accept or decline this appointment? (1: Accept, 2: Decline)");
						int response = scanner.nextInt();
						scanner.nextLine();
						String newStatus = (response == 1) ? "Confirmed" : "Cancelled";
						// Update appointment and slot status
						new AppointmentCSV().updateAppointmentStatus(appointment.getAppointmentID(), newStatus);
						if (response == 1) {
							new AppointmentSlotCSV().updateAppointmentSlotBookingStatus(
									appointment.getAppointmentSlot().getAppointmentSlotID(), true);
							System.out.println("Appointment Confirmed successfully!");
						} else {
							System.out.println("Appointment Cancelled");
						}
					}
				}

				break;

			case 6:
				// View Upcoming Appointment
				String status = "confirmed";
				List<Appointment> confirmedAppointments = AppointmentFilter
						.filterAppointmentsByDoctorAndStatus(load.getAppointments(), doctorID, status);
				// Output the filtered appointments
				for (Appointment appointment : confirmedAppointments) {
					appointment.printAppointmentDetails();
				}
				break;
			case 7:
				// Record Appointment Outcome
				String appointmentStatus = "confirmed";
				List<Appointment> filteredAppointments = AppointmentFilter
						.filterAppointmentsByDoctorAndStatus(load.getAppointments(), doctorID, appointmentStatus);

				// Output the filtered appointments and allow the doctor to select one
				if (filteredAppointments.isEmpty()) {
					System.out.println("No confirmed appointments found.");
				} else {
					System.out.println("Patients appointments:");
					for (int i = 0; i < filteredAppointments.size(); i++) {
						filteredAppointments.get(i).printAppointmentDetails();
					}

					// Select appointment
					System.out.print("Select the patient to update their medical record: ");
					int selectedAppointmentIndex = scanner.nextInt() - 1;
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
							for (int i = 0; i < diagnoses.size(); i++)
								System.out.println((i + 1) + ". " + diagnoses.get(i).getdiagnosis());
							int diagnosisChoice = scanner.nextInt() - 1;
							scanner.nextLine();

							// Select treatment
							System.out.println("Available Treatments:");
							for (int i = 0; i < treatments.size(); i++)
								System.out.println((i + 1) + ". " + treatments.get(i).gettreatment());
							int treatmentChoice = scanner.nextInt() - 1;
							scanner.nextLine();

							// Select prescriptions
							System.out.println("Available Prescriptions:");
							for (int i = 0; i < prescriptions.size(); i++) {
								Prescription p = prescriptions.get(i);
								System.out.println((i + 1) + ". " + p.getMedication().getMedicineName() + " - "
										+ p.getDosage() + " mg");
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

							MedicalRecordCSV medicalRecordCSV = new MedicalRecordCSV();
							String recordID = medicalRecordCSV.generateMedicalRecordID();
							MedicalRecord newMedicalRecord = new MedicalRecord(recordID, selectedPatient,
									diagnoses.get(diagnosisChoice), treatments.get(treatmentChoice),
									selectedPrescriptions);

							// Add the medical record to CSV
							medicalRecordCSV.addMedicalRecord(recordID, selectedPatient, diagnoses.get(diagnosisChoice),
									treatments.get(treatmentChoice), selectedPrescriptions);
							System.out.println(
									"Medical record updated for Patient ID \n" + selectedPatient.getHospitalId());

							String appointmentID = selectedAppointment.getAppointmentID();
							String appointmentOutcomeID = "A" + UUID.randomUUID().toString();
							AppointmentOutcomeCSV appointmentOutcomeCSV = new AppointmentOutcomeCSV();
							appointmentOutcomeCSV.addAppointmentOutcome(appointmentOutcomeID, selectedAppointment,
									newMedicalRecord, consultationNotes, PrescriptionStatus.Pending);
							AppointmentCSV appointmentCSV = new AppointmentCSV();
							appointmentCSV.updateAppointmentStatus(appointmentID, "Completed");

						} else {
							System.out.println("Invalid selection.");
						}
					} else {
						System.out.println("Invalid selection.");
					}
				}

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
		System.out.println("(1) View Hospital Staff");
		System.out.println("(2) Add Hospital Staff");
		System.out.println("(3) Update Hospital Staff");
		System.out.println("(4) Remove Hospital Staff");
		System.out.println("(5) View Appointments details");
		System.out.println("(6) View and Manage Medication Inventory");
		System.out.println("(7) Approve Replenishment Requests");
		System.out.println("(8) Logout");

		Administrator admin = new Administrator();
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				// View Hospital Staff

				break;
			case 2:
				// Add Hospital Staff
				StaffCSV newstaff = new StaffCSV();
				System.out.println("Select Role (1-DOCTOR, 2-PHARMACIST, 3-ADMINISTRATOR):");
				int roleChoice = scanner.nextInt();
				scanner.nextLine();
				Role role = null;
				switch (roleChoice) {
				case 1 -> role = Role.DOCTOR;
				case 2 -> role = Role.PHARMACIST;
				case 3 -> role = Role.ADMINISTRATOR;
				default -> {
					System.out.println("Invalid role selected. Exiting...");
					scanner.close();
					return;
				}
				}

				// Gender selection
				System.out.println("Select Gender (1-MALE, 2-FEMALE, 3-OTHER): ");
				int genderChoice = scanner.nextInt();
				scanner.nextLine();
				Gender gender = null;
				switch (genderChoice) {
				case 1 -> gender = Gender.MALE;
				case 2 -> gender = Gender.FEMALE;
				default -> {
					System.out.println("Invalid gender selected. Exiting...");
					scanner.close();
					return;
				}
				}

				// Common details for all roles
				System.out.print("Enter Name: ");
				String name = scanner.nextLine();

				// Generate hospital ID and password
				String hospitalID = newstaff.generateStaffId(role);
				String password = "password";
				Boolean firstTimeLogin = true;

				// Additional details based on role
				switch (role) {
				case DOCTOR -> {
					System.out.print("Enter Department: ");
					String department = scanner.nextLine();
					System.out.print("Enter Specialisation: ");
					String specialisation = scanner.nextLine();
					newstaff.addDoctor(hospitalID, password, role, gender, name, department, specialisation,
							firstTimeLogin);
				}
				case PHARMACIST -> newstaff.addPharmacist(hospitalID, password, role, gender, name, firstTimeLogin);
				case ADMINISTRATOR -> newstaff.addAdmin(hospitalID, password, role, gender, name, firstTimeLogin);
				}

				break;
			case 3:
				// Update Hospital Staff

				break;
			case 4:
				// Remove Hospital Staff

				break;

			case 5:
				// View Appointments details
				List<AppointmentOutcome> appointmentOutcomes = load.getAppointmentOutcomes();
				for(AppointmentOutcome appointmentOutcome: appointmentOutcomes)
				{
					appointmentOutcome.printAppointmentDetails();
				}
				break;

			case 6:
				// View and Manage Medication Inventory
				break;

			case 7:
				// Approve Replenishment Requests

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
				// Declare the variable only once inside the case statement without redeclaration.
				List<AppointmentOutcome> outcomesToView = AppointmentOutcomeFilter
                .filterByPendingStatus(load.getAppointmentOutcomes());

				// Use the viewAppointmentOutcomeRecords method from Pharmacist class
				admin.viewAppointmentOutcomeRecords(outcomesToView);
				break;

			case 2:
				List<AppointmentOutcome> pendingOutcomes = AppointmentOutcomeFilter
	            .filterByPendingStatus(load.getAppointmentOutcomes());

	    if (pendingOutcomes.isEmpty()) {
	        System.out.println("No pending appointment outcomes available for prescription.");
	    } else {
	        System.out.println("Select an AppointmentOutcome to prescribe:");
	        for (int i = 0; i < pendingOutcomes.size(); i++) {
	            System.out.println("(" + (i + 1) + ")");
	            pendingOutcomes.get(i).printDetails();
	        }

	        System.out.print("Enter the AppointmentOutcome to prescribe: ");
	        int outcomeChoice = scanner.nextInt();
	        scanner.nextLine();

	        if (outcomeChoice > 0 && outcomeChoice <= pendingOutcomes.size()) {
	            AppointmentOutcome selectedOutcome = pendingOutcomes.get(outcomeChoice - 1);
	            System.out.println("Prescribing medication for AppointmentOutcome ID: "
	                    + selectedOutcome.getAppointmentOutcomeID());

	            // Display available medications
	            List<Medication> medications = load.getMedications();
	            if (medications.isEmpty()) {
	                System.out.println("No medications available.");
	                return;
	            }

	            List<Medication> prescribedMedications = new ArrayList<>();
	            List<Integer> prescribedQuantities = new ArrayList<>();

	            boolean prescribing = true;
	            while (prescribing) {
	                System.out.println("Select a medication to prescribe:");
	                for (int i = 0; i < medications.size(); i++) {
	                    System.out.println("(" + (i + 1) + ") " + medications.get(i).getMedicineName()
	                            + " - Available: " + medications.get(i).getQuantity());
	                }

	                System.out.print("Enter the number of the medication (or 0 to finish): ");
	                int medicationChoice = scanner.nextInt();
	                scanner.nextLine();

	                if (medicationChoice == 0) {
	                    break;
	                }

	                if (medicationChoice > 0 && medicationChoice <= medications.size()) {
	                    Medication selectedMedication = medications.get(medicationChoice - 1);

	                    // Check if the medication has already been prescribed
	                    if (prescribedMedications.contains(selectedMedication)) {
	                        System.out.println("This medication has already been prescribed. Choose another.");
	                        continue;
	                    }

	                    System.out.print("Enter the quantity to prescribe: ");
	                    int quantity = scanner.nextInt();
	                    scanner.nextLine();

	                    if (quantity > 0 && quantity <= selectedMedication.getQuantity()) {
	                        selectedMedication.reduceQuantity(quantity);
	                        prescribedMedications.add(selectedMedication);
	                        prescribedQuantities.add(quantity);
	                        MedicationCSV medicationCSV = new MedicationCSV();
	                        medicationCSV.updateMedicationQuantity(selectedMedication.getMedicineID(), selectedMedication.getQuantity());

	                        System.out.println("Added " + quantity + " units of "
	                                + selectedMedication.getMedicineName() + " to the prescription.");
	                    } else {
	                        System.out.println("Invalid quantity.");
	                    }
	                } else {
	                    System.out.println("Invalid medication selection.");
	                }

	                System.out.print("Do you want to prescribe another medication? (y/n): ");
	                String response = scanner.nextLine();
	                prescribing = response.equalsIgnoreCase("y");
	            }

	            // Check if any medications were prescribed
	            if (!prescribedMedications.isEmpty()) {
	                // Update the status of the AppointmentOutcome
	                AppointmentOutcomeCSV outcome = new AppointmentOutcomeCSV();
	                outcome.updateAppointmentOutcomeStatus(load.getAppointmentOutcomes(),
	                        selectedOutcome.getAppointmentOutcomeID(), PrescriptionStatus.Dispensed);
	                System.out.println("Prescription successful. Medications prescribed:");

	                // Display all prescribed medications
	                for (int i = 0; i < prescribedMedications.size(); i++) {
	                    System.out.println("- " + prescribedQuantities.get(i) + " units of "
	                            + prescribedMedications.get(i).getMedicineName());
	                }
	            } else {
	                System.out.println("No medications were prescribed.");
	            }
	        } else {
	            System.out.println("Invalid AppointmentOutcome selection.");
	        }
	    }
                break;

			case 3:
				// View Medication Inventory
				List<Medication> medications = load.getMedications(); // Assuming 'load' is used to get data
				admin.checkInventory(medications);
				break;

			case 4:
			    // Submit Replenishment Request for Low-Stock Medications
			    List<Medication> allMedications = load.getMedications(); // Load all medications
			    
			    // Identify low-stock medications
			    List<Medication> lowStockMedications = new ArrayList<>();
			    List<Medication> requestedMedications = new ArrayList<>(); // Track requested medications
			    
			    for (Medication medication : allMedications) {
			        if (medication.getLowStockAlert()) {
			            lowStockMedications.add(medication);
			        }
			    }

			    boolean continueRequesting = true;

			    // Loop for multiple replenishment requests
			    while (continueRequesting && !lowStockMedications.isEmpty()) {
			        // Display low-stock medications that haven't been requested yet
			        System.out.println("\nLow Stock Medications:");
			        for (int i = 0; i < lowStockMedications.size(); i++) {
			            System.out.printf("(%d) %s%n", (i + 1), lowStockMedications.get(i).getMedicineName());
			        }

			        System.out.print("Enter the number of the medication you would like to submit a replenishment request for (or 0 to cancel): ");
			        int replenishmentChoice = scanner.nextInt();
			        scanner.nextLine(); // Consume newline

			        if (replenishmentChoice > 0 && replenishmentChoice <= lowStockMedications.size()) {
			            Medication selectedMedication = lowStockMedications.get(replenishmentChoice - 1);
			            
			            // Submit replenishment request using the method from Pharmacist class
			            admin.submitReplenishmentRequest(selectedMedication);
			            
			            // Move the selected medication to the requested list and remove it from low-stock list
			            requestedMedications.add(selectedMedication);
			            lowStockMedications.remove(replenishmentChoice - 1);

			            // Display the replenishment requests made so far
			            if (!requestedMedications.isEmpty()) {
			                System.out.println("\nReplenishment Requested:");
			                for (Medication requested : requestedMedications) {
			                    System.out.printf("- %s%n", requested.getMedicineName());
			                }
			            }

			            // Ask if they want to continue with another replenishment request
			            if (!lowStockMedications.isEmpty()) {
			                System.out.print("\nWould you like to send another replenishment request? (y/n): ");
			                String response = scanner.nextLine().trim().toLowerCase();

			                if (response.equals("n")) {
			                    continueRequesting = false;
			                    System.out.println("\nReturning to the Pharmacist Menu...");
			                } else if (!response.equals("y")) {
			                    System.out.println("\nInvalid input, returning to the Pharmacist Menu...");
			                    continueRequesting = false;
			                }
			            }
			        } else if (replenishmentChoice == 0) {
			            System.out.println("\nReplenishment request not submitted. Returning to the Pharmacist Menu...");
			            continueRequesting = false;
			        } else {
			            System.out.println("\nInvalid selection. Please try again.");
			        }
			    }

			    if (lowStockMedications.isEmpty()) {
			        System.out.println("\nAll low-stock medications have already been requested for replenishment.");
			    }
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
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		System.out.println("Enter your password");
		String password = scanner.nextLine();
		UserCSV tempuser = new UserCSV();

		// Verify username and password
		String result = tempuser.login(username, password);
		if (result == "DR") {
			displayDoctorMenu(username);
		} else if (result == "PH") {
			// displayPHMenu
			displayPharmacistMenu(username);
		} else if (result == "AD") {
			// displayADMenu
			displayAdminMenu(username);
		} else if (result == "PT") {
			// displayPTMenu
			displayPatientMenu(username);
		} else {
			System.out.println("Invalid Login");
			displayLoginMenu();
		}
	}

	public static void main(String[] args) {
		displayLoginMenu();

	}
}
