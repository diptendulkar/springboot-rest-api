package com.dip.springboot.controller;

import com.dip.springboot.dto.UserDto;
import com.dip.springboot.entity.User;
import com.dip.springboot.exception.ErrorDetails;
import com.dip.springboot.exception.ResourceNotFoundException;
import com.dip.springboot.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;


    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
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
        userService.deleteUser(id);
        if (user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user) {
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
        return new ResponseEntity<>("User not Found", HttpStatus.BAD_REQUEST);

    }

/*    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER NOT FOUND"

        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }*/
}
