package resources;

import java.io.File;

public final class NamesSpaceManager {
	private final static String TEXT = ".txt";
	
	public static String _err() {
		return ResourceConstants.STDERR_ANSWER_PREFIX+ResourceConstants.now()+TEXT;
	}
	
	public static String _out() {
		return ResourceConstants.STDOUT_ANSWER_PREFIX+ResourceConstants.now()+TEXT;
	}
	
	public static File err() {
		return new File(_err());
	}
	
	public static File out() {
		return new File(_out());
	}
}
