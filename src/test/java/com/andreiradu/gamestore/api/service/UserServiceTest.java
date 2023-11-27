package com.andreiradu.gamestore.api.service;

import com.andreiradu.gamestore.api.exception.ResourceNotFoundException;
import com.andreiradu.gamestore.api.exception.UserBadRequestException;
import com.andreiradu.gamestore.api.model.Role;
import com.andreiradu.gamestore.api.model.User;
import com.andreiradu.gamestore.api.repository.RoleRepository;
import com.andreiradu.gamestore.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void getAllUsersTest() {
        // given
        User user1 = new User(
                1L,
                "John",
                "Doe",
                "johndoe@gmail.com",
                "1234567890",
                "Romania",
                "password",
                Set.of(),
                Set.of(new Role("ADMIN"))
        );

        User user2 = new User(
                2L,
                "Jerry",
                "Lee",
                "jerrylee@gmail.com",
                "2342342342",
                "Romania",
                "password",
                Set.of(),
                Set.of(new Role("USER"))
        );

        List<User> userList = List.of(user1, user2);
        when(userRepository.findAll()).thenReturn(userList);

        // when
        List<User> actualUsers = userService.getAllUser();

        // then
        assertThat(actualUsers).hasSize(2);
        assertThat(actualUsers).isEqualTo(userList);
    }

    @Test
    void getUserByIdTest() {
        // given
        User user = new User(
                1L,
                "John",
                "Doe",
                "johndoe@gmail.com",
                "1234567890",
                "Romania",
                "password",
                Set.of(),
                Set.of(new Role("ADMIN"))
        );
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        // when
        User actualUsers = userService.getUserById(anyLong());

        // then
        assertThat(actualUsers).isEqualTo(user);
    }

    @Test
    void getUserById_ThrowsExceptionTest() {
        // given
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // then
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(anyLong()));
    }

    @Test
    void createUserTest() {
        // given
        User user = new User(
                1L,
                "John",
                "Doe",
                "johndoe@gmail.com",
                "1234567890",
                "Romania",
                "password",
                Set.of(),
                Set.of(new Role("ADMIN"))
        );
        when(roleRepository.findByName("USER")).thenReturn(new Role("USER"));
        when(userRepository.save(user)).thenReturn(user);

        // when
        User actualUser = userService.createUser(user);

        // then
        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    void createUser_ThrowsNotUniqueExceptionTest() {
        // given
        User user = new User(
                1L,
                "John",
                "Doe",
                "johndoe@gmail.com",
                "1234567890",
                "Romania",
                "password",
                Set.of(),
                Set.of(new Role("ADMIN"))
        );
        when(userRepository.findUserByEmail("johndoe@gmail.com")).thenReturn(Optional.of(user));

        // when
        assertThrows(UserBadRequestException.class, () -> userService.createUser(user));

        // then
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateUserTest() {
        // given
        User userToUpdate = new User(
                1L,
                "John",
                "Doe",
                "johndoe@gmail.com",
                "1234567890",
                "Romania",
                "password",
                Set.of(),
                Set.of(new Role("ADMIN"))
        );

        User updatedUser = new User(
                1L,
                "John",
                "Doe",
                "johndoe@gmail.com",
                "5554443331",
                "Romania, Str. test",
                "password5",
                Set.of(),
                Set.of(new Role("USER"))
        );
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userToUpdate));
        when(userRepository.save(userToUpdate)).thenReturn(updatedUser);

        // when
        User actualUsers = userService.updateUser(anyLong(), updatedUser);

        // then
        assertThat(actualUsers).isEqualTo(updatedUser);
    }

    @Test
    void deleteUserTest() {
        // given
        User user = new User(
                1L,
                "John",
                "Doe",
                "johndoe@gmail.com",
                "1234567890",
                "Romania",
                "password",
                Set.of(),
                Set.of(new Role("ADMIN"))
        );

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // when
        userService.deleteUser(1L);

        // then
        verify(userRepository).deleteById(1L);
    }

    @Test
    void findUserByEmailTest() {
        // given
        User user = new User(
                1L,
                "John",
                "Doe",
                "johndoe@gmail.com",
                "1234567890",
                "Romania",
                "password",
                Set.of(),
                Set.of(new Role("ADMIN"))
        );
        when(userRepository.findUserByEmail("johndoe@gmail.com")).thenReturn(Optional.of(user));

        // when
        User actualUser = userService.findUserByEmail("johndoe@gmail.com");

        // then
        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    void findUserByEmailThrowsExceptionTest() {
        // given
        when(userRepository.findUserByEmail("johndoe@gmail.com")).thenReturn(Optional.empty());

        // then
        assertThrows(ResourceNotFoundException.class, () -> userService.findUserByEmail("johndoe@gmail.com"));
    }

    @Test
    void findAllByPageWithKeywordTest() {
        // given
        int pageNum = 1;
        int pageSize = 10;
        String sortField = "firstName";
        String sortDir = "asc";
        String keyword = "John";
        User user1 = new User(
                1L,
                "John",
                "Doe",
                "johndoe@gmail.com",
                "1234567890",
                "Romania",
                "password",
                Set.of(),
                Set.of(new Role("ADMIN"))
        );
        User user2 = new User(
                2L,
                "John",
                "Lee",
                "johnlee@gmail.com",
                "2342342342",
                "Romania",
                "password",
                Set.of(),
                Set.of(new Role("USER"))
        );

        List<User> userList = List.of(user1, user2);

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(sortField).ascending());
        when(userRepository.findAllByFirstNameContainsOrLastNameContaining(keyword, keyword, pageable))
                .thenReturn(new PageImpl<>(userList, pageable, userList.size()));

        // when
        Page<User> resultPage = userService.findAllByPage(pageNum, pageSize, sortField, sortDir, keyword);

        // then
        assertThat(resultPage).isNotNull();
        assertThat(resultPage.getContent()).hasSize(2);
        assertThat(resultPage.getContent()).containsExactly(user1, user2);
    }

    @Test
    void findAllByPageWithoutKeywordTest() {
        // given
        int pageNum = 1;
        int pageSize = 10;
        String sortField = "firstName";
        String sortDir = "desc";
        User user1 = new User(
                1L,
                "John",
                "Doe",
                "johndoe@gmail.com",
                "1234567890",
                "Romania",
                "password",
                Set.of(),
                Set.of(new Role("ADMIN"))
        );
        User user2 = new User(
                2L,
                "Jerry",
                "Lee",
                "jerrylee@gmail.com",
                "2342342342",
                "Romania",
                "password",
                Set.of(),
                Set.of(new Role("USER"))
        );
        List<User> userList = List.of(user1, user2);

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(sortField).descending());
        when(userRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(userList, pageable, userList.size()));

        // when
        Page<User> resultPage = userService.findAllByPage(pageNum, pageSize, sortField, sortDir, "");

        // then
        assertThat(resultPage).isNotNull();
        assertThat(resultPage.getContent()).hasSize(2);
        assertThat(resultPage.getContent()).containsExactly(user1, user2);
    }
}