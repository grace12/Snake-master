package be.ephec.jSnake;

/**
 * @author Grâce Musuvaho, Kévin Boucher, Smeyers Thibault
 * @version 0.1
 * 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * Définis l'affichage de fenêtre 
 *
 */
public class JWindow extends JFrame implements Const {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Core modele;
	private JPanel content;
	private JPanel jPanel1;

	public JWindow() {
		super("Snake 0.2");								// Titre de la fenêtre
		this.modele = new Core();						// créer le modèle du jeu


		setDefaultCloseOperation(EXIT_ON_CLOSE); 		// fermeture de l'application lorsque la fenêtre est fermée
		setResizable(false);							// pas de redimensionnement possible de la fenêtre
		content = new JPanel() {						// créer un conteneur qui affichera le jeu
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override 
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);			
				JWindow.this.modele.display(g);			// affichage du modèle du jeu
			}
		};	

		getContentPane().add(content, BorderLayout.CENTER);
		content.setPreferredSize(new Dimension(			// dimension de ce conteneur
				NB_COLONNES * CASE_PIXELS,NB_LIGNES * CASE_PIXELS));

		jPanel1 = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);			
				JWindow.this.modele.display2(g);		// affichage du modèle du jeu
			}
		};

		getContentPane().add(jPanel1, BorderLayout.NORTH);
		jPanel1.setPreferredSize(new Dimension(NB_COLONNES * CASE_PIXELS, 30));

		Thread thread = new Thread(new Runnable() {		// Créer un thread infini
			@Override
			public void run() {
				while (true) { 							// boucle infinie
														// à chaque fois que la boucle est exécutée, la
					JWindow.this.modele.calcul();		// méthode de calcul du jeu est appelée

					content.repaint();					// demander à l'EDT (Event Dispatch Thread) de redessiner le conteneur
					jPanel1.repaint();

					try {								// temporisation pour une bonne perception (calcul+affichage toute les demi-secondes)
						Thread.sleep(JWindow.this.modele.changerNiveau());
					} catch (InterruptedException e){}

				}
			}
		});

		content.addKeyListener(new KeyAdapter() {		// le listener gérant les entrées au clavier
			@Override
			public void keyPressed(KeyEvent e) {
				JWindow.this.modele.KeyboardGestion(e);
			}
		});

		setFocusable(false);							// s'assurer du focus pour le listener clavier
		content.setFocusable(true);
		thread.start();									// lancer le thread
	}	

	public static void main(String[] args) {			// Lancement du jeu 	
		JWindow window = new JWindow();					/* création de la fenêtre
													   		dimensionnement de la fenêre "au plus juste" suivant
													   		la taille des composants qu'elle contient */
		window.pack();
		window.setLocationRelativeTo(null);				// centrage sur l'écran	
		window.setVisible(true);						// affichage
	}

}
