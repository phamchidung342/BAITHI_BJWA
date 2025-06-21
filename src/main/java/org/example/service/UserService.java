package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String createUser(User user) {
        if (userRepository.findByName(user.getName()).isPresent()) {
            return "Error: User with this name already exists!";
        }
        userRepository.save(user);
        return "User created successfully!";
    }

    public String updateUser(User user) {
        Optional<User> exist = userRepository.findById(user.getId());
        if (exist.isPresent()) {
            userRepository.save(user);
            return "User updated successfully!";
        }
        return "Error: User not found!";
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public List<User> search(String keyword) {
        return userRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }
}
