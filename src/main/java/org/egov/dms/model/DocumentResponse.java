package org.egov.dms.model;

import java.util.List;

import lombok.Data;

@Data
public class DocumentResponse {

	private String statusCode;
	private String message;
	private List<Document> documents;
	private int pendingCount;
	private int approvedCount;
}
