package be.ephec.jSnake;

/**
 * 
 * @author Gr�ce Musuvaho, K�vin Boucher, Smeyers Thibault
 * @version 0.1
 *
 */

/**
 * 
 * Classe box, impl�mentant l'interface Const. Cette classe permet de g�rer les diff�rents cases
 * du jeu snake.
 *
 */
public class Box implements Const {

	private int xIndice;
	private int yIndice;

	/**
	 *  D�finis les X et Y indices.
	 * @param xIndice : 
	 * @param yIndice
	 */

	public Box(int xIndice, int yIndice) {
		this.xIndice = xIndice;
		this.yIndice = yIndice;
	}

	/**
	 * Permet de savoir si une case est contenue dans le plateau de jeu
	 * @return : TRUE si la case est bien contenue dans le plateau de jeu
	 */

	public boolean isValid() {
		return this.xIndice >= 0 && this.xIndice < NB_COLONNES
				&& this.yIndice >= 0 && this.yIndice < NB_LIGNES;
	}

	/**
	 * Setter de l'indice horizontal
	 * @param x : this.xIndice, l'indice horizontal
	 */

	public void setIndiceX(int x) {
		this.xIndice = x;
	}

	/**
	 * Getter de l'indice horizontal
	 * @return : this.xIndice, l'indice horizontal
	 */

	public int getIndiceX() {
		return this.xIndice;
	}

	/**
	 * Setter de l'indice vertical
	 * @param y : this.yIndice, l'indice vertical 
	 */

	public void setIndiceY(int y) {
		this.yIndice = y;
	}

	/**
	 * Getter de l'indice vertical
	 * @return this.yIndice, l'indice vertical
	 */

	public int getIndiceY() {
		return this.yIndice;
	}

	/**
	 * Getter de la coordonn�e horizontale en pixels
	 * @return : la coordonn�e horizontale en pixels
	 */

	public int getX() {
		return this.xIndice * CASE_PIXELS;
	}

	/**
	 * Getter de la coordonn�e verticale en pixels
	 * @return : la coordonn�e verticale en pixels
	 */

	public int getY() {
		return this.yIndice * CASE_PIXELS;
	}

	/**
	 * Getter sur la largeur du plateau de jeu en pixels
	 * @return : la largeur du plateau de jeu en pixels
	 */
	public int getLargeur() {
		return CASE_PIXELS;
	}

	/**
	 * Getter sur la hauteur du plateau de jeu en pixels
	 * @return : la hauteur du plateau de jeu en pixels
	 */
	public int getHauteur() {
		return CASE_PIXELS;
	}
	
	@Override
	//Deux � box � sont identiques si leurs xIndice et yIndice ont les m�mes valeurs.
	public boolean equals(Object obj) {
	      if (obj instanceof Box) {
	           Box box = (Box) obj;
	            return this.xIndice == box.xIndice
	                   && this.yIndice == box.yIndice;
	      }
	      return false;
	}

}