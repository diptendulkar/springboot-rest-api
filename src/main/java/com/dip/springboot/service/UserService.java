package com.dip.springboot.service;

import com.dip.springboot.dto.UserDto;
import com.dip.springboot.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto user);
    List<User> getAllUser();

    User getuser(Long id);

    User updateUser(User user);

    void deleteUser(Long id);
}
