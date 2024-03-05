package com.dip.springboot.mapper;

import com.dip.springboot.dto.UserDto;
import com.dip.springboot.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        //convert user JPA entity to UserDto object
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    public static  User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId()==null?0: userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return  user;
    }
}
