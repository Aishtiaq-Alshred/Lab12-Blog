package com.example.lab12blog.Service;

import com.example.lab12blog.ApiResponce.ApiException;
import com.example.lab12blog.Model.Blog;
import com.example.lab12blog.Model.User;
import com.example.lab12blog.Repository.AuthRepository;
import com.example.lab12blog.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {


    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;


    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    public void addBlog(Integer auth , Blog blog){
        User user =authRepository.findUserById(auth);
        if(user==null){
            throw new ApiException("user id not found");
        }
        blog.setUser(user);
        blogRepository.save(blog);

    }

    public void updateBlog(Integer id , Integer auth , Blog blog){
        Blog oldblog = blogRepository.findBlogById(id);
        User user = authRepository.findUserById(auth);
        if(oldblog==null){
            throw new ApiException("blog id not found");
        }
        if(oldblog.getUser().getId()!=auth){
            throw new ApiException("you have no access to this blog");
        }
        blog.setId(id);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void deleteBlog(Integer auth , Integer id){
        Blog blog =blogRepository.findBlogById(id);
        if(blog==null){
            throw new ApiException("blog id not found");
        }
        if(blog.getUser().getId()!=auth){
            throw new ApiException("you have no access to delete this blog");
        }
        blogRepository.delete(blog);
    }

    public List<Blog> getMyBlog(Integer auth){
        User user = authRepository.findUserById(auth);
        if(user==null){
            throw new ApiException("user id not found");
        }
        List<Blog> blogs = blogRepository.findAllByUser(user);
        if(blogs.isEmpty()){
            throw new ApiException("no blogs for this user");
        }

        return blogs;
    }

    public Blog getBlogById(Integer id,Integer auth){
        Blog blog =blogRepository.findBlogById(id);
        if(blog==null){
            throw new ApiException("blog id not found");
        }
        if(blog.getUser().getId()!=auth){
            throw new ApiException("you dont have access to this blog");
        }

        return blog;
    }

    public List<Blog> getBlogByTitle(String title){
        List<Blog> blog = blogRepository.findAllByTitle(title);
        if(blog.isEmpty()){
            throw new ApiException("blog not found");
        }


        return blog;
    }
}
