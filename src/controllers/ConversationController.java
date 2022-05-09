package controllers;

import java.util.ArrayList;
import java.util.Collection;

import exceptions.ConversationKeyAlreadyExistsException;
import exceptions.UserIsNotAMemberException;
import exceptions.UserIsNotAdminException;
import exceptions.UserNotFoundException;
import models.conversation.Conversation;
import models.conversation.GroupConversation;
import models.message.MediaMessage;
import models.message.TextMessage;
import models.user.User;

public class ConversationController {
    
    public ArrayList<Conversation> getConversationsByUserName(String username) throws UserNotFoundException {
        User user = User.getUser(username);
        return user.getConversations();
    }
    

    public Conversation getConversationByUserNameAndIndex(String username, Integer index) throws UserNotFoundException {
        User user = User.getUser(username);
        Conversation conversation = user.getConversations().get(index.intValue());

        return conversation;
    }

    public void createPairConversationWith(String creatorName, String receiverName) throws ConversationKeyAlreadyExistsException, UserNotFoundException {
        User creator = User.getUser(creatorName);
        User receiver = User.getUser(receiverName);

        creator.createConversationWith(receiver);
    }

    public void createGroupConversation(String creatorName, String groupName) throws ConversationKeyAlreadyExistsException, UserNotFoundException {
        User creator = User.getUser(creatorName);
        
        creator.createGroupConversation(groupName);
        
    }

    public void addMemberToGroupConversation(String adminName, String memberName, Integer conversationIndex) throws UserIsNotAdminException, UserNotFoundException {
        GroupConversation conversation = (GroupConversation) this.getConversationByUserNameAndIndex(adminName, conversationIndex);

        conversation.addMember(adminName, memberName);
    }

    public void addAdmin(String oldAdminName, String newAdminName, Integer conversationIndex) throws UserIsNotAdminException, UserIsNotAMemberException, UserNotFoundException {
        GroupConversation conversation = (GroupConversation) this.getConversationByUserNameAndIndex(oldAdminName, conversationIndex);
        User oldAdmin = User.getUser(oldAdminName);
        User newAdmin = User.getUser(newAdminName);


        conversation.addAdmin(oldAdmin, newAdmin);
    }

    public void sendTextMessage(String username, Integer conversationIndex, String messageContent) throws UserIsNotAMemberException, UserNotFoundException {
        Conversation conversation = getConversationByUserNameAndIndex(username, conversationIndex);
        User user = User.getUser(username);
        TextMessage textMessage = new TextMessage(user, messageContent);
        conversation.addMessage(textMessage);
    }

    public void sendMediaMessage(String username, Integer conversationIndex, String mediaType, String mediaPath) throws UserIsNotAMemberException, UserNotFoundException {
        Conversation conversation = getConversationByUserNameAndIndex(username, conversationIndex);
        User user = User.getUser(username);
        MediaMessage mediaMessage = new MediaMessage(user, mediaType, mediaPath);
        conversation.addMessage(mediaMessage);
    }

    public int getConversationMembersQuantity(String username, Integer conversationIndex) throws UserNotFoundException {
        Conversation conversation = getConversationByUserNameAndIndex(username, conversationIndex);
        return conversation.getMembers().size();
    }

    public Collection<String> getConversationAdmins(String username, Integer conversationIndex) throws UserNotFoundException, UserIsNotAMemberException {
        GroupConversation conversation = (GroupConversation) getConversationByUserNameAndIndex(username, conversationIndex);
        if (conversation.getMembers().contains(User.getUser(username))) {
            return conversation.getAdmins();
        }

        throw new UserIsNotAMemberException(username, conversation.getUniqueKey());
    }

    public Collection<User> getConversationMembers(String username, Integer conversationIndex) throws UserNotFoundException {
        Conversation conversation = getConversationByUserNameAndIndex(username, conversationIndex);

        return conversation.getMembers();
    }
    
}
