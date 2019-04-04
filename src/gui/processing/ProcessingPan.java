package gui.processing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import gui.alerts.Alert;
import gui.general.Context;
import gui.style.*;

public class ProcessingPan extends JPanel {
	private static final long serialVersionUID = 4600169663295346054L;

	public ProcessingPan(JTextField processPanSpace) {
		this.setBackground(Color.GRAY);
		LibraryView pb = new LibraryView();
		Context.$M.addObserver(pb);
		this.setSize(1000,625);
		System.out.println("width "+getWidth() +"height"+getHeight());
		pb.setPreferredSize(new Dimension((int)(getWidth()*(0.2)), (int)(getHeight()*(0.9))));
		ButtonPan pm = new ButtonPan();
		StylizedJPanel processPan = new StylizedJPanel();
		processPan.setLayout(new BoxLayout(processPan, BoxLayout.PAGE_AXIS));
		processPan.setBackground(Color.GRAY);
		processPan.add(pm);
		StylizedJPanel processPanButton = new StylizedJPanel(new BorderLayout());
		processPan.setPreferredSize(new Dimension((int)(getWidth()*(0.1)), (int)(getHeight()*(0.5))));
		//StylizedJPanel processPanSpace = new StylizedJPanel();
		
		processPanSpace.setPreferredSize(new Dimension((int)(getWidth()*(0.1)), (int)(getHeight()*(0.01))));
		//processPanSpace.setBackground(Color.GRAY);
		processPan.add(processPanSpace);
		processPanButton.setPreferredSize(new Dimension(processPan.getWidth(), processPan.getHeight()/3));
		
		StylizedJButton buttonProcess = 
				new StylizedJButton("<html> "
						            + "<head><style> p{ text-align:center }</style></head>"
									+ "<body><p> Demarrer le <br> traitement </p></body>"
									+ "</html>");
		buttonProcess.setHorizontalAlignment(SwingConstants.CENTER);
		
		processPanButton.add(buttonProcess);
		processPan.add(processPanButton);
		
		
		
		PicturePan pi = new PicturePan();
		pi.setPreferredSize(new Dimension((int)(getWidth()*(0.6)), (int)(getHeight()*(0.9))));
		buttonProcess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((ProcessingModel)Context.$M).getCurrentFile() != null){
					try {
						String text = processPanSpace.getText();
						if(processPanSpace.getText().isEmpty()) 
								text = "Traitement"+System.currentTimeMillis();
						((ProcessingModel)Context.$M).getCurrentFile().setDestinationName(text);
						((ProcessingModel)Context.$M).save();
									System.out.println("fait");
				    } catch (Exception ure) {
						Alert.longAlert(Alert.FAILURE, "Aucun repertoire de sortie selectione !");

					}
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
