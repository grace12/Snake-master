package be.ephec.jSnake;

/**
 * @author Gr�ce Musuvaho, K�vin Boucher, Smeyers Thibault
 * @version 0.1
 * 
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * D�finis l'affichage de la fen�tre de jeu
 *
 */
public class JWindow extends JFrame implements Const {

	private Core modele;
	private JPanel content;
	private JPanel jPanel1;

	public JWindow() {
		super("Snake 0.2");							// Titre de la fen�tre
		this.modele = new Core();					// cr�er le mod�le du jeu

		
		setDefaultCloseOperation(EXIT_ON_CLOSE); 	// fermeture de l'application lorsque la fen�tre est ferm�e
		setResizable(false);						// pas de redimensionnement possible de la fen�tre
		content = new JPanel() {		// cr�er un conteneur qui affichera le jeu
			@Override 
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);			
				JWindow.this.modele.display(g);		// affichage du mod�le du jeu
			}
		};	
		//setContentPane(content);					// ajouter le conteneur � la fen�tre
		getContentPane().add(content, BorderLayout.CENTER);
		content.setPreferredSize(new Dimension(		// dimension de ce conteneur
				NB_COLONNES * CASE_PIXELS,NB_LIGNES * CASE_PIXELS));
		
		jPanel1 = new JPanel(){
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);			
			JWindow.this.modele.display2(g);		// affichage du mod�le du jeu
		}
	};
		
		//setContentPane(jPanel1);
		getContentPane().add(jPanel1, BorderLayout.NORTH);
		jPanel1.setPreferredSize(new Dimension(NB_COLONNES * CASE_PIXELS, 30));

		Thread thread = new Thread(new Runnable() {	// Cr�er un thread infini
			@Override
			public void run() {
				while (true) { 						/* boucle infinie
													   � chaque fois que la boucle est ex�cut�e, la */
					JWindow.this.modele.calcul();		/* m�thode de calcul du jeu est appel�e.
													   Comme la boucle est infinie, la m�thode de calcul
													   sera appel�e en cycle perp�tuel. */


					content.repaint();				// demander � l'EDT (Event Dispatch Thread) de redessiner le conteneur
					jPanel1.repaint();

					try {							// temporisation pour une bonne perception (calcul+affichage toute les demi-secondes)
						Thread.sleep(JWindow.this.modele.changerNiveau());
					} catch (InterruptedException e){}

				}
			}
		});

		content.addKeyListener(new KeyAdapter() {	// le listener g�rant les entr�es au clavier
			@Override
			public void keyPressed(KeyEvent e) {
				JWindow.this.modele.KeyboardGestion(e);
			}
		});

		setFocusable(false);						// s'assurer du focus pour le listener clavier
		content.setFocusable(true);
		thread.start();								// lancer le thread
	}

	public static void main(String[] args) {		// Lancement du jeu 	
		JWindow window = new JWindow();				/* cr�ation de la fen�tre
													   dimensionnement de la fen�re "au plus juste" suivant
													   la taille des composants qu'elle contient */
		window.pack();
		window.setLocationRelativeTo(null);			// centrage sur l'�cran	
		window.setVisible(true);					// affichage
	}

}
