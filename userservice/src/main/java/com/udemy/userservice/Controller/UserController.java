package com.udemy.userservice.Controller;


import com.udemy.userservice.Service.UserService;
import com.udemy.userservice.DTO.SignupRequest;
import com.udemy.userservice.DTO.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @GetMapping("{userId}/exists")
    public ResponseEntity<Boolean> userExists(@PathVariable String userId){
        return ResponseEntity.ok(userService.userExists(userId));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody SignupRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }
}
