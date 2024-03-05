package com.dip.springboot.service.impl;

import com.dip.springboot.dto.UserDto;
import com.dip.springboot.entity.User;
import com.dip.springboot.mapper.UserMapper;
import com.dip.springboot.repository.Userrepository;
import com.dip.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private Userrepository userrepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        //convert User DTO into User JPA entity
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userrepository.save(user);
        //convert user JPA entity to UserDto object
        UserDto savedUserDto =  UserMapper.mapToUserDto(savedUser);
        return savedUserDto;

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userrepository.findAll();
       /* List<UserDto> userDtoList = new ArrayList<>();
        for(User u : userList)
        {
            userDtoList.add(UserMapper.mapToUserDto(u));
        }
        return  userDtoList;*/

        return userList.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getuser(Long id) {
        Optional<User> optionalUser = userrepository.findById(id);
        if(optionalUser.isPresent()){
           return UserMapper.mapToUserDto(optionalUser.get());

        }

        return null;
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User u = userrepository.findById(user.getId()).get();

        u.setEmail(user.getEmail());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        User updatedUser = userrepository.save(u);
        return UserMapper.mapToUserDto(updatedUser);

    }

    @Override
    public void deleteUser(Long id) {
        userrepository.deleteById(id);
    }
}
