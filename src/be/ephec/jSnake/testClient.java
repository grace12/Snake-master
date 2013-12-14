package be.ephec.jSnake;

/**
 * @author Gr�ce Musuvaho, Boucher K�vin, Smeyers Thibault
 * @version 0.1
 */

import java.net.*;
import java.util.Scanner;
import java.io.*; 


public class testClient implements Const {

	public static void main(String[] args) {
	
		String adresServeur ;
		ServerSocket serveur;				//destin� a attendre des connexions
		Socket connexion;					//pour gerer la connexion d'un client
		Socket client; 
		BufferedReader in;
		PrintStream out;

		try {
			
			//demande au joueur l'adresse de la machine adverse
			Scanner sc = new Scanner(System.in);
			System.out.println( "Quel est l'adresse de la machine adverse ?");
			adresServeur = sc.nextLine();

			// Etape 1: Cr�er un Socket pour assurer la connexion.
			System.out.println("Tentative de connexion");
			client = new Socket(adresServeur, PORT );
			System.out.println( "Connect� �: " +client.getInetAddress().getHostName() );

			// Etape 2: Get des flux d'entr�e et de sortie.
			out= new PrintStream(client.getOutputStream() );
			out.flush();
			in = new BufferedReader( new InputStreamReader(client.getInputStream()) );
			System.out.println( "connexion ok" );

		}//fin try
		catch ( EOFException eof ) {
			System.out.println( "testServeur a cl�tur� la connexion" );
		}
		catch ( IOException e ) {
			e.printStackTrace();

		}
	}
}

