package gui_conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import files.SettingsFile;

public class VideoSettingsPanel extends JPanel {

	private ConversionModel model;

	public VideoSettingsPanel(ConversionModel m) {
		this.model = m;
		this.reevaluatePanel();
	}

	private void reevaluatePanel() {
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
					model.getCurrentFile().modifySetting(
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
		JTextField resolution_Text = new JTextField();
		resolution_Text.setPreferredSize(new Dimension(300, 20));
		resolution_Text.setEnabled(false);
		reso.add(resolution_Text, BorderLayout.EAST);

		JPanel br = new JPanel();
		br.setLayout(new FlowLayout());
		br.add(new JLabel("Bitrate (kb/s) : "), BorderLayout.WEST);
		JTextField bitrate_Text = new JTextField();
		bitrate_Text.setPreferredSize(new Dimension(300, 20));
		bitrate_Text.setEnabled(false);
		br.add(bitrate_Text, BorderLayout.EAST);

		JPanel panef = new JPanel();
		panef.setLayout(new FlowLayout());
		panef.add(new JLabel("Images par seconde (FPS) : "), BorderLayout.WEST);
		JTextField fps_Text = new JTextField();
		fps_Text.setPreferredSize(new Dimension(300, 20));
		fps_Text.setEnabled(false);
		panef.add(fps_Text, BorderLayout.EAST);

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
		// this.repaint();
	}

}
