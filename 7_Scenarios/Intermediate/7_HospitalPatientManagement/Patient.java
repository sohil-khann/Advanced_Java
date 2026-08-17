import java.util.Objects;
import java.util.regex.Pattern;

public class Patient {
    private final String id;
    private final String name;
    private final String phone;
    private final int age;

    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");

    public Patient(String id, String name, String phone, int age) {
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new IllegalArgumentException("Invalid phone number. Must be exactly 10 digits.");
        }
        this.id = Objects.requireNonNull(id, "Patient ID cannot be null");
        this.name = Objects.requireNonNull(name, "Patient name cannot be null");
        this.phone = Objects.requireNonNull(phone, "Patient phone cannot be null");
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
