package OOPProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The PharmacistService class contains the core business logic related to the actions
 * that a pharmacist can perform, such as viewing appointment outcomes, updating prescription
 * statuses, viewing the medication inventory, and submitting replenishment requests.
 */
public class PharmacistService {
	Scanner scanner = new Scanner(System.in);
	private static loadCSVClass load = new loadCSVClass();

	// View Appointment Outcome Record
	/**
     * Views the appointment outcome records with a status of "Pending" and prints them.
     * This is typically used by a pharmacist to review the outcomes of appointments
     * that have not yet been prescribed medication.
     */
	public void viewAppointmentOutcomeRecord() {
		List<AppointmentOutcome> pendingOutcomes = AppointmentOutcome
				.filterByPendingStatus(load.getAppointmentOutcomes());
		for (AppointmentOutcome outcome : pendingOutcomes) {
			outcome.printDetails();
		}
	}

	// Update Prescription Status
	/**
     * Updates the prescription status by allowing the pharmacist to select an appointment
     * outcome and prescribe medications.
     * 
     * It includes the process of selecting medications, checking stock, and updating the
     * quantity in the inventory.
     */
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

		int outcomeChoice = -1;
		while (outcomeChoice < 1 || outcomeChoice > pendingOutcomes.size()) {
			System.out.print("Enter the AppointmentOutcome to prescribe: ");
			if (scanner.hasNextInt()) {
				outcomeChoice = scanner.nextInt();
				scanner.nextLine();
				if (outcomeChoice < 1 || outcomeChoice > pendingOutcomes.size()) {
					System.out.println("Invalid choice. Please select a valid AppointmentOutcome.");
				}
			} else {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine(); // Consume the invalid input
			}
		}

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

			int medicationChoice = -1;
			while (medicationChoice < 0 || medicationChoice > medications.size()) {
				System.out.print("Enter the number of the medication (or 0 to finish): ");
				if (scanner.hasNextInt()) {
					medicationChoice = scanner.nextInt();
					scanner.nextLine();
					if (medicationChoice < 0 || medicationChoice > medications.size()) {
						System.out.println("Invalid choice. Please select a valid medication.");
					}
				} else {
					System.out.println("Invalid input. Please enter a number.");
					scanner.nextLine(); // Consume the invalid input
				}
			}

			if (medicationChoice == 0) {
				break;
			}

			Medication selectedMedication = medications.get(medicationChoice - 1);

			// Check if the medication has already been prescribed
			if (prescribedMedications.contains(selectedMedication)) {
				System.out.println("This medication has already been prescribed. Choose another.");
				continue;
			}

			int quantity = -1;
			while (quantity <= 0 || quantity > selectedMedication.getQuantity()) {
				System.out.print("Enter the quantity to prescribe: ");
				if (scanner.hasNextInt()) {
					quantity = scanner.nextInt();
					scanner.nextLine();
					if (quantity <= 0) {
						System.out.println("Quantity must be greater than 0.");
					} else if (quantity > selectedMedication.getQuantity()) {
						System.out.println(
								"Not enough stock available. Available quantity: " + selectedMedication.getQuantity());
					}
				} else {
					System.out.println("Invalid input. Please enter a valid quantity.");
					scanner.nextLine(); // Consume the invalid input
				}
			}

			selectedMedication.reduceQuantity(quantity);
			prescribedMedications.add(selectedMedication);
			prescribedQuantities.add(quantity);
			MedicationCSV medicationCSV = new MedicationCSV();
			medicationCSV.updateMedicationQuantity(selectedMedication.getMedicineID(),
					selectedMedication.getQuantity());

			System.out.println("Added " + quantity + " units of " + selectedMedication.getMedicineName()
					+ " to the prescription.");

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
	}

	// View Medication Inventory
	/**
     * Displays the current medication inventory and prints details of all available medications.
     */
	public void viewMedicationInventory() {
		List<Medication> medications = load.getMedications();
		for (Medication medication : medications) {
			medication.printDetails();
		}
	}

	// Submit Replenishment Request
	/**
     * Submits a replenishment request for medications that are low in stock.
     * The pharmacist can select medications to request replenishment for.
     * 
     * This method processes the request for low-stock medications and updates their
     * status accordingly.
     */
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
