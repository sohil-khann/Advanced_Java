import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface ValidEmail {
    String message() default "Invalid email format";
}

class UserProfile {
    @ValidEmail
    String email;
    String username;
}

public class EmailValidationAnnotation {
    public static void main(String[] args) {
        UserProfile profile = new UserProfile();
        profile.email = "priya@example.com";
        profile.username = "priya";

        for (Field field : UserProfile.class.getDeclaredFields()) {
            ValidEmail annotation = field.getAnnotation(ValidEmail.class);
            if (annotation != null) {
                field.setAccessible(true);
                try {
                    String value = (String) field.get(profile);
                    boolean valid = value != null && value.contains("@") && value.contains(".");
                    System.out.println(field.getName() + " -> " + (valid ? "Valid" : annotation.message()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

