package OOPProject;

import java.util.Scanner;

/**
 * The class provides an interactive menu for administrators to manage hospital staaff,
 * view appointments, and handle medication inventory operations.
 * It interacts with the AdministratorService class to perform these operations.
 */
public class AdministratorMenu {

	/**
	 * The service layer that provides the logic for administrator operations.
	 */
    private AdministratorService adminService;

    /**
     * Constructs an AdministratorMenu with the specified administrator service.
     * 
     * @param adminService The service to handle administrator-related operations
     */
    public AdministratorMenu(AdministratorService adminService) {
        this.adminService = adminService;
    }

    /**
     * Displays the administrator menu and allows the administrator to choose to perform various operations.
     * The menu will continue to be displayed until the administrator selects the "Logout" option.
     *  
     * @param adminID The ID of the administrator accessing the menu
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
            scanner.nextLine(); // Consume newline

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
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 8);

        scanner.close();
    }
}
