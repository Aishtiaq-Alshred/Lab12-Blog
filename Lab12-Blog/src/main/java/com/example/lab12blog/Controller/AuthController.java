package com.example.lab12blog.Controller;


import com.example.lab12blog.ApiResponce.ApiResponse;
import com.example.lab12blog.Model.User;
import com.example.lab12blog.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/all-users")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }


    @GetMapping("/user/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(authService.getUser(id));
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user){
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("Successful registration"));
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal User auth , @Valid@RequestBody User user ){
        authService.updateUser(auth.getId(),user);
        return ResponseEntity.status(200).body(new ApiResponse("Successful user updated"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User auth ){
        authService.deleteUser(auth.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Successful user deleted"));
    }


}
