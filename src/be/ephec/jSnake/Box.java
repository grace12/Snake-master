package be.ephec.jSnake;

/**
 * 
 * @author Grâce Musuvaho, Kévin Boucher, Smeyers Thibault
 * @version 0.1
 *
 */

/**
 * 
 * ??? expl de la classe
 *
 */
public class Box implements Const {

	private int xIndice;
	private int yIndice;

	/**
	 *  ???????
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
	 * Getter de la coordonnée horizontale en pixels
	 * @return : la coordonnée horizontale en pixels
	 */

	public int getX() {
		return this.xIndice * CASE_PIXELS;
	}

	/**
	 * Getter de la coordonnée verticale en pixels
	 * @return : la coordonnée verticale en pixels
	 */

	public int getY() {
		return this.yIndice * CASE_PIXELS;
	}

	/**
	 * ????
	 * @return : 
	 */
	public int getLargeur() {
		return CASE_PIXELS;
	}

	/**
	 * ?????
	 * @return
	 */
	public int getHauteur() {
		return CASE_PIXELS;
	}

	@Override
	//Deux « box » sont identiques si leurs xIndice et yIndice ont les mêmes valeurs.
	public boolean equals(Object obj) {
		if (obj instanceof Box) {
			Box box = (Box) obj;
			return this.xIndice == box.xIndice
					&& this.yIndice == box.yIndice;
		}
		return false;
	}

}