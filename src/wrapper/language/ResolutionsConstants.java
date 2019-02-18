package wrapper.language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ResolutionsConstants {
	public final static String[] CIF_RESOLUTIONS = {"128x96", "176x144", "352x288", "704x576", "1408x1152"};
	
	public final static String[] VGA_RESOLUTIONS = {"160x120", "320x240", "640x480", "800x600", "852x480"};
	
	public final static String[] GA_RESOLUTIONS = {"1024x768", "1600x1200", "1280x1024", "1366x768", "1600x1024", "1920x1200", "2048x1536", 
												   "2560x1600", "2560x2048", "3200x2048", "3840x2400","5120x4096", "6400x4096", "7680x4800"};
	
	public final static String[] CGA_AND_EGA_RESOLUTIONS = {"320x200", "640x350"};
	
	public final static String[] HD_RESOLUTIONS = {"852x480", "1280x720", "1920x1080"};
	
	public final static String[] ALL_RESOLUTIONS = getAllResolutions();
	
	public static String[] getAllResolutions() {
		List<String> allResolutions = new ArrayList<String>();
		allResolutions.addAll(Arrays.asList(CIF_RESOLUTIONS));
		allResolutions.addAll(Arrays.asList(VGA_RESOLUTIONS));
		allResolutions.addAll(Arrays.asList(GA_RESOLUTIONS));
		allResolutions.addAll(Arrays.asList(CGA_AND_EGA_RESOLUTIONS));
		allResolutions.addAll(Arrays.asList(HD_RESOLUTIONS));
		return allResolutions.toArray(new String[allResolutions.size()]);
	}
}
 