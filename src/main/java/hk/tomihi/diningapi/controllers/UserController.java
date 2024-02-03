package hk.tomihi.diningapi.controllers;


import hk.tomihi.diningapi.dto.UserDTO;
import hk.tomihi.diningapi.model.User;
import hk.tomihi.diningapi.repositories.UserRepository;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    final private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody UserDTO user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User login successfully!", HttpStatus.OK);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {
        System.out.println("registerUser");
        User user = new User(userDTO.getUsername(), userDTO.getPassword());
        return userRepository.save(user);
    }

    @GetMapping("/profile")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public Optional<User> getUserProfile(Principal principal) {
        System.out.println(principal.getName());
        return userRepository.findByUsername(principal.getName());
    }
}
