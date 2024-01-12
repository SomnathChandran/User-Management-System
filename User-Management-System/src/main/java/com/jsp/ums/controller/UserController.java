package com.jsp.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponceStructure;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/Users")
	public ResponseEntity<ResponceStructure<UserResponse>> saveUser(@RequestBody UserRequest userRequest) {
		return userService.saveUser(userRequest);
	}
	@PutMapping("/Users/{userId}")
	public ResponseEntity<ResponceStructure<UserResponse>>updateUser(@PathVariable int userId,@RequestBody UserRequest userRequest){
		return userService.updateUser(userId,userRequest);
	}
	@DeleteMapping("/Users/{userId}")
	public ResponseEntity<ResponceStructure<UserResponse>>deleteUser(@PathVariable int userId){
		return userService.deleteUser(userId);
	}
	@GetMapping("/Users/{userId}")
	public ResponseEntity<ResponceStructure<UserResponse>>getUserById(@PathVariable int userId){
		return userService.getUserById(userId);
	}
}
