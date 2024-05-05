package com.jwtexample.springsecurity6jwtexample.dao;

import com.jwtexample.springsecurity6jwtexample.models.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDTO, Integer> {
}
