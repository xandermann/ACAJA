package gui.processing;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LibraryView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2415628952891609528L;
	private ProcessingModel model;
	private Image image;
	private static boolean actualiser;

	public LibraryView(ProcessingModel m) {
		this.model = m;
		// this.setPreferredSize(new Dimension(250, 550));
		this.setLayout(new GridLayout(4, 1));
		actualiser = false;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (actualiser)
			createAll();
		ProcessingTools.drawDeco(g, this.getHeight(), this.getWidth());

	}

	public void createAll() {
		removeAll();

		for (File i : model.getImages()) {
			JPanel j = new JPanel() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 4570923892169107221L;

				@Override
				public void paintComponent(Graphics g) {
					super.paintComponents(g);

					try {
						image = ImageIO.read(i);
					} catch (IOException e) {
						e.printStackTrace();
					}

					g.drawImage(image, 12, 12, this.getWidth() - 24, 120, null);
				}
			};
			this.add(j);

		}

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				int ind = e.getY() / (getHeight() / 4);
				System.out.println("Passage ajout");
				model.addForm(10, 10, 200, 150, 'i', model.getImages().get(ind));
				System.out.println("Ajout");
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		revalidate();
		setActualiser(false);
	}

	public static void setActualiser(boolean actu) {
		actualiser = actu;
	}
}
