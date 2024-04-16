package org.egov.dms.model;

import lombok.Data;

@Data
public class StatusResponse {

	private String status;
	private String message;
	private DocumentEntity data;
	private DocumentWorkflow documentWorkflow;
}
