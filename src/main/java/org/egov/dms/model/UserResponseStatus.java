package org.egov.dms.model;

import lombok.Data;

@Data
public class UserResponseStatus {

	private String statusCode;
	private String message;
	private UserEntity user;
}
