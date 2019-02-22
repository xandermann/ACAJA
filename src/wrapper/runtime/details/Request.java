package wrapper.runtime.details;

import java.util.*;
import wrapper.language.FlagConstants;
import wrapper.streams.iterators.ProcessManager;
import wrapper.streams.managers.consumers.WatchedConsumer;

/**
 * [ CLASSE POUR LA CONSTITUTION DES REQUETES FFMPEG. ]
 * 
 * Cette classe comporte une majorité de methodes publiques 
 * retourant this. Cela permet d'enchainer en une meme instruction 
 * un nombre "infini" d'operations, de la memme maniere qu'on le ferait
 * en saisissant les flags de FFmpeg dans un SHELL. Cela ets tres pratique 
 * et tres intuitif. 
 * 
 * C'est une classe cle dans cet interfacage de FFmpeg 
 * en JAVA. 
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 *
 */
public final class Request {
	/**
	 * LA REQUETE FFMPEG : INPUT FILE / FLAG(S) / VALEUR(S) / OUTPUT FILE. 
	 */
	private List<String> request;
	/**
	 * FICHIER D'ENTREE (pas toujours necessaire).  
	 */
	private String input;
	/**
	 * FICHIER DE SORTIE (pas toujours necessaire).  
	 */
	private String output;
	
	
	
	/**
	 * [ CONSTRUCTEUR VIDE ]
	 * 
	 * Ce constructeur sert a realiser des requetes qui ne necessitent
	 * ni de fichier d'entree, ni de fichier de sortie. 
	 */
	public Request() {
		input = null;
		output = null;
		request = new ArrayList<String>();
	}
	
	/**
	 * [ CONSTRUCTEUR AVEC PARAMETRE. ]
	 * 
	 * Ce constructeur sert a realiser des requetes qui ne necessitent
	 * que le fichier d'entree. 
	 * 
	 * @param input			Le nom complet ( chemin + nom ) du fichier d'entree. 
	 */
	public Request(String input) {
		if((this.input = input) == null) throw new NullPointerException("input null !");
		output = null;
		request = new ArrayList<String>();
		request.add("-i");
		request.add(input);
	}
	
	/**
	 * [ CONSTRUCTEUR AVEC PARAMETRES. ]
	 * 
	 * Ce constructeur sert a realiser des requetes qui necessitent
	 * un fichier d'entree et un ficheir de sortie. 
	 * 
	 * @param input			Le nom complet ( chemin + nom ) du fichier d'entree. 
	 * @param output		Le nom complet ( chemin + nom ) du fichier de sortie. 
	 */
	public Request(String input, String output) {
		if((this.output = output) == null) throw new NullPointerException("Output null !");
		request = new ArrayList<String>();
		request.add("-i");
		request.add(input);
	}
	
	
	
	/**
	 * [ AJOUTER/MODIFIER UN FICHIER D'ENTREE. ]
	 * 
	 * @param input		Le nom complet ( chemin + nom ) du fichier de sortie. 
	 * 
	 * @return La requete this. 
	 */
	public Request from(String input) {
		if(input == null) throw new NullPointerException("Input null !");
		if(this.input != null) 
			request.set(1, this.input = input);
		else{
			request.add(0, "-i");
			request.add(1, this.input = input);
		}
		return this;
	}
	
	/**
	 * [ AJOUTER/MODIFIER UN FICHIER DE SORTIE. ]
	 * 
	 * @param output	Le nom complet ( chemin + nom ) du fichier de sortie. 
	 * 
	 * @return La requete this. 
	 */
	public Request to(String output) {
		if((this.output = output) == null) throw new NullPointerException("Output null !");
		return this;
	}
	
	
	
