package gui;

import javax.swing.JFrame;
import tools.WindowTools;
import wrapper.UserRequests;

public class ThreadForWaitWindow extends Thread{
	private JFrame waitWindow;
	
	private ThreadForWaitWindow(JFrame waitWindow) {
		this.waitWindow = waitWindow;
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
