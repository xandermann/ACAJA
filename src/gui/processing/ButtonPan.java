package gui.processing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ButtonPan extends JPanel {
	
	private JToggleButton flouButton;
	private JToggleButton rectangle;
	private ProcessingModel model;
	
	/**
	 * constructeur du panel de bouton central
	 * @param m
	 */
	public ButtonPan(ProcessingModel m) {
		this.model = m;
		this.setLayout(new GridLayout(3, 2, 1, 1));
		rectangle = new JToggleButton(new ImageIcon(resources.ResourceConstants.BUTTON_RECT));
		flouButton = new JToggleButton(new ImageIcon(resources.ResourceConstants.BLURRED));
		JButton pivoteD1 = new JButton(new ImageIcon(resources.ResourceConstants.LEFT_ARROW));
		JButton pivoteG1 = new JButton(new ImageIcon(resources.ResourceConstants.RIGHT_ARROW));
		
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
		
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.suppLastForm();
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
		this.add(pivoteD1);
		this.add(pivoteG1);
		this.add(undo);
		this.repaint();
	}
	
	public void cropIsSelected() {
		model.setMessage("Fonction de Crop activée !");
		flouButton.setSelected(false);
		model.setfUp(false);
		model.setcropUp(false);
		if(rectangle.isSelected())
			model.setcropUp(true);
	}
	
	public void flouIsSelected() {
		model.setMessage("Fonction de Floutage activée !");
		rectangle.setSelected(false);
		model.setcropUp(false);
		model.setfUp(false);
		if(flouButton.isSelected())
			model.setfUp(true);
	}
	
	
}
