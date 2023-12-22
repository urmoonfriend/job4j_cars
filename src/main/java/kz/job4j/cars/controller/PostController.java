package kz.job4j.cars.controller;

import kz.job4j.cars.models.dto.FileDto;
import kz.job4j.cars.models.dto.PostDto;
import kz.job4j.cars.models.entity.Post;
import kz.job4j.cars.models.entity.User;
import kz.job4j.cars.service.CarService;
import kz.job4j.cars.service.PostService;
import kz.job4j.cars.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostController {
    private final PostService postService;

    private final UserService userService;

    private final CarService carService;

    @GetMapping
    public String getAll(Model model, HttpSession session) {
        model.addAttribute(
                "posts",
                postService.findAllOrderById());
        return "posts/list";
    }

    @GetMapping("/{id}")
    public String getPost(Model model, @PathVariable Integer id, HttpSession session) {
        log.info("getPost method request: [{}]", id);
        Optional<Post> postOpt = postService.findById(id);
        if (postOpt.isEmpty()) {
            model.addAttribute("message", "Объявление с указанным идентификатором не найдено");
            return "errors/404";
        }
        model.addAttribute(
                "post", postOpt.get());
        return "posts/one";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("cars", carService.findAllOrderById());
        return "posts/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Post post, @RequestParam MultipartFile file, Model model, HttpServletRequest request) {
        try {
            var user = (User) request.getAttribute("user");
            log.info("create method user: [{}]", user);
            var userOpt = userService.findById(user.getId());
            userOpt.ifPresent(findedUser -> {
                post.setUserListToNotificate(List.of(findedUser));
                post.setUser(findedUser);
            });
            var carOpt = carService.findById(post.getCar().getId());
            carOpt.ifPresent(post::setCar);
            postService.create(post, new FileDto(file.getOriginalFilename(), file.getBytes()));
            return "redirect:/posts";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var vacancyToDelete = postService.findById(id);
        if (vacancyToDelete.isEmpty()) {
            model.addAttribute("message", "Объявление с указанным идентификатором не найдено");
            return "errors/404";
        }
        postService.delete(id);
        return "redirect:/posts";
    }

    @GetMapping("/sold/{id}")
    public String complete(Model model, @PathVariable("id") Integer id, HttpSession session) {
        var postToUpdate = postService.findById(id);
        var userWantToUpdate = (User) session.getAttribute("user");
        if (postToUpdate.isEmpty()) {
            model.addAttribute("message", "Объявление с указанным идентификатором не найдено");
            return "errors/404";
        }
        if (!userWantToUpdate.getId().equals(postToUpdate.get().getUser().getId())) {
            model.addAttribute("message", "Вы не являетесь автором объявления");
            return "errors/404";
        }
        Optional<Post> postOpt = postService.completePost(id);
        model.addAttribute("post", postOpt.get());
        return "posts/one";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(Model model, @PathVariable("id") Integer id, HttpSession session) {
        var postToUpdate = postService.findById(id);
        var userWantToUpdate = (User) session.getAttribute("user");
        if (postToUpdate.isEmpty()) {
            model.addAttribute("message", "Объявление с указанным идентификатором не найдено");
            return "errors/404";
        }
        if (!userWantToUpdate.getId().equals(postToUpdate.get().getUser().getId())) {
            model.addAttribute("message", "Вы не являетесь автором объявления");
            return "errors/404";
        }
        model.addAttribute("post", postToUpdate.get());
        model.addAttribute("cars", carService.findAllOrderById());
        return "posts/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute PostDto post, Model model) {
        var postToUpdate = postService.findById(post.getId());
        post.setPhoto(postToUpdate.get().getPhoto());
        postService.update(post);
        model.addAttribute("post", postToUpdate.get());
        return "posts/one";
    }

}
