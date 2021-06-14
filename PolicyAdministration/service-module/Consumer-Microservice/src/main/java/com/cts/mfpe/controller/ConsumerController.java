package com.cts.mfpe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mfpe.exception.AuthorizationException;
import com.cts.mfpe.exception.ConsumerNotFoundException;
import com.cts.mfpe.exception.NotEligibleException;
import com.cts.mfpe.feign.AuthClient;
import com.cts.mfpe.model.Consumer;
import com.cts.mfpe.service.ConsumerService;

@RestController
public class ConsumerController {
	
	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private AuthClient authClient;

	@PostMapping("/consumers")
	public ResponseEntity<Consumer> createConsumer(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@RequestBody Consumer Consumer) throws Exception {
		if (authClient.authorizeTheRequest(requestTokenHeader)) {
			if(!consumerService.checkEligibility(Consumer)) {
				throw new NotEligibleException("Not Eligible");
			}
			Consumer consumer = consumerService.saveConsumer(Consumer);
			return new ResponseEntity<Consumer>(consumer, HttpStatus.CREATED);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	@PutMapping("/consumers/{consumer_id}")
	public ResponseEntity<Consumer> updateConsumer(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@PathVariable Long consumer_id, @RequestBody Consumer Consumer)
			throws ConsumerNotFoundException, AuthorizationException {
		if (authClient.authorizeTheRequest(requestTokenHeader)) {
			Consumer consumer = consumerService.findConsumerById(consumer_id);
			consumer.setAgentid(Consumer.getAgentid());
			consumer.setAgentname(Consumer.getAgentname());
			consumer.setDob(Consumer.getDob());
			consumer.setEmail(Consumer.getEmail());
			consumer.setName(Consumer.getName());
			consumer.setPandetails(Consumer.getPandetails());
			consumer.setPhone(Consumer.getPhone());

			Consumer con = consumerService.saveConsumer(consumer);

			return new ResponseEntity<Consumer>(con, HttpStatus.OK);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	@DeleteMapping("/consumers/{cid}")
	public ResponseEntity<Consumer> deleteConsumer(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader, @PathVariable Long cid)
			throws ConsumerNotFoundException, AuthorizationException {
		if (authClient.authorizeTheRequest(requestTokenHeader)) {
			Consumer con = consumerService.findConsumerById(cid);
			consumerService.deleteConsumer(con.getId());
			return new ResponseEntity<Consumer>(con, HttpStatus.OK);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	@GetMapping("/getconsumers/{cid}")
	public Consumer viewConsumer(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader, @PathVariable Long cid)
			throws ConsumerNotFoundException, AuthorizationException {
		if (authClient.authorizeTheRequest(requestTokenHeader)) {
			Consumer con = consumerService.findConsumerById(cid);
			return con;
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	@GetMapping("/getallconsumers")
	public List<Consumer> viewAllConsumer(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)throws AuthorizationException {
		if (authClient.authorizeTheRequest(requestTokenHeader)) {
			List<Consumer> list = consumerService.findAllConsumers();
			return list;
		}else {
			throw new AuthorizationException("Not allowed");
		}
	}
}