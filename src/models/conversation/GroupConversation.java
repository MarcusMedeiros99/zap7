package models.conversation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import exceptions.ConversationKeyAlreadyExistsException;
import exceptions.UserIsNotAMemberException;
import exceptions.UserIsNotAdminException;
import exceptions.UserNotFoundException;
import models.user.User;

public class GroupConversation extends Conversation {
    private Set<String> admins;
    private String name;

    public GroupConversation(User createdBy, String conversationName) throws ConversationKeyAlreadyExistsException {
        Date createdAt = new Date();
        String conversationKey = Conversation.getUniqueKey(createdBy, createdAt);

        Set<User> members = new HashSet<User>();
        members.add(createdBy);

        if (!Conversation.allConversations.containsKey(conversationKey)) {
            this.messages = new ArrayList<>();
            this.name = conversationName;
            this.createdAt = createdAt;
            this.updatedAt = createdAt;
            this.createdBy = createdBy;
            this.admins = new HashSet<String>();
            this.admins.add(createdBy.getName());
            this.members = members;
            
            Conversation.allConversations.put(conversationKey, this);
        }
        else {
            throw new ConversationKeyAlreadyExistsException(conversationKey);
        }
    }

    @Override
    public String getConversationNameForUser(User user) {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin(String username) {
        return admins.contains(username);
    }

    @Override
    public void addMember(String oldMemberName, String newMemberName) throws UserIsNotAdminException, UserNotFoundException {
        User newMember = User.getUser(newMemberName);
        
        if (isAdmin(oldMemberName)) {
            this.members.add(newMember);
            newMember.addConversation(this);
        }
        else throw new UserIsNotAdminException(oldMemberName + "is not admin of group " + this.getUniqueKey());
    }

    public void addAdmin(User admin, User newAdmin) throws UserIsNotAMemberException, UserIsNotAdminException {
        if (this.admins.contains(admin.getName())) {
            if (this.members.contains(newAdmin)) {
                this.admins.add(newAdmin.getName());
            }
            else {
                throw new UserIsNotAMemberException(admin.getName(), this.getUniqueKey());
            }
        }
        else {
            throw new UserIsNotAdminException(admin.getName() + "is not admin of group " + this.getUniqueKey());
        }
    }

    public Set<String> getAdmins() {
        return this.admins;
    }
    
}
