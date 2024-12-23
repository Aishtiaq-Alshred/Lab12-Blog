package com.example.lab12blog.Service;

import com.example.lab12blog.ApiResponce.ApiException;
import com.example.lab12blog.Model.User;
import com.example.lab12blog.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =authRepository.findUserByUsername(username);
        if(user==null){
            throw new ApiException("user id not found");
        }
        return user;
    }



}
