package gui.alerts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import gui.WindowTools;
import gui.style.StyleTheme;
import gui.style.StylizedJFrame;

public final class AlertWindow extends StylizedJFrame implements ATConstants{
	private static final long serialVersionUID = 2688386215518460565L;

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
			private static final long serialVersionUID = -6325761192059369090L;

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
		
		
		WindowTools.showLogo(this);
	}
}
