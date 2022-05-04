package exceptions;

public class UserIsNotAMemberException extends Exception {
    public UserIsNotAMemberException(String username, String groupKey) {
        super("User " + username + " is not a member of the group " + groupKey);
    }
}
