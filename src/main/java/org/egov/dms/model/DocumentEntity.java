package org.egov.dms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "dms_document")
public class DocumentEntity {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		
		@Column(name = "doc_id")
	    private String docId;
		
		@Column(name = "doc_name")
	    private String docName;
		
		@Column(name = "assigned_time")
	    private Date assignedTime;
		
		@Column(name = "current_status")
	    private String currentStatus;
		
		@Column(name = "download")
	    private String download;
		@Column(name = "column1")
	    private String column1;
		@Column(name = "column2")
	    private String column2;
		/*
		 * @Column(name = "user_id") private String userId;
		 */
		/*
		 * @Column(name = "state_id") private String stateId;
		 */
		@Column(name = "active")
		private Boolean isActive;
		
		@Column(name = "hindi_doc")
		private String hindiDoc;
		
		@Column(name = "hindi_doc_id")
		private String hindiDocId;
		
		@Column(name = "hindi_doc_name")
		private String hindiDocName;
		
		
}
