package com.theironyard.invoicify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;


import com.theironyard.invoicify.models.User;



import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.models.FlatFeeBillingRecord;
import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.BillingRecordRepository;
import com.theironyard.invoicify.repositories.CompanyRepository;

@Controller
@RequestMapping("/billing-records/flat-fees")
public class FlatFeeBillingRecordController {
	
	private BillingRecordRepository billingRecordRepository;
	private CompanyRepository companyRepository;
	
	
	public FlatFeeBillingRecordController(BillingRecordRepository billingRecordRepository, 
										  CompanyRepository companyRepository) {
		this.billingRecordRepository = billingRecordRepository;
		this.companyRepository = companyRepository;
	}
	
	@PostMapping("")  
	public ModelAndView create(FlatFeeBillingRecord record, long clientId, Authentication auth) {
		ModelAndView mv = new ModelAndView("redirect:/billing-records");
		User user = (User) auth.getPrincipal();
		Company company = companyRepository.findOne(clientId);
		record.setClient(company);
		record.setCreatedBy(user);
		billingRecordRepository.save(record);
		return mv;
	}

}
