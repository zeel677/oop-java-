import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank
    String name;

    @NotBlank
    @MaxLength(10)
    String username;

    @MaxLength(15)
    String city;

    SignupForm(String name, String username, String city) {
        this.name = name;
        this.username = username;
        this.city = city;
    }
}

public class FormValidator {
    static List<String> validate(Object obj)
            throws IllegalAccessException {
        List<String> errors = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            String text = value == null ? "" : value.toString();

            if (field.isAnnotationPresent(NotBlank.class)
                    && text.trim().isEmpty()) {
                errors.add(field.getName() + " must not be blank");
            }

            if (field.isAnnotationPresent(MaxLength.class)) {
                int max = field.getAnnotation(MaxLength.class).value();

                if (text.length() > max) {
                    errors.add(field.getName()
                            + " must not exceed " + max + " characters");
                }
            }
        }

        return errors;
    }

    public static void main(String[] args) throws IllegalAccessException {
        SignupForm form = new SignupForm("", "zeelpatel123", "Ahmedabad");

        List<String> errors = validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid");
        } else {
            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}