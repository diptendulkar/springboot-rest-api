package com.dip.springboot.repository;

import com.dip.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Userrepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


    void deleteByIdIn(List<Long> ids);
}
