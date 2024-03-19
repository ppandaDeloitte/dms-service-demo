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
@Table(name = "document_workflow")
public class DocumentWorkflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dms_doc_id")
    private Long dmsDocId;
    
    @Column(name = "document_id")
    private String documentId;

 
    @Column(name = "from_user_submitted")
    private String submittedBy;


    @Column(name = "to_user_assigned")
    private String assignedTo;
    
    @Column(name = "active")
    private boolean active;

    @Column(name = "transition_time")
    private Date transitionTime;

   

   
}
