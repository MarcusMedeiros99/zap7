package views;

import java.util.ArrayList;
import java.util.Collection;

import controllers.UserController;
import exceptions.UserNotFoundException;
import models.conversation.Conversation;
import models.message.Message;
import models.user.User;

public class UserView {
    private UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void printUser(String name) throws UserNotFoundException {
        User user = userController.getUser(name);
        System.out.println("User:");
        System.out.println("\tname:" + user.getName());
        System.out.println("\tphoneNumber:" + user.getPhoneNumber());
        System.out.println();
    }

    public void printConversations(String username) throws UserNotFoundException {
        User user = userController.getUser(username);
        Collection<Conversation> conversations = user.getConversations();

        int index = 1;
        System.out.println(username + " conversations:");
        for (Conversation conversation : conversations) {
            System.out.println( (index++) + " " + conversation.getConversationNameForUser(user));
        }
        System.out.println();

    }

    public void printConversation(String username, int conversationIndex) throws UserNotFoundException {
        User user = userController.getUser(username);
        Conversation conversation = userController.getConversation(username, conversationIndex);
        ArrayList<Message> messages = conversation.getMessages();


        System.out.println("Conversation " + conversation.getConversationNameForUser(user));
        for (Message message : messages) {
            System.out.println(message.getSentFrom().getName());
            System.out.println(message.getContent());
            System.out.println();
        }

    } 
}
