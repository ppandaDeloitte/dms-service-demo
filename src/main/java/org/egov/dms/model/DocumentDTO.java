package org.egov.dms.model;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;
@Data
public class DocumentDTO {

	private Long document_id;
	private String doc_id;
    private String doc_name;
    private String assigned_time;
    private String current_status;
    private String column1;
    private String column2;
    private String hindi_doc_name;
    private Long workflow_id;
    private String from_user_submitted;
    private String to_user_assigned;
    private Boolean workflow_active;
    private String transition_time;
    private String hindi_doc_id;
    private String toUserRole;
    private String toUserFullName;
    private String fromUserRole;
    private String fromUserFullName;
 
    
    
}
