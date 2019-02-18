package wrapper.runtime.details;

import java.util.*;
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


	
	
	public Request videoCodec(String videoCodec) {
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_VIDEO_CODEC, videoCodec});
		return this;
	}
	
	public Request videoBitrate(String videoBitrate) {
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_VIDEO_BITRATE, videoBitrate});
		return this;
	}

	public Request resolution(String resolution) {
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_RESOLUTION, resolution});
		return this;
	}
	
	public Request fps(String fps) {
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_FPS, fps});
		return this;
	}
	
	
	

	public Request audioCodec(String audioCodec) {
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_AUDIO_CODEC, audioCodec});
		return this;
	}
	
	public Request audioBitrate(String audioBitrate) {
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_AUDIO_BITRATE, audioBitrate});
		return this;
	}
	
	public Request samplingRate(String samplingRate) {
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_SAMPLING_RATE, samplingRate});
		return this;
	}

	public Request numberAudioChannels(String numberAudioChannels) {
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_NUMBER_AUDIO_CHANNELS, numberAudioChannels});
		return this;
	}

	
	
	
	
	public ProcessManager result() {
		if(output!=null) askSomethingElse(new String[]{output});
		return FFmpegRuntime.execute(request);
	}
	
	public void make() {
		WatchedConsumer.consumeStreams(result());
	}
}
