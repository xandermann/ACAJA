package wrapper;
import java.io.IOException;
/**
 * [ SUPERCLASSE ABSTRAITE POUR L'INTERFACAGE JAVA - FFMPEG ---- DEGRE 1. ]
 * 
 * Cette classe realise l'interfacage de degre 1 de FFMPEG en JAVA. 
 * 
 * Le degre 1 consiste a permettre l'execution de requetes FFMPEG
 * directement en JAVA. De ce fait les methodes de la classe ont 
 * des roles primitifs, le niveau 1 n''exige pas de fonctionnalites 
 * complexes.
 *
 * Cette classe se charge de connaitre le chemin vers le fichier executable 
 * de FFMPEG. Elle se charge, aussi comme il a ete dis avant, d'executer
 * des requetes FFMPEG en interne par l'intermediaire de JAVA, sans avoir
 * a utiiser de SHELL ( = interpreteur de lignes de commandes ).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public abstract class FFmpegRuntime {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ ATTRIBUT DE CLASSE - CHEMIN VERS FFMPEG.]
	 * 
	 * Ceci est un attribut de classe a usage uniquement interne
	 * ( d'ou le private ). 
	 */
	private static String FFMPEG_PATH;
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
		//Provisoirement j'utilise le ffmpeg de la variable path.
		FFMPEG_PATH = System.getProperty("os.name").contains("Linux") ? "/bin/bash ffmpeg " : "ffmpeg ";
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
	 * 								et a faire executer 
	 * 
	 * @return Process				Un objet de type Process contenant la reponse de FFMPEG a 
	 * 								la requete qui lui est soumise par l'executeur de lignes de 
	 * 								commande RUN de type Runtime. 
	 */
	protected static Process execute(String ffmpegRequest){
		if(FFMPEG_PATH==null) install();
		try {
			return RUN.exec(FFMPEG_PATH+"-i "+ffmpegRequest);
		} catch (IOException e) {
			return null;
		}
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
