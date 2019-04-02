package gui.answers;
import java.io.File;
import gui.general.GeneralWindow;
/**
 * [ CLASSE FENETRE DE LA VUE D'UNE REPONSE. ]
 * 
 * Cette classe permet par le biais du constructeur de generer
 * une fenetre contenant la vue d'une reponse.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class AnswerWindow extends GeneralWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7588929209700593196L;

	/**
	 * [ CONSTRUCTEUR AVEC PARAMETRES. ]
	 */
	public AnswerWindow(File f){
		super("Affichage de la reponse : "+f.getName()+".", new AnswerView(f));
	}
}
