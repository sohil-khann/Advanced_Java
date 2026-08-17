import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private String name;
    private String email;
    private String mobile;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^\\d{10}$");

    public Customer(String name, String email, String mobile) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Name cannot be empty");
        }
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidDataException("Invalid email: " + email);
        }
        if (mobile == null || !MOBILE_PATTERN.matcher(mobile).matches()) {
            throw new InvalidDataException("Invalid mobile: " + mobile);
        }
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', email='" + email + "', mobile='" + mobile + "'}";
    }

    public static class InvalidDataException extends Exception {
        public InvalidDataException(String message) {
            super(message);
        }
    }
}
