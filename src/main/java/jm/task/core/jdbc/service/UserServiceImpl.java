package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    private final UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        System.out.println("Creating...");
        userDaoHibernate.createUsersTable();
        System.out.println("The Database has been created.");
    }

    public void dropUsersTable() {
        System.out.println("Deleting...");
        userDaoHibernate.dropUsersTable();
        System.out.println("The Database has been deleted.");
    }

    public void saveUser(User user) {
        saveUser(user.getName(), user.getLastName(), user.getAge());
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.printf("A user with name - %s %s - has been added to the database.\n", name, lastName);
    }

    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        System.out.println("Cleaning...");
        userDaoHibernate.cleanUsersTable();
        System.out.println("The Database has been cleaned.");
    }
}
