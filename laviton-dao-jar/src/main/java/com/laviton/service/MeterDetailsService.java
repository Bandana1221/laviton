package com.laviton.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.SocketException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laviton.Logger.LoggerInitializer;
import com.laviton.dao.MeterLogsDao;
import com.laviton.model.MeterLogs;
import com.laviton.model.OutPutJson;
import com.laviton.model.OutputJsons;

import XmlExtracUtil.XmlExtract;
import javassist.compiler.ast.Symbol;

public class MeterDetailsService {
	
	private static Logger LOG = Logger.getLogger(MeterDetailsService.class.getName());
	public void addLogs()
	{
		System.out.println(MeterDetailsService.class.getClassLoader().getResource("/home/suraj/logfiles/log4j.properties"));

		   

	}
	public void addMeterDetails(String csvFile) throws ParseException,SocketException, IOException {
		String csv = "/home/suraj/2021/001EC6001D27/";
		FTPClient ftpClient = new FTPClient();
		/* String csv ="G:/Neosoft/Desktop/LavitonDatas/"; */
		System.out.println(csv);
		BufferedReader br = null;
		int j = 0;
		int lineno = 0;
		 String server = "54.169.84.76";
	        int port = 21;
	        String user = "suraj";
	        String pass = "suraj";
		int count =100;
		String[] paths = null;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<String> pathinserted = new ArrayList<String>();
		boolean bool = false;
		ArrayList<MeterLogs> add = new ArrayList<MeterLogs>();
		MeterLogs meterLogs = new MeterLogs();
		MeterLogsDao dao = new MeterLogsDao();
		try {
			ftpClient.connect(server, port);
			 
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Connect failed");
                return;
            }
 
            boolean success = ftpClient.login(user, pass);
 
            if (!success) {
                System.out.println("Could not login to the server");
                return;
            }
			// create new file
			File f = new File("/home/suraj/2021/001EC6001D27/");
			/* File f = new File("G:/Neosoft/Desktop/LavitonDatas/"); */
			System.out.println(f.canExecute());
			f.canRead();
			f.canWrite();
			System.out.println(f.isDirectory());
			// array of files and directory
			paths = f.list();

			System.out.println(f.isDirectory());
			System.out.println(paths.length);
LOG.info("No Of files REcieved"+paths.length);
			for (String path :paths) {
				
					System.out.println(csv + path);
					System.out.println("in loop");
                   System.out.println(paths.length);
					if (path.startsWith("mb-001")) {
						LOG.info("On Starting of File: - Reading Filename at ");
						System.out.println(csv + path);
						lineno = countLine(csv + path);
						System.out.println(lineno);
						int ek = 0;
						File f1 = new File(csv + path);

						br = new BufferedReader(new FileReader(f1));
						int i = 0;
						while ((line = br.readLine()) != null) {
							
							if (i != 0) {
								String[] country = line.split(cvsSplitBy);
								System.out.println(country.length);

								System.out.println(country[0]);
								String date = country[0].replace("\"", "");
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								java.util.Date dat = (java.util.Date) sdf.parse(date);
								meterLogs.setTime(dat);
								LocalDate now = dat.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
								String t=""+now;
								meterLogs.setDate(t);
								if (!country[1].equals("0")) {
									LOG.info("An error Entry encountered");
									System.out.println("in error");
									lineno--;
									System.out.println(lineno);
									int k = 0;
									if (lineno == 1) {
										LOG.info("An error File encountered");
										File f3 = new File(csv + path);

										// tries to delete a non-existing file
										if (f3.isFile()) {
											System.out.println("in if");
											bool = ftpClient.deleteFile(csv+path);
											System.out.println(bool);
										}
										// prints
										LOG.info("An error File is deleted"+ f3.getPath());
										System.out.println("File to be deleted: " + f3.getPath());

										k++;
									}

									else {

										continue;
									}

								} else {
									meterLogs.setMeter_log_low_alarm(country[2]);
									meterLogs.setMeter_log_high_alarm(country[3]);
									meterLogs.setMeter_log_total_positive_energy(Float.parseFloat(country[4]));
									meterLogs.setMeter_log_total_negative_energy(Float.parseFloat(country[5]));
									meterLogs.setMeter_log_peak_demand(country[6]);
									add.add(meterLogs);
									dao.addmeterLogs(meterLogs);
									pathinserted.add(path);
									LOG.info("Entry Successfully added with time"+country[0]+"Energy Reading"+country[4]);
									
								}
							}
							
							i=i+1;
						}LOG.info("File read Successfully");

					

					j++;

					count--;
				} else {
					LOG.info("File not mb-001 type");
					continue;
				}
			}

			int k = 0;
			System.out.println(pathinserted.size());
			for (String path2 : pathinserted) {
				System.out.println("in 2nd delete");
				File f3 = new File(csv + path2);

				// tries to delete a non-existing file
				if (f3.isFile()) {
					System.out.println("in if");
					bool =ftpClient.deleteFile(csv+path2);
					System.out.println(bool);
					LOG.info("An File is deleted");
				}
				// prints
				System.out.println("File to be deleted: " + f3.getPath());

				// prints filename and directory name
				System.out.println(path2);

				k++;
			}
		}

