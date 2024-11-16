package OOPProject;

import java.util.HashMap;
import java.util.Map;

public class AppointmentOutcomeServiceImpl implements AppointmentOutcomeService {

    private Map<String, String> appointmentOutcomes;  // A map to store outcomes by appointment ID

    public AppointmentOutcomeServiceImpl() {
        this.appointmentOutcomes = new HashMap<>();
    }

    // Implementing the recordOutcome method from the interface
    @Override
    public void recordOutcome(String appointmentID, String outcomeDetails) {
        appointmentOutcomes.put(appointmentID, outcomeDetails);
        System.out.println("Outcome for appointment " + appointmentID + " recorded successfully.");
    }

    // Implementing the getOutcome method from the interface
    @Override
    public String getOutcome(String appointmentID) {
        return appointmentOutcomes.getOrDefault(appointmentID, "No outcome recorded for this appointment.");
    }

    // Implementing the printAllOutcomes method from the interface
    @Override
    public void printAllOutcomes() {
        if (appointmentOutcomes.isEmpty()) {
            System.out.println("No outcomes recorded yet.");
        } else {
            for (Map.Entry<String, String> entry : appointmentOutcomes.entrySet()) {
                System.out.println("Appointment ID: " + entry.getKey() + " - Outcome: " + entry.getValue());
            }
        }
    }
}