	/**
	 * [ METHODE INTERNE - AJOUTER DES ARGUMENTS A LA REQUETE. ]
	 * 
	 * @param somethingElse		Les arguemnts a ajouter. 
	 */
	private void askSomethingElse(String[] somethingElse) {
		if(somethingElse == null) throw new NullPointerException("Argument a ajouter dans la requete null !");		
		for(String element : somethingElse) request.add(element);
	}


	
	/**
	 * [ CONNAITRE LES CODECS SUPPORTES PAR FFMPEG. ]
	 * 
	 * @return La requete this. 
	 */
	public Request codecs() {
		askSomethingElse(new String[]{FlagConstants.FLAG_SUPPORTED_CODECS});
		return this;
	}
	
	
	
	/**
	 * [ MODIFIER LE CODEC D'UNE VIDEO. ]
	 * 
	 * @param videoCodec	Le nouveau codec. 
	 * 
	 * @return La requete this. 
	 */
	public Request videoCodec(String videoCodec) {
		if(videoCodec==null) throw new NullPointerException("VideoCodec null !");
		if(Integer.parseInt(videoCodec)<=0) throw new IllegalArgumentException("VideoCodec negatif ou nul !");
		askSomethingElse(new String[]{FlagConstants.FLAG_VIDEO_CODEC, videoCodec});
		return this;
	}
	
	/**
	 * [ MODIFIER LE BITRATE D'UNE VIDEO. ]
	 * 
	 * @param videoBitrate	Le nouveau bitrate. 
	 * 
	 * @return La requete this. 
	 */
	public Request videoBitrate(String videoBitrate) {
		if(videoBitrate==null) throw new NullPointerException("VideoBitrate null !");
		if(Integer.parseInt(videoBitrate)<=0) throw new IllegalArgumentException("VideoBitrate negatif ou nul !");
		askSomethingElse(new String[]{FlagConstants.FLAG_VIDEO_BITRATE, videoBitrate});
		return this;
	}

	/**
	 * [ MODIFIER LA RESOLUTION D'UNE VIDEO. ]
	 * 
	 * @param resolution	La nouvelle resolution. 
	 * 
	 * @return La requete this. 
	 */
	public Request resolution(String resolution) {
		if(resolution==null) throw new NullPointerException("Resolution null !");
		if(Integer.parseInt(resolution)<=0) throw new IllegalArgumentException("Resolution negative ou nulle !");
		askSomethingElse(new String[]{FlagConstants.FLAG_RESOLUTION, resolution});
		return this;
	}
	
	/**
	 * [ MODIFIER LE TAUX D'IMAGES PAR SECONDE (FPS) D'UNE VIDEO. ]
	 * 
	 * @param framerate		Le taux d'images. 
	 * 
	 * @return La requete this. 
	 */
	public Request framerate(String framerate) {
		if(framerate==null) throw new NullPointerException("Framerate null !");
		if(Integer.parseInt(framerate)<=0) throw new IllegalArgumentException("Framerate negatif ou nul !");
		askSomethingElse(new String[]{FlagConstants.FLAG_FRAMERATE, framerate});
		return this;
	}
	
	
	
	/**
	 * [ MODIFIER LE CODEC D'UN AUDIO. ]
	 * 
	 * @param audioCodec	Le nouveau codec. 
	 * 
	 * @return La requete this. 
	 */
	public Request audioCodec(String audioCodec) {
		if(audioCodec==null) throw new NullPointerException("AudioCodec null !");
		if(Integer.parseInt(audioCodec)<=0) throw new IllegalArgumentException("AudioCodec negatif ou nul !");
		askSomethingElse(new String[]{FlagConstants.FLAG_AUDIO_CODEC, audioCodec});
		return this;
	}
	
	/**
	 * [ MODIFIER LE BITRATE D'UN AUDIO. ]
	 * 
	 * @param videoBitrate	Le nouveau bitrate. 
	 * 
	 * @return La requete this. 
	 */
	public Request audioBitrate(String audioBitrate) {
		if(audioBitrate==null) throw new NullPointerException("AudioBitrate null !");
		if(Integer.parseInt(audioBitrate)<=0) throw new IllegalArgumentException("AudioBitrate negatif ou nul !");
		askSomethingElse(new String[]{FlagConstants.FLAG_AUDIO_BITRATE, audioBitrate});
		return this;
	}
	
