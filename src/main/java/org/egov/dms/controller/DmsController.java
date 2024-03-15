package org.egov.dms.controller;

import org.egov.dms.model.Document;
import org.egov.dms.model.DocumentEntity;
import org.egov.dms.model.DocumentResponse;
import org.egov.dms.model.DocumentResponseStatus;
import org.egov.dms.model.StatusResponse;
import org.egov.dms.model.UserEntity;
import org.egov.dms.model.UserResponseStatus;
import org.egov.dms.service.DocumentService;
import org.egov.dms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DmsController {

	@Autowired
 private UserService userService;
	
	@Autowired private DocumentService docService;
	
	@PostMapping(value = "/saveUser")
	public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {
		System.out.println("Hi this is login");
		return userService.userRegister(user);
		
	}
	@PostMapping(value = "/login")
	public UserResponseStatus loginUser(@RequestBody UserEntity user) {
		System.out.println("Hi this is login");
		return userService.loginByUserName(user);
		
	}
	
	@PostMapping(value = "/documentSave")
	public StatusResponse alfrescoDocumetSave(@RequestBody DocumentEntity document) {
		System.out.println("Inside Document save");
		StatusResponse status  =	docService.alfrescoDocSave(document);
		return status;
	}
	
	/*
	@PostMapping(value = "/getDocumentDetailsByUserName")
	public DocumentResponseStatus getDocumentDetailsByUserName(@RequestBody Document document) {
		return docService.getDocumentDetailsByUserName(document);
	}
	*/
	@PostMapping(value = "/searchDocumentByUserNameAndStatus")
	public DocumentResponse searchDocumentByUserNameAndStatus(@RequestBody Document document) {
		System.out.println("Inside Document Filter");
	return	docService.searchDocumentByUserNameAndStatus(document);
		
		
	}
	
	
	
}
