package com.jsp.ums.service;

import org.springframework.http.ResponseEntity;

import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.util.ResponceStructure;

public interface UserService {

	ResponseEntity<ResponceStructure<UserResponse>> saveUser(UserRequest userRequest);

	ResponseEntity<ResponceStructure<UserResponse>> updateUser(int userId, UserRequest userRequest);

	ResponseEntity<ResponceStructure<UserResponse>> deleteUser(int userId);

	ResponseEntity<ResponceStructure<UserResponse>> getUserById(int userId);

	

}
