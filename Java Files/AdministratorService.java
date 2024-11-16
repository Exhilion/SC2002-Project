package OOPProject;

import java.util.List;
import java.util.Scanner;

/**
 * The class provides administrators with the methods to manage hospital staff, view appointments 
 * and handle medication inventory.
 * This service acts as a layer to interact with CSV loading, staff management and other
 * administrative operations.
 */
public class AdministratorService {

	/**
	 * Helper class to load data from CSV files
	 */
	private static loadCSVClass load = new loadCSVClass();

	/**
	 * Displays the list of hospital staff
	 * If no staff members are found, it prints the corresponding message.
	 */
	public void viewHospitalStaff() {
//        List<Staff> staffList = load.getStaff();  // Assuming load is a method to fetch staff data
//        if (staffList.isEmpty()) {
//            System.out.println("No staff members found.");
//        } else {
//            System.out.println("Hospital Staff List:");
//            for (Staff staff : staffList) {
//                staff.printDetails();
//            }
//        }
	}

	/**
	 * Adds a new hospital staff member by collecting the necessary details from the administrator
	 * Details such as the role, gender, name and other relevant details to the role are requested.
	 * A unique hospital ID and default password are generated for the new staff member.
	 */
	public void addHospitalStaff() {
		Scanner scanner = new Scanner(System.in);
		StaffCSV newStaff = new StaffCSV();

		// Select Role with retry option
		Role role = null;
		while (role == null) {
			System.out.println("Select Role (1-DOCTOR, 2-PHARMACIST, 3-ADMINISTRATOR):");
			int roleChoice = scanner.nextInt();
			scanner.nextLine();
			switch (roleChoice) {
			case 1 -> role = Role.DOCTOR;
			case 2 -> role = Role.PHARMACIST;
			case 3 -> role = Role.ADMINISTRATOR;
			default -> System.out.println("Invalid role selected. Please try again.");
			}
		}

		// Select Gender with retry option
		Gender gender = null;
		while (gender == null) {
			System.out.println("Select Gender (1-MALE, 2-FEMALE, 3-OTHER): ");
			int genderChoice = scanner.nextInt();
			scanner.nextLine();
			switch (genderChoice) {
			case 1 -> gender = Gender.MALE;
			case 2 -> gender = Gender.FEMALE;
			default -> System.out.println("Invalid gender selected. Please try again.");
			}
		}

		// Common details for all roles
		System.out.print("Enter Name: ");
		String name = scanner.nextLine();

		// Generate hospital ID and password
		String hospitalID = newStaff.generateStaffId(role);
		String password = "password";
		Boolean firstTimeLogin = true;

		// Additional details based on role
		switch (role) {
		case DOCTOR -> {
			System.out.print("Enter Department: ");
			String department = scanner.nextLine();
			System.out.print("Enter Specialisation: ");
			String specialisation = scanner.nextLine();
			newStaff.addDoctor(hospitalID, password, role, gender, name, department, specialisation, firstTimeLogin);
		}
		case PHARMACIST -> newStaff.addPharmacist(hospitalID, password, role, gender, name, firstTimeLogin);
		case ADMINISTRATOR -> newStaff.addAdmin(hospitalID, password, role, gender, name, firstTimeLogin);
		}

		System.out.println("Staff added successfully!");
	}

	/**
	 * Provides the functionality to update existing hospital details
	 */
	public void updateHospitalStaff() {
		// Placeholder for future update functionality
		System.out.println("Feature to update hospital staff is not implemented yet.");
	}

	/**
	 * Provides the functionality to remove a hospital staff member.
	 */
	public void removeHospitalStaff() {
		// Placeholder for future remove functionality
		System.out.println("Feature to remove hospital staff is not implemented yet.");
	}

	/**
	 * It iterates through and displays the details of all appointment outcomes.
	 * If no appointment outcomes are available, it prints the corresponding message.
	 */
	public void viewAppointments() {
		List<AppointmentOutcome> appointmentOutcomes = load.getAppointmentOutcomes();
		if (appointmentOutcomes.isEmpty()) {
			System.out.println("No appointment outcomes available.");
		} else {
			for (AppointmentOutcome appointmentOutcome : appointmentOutcomes) {
				appointmentOutcome.printAppointmentDetails();
			}
		}
	}

	/**
	 * Displays and manages the medication inventory.
	 * Uses a helper class to load and display the list of medications.
	 */
	public void manageMedicationInventory() {
		Medication.displayInventory(load.getMedications());
	}

	/**
	 * Displays the details of all appointments
	 */
	public void viewAppointmentsDetails() {

	}

	/**
	 * Approves replenishment requests for medications in the inventory.
	 */
	public void approveReplenishmentRequests() {
//        System.out.println("Processing replenishment requests...");
//        Inventory inv = new Inventory(load.getMedications());
//        Administrator admin = new Administrator();
//        admin.approveReplenishmentRequests(inv);
//        System.out.println("Replenishment requests processed.");
	}

}
