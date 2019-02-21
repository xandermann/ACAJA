package wrapper.runtime.details;

import java.util.*;
import wrapper.language.ConversionFlagConstants;
import wrapper.streams.ProcessManager;
import wrapper.streams.WatchedConsumer;

public class Request {
	private List<String> request;
	private String input;
	private String output;
	
	
	
	
	public Request(String input) {
		if((this.input = input) == null) throw new NullPointerException("input null !");
		output = null;
		request = new ArrayList<String>();
		request.add("-i");
		request.add(input);
	}
	
	public Request(String input, String output) {
		if((this.input = input) == null) throw new NullPointerException("Input null !");
		if((this.output = output) == null) throw new NullPointerException("Output null !");
		request = new ArrayList<String>();
		request.add("-i");
		request.add(input);
	}
	
	
	
	
	public void to(String output) {
		if((this.output=output)==null) throw new NullPointerException("Output null !");
	}
	
	
	
	private void askSomethingElse(String[] somethingElse) {
		if(somethingElse == null) throw new NullPointerException("Argument a ajouter dans la requete null !");		
		for(String element : somethingElse) request.add(element);
	}


	
	
	public Request videoCodec(String videoCodec) {
		if(videoCodec==null) throw new NullPointerException("VideoCodec null !");
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_VIDEO_CODEC, videoCodec});
		return this;
	}
	
	public Request videoBitrate(String videoBitrate) {
		if(videoBitrate==null) throw new NullPointerException("VideoBitrate null !");
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_VIDEO_BITRATE, videoBitrate});
		return this;
	}

	public Request resolution(String resolution) {
		if(resolution==null) throw new NullPointerException("Resolution null !");
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_RESOLUTION, resolution});
		return this;
	}
	
	public Request fps(String fps) {
		if(fps==null) throw new NullPointerException("Fps null !");
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_FPS, fps});
		return this;
	}
	
	
	

	public Request audioCodec(String audioCodec) {
		if(audioCodec==null) throw new NullPointerException("AudioCodec null !");
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_AUDIO_CODEC, audioCodec});
		return this;
	}
	
	public Request audioBitrate(String audioBitrate) {
		if(audioBitrate==null) throw new NullPointerException("AudioBitrate null !");
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_AUDIO_BITRATE, audioBitrate});
		return this;
	}
	
	public Request samplingRate(String samplingRate) {
		if(samplingRate==null) throw new NullPointerException("SamplingRate null !");
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_SAMPLING_RATE, samplingRate});
		return this;
	}

	public Request numberAudioChannels(String numberAudioChannels) {
		if(numberAudioChannels==null) throw new NullPointerException("NumberAudioChannels null !");
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_NUMBER_AUDIO_CHANNELS, numberAudioChannels});
		return this;
	}

	
	
	
	public Request cut(String numberAudioChannels) {
		if(numberAudioChannels==null) throw new NullPointerException("NumberAudioChannels null !");
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_NUMBER_AUDIO_CHANNELS, numberAudioChannels});
		return this;
	}
	
	
	
	public Request resizeWidthImage(String width, String height) {
		if(width==null) throw new NullPointerException("Width null !");
		if(height==null) throw new NullPointerException("Height null !");
		askSomethingElse(new String[]{ConversionFlagConstants.FLAG_NUMBER_AUDIO_CHANNELS, numberAudioChannels});
		return this;
	}
	
	public Request extractImage(String numberAudioChannels) {
		if(numberAudioChannels==null) throw new NullPointerException("NumberAudioChannels null !");
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
