package org.egov.dms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.egov.dms.model.Document;
import org.egov.dms.model.DocumentEntity;
import org.egov.dms.model.DocumentRequest;
import org.egov.dms.model.DocumentResponse;
import org.egov.dms.model.DocumentResponseStatus;
import org.egov.dms.model.DocumentWorkflow;
import org.egov.dms.model.StatusResponse;
import org.egov.dms.repository.DocRepo;
import org.egov.dms.repository.DocumentRepository;
import org.egov.dms.repository.DocumentWorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository repository;

	@Autowired
	private DocRepo docRepo;
	@Autowired UserService service;
	
	@Autowired DocumentWorkflowRepository docWorkflowRepo;

	/*
	public StatusResponse alfrescoDocSave(DocumentEntity document) {
		StatusResponse status  = new StatusResponse();
		try {
			document.setAssignedTime(new Date());
			DocumentEntity entity = repository.save(document);
			
			if(entity!=null) {
				status.setStatus("201");
				status.setMessage("Data Saved Successfully");
			}
			System.out.println(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}*/

	public DocumentResponseStatus getDocumentDetailsByUserName(Document document) {
		int pendingCount = 0;
		int approvedCount = 0;
		List<DocumentEntity> documents = repository.findByCreatedUser(document.getCreatedBy());

		DocumentResponseStatus status = new DocumentResponseStatus();
		if (document != null && documents.size()>0) {
			status.setStatusCode("200");
			status.setMessage("Document Fetched Successfully");
			status.setDocuments(documents);
			for (DocumentEntity entity : documents) {
				if (entity.getCurrentStatus().equalsIgnoreCase("Pending")) {
					pendingCount++;
				} else {
					approvedCount++;
				}
			}
		}else {
			status.setStatusCode("200");
			status.setMessage("No Records Found");
			status.setDocuments(documents);
		}
		status.setApprovedCount(approvedCount);
		status.setPendingCount(pendingCount);

		System.out.println(documents);
		System.out.println(documents.size());
		return status;
	}

	/*public DocumentResponse searchDocumentByUserNameAndStatus(Document document) {
		int pendingCount = 0;
		int approvedCount = 0;
		List<Document> documentList = docRepo.searchDucumentByUserAndStatus(document);

		DocumentResponse status = new DocumentResponse();
		if (document != null && documentList.size()>0) {
			status.setStatusCode("200");
			status.setMessage("Document Fetched Successfully");
			status.setDocuments(documentList);
			for (Document entity : documentList) {
				if (entity.getCurrentStatus().equalsIgnoreCase("Pending")) {
					pendingCount++;
				} else {
					approvedCount++;
				}
			}
		}else {
			status.setStatusCode("200");
			status.setMessage("No Records Found");
			status.setDocuments(documentList);
		}
		status.setApprovedCount(approvedCount);
		status.setPendingCount(pendingCount);

		return status;
		//return  documentList;
	}
*/
	public StatusResponse alfrescoDocumetSaveModify(DocumentRequest documentrequest) {
		StatusResponse status  = new StatusResponse();
		DocumentEntity entity = new DocumentEntity();
		DocumentEntity entityResponse = new DocumentEntity();
		DocumentWorkflow workflow = new DocumentWorkflow();
		try {
			/*
			 * Data set into documentEntity
			*/
			entity.setId(documentrequest.getId());
			entity.setDocId(documentrequest.getDocId());
			entity.setDocName(documentrequest.getDocName());
			entity.setCurrentStatus(documentrequest.getCurrentStatus());
			entity.setDownload(documentrequest.getDownload());
			entity.setColumn1(documentrequest.getColumn1());
			entity.setColumn2(documentrequest.getColumn2());
			entity.setAssignedTime(new Date());
			
			workflow.setSubmittedBy(documentrequest.getCreatedBy());
			workflow.setAssignedTo(documentrequest.getAssignedTo());
			workflow.setDocumentId(documentrequest.getDocId());
			workflow.setTransitionTime(new Date());
			
			if(entity.getId()==null) {
				entityResponse =	 repository.save(entity);
				workflow.setDmsDocId(entityResponse.getId());
				workflow.setActive(true);
				docWorkflowRepo.save(workflow);
			}else {
				Optional<DocumentEntity> optionalEntity = repository.findById(entity.getId());

		        if (optionalEntity.isPresent()) {
		        	DocumentEntity entityToUpdate = optionalEntity.get();
		            
		            // Update properties of entityToUpdate using the updatedEntity
		            entityToUpdate.setDocId(entity.getDocId());
		            entityToUpdate.setDocName(entity.getDocName());
		            entityToUpdate.setCurrentStatus(entity.getCurrentStatus());
		            entityToUpdate.setDownload(entity.getDownload());
		            // Update other properties as needed
		            workflow.setDmsDocId(entityToUpdate.getId());
		            workflow.setActive(true);
		            // Save the updated entity
		            entityResponse=  repository.save(entityToUpdate);
		            
		            //check if data exists in workflow table
		            Optional<DocumentWorkflow> optionalWorkflowEntity = docWorkflowRepo.findByDmsDocId(entity.getId());
		            if (optionalWorkflowEntity.isPresent()) {
		            	DocumentWorkflow workflowToUpdate = optionalWorkflowEntity.get();
		            	//workflowToUpdate.setDmsDocId(entityToUpdate.getId());
		            	workflowToUpdate.setActive(false);
		            	//workflowToUpdate.setSubmittedBy(documentrequest.getCreatedBy());
		            	//workflowToUpdate.setAssignedTo(documentrequest.getAssignedTo());
		            	//workflowToUpdate.setDocumentId(documentrequest.getDocId());
		            	//workflowToUpdate.setTransitionTime(new Date());
		            	docWorkflowRepo.save(workflowToUpdate);
		            	
		            	System.out.println("workflow data saved successfully");
		            }else {
		            	docWorkflowRepo.save(workflow);
		            	System.out.println("Else workflow data  saved successfully");
		            }
		            docWorkflowRepo.save(workflow);
		            
		        } 
			}
			
			
			if(entityResponse!=null) {
				status.setStatus("201");
				status.setMessage("Application Send Successfully for Approval");
				status.setData(entityResponse);
			}
			System.out.println(entityResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public DocumentResponse searchDocumentByUserNameAndStatusModify(DocumentRequest document) {
		int pendingCount = 0;
		int approvedCount = 0;
		
		List<DocumentEntity> entityList = new ArrayList<DocumentEntity>();
		List<Document> documentList = new ArrayList<Document>();
		List<DocumentWorkflow> workflowList = docWorkflowRepo.searchDucumentByUserAndStatus(document.getCreatedBy());
		System.out.println(workflowList);
		
		if(!workflowList.isEmpty()) {
			for(DocumentWorkflow workflow: workflowList) {
				//workflow.getDmsDocId();
				Optional<DocumentEntity> entity =	repository.findById(workflow.getDmsDocId());
				System.out.println("Document Data ::"+entity.get());
				DocumentEntity documentResult = entity.get();
					Document doc = new Document();
					doc.setId(documentResult.getId());
					doc.setDocId(documentResult.getDocId());
					doc.setDocName(documentResult.getDocName());
					doc.setCreatedBy(workflow.getSubmittedBy());
					doc.setAssignedTo(workflow.getAssignedTo());
					doc.setAssignedTime(documentResult.getAssignedTime());
					doc.setCurrentStatus(documentResult.getCurrentStatus());
					doc.setDownload(documentResult.getDownload());
					doc.setColumn1(documentResult.getColumn1());
					doc.setColumn2(documentResult.getColumn2());
					doc.setActive(workflow.isActive());
					documentList.add(doc);
			}
		}
		
		System.out.println("Response List: "+documentList);
		
		DocumentResponse status = new DocumentResponse();
		if (documentList != null && documentList.size()>0) {
			status.setStatusCode("200");
			status.setMessage("Document Fetched Successfully");
			
			
			status.setDocuments(documentList);
			for (Document entity : documentList) {
				if (entity.getCurrentStatus().equalsIgnoreCase("Submit")) {
					pendingCount++;
				} else {
					approvedCount++;
				}
			}
		}else {
			status.setStatusCode("200");
			status.setMessage("No Records Found");
			status.setDocuments(documentList);
		}
		status.setApprovedCount(approvedCount);
		status.setPendingCount(pendingCount);

		return status;
	}

}
