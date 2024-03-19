package org.egov.dms.service;

import org.egov.dms.model.UserEntity;
import org.egov.dms.model.UserResponseStatus;
import org.egov.dms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired private UserRepository repository;
	
	public ResponseEntity<UserEntity> userRegister(UserEntity user) {
		try {
			UserEntity savedUser = repository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	public UserResponseStatus loginByUserName(UserEntity user) {
		System.out.println("UserName :"+user.getUserName() +"-----password"+user.getPassword());
		UserResponseStatus status = new UserResponseStatus();
		
		UserEntity entity=	repository.findByName(user.getUserName());
		System.out.println(entity);
		if(entity!=null){
			if(entity.getPassword().equals(user.getPassword())) {
				System.out.println("login Success");
				status.setStatusCode("200");
				status.setMessage("User Authorized");
				status.setUser(entity);
				//return ResponseEntity.ok(entity);
			}else {
				System.out.println("Login Failed");
				status.setStatusCode("400");
				status.setMessage("User UnAuthorized");
				//return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credential");
			}
		}else {
			System.out.println("User Not Found");
			status.setStatusCode("200");
			status.setMessage("User Not Found");
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
		}
		return status;
		
	}
	public UserEntity getUserDetailsByName(String createdBy) {
		
		UserEntity entity=	repository.findByName(createdBy);
		return entity;
	}
}
