package OOPProject;

import java.util.Scanner;

/**
 * The PharmacistMenu class handles the user interface for the pharmacist role.
 * It presents a menu of actions available to the pharmacist and allows the pharmacist
 * to interact with the system by choosing options from the menu.
 */
public class PharmacistMenu {

    private PharmacistService pharmacistService;
    private Scanner scanner;

    /**
     * Constructor to initialize the PharmacistMenu with the provided PharmacistService.
     * 
     * @param pharmacistService The service layer responsible for handling pharmacist actions.
     */
    public PharmacistMenu(PharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the pharmacist menu and prompts the pharmacist to select an action.
     * The menu includes options for viewing appointment outcomes, updating prescriptions,
     * viewing the medication inventory, submitting replenishment requests, and logging out.
     * 
     * @param userid The ID of the logged-in pharmacist, passed to the menu for possible use.
     */
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