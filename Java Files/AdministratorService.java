package OOPProject;

import java.util.List;
import java.util.Scanner;

public class AdministratorService {

	private static loadCSVClass load = new loadCSVClass();

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

	public void updateHospitalStaff() {
		// Placeholder for future update functionality
		System.out.println("Feature to update hospital staff is not implemented yet.");
	}

	public void removeHospitalStaff() {
		// Placeholder for future remove functionality
		System.out.println("Feature to remove hospital staff is not implemented yet.");
	}

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

	public void manageMedicationInventory() {
		Medication.displayInventory(load.getMedications());
	}

	public void viewAppointmentsDetails() {

	}

	public void approveReplenishmentRequests() {
//        System.out.println("Processing replenishment requests...");
//        Inventory inv = new Inventory(load.getMedications());
//        Administrator admin = new Administrator();
//        admin.approveReplenishmentRequests(inv);
//        System.out.println("Replenishment requests processed.");
	}

}
