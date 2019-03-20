package wrapper.streams.managers.filters;
import java.util.*;
public class Errors {
	private final static List<String> errors;
	
	
	static {
		errors = new ArrayList<String>();
		errors.add("conversionfailed!");
		errors.add("unkownencoder");
	}
	
	
	private static String normalize(String toBeNormalize) {
		return toBeNormalize.toLowerCase().replace(" ","");
	}
	
	
	public static boolean track(String tracked) {
		tracked = normalize(tracked);
		for(String error : errors) {
			if(tracked.contains(error)) 
				return false;
		}
		return true;
	}
}
