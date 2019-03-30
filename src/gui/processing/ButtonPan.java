package gui.processing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import gui.general.Context;

public class ButtonPan extends JPanel {
	
	private JToggleButton flouButton;
	private JToggleButton rectangle;
	private JToggleButton pivoteG1;
	private JToggleButton pivoteD1;
	
	/**
	 * constructeur du panel de bouton central
	 */
	public ButtonPan() {
		this.setLayout(new GridLayout(3, 2, 1, 1));
		rectangle = new JToggleButton(new ImageIcon(resources.ResourceConstants.BUTTON_RECT));
		flouButton = new JToggleButton(new ImageIcon(resources.ResourceConstants.BLURRED));
		pivoteG1 = new JToggleButton(new ImageIcon(resources.ResourceConstants.LEFT_ARROW));
		pivoteD1 = new JToggleButton(new ImageIcon(resources.ResourceConstants.RIGHT_ARROW));
		
		JButton undo = new JButton(new ImageIcon(resources.ResourceConstants.LEFT_ARROW));
		
		rectangle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cropIsSelected();
			}
		});
		
		flouButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				flouIsSelected();
			}
		});
		
		pivoteG1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rotateLeftActivated();
			}
		});
		
		pivoteD1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rotateRightActivated();
			}
			
		});
		
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((ProcessingModel)Context.$M).suppLastForm();
			}
		});

		rectangle.setPreferredSize(new Dimension(35, 35));
		flouButton.setPreferredSize(new Dimension(35, 35));
		pivoteD1.setPreferredSize(new Dimension(35, 35));
		pivoteG1.setPreferredSize(new Dimension(35, 35));
		undo.setPreferredSize(new Dimension(35, 35));
/*
		pivoteD1.setEnabled(false);
		pivoteG1.setEnabled(false);
		*/
		this.add(rectangle);
		this.add(flouButton);
		this.add(pivoteG1);
		this.add(pivoteD1);
		this.add(undo);
		this.repaint();
	}
	
	public void rotateLeftActivated() {
		((ProcessingModel)Context.$M).setMessage("Pivoter a gauche");
		if(!pivoteG1.isSelected()) {
			// Annuler le pivot de la video
			pivoteD1.setEnabled(true);
			((ProcessingModel)Context.$M).setRotateLeft(false);
		} else {
			// Activer les actions pour faire pivoter la video
			pivoteD1.setEnabled(false);
			((ProcessingModel)Context.$M).setRotateLeft(true);
		}
		System.out.println("Verification : rotateLeft("+((ProcessingModel)Context.$M).isRotateLeft()+") __ rotateRight("+((ProcessingModel)Context.$M).isRotateRight()+")");
	}
	
	public void rotateRightActivated() {
		((ProcessingModel)Context.$M).setMessage("Pivoter a droite");
		if(!pivoteD1.isSelected()) {
			// Annuler le pivot de la video
			pivoteG1.setEnabled(true);
			((ProcessingModel)Context.$M).setRotateRight(false);
		} else {
			// Activer les actions pour faire pivoter la video
			pivoteG1.setEnabled(false);
			((ProcessingModel)Context.$M).setRotateRight(true);
		}
		System.out.println("Verification : rotateLeft("+((ProcessingModel)Context.$M).isRotateLeft()+") __ rotateRight("+((ProcessingModel)Context.$M).isRotateRight()+")");
	}
	public void cropIsSelected() {
		((ProcessingModel)Context.$M).setMessage("Fonction de Crop activée !");
		flouButton.setSelected(false);
		((ProcessingModel)Context.$M).setfUp(false);
		((ProcessingModel)Context.$M).setcropUp(false);
		if(rectangle.isSelected())
			((ProcessingModel)Context.$M).setcropUp(true);
	}
	
	public void flouIsSelected() {
		((ProcessingModel)Context.$M).setMessage("Fonction de Floutage activée !");
		rectangle.setSelected(false);
		((ProcessingModel)Context.$M).setcropUp(false);
		((ProcessingModel)Context.$M).setfUp(false);
		if(flouButton.isSelected())
			((ProcessingModel)Context.$M).setfUp(true);
	}
	
	
}
