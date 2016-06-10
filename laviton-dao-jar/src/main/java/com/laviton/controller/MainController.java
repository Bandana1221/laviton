/*package com.laviton.controller;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.laviton.dao.MeterLogsDao;
import com.laviton.model.MeterLogs;
import com.laviton.model.UploadFile;

@Controller
public class MainController {
	
	
	@Autowired
	private MeterLogsDao meterlogsDao;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home()
	{
		System.out.println("in controller");
		return "index";
	}
	
	@RequestMapping(value="/fileUpload",method=RequestMethod.POST)
	public String fileUploaded(@RequestParam("file") String Name,

			Model model) throws IOException {
		System.out.println("in ");
		System.out.println("in controllr");

		
		

		String csvFile = "G:/Neosoft/Desktop/mb-001.57033814_1.log.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		MeterLogs meterLogs=new MeterLogs();
		

		try {

			br = new BufferedReader(new FileReader(csvFile));
			int i=0;
			while ((line = br.readLine()) != null) {

			       if(i!=0){
			    	   String[] country = line.split(cvsSplitBy);

			    	   meterLogs.setMeter_log_time(country[0]);
			    	   meterLogs.setMeter_log_low_alarm(country[2]);
			    	   meterLogs.setMeter_log_high_alarm(country[3]);
			    	   meterLogs.setMeter_log_total_positive_energy(Float.parseFloat(country[4]));
						meterLogs.setMeter_log_total_negative_energy(Float.parseFloat(country[5]));
						meterLogs.setMeter_log_peak_demand(country[6]);
						meterlogsDao.addmeterLogs(meterLogs);
   			       }
			       i++;
				

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	  
		
		return "index";
	}
}
*/