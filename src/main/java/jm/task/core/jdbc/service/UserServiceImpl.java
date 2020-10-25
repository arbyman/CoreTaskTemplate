package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {
        System.out.println("Creating...");
        userDaoJDBC.createUsersTable();
        System.out.println("The Database has been created.");
    }

    public void dropUsersTable() {
        System.out.println("Deleting...");
        userDaoJDBC.dropUsersTable();
        System.out.println("The Database has been deleted.");
    }

    public void saveUser(User user) {
        saveUser(user.getName(), user.getLastName(), user.getAge());
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        System.out.printf("A user with name - %s %s - has been added to the database.\n", name, lastName);
    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        System.out.println("Clearing...");
        userDaoJDBC.cleanUsersTable();
        System.out.println("The Database has been cleared.");
    }
}
