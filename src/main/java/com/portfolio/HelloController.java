package com.portfolio;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class HelloController {
    private final List<User> userList = new ArrayList<>();

    public HelloController() {
        // Initialize with sample data
        userList.add(new User(1L, "John Doe", "john.doe@example.com"));
        userList.add(new User(2L, "Jane Doe", "jane.doe@example.com"));
    }

    // Create a new user
    @PostMapping
    public User addUser(@RequestBody User user) {
        userList.add(user);
        return user;
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userList;
    }

    // Update an existing user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                return user;
            }
        }
        throw new RuntimeException("User not found with ID: " + id);
    }

    // Greet a user (example additional feature)
    @GetMapping("/greet")
    public String greetUser(@RequestParam String name) {
        return "Hello, " + name + "!";
    }
}
