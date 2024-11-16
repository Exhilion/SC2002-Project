package OOPProject;

import java.util.Scanner;

/**
 * This class provides the menu interface for the Administrator role in the system.
 * It allows the administrator to view and manage hospital staff, appointments, and medication inventory,
 * as well as approve replenishment requests and log out.
 */
public class AdministratorMenu {

    private AdministratorService adminService;

    /**
     * Constructor for initializing the AdministratorMenu with the provided AdministratorService.
     *
     * @param adminService An instance of AdministratorService to perform actions for the administrator.
     */
    public AdministratorMenu(AdministratorService adminService) {
        this.adminService = adminService;
    }

    /**
     * Displays the Admin Menu and handles the user's choice.
     * The menu includes options to view, add, update, or remove hospital staff,
     * view appointment details, manage the medication inventory, and approve replenishment requests.
     * The loop continues until the administrator selects to log out.
     *
     * @param adminID The ID of the administrator logged into the system.
     */
    public void displayAdminMenu(String adminID) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("(1) View Hospital Staff");
            System.out.println("(2) Add Hospital Staff");
            System.out.println("(3) Update Hospital Staff");
            System.out.println("(4) Remove Hospital Staff");
            System.out.println("(5) View Appointments details");
            System.out.println("(6) View and Manage Medication Inventory");
            System.out.println("(7) Approve Replenishment Requests");
            System.out.println("(8) Logout");

            choice = scanner.nextInt();
            scanner.nextLine(); 

        
            switch (choice) {
                case 1:
                    adminService.viewHospitalStaff();
                    break;
                case 2:
                    adminService.addHospitalStaff();
                    break;
                case 3:
                    adminService.updateHospitalStaff();
                    break;
                case 4:
                    adminService.removeHospitalStaff();
                    break;
                case 5:
                    adminService.viewAppointmentsDetails();
                    break;
                case 6:
                    adminService.manageMedicationInventory();
                    break;
                case 7:
                    adminService.approveReplenishmentRequests();
                    break;
                case 8:
                    System.out.println("Logout");
                    main.displayLoginMenu();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 8);

        scanner.close();
    }
}
