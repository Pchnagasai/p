
package com.model;

public class RejectEnquiry {
	private int enqr_id;
	private String enqr_status;
	private String rfpr_status;

	private int enqid;
	private int enqcustid;
	private String enqrcreatedby;
	private String enqrsubject;
	
	
	private String enqrluser;

	public int getEnqr_id() {
		return enqr_id;
	}

	public void setEnqr_id(int enqr_id) {
		this.enqr_id = enqr_id;
	}

	public int getEnqid() {
		return enqid;
	}

	public void setEnqid(int enqid) {
		this.enqid = enqid;
	}

	public int getEnqcustid() {
		return enqcustid;
	}

	public void setEnqcustid(int enqcustid) {
		this.enqcustid = enqcustid;
	}

	public String getEnqrcreatedby() {
		return enqrcreatedby;
	}

	public void setEnqrcreatedby(String enqrcreatedby) {
		this.enqrcreatedby = enqrcreatedby;
	}

	public String getEnqrsubject() {
		return enqrsubject;
	}

	public void setEnqrsubject(String enqrsubject) {
		this.enqrsubject = enqrsubject;
	}

	

	


	public String getEnqrluser() {
		return enqrluser;
	}

	public void setEnqrluser(String enqrluser) {
		this.enqrluser = enqrluser;
	}

	@Override
	public String toString() {
		return "RejectEnquiry [enqr_id=" + enqr_id + ", enqid=" + enqid + ", enqcustid=" + enqcustid
				+ ", enqrcreatedby=" + enqrcreatedby + ", enqrsubject=" + enqrsubject + ", " 
				+ ", " + ", enqrluser=" + enqrluser + "]";
	}

	public String getEnqr_status() {
		return enqr_status;
	}

	public void setEnqr_status(String enqr_status) {
		this.enqr_status = enqr_status;
	}

	public String getRfpr_status() {
		return rfpr_status;
	}

	public void setRfpr_status(String rfpr_status) {
		this.rfpr_status = rfpr_status;
	}

}
