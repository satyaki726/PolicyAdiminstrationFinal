package com.cts.mfpe.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.mfpe.exception.ConsumerNotFoundException;
import com.cts.mfpe.model.Business;
import com.cts.mfpe.model.BusinessMaster;
import com.cts.mfpe.model.Consumer;
import com.cts.mfpe.repository.BusinessMasterRepository;
import com.cts.mfpe.repository.ConsumerRepository;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	BusinessMasterRepository businessMasterRepository;

	@Override
	public Consumer saveConsumer(Consumer Consumer) {
		// TODO Auto-generated method stub
		/*
		 * Business business = Consumer.getBusiness(); Long businessValue =
		 * calBusinessValue(business.getBusinessturnover(),
		 * business.getCapitalinvested()); System.out.println(businessValue);
		 * business.setBusinessvalue(businessValue); Consumer.setBusiness(business);
		 * Consumer con = consumerRepository.save(Consumer);
		 */

		return null;
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
	public Consumer findConsumerById(Long cid) throws ConsumerNotFoundException {
		// TODO Auto-generated method stub
		Consumer con = consumerRepository.findById(cid)
				.orElseThrow(() -> new ConsumerNotFoundException("Consumer not found"));
		return con;
	}

	@Override
	public List<Consumer> findAllConsumers() {
		// TODO Auto-generated method stub
		List<Consumer> con = consumerRepository.findAll();
		return con;
	}

	@Override
	public Boolean checkEligibility(Consumer Consumer) throws Exception {
		// TODO Auto-generated method stub
		Boolean check = false;

		Business businessDetails = (Business) Collections.singletonList(Consumer.getBusiness()).get(0);

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
