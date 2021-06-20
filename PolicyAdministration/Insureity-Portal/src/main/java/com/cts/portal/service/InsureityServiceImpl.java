package com.cts.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.portal.model.BusinessDetails;
import com.cts.portal.model.ConsumerDetails;
import com.cts.portal.model.ConsumerRequest;
import com.cts.portal.model.PropertyDetails;

@Service
public class InsureityServiceImpl implements InsureityService {

	@Override
	public ConsumerDetails getConsumerDetails(ConsumerRequest consumerRequest) {
		// TODO Auto-generated method stub
		ConsumerDetails con = new ConsumerDetails();
		con.setAgentid(consumerRequest.getAgentid());
		con.setAgentname(consumerRequest.getAgentname());
		con.setDob(consumerRequest.getDob());
		con.setEmail(consumerRequest.getEmail());
		con.setPandetails(consumerRequest.getPandetails());
		con.setPhone(consumerRequest.getPhone());
		con.setName(consumerRequest.getName());
		
		List<BusinessDetails> list1 = new ArrayList<>();
		BusinessDetails bon = new BusinessDetails();
		bon.setBusinessage(consumerRequest.getBusinessage());
		bon.setBusinesscategory(consumerRequest.getBusinesscategory());
		bon.setBusinessturnover(consumerRequest.getBusinessturnover());
		bon.setBusinesstype(consumerRequest.getBusinesstype());
		bon.setCapitalinvested(consumerRequest.getCapitalinvested());
		bon.setTotalemployees(consumerRequest.getTotalemployees());

		List<PropertyDetails> list = new ArrayList<>(); 
		PropertyDetails por = new PropertyDetails();
		por.setPropertytype(consumerRequest.getPropertytype());
		por.setBuildingsqft(consumerRequest.getBuildingsqft());
		por.setBuildingtype(consumerRequest.getBuildingtype());
		por.setBuildingstoreys(consumerRequest.getBuildingstoreys());
		por.setBuildingage(consumerRequest.getBuildingage());
		por.setCostoftheasset(consumerRequest.getCostoftheasset());
		por.setUsefullifeoftheAsset(consumerRequest.getUsefullifeoftheAsset());
		por.setSalvagevalue(consumerRequest.getSalvagevalue());
		
		list.add(por);
		bon.setProperty(list);
		
		list1.add(bon);
		con.setBusiness(list1);
		
		return con;
	}
}
