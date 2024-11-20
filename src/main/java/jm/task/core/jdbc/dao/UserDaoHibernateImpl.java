package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Util.getSessionFactory();
    private static final String SQL = "CREATE TABLE IF NOT EXISTS users (" +
            "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(50), " +
            "lastName VARCHAR(50), " +
            "age TINYINT)";


    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(SQL).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица User(ов) успешно создана!");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании таблицы: " + e.getMessage(), e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            System.out.println("Таблица User(ов) удалена!");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException("Ошибка при удалении таблицы!" + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка добавления user в таблицу!" + e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("Из таблицы был удален user под номером: " + id);
            } else {
                System.out.println("User с id " + id + " не найден в базе данных.");
            }
            session.getTransaction().commit();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("FROM User", User.class).getResultList();
            session.getTransaction().commit();
            return users;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении всех users: " + e.getMessage(), e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при очистке таблицы: " + e.getMessage(), e);
        }
    }
}
