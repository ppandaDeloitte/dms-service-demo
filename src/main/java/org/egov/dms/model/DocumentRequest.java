package org.egov.dms.model;

import lombok.Data;

@Data
public class DocumentRequest {
 
	private Long id;
	private String docId;
    private String docName;
    private String createdBy;
    private String assignedTo;
    
    private String currentStatus;
    private String download;
    private String hindiDocId;
    private String column1;
    private String column2;
}
