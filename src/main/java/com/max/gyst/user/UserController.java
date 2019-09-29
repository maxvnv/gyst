package com.max.gyst.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static java.lang.Long.parseLong;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("user")
    public ResponseEntity createUser(@RequestBody @Valid User user) {
        userRepository.save(user);

        return ResponseEntity.created(URI.create(String.format("/user/%s", user.getId()))).build();
    }

    @GetMapping("user/{userId}")
    public User getUser(@PathVariable String userId) {
        return userRepository.findById(parseLong(userId)).orElseThrow(UserNotFoundException::new);
    }
}