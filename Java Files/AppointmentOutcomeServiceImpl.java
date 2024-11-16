package OOPProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AppointmentOutcomeServiceImpl implements AppointmentOutcomeService {

	@Override
	public void addAppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord,
			String consultationNotes, PrescriptionStatus status) {
		String appointmentOutcome = "AC" + UUID.randomUUID().toString();
		AppointmentOutcome newOutcome = new AppointmentOutcome(appointmentOutcome, appointment, medicalRecord,
				consultationNotes, status);
		AppointmentOutcomeCSV newOutcomeCSV = new AppointmentOutcomeCSV();
		newOutcomeCSV.addAppointmentOutcome(appointmentOutcome, appointment, medicalRecord, consultationNotes, status);
	}
}