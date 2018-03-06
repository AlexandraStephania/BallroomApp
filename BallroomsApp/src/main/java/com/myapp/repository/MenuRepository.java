package com.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.myapp.model.Menu;

@Repository("menuRepository")
public interface MenuRepository  extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu>{
    
	public List<Menu> findByBallroomId(Long ballroomId);
}
