package OOPProject;

import java.util.Date;

public class AppointmentSlot {
	private int appointmentID;
    private Doctor doctor;
    private long time;
    private Date date;

    public AppointmentSlot(int appointmentID, Doctor doctor, long time, Date date) {
        this.appointmentID = appointmentID;
        this.doctor = doctor;
        this.time = time;
        this.date = date;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public long getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

}
