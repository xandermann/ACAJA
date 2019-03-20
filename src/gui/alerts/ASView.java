package gui.alerts;
import javax.swing.*;
import gui.style.StylizedJPanel;
import java.awt.*;
import java.awt.event.*;
/**
 * [ CLASSE VUE DE CHOIX DES PARAMETRES DES ALERTES. ]
 *
 * AS = AlertSettings.
 *
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ASView extends StylizedJPanel {
	/**
	 * [ CONSTRUCTEUR VIDE. ]
	 */
	public ASView() {
		super(new BorderLayout());
		setSize(new Dimension(350, 200));
		
		
		//=======================================================================================================
		
		
		JSlider js1 = new JSlider(0,10);
		js1.setValue(AlertManager.SHORT/1000);
		js1.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				AlertManager.SHORT = js1.getValue()*1000;
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		JSlider js2 =  new JSlider(0,10);
		js2.setValue(AlertManager.LONG/1000);
		js2.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				AlertManager.LONG = js2.getValue()*1000;
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		StylizedJPanel _jp1 = new StylizedJPanel(new BorderLayout());
		_jp1.setSize(new Dimension(350, 30));
		_jp1.add(new JLabel("temps des notifications courtes : ", JLabel.CENTER), BorderLayout.NORTH);
		StylizedJPanel __jp1 = new StylizedJPanel(new BorderLayout());
		__jp1.setPreferredSize(new Dimension(290, 20));
		__jp1.add(new JLabel("0", JLabel.CENTER), BorderLayout.WEST);
		__jp1.add(js1, BorderLayout.CENTER);
		__jp1.add(new JLabel("10", JLabel.CENTER), BorderLayout.EAST);
		StylizedJPanel __jp2 = new StylizedJPanel();
		__jp2.setPreferredSize(new Dimension(30, 20));
		StylizedJPanel __jp3 = new StylizedJPanel();
		__jp3.setPreferredSize(new Dimension(30, 20));
		_jp1.add(__jp2, BorderLayout.WEST);
		_jp1.add(__jp1, BorderLayout.CENTER);
		_jp1.add(__jp3, BorderLayout.EAST);
		
		StylizedJPanel _jp2 = new StylizedJPanel(new BorderLayout());
		_jp2.setSize(new Dimension(350, 30));
		_jp2.add(new JLabel("temps des notifications longues : ", JLabel.CENTER), BorderLayout.NORTH);
		StylizedJPanel __jp4 = new StylizedJPanel(new BorderLayout());
		__jp4.setPreferredSize(new Dimension(290, 20));
		__jp4.add(new JLabel("0", JLabel.CENTER), BorderLayout.WEST);
		__jp4.add(js2, BorderLayout.CENTER);
		__jp4.add(new JLabel("10", JLabel.CENTER), BorderLayout.EAST);
		StylizedJPanel __jp5 = new StylizedJPanel();
		__jp5.setPreferredSize(new Dimension(30, 20));
		StylizedJPanel __jp6 = new StylizedJPanel();
		__jp6.setPreferredSize(new Dimension(30, 20));
		_jp2.add(__jp5, BorderLayout.WEST);
		_jp2.add(__jp4, BorderLayout.CENTER);
		_jp2.add(__jp6, BorderLayout.EAST);
		
		
		StylizedJPanel jp2 = new StylizedJPanel(new BorderLayout());
		jp2.add(new JLabel("CHOIX DES TEMPS DE NOTIFICATIONS", JLabel.CENTER), BorderLayout.NORTH);
		jp2.add(_jp1, BorderLayout.CENTER);
		jp2.add(_jp2, BorderLayout.SOUTH);
		
		
		//=======================================================================================================
		
		
		ButtonGroup bg = new ButtonGroup();
		JRadioButton jr1 = new JRadioButton("oui");
		jr1.setSelected(AlertManager.INTERRUPTOR);
		jr1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlertManager.INTERRUPTOR = true;
				js1.setEnabled(true);
				js2.setEnabled(true);
				_jp1.setEnabled(true);
				_jp2.setEnabled(true);
				jp2.setEnabled(true);
			}
		});
		
		JRadioButton jr2 = new JRadioButton("non");
		jr2.setSelected(!AlertManager.INTERRUPTOR);
		jr2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlertManager.INTERRUPTOR = false;
				js1.setEnabled(false);
				js2.setEnabled(false);
				_jp1.setEnabled(false);
				_jp2.setEnabled(false);
				jp2.setEnabled(false);
			}
		});
		
		js1.setEnabled(AlertManager.INTERRUPTOR);
		js2.setEnabled(AlertManager.INTERRUPTOR);
		_jp1.setEnabled(AlertManager.INTERRUPTOR);
		_jp2.setEnabled(AlertManager.INTERRUPTOR);
		jp2.setEnabled(AlertManager.INTERRUPTOR);
		
		bg.add(jr1);
		bg.add(jr2);
		StylizedJPanel jp1 = new StylizedJPanel(new BorderLayout());
		StylizedJPanel _jp4 = new StylizedJPanel();
		_jp4.setSize(new Dimension(350, 20));
		jp1.add(_jp4, BorderLayout.NORTH);
		jp1.add(new JLabel("afficher les notifications : ", JLabel.CENTER), BorderLayout.CENTER);
		StylizedJPanel _jp3 = new StylizedJPanel();
		_jp3.add(jr1);
		_jp3.add(jr2);
		jp1.add(_jp3, BorderLayout.SOUTH);


		//=======================================================================================================
	
		
		StylizedJPanel _jp5 = new StylizedJPanel(new BorderLayout());
		StylizedJPanel _jp6 = new StylizedJPanel();
		_jp6.setSize(new Dimension(350, 30));
		_jp5.add(_jp6, BorderLayout.NORTH);
		_jp5.add(new JLabel("CHOIX DES PARAMETRES DES NOTIFICATIONS", JLabel.CENTER), BorderLayout.CENTER);
		add(_jp5, BorderLayout.NORTH);
		StylizedJPanel jp3 = new StylizedJPanel(new BorderLayout());
		jp3.add(jp1, BorderLayout.NORTH);
		jp3.add(jp2, BorderLayout.CENTER);
		StylizedJPanel _jp7 = new StylizedJPanel();
		_jp7.setSize(new Dimension(350, 30));
		jp3.add(_jp4, BorderLayout.SOUTH);
		add(jp3, BorderLayout.CENTER);
	}
}
