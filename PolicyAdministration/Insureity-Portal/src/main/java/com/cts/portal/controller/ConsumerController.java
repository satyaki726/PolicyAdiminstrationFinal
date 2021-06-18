package com.cts.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cts.portal.feign.Consumerclient;
import com.cts.portal.model.ConsumerDetails;

@Controller
@RequestMapping("/policy")
public class ConsumerController {

	@Autowired
	private Consumerclient client;

	@PostMapping("/consumers")
	public ModelAndView createConsumer(@ModelAttribute("consumerDetails") ConsumerDetails consumerDetails,
			BindingResult result, HttpServletRequest request) throws Exception {

		if ((String) request.getSession().getAttribute("Authorization") == null) {

			ModelAndView login = new ModelAndView("error-401");
			return login;
		}

		ModelAndView model = new ModelAndView("create-consumer");

		if (consumerDetails != null) {
			try {
				client.createConsumer((String) request.getSession().getAttribute("Authorization"), consumerDetails);
				model.addObject("success", "Consumer added successfully!!");

			} catch (Exception e) {
				ModelAndView error = new ModelAndView("error-401");
				error.addObject("error", "Please Add Details Properly!!!");
				return error;
			}
		}

		return model;
	}

	@PostMapping(value = "/consumers")
	public ModelAndView updateConsumers(@ModelAttribute("consumerDetails") ConsumerDetails consumerDetails, BindingResult result,
			HttpServletRequest request) throws Exception {

		if ((String) request.getSession().getAttribute("Authorization") == null) {

			ModelAndView login = new ModelAndView("error-401");
			return login;
		}

		ModelAndView model = new ModelAndView("updated-consumer");
		try {
			client.updateConsumer((String) request.getSession().getAttribute("Authorization"),consumerDetails.getId(), consumerDetails);
			model.addObject("success","Consumer Details Updated Successfully!!");
		}catch(Exception e) {
			ModelAndView error = new ModelAndView("error-page");
			error.addObject("error","Enter The Details Properly");
			return error;
		}
		
		
		return model;
	}
}