	/**
	 * [ MODIFIER LE TAUX D'ECHANTILLONAGE D'UN AUDIO. ]
	 * 
	 * @param samplingRate	Le nouveau taux d'echantillonage. 
	 * 
	 * @return La requete this. 
	 */
	public Request samplingRate(String samplingRate) {
		if(samplingRate==null) throw new NullPointerException("SamplingRate null !");
		if(Integer.parseInt(samplingRate)<=0) throw new IllegalArgumentException("SamplingRate negative ou nulle !");
		askSomethingElse(new String[]{FlagConstants.FLAG_SAMPLING_RATE, samplingRate});
		return this;
	}

	/**
	 * [ MODIFIER LE NOMBRE DE CANAUX AUDIO. ]
	 * 
	 * @param samplingRate	Le nouveau nombre de canaux audio. 
	 * 
	 * @return La requete this. 
	 */
	public Request numberAudioChannels(String numberAudioChannels) {
		if(numberAudioChannels==null) throw new NullPointerException("NumberAudioChannels null !");
		if(Integer.parseInt(numberAudioChannels)<=0) throw new IllegalArgumentException("NumberAudioChannels negative ou nulle !");
		askSomethingElse(new String[]{FlagConstants.FLAG_NUMBER_AUDIO_CHANNELS, numberAudioChannels});
		return this;
	}

	
	
	
	/**
	 * [ ROGNER UNE VIDEO. ]
	 * 
	 * 			xCorner
	 * 	yCorner + < -------------------- > 
	 * 		   ^	        width
	 *		   |				  
	 * 		   |
	 * 		   | height
	 * 		   |
	 * 		   v 
	 * 
	 * @param xCorner	Abscisse du coin gauche. 
	 * @param yCorner	Ordonnee du coin gauche. 
	 * @param width		La largeur. 
	 * @param height	La hauteur. 
	 * 
	 * @return La requete this. 
	 */
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

	/**
	 * [ PRIVOTER DE 90° UNE VIDEO. ]
	 * 
	 * @return La requete this. 
	 */
	public Request rotate() {
		askSomethingElse(FlagConstants.FLAG_ROTATE);
		return this;
	}
	
	
	
	
	/**
	 * [ EXTRAIRE UNE IMAGE D'UNE VIDEO. ]
	 * 
	 * @param time	Le moment de la video ou se situe l'image. 
	 * 
	 * @return La requete this. 
	 */
	public Request extractImage(String time) {
		if(time==null) throw new NullPointerException("NumberAudioChannels null !");
		askSomethingElse(new String[]{
				FlagConstants.FLAG_PERIOD[0], time, FlagConstants.FLAG_PERIOD[1], "00:00:01.00", 
				FlagConstants.FLAG_FRAMERATE, "1"});
		return this;
	}
	
	/**
	 * [ REDIMMENSIONNER UNE IMAGE. ]
	 * 
	 * @param width		La largeur. 
	 * @param height	La hauteur. 
	 * 
	 * @return La requete this. 
	 */
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
	
	
	
	/**
	 * [ EXECUTER LA REQUETE ET OBTENIR LE RESULAT. ]
	 * 
	 * @return	Le resultat de l'execution de la requete : un ProcessManager. 
	 */
	public ProcessManager result() {
		if(output!=null) askSomethingElse(new String[]{output});
		return FFmpegRuntime.execute(request);
	}
	
	/**
	 * [ EXECUTER LA REQUETE ET CONSOMMER EN INTRENE TOUS LES FLUX DE SORTIE DE FFMPEG. ]
	 * 
	 * @return La requete this. 
	 */
	public Request make() {
		WatchedConsumer.consume(result());
		return this;
	}
}
