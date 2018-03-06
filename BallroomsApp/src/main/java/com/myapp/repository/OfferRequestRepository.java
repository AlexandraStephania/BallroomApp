package com.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.myapp.model.OfferRequest;

@Repository("offerRequestRepository")
public interface OfferRequestRepository extends JpaRepository<OfferRequest, Long>, JpaSpecificationExecutor<OfferRequest>{

	List<OfferRequest> findBySalonId(Long salonId);

}
