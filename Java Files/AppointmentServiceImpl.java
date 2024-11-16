package OOPProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AppointmentServiceImpl implements AppointmentService {
	private loadCSVClass load = new loadCSVClass();

	// Appointment functions
	@Override
	public void scheduleAppointment(String patientID, String doctorID, String dateOfChoice, String startTime,
			String endTime) {
		AppointmentCSV.scheduleAppointment(load.getAppointmentSlots(), patientID, doctorID, dateOfChoice, startTime,
				endTime);
	}

	@Override
	public void cancelAppointment(String patientID, String appointmentID) {
		AppointmentCSV.cancelAppointment(patientID, appointmentID);
	}

	@Override
	public List<Appointment> getPendingAppointments(String doctorID) {
		List<Appointment> pendingAppointments = new ArrayList<>();
		for (Appointment appointment : load.getAppointments()) {
			if (appointment.getAppointmentSlot().getDoctor().getHospitalId().equals(doctorID)
					&& "pending".equalsIgnoreCase(appointment.getStatus())) {
				pendingAppointments.add(appointment);
			}
		}
		return pendingAppointments;
	}

	@Override
	public void updateAppointmentStatus(String appointmentID, String newStatus) {
		new AppointmentCSV().updateAppointmentStatus(appointmentID, newStatus);
	}

	@Override
	public List<Appointment> getConfirmedAppointments(String doctorID) {
		String status = "confirmed";
		return Appointment.filterAppointmentsByDoctorAndStatus(load.getAppointments(), doctorID, status);
	}

	// Appointment Slot functions
	@Override
	public void updateAppointmentSlotBookingStatus(String appointmentSlotID, boolean isBooked) {
		AppointmentSlotCSV.updateAppointmentSlotBookingStatus(appointmentSlotID, isBooked);
		System.out.println("Appointment Slot " + (isBooked ? "Booked" : "Cancelled") + " Successfully!");
	}

	@Override
	public boolean addAppointmentSlot(String doctorID, String startTime, String endTime, Date date) {
		AppointmentSlotCSV newSlotCSV = new AppointmentSlotCSV();
		String appointmentSlotID = "AS" + UUID.randomUUID().toString();
		AppointmentSlot newSlot = new AppointmentSlot(appointmentSlotID, startTime, endTime, date, false);
		return newSlotCSV.addAppointmentSlotToCSV(newSlot, doctorID);
	}

	// Appointment outcome functions
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
