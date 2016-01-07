package sysexp.builders.lorraine;

import java.io.IOException;
import java.util.HashMap;

import sysexp.modele.*;

public class Declarations {
	private Jeton precharger;
	private Lexical lexical;
	private HashMap<String, Fait> faitDeclaree;
	
	public Declarations(Lexical lexical) throws IOException {
		this.precharger = lexical.suivant();
		this.lexical = lexical;
		this.faitDeclaree = new HashMap<>();
	}
	
	public void ecrire(String instruction) {
		//System.out.println(instruction);
	}
	
	public boolean estDeclarations() throws IOException {
		
		if(!estDeclarationsBooleennes() || !estDeclarationsSymboliques() || !estDeclarationsEntiers()){
			return false;
		}
		
		ecrire("estDeclarations");
		return true;
			
	}

	public boolean estDeclarationsBooleennes() throws IOException {
		if(!precharger.estFaitsBooleen())
			return false;
		
		precharger = lexical.suivant();
		
		if(!precharger.estEgale())
			return false;
		
		precharger = lexical.suivant();
		
		if(!estFaitsBooleens())
			return false;
		
		if(!precharger.estPointVirgule())
			return false;
		
		precharger = lexical.suivant();
		ecrire("estDeclarationsBooleennes");
		return true;
	}

	public boolean estDeclarationsSymboliques() throws IOException {
		if(!precharger.estFaitsSymbolique())
			return false;
		
		precharger = lexical.suivant();
		
		if(!precharger.estEgale())
			return false;
		
		precharger = lexical.suivant();
		
		if(!estFaitsSymboliques())
			return false;
		
		if(!precharger.estPointVirgule()){
			return false;
		}
		
		precharger = lexical.suivant();
		ecrire("estDeclarationsSymboliques");	
		return true;
	}

	public boolean estDeclarationsEntiers() throws IOException {
		if(!precharger.estFaitsEntier())
			return false;
		
		precharger = lexical.suivant();
		
		if(!precharger.estEgale())
			return false;
		
		precharger = lexical.suivant();
		
		if(!estFaitsEntiers())
			return false;
		
		if(!precharger.estPointVirgule())
			return false;
		
		precharger = lexical.suivant();
		ecrire("estDeclarationsEntieres");
		return true;
	}

	public boolean estFaitsBooleens() throws IOException {
		if(precharger.estPointVirgule()){
			ecrire("estFaitsBooleens");
			return true;
		}
		
		if(!estFaitBooleen())
			return false;
		
		while(precharger.estVirgule()){
			precharger = lexical.suivant();
			
			if(!estFaitBooleen())
				return false;
		}
		
		ecrire("estFaitsBooleens");
		return true;
	}

	public boolean estFaitsSymboliques() throws IOException {
		if(precharger.estPointVirgule()){
			ecrire("estFaitsSymboliques");
			return true;
		}
		
		if(!estFaitSymbolique())
			return false;
		
		while(precharger.estVirgule()){
			precharger = lexical.suivant();
			
			if(!estFaitSymbolique())
				return false;
		}
		
		ecrire("estFaitsSymboliques");
		return true;
	}

	public boolean estFaitsEntiers() throws IOException {
		if(precharger.estPointVirgule()){
			ecrire("estFaitsEntiers");
			return true;
		}
		
		if(!estFaitEntier())
			return false;
		
		while(precharger.estVirgule()){
			precharger = lexical.suivant();
			
			if(!estFaitEntier())
				return false;
		}
		
		ecrire("estFaitsEntiers");
		return true;
	}

	public boolean estFaitBooleen() throws IOException {
		if(!precharger.estIdentificateur())
			return false;
		
		if(!this.faitDeclaree.containsKey(precharger.lireRepresentation())){
			this.faitDeclaree.put(precharger.lireRepresentation(), new FaitBooleen(precharger.lireRepresentation()));
		}
		else{
			System.err.println(precharger.lireRepresentation() + " un fait du même nom existe déjà");
			System.exit(1);
		}
		precharger = lexical.suivant();
		return true;
	}

	public boolean estFaitSymbolique() throws IOException {
		if(!precharger.estIdentificateur())
			return false;
		
		if(!this.faitDeclaree.containsKey(precharger.lireRepresentation())){
			this.faitDeclaree.put(precharger.lireRepresentation(), new FaitSymbolique(precharger.lireRepresentation()));
		}
		else{
			System.err.println(precharger.lireRepresentation() + " un fait du même nom existe déjà");
			System.exit(1);
		}
		precharger = lexical.suivant();
		return true;
	}

	public boolean estFaitEntier() throws IOException {
		if(!precharger.estIdentificateur())
			return false;
		
		if(!this.faitDeclaree.containsKey(precharger.lireRepresentation())){
			this.faitDeclaree.put(precharger.lireRepresentation(), new FaitEntier(precharger.lireRepresentation()));
		}
		else{
			System.err.println(precharger.lireRepresentation() + " un fait du même nom existe déjà");
			System.exit(1);
		}
		precharger = lexical.suivant();
		return true;
	}
	
	protected HashMap<String, Fait> lireFaitDeclaree(){
		return this.faitDeclaree;
	}
}
