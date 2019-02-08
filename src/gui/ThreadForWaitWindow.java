package gui;

import javax.swing.JFrame;
import tools.WindowTools;
import wrapper.UserRequests;

public class ThreadForWaitWindow extends Thread{
	private JFrame waitWindow;
	
	/**
	 * [ CONSTRUCTEUR INTERNE - ENCAPSULE ]
	 * 
	 * @param waitWindow	La fenetre d'attente. 
	 */
	private ThreadForWaitWindow(JFrame waitWindow) {
		if((this.waitWindow = waitWindow) == null)
			throw new NullPointerException("Le fenetre d'attente recue en parametre est null !");
	}
	
    public void run() {
    	WindowTools.executeWindow(waitWindow);
		while(UserRequests.workIsOnGoing());
		waitWindow.dispose();
    }
    
    public static void waitInNewThread(JFrame waitWindow) {
    	(new ThreadForWaitWindow(waitWindow)).start();
    }
}
