package com.dip.springboot.service;

import com.dip.springboot.dto.UserDto;
import com.dip.springboot.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto user);
    List<UserDto> getAllUser();

    UserDto getuser(Long id);

    UserDto updateUser(UserDto user);

    void deleteUser(Long id);
}
