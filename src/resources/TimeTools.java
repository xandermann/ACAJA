package resources;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

public class TimeTools {
	public static String millisToTime(long millis) {
		String time = "";
		
	    millis -= TimeUnit.DAYS.toMillis(TimeUnit.MILLISECONDS.toDays(millis));
	    
	    long hours = TimeUnit.MILLISECONDS.toHours(millis);
	    millis -= TimeUnit.HOURS.toMillis(hours);
		time += hours>9 ? hours+":" : "0"+hours+":";

				
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        time += minutes>9 ? minutes+":" : "0"+minutes+":";
        
        
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        millis -= TimeUnit.SECONDS.toMillis(seconds);
        time +=  seconds>9 ? seconds+"." : "0"+seconds+".";
		
        time +=  millis>9 ? millis : "0"+millis;
		
		return time;
	}
	
	
	public static long timeToMillis(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
			return sdf.parse("1970-01-01 "+time).getTime();
		} catch (ParseException pe) {
			JOptionPane.showMessageDialog(null, pe.getMessage());
			return -1;
		}
	}
}
