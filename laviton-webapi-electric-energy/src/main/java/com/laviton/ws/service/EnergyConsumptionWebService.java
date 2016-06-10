package com.laviton.ws.service;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.laviton.constants.LavitonConstants;
import com.laviton.dao.LoginDao;
import com.laviton.dao.MeterDetailsDao;
import com.laviton.dao.MeterLogsDao;
import com.laviton.dao.TennantDao;
import com.laviton.model.AssignMeter;
import com.laviton.model.InputJson;
import com.laviton.model.InputJsonDates;
import com.laviton.model.InputObject;
import com.laviton.model.LoginRequesst;
import com.laviton.model.LoginResponse;
import com.laviton.model.MeterDetails;
import com.laviton.model.MeterDetailsRequest;
import com.laviton.model.MeterList;
import com.laviton.model.MeterLists;
import com.laviton.model.MeterLogs;
import com.laviton.model.OutPutJson;
import com.laviton.model.OutputJsons;
import com.laviton.model.TennantDetails;
import com.laviton.model.TennantRequest;
import com.laviton.model.TennantRequest1;
import com.laviton.model.TennantRequest2;
import com.laviton.model.TennantResponse;
import com.laviton.model.TennantResponse1;
import com.laviton.model.TennantResponse2;
import com.laviton.model.Tennants;
import com.laviton.service.CustomAuthenticator;
import com.laviton.service.LoginService;
import com.laviton.service.MeterDetailsService;

import XmlExtracUtil.XmlExtract;
@Path("/electric")
public class EnergyConsumptionWebService {
	private static Logger LOG = Logger.getLogger(EnergyConsumptionWebService.class.getName());
	
	 @GET
	    @Path("/loaddata")
	    public Response smsccacheload() throws FileNotFoundException, IOException, ParserConfigurationException, SAXException, ParseException {
		 MeterDetailsService serv=new MeterDetailsService();
		 Properties prop=new Properties();
			prop.load(new FileInputStream(LavitonConstants.PropertiesFilePath));
			LOG.info("In WebService");
			String csvFile = prop.getProperty("meterlogsData")+prop.getProperty("filename");
	serv.addMeterDetails(csvFile);
	

	return Response.status(200).build();

	}

	 @GET
	    @Path("/loadmeterdata")
	    public Response loadmeterdetails() throws FileNotFoundException, IOException, ParserConfigurationException, SAXException, ParseException {
		 Properties prop=new Properties();
			prop.load(new FileInputStream(LavitonConstants.PropertiesFilePath));
	CustomAuthenticator cus=new CustomAuthenticator();
	String url=prop.getProperty("meterDetailsData");
	cus.addMeterDetails(url);
	System.out.println("in rest");

	return Response.status(200).build();

	}

