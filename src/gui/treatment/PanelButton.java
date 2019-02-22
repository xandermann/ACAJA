package gui.treatment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import javafx.scene.control.skin.ToolBarSkin;


public class PanelButton extends JPanel{
	
	public PanelButton() {
		this.setLayout(new GridLayout(3,2,1,1));
		
		JToggleButton rectangle = new JToggleButton(new ImageIcon(resources.ResourceConstants.rectbutton));
		JToggleButton pivoteD = new JToggleButton();
		JToggleButton pivoteG = new JToggleButton();
		JToggleButton rectangle1 = new JToggleButton();
		JToggleButton pivoteD1 = new JToggleButton();
		JToggleButton pivoteG1 = new JToggleButton();
		
		rectangle.setPreferredSize(new Dimension(35,35));
		pivoteD.setPreferredSize(new Dimension(35,35));
		pivoteG.setPreferredSize(new Dimension(35,35));
		rectangle1.setPreferredSize(new Dimension(35,35));
		pivoteD1.setPreferredSize(new Dimension(35,35));
		pivoteG1.setPreferredSize(new Dimension(35,35));
		
		this.add(rectangle);
		this.add(pivoteD);
		this.add(pivoteG);
		this.add(rectangle1);
		this.add(pivoteD1);
		this.add(pivoteG1);
		this.repaint();
		
	}
}
