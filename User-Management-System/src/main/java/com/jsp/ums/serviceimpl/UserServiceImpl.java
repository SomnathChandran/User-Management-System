package com.jsp.ums.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.ums.entity.User;
import com.jsp.ums.exceptions.UserNotFoundByIdException;
import com.jsp.ums.repository.UserRepo;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponceStructure;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ResponceStructure<UserResponse> structure;
	

	@Override
	public ResponseEntity<ResponceStructure<UserResponse>> saveUser(UserRequest userRequest) {
		User user = userRepo.save(mapToUser(userRequest));
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("User Successfully Saved!!");
		structure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponceStructure<UserResponse>>(structure,HttpStatus.CREATED);
	}

	private User mapToUser(UserRequest request) {
		return User.builder()
				.userName(request.getUserName())
				.email(request.getEmail())
				.password(request.getPassword())
				.build();
	}
	
	private UserResponse mapToUserResponse(User response) {
		return UserResponse.builder()
				.userId(response.getUserId())
				.userName(response.getUserName())
				.email(response.getEmail())
				.build();
	}

	@Override
	public ResponseEntity<ResponceStructure<UserResponse>> updateUser(int userId, UserRequest userRequest) {
		/*
		 * one more way to implement -------------------------------
		 *
		 * User user2 = userRepo.findById(userId).orElseThrow(()-> new
		 * RuntimeException()); user.setUserId(userId); user2 = userRepo.save(user);
		 * 
		 * ============================================================================
		 */
		// THIS IS MORE SECURE WAY OF DOING IT!!!!-------------------------------
		  User user = mapToUser(userRequest);
		  User user2 = userRepo.findById(userId).map(u -> {
			  user.setUserId(userId); 
		  return userRepo.save(user); 
		  }).orElseThrow(()->  new UserNotFoundByIdException("Failed To Found The User"));
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Successfully Updated!!");
		structure.setData(mapToUserResponse(user2));
		
		return new ResponseEntity<ResponceStructure<UserResponse>>(structure,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponceStructure<UserResponse>> deleteUser(int userId) {
	    User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundByIdException("Failed To Found The User"));
	    int id = user.getUserId();
	    userRepo.deleteById(id);
	    structure.setStatus(HttpStatus.OK.value());
	    structure.setMessage("The User Data Deleted!!");
	    structure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponceStructure<UserResponse>>(structure,HttpStatus.OK);     
	}

	@Override
	public ResponseEntity<ResponceStructure<UserResponse>> getUserById(int userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundByIdException("Failed To Found The User"));
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("The User Data Fetched Successfully");
		structure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponceStructure<UserResponse>>(structure,HttpStatus.FOUND);     
	}
}
