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
import com.cts.mfpe.feign.AuthClient;
import com.cts.mfpe.model.ConsumerDetails;
import com.cts.mfpe.service.ConsumerService;

@RestController
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private AuthClient authClient;

	@PostMapping("/consumers")
	public ResponseEntity<ConsumerDetails> createConsumer(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@RequestBody ConsumerDetails consumerDetails) throws AuthorizationException {
		if (authClient.authorizeTheRequest(requestTokenHeader)) {
			ConsumerDetails consumer = consumerService.saveConsumer(consumerDetails);
			return new ResponseEntity<ConsumerDetails>(consumer, HttpStatus.CREATED);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	@PutMapping("/consumers/{consumer_id}")
	public ResponseEntity<ConsumerDetails> updateConsumer(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@PathVariable Long consumer_id, @RequestBody ConsumerDetails consumerDetails)
			throws ConsumerNotFoundException, AuthorizationException {
		if (authClient.authorizeTheRequest(requestTokenHeader)) {
			ConsumerDetails consumer = consumerService.findConsumerById(consumer_id);
			consumer.setAgentid(consumerDetails.getAgentid());
			consumer.setAgentname(consumerDetails.getAgentname());
			consumer.setDob(consumerDetails.getDob());
			consumer.setEmail(consumerDetails.getEmail());
			consumer.setName(consumerDetails.getName());
			consumer.setPandetails(consumerDetails.getPandetails());
			consumer.setPhone(consumerDetails.getPhone());

			ConsumerDetails con = consumerService.saveConsumer(consumer);

			return new ResponseEntity<ConsumerDetails>(con, HttpStatus.OK);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	@DeleteMapping("/consumers/{cid}")
	public ResponseEntity<ConsumerDetails> deleteConsumer(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader, @PathVariable Long cid)
			throws ConsumerNotFoundException, AuthorizationException {
		if (authClient.authorizeTheRequest(requestTokenHeader)) {
			ConsumerDetails con = consumerService.findConsumerById(cid);
			consumerService.deleteConsumer(con.getId());
			return new ResponseEntity<ConsumerDetails>(con, HttpStatus.OK);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	@GetMapping("/getconsumers/{cid}")
	public ConsumerDetails viewConsumer(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader, @PathVariable Long cid)
			throws ConsumerNotFoundException, AuthorizationException {
		if (authClient.authorizeTheRequest(requestTokenHeader)) {
			ConsumerDetails con = consumerService.findConsumerById(cid);
			return con;
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	@GetMapping("/getallconsumers")
	public List<ConsumerDetails> viewAllConsumer(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)throws AuthorizationException {
		if (authClient.authorizeTheRequest(requestTokenHeader)) {
			List<ConsumerDetails> list = consumerService.findAllConsumers();
			return list;
		}else {
			throw new AuthorizationException("Not allowed");
		}
	}
}