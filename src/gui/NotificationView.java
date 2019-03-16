package gui;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import gui.style.StylizedJFrame;
import messages.NotificationConstants;
/**
 * [ VUE DES NOTIFICATIONS  / FEEDBACK. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class NotificationView extends StylizedJFrame implements NotificationConstants{
	
	
	/**
	 * [ CONSTRUIT UNE NOTIFICATION. ]
	 * 
	 * @param title				Titre / resume de la notification.
	 * 
	 * @param content			Details de la notification.
	 */
	public NotificationView(String title, String content) {
		super(title);
		if(content==null) throw new NullPointerException("Content null !");
		setLayout(new BorderLayout());
		setSize(300, 150);
		setResizable(false);
		setLocationRelativeTo(null);
		add(new JLabel("<html>" + 
						"<body>" + 
						content +
						"</body>" + 
						"</html>", JLabel.CENTER),
			BorderLayout.CENTER);
		WindowTools.showLogo(this);
	}
	
	
	/**
	 * [ ALERTER L'UTILISATEUR PENDANT UN TEMPS FIXE. ]
	 * 
	 * @param timeOut		Temps d'affichage de la notification.
	 */
	public void alert(int timeOut) {
		if(timeOut<=0) throw new IllegalArgumentException("timeOut nul ou negatif !");
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	setVisible(true);
		    	new Thread() {
		    		 public void run() {
		 				try {
		 					while(!NotificationView.this.isVisible());
							Thread.sleep(timeOut);
						} catch (InterruptedException ie) {
							JOptionPane.showMessageDialog(null, ie.getMessage());
						}
						dispose();
		    		 }
		    	}.start();
		    }
		});
	}
	
	
	/**
	 * [ ALERTER L'UTILISATEUR PENDANT UN TEMPS. ]
	 * 
	 * @see #alert
	 */
	public static void alert(String title, String content, int timeOut) {
		new NotificationView(title, content).alert(timeOut);
	}
	
	
	/**
	 * [ ALERTER PENDANT 3 SECONDES. ]
	 * 
	 * @see #alert
	 */
	public void alert() {
		alert(3000);
	}
	
	
	/**
	 * [ ALERTER PENDANT 3 SECONDES. ]
	 * 
	 * @see #alert
	 */
	public static void alert(String title, String content) {
		new NotificationView(title, content).alert();
	}
}
