
package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.model.EnquiryRowMapper;
import com.model.RejectEnquiry;
import com.model.RejectRfp;
import com.model.RejectRfpRowMapper;
import com.model.RejectRowMapper;
import com.model.TrackerEnquiry;

public class TrackerEnquiryDaoImp implements TrackerEnquiryDao {

	@Autowired
	private JdbcTemplate jdbctemplate;

	private final String enquiryapprove = "select enqr_id,enqr_cust_id,enqr_createdby,enqr_subject,enqr_assigned_to,enqr_luser from tracker_enquiries where enqr_id not in (select e_id from rejectedenquiries)";

	private final String trackerenquiry = "SELECT DISTINCT te.enqr_id, te.enqr_cust_id, te.enqr_createdby, te.enqr_subject, te.enqr_assigned_to, te.enqr_luser\r\n"
			+ "FROM tracker_enquiries te\r\n"
			+ "JOIN rejectedenquiries rd ON te.enqr_id != rd.e_id\r\n"
			+ "ORDER BY te.enqr_id; ";

	private final String enquiryreject = "select rd.e_id ,te.enqr_id,te.enqr_cust_id,te.enqr_createdby,te.enqr_subject,te.enqr_luser  from tracker_enquiries te join rejectedenquiries rd on te.enqr_id=rd.e_id  ";

	private final String convertrfp = "select rfpr_enqr_id,rfpr_created_ausr_id,rfpr_status,rfpr_assignedto,rfpr_subject,rfpr_intro_note from tracker_rfp";

	private final String rfpreject = "select rfpr_enqr_id,rfpr_created_ausr_id,rfpr_status,rfpr_assignedto,rfpr_subject,rfpr_intro_note from  tracker_rfp  where rfpr_id  in  (select rej_rfpr_id from rejected_rfps)";

	private final String rfpapprove = "select rfpr_enqr_id,rfpr_created_ausr_id,rfpr_status,rfpr_assignedto,rfpr_subject,rfpr_intro_note from  tracker_rfp  where rfpr_id not in  (select rej_rfpr_id from rejected_rfps)";

	@Override
	public List<TrackerEnquiry> getEnquiryById() {
		// TODO Auto-generated method stub
		return jdbctemplate.query(trackerenquiry, new EnquiryRowMapper());
	}

	@Override
	public List<RejectEnquiry> getenquiryreject() {
		// TODO Auto-generated method stub
		return jdbctemplate.query(enquiryreject, new RejectRowMapper());
	}

	@Override
	public List<TrackerEnquiry> getenquiryapprove() {
		// TODO Auto-generated method stub
		return jdbctemplate.query(enquiryapprove, new EnquiryRowMapper());
	}

	@Override
	public List<RejectRfp> getconverttorfp() {
		// TODO Auto-generated method stub
		return jdbctemplate.query(convertrfp, new RejectRfpRowMapper());
	}

	@Override
	public List<RejectRfp> getrfpreject() {
		// TODO Auto-generated method stub
		return jdbctemplate.query(rfpreject, new RejectRfpRowMapper());
	}

	@Override
	public List<RejectRfp> getRfpApprove() {
		// TODO Auto-generated method stub
		return jdbctemplate.query(rfpapprove, new RejectRfpRowMapper());
	}

}
