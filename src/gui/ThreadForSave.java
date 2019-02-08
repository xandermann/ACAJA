package gui;
public class ThreadForSave extends Thread{
	private Model model;
	
	/**
	 * [ CONSTRUCTEUR INTERNE - ENCAPSULE ]
	 * 
	 * @param model		Le modele. 
	 */
	private ThreadForSave(Model model) {
		if((this.model = model) == null)
			throw new NullPointerException("Le modele recu en parametre est null !");
	}
	
    public void run() {
    	model.save();
    }
    
    public static void saveInNewThread(Model model) {
    	(new ThreadForSave(model)).start();
    }
}
