package com.myapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.exception.RecordNotFoundException;
import com.myapp.model.OfferRequest;
import com.myapp.repository.OfferRequestRepository;

@Service("offerRequestService")
public class OfferRequestServiceImpl implements OfferRequestService {
	private static final Logger logger = LoggerFactory.getLogger(OfferRequestServiceImpl.class);

	@Autowired
    private OfferRequestRepository  offerRequestRepository;
	
	@Override
	public OfferRequest findOfferRequest(Long offerRequestId) {
	    OfferRequest offerRequest= offerRequestRepository.findOne(offerRequestId);
	    return offerRequest;
	}

	@Override
	public List<OfferRequest> findAllOfferRequests() {
		return offerRequestRepository.findAll();
	}

	@Override
	public List<OfferRequest> findSalonOfferRequests(Long salonId) {
		return offerRequestRepository.findBySalonId(salonId);
	}

	@Override
	public OfferRequest add(OfferRequest offerRequest) {
		offerRequestRepository.save(offerRequest);
		return offerRequest;
	}

	@Override
	public OfferRequest update(OfferRequest offerRequest) {
		OfferRequest existingOfferRequest = offerRequestRepository.findOne(offerRequest.getId());
		if (existingOfferRequest == null) {
			String errorMessage = "Offer Request with id " + offerRequest.getId() + " not found";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
		return offerRequestRepository.save(offerRequest);
	}

	@Override
	public void delete(Long offerRequestId) {
		OfferRequest offerRequest = offerRequestRepository.findOne(offerRequestId);
		logger.debug("Offer Request menu with id: " + offerRequestId);
		if (offerRequest != null) {
			offerRequestRepository.delete(offerRequestId);
		} else {
			String errorMessage = "OfferRequest with id " + offerRequestId + " not found";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}

	}

}
