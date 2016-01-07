package sysexp.client;

/**
 * Client de l'application, il interagit avec l'utilisateur
 * pour lui signaler les erreurs possible quant a l'ouverture du fichier
 *	@author Darkham and Tanaky
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;

import sysexp.builders.Builder;
import sysexp.builders.Director;
import sysexp.builders.lorraine.BuilderLorraine;
import sysexp.modele.BaseDeFaits;
import sysexp.modele.MoteurInference;
import sysexp.modele.RegleAbstraite;

public class SysExp {
	/**
	 * Fonction d'entree de l'application
	 * @param args
	 */
	public static void main(String[] args) {
		LineNumberReader reader = null;
		if(args.length == 0){
			System.out.println("Usage: java SysExp <fichier>");
			return;
		}
		
		if(args.length != 1){
			System.err.println("Nombre d'arguments incorrecte");
			return;
		}
		
		try{
			reader = new LineNumberReader(new FileReader(args[0]));
		}
		catch(FileNotFoundException e){
			System.err.println("Impossible d'ouvrir: <" + args[0] + ">");
			return;
		}

		Builder builder = new BuilderLorraine(reader);
		Director director = new Director(builder);
		director.construct();
		
		BaseDeFaits baseDeFaits = new BaseDeFaits();
		
		RegleAbstraite baseDeRegles = director.getBuilder().lireBaseDeRegles();
		
		if(baseDeRegles == null)
			return;
			
		MoteurInference.deduire(baseDeRegles, baseDeFaits);
		
		System.out.println("\n" + baseDeFaits);
		
	}
}
