package org.egov.dms.repository;

import org.egov.dms.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	@Query("select u from UserEntity u where userName= :userName")
	public UserEntity findByName(@Param("userName") String userName);

}
