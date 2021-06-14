package com.cts.mfpe.service;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.mfpe.exception.ConsumerNotFoundException;
import com.cts.mfpe.model.BusinessDetails;
import com.cts.mfpe.model.BusinessMaster;
import com.cts.mfpe.model.ConsumerDetails;
import com.cts.mfpe.repository.BusinessMasterRepository;
import com.cts.mfpe.repository.ConsumerRepository;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	BusinessMasterRepository businessMasterRepository;

	@Override
	public ConsumerDetails saveConsumer(ConsumerDetails consumerDetails) {
		// TODO Auto-generated method stub
		BusinessDetails business = consumerDetails.getBusiness();
		Long businessValue = calBusinessValue(business.getBusinessturnover(), business.getCapitalinvested());
		System.out.println(businessValue);
		business.setBusinessvalue(businessValue);
		consumerDetails.setBusiness(business);
		ConsumerDetails con = consumerRepository.save(consumerDetails);

		return con;
	}
	
	public Long calBusinessValue(Long businessturnover, Long capitalinvested) {
		
		Double x_max = (double) businessturnover;
		Double x_min = (double) capitalinvested;
		Double x_ratio = x_max / x_min;
		Double Range_min = 0.00;
		Double Range_max = 10.00;
		Double range_diff = Range_max - Range_min;
		Double sat = ((x_ratio - x_min) / (x_max - x_min));
		Double businessvalue = (range_diff * sat);
		return Math.round(businessvalue);
	}

	@Override
	public void deleteConsumer(Long cid) {
		// TODO Auto-generated method stub
		consumerRepository.deleteById(cid);
	}

	@Override
	public ConsumerDetails findConsumerById(Long cid) throws ConsumerNotFoundException {
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

	@Override
	public Boolean checkEligibility(ConsumerDetails consumerDetails) throws Exception {
		// TODO Auto-generated method stub
		Boolean check = false;

		BusinessDetails businessDetails = consumerDetails.getBusiness();

		BusinessMaster businessMaster = businessMasterRepository.findByBusinesscategoryAndBusinesstype(
				businessDetails.getBusinesscategory(), businessDetails.getBusinesstype());
		if (businessMaster == null) {
			return check;
		}

		if (businessMaster.getTotalemployees() <= businessDetails.getTotalemployees()
				|| businessMaster.getBusinessage() <= businessDetails.getBusinessage()) {
			check = true;
		}
		return check;
	}

}
