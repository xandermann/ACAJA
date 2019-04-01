package gui.start;

import java.awt.Dimension;
import javax.swing.JFrame;

import gui.WindowTools;
import resources.ResourcesManager;

/**
 * [ FENETRE DE PRESENTATION DU LOGICIEL. ]
 */
public final class LoadingWindow extends JFrame {
	/**
	 * [ CONSTRUIT UNE FENETRE DE PRESENTATION DU LOGICIEL. ]
	 */
	public LoadingWindow() {
		super("Acaja - un logiciel a la portee de tous");

		ResourcesManager.clearResources();

		setSize(new Dimension(400, 400));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//
		// Design de l'OpeningWindow, ajout du panel
		LoadingView loadingView = new LoadingView();
		add(loadingView);
		//
		//

		WindowTools.showLogo(this);
		WindowTools.executeWindow(this);

		// Menipulation de la barre de chargement
		try {
			Thread.sleep(1000);

			loadingView.setPercentage(36);

			Thread.sleep(1000);

			loadingView.setPercentage(80);

			Thread.sleep(500);

			loadingView.setPercentage(100);

			Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		dispose();
	}
}
