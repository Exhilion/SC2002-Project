package OOPProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentOutcomeFilter {

    // Method to filter AppointmentOutcome by pending status
    public static List<AppointmentOutcome> filterByPendingStatus(List<AppointmentOutcome> allOutcomes) {
        List<AppointmentOutcome>  pendingOutcomes = new ArrayList<>();
        for (AppointmentOutcome outcome : allOutcomes) {
            if (outcome.getStatus() == PrescriptionStatus.Pending) {
                pendingOutcomes.add(outcome);
            }
        }
        return pendingOutcomes;
    }
    
   
}