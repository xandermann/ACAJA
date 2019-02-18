package gui.conversion;

import java.awt.*;

import java.util.*;

import javax.swing.*;

import files.*;

public final class SummaryView extends JPanel implements Observer {
	//=======================================================================================================================
	//=======================================================================================================================

	
	private ConversionModel model;
	private JPanel j, j1, j2, j3;
	private JLabel name, videoSummary, soundSummary, duration;

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	public SummaryView(ConversionModel model) {
		this.model = model;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		j = new JPanel();
		j.setLayout(new FlowLayout());
		j.add(new JLabel("Fichier selectionne : "), BorderLayout.EAST);
		name = new JLabel("NA");
		j.add(name, BorderLayout.WEST);
		
		j1 = new JPanel();
		j1.setLayout(new FlowLayout());
		j1.add(new JLabel("Codec video actuel : "), BorderLayout.EAST);
		videoSummary = new JLabel("NA");
		j1.add(videoSummary, BorderLayout.WEST);
		
		j2 = new JPanel();
		j2.setLayout(new FlowLayout());
		j2.add(new JLabel("Codec audio actuel : "), BorderLayout.EAST);
		soundSummary = new JLabel("NA");
		j2.add(soundSummary, BorderLayout.WEST);
		
		j3 = new JPanel();
		j3.setLayout(new FlowLayout());
		j3.add(new JLabel("Duree : "), BorderLayout.EAST);
		duration = new JLabel("NA");
		j3.add(duration,BorderLayout.WEST);

		add(j);
		add(j1);
		add(j2);
		add(j3);
		setSize(new Dimension(400, 100));
	}

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	@Override
	public void update(Observable o, Object arg) {
		if (model.getCurrentFile() != null) {
			name.setText(model.getCurrentFile().getName());
			videoSummary.setText(model.getCurrentFile().getSettings().get(SettingType.VIDEO_CODEC));
			soundSummary.setText(model.getCurrentFile().getSettings().get(SettingType.AUDIO_CODEC));
			duration.setText(this.model.getCurrentFile().getDuration());
		} else {
			name.setText("NA");
			videoSummary.setText("NA");
			soundSummary.setText("NA");
		}
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
