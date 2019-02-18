package wrapper.runtime.details;

import java.io.IOException;
import java.util.List;

import wrapper.streams.ProcessManager;

/**
 * Cette classe se charge de connaitre le chemin vers le fichier executable 
 * de FFMPEG. Elle se charge, aussi comme il a ete dis avant, d'executer
 * des requetes FFMPEG en interne par l'intermediaire de JAVA, sans avoir
 * a utiiser de SHELL ( = interpreteur de lignes de commandes ).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class FFmpegRuntime {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ ATTRIBUT DE CLASSE - CHEMIN VERS FFMPEG.]
	 * 
	 * Ceci est un attribut de classe a usage uniquement interne
	 * ( d'ou le private ). 
	 */
	private static String[] FFMPEG_PATH;
	/**
	 * [ CONSTANTE DE CLASSE - EXECUTEUR DE LIGNES DE COMMANDES. ]
	 * 
	 * Ceci est la constante de classe permettant d'executer des
	 * lignes de commandes en JAVA. 
	 */
	private final static Runtime RUN = Runtime.getRuntime();
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE - ROLE PRIMITIF. ]
	 * 
	 * Cette methode a un role primitif ( ou primaire, selon la 
	 * maniere dont on le dis... ). Cette methode a pour seul role 
	 * d'intialiser le chemin vers le fichier executable de FFMPEG.
	 * 
	 * TODO il faudra recuperer depuis l'interface graphique le chemin,
	 * en le demandant a l'utilisateur ( cf. respect des droits de 
	 * l'utilisateur vu avec M. OUNI. ).
	 */
	private static void install(){
		String os = System.getProperty("os.name");
		if(os.contains("Linux") || os.contains("Mac")){
			FFMPEG_PATH = new String[2];
			FFMPEG_PATH[0] = "/bin/bash";
			FFMPEG_PATH[1] = "ffmpeg";
		}else 
			FFMPEG_PATH = new String[]{"ffmpeg"};
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================


	/**
	 * [ METHODE DE CLASSE - ROLE PRIMITIF. ]
	 * 
	 * Methode pour executer une requete realisee en "code" FFMPEG, en interne 
	 * par l'intermediaire de JAVA. Le chemin vers le fichier executable est 
	 * deja pris en compte en interne dans la methode, ce pourquoi la requete qui
	 * est soumise ne doit contenir que la partie "arguments" d'une requete FFMPEG
	 * classique. 
	 * 
	 * On retourne l'objet Process issu de l'execution, pour que les sous-classes 
	 * realisant l'interfacage de degre 2 puissent acceder aux resultats des requetes 
	 * FFMPEG qu'elles soumettent.
	 * 
	 * @param ffmpegRequest			Une requete FFMPEG : c-a-d une requete realisee en "code" FFMPEG
	 * 								et a faire executer.
	 * 
	 * @return ProcessManager		Un objet de type ProcessManager gerant la reponse de FFMPEG a 
	 * 								la requete qui lui est soumise par l'executeur de lignes de 
	 * 								commande RUN de type Runtime. 
	 */
	public static ProcessManager execute(List<String> ffmpegRequest){
		if(FFMPEG_PATH==null) install();
		try {
			for(int i = 0; i < FFMPEG_PATH.length; i++) ffmpegRequest.add(i, FFMPEG_PATH[i]);
			return new ProcessManager(RUN.exec(ffmpegRequest.toArray(new String[ffmpegRequest.size()])));
		} catch (IOException e) {
			return null;
		}
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
