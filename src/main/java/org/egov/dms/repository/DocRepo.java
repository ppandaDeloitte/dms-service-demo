package org.egov.dms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egov.dms.builder.DMSQueryBuilder;
import org.egov.dms.model.Document;
import org.egov.dms.model.DocumentDTO;
import org.egov.dms.model.DocumentRequest;
import org.egov.dms.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DocRepo {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	DMSQueryBuilder builder;

	public List<Document> searchDucumentByUserAndStatus(Document documentCriteria) {
		String sql = "SELECT * FROM dms_document WHERE 1=1 ";

		Map<String, Object> paramMap = new HashMap<>();
		if (documentCriteria.getCreatedBy() != null && !documentCriteria.getCreatedBy().isEmpty()) {
			sql += "AND created_by = :createdBy ";
			paramMap.put("createdBy", documentCriteria.getCreatedBy());
		}
		if (documentCriteria.getCurrentStatus() != null && !documentCriteria.getCurrentStatus().isEmpty()) {
			sql += "AND current_status = :currentStatus ";
			paramMap.put("currentStatus", documentCriteria.getCurrentStatus());
		}

		System.out.println("Executing Query::" + sql);

		List<Document> documentList = namedJdbcTemplate.query(sql, paramMap, new RowMapper<Document>() {

			@Override
			public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
				Document doc = new Document();
				doc.setId((long) rs.getInt("id"));
				doc.setDocId(rs.getString("doc_id"));
				doc.setDocName(rs.getString("doc_name"));
				doc.setCreatedBy(rs.getString("created_by"));
				doc.setAssignedTo(rs.getString("assigned_to"));
				doc.setAssignedTime(rs.getDate("assigned_time"));
				doc.setCurrentStatus(rs.getString("current_status"));
				doc.setDownload(rs.getString("download"));
				doc.setColumn1(rs.getString("column1"));
				doc.setColumn2(rs.getString("column2"));
				return doc;
			}
		});
		return documentList;
	}

	public List<DocumentDTO> getDocumentDetailsWorkflow(DocumentRequest document) {
		List<DocumentDTO> dtoList = new ArrayList<DocumentDTO>();
		// List<UserDTO> userList = new ArrayList<UserDTO>();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("doc_id", document.getId());
		try {
			jdbcTemplate.query(builder.queryFordocumentDetails(), new Object[] { document.getId() }, rs -> {

				DocumentDTO dto = new DocumentDTO();
				dto.setDocument_id(rs.getLong("document_id"));
				dto.setDoc_id(rs.getString("doc_id"));
				dto.setDoc_name(rs.getString("doc_name"));
				dto.setAssigned_time(rs.getDate("assigned_time"));
				dto.setCurrent_status(rs.getString("current_status"));
				dto.setColumn1(rs.getString("column1"));
				dto.setColumn2(rs.getString("column2"));
				dto.setHindi_doc_name(rs.getString("hindi_doc_name"));
				dto.setWorkflow_id(rs.getLong("workflow_id"));
				dto.setFrom_user_submitted(rs.getString("from_user_submitted"));
				dto.setTo_user_assigned(rs.getString("to_user_assigned"));
				dto.setWorkflow_active(rs.getBoolean("workflow_active"));
				dto.setTransition_time(rs.getDate("transition_time"));
				dto.setHindi_doc_id(rs.getString("hindi_doc_id"));

				jdbcTemplate.query(builder.getToUserName(), new Object[] { rs.getString("to_user_assigned") },
						result -> {

							// UserDTO user = new UserDTO(); // dto.setU(result.getString("username"));
							dto.setToUserRole(result.getString("userrole"));
							dto.setToUserFullName(result.getString("name"));

							// userList.add(user);
						});

				jdbcTemplate.query(builder.getFromUserName(), new Object[] { rs.getString("from_user_submitted") },
						result -> {

							// UserDTO user = new UserDTO(); // dto.setU(result.getString("username"));
							dto.setFromUserRole(result.getString("fromuserrole"));
							dto.setFromUserFullName(result.getString("fromusername"));

							// userList.add(user);
						});

				dtoList.add(dto);
			});

		//	System.out.println("List::" + dtoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtoList;
	}

}
