package be.ephec.jSnake;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;

/**
 * 
 * @author Gr�ce Musuvaho, K�vin Boucher, Smeyers Thibault
 * @version 0.1
 * 
 */

/**
 * 
 * Classe d�finissant les principaux "calculs" du jeu (gestion des claviers, changement de 
 * niveau, ...)
 *
 */
public class Core {

	private Snake snake;
	private Snake snake2;
	private boolean finish;
	private Pomme pomme;

	public Core() {
		this.snake = new Snake();
		this.snake2 = new Snake();
		this.finish = false;
		this.pomme= new Pomme();
	}

	/**
	 * Gestion au clavier les serpents (snake 1 + 2). Permet de diriger le snake du joueur 1 et 2
	 * @param event : gestionnaire de touche au clavier (gauche / Q, droite / D, haut / S, bas / X)
	 */

	public void KeyboardGestion(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) { 			// touche fl�che droite
			this.snake.setDemand(Direction.RIGHT);
		} else if (event.getKeyCode() == KeyEvent.VK_LEFT) { 	// touche fl�che gauche
			this.snake.setDemand(Direction.LEFT);
		} else if (event.getKeyCode() == KeyEvent.VK_UP) { 		// touche fl�che haut
			this.snake.setDemand(Direction.HIGH);
		} else if (event.getKeyCode() == KeyEvent.VK_DOWN) { 	// touche fl�che bas
			this.snake.setDemand(Direction.LOW);
		}
		//Snake 2

		if (event.getKeyCode() == KeyEvent.VK_D) { 				// touche fl�che droite
			this.snake2.setDemand(Direction.RIGHT);
		} else if (event.getKeyCode() == KeyEvent.VK_Q) { 		// touche fl�che gauche
			this.snake2.setDemand(Direction.LEFT);
		} else if (event.getKeyCode() == KeyEvent.VK_S) { 		// touche fl�che haut
			this.snake2.setDemand(Direction.HIGH);
		} else if (event.getKeyCode() == KeyEvent.VK_X) { 		// touche fl�che bas
			this.snake2.setDemand(Direction.LOW);
		}
	}



	/**
	 * Calculs du jeu
	 */

	public void calcul() {
		if (!this.finish) {
			// calcul du serpent
			this.snake.calcul(this.pomme);
			this.snake2.calcul(this.pomme);
			if (this.snake.isDead() || this.snake2.isDead()) {
				/* la partie est perdue car le serpent
																   a atteint les limites du plateau de jeu */
				this.finish = true;
			}
		}
	}

	/**
	 * Dessin graphique du jeu
	 * @param g : graphique de jeu
	 */

	public void display2(Graphics g){
		
		g.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		// Couleurs du premier serpent
		g.setColor(Color.DARK_GRAY);
		g.drawString(String.valueOf("Score snake1: "+snake.getNbEat()), 500, 15); //5;25 = coordonn�es
		
		// Couleurs du second serpent
		g.setColor(Color.BLUE);
		g.drawString(String.valueOf("Score snake2: "+snake2.getNbEat()), 5, 15); //5;25 = coordonn�es
	}
	
	public void display(Graphics g) {
		this.snake.display(g);			// affichage des serpents
		this.snake2.display(g);
		this.pomme.display(g);			// affichage de la pomme

		if (this.finish) {
			String str = "Perdu !";
			g.setColor(Color.RED);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
			FontMetrics fm = g.getFontMetrics();
			int x = (g.getClipBounds().width - fm.stringWidth(str)) / 2;
			int y = (g.getClipBounds().height / 3) + fm.getMaxDescent();
			g.drawString(str, x, y);
			
			String s="Gagnant :";
				g.setColor(Color.RED);
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				FontMetrics f = g.getFontMetrics();
				int x1 = (g.getClipBounds().width/4 - f.stringWidth(str));
				int y1 = (g.getClipBounds().height/2) + f.getMaxDescent();
				
				if(snake.getNbEat()>snake2.getNbEat()) g.drawString(s+"Joueur 1", x1, y1);
				else if(snake.getNbEat()>snake2.getNbEat())  g.drawString(s+"Joueur 2", x1, y1);
				else g.drawString(s+" aucun, match null", x1, y1);
		}		
		
	}

	/**
	 * Getter sur le niveau du premier serpent
	 * @return : le nombre de pommes mang�es par le premier serpent 
	 * Le niveau augmente apr�s 5 pommes (+1 = niveau commence � 1)
	 */
	public int getNiveau() {
		return (this.snake.getNbEat() / 2) + 1;
	}

	
	/**
	 * Permet le changement de niveau pour le snake 1
	 * @return le nombres de points du joueur
	 */
	public int changerNiveau() {
		switch (getNiveau()) {
		case 1:
			return 500;
		case 2:
			return 400;
		case 3:
			return 300;
		case 4:
			return 100;

		default:
			return 50;
		}
	}



}
