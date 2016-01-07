package sysexp.modele;

public class PremisseEntiereFait extends FormeEntiere {
	private FaitEntier valeur;
	public PremisseEntiereFait(String fait, Comparateur comparateur, FaitEntier valeur) {
		super(fait, comparateur);
		this.valeur = valeur;
}

	@Override
	public void accept(VisiteurForme visitor) {
		visitor.visite(this);
	}
	
	public FaitEntier lireValeur(){
		return this.valeur;
	}
	
	public String toString(){
		return this.lireFait() + " " + this.lireComparateur() + " " + this.valeur;
	}

}
