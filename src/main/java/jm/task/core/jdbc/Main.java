package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        System.out.println("Создается таблица...");
        userDao.createUsersTable();
        System.out.println("Таблица создана, метод отработал успешно.");

        userDao.saveUser("Alex", "Nolan", (byte) 34);
        userDao.saveUser("July", "Bundera", (byte) 29);
        userDao.saveUser("Anna", "Smirnova", (byte) 15);
        userDao.saveUser("Andrey", "Shaman", (byte) 45);

        userDao.removeUserById(1);
        userDao.getAllUsers().forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
