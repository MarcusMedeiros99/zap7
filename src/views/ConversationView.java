package views;

import java.util.Collection;

import controllers.ConversationController;
import exceptions.UserIsNotAMemberException;
import exceptions.UserNotFoundException;
import models.conversation.Conversation;
import models.message.Message;
import models.user.User;

public class ConversationView {
    private ConversationController conversationController;

    public ConversationView(ConversationController conversationController) {
        this.conversationController = new ConversationController();
    }


    public void printConversations(String username) throws UserNotFoundException {
        Collection<Conversation> conversations = conversationController.getConversationsByUserName(username);

        int index = 1;
        System.out.println(username + " conversations:");
        for (Conversation conversation : conversations) {
            System.out.println( (index++) + " " + conversation.getConversationNameForUserName(username));
        }
        System.out.println();

    }

    public void printConversation(String username, int conversationIndex) throws UserNotFoundException {
        Conversation conversation = conversationController.getConversationByUserNameAndIndex(username, conversationIndex);
        Collection<Message> messages = conversation.getMessages();


        System.out.println("Conversation " + conversation.getConversationNameForUserName(username));
        for (Message message : messages) {
            System.out.println(message.getSentFrom().getName());
            System.out.println(message.getContent());
            System.out.println();
        }

        System.out.println();

    }

    public void printConversationMembersQuantity(String username, int conversationIndex) throws UserNotFoundException {
        int membersQuantity = conversationController.getConversationMembersQuantity(username, conversationIndex);
        System.out.println(membersQuantity + "\n");    
    }

    public void printAdmins(String username, int conversationIndex) throws UserNotFoundException, UserIsNotAMemberException {
        Collection<String> admins = conversationController.getConversationAdmins(username, conversationIndex);

        for (String admin :  admins) {
            System.out.println(admin);
        }
        System.out.println();
    }

    public void printMembers(String username, int conversationIndex) throws UserNotFoundException {
        Collection<User> members = conversationController.getConversationMembers(username, conversationIndex);

        for (User member: members) {
            System.out.println(member.getName());
        }
        System.out.println();
    }

}
