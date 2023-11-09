package com.andreiradu.gamestore.api.service;

import com.andreiradu.gamestore.api.exception.ResourceNotFoundException;
import com.andreiradu.gamestore.api.exception.UserBadRequestException;
import com.andreiradu.gamestore.api.model.Role;
import com.andreiradu.gamestore.api.model.User;
import com.andreiradu.gamestore.api.repository.RoleRepository;
import com.andreiradu.gamestore.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        if (!isEmailUnique(user.getEmail())) {
            throw new UserBadRequestException("User is not unique");
        }
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
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
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

    public boolean isEmailUnique(String email) {
        return userRepository.findUserByEmail(email).isEmpty();
    }

    public Page<User> findAllByPage(int pageNum, int pageSize, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        if (pageNum < 1) pageNum = 1;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);

        Page<User> page;
        if (keyword != null && !keyword.isEmpty()) {
            page = userRepository.findAllByFirstNameContainsOrLastNameContaining(keyword, keyword, pageable);
        } else {
            page = userRepository.findAll(pageable);
        }
        return page;
    }
}
