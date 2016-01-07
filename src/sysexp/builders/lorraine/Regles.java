package sysexp.builders.lorraine;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import sysexp.modele.*;

public class Regles {
	private Jeton precharger;
	private Lexical lexical;
	private HashMap<String, Fait> faitDeclaree;
	
	private RegleAbstraite baseDeRegles;
	
	private Forme conclusion;
	private LinkedList<Forme> premisses;
	
	private String fait;
	private Comparateur comparateur;
	private String valeurFormeSymbolique;
	private Noeud valeurFormeEntiere;
	private String additif, multiplicatif;
	private String valeurNombre;
	
	public Regles(Lexical lexical, HashMap<String, Fait> faitDeclaree) {
		this.lexical = lexical;
		this.precharger = lexical.lireJetonActuel();
		this.faitDeclaree = faitDeclaree;
		this.premisses = new LinkedList<Forme>();
	}
	
	public boolean estFaitBooleen() throws IOException {
		if(!precharger.estIdentificateur())
			return false;
		
		if(!this.faitDeclaree.containsKey(precharger.lireRepresentation()))
			return false;
		
		if(this.faitDeclaree.get(precharger.lireRepresentation()).getClass() != FaitBooleen.class)
			return false;
		
		this.fait = precharger.lireRepresentation();
		precharger = lexical.suivant();
		return true;
	}

	public boolean estFaitSymbolique() throws IOException {
		if(!precharger.estIdentificateur())
			return false;
		
		if(!this.faitDeclaree.containsKey(precharger.lireRepresentation())){
			return false;
		}
		
		if(this.faitDeclaree.get(precharger.lireRepresentation()).getClass() != FaitSymbolique.class)
			return false;
		
		this.fait = precharger.lireRepresentation();
		precharger = lexical.suivant();
		return true;
	}
	
	public boolean estFaitEntier() throws IOException {
		if(!precharger.estIdentificateur())
			return false;
		
		if(!this.faitDeclaree.containsKey(precharger.lireRepresentation()))
			return false;
		
		if(this.faitDeclaree.get(precharger.lireRepresentation()).getClass() != FaitEntier.class)
			return false;
		
		this.valeurFormeEntiere = new FaitEntier(precharger.lireRepresentation());
		this.fait = precharger.lireRepresentation();
		precharger = lexical.suivant();
		return true;
	}

	public boolean estRegles() throws IOException {
		while(estRegle()){
			if(!precharger.estPointVirgule())
				return false;
			
			precharger = lexical.suivant();
		}
		
		return true;
	}

	public boolean estRegle() throws IOException {
		this.conclusion = null;
		this.premisses.removeAll(this.premisses);
		
		if(!estRegleSansPremisse() && !estRegleAvecPremisses())
			return false;
		
		return true;
	}

	public boolean estRegleSansPremisse() throws IOException {
		if(!estConclusion()){
			return false;
		}
		
		RegleAbstraite regleAbstraite = new RegleSansPremisse(this.conclusion);
		regleAbstraite.ajouterSuccesseur(this.baseDeRegles);
		this.baseDeRegles = regleAbstraite;
		return true;
	}

	public boolean estRegleAvecPremisses() throws IOException {
		
		if(!precharger.estSi())
			return false;
		
		precharger = lexical.suivant();
		
		if(!estCondition())
			return false;
		
		if(!precharger.estAlors())
			return false;
		
		precharger = lexical.suivant();
				
		if(!estConclusion())
			return false;
		
		RegleAbstraite regleAbstraite = new RegleAvecPremisse((LinkedList<Forme>) this.premisses.clone(), this.conclusion);
		regleAbstraite.ajouterSuccesseur(this.baseDeRegles);
		this.baseDeRegles = regleAbstraite;
		return true;
	}
	
	public boolean estConclusion() throws IOException {
		if(!estConclusionBooleenne() && !estConclusionSymbolique() && !estConclusionEntiere())
			return false;
		
		return true;
	}

