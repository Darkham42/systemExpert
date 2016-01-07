package sysexp.builders.lorraine;

import java.io.IOException;
import java.io.LineNumberReader;

import sysexp.builders.Builder;
import sysexp.modele.RegleAbstraite;

public class BuilderLorraine extends Builder {
	private Lexical lexical;
	private Declarations declarations;
	private Regles regles;
	public BuilderLorraine(LineNumberReader reader) {
		super(reader);
		this.lexical = new Lexical(reader);
	}
	
	@Override
	public void build(){
		try{
			if(!this.estBaseDeConnaissance()){
				System.err.println(this.afficherErreur());
				this.ecrireBaseDeRegle(null);
				return;
			}
		}
		catch(IOException e){
			System.err.println("Erreur: Le programme à rencontré un problème de lecture de la base de connaissance !");
			this.ecrireBaseDeRegle(null);
			return ;
		}
		
		this.ecrireBaseDeRegle(regles.lireBaseDeRegles());
	}

	public boolean estBaseDeConnaissance() throws IOException{
		declarations = new Declarations(lexical);
		if(!declarations.estDeclarations())
			return false;
		
		regles = new Regles(lexical, declarations.lireFaitDeclaree());
		if(!regles.estRegles()){
			return false;
		}
		
		if(lexical.lireJetonActuel().estFinExpression())
			return true;
		else
			return false;
	}
	
	public String afficherErreur(){
		if(!lexical.lireJetonActuel().estFinExpression())
			return "Erreur à\n" + lexical.getCursor();
		else
			return "Erreur, le fichier se termine trop vite";
	}
	

}
