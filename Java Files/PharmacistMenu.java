package OOPProject;

import java.util.Scanner;

public class PharmacistMenu {

    private PharmacistService pharmacistService;
    private Scanner scanner;

    public PharmacistMenu(PharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu(String userid) {
        int choice;

        do {
            System.out.println("\nPharmacist Menu:");
            System.out.println("(1) View Appointment Outcome Record");
            System.out.println("(2) Update Prescription Status");
            System.out.println("(3) View Medication Inventory");
            System.out.println("(4) Submit Replenishment Request");
            System.out.println("(5) Logout");

            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    pharmacistService.viewAppointmentOutcomeRecord();
                    break;
                case 2:
                    pharmacistService.updatePrescriptionStatus();
                    break;
                case 3:
                    pharmacistService.viewMedicationInventory();
                    break;
                case 4:
                    pharmacistService.submitReplenishmentRequest();
                    break;
                case 5:
                    System.out.println("Logout");
                    main.displayLoginMenu();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }

   
}