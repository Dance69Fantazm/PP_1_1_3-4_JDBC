package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
    private static final UserDaoHibernateImpl userDao2 = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        userDao2.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDao2.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao2.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userDao2.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao2.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDao2.cleanUsersTable();
    }
}
