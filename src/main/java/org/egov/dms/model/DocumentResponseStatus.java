package org.egov.dms.model;

import java.util.List;

import lombok.Data;

@Data
public class DocumentResponseStatus {

	private String statusCode;
	private String message;
	private List<DocumentEntity> documents;
	private int pendingCount;
	private int approvedCount;
}
