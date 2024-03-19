package org.egov.dms.repository;

import java.util.List;
import java.util.Optional;

import org.egov.dms.model.DocumentWorkflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentWorkflowRepository extends JpaRepository<DocumentWorkflow, Long> {

	@Query("SELECT d FROM DocumentWorkflow d WHERE dmsDocId = :dmsDocId AND active = 'true'")
	Optional<DocumentWorkflow> findByDmsDocId(@Param (value = "dmsDocId")Long dmsDocId);
	
	@Query("select d from DocumentWorkflow d WHERE assignedTo = :assignedTo AND active = 'true'")
	List<DocumentWorkflow> searchDucumentByUserAndStatus(@Param(value="assignedTo")String assignedTo);
}
