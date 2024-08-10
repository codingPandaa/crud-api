package com.example.crud.basic.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.crud.basic.entity.User;
import com.example.crud.basic.exception.ResourceNotFoundException;
import com.example.crud.basic.payload.UserDto;
import com.example.crud.basic.repository.UserRepo;
import com.example.crud.basic.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepo userRepo;

	private ModelMapper mapper;

	public UserServiceImpl(UserRepo userRepo, ModelMapper mapper) {
		this.userRepo = userRepo;
		this.mapper = mapper;
	}

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = mapToEntity(userDto);

		User newUser = userRepo.save(user);

		UserDto userResponse = mapToDto(newUser);

		return userResponse;
	}

	@Override
	public List<UserDto> getAllPosts() {

		List<User> users = userRepo.findAll();
		return users.stream().map((user) -> mapToDto(user)).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(long id) {

		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return mapToDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, long id) {

		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

		user.setName(userDto.getName());
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());

		User updatedUser = userRepo.save(user);

		return mapToDto(updatedUser);
	}

	@Override
	public void deleteUserById(long id) {

		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

		userRepo.delete(user);

	}

	private UserDto mapToDto(User newUser) {

		UserDto userDto = mapper.map(newUser, UserDto.class);
		return userDto;
	}

	private User mapToEntity(UserDto userDto) {
		User user = mapper.map(userDto, User.class);
		return user;
	}

}
