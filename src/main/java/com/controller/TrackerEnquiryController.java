
package com.controller;
import java.util.Map;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.TrackerEnquiryDao;
import com.google.gson.Gson;
import com.model.MainModelForm;
import com.model.RejectEnquiry;
import com.model.RejectRfp;
import com.model.TrackerEnquiry;
import com.service.MainService;
import java.util.HashMap;
@Controller
public class TrackerEnquiryController {
	@Autowired
	private TrackerEnquiryDao trackerenquirydao;
	@Autowired
	private MainModelForm mainmodelform;
	@Autowired
	private MainService mainservice;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getEnquiryid(Model model) {
      System.out.println("hello");
		List<TrackerEnquiry> enquiry = trackerenquirydao.getEnquiryById();

		for (TrackerEnquiry x : enquiry) {
			System.out.println(x);
		}
		model.addAttribute("enquiry", enquiry);

		List<RejectEnquiry> reject = trackerenquirydao.getenquiryreject();
		for (RejectEnquiry x : reject) {
			System.out.println(x);
		}

		model.addAttribute("reject", reject);

		List<TrackerEnquiry> approve = trackerenquirydao.getenquiryapprove();
		for (TrackerEnquiry x : approve) {
			System.out.println(x);
		}
		model.addAttribute("approve", approve);

		List<RejectRfp> converttorfo = trackerenquirydao.getconverttorfp();

		for (RejectRfp x : converttorfo) {
			System.out.println(x);
		}
		model.addAttribute("converttorfo", converttorfo);

		List<RejectRfp> rfpreject = trackerenquirydao.getrfpreject();
		for (RejectRfp x : rfpreject) {
			System.out.println(x);
		}
		model.addAttribute("rfpreject", rfpreject);

		List<RejectRfp> rfpapprove = trackerenquirydao.getRfpApprove();
		for (RejectRfp x : rfpapprove) {
			System.out.println(x);
		}
		model.addAttribute("rfpapprove", rfpapprove);

		mainmodelform.setTrackerenquiry(enquiry);
		mainmodelform.setRejectenjuiry(reject);
		mainmodelform.setApproveenquiry(approve);
		mainmodelform.setConvertrfp(converttorfo);
		mainmodelform.setRejectrfp(rfpreject);
		mainmodelform.setRfpapproive(rfpapprove);
		model.addAttribute("viewmodel", mainmodelform);

		return "Enquirystage";

	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ResponseEntity<String>  getform(@RequestParam("id") String id,Model model) {
		System.out.println(id);
		String data = mainservice.checkEnquiryStatus(Integer.parseInt(id.trim()));
		
		Map<String, Object> enquiryStatus = mainservice.checkEnquiryStatu(Integer.parseInt(id.trim()));
		System.out.println(enquiryStatus);
		
		new Gson().toJson(enquiryStatus);
		
		return ResponseEntity.ok(new Gson().toJson(enquiryStatus));

	}
	
	

}
