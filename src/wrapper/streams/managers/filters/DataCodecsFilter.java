package wrapper.streams.managers.filters;

import wrapper.streams.iterators.*;

public final class DataCodecsFilter implements DataStreamsFilter {
	private static String[] findCodecs(String typeCodec, ProcessManager processToStudy) {
		StreamIterator extractor = processToStudy.errorStreamIterator();
		return null;
	}
	
	public static String[] findVideoCodecs(ProcessManager processToStudy) {
		return findCodecs("V", processToStudy);
	}
		
	public static String[] findAudioCodecs(ProcessManager processToStudy) {
		return findCodecs("A", processToStudy);
	}
}