	public boolean estConclusionBooleenne() throws IOException {
		boolean affirmative = true;
		if (precharger.estNon()) {
			precharger = lexical.suivant();
			affirmative = false;
		}
		
		if(!estFaitBooleen()){
			return false;
		}
		
		if(affirmative)
			this.conclusion = new ConclusionBooleenneAffirmative(this.fait);
		else
			this.conclusion = new ConclusionBooleenneNegative(this.fait);
		return true;
	}
	
	public boolean estConclusionSymbolique() throws IOException {
		boolean faitSymbolique = false;
		if(!estFaitSymbolique())
			return false;
		
		if(!precharger.estEgale())
			return false;
		
		precharger = lexical.suivant();
		
		if(!(faitSymbolique = estFaitSymbolique()) && !estConstanteSymbolique())
			return false;
		
		if(faitSymbolique)
			this.conclusion = new ConclusionSymboliqueFait(this.fait, Comparateur.egale, this.valeurFormeSymbolique);
		else
			this.conclusion = new ConclusionSymboliqueConstante(this.fait, Comparateur.egale, this.valeurFormeSymbolique);
		
		return true;
	}
	
	public boolean estConstanteSymbolique() throws IOException {
		if(!precharger.estIdentificateur())
			return false;
		
		if(this.faitDeclaree.containsKey(precharger.lireRepresentation())){
			System.err.println("Une Constante symbolique ne doit pas etre dans la base de faits");
			return false;
		}
		
		this.valeurFormeSymbolique = precharger.lireRepresentation();
		precharger = lexical.suivant();
		return true;
	}

	public boolean estConclusionEntiere() throws IOException {
		if(!estFaitEntier())
			return false;
		String fait = this.fait;
		
		if(!precharger.estEgale())
			return false;
		
		precharger = lexical.suivant();
		
		if(!estExpressionEntiere())
			return false;
		
		if(this.valeurFormeEntiere.getClass() == FaitEntier.class)
			this.conclusion = new ConclusionEntiereFait(fait, Comparateur.egale, (FaitEntier)this.valeurFormeEntiere);
		else
			this.conclusion = new ConclusionEntiereExpression(fait, Comparateur.egale, this.valeurFormeEntiere);
		
		return true;
	}

	public boolean estExpressionEntiere() throws IOException {
		int signe = 1;
		boolean additif = estAdditif();
		
		if(additif && this.additif.equals(FabriqueJeton.operateurMoins().lireRepresentation()))
			signe = -1;
		
		if (!estTerme()) {
			return false;
		}
		
		this.valeurFormeEntiere.attribuerUnSigne(signe);
		
		while (estAdditif()) {
			String additifActuel = this.additif;
			Noeud gauche = this.valeurFormeEntiere;
			
			if (!estTerme())
				return false;
			
			Noeud droite = this.valeurFormeEntiere;
			
			if(additifActuel.equals(FabriqueJeton.operateurMoins().lireRepresentation()))
				this.valeurFormeEntiere = new Soustraction(gauche, droite);
			else
				this.valeurFormeEntiere = new Addition(gauche, droite);
		}
		
		return true;
	}

	public boolean estTerme() throws IOException {
		if (!estFacteur()) {
			return false;
		}
		
		while (estMultiplicatif()) {
			Noeud gauche = this.valeurFormeEntiere;
			
			if(!estFacteur())
				return false;
			
			Noeud droite = this.valeurFormeEntiere;
			
			if(this.multiplicatif.equals(FabriqueJeton.operateurMultiplie().lireRepresentation()))
				this.valeurFormeEntiere = new Multiplication(gauche, droite);
			else
				this.valeurFormeEntiere = new Division(gauche, droite);
		}
		
		return true;
	}

	public boolean estFacteur() throws IOException {
		boolean estParentheseOuvrante = precharger.estParentheseOuvrante();
		if(!estConstanteEntiere() && !estFaitEntier() && !estParentheseOuvrante){
			return false;
		}
		if(estParentheseOuvrante){
			precharger = lexical.suivant();
			
			if(!estExpressionEntiere())
				return false;
			
			if(!precharger.estParentheseFermante())
				return false;
			
			precharger = lexical.suivant();
		}
		
		return true;
	}
	
	public boolean estAdditif() throws IOException {
		if (!precharger.estOperateurPlus() && !precharger.estOperateurMoins())
			return false;
		
		this.additif = precharger.lireRepresentation();
		precharger = lexical.suivant();
		return true;
	}
	
