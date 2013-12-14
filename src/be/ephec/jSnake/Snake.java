package be.ephec.jSnake;
/**
 * @author Gr�ce Musuvaho, K�vin Boucher, Smeyers Thibault
 * @version 0.1
 * 
 */

import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * 
 * Classe principale d�finissant le jeu snake, d�finis quand le serpent meurt, sa direction,
 * s'il peut manger ou non la pomme,...
 *
 */

public class Snake {

	private LinkedList<Box> list;
	private Direction direction;
	private Direction demand;
	private boolean isDead;
	private int nbEat;

	public Snake() {
		this.list = new LinkedList<Box>();
		this.list.add(new Box(14, 15));
		this.list.add(new Box(15, 15));
		this.list.add(new Box(16, 15));
		this.direction = Direction.LEFT;
	}

	/**
	 * D�finis la direction du serpent selon o� celui-ci se trouve
	 * @return : null
	 */
	private Box getNextBox() {
		Box head = this.list.getFirst();
		switch (this.direction) {
		case HIGH:
			return new Box(head.getIndiceX(), head.getIndiceY() - 1);
		case RIGHT:
			return new Box(head.getIndiceX() + 1, head.getIndiceY());
		case LOW:
			return new Box(head.getIndiceX(), head.getIndiceY() + 1);
		case LEFT:
			return new Box(head.getIndiceX() - 1, head.getIndiceY());
		}
		return null;
	}

	/**
	 * Demande la direction
	 * @param demand : direction demand�e
	 */
	public void setDemand(Direction demand) {
		this.demand = demand;
	}

	/**
	 * Le serpent prend la direction donn�e selon la touche clavier press�e
	 */
	private void turn() {
		if (this.demand != null) { 							// une touche � �t� press�e, le serpent va vers le haut ou le bas 
			if (this.direction == Direction.HIGH
					|| this.direction == Direction.LOW) {
				if (this.demand == Direction.RIGHT) { 		// la touche droite � �t� press�e
					// le serpent tourne � droite
					this.direction = Direction.RIGHT;
				} else if (this.demand == Direction.LEFT) { // la touche gauche � �t� press�e
					// le serpent tourne � gauche
					this.direction = Direction.LEFT;
				}
			} else { 										// le serpent va vers la droite ou la gauche
				if (this.demand == Direction.HIGH) { 		// la touche haut � �t� press�e
					// le serpent tourne vers le haut
					this.direction = Direction.HIGH;
				} else if (this.demand == Direction.LOW) { 	// la touche bas � �t� press�e
					// le serpent tourne vers le bas
					this.direction = Direction.LOW;
				}
			}
			/* nous avons tenu compte du clavier, nous le vidons afin de
															   forcer le joueur a r�appuy� sur une touche pour demander
															   une autre direction */
			this.demand = null;
		}
	}

	/**
	 * Permet au serpent d'avancer si la case suivante est valide (pas atteind la limite de
	 * jeu et si la case suivante ne fait pas partie de lui m�me.
	 * @return si la case valide suivante
	 */
	private boolean OKAvancer() {
		Box nextBox = getNextBox();
		return getNextBox().isValid() && !this.list.contains(nextBox); // peut avancer si la case suivante est valide et n'est pas une parti du serpent
	}
	
	
	/**
	 * Ajoute en t�te de liste la case sur laquelle le serpent doit se d�placer
	 */
	private void avance() {									
		this.list.addFirst(getNextBox());	
		this.list.removeLast();								// supprime le dernier �l�ment de la liste
	}

	/**
	 * D�finis si la pomme peut �tre mang�e par le serpent ou non et d�place celle-ci ensuite
	 * @param pomme
	 */
	public void calcul(Pomme pomme) {						// calcul du serpent	
		turn();
		if(canEat(pomme)){ 									// vois si la pomme peut etre mang�e
			eat();            								//  mange la pomme
			pomme.newPomme();  								// deplace la pommme (new position) 
		}

		if (OKAvancer()) {
			avance();
		} else {
			// la partie est perdue car le serpent a atteint les limites du plateau de jeu
			this.isDead = true;
		}
	}

	/**
	 * D�finis si le serpent est mort ou non
	 * @return : serpent mort
	 */
	public boolean isDead() {
		return this.isDead;
	}

	/**
	 * Permet l'affichage du serpent au sein du jeu et permet l'anti-aliasing du dessin (anti-
	 * crenelage)
	 */
	public void display(Graphics g) {						// activer l'anti-aliasing du dessin
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		for (Box box : this.list) {							// dessin du serpent
			g.fillRect(box.getX(), box.getY(), box.getLargeur(), box.getHauteur()); 
			g.setColor(Color.blue); 						// permet de changer la couleur du serpent
		} 
	}

	/**
	 * Ajoute en fin de liste la case sur laquelle le serpent doit se d�placer
	 */
	private void eat() {																		

		this.list.addFirst(getNextBox());
		this.nbEat++; 										// incrementer le nombre de pomme mang�
	}

	/**
	 * Permet de voir si le serpent peut manger la pomme, c'est � dire, si la prochaine case
	 * ou le serpent va, est celle contenant la pomme														
	 * @param pomme : le lieu ou se trouve la pomme
	 * @return : TRUE si la prochaine case contient bien la pomme
	 */
	private boolean canEat(Pomme pomme) {
		Box nextCase = getNextBox();   						//nextcCase c'est la prochaine case ou le serpent va
		return pomme.getIndiceX() == nextCase.getIndiceX()  // compare l'emplacement de la pomme avec la prochaine case du serpent
				&& pomme.getIndiceY() == nextCase.getIndiceY(); // retourne vrai si la prochaine case ou le serpent va est la m�me que la case de la pomme
	}

	/**
	 * Getter sur NbEat
	 * @return : nbEat
	 */
	public int getNbEat() {
		return this.nbEat;
	}



}