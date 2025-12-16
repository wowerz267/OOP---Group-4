	package finals;
	
	import java.util.regex.Pattern;
	
	abstract class EmailValidator{
	    // This method is overridden in each subclass (runtime polymorphism).
	    public abstract boolean validate(String email);
	    
	    protected static final Pattern EMAIL_PATTERN = 
	        Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
	}
	
	// Subclass 1: specific implementation of validate for general emails.
	class GeneralEmailValidator extends EmailValidator {
	    @Override // <-- polymorphism: overriding the abstract method from Validator
	    public boolean validate(String email) {
	        return email != null && EMAIL_PATTERN.matcher(email).matches();
	    }
	}
	
	// Subclass 2: specific implementation of validate for USC emails.
	class USCEmailValidator extends EmailValidator {
	    @Override // <-- polymorphism: different behavior for the same validate method
	    public boolean validate(String email) {
	        return email != null && email.endsWith("@usc.edu.ph") 
	               && EMAIL_PATTERN.matcher(email).matches();
	    }
	}
