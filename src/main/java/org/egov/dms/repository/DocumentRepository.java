package org.egov.dms.repository;

import java.util.List;

import org.egov.dms.model.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {

	@Query("SELECT d FROM DocumentEntity d WHERE createdBy = :createdBy")
	List<DocumentEntity> findByCreatedUser(@Param(value = "createdBy") String createdBy);
	
	
}
