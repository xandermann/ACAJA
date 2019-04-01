package gui.alerts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.WindowTools;
import gui.general.Context;
import gui.style.StyleTheme;
import gui.style.StylizedJFrame;

public final class AlertWindow extends StylizedJFrame implements ATConstants{
	/**
	 * [ CONSTRUIT UNE FENETRE D'ALERTE. ]
	 * 
	 * @param title				Titre / resume de la notification.
	 * @param content			Details de la notification.
	 */
	public AlertWindow(String title, String content) {
		super(title);
		
		
		if(!title.equals(INFO) && !title.equals(SUCCESS) && !title.equals(FAILURE))
			throw new IllegalArgumentException("Type de notification inconnu !");
		
		if(content==null) throw new NullPointerException("Content null !");
		
		
		setLayout(new BorderLayout());
		setSize(300, 120);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		
		Color theme = title.equals(FAILURE) ? Color.RED : 
				   	  (title.equals(SUCCESS) ? new Color(0,128,0) : StyleTheme.BACKGROUND_COLOR_SECONDARY);
		
		
		setContentPane(new JPanel() {
			public void paintComponent(Graphics g) {
				g.setColor(theme);
				g.drawRect(0, 0, 299, 119); 
				g.drawRect(1, 1, 297, 117); 
				g.drawRect(2, 2, 295, 115); 
			}	
		});
		
		
		JLabel notification = new JLabel(
				"<html>" + 
						"<head>" +
							"<style>"+
								"#content { "+
									"text-align: center; "+
								"}"+
							"</style>" +
						"</head>" +
						"<body>" + 
							"<p id=content>" +
								"<br>" +
								"<br>" +
								"<br>" +
								content +
							"</p>" +
						"</body>" + 
					"</html>", JLabel.CENTER);
		notification.setForeground(theme);
		add(notification, BorderLayout.CENTER);
		
		
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_DELETE || e.getKeyCode()==KeyEvent.VK_ESCAPE 
				|| e.getKeyCode()==KeyEvent.VK_ENTER) {
					dispose();
					/**
					 * RENDRE LE FOCUS AU COMPONENT PARENT.
					 */
					if(Context.$C(0) != null) 
						Context.$C(0).requestFocus();
					else {
						if(Context.$W != null) 
							Context.$W.requestFocus();
					}
				}
			}
		});
		setFocusable(true);
		requestFocus();
		
		
		WindowTools.showLogo(this);
	}
}
