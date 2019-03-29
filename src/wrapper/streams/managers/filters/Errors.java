package wrapper.streams.managers.filters;
import java.util.*;
public class Errors {
	private final static List<String> errors;
	
	
	static {
		errors = new ArrayList<String>();
		errors.add("conversionfailed");
		errors.add("unknownencoder");
		errors.add("unsafefile");
		errors.add("operationnotpermitted");
	}
	
	
	private static String normalize(String toBeNormalize) {
		if(toBeNormalize==null) throw new NullPointerException("toBeNormalize null !");
		return toBeNormalize.toLowerCase().replace(" ","");
	}
	
	
	public static boolean track(String tracked) {
		if(tracked==null) throw new NullPointerException("tracked null !");
		tracked = normalize(tracked);
		for(String error : errors) {
			if(tracked.contains(error)) 
				return false;
		}
		return true;
	}
}
