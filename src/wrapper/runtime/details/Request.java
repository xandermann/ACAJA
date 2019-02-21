package wrapper.runtime.details;

import java.util.*;
import wrapper.language.FlagConstants;
import wrapper.streams.iterators.ProcessManager;
import wrapper.streams.managers.consumers.WatchedConsumer;

public class Request {
	private List<String> request;
	private String input;
	private String output;
	
	
	
	
	public Request() {
		input = null;
		output = null;
		request = new ArrayList<String>();
	}
	
	public Request(String input) {
		if((this.input = input) == null) throw new NullPointerException("input null !");
		output = null;
		request = new ArrayList<String>();
		request.add("-i");
		request.add(input);
	}
	
	public Request(String input, String output) {
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


	
	
	public Request codecs() {
		askSomethingElse(new String[]{FlagConstants.FLAG_SUPPORTED_CODECS});
		return this;
	}
	
	
	
	
	public Request videoCodec(String videoCodec) {
		if(videoCodec==null) throw new NullPointerException("VideoCodec null !");
		if(Integer.parseInt(videoCodec)<=0) throw new IllegalArgumentException("VideoCodec negatif ou nul !");
		askSomethingElse(new String[]{FlagConstants.FLAG_VIDEO_CODEC, videoCodec});
		return this;
	}
	
	public Request videoBitrate(String videoBitrate) {
		if(videoBitrate==null) throw new NullPointerException("VideoBitrate null !");
		if(Integer.parseInt(videoBitrate)<=0) throw new IllegalArgumentException("VideoBitrate negatif ou nul !");
		askSomethingElse(new String[]{FlagConstants.FLAG_VIDEO_BITRATE, videoBitrate});
		return this;
	}

	public Request resolution(String resolution) {
		if(resolution==null) throw new NullPointerException("Resolution null !");
		if(Integer.parseInt(resolution)<=0) throw new IllegalArgumentException("Resolution negative ou nulle !");
		askSomethingElse(new String[]{FlagConstants.FLAG_RESOLUTION, resolution});
		return this;
	}
	
	public Request framerate(String framerate) {
		if(framerate==null) throw new NullPointerException("Framerate null !");
		if(Integer.parseInt(framerate)<=0) throw new IllegalArgumentException("Framerate negatif ou nul !");
		askSomethingElse(new String[]{FlagConstants.FLAG_FRAMERATE, framerate});
		return this;
	}
	
	
	

	public Request audioCodec(String audioCodec) {
		if(audioCodec==null) throw new NullPointerException("AudioCodec null !");
		if(Integer.parseInt(audioCodec)<=0) throw new IllegalArgumentException("AudioCodec negatif ou nul !");
		askSomethingElse(new String[]{FlagConstants.FLAG_AUDIO_CODEC, audioCodec});
		return this;
	}
	
	public Request audioBitrate(String audioBitrate) {
		if(audioBitrate==null) throw new NullPointerException("AudioBitrate null !");
		if(Integer.parseInt(audioBitrate)<=0) throw new IllegalArgumentException("AudioBitrate negatif ou nul !");
		askSomethingElse(new String[]{FlagConstants.FLAG_AUDIO_BITRATE, audioBitrate});
		return this;
	}
	
	public Request samplingRate(String samplingRate) {
		if(samplingRate==null) throw new NullPointerException("SamplingRate null !");
		if(Integer.parseInt(samplingRate)<=0) throw new IllegalArgumentException("SamplingRate negative ou nulle !");
		askSomethingElse(new String[]{FlagConstants.FLAG_SAMPLING_RATE, samplingRate});
		return this;
	}

	public Request numberAudioChannels(String numberAudioChannels) {
		if(numberAudioChannels==null) throw new NullPointerException("NumberAudioChannels null !");
		if(Integer.parseInt(numberAudioChannels)<=0) throw new IllegalArgumentException("NumberAudioChannels negative ou nulle !");
		askSomethingElse(new String[]{FlagConstants.FLAG_NUMBER_AUDIO_CHANNELS, numberAudioChannels});
		return this;
	}

	
	
	
	public Request crop(String xCorner, String yCorner, String width, String height) {
		if(xCorner==null) throw new NullPointerException("xCorner null !");
		if(yCorner==null) throw new NullPointerException("yCorner null !");
		if(width==null) throw new NullPointerException("Width null !");
		if(height==null) throw new NullPointerException("height null !");
		
		
		if(Integer.parseInt(xCorner)<=0) throw new IllegalArgumentException("xCorner negatif ou nul !");
		if(Integer.parseInt(yCorner)<=0) throw new IllegalArgumentException("yCorner negatif ou nul !");
		if(Integer.parseInt(width)<=0) throw new IllegalArgumentException("Width negative ou nulle !");
		if(Integer.parseInt(height)<=0) throw new IllegalArgumentException("Height negative ou nulle !");
		
		String s = FlagConstants.FLAG_CROP[2];
		askSomethingElse(new String[]{
				FlagConstants.FLAG_CROP[0], 
				FlagConstants.FLAG_CROP[1]+width+s+height+s+xCorner+s+yCorner+FlagConstants.FLAG_CROP[3]});
		return this;
	}
	
	public Request rotate() {
		askSomethingElse(FlagConstants.FLAG_ROTATE);
		return this;
	}
	
	
	
	
	public Request extractImage(String time) {
		if(time==null) throw new NullPointerException("NumberAudioChannels null !");
		askSomethingElse(new String[]{
				FlagConstants.FLAG_PERIOD[0], time, FlagConstants.FLAG_PERIOD[1], "00:00:01.00", 
				FlagConstants.FLAG_FRAMERATE, "1"});
		return this;
	}
	
	public Request resizeImage(String width, String height) {
		if(width==null) throw new NullPointerException("Width null !");
		if(height==null) throw new NullPointerException("Height null !");
		
		if(Integer.parseInt(width)<=0) throw new IllegalArgumentException("Width negative ou nulle !");
		if(Integer.parseInt(height)<=0) throw new IllegalArgumentException("Height negative ou nulle !");
		
		askSomethingElse(new String[]{
				FlagConstants.FLAG_RESIZE[0], 
				FlagConstants.FLAG_RESIZE[1]+width+FlagConstants.FLAG_RESIZE[2]+height});
		return this;
	}
	
	
	
	
	public ProcessManager result() {
		if(output!=null) askSomethingElse(new String[]{output});
		return FFmpegRuntime.execute(request);
	}
	
	public void make() {
		WatchedConsumer.consume(result());
	}
}
