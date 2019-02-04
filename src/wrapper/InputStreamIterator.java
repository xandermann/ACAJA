package wrapper;

import java.util.Iterator;

public final class InputStreamIterator extends StreamIterator{
	public InputStreamIterator(Process processToBeConsume) {
		super(processToBeConsume.getInputStream());
	}
}