import java.util.Objects;

public class Appointment {
    private final String appointmentId;
    private final String patientId;
    private final String doctorName;
    private final String date;
    private final String timeSlot;

    public Appointment(String appointmentId, String patientId, String doctorName, String date, String timeSlot) {
        this.appointmentId = Objects.requireNonNull(appointmentId, "Appointment ID cannot be null");
        this.patientId = Objects.requireNonNull(patientId, "Patient ID cannot be null");
        this.doctorName = Objects.requireNonNull(doctorName, "Doctor name cannot be null");
        this.date = Objects.requireNonNull(date, "Date cannot be null");
        this.timeSlot = Objects.requireNonNull(timeSlot, "Time slot cannot be null");
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDate() {
        return date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", date='" + date + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                '}';
    }
}
