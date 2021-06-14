package com.cts.mfpe.service;

import java.util.List;

import com.cts.mfpe.exception.BusinessMasterNotFoundException;
import com.cts.mfpe.exception.ConsumerNotFoundException;
import com.cts.mfpe.model.Consumer;

public interface ConsumerService {
	
	Consumer saveConsumer(Consumer Consumer);

	void deleteConsumer(Long cid);

	Consumer findConsumerById(Long cid) throws ConsumerNotFoundException;

	List<Consumer> findAllConsumers();

	Boolean checkEligibility(Consumer Consumer) throws Exception;
}
