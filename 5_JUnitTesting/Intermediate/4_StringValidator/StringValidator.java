public class StringValidator {

    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }

    public boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        return phone.matches("\\d{10}");
    }

    public boolean isAlphabetic(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("[a-zA-Z]+");
    }
}
