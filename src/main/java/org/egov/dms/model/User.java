package org.egov.dms.model;

import lombok.Data;

@Data
public class User {

	private String userName;
	private String password;
	private String userRole;
	private String email;
}
