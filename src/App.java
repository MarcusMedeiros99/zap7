
import controllers.ConversationController;
import controllers.UserController;
import exceptions.UserIsNotAdminException;
import views.ConversationView;
import views.UserView;

public class App {
    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        ConversationController conversationController = new ConversationController();
        UserView userView = new UserView(userController);
        ConversationView conversationView = new ConversationView(conversationController);

        userController.addUser("marcus", "92995003719");
        userController.addUser("ana", "169123456789");
        userController.addUser("feliz", "11987654321");
        userController.addUser("joao", "123");
        conversationController.createPairConversationWith("marcus", "ana");
        Thread.sleep(1000);
        conversationController.createPairConversationWith("marcus", "marcus");
        
        userView.printUser("marcus");
        userView.printUser("ana");

        conversationView.printConversations("marcus");

        conversationView.printConversation("ana", 0);

        conversationController.sendTextMessage("marcus", 0, "Oi ana!");
        conversationController.sendTextMessage("marcus", 0, "Tudo bom?");
        conversationController.sendTextMessage("ana", 0, "Tudo e vc");

        conversationView.printConversation("marcus", 0);

        Thread.sleep(1000);
        conversationController.createGroupConversation("marcus", "icm7");

        conversationView.printConversations("marcus");

        conversationController.sendTextMessage("marcus", 2, "comecei o grupooooooo");

        conversationView.printConversation("marcus", 2);

        conversationController.addMemberToGroupConversation("marcus", "ana", 2);
        conversationController.addMemberToGroupConversation("marcus", "feliz", 2);

        conversationController.sendTextMessage("feliz", 0, "salve");

        conversationView.printConversation("marcus", 2);


        try {
            conversationController.addMemberToGroupConversation("feliz", "joao", 0);
        }
        catch (UserIsNotAdminException e) {
            System.out.println("Apenas administradores podem adicionar membros");
        }

        conversationController.addAdmin("marcus", "feliz", 2);
        conversationController.addMemberToGroupConversation("feliz", "joao", 0);
        

        conversationController.sendTextMessage("joao", 0, "sou o jao");
        conversationController.sendMediaMessage("marcus", 2, "AUDIO", "audio/audio1");
        conversationView.printConversation("joao", 0);

        conversationView.printAdmins("joao", 0);
        conversationView.printMembers("joao", 0);
    }
}
