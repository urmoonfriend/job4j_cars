package kz.job4j.cars.repository.impl;

import kz.job4j.cars.models.User;
import kz.job4j.cars.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class HibernateUserRepository implements UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    @Override
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    @Override
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE User SET login = :uLogin, password = :uPassword WHERE id = :uId")
                    .setParameter("uLogin", user.getLogin())
                    .setParameter("uPassword", user.getPassword())
                    .setParameter("uId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    @Override
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    @Override
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        return session.createQuery("from User order by id").list();
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    @Override
    public Optional<User> findById(int userId) {
        Session session = sf.openSession();
        return session.createQuery("from User as i where i.id = :uId")
                .setParameter("uId", userId).uniqueResultOptional();
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    @Override
    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        return session.createQuery("from User as i where i.login like :uLogin")
                .setParameter("uLogin", "%" + key + "%").list();
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    @Override
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        return session.createQuery("from User as i where i.login = :uLogin")
                .setParameter("uLogin", login).uniqueResultOptional();
    }
}
