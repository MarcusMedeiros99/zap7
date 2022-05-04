package models.conversation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import exceptions.ConversationKeyAlreadyExistsException;
import models.user.User;

public class PairConversation extends Conversation {

    static final Set<String> allPairs;

    static {
        allPairs = new HashSet<>();
    }


    public PairConversation(User createdBy, User withUser) throws ConversationKeyAlreadyExistsException {
        Date createdAt = new Date();
        String conversationKey = Conversation.getUniqueKey(createdBy, createdAt);

        Set<User> members = new HashSet<User>();
        members.add(createdBy);
        members.add(withUser);

        String pair = new String();
        for (User member: members) pair = pair.concat(member.getName());

        if (!Conversation.allConversations.containsKey(conversationKey) && !allPairs.contains(pair)) {
            this.messages = new ArrayList<>();
            this.createdAt = createdAt;
            this.updatedAt = createdAt;
            this.createdBy = createdBy;

            this.members = members;
            
            Conversation.allConversations.put(conversationKey, this);
            allPairs.add(pair);
        }
        else {
            throw new ConversationKeyAlreadyExistsException(conversationKey);
        }
    }

    @Override
    public String getConversationNameForUser(User user) {
        ArrayList<User> membersCopy = new ArrayList<User>(members);
        if (membersCopy.size() > 1) membersCopy.remove(user);

        return membersCopy.get(0).getName();
    }

    @Override
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        
    }
    
    @Override
    public void addMember(String oldMemberName, String memberName) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't add member to a pair conversation");
    }
}
