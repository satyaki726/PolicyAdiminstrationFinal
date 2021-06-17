package com.cts.mfpe.service;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.mfpe.exception.ConsumerNotFoundException;
import com.cts.mfpe.model.ConsumerDetails;
import com.cts.mfpe.repository.ConsumerRepository;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	ConsumerRepository consumerRepository;

	@Override
	public ConsumerDetails saveConsumer(ConsumerDetails consumerDetails) {
		// TODO Auto-generated method stub
		ConsumerDetails con = consumerRepository.save(consumerDetails);

		return con;
	}


	@Override
	public void deleteConsumer(Long cid){
		// TODO Auto-generated method stub
		consumerRepository.deleteById(cid);
	}

	@Override
	public ConsumerDetails findConsumerById(Long cid) throws ConsumerNotFoundException{
		// TODO Auto-generated method stub
		ConsumerDetails con = consumerRepository.findById(cid)
				.orElseThrow(() -> new ConsumerNotFoundException("Consumer not found"));
		return con;
	}

	@Override
	public List<ConsumerDetails> findAllConsumers() {
		// TODO Auto-generated method stub
		List<ConsumerDetails> con = consumerRepository.findAll();
		return con;
	}

}
