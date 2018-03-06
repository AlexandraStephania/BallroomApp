package com.myapp.service;

import java.util.List;

import com.myapp.model.Ballroom;


public interface BallroomService {
   
	public Ballroom findBallroom(Long ballroomId);
	
	public Ballroom findBallroom( String ballroomName);
	
	public List<Ballroom> findAllBallrooms();
	
//	public List<Ballroom> findContactBallrooms(Long contactId);
	
    public Ballroom add(Ballroom ballroom);
	
	public Ballroom update(Ballroom ballroom);
	
	public void delete(Long ballroomId);
	
	public List<Ballroom> searchBallroom(String pSearchTerm);

}
