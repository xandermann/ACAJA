package resources;

public class Tools {
	public static String convertTimeToString(int time) {
		String stTime;
		
		if(time/3600>9)
			stTime = ""+(time/3600);
		else
			stTime = "0"+(time/3600);
		
		time%=3600;		
		if(time/60>9)
			stTime += ":"+(time/60);
		else
			stTime += ":0"+(time/60);
	
		time%=60;		
		if(time>9)
			stTime += ":"+time;
		else
			stTime += ":0"+time;
		
		return stTime;
	}
}
