package com.myapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.exception.RecordNotFoundException;
import com.myapp.model.Salon;
import com.myapp.repository.SalonRepository;

@Service("salonService")
public class SalonServiceImpl implements SalonService {
    
	private static final Logger logger = LoggerFactory.getLogger(SalonServiceImpl.class);

	@Autowired
	private SalonRepository salonRepository;
	
	@Override
	public Salon findSalon(Long salonId) {
		Salon salon = salonRepository.findOne(salonId);
		return salon;
	}

	@Override
	public List<Salon> findAllSalons() {
	     return salonRepository.findAll();
		
	}

	@Override
	public List<Salon> findBallroomSalons(Long ballroomId) {
		return salonRepository.findByBallroomId(ballroomId);
	}

	@Override
	public Salon add(Salon salon) {
		salonRepository.save(salon);
		return salon;
	}

	@Override
	public Salon update(Salon salon) {
		Salon existingSalon = salonRepository.findOne(salon.getId());
		if (existingSalon == null) {
			String errorMessage = "Salon with id " + salon.getId() + " not found";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
		return salonRepository.save(salon);
	}

	@Override
	public void delete(Long salonId) {
		Salon salon= salonRepository.findOne(salonId);
		logger.debug("Delete salon with id: " + salonId);
		if (salon != null) {
			salonRepository.delete(salonId);
		} else {
			String errorMessage = "salon with id " + salonId + " not found";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
		
	}

}
