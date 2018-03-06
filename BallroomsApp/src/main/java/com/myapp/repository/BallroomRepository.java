package com.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myapp.model.Ballroom;

@Repository("ballroomRepository")
public interface BallroomRepository extends JpaRepository<Ballroom, Long>, JpaSpecificationExecutor<Ballroom>{
    
	@Query("select b from Ballroom b where b.ballroomName = :ballroomName")
	public Ballroom findByName(@Param("ballroomName") String ballroomName);

	//public List<Ballroom> findByContactId(Long contactId);
    
	@Query("SELECT b FROM Ballroom b WHERE b.ballroomName LIKE :searchTerm ")
	public List<Ballroom> search(@Param("searchTerm") String searchTerm );
	
}
