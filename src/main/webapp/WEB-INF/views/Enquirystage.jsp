<%@page import="javax.management.AttributeValueExp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List, com.model.*,com.model.TrackerEnquiry,com.model.RejectEnquiry,com.model.TrackerEnquiry,com.model.RejectRfp,com.model.MainModelForm" %>
<!DOCTYPE html>
<html>
<head>



<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <title>Enquiry Tracker</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }
        
        
        .form-overlay {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        z-index: 9999;
        display: none;
    }

    .form-container {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: white;
        padding: 20px;
    }

    .close {
        position: absolute;
        top: 10px;
        right: 10px;
    }
   
    .disabled {
        opacity: 0.5;
        pointer-events: none;
    }
    
    
    </style>
</head>
<body>



<div id="formOverlay" class="form-overlay">
	  <div class="form-container">
	  
	    <button id="closeFormButton" class="btn btn-secondary close"><i class="fas fa-times"></i></button>
	    

	    <form id="overlayForm" class="hidden-form">
      <!-- Form fields -->
      <h1>Enquary</h1>
      <label for="enqrid">Enquiry ID:</label>
      <input type="number" id="enqrid" name="enqrid" required readonly><br>
      
      <label for="enqrcustid">Enquiry Customer ID:</label>
      <input type="number" id="enqrcustid" name="enqrcustid" required readonly><br>
      
       <label for="enqrcreatby">Enquiry Created By:</label>
      <input type="text" id="enqrcreatby" name="enqrcreatby" required readonly><br>
      
      <label for="enqrsubject">Enquiry Subject:</label>
      <input type="text" id="enqrsubject" name="enqrsubject" required readonly><br>
      
      
      
       <label for="enqrassignto">Enquiry Assigned To:</label>
      <input type="text" id="enqrassignto" name="enqrassignto" required readonly><br>
      
       <label for="enqrluser">Enquiry last_user:</label>
      <input type="text" id="enqrluser" name="enqrluser" required readonly><br>
     
   </form>
   
   
       
   
   
   <form id="rfp" class="hidden-form">
   <h1>RFP</h1>
   <label for="rfpenqrid">RFP Enquiry ID:</label>
      <input type="number" id="rfpenqrid" name="rfpenqrid" required readonly><br>
      
       <label for="rfprcreatedausrid">Rfpr Created Auser Id:</label>
      <input type="text" id="rfprcreatedausrid" name="rfprcreatedausrid" required readonly><br>
      
       <label for="rfprstatus">Rfpr Status:</label>
      <input type="text" id="rfprstatus" name="rfprstatus" required readonly><br>
      
      <label for="rfpassignto">rfp Assigned To:</label>
      <input type="text" id="rfpassignto" name="rfpassignto" required readonly><br>
      
       <label for="rfpsubject">Rfp Subject:</label>
      <input type="text" id="rfpsubject" name="rfpsubject" required readonly><br>
      
      <label for="rfpintronote">Rfp Intro Note:</label>
      <input type="text" id="rfpintronote" name="rfpintronote" required readonly><br>
      
     
   
   
   </form>
	   
	</div>
</div>

<% List<TrackerEnquiry> enqid = (List<TrackerEnquiry>) request.getAttribute("enquiry"); %>
<% List<RejectEnquiry> rejectdata = (List<RejectEnquiry>) request.getAttribute("reject"); %>
<% List<RejectRfp> convertrfp = (List<RejectRfp>) request.getAttribute("converttorfo"); %>
<% List<RejectRfp> rfprejected = (List<RejectRfp>)request.getAttribute("rfpreject"); %>
<%List<RejectRfp> rfpapprove =(List<RejectRfp>)request.getAttribute("rfpapprove"); %>

