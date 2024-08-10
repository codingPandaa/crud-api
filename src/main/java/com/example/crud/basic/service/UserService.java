package com.example.crud.basic.service;

import java.util.List;

import com.example.crud.basic.payload.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	List<UserDto> getAllPosts();

	UserDto getUserById(long id);

	UserDto updateUser(UserDto userDto, long id);

	void deleteUserById(long id);
}
