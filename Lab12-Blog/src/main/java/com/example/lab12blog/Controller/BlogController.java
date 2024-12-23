package com.example.lab12blog.Controller;

import com.example.lab12blog.ApiResponce.ApiResponse;
import com.example.lab12blog.Model.Blog;
import com.example.lab12blog.Model.User;
import com.example.lab12blog.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    @GetMapping("/get")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }
    @PostMapping("/add")
    public ResponseEntity addBlog(@Valid @RequestBody Blog blog , @AuthenticationPrincipal User user){
        blogService.addBlog(user.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@PathVariable Integer id , @Valid @RequestBody Blog blog , @AuthenticationPrincipal User user){
        blogService.updateBlog(id, user.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@PathVariable Integer id ,@AuthenticationPrincipal User user){
        blogService.deleteBlog(id, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("blog deleted"));
    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id,@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getBlogById(id, user.getId()));
    }
    @GetMapping("/getMyBlogs")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getMyBlog(user.getId()));
    }
}
