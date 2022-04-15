package pageObject;

import org.apache.commons.validator.routines.*;

/* This class contains common utilities. We can use in entire project */
public class Utility 
{
	public boolean validatedEmailAddress(Object value) 
	{
		EmailValidator validator = EmailValidator.getInstance();
		 
        // Verify that given email address valid or not
        if (!validator.isValid((String) value)) 
        {
            return false;
        }
        
        return true;
    }



}
