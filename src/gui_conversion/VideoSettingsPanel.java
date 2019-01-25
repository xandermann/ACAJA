package gui_conversion;

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

import files.SettingsFile;

public final class VideoSettingsPanel extends JPanel implements Observer{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	private ConversionModel model;
	private JTextField includeResolution;
	private JTextField includeBitrate;
	private JTextField includeFps;
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	public VideoSettingsPanel(ConversionModel m) {
		this.model = m;
		
		includeResolution = new JTextField();
		includeBitrate = new JTextField();
		includeFps = new JTextField();
		
		this.setSize(new Dimension(400, 400));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel Codec = new JPanel();
		Codec.setLayout(new FlowLayout());

		/*
		 * NOTE : pour la premiere iteration nous avons choisi d'implementer simplement
		 * un FORMAT de sortie, par la suite, l'utilisateur choisira les codecs
		 * audio/videos voulu et le conteneur(format) sera determine automatiquement par
		 * le logiciel pour creer un fichier optimal et lisible
		 */
		Codec.add(new JLabel("Format sortie : "), BorderLayout.WEST);
		String[] format = { ".avi", ".mp4", ".flv" };
		JComboBox box_format = new JComboBox(format);
		
		box_format.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) {
					model.modify
					(
						SettingsFile.VIDEO_CODEC, 
						((JComboBox)e.getSource()).getSelectedItem().toString()
					);
				}
			}
		});

		Codec.add(box_format, BorderLayout.EAST);
		
		JPanel reso = new JPanel();
		reso.setLayout(new FlowLayout());
		
		reso.add(new JLabel("Resolution : "), BorderLayout.WEST);
		includeResolution.setPreferredSize(new Dimension(300, 20));
		includeResolution.setEnabled(false);
		reso.add(includeResolution, BorderLayout.EAST);

		JPanel br = new JPanel();
		br.setLayout(new FlowLayout());
		br.add(new JLabel("Bitrate (kb/s) : "), BorderLayout.WEST);
		includeBitrate.setPreferredSize(new Dimension(300, 20));
		includeBitrate.setEnabled(false);
		br.add(includeBitrate, BorderLayout.EAST);

		JPanel panef = new JPanel();
		panef.setLayout(new FlowLayout());
		panef.add(new JLabel("Images par seconde (FPS) : "), BorderLayout.WEST);
		includeFps.setPreferredSize(new Dimension(300, 20));
		includeFps.setEnabled(false);
		panef.add(includeFps, BorderLayout.EAST);

		JPanel panest = new JPanel();
		panest.setLayout(new FlowLayout());
		panest.add(new JLabel("Fichiers sous-titres : "), BorderLayout.WEST);

		JButton parcourir = new JButton("Parcourir");
		parcourir.setEnabled(false);
		parcourir.setPreferredSize(new Dimension(100, 20));
		parcourir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ATTENTION
				try {
					File f = DataChoose.FileChoose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				// Ce fichier sera le fichier des sous-titres, pas un SettingsFile !
			}
		});
		panest.add(parcourir, BorderLayout.EAST);

		this.add(Codec);
		this.add(reso);
		this.add(br);
		this.add(panef);
		this.add(panest);
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	@Override
	public void update(Observable o, Object arg) { 
		if(model.getCurrentFile() != null) {
			HashMap<Integer, Object> settings = model.getCurrentFile().getSettings();
			/*includeResolution.setText( "" +
					( (Integer[]) settings.get(SettingsFile.VIDEO_RESOLUTION) )[0]
					+"x"
					+ ( (Integer[]) settings.get(SettingsFile.VIDEO_RESOLUTION) )[1]);	
			includeResolution.setEnabled(true);
			includeBitrate.setText( "" +
					(Integer) settings.get(SettingsFile.VIDEO_BITRATE));
			includeBitrate.setEnabled(true);
			includeFps.setText( "" +
					(Double) settings.get(SettingsFile.FPS));
			includeFps.setEnabled(true);*/
		}else{
			includeResolution.setText("");
			includeResolution.setEnabled(false);
			includeBitrate.setText("");
			includeBitrate.setEnabled(false);
			includeFps.setText("");
			includeFps.setEnabled(false);
		}
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
