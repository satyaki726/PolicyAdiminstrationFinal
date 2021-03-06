package com.cts.mfpe.service;

import java.util.List;

import com.cts.mfpe.exception.ConsumerNotFoundException;
import com.cts.mfpe.model.ConsumerDetails;

public interface ConsumerService {
	
	ConsumerDetails saveConsumer(ConsumerDetails consumerDetails);

	void deleteConsumer(Long cid);

	ConsumerDetails findConsumerById(Long cid) throws ConsumerNotFoundException;

	List<ConsumerDetails> findAllConsumers();
}
