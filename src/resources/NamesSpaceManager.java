package resources;

import java.io.File;

public final class NamesSpaceManager {
	private final static String TEXT = ".txt", JPG = ".jpg";
	
	public static String _err() {
		return ResourceConstants.STDERR_ANSWER_PREFIX+ResourceConstants.now()+TEXT;
	}
	
	public static String _out() {
		return ResourceConstants.STDOUT_ANSWER_PREFIX+ResourceConstants.now()+TEXT;
	}
	
	public static String _temporary() {
		return ResourceConstants.TEMPORARY_FILES_PREFIX+ResourceConstants.now()+JPG;
	}
	
	public static File err() {
		return new File(_err());
	}
	
	public static File out() {
		return new File(_out());
	}
	
	public static File temporary() {
		return new File(_temporary());
	}
}
