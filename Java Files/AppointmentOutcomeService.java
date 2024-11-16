package OOPProject;

public interface AppointmentOutcomeService {

    // Method to record the outcome (e.g., diagnosis, treatment) for a specific appointment
    void recordOutcome(String appointmentID, String outcomeDetails);

    // Method to retrieve the outcome for a specific appointment
    String getOutcome(String appointmentID);

    // Method to print all recorded outcomes (for debugging or reviewing)
    void printAllOutcomes();
}
