package wrapper.runtime.details;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import wrapper.language.ConversionFlagConstants;
import wrapper.streams.WatchedConsumer;

public class Request {
	private List<String> request;
	private String input;
	private String output;
	
	
	
	
	public Request(String input) {
		if((this.input = input) == null) 
			throw new NullPointerException("input null !");
		output = null;
		request = new ArrayList<String>();
		request.add("-i");
		request.add(input);
	}
	
	public Request(String input, String output) {
		if((this.input = input) == null) 
			throw new NullPointerException("input null !");
		if((this.output = output) == null) 
			throw new NullPointerException("ouput null !");
		request = new ArrayList<String>();
		request.add("-i");
		request.add(input);
	}
	
	
	
	
	
	private void askSomethingElse(String[] somethingElse) {
		if(somethingElse == null) 
			throw new NullPointerException("Argument a ajouter dans la requete null !");		
		for(String element : somethingElse) request.add(element);
	}


	
	
	public Request buildVideoCodec(String vcodec) {
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_VIDEO_CODEC, vcodec});
		return this;
	}
	
	public Request buildAudioCodec(String acodec) {
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_VIDEO_CODEC, acodec});
		return this;
	}

	public Request buildResolution(String resolution) {
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_RESOLUTION, resolution});
		return this;
	}
	
	public Request buildSamplingRate(String samplingRate) {
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_SAMPLING_RATE, samplingRate});
		return this;
	}

	
	
	public ProcessManager result() {
		return FFmpegRuntime.execute(request);
	}
	
	public void make() {
		WatchedConsumer.consumeStreams(result());
	}
}
