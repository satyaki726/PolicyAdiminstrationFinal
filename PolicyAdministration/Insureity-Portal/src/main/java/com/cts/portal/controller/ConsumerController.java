package com.cts.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cts.portal.feign.Consumerclient;
import com.cts.portal.model.ConsumerDetails;
import com.cts.portal.model.ConsumerRequest;
import com.cts.portal.service.InsureityService;

@Controller
@RequestMapping("/policy")
public class ConsumerController {

	@Autowired
	private Consumerclient client;

	@Autowired
	private InsureityService insureityService;

	@GetMapping("/consumers")
	public String showView(@ModelAttribute("consumerRequest") ConsumerRequest consumerRequest, BindingResult result) {
		return "create-consumer";
	}

	@PostMapping("/consumers")
	public ModelAndView createConsumer(@ModelAttribute("consumerRequest") ConsumerRequest consumerRequest,
			BindingResult result, HttpServletRequest request) throws Exception {

		if ((String) request.getSession().getAttribute("Authorization") == null) {

			ModelAndView login = new ModelAndView("error-401");
			return login;
		}

		ModelAndView model = new ModelAndView("create-consumer");

		if (consumerRequest != null) {
			try {
				ConsumerDetails con = insureityService.getConsumerDetails(consumerRequest);
				client.createConsumer((String) request.getSession().getAttribute("Authorization"), con);
				model.addObject("success", "Consumer added successfully!!");

			} catch (Exception e) {
				ModelAndView error = new ModelAndView("error-401");
				error.addObject("error", "Please Add Details Properly!!!");
				return error;
			}
		}

		return model;
	}

	@GetMapping("/getallconsumers")
	public ModelAndView getAllConsumer(HttpServletRequest request) throws Exception {

		if ((String) request.getSession().getAttribute("Authorization") == null) {

			ModelAndView login = new ModelAndView("error-401");
			return login;
		}

		ModelAndView model = new ModelAndView("view-consumer");

		try {
			List<ConsumerDetails> list = client.viewAllConsumer((String) request.getSession().getAttribute("Authorization"));
			model.addObject("list", list);

		} catch (Exception e) {
			ModelAndView error = new ModelAndView("error-401");
			error.addObject("error", "List is empty");
			return error;
		}

		return model;
	}

}
