package tools;

public class IdSequence{
	private static int id=1;
	
	public static int newId() {
		return ++id;
	}
	
	public static int currentId() {
		return id;
	}
	
	public static void clear() {
		id=1;
	}
}
