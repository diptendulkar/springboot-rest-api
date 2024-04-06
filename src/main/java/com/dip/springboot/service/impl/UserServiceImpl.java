package com.dip.springboot.service.impl;

import com.dip.springboot.dto.UserDto;
import com.dip.springboot.entity.User;
import com.dip.springboot.exception.EmailAlreadyExistException;
import com.dip.springboot.exception.ResourceNotFoundException;
import com.dip.springboot.mapper.AutoUserMapper;
import com.dip.springboot.mapper.UserMapper;
import com.dip.springboot.repository.Userrepository;
import com.dip.springboot.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private Userrepository userrepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> optionalUser = userrepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistException("Email Already Exist");
        }
        //convert User DTO into User JPA entity
//        User user = UserMapper.mapToUser(userDto);
//        User user = modelMapper.map(userDto, User.class);
         User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userrepository.save(user);
        //convert user JPA entity to UserDto object
//        UserDto savedUserDto =  UserMapper.mapToUserDto(savedUser);
//        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

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

//        return userList.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
//        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userList.stream().map(user -> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto getuser(Long id) {
         User user = userrepository.findById(id).orElseThrow(
                 ()-> new ResourceNotFoundException("User","id",id.toString())
         );

//            return UserMapper.mapToUserDto(optionalUser.get());
//            return modelMapper.map(optionalUser.get(), UserDto.class);
            return AutoUserMapper.MAPPER.mapToUserDto(user);

    }

    @Override
    public UserDto updateUser(UserDto user) {
        User u = userrepository.findById(user.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Update User", "userId", user.getId().toString())
        );

        u.setEmail(user.getEmail());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        User updatedUser = userrepository.save(u);
//        return UserMapper.mapToUserDto(updatedUser);
//        return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);

    }

    @Override
    public void deleteUser(Long id) {
        User u = userrepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Delete User", "userId", id.toString())
        );
        userrepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByIdIn(List<Long> ids) {

        userrepository.deleteByIdIn(ids);
    }
}
