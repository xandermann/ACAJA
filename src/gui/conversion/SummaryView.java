package gui.conversion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import files.SettingsFile;
import files.Setting;

public final class SummaryView extends JPanel implements Observer {
	// =======================================================================================================================
	// =======================================================================================================================

	private ConversionModel model;
	private JPanel j, j1, j2, j3;
	private JLabel name, videoSummary, soundSummary, duration;

	// =======================================================================================================================
	// =======================================================================================================================

	public SummaryView(ConversionModel p_model) {
		this.model = p_model;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel nom = new JLabel("Fichier selectionné : ");
		JLabel video = new JLabel("Codec video actuel : ");
		JLabel son = new JLabel("Codec video actuel : ");
		JLabel duree = new JLabel("Durée : ");

		j = new JPanel();
		j.setLayout(new FlowLayout());
		j.add(nom, BorderLayout.EAST);

		j1 = new JPanel();
		j1.setLayout(new FlowLayout());
		j1.add(video, BorderLayout.EAST);

		j2 = new JPanel();
		j2.setLayout(new FlowLayout());
		j2.add(son, BorderLayout.EAST);

		j3 = new JPanel();
		j3.setLayout(new FlowLayout());
		j3.add(duree, BorderLayout.EAST);
		name = new JLabel("NA");
		j.add(name, BorderLayout.WEST);
		videoSummary = new JLabel("NA");
		j1.add(videoSummary, BorderLayout.WEST);
		soundSummary = new JLabel("NA");
		j2.add(soundSummary, BorderLayout.WEST);
		// duration = new JLabel("NA");
		// j2.add(duration,BorderLayout.WEST);

		add(j);
		add(j1);
		add(j2);
		add(j3);
		setSize(new Dimension(400, 100));
	}

	// =======================================================================================================================
	// =======================================================================================================================

	@Override
	public void update(Observable o, Object arg) {
		if (this.model.getCurrentFile() != null) {
			name.setText(model.getCurrentFile().getSourceFilename());
			videoSummary
					.setText((String) model.getCurrentFile().getSettings().get(Setting.VIDEO_CODEC));
			soundSummary
					.setText((String) model.getCurrentFile().getSettings().get(Setting.AUDIO_CODEC));
			// duration.setText((String)
			// this.model.getCurrentFile().getSettings().get("duree"));
		} else {
			name.setText("NA");
			videoSummary.setText("NA");
			soundSummary.setText("NA");
		}
	}

	// =======================================================================================================================
	// =======================================================================================================================
}
