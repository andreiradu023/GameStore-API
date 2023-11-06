package com.andreiradu.gamestore.api.service;

import com.andreiradu.gamestore.api.exception.ResourceNotFoundException;
import com.andreiradu.gamestore.api.model.Role;
import com.andreiradu.gamestore.api.model.User;
import com.andreiradu.gamestore.api.repository.RoleRepository;
import com.andreiradu.gamestore.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with id: " + id));
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        Role role = roleRepository.findByName("USER");
        user.setRoles(Set.of(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User updatedUser) {
        User existingUser = getUserById(userId);
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) {
        getUserById(userId);
        userRepository.deleteById(userId);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found with email: " + email));
    }
}
