package com.myapp.service;

import java.util.List;

import com.myapp.model.OfferRequest;

public interface OfferRequestService {
	 public OfferRequest findOfferRequest(Long offerRequestId);
     
     public List<OfferRequest> findAllOfferRequests();
     
     public List<OfferRequest> findSalonOfferRequests(Long salonId);
     
     public OfferRequest add(OfferRequest offerRequest);
     
     public OfferRequest update(OfferRequest offerRequest);
     
     public void delete(Long offerRequestId);
     
}
