package com.example.second.service;

import com.example.second.entity.User;
import com.example.second.entity.UserRole;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findAllUsers();

    User findUser(Integer id);

    void deleteUserById(Integer id);

    void addUser(User user);

    void updateUser(User user);

    User selectByName(String username);

    int insert(User user);

    void insert_role(UserRole userRole);

    List<User> findUserByName(String username);

    List<User> findUserByStatus(Boolean enabled);

    void resetPassword(String password);
}
