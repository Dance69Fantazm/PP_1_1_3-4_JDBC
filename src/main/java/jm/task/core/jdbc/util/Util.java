package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_hibernate";
    private static final String USER = "root";
    private static final String PASSWORD = "Config$MySQL!1";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DIALECT = "org.hibernate.dialect.MySQL8Dialect";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
            throw new RuntimeException("Не удается подключиться к базе данных", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.setProperty(Environment.DRIVER, DRIVER);
            settings.setProperty(Environment.URL, URL);
            settings.setProperty(Environment.USER, USER);
            settings.setProperty(Environment.PASS, PASSWORD);
            settings.put(Environment.DIALECT, DIALECT);
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.SHOW_SQL, true);
            settings.setProperty(Environment.FORMAT_SQL, "true");
            settings.setProperty(Environment.HBM2DDL_AUTO, "create");
            configuration.addProperties(settings);
            configuration.addAnnotatedClass(User.class);
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Ошибка подключения к базе данных через Hibernate: " + e.getMessage());
            throw new RuntimeException("Не удалось подключиться к базе данных через Hibernate!", e);
        }
    }
}
