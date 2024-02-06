package kz.job4j.cars.service.impl;

import kz.job4j.cars.models.dto.FileDto;
import kz.job4j.cars.models.dto.PostDto;
import kz.job4j.cars.models.entity.Photo;
import kz.job4j.cars.models.entity.Post;
import kz.job4j.cars.repository.PostRepository;
import kz.job4j.cars.service.FileService;
import kz.job4j.cars.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final FileService fileService;

    private final ModelMapper modelMapper;

    @Override
    public Post create(Post post) {
        return postRepository.create(post);
    }

    @Override
    public Post create(Post post, FileDto fileDto) {
        saveNewFile(post, fileDto);
        return postRepository.create(post);
    }

    private void saveNewFile(Post post, FileDto image) {
        Photo photo = fileService.save(image);
        post.setPhoto(photo);
    }

    @Override
    public void update(PostDto post) {
        log.info("update post request : [{}]", post);
        postRepository.findById(post.getId()).ifPresent(
                postToUpdate -> {
                    modelMapper.map(post, postToUpdate);
                    postRepository.update(postToUpdate);
                }
        );
    }

    @Override
    public void delete(int postId) {
        postRepository.delete(postId);
    }

    @Override
    public List<Post> findAllOrderById() {
        return postRepository.findAllOrderById();
    }

    @Override
    public Optional<Post> findById(Integer postId) {
        return postRepository.findById(postId);
    }

    @Override
    public List<Post> findForTheLastDay() {
        return postRepository.findForTheLastDay();
    }

    @Override
    public List<Post> findWithPhoto() {
        return postRepository.findWithPhoto();
    }

    @Override
    public List<Post> findCarByName(String name) {
        return postRepository.findCarByName(name);
    }

    @Override
    public Optional<Post> completePost(Integer id) {
        Optional<Post> postToUpdate = findById(id);
        if (postToUpdate.isPresent()) {
            var post = postToUpdate.get().setSold(true);
            postRepository.update(post);
        }
        return postToUpdate;
    }
}
