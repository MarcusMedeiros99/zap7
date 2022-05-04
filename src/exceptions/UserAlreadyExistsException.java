package exceptions;


public class UserAlreadyExistsException extends Exception {
    
    public UserAlreadyExistsException(String username) {
        super("Username " + username + " already exists.");
    }
}
