package com.example.crud.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.basic.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
