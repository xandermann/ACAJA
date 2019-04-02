package gui.start;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import gui.WindowTools;
import gui.alerts.Alert;
import gui.conversion.ConversionWindow;
import gui.general.Context;
import gui.processing.ProcessingWindow;
import gui.style.StyleTheme;
import gui.style.StylizedJButton;
import gui.style.StylizedJPanel;

/**
 * [ FENETRE CHOIX DU MODE D'UTILISATION DU LOGICIEL. ]
 */
public final class ChoiceModeWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6014955014572352919L;

	/**
	 * [ CONSTRUIT UNE FENETRE CHOIX DU MODE D'UTILISATION DU LOGICIEL. ]
	 */
	public ChoiceModeWindow() {
		super("Acaja");

		Context.$W = this;

		StylizedJButton convertbutton = new StylizedJButton("Conversion");
		convertbutton.setPreferredSize(new Dimension(100, 40));
		convertbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConversionWindow();
				Alert.longAlert(Alert.INFO, "Ceci est la fenetre pour realiser<br>des conversions.");
				dispose();
			}
		});

		StylizedJButton processingButton = new StylizedJButton("Traitement");
		processingButton.setPreferredSize(new Dimension(100, 40));
		processingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ProcessingWindow();
				Alert.longAlert(Alert.INFO, "Ceci est la fenetre pour realiser<br>des traitements.");
				dispose();
			}
		});

		setResizable(false);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(400, 400));
		setLocationRelativeTo(null);

		WindowTools.showLogo(this);

		StylizedJPanel centerPanel = new StylizedJPanel(new GridLayout(2, 1, 0, 50));
		centerPanel.add(convertbutton);
		centerPanel.add(processingButton);

		StylizedJPanel northPanel = new StylizedJPanel();
		northPanel.setPreferredSize(new Dimension(400, 135));
		StylizedJPanel southPanel = new StylizedJPanel();
		southPanel.setPreferredSize(new Dimension(400, 135));
		StylizedJPanel eastPanel = new StylizedJPanel();
		eastPanel.setPreferredSize(new Dimension(100, 400));
		StylizedJPanel westPanel = new StylizedJPanel();
		westPanel.setPreferredSize(new Dimension(100, 400));

		add(centerPanel, BorderLayout.CENTER);
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(eastPanel, BorderLayout.EAST);
		add(westPanel, BorderLayout.WEST);

		centerPanel.setBackground(StyleTheme.BACKGROUND_COLOR);
		northPanel.setBackground(StyleTheme.BACKGROUND_COLOR);
		southPanel.setBackground(StyleTheme.BACKGROUND_COLOR);
		eastPanel.setBackground(StyleTheme.BACKGROUND_COLOR);
		westPanel.setBackground(StyleTheme.BACKGROUND_COLOR);

		WindowTools.executeWindow(this);

		Alert.longAlert(Alert.INFO, "Bienvenue dans ACAJA.<br>Ici vous pouvez choisir un mode de travail.");
	}
}
