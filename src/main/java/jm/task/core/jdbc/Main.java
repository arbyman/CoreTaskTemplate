package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("Tom", "Hardy", (byte) 43);
        User user2 = new User("Tom", "Cruise", (byte) 58);
        User user3 = new User("Tom", "Hanks", (byte) 64);
        User user4 = new User("Tom", "Holland", (byte) 24);
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
        userService.saveUser(user4);
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
