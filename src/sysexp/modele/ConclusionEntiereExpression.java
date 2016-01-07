package sysexp.modele;

public class ConclusionEntiereExpression extends FormeEntiere {
	private Noeud valeur;
	
	public ConclusionEntiereExpression(String fait, Comparateur comparateur, Noeud valeur) {
		super(fait, comparateur);
		this.valeur = valeur;
	}

	@Override
	public void accept(VisiteurForme visitor) {
		visitor.visite(this);
	}
	
	public Noeud lireValeur(){
		return this.valeur;
	}
	
	public String toString(){
		return this.lireFait() + " " + this.lireComparateur() + " " + this.valeur;
	}
}
