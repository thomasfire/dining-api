package hk.tomihi.diningapi.controllers;


import hk.tomihi.diningapi.dto.UserDTO;
import hk.tomihi.diningapi.model.User;
import hk.tomihi.diningapi.repositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    final private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {
        User user = new User(userDTO.getUsername(), userDTO.getPassword());
        return userRepository.save(user);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public Optional<User> getUserProfile(Principal principal) {
        return userRepository.findByUsername(principal.getName());
    }
}
