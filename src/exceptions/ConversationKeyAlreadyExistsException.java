package exceptions;

public class ConversationKeyAlreadyExistsException extends Exception {
    
    public ConversationKeyAlreadyExistsException(String conversationKey) {
        super("Conversation key " + conversationKey + " already exists.");
    }
}
