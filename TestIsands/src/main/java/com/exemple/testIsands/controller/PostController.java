package com.exemple.testIsands.controller;

import com.exemple.testIsands.domain.Employee;
import com.exemple.testIsands.domain.Post;
import com.exemple.testIsands.repos.EmployeeRepos;
import com.exemple.testIsands.repos.PostRepos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    private final PostRepos postRepos;

    private final EmployeeRepos employeeRepos;

    public PostController(PostRepos postRepos, EmployeeRepos employeeRepos) {
        this.postRepos = postRepos;
        this.employeeRepos = employeeRepos;
    }

    @GetMapping("/post")
    public String getPostsList(Model model) {
        model.addAttribute("posts", postRepos.findAll());

        return "post";
    }

    @PostMapping("/post/{post_id}")
    public String updatePost(@PathVariable("post_id") Integer id,
                             @RequestParam(required = false) String postName) {
        Post post = postRepos.getOne(id);

        if (postName != null && !postName.isEmpty()) {
            post.setPostName(postName);
        }

        postRepos.save(post);

        return "redirect:/post";
    }

    @GetMapping("/addPost")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());

        return "addPost";
    }

    @PostMapping("/addPost")
    public String add(Post post) {
        post.setArchived(false);
        postRepos.save(post);

        return "redirect:/post";
    }

    @PostMapping("/archivedPost/{post_id}")
    public String archivedPost(@PathVariable("post_id") Integer id) {
        Post post = postRepos.getOne(id);

        List<Employee> employees = employeeRepos.findByPost(post);

        if (!employees.isEmpty()) {
            return "redirect:/warning/{post_id}";
        }

        post.setArchived(!post.isArchived());

        postRepos.save(post);

        return "redirect:/post";
    }

    @GetMapping("/warning/{post_id}")
    private String warning(@PathVariable("post_id") Integer id, Model model) {
        Post post = postRepos.getOne(id);

        model.addAttribute("post", post);
        model.addAttribute("employees", employeeRepos.findByPost(post));

        return "warning";
    }

    @PostMapping("/save/{post_id}")
    private String save(@PathVariable("post_id") Integer id, Model model) {
        Post post = postRepos.getOne(id);

        post.setArchived(!post.isArchived());

        postRepos.save(post);

        return "redirect:/post";
    }

    @PostMapping("/notSave")
    private String notSave() {
        return "redirect:/post";
    }
}
