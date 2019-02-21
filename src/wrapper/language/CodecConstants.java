package wrapper.language;

import wrapper.runtime.global.SystemRequests;

public final class CodecConstants {
	public final static String[] ALL_SUPPORTED_VIDEO_CODECS = SystemRequests.askVideoCodecs();
	public final static String[] ALL_SUPPORTED_AUDIO_CODECS = SystemRequests.askAudioCodecs();
}
