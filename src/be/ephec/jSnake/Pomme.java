package be.ephec.jSnake;

/**
 * @author Gr�ce Musuvaho, K�vin Boucher, Smeyers Thibault
 * @version 0.1
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * 
 * D�finis la "pomme" dans le jeu
 *
 */

public class Pomme extends Box {
	private final static Random rnd = new Random(); //utilis�e pour g�n�rer des nombres al�atoires

	public Pomme() {
		super(getRndX(), getRndY()); 				// appel au constructeur de box 
	}

	public static int getRndX() {
		return rnd.nextInt(NB_COLONNES); 			//renvoi un entier au hasard pour la colonne
	}

	public static int getRndY() {
		return rnd.nextInt(NB_LIGNES); 				//renvoi un entier au hasard pour la ligne
	}

	public void newPomme(){							//modifier l'emplacement de la pomme une fois mang� serpent.
		setIndiceX(getRndX());
		setIndiceY(getRndY());
	}

	public void display(Graphics g) {											  //affichage de la pomme
		g.setColor(Color.green);  												  // couleur de la pomme 
		g.fillOval(getX() + 2, getY() + 2, getLargeur() - 4, getHauteur() - 4);   //fillOval = pomme ovale
	}

}
