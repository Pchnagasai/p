package com.service;


import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TrackerEnquiryDao;
import com.model.MainModelForm;
import com.model.RejectEnquiry;
import com.model.RejectRfp;
import com.model.TrackerEnquiry;

@Service
public class MainService {
	@Autowired
	private MainModelForm mainmodelform;
	
	@Autowired
	private TrackerEnquiryDao trackerenquiry;

	public String checkEnquiryStatus(int id) {
		//HashMap<String,RejectEnquiry>

		List<RejectEnquiry> rejects = mainmodelform.getRejectenquiry();
		List<RejectRfp> converttorfo = mainmodelform.getConvertrfp();
		List<RejectRfp> rfpreject = mainmodelform.getRejectrfp();
		List<RejectRfp> rfpapprove = mainmodelform.getRfpapproive();

	
		for (RejectEnquiry reject : rejects) {
			if (reject.getEnqr_id() == id) {
			//	return new HashMap<String,RejectEnquiry>().put("reject", reject);
				return "reject";
			}
		}

		String temp = "Approve";

		for (RejectRfp convertrfp : converttorfo) {
			if (convertrfp.getRfprenqrid() == id) {
				  temp ="convertrfp";
			}
		}
		for(RejectRfp rfprejec :rfpreject ) {
			if(rfprejec.getRfprenqrid()==id) {
				temp="rfpreject";
			}
			
		}
		for(RejectRfp rfpapprov : rfpapprove) {
			if(rfpapprov.getRfprenqrid()==id) {
				temp="rfpapprove";
			}
			
		}

	//	return new HashMap<String,>;
		return "Approve";
	}
	public Map<String, Object> checkEnquiryStatu(int id) {
		Map<String, Object> statusMap = new HashMap<>();

		List<RejectEnquiry> rejects = trackerenquiry.getenquiryreject();
		List<RejectRfp> converttorfo = trackerenquiry.getconverttorfp();
		List<RejectRfp> rfpreject = trackerenquiry.getrfpreject();
		List<RejectRfp> rfpapprove = trackerenquiry.getRfpApprove();

		for (RejectEnquiry reject : rejects) {
			if (reject.getEnqr_id() == id) {
				statusMap.put("status", "reject");
				statusMap.put("data", reject);
				System.out.println(reject.getEnqid());
				
				return statusMap;
			}
		}

		for (RejectRfp convertrfp : converttorfo) {
			if (convertrfp.getRfprenqrid() == id) {
				statusMap.put("status", "convertrfp");
				statusMap.put("data", convertrfp);
			
			}
		}

		for (RejectRfp rfprejec : rfpreject) {
			if (rfprejec.getRfprenqrid() == id) {
				statusMap.put("status", "rfpreject");
				statusMap.put("data", rfprejec);
				return statusMap;
			}
		}

		for (RejectRfp rfpapprov : rfpapprove) {
			if (rfpapprov.getRfprenqrid() == id) {
				statusMap.put("status", "rfpapprove");
				statusMap.put("data", rfpapprov);
				return statusMap;
			}
		}

	
		return statusMap;
	}
}
	
	
	
	


