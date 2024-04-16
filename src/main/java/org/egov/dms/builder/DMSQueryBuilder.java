package org.egov.dms.builder;

import org.springframework.stereotype.Component;

@Component
public class DMSQueryBuilder {

	public String queryFordocumentDetails() {
		String query = "SELECT \r\n" + 
				"    d.id AS document_id,\r\n" + 
				"    d.doc_id,\r\n" + 
				"    d.doc_name,\r\n" + 
				"    d.assigned_time,\r\n" + 
				"    d.current_status,\r\n" + 
				"    d.column1,\r\n" + 
				"    d.column2,\r\n" + 
				"    d.user_id,\r\n" + 
				"    d.state_id,\r\n" + 
				"    d.active,\r\n" + 
				"    d.hindi_doc_id,\r\n" + 
				"    d.hindi_doc_name,\r\n" + 
				"    dw.id AS workflow_id,\r\n" + 
				"    dw.document_id AS workflow_doc_id,\r\n" + 
				"    dw.from_user_submitted,\r\n" + 
				"    dw.to_user_assigned,\r\n" + 
				"    dw.active AS workflow_active,\r\n" + 
				"    dw.transition_time, \r\n" + 
				"    dw.hindi_doc_id AS workflow_hindi_doc_id\r\n" + 
				"FROM \r\n" + 
				"    public.dms_document d \r\n" + 
				"INNER JOIN \r\n" + 
				"    public.document_workflow dw ON d.id = dw.dms_doc_id\r\n" + 
				"WHERE \r\n" + 
				"    d.id = ? "
				+ "ORDER BY \r\n" + 
				"    d.assigned_time ASC";
		
		return query;
	}
	
	public String getToUserName() {
		String sql = "select du.username, du.name, du.userrole from dms_user du \r\n" + 
				"inner join document_workflow dw ON du.username = dw.to_user_assigned\r\n" + 
				"where du.username = ?";
		return sql;
	}
	public String getFromUserName() {
		String sql = "select du.name as fromusername, du.userrole as fromuserrole from dms_user du \r\n" + 
				"inner join document_workflow dw ON du.username = dw.from_user_submitted\r\n" + 
				"where du.username = ?";
		return sql;
	}
	
	
}