		catch (IOException e) {
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
		LoggerInitializer.InitializeLogger(); 
addLogs();
		System.out.println("Done");
	}
public OutputJsons weekdatas(String date) throws ParseException, JSONException, SocketException, IOException
	{
		XmlExtract xml = new XmlExtract();
		OutputJsons out=new OutputJsons();
		MeterLogsDao dao = new MeterLogsDao();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dat = (java.util.Date) sdf.parse(date);
		ArrayList<String> dates = xml.listOfDates(dat, 7);
		ArrayList<OutPutJson> oj=new ArrayList<OutPutJson>();
		JSONArray ja = new JSONArray();
		float penergy[] = new float[100];
		int i = 0;
		while (i < dates.size()) {
			System.out.println(dates.get(i));
			ArrayList<MeterLogs> mt = dao.getlogs(dates.get(i));
			penergy[i] = xml.getenergyConsumed(mt);
			OutPutJson o =new OutPutJson();
			o.setDate(dates.get(i));
			o.setPenergy(""+penergy[i]+"kwh");
			oj.add(o);

			i++;
		}
		out.setOut(oj);

		
		
		LoggerInitializer.InitializeLogger(); 
		addLogs();
	return out;

	
}
public OutputJsons rangeweekdatas(String date,String date2) throws ParseException, JSONException, SocketException, IOException
{
	XmlExtract xml = new XmlExtract();
	OutputJsons out=new OutputJsons();
	MeterLogsDao dao = new MeterLogsDao();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	java.util.Date dat = (java.util.Date) sdf.parse(date);
	ArrayList<String> dates = xml.listOfDates(dat, 7);
	ArrayList<OutPutJson> oj=new ArrayList<OutPutJson>();
	JSONArray ja = new JSONArray();
	float penergy[] = new float[100];
	int i = 0;
	while (i < dates.size()) {
		System.out.println(dates.get(i));
		ArrayList<MeterLogs> mt = dao.getlogs(dates.get(i));
		penergy[i] = xml.getenergyConsumed(mt);
		OutPutJson o =new OutPutJson();
		o.setDate(dates.get(i));
		o.setPenergy(""+penergy[i]+"kwh");
		oj.add(o);

		i++;
	}
	out.setOut(oj);

	
	
	LoggerInitializer.InitializeLogger(); 
	addLogs();
return out;


}
public OutputJsons monthdatas(String date) throws ParseException, JSONException, SocketException, IOException
{
	XmlExtract xml = new XmlExtract();
	OutputJsons out=new OutputJsons();
	ArrayList<OutPutJson> oj=new ArrayList<OutPutJson>();
	MeterLogsDao dao = new MeterLogsDao();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	java.util.Date dat = (java.util.Date) sdf.parse(date);
	LocalDate now = dat.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	int month=now.getMonthValue();
	int year= now.getYear();
	
	int days=0;
	if(month==4 || month==6 || month==9 || month==11)
	{
		days=30;
	}
	else if (month==2) {
		if(year%400==0)
		{
			days=29;
		}
		else{
			days=28;
		}
		
		
		
	}
	else{
		days=31;
	}
	ArrayList<String> dates = xml.listOfDates(dat, days);
	JSONArray ja = new JSONArray();
	float penergy[] = new float[100];
	int i = 0;
	while (i <dates.size()) {
		ArrayList<MeterLogs> mt = dao.getlogs(dates.get(i));
		penergy[i] = xml.getenergyConsumed(mt);
		OutPutJson o =new OutPutJson();
		o.setDate(dates.get(i));
		o.setPenergy(""+penergy[i]+"kwh");
		oj.add(o);
		i++;
	}

	out.setOut(oj);
	LoggerInitializer.InitializeLogger(); 
	addLogs();
return out;


}
	private int countLine(String filename) {
		int linenumber = 0;
		try {

			File file = new File(filename);

			if (file.exists()) {

				FileReader fr = new FileReader(file);
				LineNumberReader lnr = new LineNumberReader(fr);

				while (lnr.readLine() != null) {
					linenumber++;
				}

				System.out.println("Total number of lines : " + linenumber);

				lnr.close();

			} else {
				System.out.println("File does not exists!");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return linenumber;
	}

}
