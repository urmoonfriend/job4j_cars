package kz.job4j.cars.controller;

import kz.job4j.cars.EnvConfigTest;
import kz.job4j.cars.models.dto.FileDto;
import kz.job4j.cars.models.dto.PostDto;
import kz.job4j.cars.models.entity.*;
import kz.job4j.cars.service.CarService;
import kz.job4j.cars.service.PostService;
import kz.job4j.cars.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PostControllerTest extends EnvConfigTest {
    @MockBean
    private PostService posts;
    @MockBean
    private CarService cars;
    @MockBean
    private UserService users;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private User user;
    private Owner owner;
    private Engine engine;
    private Car car;

    @BeforeEach
    public void init() {
        user = new User();
        user.setId(1);
        user.setName("user");
        user.setLogin("login");
        user.setPassword("password");

        owner = new Owner();
        owner.setId(1);
        owner.setName("owner");
        owner.setHistoryOwners(new HashSet<>());
        owner.setUser(user);

        engine = new Engine();
        engine.setId(1);
        engine.setName("engine");

        car = new Car();
        car.setId(1);
        car.setName("name");
        car.setEngine(engine);
        car.setHistoryOwners(new HashSet<>());
    }

    @Test
    @WithMockUser
    public void whenCreateThenOk() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE, "Test content".getBytes());
        Post post = new Post().setTitle("title").setCar(car).setUser(user);
        MockHttpServletRequestBuilder builder = multipart("/posts/create")
                .file(file)
                .flashAttr("post", post)
                .sessionAttr("user", user);
        when(cars.findById(any(Integer.class))).thenReturn(Optional.of(car));
        when(users.findById(any(Integer.class))).thenReturn(Optional.of(user));
        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).create(argument.capture(), any(FileDto.class));
        assertThat(argument.getValue().getTitle()).isEqualTo("title");
    }

    @Test
    @WithMockUser
    public void whenCreateThenNotError() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE, "Test content".getBytes());
        Post post = new Post().setTitle("title").setUser(user);
        MockHttpServletRequestBuilder builder = multipart("/posts/create")
                .file(file)
                .flashAttr("post", post)
                .sessionAttr("user", user);
        when(cars.findById(any(Integer.class))).thenReturn(Optional.of(car));
        when(users.findById(any(Integer.class))).thenReturn(Optional.of(user));
        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("errors/404"));
    }

    @Test
    @WithMockUser
    public void whenUpdateThenOk() throws Exception {
        PostDto postDto = new PostDto().setId(1).setTitle("title2").setCar(car);
        Photo photo = new Photo().setId(1).setName("photo").setPath("/path");
        Post post = new Post().setId(1).setTitle("title").setCar(car).setPhoto(photo);
        post.setPhoto(photo);
        when(posts.findById(any(Integer.class))).thenReturn(Optional.of(post));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/posts/update")
                        .flashAttr("postDto", postDto)
                        .sessionAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("posts/one"));
        ArgumentCaptor<PostDto> argument = ArgumentCaptor.forClass(PostDto.class);
        verify(posts).update(argument.capture());
        assertThat(argument.getValue().getTitle()).isEqualTo("title2");
    }

    @Test
    @WithMockUser
    public void whenUpdateThenNotFound() throws Exception {
        PostDto postDto = new PostDto();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/posts/update")
                        .flashAttr("postDto", postDto)
                        .sessionAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("errors/404"));
        verify(posts).findById(any());
    }

}
