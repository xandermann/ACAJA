package gui.conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import files.SettingType;

public final class VideoSettingsPanel extends JPanel implements Observer {
	//=======================================================================================================================
	//=======================================================================================================================

	
	private ConversionModel model;
	private JTextField resolution;
	private JTextField bitrate;
	private JTextField fps;

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	public VideoSettingsPanel(ConversionModel model) {
		this.model = model;

		resolution = new JTextField();
		bitrate = new JTextField();
		fps = new JTextField();

		setSize(new Dimension(400, 400));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel codec = new JPanel();
		codec.setLayout(new FlowLayout());

		codec.add(new JLabel("Codec video : "), BorderLayout.WEST);
		String[] format = { ".avi", ".mp4", ".flv" };
		JComboBox box_format = new JComboBox(format);
		box_format.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getCurrentFile() != null) {
					model.modify(SettingType.VIDEO_CODEC, ((JComboBox) e.getSource()).getSelectedItem().toString());
				}
			}
		});
		codec.add(box_format, BorderLayout.EAST);

		JPanel reso = new JPanel();
		reso.setLayout(new FlowLayout());

		reso.add(new JLabel("Resolution : "), BorderLayout.WEST);
		resolution.setPreferredSize(new Dimension(300, 20));
		resolution.setEnabled(false);
		reso.add(resolution, BorderLayout.EAST);

		JPanel br = new JPanel();
		br.setLayout(new FlowLayout());
		br.add(new JLabel("Bitrate (kb/s) : "), BorderLayout.WEST);
		bitrate.setPreferredSize(new Dimension(300, 20));
		bitrate.setEnabled(false);
		br.add(bitrate, BorderLayout.EAST);

		JPanel panef = new JPanel();
		panef.setLayout(new FlowLayout());
		panef.add(new JLabel("Images par seconde (FPS) : "), BorderLayout.WEST);
		fps.setPreferredSize(new Dimension(300, 20));
		fps.setEnabled(false);
		panef.add(fps, BorderLayout.EAST);

		add(codec);
		add(reso);
		add(br);
		add(panef);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	@Override
	public void update(Observable o, Object arg) {
		if(model.getCurrentFile() != null) {
		     HashMap<SettingType, String> settings = model.getCurrentFile().getSettings();
			 resolution.setText(settings.get(SettingType.RESOLUTION));
			 resolution.setEnabled(true); 
			 bitrate.setText(settings.get(SettingType.VIDEO_BITRATE)); 
			 bitrate.setEnabled(true);
			 fps.setText(settings.get(SettingType.FPS));
			 fps.setEnabled(true);
		} else {
			resolution.setText("");
			resolution.setEnabled(false);
			bitrate.setText("");
			bitrate.setEnabled(false);
			fps.setText("");
			fps.setEnabled(false);
		}
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
