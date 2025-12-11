package MapProject;

import java.util.regex.Pattern;

abstract class Validator {
    public abstract boolean validate(String email);
    
    protected static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
}

class GeneralEmailValidator extends Validator {
    @Override
    public boolean validate(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}

class USCEmailValidator extends Validator {
    @Override
    public boolean validate(String email) {
        return email != null && email.endsWith("@usc.edu.ph") 
               && EMAIL_PATTERN.matcher(email).matches();
    }
}
