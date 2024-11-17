package OOPProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Provides services for pharmacists, including managing appointment outcomes, 
 * prescriptions, and medication inventory.
 */
public class PharmacistService {
    private Scanner scanner = new Scanner(System.in);
    private loadCSVClass load;

    /**
     * Constructs a new {@code PharmacistService} with the provided CSV loader.
     *
     * @param load The {@link loadCSVClass} instance to load data from CSV files.
     */
    public PharmacistService(loadCSVClass load) {
        this.load = load;
    }

    /**
     * Displays pending appointment outcomes for the pharmacist to review.
     * Filters outcomes with a "Pending" status and displays their details.
     */
    public void viewAppointmentOutcomeRecord() {
        List<AppointmentOutcome> pendingOutcomes = AppointmentOutcome
                .filterByPendingStatus(load.getAppointmentOutcomes());
        
        if (pendingOutcomes == null || pendingOutcomes.isEmpty()) {
            System.out.println("No pending appointment outcomes found.");
            return;
        }
        
        for (AppointmentOutcome outcome : pendingOutcomes) {
            outcome.printDetails();
        }
    }


    /**
     * Allows the pharmacist to update the prescription status for appointment outcomes.
     * The pharmacist can select an outcome, prescribe medications, and update the status to "Dispensed."
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

        // Prescribe medications
        List<Medication> medications = load.getMedications();
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
            new MedicationCSV().updateMedicationQuantity(selectedMedication.getMedicineID(),
                    selectedMedication.getQuantity());
        }

        if (!prescribedMedications.isEmpty()) {
            // Update AppointmentOutcome status
            new AppointmentOutcomeCSV().updateAppointmentOutcomeStatus(load.getAppointmentOutcomes(),
                    selectedOutcome.getAppointmentOutcomeID(), PrescriptionStatus.Dispensed);
            System.out.println("Prescription successful.");
        } else {
            System.out.println("No medications were prescribed.");
        }
    }

    /**
     * Displays the details of all medications in the inventory.
     */
    public void viewMedicationInventory() {
        List<Medication> medications = load.getMedications();
        for (Medication medication : medications) {
            medication.printDetails();
        }
    }

    /**
     * Allows the pharmacist to submit a replenishment request for low-stock medications.
     * Medications with a low stock alert are displayed for selection.
     */
    public void submitReplenishmentRequest() {
        List<Medication> allMedications = load.getMedications();
        List<Medication> lowStockMedications = new ArrayList<>();

        for (Medication medication : allMedications) {
            if (medication.getLowStockAlert()) {
                lowStockMedications.add(medication);
            }
        }

        while (!lowStockMedications.isEmpty()) {
            System.out.println("\nLow Stock Medications:");
            for (int i = 0; i < lowStockMedications.size(); i++) {
                System.out.printf("(%d) %s%n", (i + 1), lowStockMedications.get(i).getMedicineName());
            }

            System.out.print(
                    "Enter the number of the medication you would like to submit a replenishment request for (or 0 to cancel): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= lowStockMedications.size()) {
                Medication selectedMedication = lowStockMedications.get(choice - 1);
                new MedicationCSV().updateRestockRequestStatus(selectedMedication.getMedicineID(), "Pending");
                lowStockMedications.remove(choice - 1);
                System.out.println("Replenishment request submitted.");
            } else if (choice == 0) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}