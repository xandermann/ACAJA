package wrapper.runtime.details;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import exceptions.UnfindableResourceException;
import resources.NamesSpaceManager;
import resources.ResourceConstants;
import wrapper.language.FlagConstants;
import wrapper.language.ValueConstants;
import wrapper.streams.iterators.ProcessManager;
import wrapper.streams.managers.consumers.WatchedConsumer;

/**
 * [ CLASSE POUR LA CONSTITUTION DES REQUETES FFMPEG. ]
 * 
 * Cette classe comporte une majorite de methodes publiques 
 * retournant this. Cela permet d'enchainer en une meme instruction 
 * un nombre "infini" d'operations, de la memme maniere qu'on le ferait
 * en saisissant une multitude de flags dans une commande FFmpeg dans un SHELL. 
 * Cela est tres pratique et tres intuitif. 
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
public final class Request implements FlagConstants, ValueConstants{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
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
	
	
	
	//=======================================================================================================================
	
	
	
	/**
	 * [ CONSTRUCTEUR VIDE ]
	 * 
	 * Ce constructeur sert a realiser des requetes qui ne necessitent
	 * ni de fichier d'entree, ni de fichier de sortie. 
	 */
	public Request() {
		request = new ArrayList<String>();
	}
	
	/**
	 * [ CONSTRUCTEUR AVEC PARAMETRES. ]
	 * 
	 * Ce constructeur sert a realiser des requetes qui ne necessitent
	 * que le fichier d'entree. 
	 * 
	 * @param input			Le nom complet ( chemin + nom ) du fichier d'entree. 
	 */
	public Request(String input) {
		this();
		from(input);
	}
	
	/**
	 * [ CONSTRUCTEUR AVEC PARAMETRES. ]
	 * 
	 * Ce constructeur sert a realiser des requetes qui necessitent
	 * un fichier d'entree et un fichier de sortie. 
	 * 
	 * @param input			Le nom complet ( chemin + nom ) du fichier d'entree. 
	 * @param output		Le nom complet ( chemin + nom ) du fichier de sortie. 
	 */
	public Request(String input, String output) {
		this(input);
		to(output);
	}
	
	
	
	//=======================================================================================================================
	
	
	
	/**
	 * [ AJOUTER/MODIFIER UN FICHIER D'ENTREE. ]
	 * 
	 * @param input		Le nom complet ( chemin + nom ) du fichier de sortie. 
	 * 
	 * @return La requete this. 
	 */
	public Request from(String input) {
		if(input == null) 
			throw new NullPointerException("Input null !");
		if(!new File(input).exists()) 
			throw new IllegalArgumentException("Input est inexistant !");
		
		if(this.input != null) 
			request.set(1, this.input = input);
		else{
			request.add(0, FLAG_INPUT);
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
		if((this.output = output) == null) 
			throw new NullPointerException("Output null !");
		return this;
	}
	
	
	
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE INTERNE - AJOUTER DES ARGUMENTS A LA REQUETE. ]
	 * 
	 * @param somethingElse		Les arguemnts a ajouter. 
	 */
	private void askSomethingElse(String[] somethingElse) {
		if(somethingElse == null) throw new NullPointerException("Arguments null !");		
		for(String element : somethingElse) {
			if(element == null) throw new NullPointerException("Argument null !");		
			request.add(element);
		}
	}


	
	//=======================================================================================================================
	
	
	
	/**
	 * [ CONNAITRE LES CODECS SUPPORTES PAR FFMPEG. ]
	 * 
	 * @return La requete this. 
	 */
	public Request codecs() {
		askSomethingElse(new String[]{FLAG_SUPPORTED_CODECS});
		return this;
	}
	
	
	
	//=======================================================================================================================
	
	
	
	/**
	 * [ MODIFIER LE CODEC D'UNE VIDEO. ]
	 * 
	 * @param videoCodec	Le nouveau codec. 
	 * 
	 * @return La requete this. 
	 */
	public Request videoCodec(String videoCodec) {
		if(videoCodec==null) throw new NullPointerException("VideoCodec null !");
		askSomethingElse(new String[]{FLAG_VIDEO_CODEC, videoCodec});
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
		askSomethingElse(new String[]{FLAG_VIDEO_BITRATE, videoBitrate});
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
		askSomethingElse(new String[]{FLAG_RESOLUTION, resolution});
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
		askSomethingElse(new String[]{FLAG_FRAMERATE, framerate});
		return this;
	}
	
	
	
	//=======================================================================================================================
	
	
	
	/**
	 * [ MODIFIER LE CODEC D'UN AUDIO. ]
	 * 
	 * @param audioCodec	Le nouveau codec. 
	 * 
	 * @return La requete this. 
	 */
	public Request audioCodec(String audioCodec) {
		if(audioCodec==null) throw new NullPointerException("AudioCodec null !");
		askSomethingElse(new String[]{FLAG_AUDIO_CODEC, audioCodec});
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
		askSomethingElse(new String[]{FLAG_AUDIO_BITRATE, audioBitrate});
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
		askSomethingElse(new String[]{FLAG_SAMPLING_RATE, samplingRate});
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
		askSomethingElse(new String[]{FLAG_NUMBER_AUDIO_CHANNELS, numberAudioChannels});
		return this;
	}

	
	
	//=======================================================================================================================
	
	
	
	/**
	 * [ ROGNER. ]
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
		
		
		if(Integer.parseInt(xCorner)<0) throw new IllegalArgumentException("xCorner negatif !");
		if(Integer.parseInt(yCorner)<0) throw new IllegalArgumentException("yCorner negatif !");
		if(Integer.parseInt(width)<=0) throw new IllegalArgumentException("Width negative ou nulle !");
		if(Integer.parseInt(height)<=0) throw new IllegalArgumentException("Height negative ou nulle !");
		
		askSomethingElse(new String[]{FLAG_CROP[0], FLAG_CROP[1]+width+S+height+S+xCorner+S+yCorner});
		return this;
	}

	/**
	 * [ COUPER. ]
	 * 
	 *  [ -------------------------------------- ] 	=>	input video
	 *  
	 *  			[ --------------------- ]		=>	output video
	 *  		   time			        time+period
	 *  
	 *  
	 * @param time		Le temps de depart de coupage de la video. 
	 * @param perod		La periode a conserver de la video a partir du temps de depart. 
	 * 
	 * @return La requete this. 
	 */
	public Request cut(String time, String period) {
		if(time==null) throw new NullPointerException("Time null !");
		if(period==null) throw new NullPointerException("Period null !");
		askSomethingElse(new String[]{FLAG_PERIOD[0], time, FLAG_PERIOD[1], period});
		return this;
	}
	
	/**
	 * [ PIVOTER DE 90 DEGRES A GAUCHE ]
	 * 
	 * @return La requete this. 
	 */
	public Request rotateLeft() {
		askSomethingElse(new String[] {FLAG_ROTATE[0], FLAG_ROTATE[2]});
		return this;
	}
	
	/**
	 * [ PIVOTER DE 90 DEGRES A DROITE ]
	 * 
	 * @return La requete this. 
	 */
	public Request rotateRight() {
		askSomethingElse(new String[] {FLAG_ROTATE[0], FLAG_ROTATE[1]});
		return this;
	}
	
	/**
	 * [ PIVOTER DE 180 DEGRES ]
	 * 
	 * @return La requete this. 
	 */
	public Request rotate180() {
		askSomethingElse(new String[] {FLAG_ROTATE[0], FLAG_ROTATE[3]});
		return this;
	}
	
	/**
	 * [ PIVOTER DE 180 DEGRES ]
	 * 
	 * @return La requete this. 
	 */
	public Request rotate180Left() {
		askSomethingElse(new String[] {FLAG_ROTATE[0], FLAG_ROTATE[4]});
		return this;
	}
	
	/**
	 * [ PIVOTER DE 180 DEGRES ]
	 * 
	 * @return La requete this. 
	 */
	public Request rotate180Right() {
		askSomethingElse(new String[] {FLAG_ROTATE[0], FLAG_ROTATE[5]});
		return this;
	}
	/**
	 * [ REDIMMENSIONNER. ]
	 * 
	 * @param width		La largeur. 
	 * @param height	La hauteur. 
	 * 
	 * @return La requete this. 
	 */
	public Request resize(String width, String height) {
		if(width==null) throw new NullPointerException("Width null !");
		if(height==null) throw new NullPointerException("Height null !");
		
		if(Integer.parseInt(width)<=0) throw new IllegalArgumentException("Width negative ou nulle !");
		if(Integer.parseInt(height)<=0) throw new IllegalArgumentException("Height negative ou nulle !");
		
		askSomethingElse(new String[]{FLAG_RESIZE[0],FLAG_RESIZE[1]+width+S+height});
		return this;
	}
	
	/**
	 * [ FLOUTER / PIXELISER. ]
	 * 
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
	public Request blur(String xCorner, String yCorner, String width, String height) {
		if(xCorner==null) throw new NullPointerException("xCorner null !");
		if(yCorner==null) throw new NullPointerException("yCorner null !");
		if(width==null) throw new NullPointerException("Width null !");
		if(height==null) throw new NullPointerException("height null !");
		
		
		if(Integer.parseInt(xCorner)<0) throw new IllegalArgumentException("xCorner negatif !");
		if(Integer.parseInt(yCorner)<0) throw new IllegalArgumentException("yCorner negatif !");
		if(Integer.parseInt(width)<=0) throw new IllegalArgumentException("Width negative ou nulle !");
		if(Integer.parseInt(height)<=0) throw new IllegalArgumentException("Height negative ou nulle !");
		
		askSomethingElse(new String[]{FLAG_BLUR[0], 
				FLAG_BLUR[1]+width+S+height+S+xCorner+S+yCorner+FLAG_BLUR[2]+xCorner+S+yCorner+FLAG_BLUR[3]});
		
		for(int i=4; i<FLAG_BLUR.length; i++) askSomethingElse(new String[]{FLAG_BLUR[i]});
				
		return this;
	}
	
	//=======================================================================================================================
	
	
	
	/**
	 * [ EXTRAIRE UNE IMAGE / FRAME. ]
	 * 
	 * @param time	Le moment de la video ou se situe l'image. 
	 * 
	 * @return La requete this. 
	 */
	public Request frame(String time) {
		if(time==null) throw new NullPointerException("Time null !");
		cut(time, ONE_SECOND);
		framerate("1");
		return this;
	}
	
	
	
	//=======================================================================================================================
	
	
	/**
	 * [ CONCATENER. ]
	 * 
	 * @param inputs	Les videos/sons en entree a concatener.
	 * 					
	 * 					Optionnelement, on peut preciser une video/son en entree dans le 
	 * 					constructeur a concatener en plus des autres vidoes fournies 
	 * 					deja a concatener. 
	 * 
	 * 					Il est possibles de concatener des videos qu'avec des videos,
	 * 					de meme pour les sons.
	 * 
	 * @return La requete this. 
	 */
	public Request concat(String[] inputs) {	
		if(inputs==null) 
			throw new NullPointerException("inputs est null !");
		
		try {
			File inputsFile = new File(ResourceConstants.TEMPORARY_FILES_FULL_PATH + "inputs.txt");
			if(inputsFile.exists()) inputsFile.delete();
			
			Writer writer = new BufferedWriter(new FileWriter(inputsFile));
			if(input != null) writer.write("file '"+input+"'\n");
			for(String tmp : inputs) {
				if(tmp==null) 
					throw new NullPointerException("Un des inputs est null !");
				
				if(!new File(tmp).exists())
					throw new IllegalArgumentException("Un des inputs est inexistant !");
				
				writer.write("file '"+tmp+"'\n");
			}
			writer.close();
			
			request.clear();
			askSomethingElse(new String[]{FLAG_CONCAT[0], FLAG_CONCAT[1], FLAG_CONCAT[2], FLAG_CONCAT[3], FLAG_CONCAT[4],
							inputsFile.getAbsolutePath(), FLAG_CONCAT[5], FLAG_CONCAT[6]});
		} catch (IOException ioe) {}
		
		return this;
	}
	
	
	//=======================================================================================================================

	
	
	/**
	 * [ CHOISIR LA QUALITE. ]
	 * 
	 * @param quality		La qualite. 
	 * 
	 * @return La requete this. 
	 */
	public Request quality(String quality) {
		if(quality==null) throw new NullPointerException("Quality null !");
		askSomethingElse(new String[]{FLAG_QUALITY, quality});
		return this;
	}
	
	
	
	//=======================================================================================================================
	
	
	/**
	 * [ SUPPRIMER UN SON. ]
	 * 
	 * @return La requete this. 
	 */
	public Request removeSound() {
		askSomethingElse(FLAG_REMOVE_SOUND);
		return this;
	}
	
	
	/**
	 * [ AJOUTER UN SON. ]
	 * 
	 * @param inputSound		Le son a ajouter.
	 * 
	 * @return La requete this. 
	 */
	public Request addSound(String inputSound) {
		if(inputSound==null) 
			throw new NullPointerException("inputSound est null !");
		
		if(!new File(inputSound).exists())
			throw new IllegalArgumentException("inputSound est inexistant !");
		
		askSomethingElse(new String[]{FLAG_ADD_SOUND[0], inputSound, FLAG_ADD_SOUND[1], FLAG_ADD_SOUND[2], 
						FLAG_ADD_SOUND[3], FLAG_ADD_SOUND[4], FLAG_ADD_SOUND[5], FLAG_ADD_SOUND[6]});
		return this;
	}
	
	
	
	//=======================================================================================================================
	
	
	
	/**
	 * [ INSCRUSTER UNE IMAGE. ]
	 * 
	 * @param inputImage		L'image a incruster.
	 * 
	 * @param xCorner			Abscisse du coin gauche ou incruster l'image. 
	 * @param yCorner			Ordonnee du coin gauche ou incruster l'image.
	 * 
	 * @return La requete this. 
	 */
	public Request addImage(String inputImage, String xCorner, String yCorner) {
		if(inputImage==null) throw new NullPointerException("inputImage est null !");
		if(!new File(inputImage).exists()) throw new IllegalArgumentException("inputImage est inexistant !");
		
		if(xCorner==null) throw new NullPointerException("xCorner null !");
		if(yCorner==null) throw new NullPointerException("yCorner null !");
		
		if(Integer.parseInt(xCorner)<0) throw new IllegalArgumentException("xCorner negatif !");
		if(Integer.parseInt(yCorner)<0) throw new IllegalArgumentException("yCorner negatif !");
		
		askSomethingElse(new String[]{FLAG_ADD_IMAGE[0], inputImage, FLAG_ADD_IMAGE[1], FLAG_ADD_IMAGE[2]+xCorner+S+yCorner});
		return this;
	}
	
	
	
	//=======================================================================================================================
	
	
	
	/**
	 * [ EXECUTER LA REQUETE ET OBTENIR LE RESULAT. ]
	 * 
	 * @return	Le resultat de l'execution de la requete : un ProcessManager. 
	 */
	public ProcessManager result() {
		if(output!=null) askSomethingElse(new String[]{output});
		System.out.println();
		System.out.println("debut requete");
		for(String r : request) {
			System.out.print(r+" ");
		}
		System.out.println("fin requete");
		return FFmpegRuntime.execute(request);
	}
	
	
	/**
	 * [ EXECUTER LA REQUETE ET CONSOMMER EN INTERNE TOUS LES FLUX DE SORTIE DE FFMPEG. ]
	 * 
	 * @return La requete this. 
	 * 
	 * @throws UnfindableResourceException 	Exception sur les ressources introuvables.
	 */
	public Request make() throws UnfindableResourceException {
		WatchedConsumer.consume(result());
		return this;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
