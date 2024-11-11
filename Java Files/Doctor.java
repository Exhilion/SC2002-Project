import java.io.*;
import java.util.*;

public class Doctor implements DoctorAppointmentManagement {
    private String doctorID;
    private String doctorName;
    private String department;
    private String specialization;

    private static final String CSV_FILE_PATH = "doctors.csv";

    public Doctor(String doctorID, String doctorName, String department, String specialization) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.department = department;
        this.specialization = specialization;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public static Doctor getDoctorFromCSV(String doctorID) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(doctorID)) {
                    return new Doctor(values[0], values[1], values[2], values[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateDoctorInCSV() {
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(this.doctorID)) {
                    // Update the doctor information in the record
                    values[1] = this.doctorName;
                    values[2] = this.department;
                    values[3] = this.specialization;
                }
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write all records back to the CSV file
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE_PATH))) {
            for (String[] record : records) {
                pw.println(String.join(",", record));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDoctorToCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE_PATH, true))) {
            pw.println(String.join(",", this.doctorID, this.doctorName, this.department, this.specialization));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recordAppointmentOutcome(String appointmentID, String outcome) {
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(APPOINTMENTS_CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(appointmentID)) {
                    values[6] = outcome; // Set the outcome for the appointment
                }
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write all records back to the appointments CSV file
        try (PrintWriter pw = new PrintWriter(new FileWriter(APPOINTMENTS_CSV_FILE))) {
            for (String[] record : records) {
                pw.println(String.join(",", record));
            }
            System.out.println("Outcome recorded for appointment ID: " + appointmentID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setAvailableDates(String doctorID, List<TimeSlot> schedule) {
        try (FileWriter fw = new FileWriter("time_slots.csv", true)) { // Appending mode
            for (TimeSlot timeSlot : schedule) {
                fw.write(timeSlot.getId() + "," + doctorID + "," + timeSlot.getDate() + "," + timeSlot.getTime() + "\n");
            }
            System.out.println("Available dates set for doctor ID: " + doctorID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void acceptAppointmentRequest(String appointmentID) {
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("appointments.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(appointmentID)) {
                    values[5] = "CONFIRMED";
                }
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write all records back to the CSV file
        try (PrintWriter pw = new PrintWriter(new FileWriter("appointments.csv"))) {
            for (String[] record : records) {
                pw.println(String.join(",", record));
            }
            System.out.println("Appointment " + appointmentID + " confirmed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void declineAppointmentRequest(String appointmentID) {
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("appointments.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(appointmentID)) {
                    values[5] = "DECLINED";
                }
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write all records back to the CSV file
        try (PrintWriter pw = new PrintWriter(new FileWriter("appointments.csv"))) {
            for (String[] record : records) {
                pw.println(String.join(",", record));
            }
            System.out.println("Appointment " + appointmentID + " declined.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Appointment> viewAppointmentSchedule(String doctorID) {
        List<Appointment> appointments = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("appointments.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(doctorID) && values[5].equals("CONFIRMED")) {
                    Appointment appointment = new Appointment(
                            values[0], // appointment_id
                            values[1], // doctor_id
                            values[2], // patient_id
                            values[3], // date
                            values[4]  // time
                    );
                    appointments.add(appointment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appointments;
    }
}