	public boolean estMultiplicatif() throws IOException {
		if (!precharger.estOperateurMultiplie() && !precharger.estOperateurDivise())
			return false;
		
		this.multiplicatif = precharger.lireRepresentation();
		precharger = lexical.suivant();
		return true;
	}

	public boolean estConstanteEntiere() throws IOException {
		this.valeurNombre = "";
		if (precharger.estZero()){
			this.valeurFormeEntiere = new ConstanteEntiere(0);
			precharger = lexical.suivant();
			return true;
		}
		
		if(!precharger.estChiffrePositif())
			return false;
		
		this.valeurNombre += precharger.lireRepresentation();
		precharger = lexical.suivant();
		
		while (estChiffre());

		this.valeurFormeEntiere = new ConstanteEntiere(Integer.parseInt(this.valeurNombre));
		return true;
	}
	
	public boolean estCondition() throws IOException {
		if(!estPremisse())
			return false;
		
		while(precharger.estEt()){
			precharger = lexical.suivant();
			if(!estPremisse())
				return false;
		}
		
		return true;
	}

	public boolean estPremisse() throws IOException {
		if (!estPremisseBooleenne() && !estPremisseSymbolique() && !estPremisseEntiere()) {
			return false;
		}

		return true;
	}

	public boolean estPremisseBooleenne() throws IOException {
		boolean affirmative = true;
		
		if (precharger.estNon()) {
			precharger = lexical.suivant();
			affirmative = false;
		}
		
		if(!estFaitBooleen()){
			return false;
		}
		
		if(affirmative)
			this.premisses.add(new PremisseBooleenneAffirmative(this.fait));
		else
			this.premisses.add(new PremisseBooleenneNegative(this.fait));
		
		return true;
	}

	public boolean estPremisseSymbolique() throws IOException {
		boolean estEgale = false;
		if(!estFaitSymbolique())
			return false;
		
		String faitSymbolique = this.fait;
		
		if(!(estEgale = precharger.estEgale()) && !precharger.estDifferent()){
			return false;
		}
		
		precharger = lexical.suivant();
		
		if(estEgale){
			if(!estConstanteSymbolique())
				return false;
			
			this.premisses.add(new PremisseSymboliqueConstante(faitSymbolique, Comparateur.egale, this.valeurFormeSymbolique));
		}
		else{
			if(!estFaitSymbolique())
				return false;
			
			this.premisses.add(new PremisseSymboliqueFait(faitSymbolique, Comparateur.egale, this.valeurFormeSymbolique));
			
		}
		
		return true;
	}

	public boolean estPremisseEntiere() throws IOException {
		if(!estFaitEntier())
			return false;
		
		String faitEntier = this.fait;
		
		if(!estComparateur())
			return false;
		
		if(!estExpressionEntiere())
			return false;
		
		if(this.valeurFormeEntiere.getClass() == FaitEntier.class)
			this.premisses.add(new PremisseEntiereFait(faitEntier, this.comparateur, (FaitEntier) this.valeurFormeEntiere));
		else
			this.premisses.add(new PremisseEntiereExpression(faitEntier, this.comparateur, this.valeurFormeEntiere));
		
		return true;
	}

	public boolean estComparateur() throws IOException {
		if(!precharger.estInferieur() && !precharger.estSuperieur() && !precharger.estInferieurOuEgale() && !precharger.estSuperieurOuEgale() && !precharger.estEgale() && !precharger.estDifferent())
			return false;
			
		for(Comparateur comparateur : Comparateur.values()){
			if(precharger.lireRepresentation().equals(comparateur.lireRepresentation())){
				this.comparateur = comparateur;
				break;
			}
		}
		precharger = lexical.suivant();
		return true;
	}

	public boolean estChiffre() throws IOException {
		if(!precharger.estZero() && !precharger.estChiffrePositif())
			return false;
		
		this.valeurNombre += precharger.lireRepresentation();
		precharger = lexical.suivant();
		return true;
	}
	
	public RegleAbstraite lireBaseDeRegles(){
		return this.baseDeRegles;
	}
}
