package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Service class for managing hospital staff and medication inventory. Provides
 * methods for updating, removing, viewing, and adding hospital staff, as well
 * as managing medication inventory, including replenishment requests.
 */
public class AdministratorService {

	private static loadCSVClass load = new loadCSVClass();

	/**
	 * Updates the details of a hospital staff member based on their Hospital ID.
	 * Prompts the user for input and updates the corresponding record in the CSV
	 * file.
	 */
	public void updateHospitalStaff() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n--- Update Hospital Staff ---");
		System.out.print("Enter Hospital ID to update: ");
		String hospitalID = scanner.nextLine().trim();

		// Identify role based on the prefix
		String role = hospitalID.substring(0, 2).toUpperCase();
		String filePath;
		List<String> headers = new ArrayList<>();
		switch (role) {
		case "PH":
			filePath = AppConfig.PHARMACIST_FILE_PATH;
			headers = Arrays.asList("HospitalID", "password", "Role", "Gender", "Name", "firstTimeLogin");
			break;
		case "DR":
			filePath = AppConfig.DOCTOR_FILE_PATH;
			headers = Arrays.asList("HospitalID", "password", "Role", "Gender", "DoctorName", "Department",
					"Specialization", "firstTimeLogin");
			break;
		case "AD":
			filePath = AppConfig.ADMIN_FILE_PATH;
			headers = Arrays.asList("HospitalID", "password", "Role", "Gender", "Name", "firstTimeLogin");
			break;
		default:
			System.out.println("Invalid Hospital ID prefix. Must start with PH, DR, or AD.");
			return;
		}
		List<String> lines = new ArrayList<>();
		boolean isUpdated = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = reader.readLine();
			if (line != null) {
				lines.add(line);
			}

			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				if (values[0].equalsIgnoreCase(hospitalID)) {
					System.out.println("\nUpdating record for ID: " + hospitalID);
					for (int i = 1; i < headers.size(); i++) {
						System.out.print("Enter new value for " + headers.get(i) + " (current: " + values[i] + "): ");
						String newValue = scanner.nextLine().trim();
						if (!newValue.isEmpty()) {
							values[i] = newValue;
						}
					}
					isUpdated = true;
				}
				lines.add(String.join(",", values));
			}
		} catch (IOException e) {
			System.out.println("Error reading the file: " + e.getMessage());
			return;
		}

		if (!isUpdated) {
			System.out.println("No record found with ID: " + hospitalID);
			return;
		}

		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			for (String updatedLine : lines) {
				writer.println(updatedLine);
			}
			System.out.println("Record updated successfully.");
		} catch (IOException e) {
			System.out.println("Error writing to the file: " + e.getMessage());
		}

	}

	/**
	 * Removes a hospital staff member from the system by their Hospital ID. Prompts
	 * the user for the ID and removes the corresponding record from the CSV file.
	 */
	public void removeHospitalStaff() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n--- Remove Hospital Staff ---");
		System.out.print("Enter Hospital ID to remove: ");
		String hospitalID = scanner.nextLine().trim();

		String role = hospitalID.substring(0, 2).toUpperCase();
		String filePath;

		switch (role) {
		case "PH":
			filePath = AppConfig.PHARMACIST_FILE_PATH;
			break;
		case "DR":
			filePath = AppConfig.DOCTOR_FILE_PATH;
			break;
		case "AD":
			filePath = AppConfig.ADMIN_FILE_PATH;
			break;
		default:
			System.out.println("Invalid Hospital ID prefix. Must start with PH, DR, or AD.");
			return;
		}

		List<String> lines = new ArrayList<>();
		boolean isRemoved = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = reader.readLine();
			if (line != null) {
				lines.add(line);
			}

			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				if (!values[0].equalsIgnoreCase(hospitalID)) {
					lines.add(line);
				} else {
					isRemoved = true;
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading the file: " + e.getMessage());
			return;
		}

		if (!isRemoved) {
			System.out.println("No record found with ID: " + hospitalID);
			return;
		}

		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			for (String updatedLine : lines) {
				writer.println(updatedLine);
			}
			System.out.println("Record removed successfully.");
		} catch (IOException e) {
			System.out.println("Error writing to the file: " + e.getMessage());
		}
	}

	/**
	 * Displays the details of all appointment outcomes. If no outcomes are
	 * available, a message is shown.
	 */
	public void viewAppointmentsDetails() {
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
	 * Manages medication inventory, allowing the user to view medications or check
	 * and update low stock alerts.
	 */
	public void manageMedicationInventory() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n--- Manage Medication Inventory ---");
		System.out.println("1. View Medications");
		System.out.println("2. Check and Update Low Stock Alert");
		System.out.print("Enter your choice (1-2): ");

		int choice;
		try {
			choice = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a number between 1 and 2.");
			return;
		}

		switch (choice) {
		case 1:
			viewMedications();
			break;
		case 2:
			checkAndUpdateLowStockAlert();
			break;
		default:
			System.out.println("Invalid choice. Exiting.");
		}
	}

	/**
	 * Displays a list of all medications in the inventory.
	 */
	private void viewMedications() {
		String filePath = AppConfig.MEDICATION_FILE_PATH;

		System.out.println("\n--- Medication Inventory ---");
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			boolean isHeader = true;
			while ((line = br.readLine()) != null) {
				if (isHeader) {
					System.out.println(line);
					System.out.println("-".repeat(line.length()));
					isHeader = false;
				} else {
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading the file: " + e.getMessage());
		}
	}

	/**
	 * Checks and updates medications that have a low stock level. A low stock alert
	 * is triggered if the quantity is less than or equal to the specified
	 * threshold.
	 */
	private void checkAndUpdateLowStockAlert() {
		String filePath = AppConfig.MEDICATION_FILE_PATH;
		List<String> lines = new ArrayList<>();
		boolean isUpdated = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = reader.readLine();
			if (line != null) {
				lines.add(line);
			}

			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length >= 6) {
					try {

						String cleanedQuantity = values[2].replaceAll("[^0-9]", "").trim();
						String cleanedLowQuantity = values[3].replaceAll("[^0-9]", "").trim();

						if (cleanedQuantity.matches("\\d+") && cleanedLowQuantity.matches("\\d+")) {
							int quantity = Integer.parseInt(cleanedQuantity);
							int lowQuantity = Integer.parseInt(cleanedLowQuantity);

							System.out.println("Parsed Values -> Medication ID: " + values[0] + ", Quantity: "
									+ quantity + ", LowQuantity: " + lowQuantity);

							if (quantity <= lowQuantity) {
								System.out.println("Low stock alert triggered for Medication ID: " + values[0]);
								values[4] = "true";
								isUpdated = true;
							}
						} else {
							System.out.println("Skipping record due to non-numeric values: " + line);
						}
					} catch (NumberFormatException e) {
						System.out.println("Skipping record due to invalid number format: " + line);
					}
				} else {
					System.out.println("Invalid record format: " + line);
				}
				lines.add(String.join(",", values));
			}
		} catch (IOException e) {
			System.out.println("Error reading the file: " + e.getMessage());
			return;
		}

		if (!isUpdated) {
			System.out.println("No medications require a low stock alert update.");
			return;
		}

		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			for (String updatedLine : lines) {
				writer.println(updatedLine);
			}
			System.out.println("Low stock alerts updated successfully.");
		} catch (IOException e) {
			System.out.println("Error writing to the file: " + e.getMessage());
		}
	}

	/**
	 * Approves replenishment requests for medications that are marked as "pending".
	 * Updates the quantity of the medication when the request is approved.
	 */
	public void approveReplenishmentRequests() {
		String filePath = AppConfig.MEDICATION_FILE_PATH;
		List<String> lines = new ArrayList<>();
		boolean isUpdated = false;

		System.out.println("\n--- Approving Replenishment Requests ---");

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = reader.readLine();
			if (line != null) {
				lines.add(line);
			}

			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length >= 6) {
					try {

						if (values[5].trim().equalsIgnoreCase("pending")) {
							System.out.println("Pending replenishment found for Medication ID: " + values[0]);

							int currentQuantity = Integer.parseInt(values[3].trim());
							int updatedQuantity = currentQuantity + 100;
							values[2] = String.valueOf(updatedQuantity);

							values[5] = "approved";
							values[4] = "false";
							System.out.println(
									"Updated quantity for Medication ID: " + values[0] + " to " + updatedQuantity);

							isUpdated = true;
						}
					} catch (NumberFormatException e) {
						System.out.println("Skipping record due to invalid number format: " + line);
					}
				}
				lines.add(String.join(",", values));
			}
		} catch (IOException e) {
			System.out.println("Error reading the file: " + e.getMessage());
			return;
		}

		if (!isUpdated) {
			System.out.println("No pending replenishment requests found.");
			return;
		}

		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			for (String updatedLine : lines) {
				writer.println(updatedLine);
			}
			System.out.println("Replenishment requests approved successfully.");
		} catch (IOException e) {
			System.out.println("Error writing to the file: " + e.getMessage());
		}
	}

	/**
	 * Displays hospital staff based on user-selected filters.
	 */
	public void viewHospitalStaff() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n--- Hospital Staff ---");
		System.out.println("Would you like to filter the results?");
		System.out.println("1. By Role (Pharmacist/Doctor/Admin)");
		System.out.println("2. By Gender (Separate Male/Female)");
		System.out.println("3. No Filter (View all)");
		System.out.print("Enter your choice (1-4): ");

		int choice;
		try {
			choice = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Exiting.");
			return;
		}

		if (choice == 1) {

			displayByRole();
		} else if (choice == 2) {

			displayByGender();
		} else if (choice == 3) {

			System.out.println("Invalid choice. Exiting.");
		} else {

			System.out.println("\n--- Pharmacists ---");
			readAndDisplayFile(AppConfig.PHARMACIST_FILE_PATH, null, null);
			System.out.println("\n--- Doctors ---");
			readAndDisplayFile(AppConfig.DOCTOR_FILE_PATH, null, null);
			System.out.println("\n--- Admins ---");
			readAndDisplayFile(AppConfig.ADMIN_FILE_PATH, null, null);
		}
	}

	/**
	 * Displays hospital staff filtered by their role.
	 */
	private void displayByRole() {
		System.out.println("\n--- Pharmacists ---");
		readAndDisplayFile(AppConfig.PHARMACIST_FILE_PATH, null, null);
		System.out.println("\n--- Doctors ---");
		readAndDisplayFile(AppConfig.DOCTOR_FILE_PATH, null, null);
		System.out.println("\n--- Admins ---");
		readAndDisplayFile(AppConfig.ADMIN_FILE_PATH, null, null);
	}

	/**
	 * Displays hospital staff filtered by gender (Male/Female).
	 */
	private void displayByGender() {
		System.out.println("\n--- Male Staff ---");
		readAndDisplayFile(AppConfig.PHARMACIST_FILE_PATH, "gender", "Male");
		readAndDisplayFile(AppConfig.DOCTOR_FILE_PATH, "gender", "Male");
		readAndDisplayFile(AppConfig.ADMIN_FILE_PATH, "gender", "Male");

		System.out.println("\n--- Female Staff ---");
		readAndDisplayFile(AppConfig.PHARMACIST_FILE_PATH, "gender", "Female");
		readAndDisplayFile(AppConfig.DOCTOR_FILE_PATH, "gender", "Female");
		readAndDisplayFile(AppConfig.ADMIN_FILE_PATH, "gender", "Female");
	}

	
	

	/**
	 * Reads the specified file and displays the content, optionally filtered by
	 * type and value.
	 *
	 * @param filePath    the path to the file to read.
	 * @param filterType  the filter type (e.g., "gender" or "age"), or null for no
	 *                    filter.
	 * @param filterValue the value to filter by, or null for no filter.
	 */
	private void readAndDisplayFile(String filePath, String filterType, String filterValue) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			boolean isHeader = true;
			while ((line = br.readLine()) != null) {
				if (isHeader) {
					System.out.println(line);
					System.out.println("-".repeat(line.length()));
					isHeader = false;
				} else {
					if (filterType == null || passesFilter(line, filterType, filterValue)) {
						System.out.println(line);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + filePath + " | " + e.getMessage());
		}
	}

	/**
	 * Determines if the given record passes the specified filter.
	 *
	 * @param line        the line from the file to check.
	 * @param filterType  the filter type (e.g., "gender" or "age").
	 * @param filterValue the filter value.
	 * @return true if the record passes the filter, false otherwise.
	 */
	private boolean passesFilter(String line, String filterType, String filterValue) {
		String[] values = line.split(",");
		switch (filterType) {
		case "gender":

			return values[3].trim().equalsIgnoreCase(filterValue);
		default:
			return true; // No filter
		}
	}

	

	/**
	 * Adds a new hospital staff member based on user input.
	 */
	public void addHospitalStaff() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n--- Add Hospital Staff ---");
		System.out.println("Select the type of staff to create:");
		System.out.println("1. Pharmacist");
		System.out.println("2. Doctor");
		System.out.println("3. Admin");
		System.out.print("Enter your choice (1-3): ");

		int choice;
		try {
			choice = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a number between 1 and 3.");
			return;
		}

		String csvFilePath;
		String newEntry = "";

		switch (choice) {
		case 1: 
			csvFilePath = AppConfig.PHARMACIST_FILE_PATH;
			newEntry = createPharmacistEntry(scanner);
			break;

		case 2: 
			csvFilePath = AppConfig.DOCTOR_FILE_PATH;
			newEntry = createDoctorEntry(scanner);
			break;

		case 3: 
			csvFilePath = AppConfig.ADMIN_FILE_PATH;
			newEntry = createAdminEntry(scanner);
			break;

		default:
			System.out.println("Invalid choice. Please select a valid staff type.");
			return;
		}

		if (!newEntry.isEmpty()) {
			try (FileWriter writer = new FileWriter(csvFilePath, true)) {
				writer.write(newEntry + "\n");
				System.out.println("New staff member added successfully!");
			} catch (IOException e) {
				System.out.println("Error writing to the file: " + e.getMessage());
			}
		}
	}

	/**
	 * Creates a new pharmacist entry from user input.
	 *
	 * @param scanner the Scanner object to capture user input.
	 * @return the formatted CSV entry for a new pharmacist.
	 */
	private String createPharmacistEntry(Scanner scanner) {
		System.out.println("\n--- Add Pharmacist ---");
		System.out.print("Enter Pharmacist ID: ");
		String id = scanner.nextLine().trim();

		String gender = "";
		while (true) {
			System.out.print("Enter Gender (MALE/FEMALE): ");
			gender = scanner.nextLine().trim().toUpperCase();
			try {
				Gender.valueOf(gender);
				break; 
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid gender. Please enter 'MALE' or 'FEMALE'.");
			}
		}

		System.out.print("Enter Name: ");
		String name = scanner.nextLine().trim();

		return String.join(",", id, "password", "PHARMACIST", gender, name, "true");
	}

	/**
	 * Creates a new doctor entry from user input.
	 *
	 * @param scanner the Scanner object to capture user input.
	 * @return the formatted CSV entry for a new doctor.
	 */
	private String createDoctorEntry(Scanner scanner) {
		System.out.println("\n--- Add Doctor ---");
		System.out.print("Enter Doctor ID: ");
		String id = scanner.nextLine().trim();

		String gender = "";
		while (true) {
			System.out.print("Enter Gender (MALE/FEMALE): ");
			gender = scanner.nextLine().trim().toUpperCase();
			try {
				Gender.valueOf(gender);
				break; 
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid gender. Please enter 'MALE' or 'FEMALE'.");
			}
		}

		System.out.print("Enter Name: ");
		String name = scanner.nextLine().trim();
		System.out.print("Enter Department: ");
		String department = scanner.nextLine().trim();
		System.out.print("Enter Specialization: ");
		String specialization = scanner.nextLine().trim();

		return String.join(",", id, "password", "DOCTOR", gender, name, department, specialization, "true");
	}

	/**
	 * Creates a new admin entry from user input.
	 *
	 * @param scanner the Scanner object to capture user input.
	 * @return the formatted CSV entry for a new admin.
	 */
	private String createAdminEntry(Scanner scanner) {
		System.out.println("\n--- Add Admin ---");
		System.out.print("Enter Hospital ID: ");
		String id = scanner.nextLine().trim();

		String gender = "";
		while (true) {
			System.out.print("Enter Gender (MALE/FEMALE): ");
			gender = scanner.nextLine().trim().toUpperCase();
			try {
				Gender.valueOf(gender);
				break; 
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid gender. Please enter 'MALE' or 'FEMALE'.");
			}
		}

		System.out.print("Enter Name: ");
		String name = scanner.nextLine().trim();

		return String.join(",", id, "password", "ADMIN", gender, name, "true");
	}

}