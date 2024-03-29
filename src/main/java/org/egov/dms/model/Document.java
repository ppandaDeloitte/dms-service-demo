package org.egov.dms.model;

import java.util.Date;

import lombok.Data;

@Data
public class Document {
 
	private Long id;
	private String docId;
    private String docName;
    private String createdBy;
    private String assignedTo;
    private Date assignedTime;
    private String currentStatus;
    private String download;
    private String column1;
    private String column2;
    private int userId;
    private int stateId;
    private boolean active;
}
