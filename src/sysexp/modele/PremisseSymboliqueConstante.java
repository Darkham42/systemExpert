package sysexp.modele;

public class PremisseSymboliqueConstante extends FormeSymbolique {

	public PremisseSymboliqueConstante(String fait, Comparateur comparateur, String valeur) {
		super(fait, comparateur, valeur);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(VisiteurForme visitor) {
		visitor.visite(this);
	}

}
