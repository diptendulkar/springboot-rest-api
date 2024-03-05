package com.dip.springboot.repository;

import com.dip.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepository extends JpaRepository<User, Long> {
}
