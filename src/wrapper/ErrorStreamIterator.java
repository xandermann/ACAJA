package wrapper;

import java.util.Iterator;

public final class ErrorStreamIterator extends StreamIterator{
	public ErrorStreamIterator(Process processToBeConsume) {
		super(processToBeConsume.getErrorStream());
	}
}
