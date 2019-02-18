package wrapper.runtime.details;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wrapper.streams.WatchedConsumer;

public class Request {
	private List<String> request;
	private String sourceFileName;
	
	
	
	public Request(String sourceFileName) {
		if((this.sourceFileName = sourceFileName) == null) 
			throw new NullPointerException("SourceFileName null !");
		request = new ArrayList<String>();
		request.add("-i");
		request.add(sourceFileName);
	}
	
	
	
	private void askSomethingElse(String[] somethingElse) {
		if(somethingElse == null) 
			throw new NullPointerException("Argument a ajouter dans la requete null !");		
		for(String element : somethingElse) request.add(element);
	}


	
	public Request buildVideoCodec(String newCodec) {
		askSomethingElse(new String[]{""});
		return this;
	}
	
	public Request buildAudioCodec() {
		askSomethingElse(new String[]{""});
		return this;
	}

	
	
	public ProcessManager result() {
		return FFmpegRuntime.execute(request);
	}
	
	public void make() {
		WatchedConsumer.consumeStreams(result());
	}
}
