import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PatientManagementSystem {
    private final Map<String, Patient> patients;
    private final List<Appointment> appointments;

    public static class DuplicatePatientException extends Exception {
        public DuplicatePatientException(String message) {
            super(message);
        }
    }

    public PatientManagementSystem() {
        this.patients = new HashMap<>();
        this.appointments = new ArrayList<>();
    }

    public void registerPatient(Patient patient) throws DuplicatePatientException {
        if (patients.containsKey(patient.getId())) {
            throw new DuplicatePatientException("Patient with ID " + patient.getId() + " already registered.");
        }
        patients.put(patient.getId(), patient);
    }

    public Patient searchById(String id) {
        return patients.get(id);
    }

    public void bookAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Patient> searchByName(String name) {
        return patients.values().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public long getDailyAppointmentCount(String date) {
        return appointments.stream()
                .filter(a -> a.getDate().equals(date))
                .count();
    }

    public List<Patient> getAllPatientsSortedByName() {
        return patients.values().stream()
                .sorted(Comparator.comparing(Patient::getName))
                .collect(Collectors.toList());
    }

    public Map<String, Long> getAppointmentCountPerDoctor() {
        return appointments.stream()
                .collect(Collectors.groupingBy(Appointment::getDoctorName, Collectors.counting()));
    }

    public static void main(String[] args) {
        PatientManagementSystem system = new PatientManagementSystem();
        try {
            system.registerPatient(new Patient("P001", "Aarav Sharma", "9876543210", 30));
            system.registerPatient(new Patient("P002", "Kiara Patel", "8765432109", 25));
            system.registerPatient(new Patient("P003", "Aarav Sharma", "7654321098", 40));
        } catch (DuplicatePatientException e) {
            System.out.println(e.getMessage());
        }

        system.bookAppointment(new Appointment("A001", "P001", "Dr. Gupta", "2026-08-14", "10:00"));
        system.bookAppointment(new Appointment("A002", "P002", "Dr. Sharma", "2026-08-14", "11:00"));
        system.bookAppointment(new Appointment("A003", "P001", "Dr. Gupta", "2026-08-15", "09:00"));

        System.out.println("Search P001 by ID: " + system.searchById("P001"));
        System.out.println("Daily appointments on 2026-08-14: " + system.getDailyAppointmentCount("2026-08-14"));
        System.out.println("Patients sorted by name: " + system.getAllPatientsSortedByName());
        System.out.println("Appointments per doctor: " + system.getAppointmentCountPerDoctor());
    }
}



