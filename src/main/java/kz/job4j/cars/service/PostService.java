package kz.job4j.cars.service;

import kz.job4j.cars.models.dto.FileDto;
import kz.job4j.cars.models.dto.PostDto;
import kz.job4j.cars.models.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post create(Post post);

    Post create(Post post, FileDto fileDto);

    void update(PostDto post);

    void delete(int postId);

    List<Post> findAllOrderById();

    Optional<Post> findById(int postId);

    List<Post> findForTheLastDay();

    List<Post> findWithPhoto();

    List<Post> findCarByName(String name);

    Optional<Post> completePost(Integer id);
}
