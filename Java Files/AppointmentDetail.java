package OOPProject;

public class AppointmentDetail {
	private Patient patient; 
	private Doctor doctor; 
	private Appointment appointment; 
	private AppointmentOutcome appointmentOutcome; 
	
	public AppointmentDetail(Patient patient, Doctor doctor, Appointment appointment, AppointmentOutcome appointmentOutcome) {
		this.patient = patient; 
		this.doctor = doctor; 
		this.appointment = appointment; 
		this.appointmentOutcome = appointmentOutcome;
	}
}
