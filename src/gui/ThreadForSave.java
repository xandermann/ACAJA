package gui;
public class ThreadForSave extends Thread{
	private Model model;
	
	private ThreadForSave(Model model) {
		this.model = model;
	}
	
    public void run() {
    	model.save();
    }
    
    public static void saveInNewThread(Model model) {
    	(new ThreadForSave(model)).start();
    }
}
