package XmlExtracUtil;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import com.laviton.model.MeterLogs;

public class XmlExtract {
	
	public String getcorrect(String abc)
	{
		String correct=""+abc.charAt(0)+abc.charAt(1);
		return correct;
	}

	public float getenergyConsumed(ArrayList<MeterLogs> met)
	{   float tot=0.0f;
		int l=met.size();
		if(l>0)
		{
		float eng=met.get(0).getMeter_log_total_positive_energy();
		
		tot=met.get((met.size()-1)).getMeter_log_total_positive_energy()-eng;
		}
		
		
		System.out.println(tot);
		return tot;
	}
	public ArrayList<String> listOfDates(Date date,int day)
	{
		ArrayList<String> dates=new ArrayList<String>();
		for(int i=0;i<=day;i++)
		{
			  DateTimeFormatter format =
			            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			  LocalDate now = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        LocalDate then = now.minusDays(i);
		        String t=""+then;
		        Date date1 = Date.from(then.atStartOfDay(ZoneId.systemDefault()).toInstant());
		        dates.add(t);
		}
		return dates;		
	}
}
