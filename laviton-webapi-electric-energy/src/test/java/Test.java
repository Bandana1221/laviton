/*import static org.junit.Assert.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.laviton.dao.MeterLogsDao;
import com.laviton.model.MeterLogs;

public class Test {

	@org.junit.Test
	public void test() throws ParseException {
		int count=0;
		try{
		String date="2016-04-06 05:00:00";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dat= sdf.parse(date);
		System.out.println(dat.getMonth());
		  DateTimeFormatter format =
		            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		  LocalDate now = dat.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		  System.out.println(now.getMonthValue());
	        LocalDate then = now.minusDays(1);

	        System.out.println(now+"   "+then);
			MeterLogs mt=new MeterLogs();
			MeterLogsDao dao=new MeterLogsDao();
			String date="2016-04-05 03:45:00";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dat= sdf.parse(date);
			LocalDate now = dat.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			mt.setTime(dat);
			String t=""+now;
			mt.setDate(t);
			dao.addmeterLogs(mt);
			ArrayList<MeterLogs> mtr=dao.getlogs(t);
			String date="2016-04-05 03:45:00";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dat= sdf.parse(date);
			
			System.out.println(dat.getTime());
			String change=""+now.getYear()+"-"+now.getMonthValue()+"-"+now.getDayOfMonth();
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
			Date dat2= sdf1.parse(date);
			System.out.println(dat2);
			System.out.println(now.getYear()+"-"+now.getMonthValue()+"-"+now.getDayOfMonth());
			
			
			System.out.println(mtr.get(0).getMeter_log_total_positive_energy());
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
			
			
		
	}

}
*/