package gui.general;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import gui.WindowTools;
import gui.alerts.AlertSettings;
import gui.style.StylizedJFrame;

/**
 * [ CLASSE FENETRE D'UNE VUE. ]
 * 
 * Cette classe permet par le biais du constructeur de generer
 * une fenetre contenant une vue.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public abstract class GeneralWindow extends StylizedJFrame{
	private static final long serialVersionUID = 1372173727022340518L;

	/**
	 * [ CONSTRUCTEUR AVEC PARAMETRES. ]
	 */
	public GeneralWindow(String title, JPanel view){
		super(title);
		if(view == null) throw new NullPointerException("view null !");
		
		Context.$F.add(this);
		
		setResizable(false);
		setContentPane(view);
		setSize(new Dimension(view.getWidth(), view.getHeight()));
		setLocationRelativeTo(null);
		
		
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_DELETE || e.getKeyCode()==KeyEvent.VK_ESCAPE 
				|| e.getKeyCode()==KeyEvent.VK_ENTER) {
					dispose();
					/**
					 * RENDRE LE FOCUS A L'EVENEMENT PARENT.
					 */
					renderFocus();
				}
			}
		});
		setFocusable(true);
		requestFocus();
		
		
		/**
		 * RENDRE LE FOCUS A L'EVENEMENT PARENT.
		 */
		addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				/**
				 * RENDRE LE FOCUS A L'EVENEMENT PARENT.
				 */
				renderFocus();
			}
			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
		});
		
		WindowTools.showLogo(this);
		WindowTools.executeWindow(this);
	}
	
	
	/**
	 * [ METHODE POUR RETABLIR LE FOCUS. ]
	 */
	private void renderFocus() {
		Context.$F.poll();
		new Focus();
	}
}
