package gui.processing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import exceptions.UnfindableResourceException;
import gui.JFileChooserManager;
import gui.style.*;

public class ProcessingPan extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4600169663295346054L;

	public ProcessingPan(ProcessingModel m) {
		this.setBackground(Color.GRAY);
		LibraryView pb = new LibraryView(m);
		this.setSize(1000,625);
		System.out.println("width "+getWidth() +"height"+getHeight());
		pb.setPreferredSize(new Dimension((int)(getWidth()*(0.2)), (int)(getHeight()*(0.9))));
		ButtonPan pm = new ButtonPan();
		StylizedJPanel processPan = new StylizedJPanel();
		processPan.setLayout(new BoxLayout(processPan, BoxLayout.PAGE_AXIS));
		processPan.setBackground(Color.GRAY);
		processPan.add(pm);
		StylizedJPanel processPanButton = new StylizedJPanel(new BorderLayout());
		processPan.setPreferredSize(new Dimension((int)(getWidth()*(0.1)), (int)(getHeight()*(0.4))));
		StylizedJPanel processPanSpace = new StylizedJPanel();
		processPanSpace.setPreferredSize(new Dimension((int)(getWidth()*(0.1)), (int)(getHeight()*(0.05))));
		processPanSpace.setBackground(Color.GRAY);
		processPan.add(processPanSpace);
		processPanButton.setPreferredSize(new Dimension(processPan.getWidth(), processPan.getHeight()/3));
		StylizedJButton buttonProcess = new StylizedJButton("<html> <head><style> p{ text-align:center }</style></head><body><p> Démarrer le <br> traitement </p></body></html>");
		buttonProcess.setHorizontalAlignment(SwingConstants.CENTER);
		processPanButton.add(buttonProcess);
		processPan.add(processPanButton);
		PicturePan pi = new PicturePan(m);
		pi.setPreferredSize(new Dimension((int)(getWidth()*(0.6)), (int)(getHeight()*(0.9))));
		pi.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("COORD : X"+e.getX() + "     Y"+e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		buttonProcess.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						m.setDestinationFolder(JFileChooserManager.chooseDirectory());
						m.getCurrentFile().setDestinationPath(m.getDestinationFolder());
						m.getCurrentFile().setDestinationName("Traitement"+System.currentTimeMillis());
						m.getCurrentFile().setFileExtension(m.getCurrentFile().getSourceFileExtension());
						m.save();
						System.out.println("fait");
					} catch (UnfindableResourceException ure) {
						// TODO Auto-generated catch block
						ure.printStackTrace();
					}
				}
				
			
			
		});
		this.add(pb);
		this.add(processPan);
		this.add(pi);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
