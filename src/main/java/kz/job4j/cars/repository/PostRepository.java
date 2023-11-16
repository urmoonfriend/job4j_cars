package kz.job4j.cars.repository;

import kz.job4j.cars.models.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post create(Post post);

    void update(Post post);

    void delete(int postId);

    List<Post> findAllOrderById();

    Optional<Post> findById(int postId);

    List<Post> findForTheLastDay();

    List<Post> findWithPhoto();

    List<Post> findCarByName(String name);
}
