package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;


public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.getSessionFactory();
        UserDao userDao = new UserDaoHibernateImpl();

        System.out.println("Создается таблица с помощью Hibernate...");
        userDao.createUsersTable();
        System.out.println("Таблица создана с помощью Hibernate, метод отработал успешно.");

        userDao.saveUser("General", "@Grievous", (byte) 0);
        userDao.saveUser("Darth", "@Vader", (byte) 124);
        userDao.saveUser("Jar-Jar", "@Binks", (byte) 10);
        userDao.saveUser("Anakin", "@Skywalker", (byte) 15);

        userDao.removeUserById(1);
        userDao.getAllUsers().forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
