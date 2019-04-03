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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1294342658122646334L;
	private JToggleButton flouButton;
	private JToggleButton rectangle;
	private JToggleButton rotateL1;
	private JToggleButton rotateR1;
	private JToggleButton rotateInvertedL1;
	private JToggleButton rotateInvertedR1;
	private JToggleButton rotate180;
	
	
	/**
	 * constructeur du panel de bouton central
	 */
	public ButtonPan() {
		this.setLayout(new GridLayout(4, 2, 1, 1));
		rectangle = new JToggleButton(new ImageIcon(resources.ResourceConstants.BUTTON_RECT));
		flouButton = new JToggleButton(new ImageIcon(resources.ResourceConstants.BLURRED));
		rotateL1 = new JToggleButton(new ImageIcon(resources.ResourceConstants.LEFT_ARROW));
		rotateR1 = new JToggleButton(new ImageIcon(resources.ResourceConstants.RIGHT_ARROW));
		rotateInvertedL1 = new JToggleButton(new ImageIcon("img/fliptournG.png"));
		rotateInvertedR1 = new JToggleButton(new ImageIcon("img/fliptournD.png"));
		rotate180 = new JToggleButton(new ImageIcon("img/180.png"));
	
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
		
		rotateL1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rotateLeftActivated();
			}
		});
		
		rotateR1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rotateRightActivated();
			}
			
		});
		rotateInvertedL1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateLeft180Activated();
				
			}
		});
		rotateInvertedR1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				rotateRight180Activated();
				
			}
		});
		rotate180.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rotate180Activated();
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
		rotateR1.setPreferredSize(new Dimension(35, 35));
		rotateL1.setPreferredSize(new Dimension(35, 35));
		rotateInvertedL1.setPreferredSize(new Dimension(35,35));
		rotateInvertedR1.setPreferredSize(new Dimension(35,35));
		rotate180.setPreferredSize(new Dimension(35,35));
		undo.setPreferredSize(new Dimension(35, 35));

		this.add(rectangle);
		this.add(flouButton);
		this.add(rotateL1);
		this.add(rotateR1);
		this.add(rotateInvertedL1);
		this.add(rotateInvertedR1);
		this.add(rotate180);
		this.add(undo);
		this.repaint();
	}
	
	public void rotate180Activated() {
		if(((ProcessingModel)Context.$M).getCurrentFile() != null) {
			if(!rotate180.isSelected()) {
				// Annuler le pivot de la video
				rotateR1.setEnabled(true);
				rotateInvertedR1.setEnabled(true);
				rotateL1.setEnabled(true);
				rotateInvertedL1.setEnabled(true);
				((ProcessingModel)Context.$M).setRotate180(false);
			} else {
				// Activer les actions pour faire pivoter la video
				rotateR1.setEnabled(false);
				rotateInvertedR1.setEnabled(false);
				rotateL1.setEnabled(false);
				rotateInvertedL1.setEnabled(false);
				((ProcessingModel)Context.$M).setRotate180(true);
			}
			}else rotate180.setSelected(false);
		((ProcessingModel)Context.$M).sendChanges();
	}
	public void rotateLeft180Activated() {
		if(((ProcessingModel)Context.$M).getCurrentFile() != null) {
			if(!rotateInvertedL1.isSelected()) {
				// Annuler le pivot de la video
				rotateR1.setEnabled(true);
				rotateInvertedR1.setEnabled(true);
				rotateL1.setEnabled(true);
				rotate180.setEnabled(true);
				((ProcessingModel)Context.$M).setRotate180Left(false);
			} else {
				// Activer les actions pour faire pivoter la video
				rotateR1.setEnabled(false);
				rotateInvertedR1.setEnabled(false);
				rotateL1.setEnabled(false);
				rotate180.setEnabled(false);
				((ProcessingModel)Context.$M).setRotate180Left(true);
			}
			}else rotateInvertedL1.setSelected(false);
		((ProcessingModel)Context.$M).sendChanges();
	}
	
	public void rotateRight180Activated() {
		if(((ProcessingModel)Context.$M).getCurrentFile() != null) {
			if(!rotateInvertedR1.isSelected()) {
				// Annuler le pivot de la video
				rotateL1.setEnabled(true);
				rotateR1.setEnabled(true);
				rotateInvertedL1.setEnabled(true);
				rotate180.setEnabled(true);
				((ProcessingModel)Context.$M).setRotate180Right(false);
			} else {
				// Activer les actions pour faire pivoter la video
				rotateL1.setEnabled(false);
				rotateR1.setEnabled(false);
				rotateInvertedL1.setEnabled(false);
				rotate180.setEnabled(false);
				((ProcessingModel)Context.$M).setRotate180Right(true);
			}
		} else rotateInvertedR1.setSelected(false);
		((ProcessingModel)Context.$M).sendChanges();
	}
	public void rotateLeftActivated() {
		if(((ProcessingModel)Context.$M).getCurrentFile() != null) {
		if(!rotateL1.isSelected()) {
			// Annuler le pivot de la video
			rotateR1.setEnabled(true);
			rotateInvertedL1.setEnabled(true);
			rotateInvertedR1.setEnabled(true);
			rotate180.setEnabled(true);
			((ProcessingModel)Context.$M).setRotateLeft(false);
		} else {
			// Activer les actions pour faire pivoter la video
			rotateR1.setEnabled(false);
			rotateInvertedL1.setEnabled(false);
			rotateInvertedR1.setEnabled(false);
			rotate180.setEnabled(false);
			((ProcessingModel)Context.$M).setRotateLeft(true);
		}
		}else rotateL1.setSelected(false);
		((ProcessingModel)Context.$M).sendChanges();
	}
	
	public void rotateRightActivated() {
		if(((ProcessingModel)Context.$M).getCurrentFile() != null) {
			if(!rotateR1.isSelected()) {
				// Annuler le pivot de la video
				rotateL1.setEnabled(true);
				rotateInvertedL1.setEnabled(true);
				rotateInvertedR1.setEnabled(true);
				rotate180.setEnabled(true);
				((ProcessingModel)Context.$M).setRotateRight(false);
			} else {
				// Activer les actions pour faire pivoter la video
				rotateL1.setEnabled(false);
				rotateInvertedL1.setEnabled(false);
				rotateInvertedR1.setEnabled(false);
				rotate180.setEnabled(false);
				((ProcessingModel)Context.$M).setRotateRight(true);
			}
		} else rotateR1.setSelected(false);
		((ProcessingModel)Context.$M).sendChanges();
	}
	
	public void cropIsSelected() {
		flouButton.setSelected(false);
		((ProcessingModel)Context.$M).setfUp(false);
		((ProcessingModel)Context.$M).setcropUp(false);
		if(rectangle.isSelected())
			((ProcessingModel)Context.$M).setcropUp(true);
	}
	
	public void flouIsSelected() {
		rectangle.setSelected(false);
		((ProcessingModel)Context.$M).setcropUp(false);
		((ProcessingModel)Context.$M).setfUp(false);
		if(flouButton.isSelected())
			((ProcessingModel)Context.$M).setfUp(true);
	}
	
	
}
