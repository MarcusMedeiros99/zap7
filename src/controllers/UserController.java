package controllers;

import java.util.ArrayList;

import exceptions.ConversationKeyAlreadyExistsException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserIsNotAMemberException;
import exceptions.UserIsNotAdminException;
import exceptions.UserNotFoundException;
import models.conversation.Conversation;
import models.conversation.GroupConversation;
import models.message.TextMessage;
import models.user.User;

public class UserController {

    public ArrayList<User> getUsers() {
        return new ArrayList<>(User.getAllUsers().values());
    }

    public User getUser(String name) throws UserNotFoundException{
        return User.getUser(name);
    }

    public void addUser(String name, String phoneNumber) throws UserAlreadyExistsException {
        User.addUser(name, phoneNumber);
    }

    public ArrayList<Conversation> getConversations(String username) throws UserNotFoundException {
        User user = User.getUser(username);
        return user.getConversations();
    }
    
    public ArrayList<Conversation> getConversations(User user) {
        return user.getConversations();
    }

    public Conversation getConversation(String username, Integer index) throws UserNotFoundException {
        User user = User.getUser(username);
        Conversation conversation = user.getConversations().get(index.intValue());

        return conversation;
    }

    public void sendTextMessage(String username, Integer conversationIndex, String messageContent) throws UserIsNotAMemberException, UserNotFoundException {
        Conversation conversation = getConversation(username, conversationIndex);
        User user = User.getUser(username);
        TextMessage textMessage = new TextMessage(user, messageContent);
        conversation.addMessage(textMessage);
    }

    public void createPairConversationWith(String creatorName, String receiverName) throws ConversationKeyAlreadyExistsException, UserNotFoundException {
        User creator = getUser(creatorName);
        User receiver = getUser(receiverName);

        creator.createConversationWith(receiver);
    }

    public void createGroupConversation(String creatorName, String groupName) throws ConversationKeyAlreadyExistsException, UserNotFoundException {
        User creator = getUser(creatorName);
        
        creator.createGroupConversation(groupName);
        
    }

    public void addMemberToGroupConversation(String adminName, String memberName, Integer conversationIndex) throws UserIsNotAdminException, UserNotFoundException {
        GroupConversation conversation = (GroupConversation) this.getConversation(adminName, conversationIndex);

        conversation.addMember(adminName, memberName);
    }

    public void addAdmin(String oldAdminName, String newAdminName, Integer conversationIndex) throws UserIsNotAdminException, UserIsNotAMemberException, UserNotFoundException {
        GroupConversation conversation = (GroupConversation) this.getConversation(oldAdminName, conversationIndex);
        User oldAdmin = User.getUser(oldAdminName);
        User newAdmin = User.getUser(newAdminName);


        conversation.addAdmin(oldAdmin, newAdmin);
    }
}
