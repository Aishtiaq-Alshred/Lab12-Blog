package com.example.lab12blog.Repository;

import com.example.lab12blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {

    User findUserByUsername(String username);

    User findUserById(Integer user_id);
}