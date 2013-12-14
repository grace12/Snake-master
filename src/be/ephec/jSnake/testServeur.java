package be.ephec.jSnake;

/**
 * @author Grâce Musuvaho, Smeyers Thibault, Boucher Kévin
 * @version 0.1
 * 
 */

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;

public class testServeur implements Const {
	public static void main(String[] args) {
		ServerSocket client ; //destiné a attendre des connexions
		Socket socketduserveur; //pour gerer la connexion d'un client
		BufferedReader in;
		PrintWriter out;
		KeyEvent event = null;


		try {
			// Etape 1: Créer un ServerSocket.
			client = new ServerSocket( PORT, 1 ); //(n°port,long file d'attente)
			//affichage du nom de la machine locale
			System.out.println("Adresse de la machine : "+InetAddress.getLocalHost().getHostAddress());
			System.out.println("Nom de la machine : "+InetAddress.getLocalHost().getHostName());

			// Etape 2: Attendre une connexion.
			System.out.println( "Attente d'une connexion\n" );
			socketduserveur = client.accept();//pour ecouter
			//cette methode se bloque jusqu'a reception d'une connexion

			System.out.println( "Connexion " +" reçue de:  " +                //getInetAddress() recupere l'@ internet de l'ordinateur client qui vient de se connecter
					socketduserveur.getInetAddress().getHostName() );  //getHostName() renvoie le nom d'hote associe à cette @


			// Etape 3: Get des flux d'entrée et de sortie.A FAIRE TOUJOURS DANS CET ORDRE
			out = new PrintWriter(socketduserveur.getOutputStream());  //sortie pour envoyer
			out.flush();//pour envoyer des info au client necessaire à une bonne connexion
			in = new BufferedReader(new InputStreamReader(socketduserveur.getInputStream()));            
			out.println( "testServeur : Vous êtes connecté !" );

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}








