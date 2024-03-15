package org.egov.dms.service;

import java.util.Date;
import java.util.List;

import org.egov.dms.model.Document;
import org.egov.dms.model.DocumentEntity;
import org.egov.dms.model.DocumentResponse;
import org.egov.dms.model.DocumentResponseStatus;
import org.egov.dms.model.StatusResponse;
import org.egov.dms.repository.DocRepo;
import org.egov.dms.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository repository;

	@Autowired
	private DocRepo docRepo;

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
	}

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

	public DocumentResponse searchDocumentByUserNameAndStatus(Document document) {
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

}