<h1>Enquiry Tracker</h1>
<table>
    <tr>
        <th>Enqr_ID</th>
        <th>Status</th>
        <th>Enq_approve</th>
        <th>Converted_To_Rfp</th>
        <th>RFP Reject</th>
        <th>RFP Approve</th>
        <th>RFP Completed</th>
        <th>View</th>
    </tr>
    <% for (int i = 0; i < enqid.size(); i++) { %>
        <tr>
            <td><%= enqid.get(i).getEnqrid() %></td>
            <% boolean isRejected = false;
            for (int j = 0; j < rejectdata.size(); j++) {
                RejectEnquiry reject = rejectdata.get(j);
                if (reject.getEnqr_id() == enqid.get(i).getEnqrid()) {
                    isRejected = true;
                    break;
                }
            }
            if (isRejected) { %>
                <td>Rejected</td>
                <td></td>
            <% } else { %>
                <td></td>
                <td>Approve</td>
            <% } %>
            
            <% boolean isConvertedToRfp = false;
            for (int k = 0; k < convertrfp.size(); k++) {
            	RejectRfp rfp = convertrfp.get(k);
                if (rfp.getRfprenqrid() == enqid.get(i).getEnqrid()) {
                    isConvertedToRfp = true;
                    break;
                }
            }
            if (isConvertedToRfp) { %>
                <td>rfps</td>
            <% } else { %>
                <td></td>
            <% } %>
            
            <% boolean isRfpRejected = false;
            for(int l=0;l<rfprejected.size();l++){
            	RejectRfp rejectrfp = rfprejected.get(l);
            	if(rejectrfp.getRfprenqrid() == enqid.get(i).getEnqrid()){
            		isRfpRejected = true;
            		break;
            	}
            }
            
            if(isRfpRejected) { %>
            <td>Rejected</td>
            <% } else { %>
            <td></td>
            <% } %>
            
            
            <% boolean isRfpApprove = false;
            
            for(int m=0;m<rfpapprove.size();m++){
            	RejectRfp approverfp = rfpapprove.get(m);
            	if(approverfp.getRfprenqrid() == enqid.get(i).getEnqrid()){
            		isRfpApprove = true;
            		break;
            	}
            }
            
            if(isRfpApprove) { %> 
            <td>Approve</td>
            <% } else { %>
            <td></td>
            <% } %>
            <td>"-"</td>
              <td>
           
                 <button class="btn btn-primary request-button" data-form="overlayForm" 
                 onclick="getForm('<%=enqid.get(i).getEnqrid() %>');">view</button>
             
                 </td>
        </tr>
    <% } %>
</table>


<script>
  $(document).ready(function() {
    var formOverlay = $('#formOverlay');
    var formContainer = formOverlay.find('.form-container');
    

    $('.request-button').click(function(e) {
      e.stopPropagation();
      var formId = $(this).data('form');
      var form = $('#' + formId);
      // Display the selected form and open the overlay
       var enqrid = $(this).data('enqrid');
                
                // Populate form fields with the corresponding data
                form.find('#enqrid').val(enqrid);
                form.find('#enqrcustid').val(''); // Populate with customer ID data
                form.find('#enqrcreatby').val(''); // Populate with created by data
                form.find('#enqrsubject').val(''); // Populate with subject data
                                                   // Populate with description data
                form.find('#enqrassignto').val(''); // Populate with assigned to data
                form.find('#enqrluser').val(''); // Populate with last user data
      
      
      
      formOverlay.fadeIn();
      form.show();
    });
    
  //Close form overlay when the close button is clicked
    $('#closeFormButton').click(function(e) {
      e.stopPropagation();
      closeFormOverlay();
    });
    // Close form overlay when clicking outside the form container or form overlay
    $(document).on('click', function(e) {
      if (!formContainer.is(e.target) && formContainer.has(e.target).length === 0) {
        closeFormOverlay();
      }
    });
    
    function closeFormOverlay() {
      formOverlay.fadeOut();
    }
  });
  
  
  
  
  
  function getForm(id) {
	  $.ajax({
	    url: "form", // Replace with the actual URL to retrieve the form data
	    method: "GET",
	    data: {
	      id: id
	    },
	    success: function(response) {
	      // Handle the response and populate the form fields
	      
	      
	     console.log(response)
	      
	    
	     const parsedResponse = JSON.parse(response);

// Extract the values
         const status = parsedResponse.status;
	      
	      if (status === 'reject') {
	    	  const enqrId = parsedResponse.data.enqr_id;
	    	  const enqCustId = parsedResponse.data.enqcustid;
	    	  const enqrCreatedBy = parsedResponse.data.enqrcreatedby;
	    	  const enqrSubject = parsedResponse.data.enqrsubject;
	    	  const enqrLUser = parsedResponse.data.enqrluser;
	    	  
	    	  console.log('enqr_id:', enqrId);
	    	  console.log('enqcustid:', enqCustId);
	    	  console.log('enqrcreatedby:', enqrCreatedBy);
	    	  console.log('enqrsubject:', enqrSubject);
	    	  console.log('enqrluser:', enqrLUser);
	    	  console.log('status:', status);
	    	  	     


	       
	        $("#enqrid").val(enqrId);
	        $("#enqrcustid").val(enqCustId);
	        $("#enqrcreatby").val(enqrCreatedBy);
	        $("#enqrsubject").val(enqrSubject);
	       
	        $("#enqrassignto").val(enqrLUser);
	        $("#enqrluser").val(enqrLUser);
	        
	        // Show the form overlay
	        $("#overlayForm").fadeIn();
	        
	      } else if (status === 'Approve') {
	    	  
	        // Handle 'Approve' response
	       
	        
	      } else if (status === 'convertrfp') {
	        // Handle 'convertrfp' response
	       
	        
	       
	        
	      } else if (status === 'rfpreject') {
	        // Handle 'rfpreject' response
	      } else {
	        // Handle other responses
	      }
	    },
	    error: function() {
	      // Handle the error case
	      console.log("Error occurred while retrieving form data.");
	    }
	  });
	}

  
</script>




</body>
</html>