	 @POST
	    @Path("/data")
	    @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public OutPutJson sendDataTest(InputJson input) throws JSONException, JsonParseException, JsonProcessingException, IOException, ParseException {
		XmlExtract xml = new XmlExtract();
		System.out.println("in json ");
		OutPutJson output = new OutPutJson();
		MeterLogsDao dao = new MeterLogsDao();
		System.out.println(input.getTime().toString());
		String penergy = "";
		String valueFromDB = input.getTime().toString();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dat=(java.util.Date) sdf.parse(valueFromDB);
		//String date[] = valueFromDB.split("-");

		Float energy = 0.0f;

		/*System.out.println(date[0] + date[1] + date[2]);
		date[2] = xml.getcorrect(date[2]);
		System.out.println(date[0] + date[1] + date[2]);
		String pattern = date[0] + "-" + date[1] + "-" + date[2];
		String date1 = date[0] + "-" + date[1] + "-" + date[2];
		System.out.println("Pattern:" + pattern);
*/
		try {

			ArrayList<MeterLogs> readings = dao.getlogs(valueFromDB);
			energy = xml.getenergyConsumed(readings);
			penergy = "" + energy + "kwh";
			output.setDate(valueFromDB);
			output.setPenergy(penergy);
			System.out.println("in ws");

		} catch (Exception e) {
			System.out.println(e);
		}
		return output;

	}
	 @POST
	    @Path("/databydate")
	    @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public OutputJsons sendTest(InputJsonDates input) throws JSONException, JsonParseException, JsonProcessingException, IOException, ParseException {
		OutputJsons oj=new OutputJsons();
		
		MeterDetailsService sv=new MeterDetailsService();

		if(input.getAccestoken().equals(LavitonConstants.AccessToken))
		{
			
		if(input.getEdate()!=null && input.getSdate()!=null)
		{ 
			if(input.getReqtype().toUpperCase().equals("WEEKLY"))
		
		{
			oj=sv.weekdatas(input.getEdate()
					);
		}
		 else if (input.getReqtype().toUpperCase().equals("MONTHLY")) {
			 oj=sv.monthdatas(input.getEdate());
			
		}
			
		}
		}
		
		 if(input.getReqtype().toUpperCase().equals("WEEKLY"))
		{
			oj=sv.weekdatas(input.getEdate()
					);
		}
		 else if (input.getReqtype().toUpperCase().equals("MONTHLY")) {
			 oj=sv.monthdatas(input.getEdate());
			
		}
		 else{
			 oj.setError("Invalid Request Type");
		 }
		 return oj;
	}
	 @POST
	    @Path("/addLogin")
	    @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response logintest(LoginRequesst input) throws JSONException, JsonParseException, JsonProcessingException, IOException, ParseException {
		 LoginResponse oj=new LoginResponse();
		 LoginService service=new LoginService();
		 if(service.check(input.getUsername(), input.getPassword(),input.getType()))
		 {
			oj.setAccessRequest(LavitonConstants.AccessToken);
			oj.setSuccess("true");
			oj.setUsername(input.getUsername());
		 }
		 else{
			 oj.setSuccess("false");
			 oj.setError("Wrong Credentials");
			 
		 }
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(oj)
	            .build();
	}
	 @POST
	    @Path("/addTennant")
	    @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response addTennat(TennantRequest input) throws JSONException, JsonParseException, JsonProcessingException, IOException, ParseException {
		TennantResponse response=new TennantResponse();
		TennantDao dao=new TennantDao();
		TennantDetails tennant=new TennantDetails();
		
		if(input.getAccesstoken().equals(LavitonConstants.AccessToken))
		{
		tennant.setTennant_account_number(input.getAccountno());
		tennant.setTennant_address(input.getAddress());
		tennant.setTennant_display_name(input.getDisplayname());
		tennant.setTennant_billing_interval(input.getBillinginterval());
		tennant.setTennant_last_billdate(input.getLastbilldate());
		tennant.setTennant_name(input.getTennantname());
		tennant.setTennant_end_date(input.getEnddate());
		tennant.setTennant_next_billdate(input.getNextbilldate());
		tennant.setTennant_start_date(input.getStartdate());
		dao.addmeterLogs(tennant);
		
		int id=dao.getTennatByacno(input.getAccountno());
		if(id!=0)
		{response.setSuccess("true");
			response.setTennantid(""+id);
		}
		else{
			response.setError("Error in adding tennant");
			response.setSuccess("false");
		}
		
		
		}
		else{
			response.setError("Wrong Access Token");
			response.setSuccess("false");
		}
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(response)
	            .build();
	}
	 @POST
	    @Path("/deleteTennant")
	    @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response deleteTennant(TennantRequest1 input) throws JSONException, JsonParseException, JsonProcessingException, IOException, ParseException {
		TennantResponse response=new TennantResponse();
		TennantDao dao=new TennantDao();
		TennantDetails tennant=new TennantDetails();
		
	if(input.getAcesstoken().equals(LavitonConstants.AccessToken))
	{
	tennant=dao.getlogs(Integer.parseInt(input.getTennantid()));
	if(tennant!=null)
	{
	
		dao.deletetennant(tennant);
		response.setSuccess("true");
		response.setTennantid(""+tennant.getTennant_id());
		
	}
	else{
		response.setError("Tennant not found");
		response.setSuccess("false");
	}
	
	}
	else{
		response.setError("Wrong Access Token");
		response.setSuccess("false");
	}
	return Response
            .status(200)
            .header("Access-Control-Allow-Origin", "*")
           
            .entity(response)
            .build();
	 }
	 @POST
	    @Path("/editTennant")
	    @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response editTennat(TennantRequest input) throws JSONException, JsonParseException, JsonProcessingException, IOException, ParseException {
			TennantResponse response=new TennantResponse();
			TennantDao dao=new TennantDao();
			TennantDetails tennant=new TennantDetails();
			TennantDetails tennant1=new TennantDetails();
			
			
			if(input.getAccesstoken().equals(LavitonConstants.AccessToken))
			{
			tennant.setTennant_id(Integer.parseInt(input.getTennantid()));
			tennant1=dao.getlogs(Integer.parseInt(input.getTennantid()));
			if(input.getAccountno()!=null)
			tennant.setTennant_account_number(input.getAccountno());
			else{
				tennant.setTennant_account_number(tennant1.getTennant_account_number());
			}
			if(input.getAddress()!=null)
			tennant.setTennant_address(input.getAddress());
			else{
				tennant.setTennant_address(tennant1.getTennant_address());
			}
			if(input.getDisplayname()!=null)
			tennant.setTennant_display_name(input.getDisplayname());
			else{
				tennant.setTennant_display_name(tennant1.getTennant_display_name());
			}
			if(input.getBillinginterval()!=null)
			tennant.setTennant_billing_interval(input.getBillinginterval());
			else{
				tennant.setTennant_billing_interval(tennant1.getTennant_billing_interval());
			}
			if(input.getLastbilldate()!=null)
			tennant.setTennant_last_billdate(input.getLastbilldate());
			else{
				tennant.setTennant_last_billdate(tennant1.getTennant_last_billdate());
			}
			if(input.getTennantname()!=null)
			tennant.setTennant_name(input.getTennantname());
			else{
				tennant.setTennant_name(tennant1.getTennant_name());
			}
			if(input.getEnddate()!=null)
			tennant.setTennant_end_date(input.getEnddate());
			else{
				tennant.setTennant_end_date(tennant1.getTennant_end_date());
			}
			if(input.getNextbilldate()!=null)
			tennant.setTennant_next_billdate(input.getNextbilldate());
			else{
				tennant.setTennant_next_billdate(tennant1.getTennant_next_billdate());
			}
			if(input.getStartdate()!=null)
			tennant.setTennant_start_date(input.getStartdate());
			else{
				tennant.setTennant_start_date(tennant1.getTennant_start_date());
			}
			dao.updatetennant(tennant);
			
			response.setSuccess("true");
			response.setTennantid(input.getTennantid());
			
			}
			else{
				response.setError("Wrong Access Token");
				response.setSuccess("false");
			}
			return Response
            .status(200)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
            
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .header("Access-Control-Max-Age", "1209600")
		           
		            .entity(response)
		            .build();
		}
	 @POST
	    @Path("/retrieveTennant")
	    @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response retrieveTennant(TennantRequest1 input) throws JSONException, JsonParseException, JsonProcessingException, IOException, ParseException {
		
				TennantResponse1 response=new TennantResponse1();
				TennantDao dao=new TennantDao();
				TennantDetails tennant=new TennantDetails();
				
			if(input.getAcesstoken().equals(LavitonConstants.AccessToken))
			{
			tennant=dao.getlogs(Integer.parseInt(input.getTennantid()));
			if(tennant!=null)
			{
			
	
				response.setSuccess("true");
				response.setTennantid(""+tennant.getTennant_id());
				response.setAccountno(tennant.getTennant_account_number());
				response.setAddress(tennant.getTennant_address());
				response.setBillinginterval(tennant.getTennant_billing_interval());
				response.setDisplayname(tennant.getTennant_display_name());
				response.setEnddate(tennant.getTennant_end_date());
				response.setLastbilldate(tennant.getTennant_last_billdate());
				response.setNextbilldate(tennant.getTennant_next_billdate());
				response.setStartdate(tennant.getTennant_start_date());
				response.setTennantname(tennant.getTennant_name());
				
				
			}
			else{
				response.setError("Tennant not found");
				response.setSuccess("false");
			}
			
			}
			else{
				response.setError("Wrong Access Token");
				response.setSuccess("false");
			}
			return Response
            .status(200)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
            
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .header("Access-Control-Max-Age", "1209600")
		            .entity(response)
		            .build();
			 }
	 @POST
	    @Path("/listoftennat")
	    @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response listing(TennantRequest2 input) throws JSONException, JsonParseException, JsonProcessingException, IOException, ParseException {
		OutputJsons oj=new OutputJsons();
		TennantResponse2 tr=new TennantResponse2();
		
		LoginDao dao =new LoginDao();
		ArrayList<TennantDetails> list=new ArrayList<TennantDetails>();
		ArrayList<Tennants> l=new ArrayList<Tennants>();
		if(input.getAccesstoken().equals(LavitonConstants.AccessToken))
		{
			list=dao.getList();
			for(TennantDetails tl:list){
				Tennants response=new Tennants();
				response.setTennantid(""+tl.getTennant_id());
			response.setAccesstoken(LavitonConstants.AccessToken);
			response.setAccountno(tl.getTennant_account_number());
			response.setAddress(tl.getTennant_address());
			response.setBillinginterval(tl.getTennant_billing_interval());
			response.setDisplayname(tl.getTennant_display_name());
			response.setEnddate(tl.getTennant_end_date());
			response.setLastbilldate(tl.getTennant_last_billdate());
			response.setNextbilldate(tl.getTennant_next_billdate());
			response.setStartdate(tl.getTennant_start_date());
			response.setTennantname(tl.getTennant_name());
			l.add(response);
			}
			tr.setResponses(l);
			tr.setSuccess("true");
		}
		else{
			tr.setSuccess("false");
			tr.setError("Wrong Access Token");
			
		}
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(tr)
	            .build();
	}
	 @POST
	    @Path("/listofmeters")
	    @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response listingmeters(TennantRequest1 input) throws JSONException, JsonParseException, JsonProcessingException, IOException, ParseException {
		MeterList meter=new MeterList();
		ArrayList<MeterList> lists=new ArrayList<MeterList>();
		
		MeterLists response=new MeterLists();
		MeterDetailsDao dao =new MeterDetailsDao();
		ArrayList<MeterDetails> list=new ArrayList<MeterDetails>();
		ArrayList<Tennants> l=new ArrayList<Tennants>();
		if(input.getAcesstoken().equals(LavitonConstants.AccessToken))
		{
			list=dao.getlogs();
			for(MeterDetails tl:list){
				meter.setAssigned(tl.getMeterassign());
				meter.setMeteraddress(tl.getMeter_address());
				meter.setMeterclass(tl.getMeter_class());
				meter.setMeterid(""+tl.getMeter_id());
				meter.setMetername(tl.getMeter_name());
				meter.setMetertype(tl.getMeter_type());
				lists.add(meter);
			}
			response.setSuccess("true");
			response.setM(lists);
		}
		else{
			response.setSuccess("false");
			response.setError("Wrong Access Token");
			
		}
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(response)
	            .build();
	}
	 @POST
	    @Path("/deleteMeters")
	    @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response deleteMeters(MeterDetailsRequest input) throws JSONException, JsonParseException, JsonProcessingException, IOException, ParseException {
		AssignMeter response=new AssignMeter();
		TennantDao dao=new TennantDao();
		TennantDetails tennant=new TennantDetails();
		MeterDetailsDao meterdao=new MeterDetailsDao();
		MeterDetails meter=new MeterDetails();
		
	if(input.getAccesstoken().equals(LavitonConstants.AccessToken))
	{
		tennant=dao.getlogs(Integer.parseInt(input.getTennantid()));
		meter=meterdao.getlogs(Integer.parseInt(input.getMeterid()));
		if(tennant!=null && meter!=null)
		{
			dao.removemeter(Integer.parseInt(input.getTennantid()),Integer.parseInt(input.getMeterid()));
			response.setSuccess("true");
			response.setMeterid(""+input.getMeterid());
			response.setTennantid(""+input.getTennantid());
			
		}
		
		else{
			response.setError("Wrong Credentials Entered");
			response.setSuccess("false");
		}
		
	}
	else{
		response.setError("Wrong Access Token");
		response.setSuccess("false");
	}
	return Response
         .status(200)
         .header("Access-Control-Allow-Origin", "*")
        
         .entity(response)
         .build();
	 }
	 @POST
	    @Path("/assignmeters")
	    @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response asssignMeters(MeterDetailsRequest input) throws JSONException, JsonParseException, JsonProcessingException, IOException, ParseException {
			AssignMeter response=new AssignMeter();
			TennantDao dao=new TennantDao();
			TennantDetails tennant=new TennantDetails();
			MeterDetailsDao meterdao=new MeterDetailsDao();
			MeterDetails meter=new MeterDetails();
			
		if(input.getAccesstoken().equals(LavitonConstants.AccessToken))
		{
			
			if(tennant!=null && meter!=null)
			{
				dao.assignmeter(Integer.parseInt(input.getTennantid()),Integer.parseInt(input.getMeterid()));
				response.setSuccess("true");
				response.setMeterid(""+input.getMeterid());
				response.setTennantid(""+input.getTennantid());
				
			}
			
			else{
				response.setError("Wrong Credentials Entered");
				response.setSuccess("false");
			}
			
		}
		else{
			response.setError("Wrong Access Token");
			response.setSuccess("false");
		}
		return Response
	         .status(200)
	         .header("Access-Control-Allow-Origin", "*")
	        
	         .entity(response)
	         .build();
		 }
}