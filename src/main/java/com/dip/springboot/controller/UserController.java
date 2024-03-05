package com.dip.springboot.controller;

import com.dip.springboot.dto.UserDto;
import com.dip.springboot.entity.User;
import com.dip.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;


    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto saveUser = userService.createUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userList = userService.getAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto user = userService.getuser(id);
        if (user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) {
        UserDto updateUser = userService.updateUser(user);

        return new ResponseEntity<>(updateUser, HttpStatus.OK);

        // return new ResponseEntity<>(new String("No ID"),HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        UserDto user = userService.getuser(id);
        if (user != null) {
            userService.deleteUser(id);
            return new ResponseEntity<>("User Deleted Scussfully", HttpStatus.OK);
        }
        return  new ResponseEntity<>("User not Found",HttpStatus.BAD_REQUEST);

    }
}
