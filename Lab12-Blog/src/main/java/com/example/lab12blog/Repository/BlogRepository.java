package com.example.lab12blog.Repository;

import com.example.lab12blog.Model.Blog;
import com.example.lab12blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    Blog findBlogById(Integer id);
    List<Blog> findAllByUser(User user);
    List<Blog> findAllByTitle(String title);
}
