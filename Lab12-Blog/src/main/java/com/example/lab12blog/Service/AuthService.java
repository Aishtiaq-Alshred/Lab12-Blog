package com.example.lab12blog.Service;


import com.example.lab12blog.ApiResponce.ApiException;
import com.example.lab12blog.Model.User;
import com.example.lab12blog.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;


    public List<User> getAllUser(Integer id){

        User user=authRepository.findUserById(id);

        if(user==null){

            throw new ApiException("user not found");
        }
        return authRepository.findAll();
    }



    public void register(User user){

        String hash=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);

    }

    public void updateUser(Integer auth,User user){
        User olduser = authRepository.findUserById(auth);
        if(olduser==null){
            throw new ApiException("user id not found");
        }
        olduser.setUsername(user.getUsername());
        String hash = new BCryptPasswordEncoder().encode(olduser.getPassword());
        olduser.setPassword(hash);
        authRepository.save(olduser);

    }

    public void deleteUser(Integer auth){
        User user = authRepository.findUserById(auth);
        if(user==null){
            throw new ApiException("id user not found");
        }


        authRepository.delete(user);
    }


}
