package com.dip.springboot.service.impl;

import com.dip.springboot.dto.UserDto;
import com.dip.springboot.entity.User;
import com.dip.springboot.repository.Userrepository;
import com.dip.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private Userrepository userrepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        //convert User DTO into User JPA entity
        User user = new User(
                userDto.getId()==null?0: userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        User savedUser = userrepository.save(user);
        //convert user JPA entity to UserDto object
        UserDto savedUserDto = new UserDto(
               savedUser.getId(),
               savedUser.getFirstName(),
               savedUser.getLastName(),
                savedUser.getEmail()
        );
        return savedUserDto;

    }

    @Override
    public List<User> getAllUser() {
        return userrepository.findAll();
    }

    @Override
    public User getuser(Long id) {
        Optional<User> optionalUser = userrepository.findById(id);
        if(optionalUser.isPresent())
            return optionalUser.get();
        return null;
    }

    @Override
    public User updateUser(User user) {
        User u = userrepository.findById(user.getId()).get();

        u.setEmail(user.getEmail());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        return userrepository.save(u);

    }

    @Override
    public void deleteUser(Long id) {
        userrepository.deleteById(id);
    }
}
