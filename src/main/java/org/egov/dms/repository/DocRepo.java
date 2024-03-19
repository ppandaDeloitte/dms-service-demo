package org.egov.dms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egov.dms.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class DocRepo {

	@Autowired private NamedParameterJdbcTemplate namedJdbcTemplate;
	
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
			
			System.out.println("Executing Query::"+sql);
			
		List<Document> documentList =	namedJdbcTemplate.query(sql, paramMap, new RowMapper<Document>() {

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
}
