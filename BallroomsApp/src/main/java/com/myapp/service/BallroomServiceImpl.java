package com.myapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.exception.RecordNotFoundException;
import com.myapp.model.Ballroom;
import com.myapp.repository.BallroomRepository;

@Service("ballroomService")
public class BallroomServiceImpl implements BallroomService {

	private static final Logger logger = LoggerFactory.getLogger(BallroomServiceImpl.class);
    
 
	@Autowired
	private BallroomRepository ballroomRepository;
    
	@Override
	public Ballroom findBallroom(Long ballroomId) {
		Ballroom ballroom = ballroomRepository.findOne(ballroomId);
		return ballroom;

	}

	@Override
	public Ballroom findBallroom(String ballroomName) {
		return ballroomRepository.findByName(ballroomName);
	}

	/*@Override
	public List<Ballroom> findContactBallrooms(Long contactId) {
		return ballroomRepository.findByContactId(contactId);
	}*/

	@Override
	public List<Ballroom> findAllBallrooms() {
		return ballroomRepository.findAll();
	}

	@Override
	public Ballroom add(Ballroom ballroom) {

		ballroomRepository.save(ballroom);
		return ballroom;

	}

	@Override
	public Ballroom update(Ballroom ballroom) {
		Ballroom existingBallroom = ballroomRepository.findOne(ballroom.getId());
		if (existingBallroom == null) {
			String errorMessage = "Ballroom with id " + ballroom.getId() + " not found";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}

		return ballroomRepository.save(ballroom);
	}

	@Override
	public void delete(Long ballroomId) {
		Ballroom ballroom = ballroomRepository.findOne(ballroomId);
		logger.debug("Delete ballroom with id: " + ballroomId);

		if (ballroom != null) {

			ballroomRepository.delete(ballroomId);

		} else {
			String errorMessage = "Ballroom with id " + ballroomId + " not found";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
	}

	@Override
	public List<Ballroom> searchBallroom(String pSearchTerm) {
		 logger.debug("Searching ballrooms with search term: " + pSearchTerm);
		 List<Ballroom> ballrooms;
		 ballrooms =ballroomRepository.search(pSearchTerm);
	     return ballrooms; 
	}
   

	

}
