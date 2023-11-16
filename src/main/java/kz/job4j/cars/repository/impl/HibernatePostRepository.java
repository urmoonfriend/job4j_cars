package kz.job4j.cars.repository.impl;

import kz.job4j.cars.models.entity.Post;
import kz.job4j.cars.repository.CrudRepository;
import kz.job4j.cars.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class HibernatePostRepository implements PostRepository {
    private final CrudRepository crudRepository;

    /**
     * Создаь объявление.
     *
     * @param post объявление
     * @return post объявление.
     */
    @Override
    public Post create(Post post) {
        crudRepository.run(session -> session.persist(post));
        return post;
    }

    /**
     * Обновить объявление.
     *
     * @param post объявление.
     */
    @Override
    public void update(Post post) {
        crudRepository.run(session -> session.persist(post));
    }

    /**
     * Удалить объявление по id.
     *
     * @param postId ID
     */
    @Override
    public void delete(int postId) {
        crudRepository.run("delete from Post where id = :pId",
                Map.of("pId", postId));
    }

    /**
     * Список объявлений отсортированных по id.
     *
     * @return список объявлений.
     */
    @Override
    public List<Post> findAllOrderById() {
        return crudRepository.query("from Post order by id asc", Post.class);
    }

    /**
     * Найти объявление по ID
     *
     * @return объявление.
     */
    @Override
    public Optional<Post> findById(int postId) {
        return crudRepository.optional("from Post where id = :pId", Post.class,
                Map.of("pId", postId));
    }

    @Override
    public List<Post> findForTheLastDay() {
        List<SimpleExpression> filters = new ArrayList<>();
        filters.add(Restrictions.ge("created", LocalTime.now().minusHours(24)));
        filters.add(Restrictions.le("created", LocalTime.now()));
        return crudRepository.criteriaQuery(Post.class, filters);
    }

    @Override
    public List<Post> findWithPhoto() {
        List<SimpleExpression> filters = new ArrayList<>();
        filters.add(Restrictions.ne("photo", null));
        return crudRepository.criteriaQuery(Post.class, filters);
    }

    @Override
    public List<Post> findCarByName(String name) {
        List<SimpleExpression> filters = new ArrayList<>();
        filters.add(Restrictions.eq("car.name", name));
        return crudRepository.criteriaQuery(Post.class, filters);
    }
}
