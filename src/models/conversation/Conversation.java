package models.conversation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import exceptions.UserIsNotAMemberException;
import exceptions.UserIsNotAllowedException;
import exceptions.UserNotFoundException;
import models.message.Message;
import models.user.User;

public abstract class Conversation {
    static final Map<String, Conversation> allConversations = new HashMap<String, Conversation>();
    protected User createdBy;
    protected Set<User> members;
    protected ArrayList<Message> messages;
    protected Date createdAt;
    protected Date updatedAt;

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) throws UserIsNotAMemberException {
        if (this.members.contains(message.getSentFrom()))  {
            this.messages.add(message);
            this.updatedAt = new Date();
        }
        else {
            throw new UserIsNotAMemberException(message.getSentFrom().getName(), this.getUniqueKey());
        }
        
    }

    public void removeMessage(Integer index) {
        this.messages.remove(index.intValue());
    }

    public Set<User> getMembers() {
        return this.members;
    }

    public User getCreatedBy() {
        return this.createdBy;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }    

    public String getUniqueKey() {
        return getCreatedBy().getName() + " " + getCreatedAt().toString();
    }

    public abstract String getConversationNameForUser(User user);

    public String getConversationNameForUserName(String username) throws UserNotFoundException {
        User user = User.getUser(username);
        return getConversationNameForUser(user);
    }


    public static String getUniqueKey(User creator, Date createdAt) {
        return creator.getName() + createdAt.toString();
    }

    public static Map<String, Conversation> getAllConversations() {
        return allConversations;
    }

    public abstract void addMember(String oldMemberName, String newMemberName) throws UserIsNotAllowedException, UserNotFoundException;
    

}
