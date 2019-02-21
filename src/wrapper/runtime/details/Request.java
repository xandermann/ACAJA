package wrapper.runtime.details;

import java.util.*;
import wrapper.language.FlagConstants;
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
		askSomethingElse(new String[]{FlagConstants.FLAG_VIDEO_CODEC, videoCodec});
		return this;
	}
	
	public Request videoBitrate(String videoBitrate) {
		if(videoBitrate==null) throw new NullPointerException("VideoBitrate null !");
		askSomethingElse(new String[]{FlagConstants.FLAG_VIDEO_BITRATE, videoBitrate});
		return this;
	}

	public Request resolution(String resolution) {
		if(resolution==null) throw new NullPointerException("Resolution null !");
		askSomethingElse(new String[]{FlagConstants.FLAG_RESOLUTION, resolution});
		return this;
	}
	
	public Request fps(String fps) {
		if(fps==null) throw new NullPointerException("Fps null !");
		askSomethingElse(new String[]{FlagConstants.FLAG_FPS, fps});
		return this;
	}
	
	
	

	public Request audioCodec(String audioCodec) {
		if(audioCodec==null) throw new NullPointerException("AudioCodec null !");
		askSomethingElse(new String[]{FlagConstants.FLAG_AUDIO_CODEC, audioCodec});
		return this;
	}
	
	public Request audioBitrate(String audioBitrate) {
		if(audioBitrate==null) throw new NullPointerException("AudioBitrate null !");
		askSomethingElse(new String[]{FlagConstants.FLAG_AUDIO_BITRATE, audioBitrate});
		return this;
	}
	
	public Request samplingRate(String samplingRate) {
		if(samplingRate==null) throw new NullPointerException("SamplingRate null !");
		askSomethingElse(new String[]{FlagConstants.FLAG_SAMPLING_RATE, samplingRate});
		return this;
	}

	public Request numberAudioChannels(String numberAudioChannels) {
		if(numberAudioChannels==null) throw new NullPointerException("NumberAudioChannels null !");
		askSomethingElse(new String[]{FlagConstants.FLAG_NUMBER_AUDIO_CHANNELS, numberAudioChannels});
		return this;
	}

	
	
	
	public Request crop(int xCorner,int yCorner,int width,int height) {
		if(xCorner<=0) throw new IllegalArgumentException("xCorner negative ou nulle !");
		if(yCorner<=0) throw new IllegalArgumentException("yCorner negative ou nulle !");
		if(width<=0) throw new IllegalArgumentException("Width negative ou nulle !");
		if(height<=0) throw new IllegalArgumentException("height negative ou nulle !");
		String separator = FlagConstants.FLAG_CROP_VIDEO[2];
		askSomethingElse(new String[]{
				FlagConstants.FLAG_CROP_VIDEO[0], 
				FlagConstants.FLAG_CROP_VIDEO[1]+width+separator+height+separator+xCorner+separator+yCorner+FlagConstants.FLAG_CROP_VIDEO[3]});
		return this;
	}
	
	
	public Request extractImage(String numberAudioChannels) {
		if(numberAudioChannels==null) throw new NullPointerException("NumberAudioChannels null !");
		askSomethingElse(new String[]{FlagConstants.FLAG_NUMBER_AUDIO_CHANNELS, numberAudioChannels});
		return this;
	}
	
	public Request resizeImage(int width, int height) {
		if(width<=0) throw new NullPointerException("Width negative ou nulle !");
		if(height<=0) throw new NullPointerException("Height negative ou nulle !");
		askSomethingElse(new String[]{
				FlagConstants.FLAG_RESIZE_IMAGE[0], 
				FlagConstants.FLAG_RESIZE_IMAGE[1]+width+FlagConstants.FLAG_RESIZE_IMAGE[2]+height});
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
