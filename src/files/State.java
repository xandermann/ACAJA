package files;

public interface State{
	/**
	 * [ METHODE POUR SAVOIR SI THIS A ETE MODIFIE. ]
	 * 
	 * Methode dont le comportement en interne change selon si on est dans
	 * la classe SettingsFile ou dans la classe ProcessingFile.
	 * 
	 * En effet selon la classe, etre modifiee a un sens different. Cela
	 * justifie la declaration l'exsitence de cette interface.
	 * 
	 * Cette methode sera tres utile au moment ou l'utilisateur du logiciel voudra
	 * generer le fichier de sortie, elle permettra des lors de savoir si this a
	 * bien ete modifie et est bien a prendre en compte lors de la generation du
	 * fichier de sortie.
	 * 
	 * @return booleen 						Vaut true si this a ete modifie. 
	 */
	public boolean isModified();
}
