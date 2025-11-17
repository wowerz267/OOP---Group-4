package MapProject;

import java.util.regex.Pattern;

public class EmailValidator {
    private static final Pattern pattern = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    public static boolean isValid(String email) {
        return email != null && pattern.matcher(email).matches();
    }

    public static boolean isUSCEmail(String email) {
        return email != null && email.endsWith("@usc.edu.ph");
    }
}
