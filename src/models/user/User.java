package models.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Comparable;

import exceptions.ConversationKeyAlreadyExistsException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import models.conversation.Conversation;
import models.conversation.GroupConversation;
import models.conversation.PairConversation;

public class User implements Comparable<User> {
    
    private String name;
    private String phoneNumber;
    private ArrayList<Conversation> conversations;
    private static final Map<String,User> allUsers;

    static {
        allUsers = new HashMap<String, User>();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public User(String name, String phoneNumber) throws UserAlreadyExistsException {
        if (!allUsers.containsKey(name)) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.conversations = new ArrayList<Conversation>();
            allUsers.put(name, this);
        }
        else {
            throw new UserAlreadyExistsException(name);
        }
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Conversation> getConversations() {
        return conversations;
    }

    public void addConversation(Conversation newConversation) {
        this.conversations.add(newConversation);
    }

    public void createConversationWith(User user) throws ConversationKeyAlreadyExistsException {
        PairConversation conversation = new PairConversation(this, user);
        this.addConversation(conversation);
        if (this != user) user.addConversation(conversation);
        
    }

    public void createGroupConversation(String conversationName) throws ConversationKeyAlreadyExistsException {
        GroupConversation groupConversation = new GroupConversation(this, conversationName);
        this.addConversation(groupConversation);
    }

    public static Map<String,User> getAllUsers() {
        return allUsers;
    }

    public static User getUser(String name) throws UserNotFoundException {
        User user = allUsers.get(name);
        if (user == null) throw new UserNotFoundException(name);
        return user;
    }

    public static void addUser(String name, String phoneNumber) throws UserAlreadyExistsException {
        new User(name, phoneNumber);
    }

    @Override
    public int compareTo(User arg0) {
        if (this == arg0) return 0;
        return this.name.compareTo(((User) arg0).name );
    }

}
