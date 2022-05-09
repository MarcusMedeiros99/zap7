package controllers;

import java.util.ArrayList;

import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
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

}
