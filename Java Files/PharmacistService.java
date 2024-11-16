package OOPProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PharmacistService {

	private static loadCSVClass load = new loadCSVClass();

	// View Appointment Outcome Record
	public void viewAppointmentOutcomeRecord() {
		List<AppointmentOutcome> pendingOutcomes = AppointmentOutcome
				.filterByPendingStatus(load.getAppointmentOutcomes());
		for (AppointmentOutcome outcome : pendingOutcomes) {
			outcome.printDetails();
		}
	}

	// Update Prescription Status
	public void updatePrescriptionStatus() {
		List<AppointmentOutcome> pendingOutcomes = AppointmentOutcome
				.filterByPendingStatus(load.getAppointmentOutcomes());

		if (pendingOutcomes.isEmpty()) {
			System.out.println("No pending appointment outcomes available for prescription.");
			return;
		}

		System.out.println("Select an AppointmentOutcome to prescribe:");
		for (int i = 0; i < pendingOutcomes.size(); i++) {
			System.out.println("(" + (i + 1) + ")");
			pendingOutcomes.get(i).printDetails();
		}

		System.out.print("Enter the AppointmentOutcome to prescribe: ");
		Scanner scanner = new Scanner(System.in);
		int outcomeChoice = scanner.nextInt();
		scanner.nextLine();

		if (outcomeChoice > 0 && outcomeChoice <= pendingOutcomes.size()) {
			AppointmentOutcome selectedOutcome = pendingOutcomes.get(outcomeChoice - 1);
			System.out.println(
					"Prescribing medication for AppointmentOutcome ID: " + selectedOutcome.getAppointmentOutcomeID());

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
					System.out.println("(" + (i + 1) + ") " + medications.get(i).getMedicineName() + " - Available: "
							+ medications.get(i).getQuantity());
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
						medicationCSV.updateMedicationQuantity(selectedMedication.getMedicineID(),
								selectedMedication.getQuantity());

						System.out.println("Added " + quantity + " units of " + selectedMedication.getMedicineName()
								+ " to the prescription.");
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

	// View Medication Inventory
	public void viewMedicationInventory() {
		List<Medication> medications = load.getMedications();
		for (Medication medication : medications) {
			medication.printDetails();
		}
	}

	// Submit Replenishment Request
	public void submitReplenishmentRequest() {
		List<Medication> allMedications = load.getMedications();
		List<Medication> lowStockMedications = new ArrayList<>();
		List<Medication> requestedMedications = new ArrayList<>(); // Track requested medications

		// Filter low-stock medications
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

			System.out.print(
					"Enter the number of the medication you would like to submit a replenishment request for (or 0 to cancel): ");
			Scanner scanner = new Scanner(System.in);
			int replenishmentChoice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

//			if (replenishmentChoice > 0 && replenishmentChoice <= lowStockMedications.size()) {
//				Medication selectedMedication = lowStockMedications.get(replenishmentChoice - 1);
//
//				// Submit replenishment request using the method from Pharmacist class
//				admin.submitReplenishmentRequest(selectedMedication);
//
//				// Move the selected medication to the requested list and remove it from the
//				// low-stock list
//				requestedMedications.add(selectedMedication);
//				lowStockMedications.remove(replenishmentChoice - 1);
//
//				// Display the replenishment requests made so far
//				if (!requestedMedications.isEmpty()) {
//					System.out.println("\nReplenishment Requested:");
//					for (Medication requested : requestedMedications) {
//						System.out.printf("- %s%n", requested.getMedicineName());
//					}
//				}
//
//				// Ask if they want to continue with another replenishment request
//				if (!lowStockMedications.isEmpty()) {
//					System.out.print("\nWould you like to send another replenishment request? (y/n): ");
//					String response = scanner.nextLine().trim().toLowerCase();
//
//					if (response.equals("n")) {
//						continueRequesting = false;
//						System.out.println("\nReturning to the Pharmacist Menu...");
//					} else if (!response.equals("y")) {
//						System.out.println("\nInvalid input, returning to the Pharmacist Menu...");
//						continueRequesting = false;
//					}
//				}
//			} else if (replenishmentChoice == 0) {
//				System.out.println("\nReplenishment request not submitted. Returning to the Pharmacist Menu...");
//				continueRequesting = false;
//			} else {
//				System.out.println("\nInvalid selection. Please try again.");
//			}
//		}
//
//		if (lowStockMedications.isEmpty()) {
//			System.out.println("\nAll low-stock medications have already been requested for replenishment.");
//		}
		}
	}
}
