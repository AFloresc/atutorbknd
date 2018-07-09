package com.inredec.ATutor_Backend.controller;


import com.inredec.ATutor_Backend.exception.ResourceNotFoundException;
import com.inredec.ATutor_Backend.model.User;
import com.inredec.ATutor_Backend.payload.UserAuth;
import com.inredec.ATutor_Backend.payload.UserIdentityAvailability;
import com.inredec.ATutor_Backend.payload.UserProfile;
import com.inredec.ATutor_Backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/checklogin/{username}/pass/{pass}")
    public UserAuth checkUserAuthorized(@PathVariable (value = "username") String username,
                                        @PathVariable (value = "pass") String pass){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return new UserAuth(user.getPassword() == pass);
    }


    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());
        return userProfile;
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
        return userRepository.findById(userId).map(user -> {
            user.setEmail(userRequest.getEmail());
            user.setName(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());
            user.setUsername(userRequest.getUsername());
            user.setUrl_image(userRequest.getUrl_image());
            user.setRoles(userRequest.getRoles());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId ",  "not found ", userId));
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deletePost(@PathVariable Long userIf) {
        return userRepository.findById(userIf).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " ,"not found", userIf));
    }
}
