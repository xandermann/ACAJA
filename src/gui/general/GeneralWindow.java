package gui.general;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import gui.WindowTools;
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
	/**
	 * [ CONSTRUCTEUR AVEC PARAMETRES. ]
	 */
	public GeneralWindow(String title, JPanel view){
		super(title);
		if(view == null) throw new NullPointerException("view null !");
		
		/**
		 * THIS DETIENT LE FOCUS.
		 */
		Context.$C(0, this);
		
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
					if(Context.$C(0) != null) 
						Context.$C(0, null);
					if(Context.$W != null) 
						Context.$W.requestFocus();
				}
			}
		});
		setFocusable(true);
		requestFocus();
		
		
		WindowTools.showLogo(this);
		WindowTools.executeWindow(this);
	}
}
