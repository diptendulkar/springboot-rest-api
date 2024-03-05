package com.dip.springboot.service.impl;

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
    public User createUser(User user) {
        return userrepository.save(user);
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
