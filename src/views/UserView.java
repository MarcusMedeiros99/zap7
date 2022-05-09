package views;

import controllers.UserController;
import exceptions.UserNotFoundException;
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

}
