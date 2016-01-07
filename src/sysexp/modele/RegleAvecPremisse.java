package sysexp.modele;

import java.util.LinkedList;

import sysexp.modele.VisiteurForme.CodeErreur;

public class RegleAvecPremisse extends RegleAbstraite{
	private LinkedList<Forme> premisses;
	
	public RegleAvecPremisse(LinkedList<Forme> premisses, Forme conclusion) {
		super(conclusion);
		this.premisses = premisses;
	}

	@Override
	public String toString() {
		String premisses = "";
		for (Forme forme : this.premisses) {
			premisses += forme + ", ";
		}
		return "SI " + premisses + " ALORS " + this.conclusion;
	}

	@Override
	public boolean estDeclenchable(BaseDeFaits baseDeFaits) {
		VisiteurForme visiteur = new VisiteurFormeMoteur(baseDeFaits);
		for(Forme premisse : this.premisses){
			premisse.accept(visiteur);
			if(!visiteur.dernierePremisseVerifier)
				return false;
			
			visiteur.reinitialiser();
		}
		return true;
	}
}
