package gui_conversion;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SoundSettingsPanel extends JPanel {

	private ConversionModel model;
	
	public SoundSettingsPanel(ConversionModel m) {
		this.model = m;
	}
	
	private void reevaluatePanel() {
		this.removeAll();
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5, 2));
		p.add(new JLabel("Codec utilise : "));
		p.add(new JComboBox<>());
		p.add(new JLabel("Volume en sortie (en %): "));
		p.add(new JTextField());
		p.add(new JLabel("Bitrate (kb/s) : "));
		p.add(new JTextField());
		p.add(new JLabel("Taux d'echantillonnage (Hz) : "));
		p.add(new JTextField());
		p.add(new JLabel("Nombre de canaux audio en sortie : "));
		p.add(new JTextField());
	}
}
